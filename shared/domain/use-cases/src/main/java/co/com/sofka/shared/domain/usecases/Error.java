package co.com.sofka.shared.domain.usecases;

public final class Error {
    private final ErrorType type;
    private final String source;
    private final String code;
    private final String providerErrorCode;
    private final String message;
    private final String detail;

    public static final Error NONE = new Error(
            ErrorType.SUCCESS,
            "CuentaFlex accounts service",
            "0000",
            null,
            "Successful operation",
            "Successful operation"
    );

    public Error(ErrorType type, String source, String code, String providerErrorCode, String message, String detail) {
        this.type = type;
        this.source = source;
        this.code = code;
        this.providerErrorCode = providerErrorCode;
        this.message = message;
        this.detail = detail;
    }

    public ErrorType getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public String getCode() {
        return code;
    }

    public String getProviderErrorCode() {
        return providerErrorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }
}
