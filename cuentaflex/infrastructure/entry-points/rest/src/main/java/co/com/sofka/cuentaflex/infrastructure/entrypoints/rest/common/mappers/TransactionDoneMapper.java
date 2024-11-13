package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.common.mappers;

import co.com.sofka.cuentaflex.domain.usecases.common.transactions.TransactionDoneResponse;
import co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.common.dtos.TransactionDoneDto;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinHeader;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinResponse;

public final class TransactionDoneMapper {
    public static DinResponse<TransactionDoneDto> fromUseCaseToDinResponse(DinHeader dinHeader, TransactionDoneResponse response) {
        TransactionDoneDto body = new TransactionDoneDto(
                response.getTransactionId(),
                response.getAmount(),
                response.getCost(),
                response.getTimestamp()
        );

        return new DinResponse<>(dinHeader, body);
    }
}
