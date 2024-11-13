package co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PostgresUserDetailsService implements UserDetailsService {
    private final JpaCustomerUserDetailsRepository jpaCustomerUserDetailsRepository;

    public PostgresUserDetailsService(JpaCustomerUserDetailsRepository jpaCustomerUserDetailsRepository) {
        this.jpaCustomerUserDetailsRepository = jpaCustomerUserDetailsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.jpaCustomerUserDetailsRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
