package com.poseidoncapitalsolutions.aggregator.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Value;

import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.Optional;

@Value
@JsonDeserialize(using = Base64String.Base64StringDeserializer.class)
public class Base64String implements Serializable {
    private String encodedString;

    public String getDecodedString() {
        return new String(BASE64_DECODER.decode(encodedString));
    }

    @Override
    public String toString() {
        return encodedString;
    }

    static final Base64.Decoder BASE64_DECODER = Base64.getDecoder();
    static final Base64.Encoder BASE64_ENCODER = Base64.getEncoder().withoutPadding();

    // -- Ctors --

    public Base64String(final byte[] bytes) {
        this.encodedString = this.encodeBytes(bytes);
    }

    public Base64String(final @Nullable String maybeString, final boolean alreadyEncoded) {
        if (alreadyEncoded) {
            this.encodedString = Optional.ofNullable(maybeString).orElse("");
        } else {
            this.encodedString = Optional.ofNullable(maybeString)
                    .map(String::getBytes)
                    .map(this::encodeBytes)
                    .orElse("");
        }
    }

    public Base64String(final @Nullable String maybeString) {
        this(maybeString, false);
    }

    private String encodeBytes(Base64String this, final byte[] bytes) {
        return new String(BASE64_ENCODER.encode(bytes));
    }

    // -- JSON --

    /// Deserialize a [Base64String] like if it was a [String].
    static class Base64StringDeserializer extends JsonDeserializer<Base64String> {
        @Override
        public Base64String deserialize(final JsonParser json, final DeserializationContext ctx)
                throws IOException {
            return new Base64String(json.getValueAsString());
        }
    }
}
