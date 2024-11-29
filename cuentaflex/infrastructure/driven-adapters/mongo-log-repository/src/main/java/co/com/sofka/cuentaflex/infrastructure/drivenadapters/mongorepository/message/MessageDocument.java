package co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository.message;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "logs")
public class MessageDocument {
    @Id
    private String id;
    private String message;
    private String detail;
    private LocalDateTime createdAt = LocalDateTime.now();

    public MessageDocument() {
    }

    public MessageDocument(String id, String message, String detail) {
        this.id = id;
        this.message = message;
        this.detail = detail;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
