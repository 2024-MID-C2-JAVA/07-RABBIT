package co.com.sofka.cuentaflex.domain.usecases.accountservice.deposit.branch;

import co.com.sofka.cuentaflex.domain.drivenports.messaging.BusPort;
import co.com.sofka.cuentaflex.domain.drivenports.messaging.Message;
import co.com.sofka.cuentaflex.domain.drivenports.repositories.AccountRepository;
import co.com.sofka.cuentaflex.domain.models.*;
import co.com.sofka.cuentaflex.domain.usecases.accountservice.transactions.FeesValues;
import co.com.sofka.cuentaflex.domain.usecases.accountservice.transactions.TransactionDoneResponse;
import co.com.sofka.cuentaflex.domain.usecases.accountservice.transactions.TransactionErrors;
import co.com.sofka.cuentaflex.domain.usecases.accountservice.transactions.UnidirectionalTransactionRequest;
import co.com.sofka.shared.domain.usecases.ResultWith;
import co.com.sofka.shared.domain.usecases.UseCase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public final class DepositFromBranchUseCase implements UseCase<UnidirectionalTransactionRequest, ResultWith<TransactionDoneResponse>> {
    private final FeesValues feesValues;
    private final AccountRepository accountRepository;
    private final BusPort busPort;

    public DepositFromBranchUseCase(FeesValues feesValues, AccountRepository accountRepository, BusPort busPort) {
        this.feesValues = feesValues;
        this.accountRepository = accountRepository;
        this.busPort = busPort;
    }

    @Override
    public ResultWith<TransactionDoneResponse> execute(UnidirectionalTransactionRequest request) {
        Message transactionBeganMessage = new Message(
                String.format(
                        "Transaction for account %s has begun.",
                        request.getAccountId()
                ),
                String.format(
                        "Amount: $%s",
                        request.getAmount()
                )
        );
        this.busPort.send(transactionBeganMessage);

        BigDecimal twoDecimalsAmount = request.getAmount().setScale(2, RoundingMode.HALF_UP);
        BigDecimal fee = this.feesValues.getDepositFromBranchFee();

        if (twoDecimalsAmount.compareTo(fee) <= 0) {
            Message transactionFailedMessage = new Message(
                    String.format(
                            "Transaction for account %s has failed",
                            request.getAccountId()
                    ),
                    String.format(
                            "Invalid amount: $%s",
                            request.getAmount()
                    )
            );
            this.busPort.send(transactionFailedMessage);

            return ResultWith.failure(TransactionErrors.INVALID_AMOUNT);
        }

        Account account = accountRepository.getByIdAndCustomerId(request.getAccountId(), request.getCustomerId());

        if (account == null) {
            Message transactionFailedMessage = new Message(
                    String.format(
                            "Transaction for account %s has failed",
                            request.getAccountId()
                    ),
                    String.format(
                            "Account not found: $%s",
                            request.getAccountId()
                    )
            );
            this.busPort.send(transactionFailedMessage);
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

        Message successMessage = new Message(
                String.format(
                        "Transaction for account %s has been completed",
                        request.getAccountId()
                ),
                String.format(
                        "Amount: $%s",
                        request.getAmount()
                )
        );
        this.busPort.send(successMessage);

        return ResultWith.success(response);
    }
}
