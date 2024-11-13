package co.com.sofka.cuentaflex.domain.usecases.common.transactions;

import co.com.sofka.shared.domain.usecases.Error;
import co.com.sofka.shared.domain.usecases.ErrorType;

public class TransactionErrors {
    public static final Error ACCOUNT_NOT_FOUND = new co.com.sofka.shared.domain.usecases.Error(
                ErrorType.WARNING,
            "Database",
            "1002",
            null,
            "The account was not found.",
            "The account with the id %s was not found in the requested context."

    );

    public static final Error INVALID_AMOUNT = new Error(
            ErrorType.WARNING,
            "User",
            "3001",
            null,
            "The amount to deposit doesn't reach the minimum value",
            "The amount to deposit must be greater than $%s"
    );

    public static final Error INSUFFICIENT_FUNDS = new Error(
            ErrorType.WARNING,
            "User",
            "1003",
            null,
            "Insufficient funds to do the transaction",
            "The account doesn't have enough funds to do the transaction"
    );
}
