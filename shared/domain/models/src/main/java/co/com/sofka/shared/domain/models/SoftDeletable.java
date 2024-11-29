package co.com.sofka.shared.domain.models;

public interface SoftDeletable {
    boolean isDeleted();
    void setDeleted(boolean deleted);
}
