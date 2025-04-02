package com.poseidoncapitalsolutions.aggregator.domains.internal;

import jakarta.persistence.Embeddable;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import net.xyzsd.dichotomy.Conversion;
import net.xyzsd.dichotomy.Result;
import net.xyzsd.dichotomy.trying.Try;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.util.regex.Pattern;

/**
 * Represent a secret that should never be leaked.
 *
 * <p>This is a {@link <a href=
 * "https://doc.rust-lang.org/rust-by-example/generics/new_types.html">newtype</a>} around a
 * {@link String}.
 *
 * <p>This type make sure that the value given is always hashed with Argon2, this way, the password
 * can never be leaked, even by accident.
 */
@NoArgsConstructor(force = true)
@Embeddable
@Builder
@Value
public class Password {
    @NonNull private String password;

    @Override
    public String toString() {
        return password;
    }

    public boolean matches(final String rawPassword) {
        return PASSWORD_ENCODER.matches(rawPassword, password);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        if (this.getClass() != o.getClass()) return false;
        final var password = (Password) o;
        if (password.matches(this.toString())) return true;

        return this.matches(o.toString());
    }

    /** Force validation by using the builder */
    public Password(final String rawPassword) {
        this.password = Password.builder().password(rawPassword).build().password;
    }

    private Password(final String rawPassword, boolean privateValue) {
        this.password = PASSWORD_ENCODER.encode(rawPassword);
    }

    public static final class PasswordBuilder {
        private static final Pattern REGEX_PASSWORD =
                Pattern.compile("(?=.*\\d)(?=.*[A-Z])(?=.*\\W).{8,}");

        public @NonNull Result<Password, RuntimeException> tryBuild() {
            return Conversion.toResult(Try.wrap(this::build)).mapErr(RuntimeException.class::cast);
        }

        public @NonNull Password build() {
            return Result.ofNullable(password)
                    .mapErr(RuntimeException.class::cast)
                    .flatMap(password -> {
                        if (!REGEX_PASSWORD.matcher(password).matches()) {
                            return Result.ofErr(
                                    new RuntimeException(
                                            "Password is not valid. Expecting at least 8 characters, 1 uppercase, 1 digit and 1 symbol."));
                        }
                        return Result.ofOK(new Password(password, true));
                    })
                    .getOrThrow(error -> error);
        }
    }

    // -- Interfaces --

    // Default defined by OWASP
    public static final Argon2PasswordEncoder PASSWORD_ENCODER =
            Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
}
