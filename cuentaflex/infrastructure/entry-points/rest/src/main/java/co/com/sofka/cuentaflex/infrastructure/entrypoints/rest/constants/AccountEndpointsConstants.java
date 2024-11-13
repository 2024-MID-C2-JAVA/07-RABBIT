package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.constants;

public final class AccountEndpointsConstants {
    public static final String CUSTOMER_ACCOUNTS_BASE_URL = "/customers/accounts";

    public static final String BRANCH_DEPOSIT_TO_ACCOUNT_URL = CUSTOMER_ACCOUNTS_BASE_URL + "/branch-deposits";
    public static final String ATM_DEPOSIT_TO_ACCOUNT_URL = CUSTOMER_ACCOUNTS_BASE_URL + "/atm-deposits";
    public static final String EXTERNAL_DEPOSIT_TO_ACCOUNT_URL = CUSTOMER_ACCOUNTS_BASE_URL + "/external-deposits";

    public static final String ONLINE_PURCHASE_TO_ACCOUNT_URL = CUSTOMER_ACCOUNTS_BASE_URL + "/online-purchases";
    public static final String PHYSICAL_PURCHASE_TO_ACCOUNT_URL = CUSTOMER_ACCOUNTS_BASE_URL + "/physical-purchases";

    public static final String ATM_WITHDRAWAL_TO_ACCOUNT_URL = CUSTOMER_ACCOUNTS_BASE_URL + "/atm-withdrawals";
}
