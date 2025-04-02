package com.poseidoncapitalsolutions.aggregator.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.poseidoncapitalsolutions.aggregator.utils.errors.EErrorPassword;

import lombok.SneakyThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PasswordTest {
    static final String VALID_PASSWORD = "Val1dPassword!";

    @ParameterizedTest
    @ValueSource(
            strings = {
                "Not8!", // At least 8 characters
                "not_uppercase1!", // At least 1 uppercase
                "NotOneDigit!", // At least 1 digits
                "Not1Symbol" // At least 1 symbol
            })
    void passwordInvalid(String password) {
        final var passwordBuilder = Password.builder().secret(password);

        assertTrue(passwordBuilder.tryBuild().isErr());
        assertThrows(EErrorPassword.Strength.class, passwordBuilder::build);
    }

    @Test
    void passwordValid() {
        final var passwordBuilder = Password.builder().secret(VALID_PASSWORD);
        assertTrue(passwordBuilder.tryBuild().isOK());
        assertDoesNotThrow(passwordBuilder::build);
    }

    @Test
    @SneakyThrows
    void passwordMatch() {
        final var expected =
                Password.builder().secret(VALID_PASSWORD).salt("0xDEADBEEF").build();
        final var actual =
                Password.builder().secret(VALID_PASSWORD).salt("0xDEADBEEF").build();

        assertEquals(expected, actual);
    }
}
