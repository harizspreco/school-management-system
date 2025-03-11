package com.harizspreco.school_management_system.utilities;

import com.harizspreco.school_management_system.entity.Administrator;
import com.harizspreco.school_management_system.entity.User;
import com.harizspreco.school_management_system.entity.Role;
import com.harizspreco.school_management_system.repository.RoleRepository;
import com.harizspreco.school_management_system.repository.UserRepository;
import com.harizspreco.school_management_system.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            // Kreiraj admin korisnika
            User adminUser = new User();
            adminUser.setEmail(System.getenv("ADMIN_USERNAME"));
            adminUser.setPassword(passwordEncoder.encode(System.getenv("ADMIN_PASSWORD")));

            // Dodijeli mu rolu ADMIN
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setName("ROLE_ADMIN");
                        return roleRepository.save(newRole);
                    });

            adminUser.getRoles().add(adminRole);
            userRepository.save(adminUser);

            // Kreiraj administratora i poveži ga sa User entitetom
            Administrator admin = new Administrator();
            admin.setUser(adminUser);
            admin.setFirstName("Admin");
            admin.setLastName("User");
            administratorService.save(admin);

            System.out.println("Default administrator created!");
        }
    }
}
