package co.com.sofka.cuentaflex.domain.drivenports.messaging;

public final class Message {
    private String message;
    private String detail;

    public Message() {
    }

    public Message(String message, String detail) {
        this.message = message;
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
