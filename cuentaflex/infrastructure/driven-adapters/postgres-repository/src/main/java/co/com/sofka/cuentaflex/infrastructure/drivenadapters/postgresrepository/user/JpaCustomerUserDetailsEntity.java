package co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.user;

import co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.customer.JpaCustomerEntity;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "customer_user")
public class JpaCustomerUserDetailsEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private JpaCustomerEntity customer;

    public JpaCustomerUserDetailsEntity() {
    }

    public JpaCustomerUserDetailsEntity(int id, String username, String password, JpaCustomerEntity customer) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.customer = customer;
    }

    private int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public JpaCustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(JpaCustomerEntity customer) {
        this.customer = customer;
    }
}
