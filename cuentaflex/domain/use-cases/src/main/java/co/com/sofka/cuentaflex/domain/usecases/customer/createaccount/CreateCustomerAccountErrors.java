package co.com.sofka.cuentaflex.domain.usecases.customer.createaccount;

import co.com.sofka.shared.domain.usecases.Error;
import co.com.sofka.shared.domain.usecases.ErrorType;

public final class CreateCustomerAccountErrors {
    public static final Error CUSTOMER_NOT_FOUND = new Error(
            ErrorType.WARNING,
            "Database",
            "1008",
            null,
            "Customer not found.",
            "The customer with the id %s was not found in the requested context."
    );

    public static final Error NEGATIVE_INITIAL_AMOUNT = new Error(
            ErrorType.WARNING,
            "User",
            "3001",
            null,
            "The amount to deposit doesn't reach the minimum value",
            "The amount to deposit must be greater than $0.00"
    );
}
