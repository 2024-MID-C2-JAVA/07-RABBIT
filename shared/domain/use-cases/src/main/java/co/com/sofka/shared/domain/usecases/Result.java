package co.com.sofka.shared.domain.usecases;

public final class Result {
    private final boolean isSuccess;
    private final Error error;

    private Result(boolean isSuccess, Error error) {
        this.isSuccess = isSuccess;
        this.error = error;
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

    public static Result success() {
        return new Result(true, Error.NONE);
    }

    public static Result failure(Error error) {
        return new Result(false, error);
    }
}