package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.common.mappers;

import co.com.sofka.cuentaflex.domain.usecases.common.transactions.UnidirectionalTransactionRequest;
import co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.common.dtos.UnidirectionalTransactionDto;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinRequest;

public final class UnidirectionalTransactionMapper {
    public static UnidirectionalTransactionRequest fromDinToUseCaseRequest(DinRequest<UnidirectionalTransactionDto> dto) {
        return new UnidirectionalTransactionRequest(
                dto.getDinBody().getCustomerId(),
                dto.getDinBody().getAccountId(),
                dto.getDinBody().getAmount()
        );
    }
}
