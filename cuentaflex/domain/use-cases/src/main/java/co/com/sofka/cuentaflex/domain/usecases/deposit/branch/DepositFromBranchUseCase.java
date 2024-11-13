package co.com.sofka.cuentaflex.domain.usecases.deposit.branch;

import co.com.sofka.cuentaflex.domain.drivenports.repositories.AccountRepository;
import co.com.sofka.cuentaflex.domain.models.*;
import co.com.sofka.cuentaflex.domain.usecases.common.transactions.FeesValues;
import co.com.sofka.cuentaflex.domain.usecases.common.transactions.TransactionDoneResponse;
import co.com.sofka.cuentaflex.domain.usecases.common.transactions.TransactionErrors;
import co.com.sofka.cuentaflex.domain.usecases.common.transactions.UnidirectionalTransactionRequest;
import co.com.sofka.shared.domain.usecases.ResultWith;
import co.com.sofka.shared.domain.usecases.UseCase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public final class DepositFromBranchUseCase implements UseCase<UnidirectionalTransactionRequest, ResultWith<TransactionDoneResponse>> {
    private final FeesValues feesValues;
    private final AccountRepository accountRepository;

    public DepositFromBranchUseCase(FeesValues feesValues, AccountRepository accountRepository) {
        this.feesValues = feesValues;
        this.accountRepository = accountRepository;
    }

    @Override
    public ResultWith<TransactionDoneResponse> execute(UnidirectionalTransactionRequest request) {
        BigDecimal twoDecimalsAmount = request.getAmount().setScale(2, RoundingMode.HALF_UP);
        BigDecimal fee = this.feesValues.getDepositFromBranchFee();

        if (twoDecimalsAmount.compareTo(fee) <= 0) {
            return ResultWith.failure(TransactionErrors.INVALID_AMOUNT);
        }

        Account account = accountRepository.getByIdAndCustomerId(request.getAccountId(), request.getCustomerId());

        if (account == null) {
            return ResultWith.failure(TransactionErrors.ACCOUNT_NOT_FOUND);
        }

        Transaction transaction = new Transaction(
                null,
                twoDecimalsAmount,
                fee,
                TransactionType.BRANCH_DEPOSIT
        );

        AccountMovement movement = new AccountMovement(
                transaction,
                AccountRole.SUPPLIER
        );

        account.addAccountMovement(movement);

        account.setAmount(account.getAmount().add(twoDecimalsAmount).subtract(fee));

        List<Account> updatedAccounts = this.accountRepository.updateMany(account);
        Transaction firstTransaction = updatedAccounts.getFirst().getTransactionHistory().getLastMovement().getTransaction();
        TransactionDoneResponse response = new TransactionDoneResponse(
                firstTransaction.getId(),
                firstTransaction.getAmount(),
                firstTransaction.getCost(),
                firstTransaction.getTimestamp()
        );

        return ResultWith.success(response);
    }
}
