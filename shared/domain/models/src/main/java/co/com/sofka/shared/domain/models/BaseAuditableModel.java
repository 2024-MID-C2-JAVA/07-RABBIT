package co.com.sofka.shared.domain.models;

import java.time.LocalDateTime;

public abstract class BaseAuditableModel {
    private String id;
    private LocalDateTime createdAt;

    protected BaseAuditableModel(String id, LocalDateTime createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    protected BaseAuditableModel(String id) {
        this(id, LocalDateTime.now());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
