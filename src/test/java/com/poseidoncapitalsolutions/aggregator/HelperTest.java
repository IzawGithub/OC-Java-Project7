package com.poseidoncapitalsolutions.aggregator;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.poseidoncapitalsolutions.aggregator.domains.User;
import com.poseidoncapitalsolutions.aggregator.domains.internal.Password;

import net.xyzsd.dichotomy.Maybe;

import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;
import java.util.regex.Pattern;

public final class HelperTest {
    private static final Pattern REGEX_CSRF =
            Pattern.compile("(?<begin><input .* name=\"_csrf\" value=\")(?<value>.*)(?<end>\"/>)");
    private static final Pattern REGEX_ARGON2 = Pattern.compile("\\$argon2.*");

    public static final String JOHN_DOE_PASSWORD = "Val1dPassword!";

    public static UUID uuid() {
        return UUID.fromString("76543210-dcba-cba1-dcba-ba9876543210");
    }

    public static User johnDoe() {
        return User.builder()
                .id(UUID.fromString("01234567-abcd-1abc-abcd-0123456789ab"))
                .username("John Doe")
                .password(Password.builder().password(JOHN_DOE_PASSWORD).build())
                .fullname("FullName John Doe")
                .role("USER")
                .build();
    }

    public static String sanitizedHtml(@NonNull final String html) {
        var sanitisedHtml = html;
        final var matcherCsrf = REGEX_CSRF.matcher(sanitisedHtml);
        if (matcherCsrf.find()) {
            sanitisedHtml = matcherCsrf.replaceAll("${begin}${end}");
        }
        final var matcherArgon2 = REGEX_ARGON2.matcher(sanitisedHtml);
        if (matcherArgon2.find()) {
            sanitisedHtml = matcherArgon2.replaceAll("");
        }
        return sanitisedHtml;
    }

    public static Maybe<Authentication> expectAuth(
            @NonNull final ResultActions mvc, @NonNull final String redirectUrl) throws Exception {
        return Maybe.ofNullable(
                        (SecurityContextImpl)
                                andExpectRedirect(mvc, redirectUrl)
                                        .andReturn()
                                        .getRequest()
                                        .getAttribute(
                                                "org.springframework.security.web.context.RequestAttributeSecurityContextRepository.SPRING_SECURITY_CONTEXT"))
                .flatMap(securityContext -> Maybe.ofNullable(securityContext.getAuthentication()));
    }

    // -- lombok.ExtensionMethod --

    public static ResultActions andExpectRedirectAuth(@NonNull final ResultActions mvc)
            throws Exception {
        return andExpectRedirect(mvc, "http://localhost/auth/log-in");
    }

    public static ResultActions andExpectRedirect(
            @NonNull final ResultActions mvc, @NonNull final String redirectUrl) throws Exception {
        return mvc.andExpectAll(status().isFound(), redirectedUrl(redirectUrl));
    }
}
