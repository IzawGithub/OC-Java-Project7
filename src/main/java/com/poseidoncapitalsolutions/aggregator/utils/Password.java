package com.poseidoncapitalsolutions.aggregator.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.poseidoncapitalsolutions.aggregator.utils.errors.EErrorPassword;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import net.xyzsd.dichotomy.Conversion;
import net.xyzsd.dichotomy.Result;
import net.xyzsd.dichotomy.trying.Try;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Represent a secret that should never be leaked.
 *
 * <p>This is a [NewType] around a [String]
 *
 * <p>This type make sure that the value given is always hashed using the [Argon2 algorithm], this
 * way, the secret can never be leaked, even (and especially!) by accident.
 *
 * <p>[NewType]: https://doc.rust-lang.org/rust-by-example/generics/new_types.html [Argon2
 * algorithm]: https://en.wikipedia.org/wiki/Argon2
 */
@Builder
@Embeddable
@NoArgsConstructor(force = true)
@Value
@JsonDeserialize(using = Password.PasswordDeserializer.class)
@JsonSerialize(using = Password.PasswordSerializer.class)
public class Password implements Serializable {
    /** Represent the different type of Argon2 hash that exist. */
    public enum Type {
        // GPU
        D,
        // Side channel
        I,
        // Both
        ID;

        public Integer into() {
            return switch (this) {
                case D -> Argon2Parameters.ARGON2_d;
                case I -> Argon2Parameters.ARGON2_i;
                case ID -> Argon2Parameters.ARGON2_id;
            };
        }
    }

    /**
     * The different type of hash
     *
     * <p>See: [Type]
     */
    private final Type type;
    /**
     * The Argon2 hash version.
     *
     * <p>The current version is 0x13 (19).
     */
    private final Integer version;
    /** Minimum amount of memory to use, in KiB. */
    private final Integer memory;
    /** Number of iterations to run. */
    private final Integer iterations;
    /** Number of threads to use. */
    private final Integer parallelism;
    /**
     * Current salt.
     *
     * <p>See: [Base64String]
     */
    private final Base64String salt;
    /**
     * Current digest.
     *
     * <p>See: [Base64String]
     */
    private final Base64String digest;

    /**
     * Return the string representation of the hash.
     *
     * <p>## Examples
     *
     * <p>```java final var expected =
     * "$argon2id$v=19$m=12288,t=3,p=1$MHhERUFEQkVFRg$LqF/GxQuEACfFXhFQzbP1xx8N6rxsi8OEovKyV/Qlug";
     * final var actual = Password.builder() .secret("0xDEADBEEF") .type(Type.ID) .memory(12288)
     * .iterations(3) .parallelism(1) .salt("0xDEADBEEF") .build() .getHashed();
     *
     * <p>assertEquals(expected, actual); ```
     */
    public String getHashed() {
        return "$argon2" + this.type.toString().toLowerCase() + "$v="
                + this.version + "$m="
                + this.memory + ",t="
                + this.iterations + ",p="
                + this.parallelism + "$"
                + this.salt.getEncodedString() + "$"
                + digest.getEncodedString();
    }

    /**
     * Redact the hashed secret.
     *
     * <p>We can display the hash with [getHashed], but a big `REDACTED` from this `toString`make it
     * obvious that this shouldn't be displayed.
     */
    @Override
    public String toString() {
        LOGGER.warn(
                """
                `Password.toString()` was called.
                Redacted the hash, check if something is not trying to get its value by reflection.
                """);

        var className = this.getClass().getName();
        className = className.substring(className.lastIndexOf('.') + 1);
        return className + "[[REDACTED]]";
    }

    private static final Logger LOGGER = LogManager.getLogger();

    private static final Pattern REGEX_ARGON2 = Pattern.compile(
            "^\\$argon2(?<Type>i?d?)\\$v=(?<Version>\\d+)\\$m=(?<Memory>\\d+),t=(?<Iterations>\\d+),p=(?<Parallelism>\\d)\\$(?<Salt>.*?)\\$(?<Digest>.*)$");
    private static final Pattern REGEX_PASSWORD =
            Pattern.compile("(?=.*\\d)(?=.*[A-Z])(?=.*\\W).{8,}");
    // -- Ctors --

    /**
     * Hash a [String] using the [Argon2 algorithm].
     *
     * <p>## Examples
     *
     * <p>```java final var expected =
     * "$argon2id$v=19$m=12288,t=3,p=1$QjhQVTFlS2N6d0JzZ09aSQ$0UunrdF3wZXMCSt5IbIWrfoqmvqU+BEkvAZHAgFST/c";
     * final var actual = new Password("0xDEADBEEF").getHashed();   assertEquals(expected, actual);
     * ```
     *
     * <p>[Argon2 algorithm]: https://en.wikipedia.org/wiki/Argon2
     */
    public Password(final @Nullable String maybeRawPassword) throws EErrorPassword {
        final var matcher = REGEX_ARGON2.matcher(maybeRawPassword);

        final Password password;
        if (matcher.matches()) {
            final var type = Type.valueOf(matcher.group("Type").toUpperCase());
            final var version = Integer.valueOf(matcher.group("Version"));
            final var memory = Integer.valueOf(matcher.group("Memory"));
            final var iterations = Integer.valueOf(matcher.group("Iterations"));
            final var parallelism = Integer.valueOf(matcher.group("Parallelism"));
            final var salt = new Base64String(matcher.group("Salt"), true);
            final var digest = new Base64String(matcher.group("Digest"), true);
            password = new Password(type, version, memory, iterations, parallelism, salt, digest);
        } else {
            password = Password.builder().secret(maybeRawPassword).build();
        }

        this.type = password.type;
        this.version = password.version;
        this.memory = password.memory;
        this.iterations = password.iterations;
        this.parallelism = password.parallelism;
        this.salt = password.salt;
        this.digest = password.digest;
    }

