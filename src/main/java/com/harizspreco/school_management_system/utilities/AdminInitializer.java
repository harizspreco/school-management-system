package com.harizspreco.school_management_system.utilities;

import com.harizspreco.school_management_system.entity.Administrator;
import com.harizspreco.school_management_system.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {
    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminInitializer(AdministratorService administratorService, PasswordEncoder passwordEncoder) {
        this.administratorService = administratorService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (administratorService.count() == 0) {
            Administrator admin = new Administrator();
            admin.setEmail(System.getenv("ADMIN_USERNAME"));
            admin.setPassword(passwordEncoder.encode(System.getenv("ADMIN_PASSWORD")));
            admin.setFirstName("Admin");
            admin.setLastName("User");
            administratorService.save(admin);
            System.out.println("Default administrator created!");
        }
    }
}

