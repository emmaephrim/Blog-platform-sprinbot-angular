package com.blog_platform.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class DefaultUserCreator {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    public ApplicationRunner createDefaultAdmin() {
        return args -> {
            if (userRepository.findByUsername("admin") == null) {
                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword(passwordEncoder.encode("password"));
                adminUser.setEmail("admin@admin.com");
                adminUser.setRole("ROLE_ADMIN");
                adminUser.setCreatedAt(new Date());

                userRepository.save(adminUser);
                System.out.println(
                        "Admin user created with username: admin ,password: password and email: admin@admn.com");
            } else {
                System.out.println("Admin user already exists, skipping creation....");
            }
        };
    }

}
