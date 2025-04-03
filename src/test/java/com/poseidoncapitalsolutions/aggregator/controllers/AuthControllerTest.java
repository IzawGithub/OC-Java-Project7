package com.poseidoncapitalsolutions.aggregator.controllers;

import static com.diffplug.selfie.Selfie.expectSelfie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.poseidoncapitalsolutions.aggregator.HelperTest;
import com.poseidoncapitalsolutions.aggregator.domains.User;
import com.poseidoncapitalsolutions.aggregator.repositories.UserRepository;

import jakarta.transaction.Transactional;

import lombok.experimental.ExtensionMethod;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.NonNull;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.ANY)
@ActiveProfiles("test")
@ExtensionMethod({HelperTest.class})
@SpringBootTest
@Transactional
@WithAnonymousUser
class AuthControllerTest {
    private User userJohnDoe = HelperTest.johnDoe();

    private final MockHttpServletRequestBuilder getAuth = get("/auth");
    private final MockHttpServletRequestBuilder getLogin = get("/auth/log-in");

    @Test
    @WithMockUser(username = "john.doe@test.com", password = HelperTest.JOHN_DOE_PASSWORD)
    void authAuthenticated() throws Exception {
        userRepository.save(userJohnDoe);
        mockMvc.perform(getAuth).andExpectRedirect("/");
    }

    @Test
    void auth() throws Exception {
        final var actual = mockMvc.perform(getAuth).andReturn().getResponse().getContentAsString();
        expectSelfie(actual).toMatchDisk();
    }

    @Test
    @WithMockUser(username = "john.doe@test.com", password = HelperTest.JOHN_DOE_PASSWORD)
    void getLoginAuthenticated() throws Exception {
        userRepository.save(userJohnDoe);
        mockMvc.perform(getLogin).andExpectRedirect("/");
    }

    @Test
    void getLogin() throws Exception {
        final var actualWithCsrf =
                mockMvc.perform(getLogin).andReturn().getResponse().getContentAsString();
        final var actual = HelperTest.sanitizedHtml(actualWithCsrf);

        expectSelfie(actual).toMatchDisk();
    }

    @Test
    void logInWrongUsername() throws Exception {
        userRepository.save(userJohnDoe);
        mockMvc.perform(formLogin()
                        .loginProcessingUrl("/auth/log-in")
                        .user("username", "0xDEADBEEF")
                        .password("password"))
                .andExpectRedirect("/auth/log-in?error");
    }

    @Test
    void logInWrongPassword() throws Exception {
        userRepository.save(userJohnDoe);
        mockMvc.perform(formLogin()
                        .loginProcessingUrl("/auth/log-in")
                        .user("username", userJohnDoe.getUsername())
                        .password("0xDEADBEEF"))
                .andExpectRedirect("/auth/log-in?error");
    }

    @Test
    void logInSuccess() throws Exception {
        userRepository.save(userJohnDoe);
        final var expectedUsername = userJohnDoe.getUsername();
        final var expectedPassword = userJohnDoe.getPassword();

        final var auth = mockMvc.perform(formLogin()
                        .loginProcessingUrl("/auth/log-in")
                        .user(userJohnDoe.getUsername())
                        .password(HelperTest.JOHN_DOE_PASSWORD))
                .expectAuth("/bidList")
                .expect();

        final var actual = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        final var actualUsername = actual.getUsername();
        final var actualPassword = actual.getPassword();
        assertTrue(auth.isAuthenticated());
        assertEquals(expectedUsername, actualUsername);
        assertEquals(expectedPassword.toString(), actualPassword);
    }

    @Test
    @WithMockUser(username = "john.doe@test.com")
    void logOutSuccess() throws Exception {
        userRepository.save(userJohnDoe);
        final var auth = mockMvc.perform(logout("/auth/log-out")).expectAuth("/");
        assertTrue(auth.isNone());
    }

    // -- Beans --

    @NonNull private final MockMvc mockMvc;

    @NonNull private final UserRepository userRepository;

    @Autowired
    AuthControllerTest(
            @NonNull final MockMvc mockMvc, @NonNull final UserRepository userRepository) {
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
    }
}
