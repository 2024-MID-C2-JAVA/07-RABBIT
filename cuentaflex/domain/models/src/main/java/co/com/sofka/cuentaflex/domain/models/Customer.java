package co.com.sofka.cuentaflex.domain.models;

import co.com.sofka.shared.domain.models.BaseAuditableModel;
import co.com.sofka.shared.domain.models.SoftDeletable;

import java.time.LocalDateTime;

public final class Customer extends BaseAuditableModel implements SoftDeletable {
    private String firstName;
    private String lastName;
    private String identification;
    private boolean isDeleted;

    public Customer(String id, String firstName, String lastName, String identification, boolean isDeleted) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.identification = identification;
        this.isDeleted = isDeleted;
    }

    public Customer(String id, String firstName, String lastName, String identification) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.identification = identification;
        this.isDeleted = false;
    }

    public Customer(String id, LocalDateTime createdAt, String firstName, String lastName, String identification, boolean isDeleted) {
        super(id, createdAt);
        this.firstName = firstName;
        this.lastName = lastName;
        this.identification = identification;
        this.isDeleted = isDeleted;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @Override
    public boolean isDeleted() {
        return isDeleted;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }
}
