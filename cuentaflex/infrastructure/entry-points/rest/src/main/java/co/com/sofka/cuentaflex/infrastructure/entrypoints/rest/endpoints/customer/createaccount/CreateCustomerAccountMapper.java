package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.customer.createaccount;

import co.com.sofka.cuentaflex.domain.usecases.customer.createaccount.CreateCustomerAccountRequest;
import co.com.sofka.cuentaflex.domain.usecases.customer.createaccount.CreateCustomerAccountResponse;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinHeader;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinRequest;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinResponse;

import java.math.BigDecimal;

public final class CreateCustomerAccountMapper {
    public static CreateCustomerAccountRequest fromDinToUseCaseRequest(DinRequest<CreateCustomerAccountRequestDto> dto) {
        BigDecimal amount = dto.getDinBody().getAmount() == null ? BigDecimal.ZERO : dto.getDinBody().getAmount();
        return new CreateCustomerAccountRequest(
                dto.getDinBody().getCustomerId(),
                amount,
                dto.getDinHeader().getInitializationVector(),
                dto.getDinHeader().getSymmetricKey()
        );
    }

    public static DinResponse<CreateCustomerAccountResponseDto> fromUseCaseToDinResponse(
            DinHeader dinHeader,
            CreateCustomerAccountResponse response
    ) {
        CreateCustomerAccountResponseDto responseDto = new CreateCustomerAccountResponseDto(
                response.getAccountId(),
                response.getEncryptedNumber(),
                response.getAmount()
        );

        return new DinResponse<>(dinHeader, responseDto);
    }
}