    /**
     * Used to create a [Password] that is already computed.
     *
     * <p>Can be created by defining all of the setters in [PasswordBuilder].
     */
    private Password(
            final Type type,
            final Integer version,
            final Integer memory,
            final Integer iterations,
            final Integer parallelism,
            final Base64String salt,
            final Base64String digest) {
        this.type = type;
        this.version = version;
        this.memory = memory;
        this.iterations = iterations;
        this.parallelism = parallelism;
        this.salt = salt;
        this.digest = digest;
    }

    // -- Builder --

    /**
     * The builder for [Password].
     *
     * <p>Most users will not need to change the default configuration, which are taken directly
     * from the [OWASP] recommendation.
     *
     * <p>Using the public constructor will internally use this builder, guaranteeing a valid hash.
     *
     * <p>[OWASP]:
     * https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html#argon2id
     */
    public static class PasswordBuilder {
        @Getter
        private @Nullable String maybeSecret;

        @Getter
        private Type type = Type.ID;

        @Getter
        private int version = Argon2Parameters.ARGON2_VERSION_13;

        @Getter
        private int memory = 12288;

        @Getter
        private int iterations = 3;

        @Getter
        private int parallelism = 1;

        @Getter
        private int saltLength = 8;

        @Getter
        private Base64String salt = generateSalt();

        @Getter
        private int digestLength = 32;

        public PasswordBuilder secret(final @Nullable String maybeSecret) {
            this.maybeSecret = maybeSecret;
            return this;
        }

        public PasswordBuilder salt(final @Nullable String maybeSalt) {
            this.salt = Optional.ofNullable(maybeSalt).map(Base64String::new).orElse(this.salt);
            return this;
        }

        private PasswordBuilder salt(final @Nullable Base64String maybeSalt) {
            this.salt = Optional.ofNullable(maybeSalt).orElse(this.salt);
            return this;
        }

        /// Try to build a [Password].
        ///
        /// This wrap the [build][#build] method in a [Result].
        ///
        /// ## Errors
        ///
        /// See [build][#build]
        public Result<Password, EErrorPassword> tryBuild() {
            return Conversion.toResult(Try.wrap(this::build)).mapErr(EErrorPassword.class::cast);
        }

        /// Build a [Password].
        ///
        /// ## Exceptions
        ///
        /// - [EErrorPassword.Null]: No secret was given, cannot hash null.
        public Password build() throws EErrorPassword {
            return Result.ofNullable(this.maybeSecret)
                    .mapErr(unnamned -> (EErrorPassword) new EErrorPassword.Null())
                    .flatMap(rawPassword -> {
                        if (!REGEX_PASSWORD.matcher(rawPassword).matches()) {
                            return Result.ofErr(new EErrorPassword.Strength());
                        }
                        final var password = new Password(
                                type,
                                version,
                                memory,
                                iterations,
                                parallelism,
                                salt,
                                generateDigest(rawPassword));
                        LOGGER.debug("Password hash generated.");

                        return Result.ofOK(password);
                    })
                    // If there's no secret, check if we already have the digest to construct the
                    // hash
                    .flatMapErr(error -> Result.ofNullable(this.digest)
                            .mapErr(unnamned -> error)
                            .map(unnamned -> {
                                final var password = new Password(
                                        type,
                                        version,
                                        memory,
                                        iterations,
                                        parallelism,
                                        salt,
                                        digest);
                                LOGGER.debug("Password hash precalculated");

                                return password;
                            }))
                    .getOrThrow(error -> error);
        }

        private Base64String generateDigest(PasswordBuilder this, final String rawSecret) {
            final byte[] byteDigest = new byte[this.digestLength];
            final var argon2Generator = new Argon2BytesGenerator();
            argon2Generator.init(createArgon2Parameters());

            argon2Generator.generateBytes(rawSecret.getBytes(), byteDigest);
            return new Base64String(byteDigest);
        }

        private Base64String generateSalt(PasswordBuilder this) {
            final var bytes = new byte[this.saltLength];
            new SecureRandom().nextBytes(bytes);

            return new Base64String(new String(bytes));
        }

        private Argon2Parameters createArgon2Parameters() {
            return new Argon2Parameters.Builder(this.type.into())
                    .withVersion(this.version)
                    .withSalt(this.salt.getDecodedString().getBytes())
                    .withParallelism(this.parallelism)
                    .withMemoryAsKB(this.memory)
                    .withIterations(this.iterations)
                    .build();
        }
    }

    // -- JSON --

    static class PasswordDeserializer extends JsonDeserializer<Password> {
        @Override
        public Password deserialize(final JsonParser json, final DeserializationContext ctx)
                throws IOException {
            try {
                return new Password(json.getValueAsString());
            } catch (final EErrorPassword errorSecret) {
                throw JsonMappingException.from(json, errorSecret.toString(), errorSecret);
            }
        }
    }

    static class PasswordSerializer extends JsonSerializer<Password> {
        @Override
        public void serialize(
                final Password value,
                final JsonGenerator generator,
                final SerializerProvider serializers)
                throws IOException {
            generator.writeString(value.getHashed());
        }
    }
}
