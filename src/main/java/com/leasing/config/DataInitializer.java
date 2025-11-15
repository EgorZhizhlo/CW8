package com.leasing.config;

import com.leasing.model.User;
import com.leasing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // Если администратор уже существует — ничего не делаем
        boolean adminExists = userRepository.existsByRole(User.Role.ADMIN);

        if (!adminExists) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("ADMIN@_1"))
                    .role(User.Role.ADMIN)
                    .build();

            userRepository.save(admin);

            System.out.println("========== ADMIN CREATED ==========");
            System.out.println("Login: admin");
            System.out.println("Password: ADMIN@_1");
            System.out.println("===================================");
        }
    }
}
