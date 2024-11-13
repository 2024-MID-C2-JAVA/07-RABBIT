package co.com.sofka.cuentaflex.domain.usecases.deposit.externalaccount;

import co.com.sofka.core.cryptography.aes.AESCipher;
import co.com.sofka.cuentaflex.domain.drivenports.repositories.AccountRepository;
import co.com.sofka.cuentaflex.domain.models.*;
import co.com.sofka.cuentaflex.domain.usecases.common.transactions.FeesValues;
import co.com.sofka.cuentaflex.domain.usecases.common.transactions.TransactionErrors;
import co.com.sofka.shared.domain.usecases.ResultWith;
import co.com.sofka.shared.domain.usecases.UseCase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public final class DepositToExternalAccountUseCase implements UseCase<DepositToExternalAccountRequest, ResultWith<DepositToExternalAccountResponse>> {
    private final AccountRepository accountRepository;
    private final FeesValues feesValues;

    public DepositToExternalAccountUseCase(AccountRepository accountRepository, FeesValues feesValues) {
        this.accountRepository = accountRepository;
        this.feesValues = feesValues;
    }

    @Override
    public ResultWith<DepositToExternalAccountResponse> execute(DepositToExternalAccountRequest request) {
        BigDecimal twoDecimalsAmount = request.getAmount().setScale(2, RoundingMode.HALF_UP);
        BigDecimal fee = this.feesValues.getDepositToExternalAccountFee();

        if (twoDecimalsAmount.compareTo(fee) <= 0) {
            return ResultWith.failure(TransactionErrors.INVALID_AMOUNT);
        }

        Account fromAccount = accountRepository.getByIdAndCustomerId(request.getAccountId(), request.getCustomerId());

        if (fromAccount == null) {
            return ResultWith.failure(TransactionErrors.ACCOUNT_NOT_FOUND);
        }

        int decryptedAccountNumber = Integer.parseInt(AESCipher.decryptFromBase64(request.getEncryptedAccountNumberToDeposit(), request.getSecretKey(), request.getInitializationVector()));
        Account toAccount = accountRepository.getByAccountNumber(decryptedAccountNumber);

        if (toAccount == null) {
            return ResultWith.failure(TransactionErrors.ACCOUNT_NOT_FOUND);
        }

        Transaction transaction = new Transaction(
                null,
                twoDecimalsAmount,
                fee,
                TransactionType.EXTERNAL_ACCOUNT_DEPOSIT
        );

        AccountMovement fromMovement = new AccountMovement(
                transaction,
                AccountRole.PAYROLL
        );

        AccountMovement toMovement = new AccountMovement(
                transaction,
                AccountRole.SUPPLIER
        );

        fromAccount.addAccountMovement(fromMovement);
        toAccount.addAccountMovement(toMovement);

        fromAccount.setAmount(fromAccount.getAmount().subtract(twoDecimalsAmount).subtract(fee));
        toAccount.setAmount(toAccount.getAmount().add(twoDecimalsAmount));

        if(fromAccount.getAmount().compareTo(BigDecimal.ZERO) < 0 || toAccount.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            return ResultWith.failure(TransactionErrors.INSUFFICIENT_FUNDS);
        }

        List<Account> updatedAccounts = this.accountRepository.updateMany(fromAccount, toAccount);
        Transaction firstTransaction = updatedAccounts.getFirst().getTransactionHistory().getLastMovement().getTransaction();
        DepositToExternalAccountResponse response = new DepositToExternalAccountResponse(
                firstTransaction.getId(),
                firstTransaction.getAmount(),
                firstTransaction.getCost(),
                firstTransaction.getTimestamp(),
                AESCipher.encryptToBase64(String.valueOf(fromAccount.getNumber()), request.getSecretKey(), request.getInitializationVector()),
                AESCipher.encryptToBase64(String.valueOf(toAccount.getNumber()), request.getSecretKey(), request.getInitializationVector())
        );

        return ResultWith.success(response);
    }
}
