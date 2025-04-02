package com.poseidoncapitalsolutions.aggregator.security;

import com.poseidoncapitalsolutions.aggregator.utils.Password;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    PasswordEncoder passwordEncoder() {
        final var parameters = Password.builder();

        return new Argon2PasswordEncoder(
                parameters.getSaltLength(),
                parameters.getDigestLength(),
                parameters.getParallelism(),
                parameters.getMemory(),
                parameters.getIterations());
    }
}
