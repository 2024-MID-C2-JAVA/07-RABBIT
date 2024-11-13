package co.sofka.middleware;



public class BadRequestException extends RuntimeException {

    private Response bodyOut;

    public BadRequestException() {
        super();
    }

    public BadRequestException(final String message) {
        super(message);
    }

    public BadRequestException(Response bodyOut) {
        this.bodyOut = bodyOut;
    }

    public Response getBodyOut() {
        return bodyOut;
    }

    public void setBodyOut(Response bodyOut) {
        this.bodyOut = bodyOut;
    }
}
