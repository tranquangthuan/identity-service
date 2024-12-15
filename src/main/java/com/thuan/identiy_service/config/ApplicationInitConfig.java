package com.thuan.identiy_service.config;

import com.thuan.identiy_service.entity.User;
import com.thuan.identiy_service.enums.Role;
import com.thuan.identiy_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@Slf4j
public class ApplicationInitConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (!userRepository.existsByUsername("admin")) {
                User user = User.builder()
                        .username("admin")
                        //.roles(Set.of(Role.ADMIN.name()))
                        .firstName("admin")
                        .lastName("admin")
                        .password(passwordEncoder.encode("password"))
                        .build();
                userRepository.save(user);
                log.info("admin user has been created");
            }
        };
    }
}
