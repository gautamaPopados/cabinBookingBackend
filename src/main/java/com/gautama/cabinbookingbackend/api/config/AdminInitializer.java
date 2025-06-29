package com.gautama.cabinbookingbackend.api.config;

import com.gautama.cabinbookingbackend.api.enums.Role;
import com.gautama.cabinbookingbackend.core.model.User;
import com.gautama.cabinbookingbackend.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
            User admin = new User();
            admin.setEmail("admin@example.com");
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setPhone("123456789");
            admin.setPassword(passwordEncoder.encode("admin123!"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
    }
}