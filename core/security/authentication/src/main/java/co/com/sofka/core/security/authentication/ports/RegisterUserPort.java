package co.com.sofka.core.security.authentication.ports;

import co.com.sofka.core.cryptography.aes.AESCipher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class RegisterUserPort {
    protected final PasswordEncoder passwordEncoder;
    protected final UserDetailsService userDetailsService;

    public RegisterUserPort(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public RegisterUserResponse registerUser(RegisterUserRequest request) throws UserAlreadyExistsException {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            throw new UserAlreadyExistsException("The user with the username " + userDetails.getUsername() + " already exists.");
        } catch (UsernameNotFoundException e) {
            String decryptedIdentification = AESCipher.decryptFromBase64(request.getEncryptedIdentification(), request.getSymmetricKey(), request.getInitializationVector());
            String decryptedPassword = AESCipher.decryptFromBase64(request.getEncryptedPassword(), request.getSymmetricKey(), request.getInitializationVector());

            RegisterUserRequest plainRequest = new RegisterUserRequest(
                    request.getFirstName(),
                    request.getLastName(),
                    decryptedIdentification,
                    request.getUsername(),
                    passwordEncoder.encode(decryptedPassword),
                    null,
                    null
            );

            return this.handleRegisterUser(plainRequest);
        }
    }

    protected abstract RegisterUserResponse handleRegisterUser(RegisterUserRequest request);
}
