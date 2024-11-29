package co.com.sofka.shared.domain.usecases;


public final class ResultWith<T> {
    private final boolean isSuccess;
    private final Error error;
    private final T value;

    private ResultWith(boolean isSuccess, Error error, T value) {
        this.isSuccess = isSuccess;
        this.error = error;
        this.value = value;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isFailure() {
        return !isSuccess;
    }

    public Error getError() {
        return error;
    }

    public T getValue() {
        return value;
    }

    public static <T> ResultWith<T> success(T value) {
        return new ResultWith<>(true, Error.NONE, value);
    }

    public static <T> ResultWith<T> failure(Error error) {
        return new ResultWith<>(false, error, null);
    }
}
