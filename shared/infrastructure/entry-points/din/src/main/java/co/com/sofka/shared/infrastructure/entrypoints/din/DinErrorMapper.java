package co.com.sofka.shared.infrastructure.entrypoints.din;

import co.com.sofka.shared.domain.usecases.Error;

public final class DinErrorMapper {
    public static DinError fromUseCaseToDinError(Error error, String... details) {
        return new DinError(
                error.getType().name(),
                error.getSource(),
                error.getCode(),
                error.getProviderErrorCode(),
                error.getMessage(),
                String.format(error.getDetail(), (Object[]) details)
        );
    }

    public static <T> DinResponse<T> fromUseCaseToDinResponse(DinHeader dinHeader, Error error, String... details) {
        return new DinResponse<>(dinHeader, fromUseCaseToDinError(error, details));
    }
}
