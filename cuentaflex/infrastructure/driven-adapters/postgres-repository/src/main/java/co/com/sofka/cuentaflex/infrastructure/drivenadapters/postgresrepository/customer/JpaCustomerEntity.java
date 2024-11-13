package co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.customer;

import co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.account.JpaAccountEntity;
import co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.user.JpaCustomerUserDetailsEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "customer")
@Table(name = "customer")
public class JpaCustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "identification")
    private String identification;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<JpaAccountEntity> accounts;

    @OneToOne(mappedBy = "customer", fetch = FetchType.EAGER)
    private JpaCustomerUserDetailsEntity userDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<JpaAccountEntity> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<JpaAccountEntity> accounts) {
        this.accounts = accounts;
    }

    public JpaCustomerUserDetailsEntity getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(JpaCustomerUserDetailsEntity userDetails) {
        this.userDetails = userDetails;
    }
}
