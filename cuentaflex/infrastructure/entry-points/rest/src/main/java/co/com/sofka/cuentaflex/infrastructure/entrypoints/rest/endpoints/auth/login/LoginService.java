package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.auth.login;

import co.com.sofka.core.cryptography.aes.AESCipher;
import co.com.sofka.core.security.authentication.ports.IdentityProviderPort;
import co.com.sofka.core.security.jwt.JwtService;
import co.com.sofka.shared.domain.usecases.ErrorType;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinError;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinRequest;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public final class LoginService {
    private final AuthenticationManager authenticationManager;
    private final IdentityProviderPort identityProviderPort;
    private final JwtService jwtService;

    public LoginService(AuthenticationManager authenticationManager, IdentityProviderPort identityProviderPort, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.identityProviderPort = identityProviderPort;
        this.jwtService = jwtService;
    }

    public ResponseEntity<DinResponse<LoginResponseDto>> login(DinRequest<LoginRequestDto> request) {
        try {
            String username = request.getDinBody().getUsername();
            String decryptedPassword =
                    AESCipher.decryptFromBase64(
                            request.getDinBody().getEncryptedPassword(),
                            request.getDinHeader().getSymmetricKey(),
                            request.getDinHeader().getInitializationVector()
                    );

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            decryptedPassword
                    )
            );

            String customerId = identityProviderPort.getUserIdentity(username);
            Map<String, Object> claims = new HashMap<>();
            claims.put("sub", customerId);
            claims.put("username", username);

            String token = jwtService.generateToken(claims);

            return ResponseEntity.ok(new DinResponse<>(
                    request.getDinHeader(),
                    new LoginResponseDto(token)
            ));
        } catch (AuthenticationException e) {
            DinResponse<LoginResponseDto> response = new DinResponse<>(
                    request.getDinHeader(),
                    new DinError(
                            ErrorType.ERROR.name(),
                            "User",
                            "1006",
                            null,
                            "Wrong credentials",
                            "Wrong credentials"
                    )
            );

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
