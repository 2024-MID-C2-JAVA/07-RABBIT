package co.com.sofka.cuentaflex.business.usecases.deposit.atm;

import co.com.sofka.cuentaflex.business.drivenports.repositories.AccountRepository;
import co.com.sofka.cuentaflex.business.models.*;
import co.com.sofka.cuentaflex.business.usecases.common.transactions.FeesValues;
import co.com.sofka.cuentaflex.business.usecases.common.transactions.TransactionDoneResponse;
import co.com.sofka.cuentaflex.business.usecases.common.transactions.TransactionErrors;
import co.com.sofka.cuentaflex.business.usecases.common.transactions.UnidirectionalTransactionRequest;
import co.com.sofka.shared.business.usecases.ResultWith;
import co.com.sofka.shared.business.usecases.UseCase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public final class DepositFromAtmUseCase implements UseCase<UnidirectionalTransactionRequest, ResultWith<TransactionDoneResponse>> {
    private final FeesValues feesValues;
    private final AccountRepository accountRepository;

    public DepositFromAtmUseCase(FeesValues feesValues, AccountRepository accountRepository) {
        this.feesValues = feesValues;
        this.accountRepository = accountRepository;
    }

    @Override
    public ResultWith<TransactionDoneResponse> execute(UnidirectionalTransactionRequest request) {
        BigDecimal twoDecimalsAmount = request.getAmount().setScale(2, RoundingMode.HALF_UP);
        BigDecimal fee = this.feesValues.getDepositFromAtmFee();

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
                TransactionType.ATM_DEPOSIT
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
