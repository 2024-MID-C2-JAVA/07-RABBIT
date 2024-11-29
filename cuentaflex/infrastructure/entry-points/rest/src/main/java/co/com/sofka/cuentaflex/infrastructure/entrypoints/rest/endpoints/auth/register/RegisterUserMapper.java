package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.auth.register;

import co.com.sofka.core.security.authentication.ports.RegisterUserRequest;
import co.com.sofka.core.security.authentication.ports.RegisterUserResponse;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinHeader;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinRequest;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinResponse;

public final class RegisterUserMapper {
    public static RegisterUserRequest fromDinToPortRequest(DinRequest<RegisterUserRequestDto> request) {
        return new RegisterUserRequest(
                request.getDinBody().getFirstName(),
                request.getDinBody().getLastName(),
                request.getDinBody().getEncryptedIdentification(),
                request.getDinBody().getUsername(),
                request.getDinBody().getEncryptedPassword(),
                request.getDinHeader().getInitializationVector(),
                request.getDinHeader().getSymmetricKey()
        );
    }

    public static DinResponse<RegisterUserResponseDto> fromPortToDinResponse(
            DinHeader dinHeader,
            RegisterUserResponse response
    ) {
        return new DinResponse<>(dinHeader, new RegisterUserResponseDto(response.getIdentity()));
    }
}
