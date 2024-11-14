package com.bank.management.enums;

public enum DinErrorCode {

    // GENERIC
    SUCCESS("0", "0000", "Successful operation", "SUCCESS"),
    OPERATION_FAILED("1", "1004", "Operation failed", "ERROR"),
    INTERNAL_SERVER_ERROR("1", "1005", "Internal server error", "ERROR"),
    UNKNOWN_ERROR("1", "1006", "Unknown error", "ERROR"),
    ERROR_ENCRYPTING_DATA("1", "3006", "Error encrypting data", "ERROR"),
    ACCOUNT_DOESNT_BELONG("2", "1007", "The user is not the owner of the source account", "WARNING"),
    BAD_CREDENTIALS("1", "3007", "Authentication failed.", "ERROR"),
    BAD_REQUEST("1", "3008", "Invalid request", "ERROR"),

    // CUSTOMER
    CUSTOMER_NOT_FOUND("2", "1008", "Customer not found", "WARNING"),
    CUSTOMER_DELETED("0", "1009", "Customer deleted successfully", "SUCCESS"),

    // ACCOUNT
    ERROR_CREATING_ACCOUNT("1", "2002", "Error creating bank account", "ERROR"),
    ERROR_DELETING_ACCOUNT("1", "2003", "Error deleting bank account", "ERROR"),
    ERROR_DECRYPTING_ACCOUNT("1", "1001", "Error decrypting the account number", "ERROR"),
    ACCOUNT_NOT_FOUND("2", "1002", "The account does not exist", "WARNING"),

    // TRANSACTIONS
    DEPOSIT_FAILED("1", "3001", "Deposit failed", "ERROR"),
    PURCHASE_FAILED("1", "3003", "Purchase failed", "ERROR"),
    WITHDRAWAL_FAILED("1", "3005", "Withdrawal failed", "ERROR"),
    INSUFFICIENT_FUNDS("2", "1003", "Insufficient funds", "WARNING");

    private final String code;
    private final String errorCodeProvider;
    private final String message;
    private final String type;

    DinErrorCode(String code, String errorCodeProvider, String message, String type) {
        this.code = code;
        this.errorCodeProvider = errorCodeProvider;
        this.message = message;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public String getErrorCodeProvider() {
        return errorCodeProvider;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
