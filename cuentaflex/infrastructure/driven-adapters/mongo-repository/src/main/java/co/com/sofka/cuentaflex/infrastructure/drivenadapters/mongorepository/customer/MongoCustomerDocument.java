package co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository.customer;

import co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository.account.MongoAccountDocument;
import co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository.user.MongoCustomerUserDetailsDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "customers")
public class MongoCustomerDocument {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String identification;
    private LocalDateTime createdAt;
    private boolean isDeleted;
    private List<MongoAccountDocument> accounts;
    private MongoCustomerUserDetailsDocument userDetails;

    public MongoCustomerDocument() {
    }

    public MongoCustomerDocument(String id, String firstName, String lastName, String identification, LocalDateTime createdAt, boolean isDeleted, List<MongoAccountDocument> accounts, MongoCustomerUserDetailsDocument userDetails) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identification = identification;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.accounts = accounts;
        this.userDetails = userDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<MongoAccountDocument> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<MongoAccountDocument> accounts) {
        this.accounts = accounts;
    }

    public MongoCustomerUserDetailsDocument getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(MongoCustomerUserDetailsDocument userDetails) {
        this.userDetails = userDetails;
    }
}
