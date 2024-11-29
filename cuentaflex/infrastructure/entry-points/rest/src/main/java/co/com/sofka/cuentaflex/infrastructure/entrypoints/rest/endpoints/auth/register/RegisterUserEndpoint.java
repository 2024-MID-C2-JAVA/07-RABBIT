package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.auth.register;

import co.com.sofka.core.security.authentication.ports.RegisterUserPort;
import co.com.sofka.core.security.authentication.ports.RegisterUserRequest;
import co.com.sofka.core.security.authentication.ports.RegisterUserResponse;
import co.com.sofka.core.security.authentication.ports.UserAlreadyExistsException;
import co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.constants.AuthEndpoinstConstants;
import co.com.sofka.shared.domain.usecases.Error;
import co.com.sofka.shared.domain.usecases.ErrorType;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinErrorMapper;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinRequest;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthEndpoinstConstants.REGISTER_ENDPOINT)
@Tag(name = "Auth")
public final class RegisterUserEndpoint {
    private final RegisterUserPort registerUserPort;

    private static final Error USER_ALREADY_EXISTS = new Error(ErrorType.WARNING, "User", "4001", null, "Customer already exists", "The user with the username %s already exists");

    public RegisterUserEndpoint(RegisterUserPort registerUserPort) {
        this.registerUserPort = registerUserPort;
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "201"
            ),
            @ApiResponse(
                    responseCode = "409",
                    content = @Content(schema = @Schema(implementation = DinResponse.class))
            )
    })
    @PostMapping
    public ResponseEntity<DinResponse<RegisterUserResponseDto>> registerUser(@RequestBody DinRequest<RegisterUserRequestDto> request) {
        try {
            RegisterUserRequest portRequest = RegisterUserMapper.fromDinToPortRequest(request);
            RegisterUserResponse response = registerUserPort.registerUser(portRequest);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(RegisterUserMapper.fromPortToDinResponse(request.getDinHeader(), response));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT).
                    body(DinErrorMapper.fromUseCaseToDinResponse(
                            request.getDinHeader(),
                            USER_ALREADY_EXISTS,
                            request.getDinBody().getUsername()
                    ));
        }
    }
}
