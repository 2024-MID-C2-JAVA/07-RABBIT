package co.sofka.middleware;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class ErrorMessage {
    private Date timestamp;
    private String code;
    private String message;

    public ErrorMessage(Date timestamp, String code, String message) {
        this.timestamp = timestamp;
        this.code = code;
        this.message = message;
    }

    public ErrorMessage() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
