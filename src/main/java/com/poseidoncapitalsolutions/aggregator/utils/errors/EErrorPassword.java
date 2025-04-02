package com.poseidoncapitalsolutions.aggregator.utils.errors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(
        value = {"cause", "stackTrace", "localizedMessage", "suppressed"},
        ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class EErrorPassword extends Exception {

    @JsonProperty("error")
    private String enumError() {
        final var className = this.getClass().getName();
        return className.substring(className.lastIndexOf('.') + 1);
    }

    @Value
    @EqualsAndHashCode(callSuper = true)
    public static final class Null extends EErrorPassword {
        @Override
        @JsonProperty("message")
        public String toString() {
            return "Password is null.";
        }
    }

    @Value
    @EqualsAndHashCode(callSuper = true)
    public static final class Invalid extends EErrorPassword {
        record Value(String string) implements Serializable {}

        private final Value value;

        @Override
        @JsonProperty("message")
        public String toString() {
            return """
                Hashed password invalid:
                    Expected form: $argon2<Type>$v=<Version>$m=<Memory>,t=<Iterations>,p=<Parallelism>$<Salt>$<Digest>
                """;
        }

        // -- Ctors --

        public Invalid(final String string) {
            this.value = new Value(string);
        }
    }

    @Value
    @EqualsAndHashCode(callSuper = true)
    public static final class Strength extends EErrorPassword {
        @Override
        @JsonProperty("message")
        public String toString() {
            return "Password is not strong enough. Expecting at least 8 characters, 1 uppercase, 1 digit, and 1 symbol.";
        }
    }
}
