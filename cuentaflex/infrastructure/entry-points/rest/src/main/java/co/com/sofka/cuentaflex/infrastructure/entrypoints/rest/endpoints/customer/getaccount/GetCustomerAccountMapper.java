package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.customer.getaccount;

import co.com.sofka.cuentaflex.domain.usecases.customer.getaccount.GetCustomerAccountRequest;
import co.com.sofka.cuentaflex.domain.usecases.customer.getaccount.GetCustomerAccountResponse;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinHeader;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinRequest;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinResponse;

public final class GetCustomerAccountMapper {
    public static GetCustomerAccountRequest fromDinToUseCaseRequest(DinRequest<GetCustomerAccountRequestDto> dto) {
        return new GetCustomerAccountRequest(
                dto.getDinBody().getCustomerId(),
                dto.getDinBody().getAccountId(),
                dto.getDinHeader().getInitializationVector(),
                dto.getDinHeader().getSymmetricKey()
        );
    }

    public static DinResponse<GetCustomerAccountResponseDto> fromUseCaseToDinResponse(
            DinHeader dinHeader,
            GetCustomerAccountResponse response
    ) {
        GetCustomerAccountResponseDto body =  new GetCustomerAccountResponseDto(response.getAccountId(), response.getEncryptedNumber(), response.getAmount());
        return new DinResponse<>(dinHeader, body);
    }
}
