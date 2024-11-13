package co.com.sofka.cuentaflex.domain.usecases.customer.getaccount;

import co.com.sofka.shared.domain.usecases.Error;
import co.com.sofka.shared.domain.usecases.ErrorType;

public final class GetCustomerAccountErrors {
    public static final Error ACCOUNT_NOT_FOUND = new Error(ErrorType.WARNING,
            "Database",
            "1002",
            null,
            "The account was not found.",
            "The account with the id %s was not found in the requested context."
    );
}
