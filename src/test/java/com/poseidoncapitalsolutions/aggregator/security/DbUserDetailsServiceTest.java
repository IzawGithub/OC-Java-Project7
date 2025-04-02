package com.poseidoncapitalsolutions.aggregator.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.poseidoncapitalsolutions.aggregator.HelperTest;
import com.poseidoncapitalsolutions.aggregator.domains.User;
import com.poseidoncapitalsolutions.aggregator.repositories.UserRepository;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;

@AutoConfigureTestDatabase(replace = Replace.ANY)
@ActiveProfiles("test")
@SpringBootTest
@Transactional
class DbUserDetailsServiceTest {
    private User userJoneDoe = HelperTest.johnDoe();

    @Test
    void loadUserByUsernameNull() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> dbUserDetailsService.loadUserByUsername(null));
    }

    @Test
    void loadUserByUsernameNotExist() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> dbUserDetailsService.loadUserByUsername("0xDEADBEEF"));
    }

    @Test
    void loadUserByUsernameSuccess() {
        userJoneDoe = userRepository.save(userJoneDoe);
        final var actualUserDetails =
                dbUserDetailsService.loadUserByUsername(userJoneDoe.getUsername());

        assertEquals(userJoneDoe.getUsername(), actualUserDetails.getUsername());
        assertEquals(userJoneDoe.getPassword().toString(), actualUserDetails.getPassword());
    }

    // -- Beans --

    @NonNull private final UserRepository userRepository;

    @NonNull private final DbUserDetailsService dbUserDetailsService;

    @Autowired
    DbUserDetailsServiceTest(
            @NonNull final UserRepository userRepository,
            @NonNull final DbUserDetailsService dbUserDetailsService) {
        this.userRepository = userRepository;
        this.dbUserDetailsService = dbUserDetailsService;
    }
}
