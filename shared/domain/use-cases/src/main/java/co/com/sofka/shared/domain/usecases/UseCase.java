package co.com.sofka.shared.domain.usecases;

public interface UseCase<RequestType, ResponseType> {
    public ResponseType execute(RequestType request);
}
