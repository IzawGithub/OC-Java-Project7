package com.poseidoncapitalsolutions.aggregator.domains.internal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PasswordTest {
    @ParameterizedTest
    @ValueSource(
            strings = {
                "Not8!", // At least 8 characters
                "not_uppercase1!", // At least 1 uppercase
                "NotOneDigit!", // At least 1 digits
                "Not1Symbol" // At least 1 symbol
            })
    void passwordInvalid(String password) {
        final var passwordBuilder = Password.builder().password(password);
        assertTrue(passwordBuilder.tryBuild().isErr());
        assertThrows(RuntimeException.class, passwordBuilder::build);
    }

    @Test
    void passwordValid() {
        final var passwordBuilder = Password.builder().password("Val1dPassword!");
        assertTrue(passwordBuilder.tryBuild().isOK());
        assertDoesNotThrow(passwordBuilder::build);
    }

    @Test
    void passwordMatch() {
        final var expected = "Val1dPassword!";
        final var password = Password.builder().password(expected).build();
        assertTrue(password.matches(expected));
    }
}
