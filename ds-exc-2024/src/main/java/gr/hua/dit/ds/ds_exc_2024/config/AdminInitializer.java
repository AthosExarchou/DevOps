package gr.hua.dit.ds.ds_exc_2024.config;

/* imports */
import gr.hua.dit.ds.ds_exc_2024.entities.User;
import gr.hua.dit.ds.ds_exc_2024.entities.Role;
import gr.hua.dit.ds.ds_exc_2024.repositories.RoleRepository;
import gr.hua.dit.ds.ds_exc_2024.repositories.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class AdminInitializer {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AdminInitializer(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createDefaultAdminUser() {
        Optional<User> defaultAdmin = userRepository.findByUsername("admin");

        /* creates default admin user if not exists */
        if (defaultAdmin.isEmpty()) {
            User admin = new User("admin", "admin@gmail.com", passwordEncoder.encode("admin"));
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new IllegalStateException("ROLE_ADMIN not found in database"));

            admin.getRoles().add(adminRole);
            userRepository.save(admin);
            System.out.println("Default admin user created.");
        }
    }
}
