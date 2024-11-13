package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.deposit.externalaccount;

import co.com.sofka.cuentaflex.domain.usecases.deposit.externalaccount.DepositToExternalAccountRequest;
import co.com.sofka.cuentaflex.domain.usecases.deposit.externalaccount.DepositToExternalAccountResponse;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinHeader;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinRequest;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinResponse;

public final class DepositToExternalAccountMapper {
    public static DepositToExternalAccountRequest fromDinToUseCaseRequest(DinRequest<DepositToExternalAccountRequestDto> requestDto) {
        return new DepositToExternalAccountRequest(
                requestDto.getDinBody().getCustomerId(),
                requestDto.getDinBody().getAccountId(),
                requestDto.getDinBody().getAmount(),
                requestDto.getDinBody().getEncryptedAccountNumberToDeposit(),
                requestDto.getDinHeader().getInitializationVector(),
                requestDto.getDinHeader().getSymmetricKey()
        );
    }

    public static DinResponse<DepositToExternalAccountResponseDto> fromUseCaseToDinResponse(
            DinHeader dinHeader,
            DepositToExternalAccountResponse response
    ) {
        DepositToExternalAccountResponseDto responseDto = new DepositToExternalAccountResponseDto(
                response.getTransactionId(),
                response.getAmount(),
                response.getCost(),
                response.getTimestamp(),
                response.getEncryptedPayrollAccountNumber(),
                response.getEncryptedSupplierAccountNumber()
        );

        return new DinResponse<>(dinHeader, responseDto);
    }
}
