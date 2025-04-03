package com.poseidoncapitalsolutions.aggregator.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
class HomeControllerTest {
    private User userJohnDoe = HelperTest.johnDoe();

    private final MockHttpServletRequestBuilder getHome = get("/");

    @Test
    @WithAnonymousUser
    void redirectAnonymousToAuth() throws Exception {
        mockMvc.perform(getHome).andExpectAll(status().isOk());
    }

    @Test
    @WithMockUser(username = "john.doe@test.com", password = HelperTest.JOHN_DOE_PASSWORD)
    void redirectUserToBidList() throws Exception {
        userRepository.save(userJohnDoe);
        mockMvc.perform(getHome).andExpectRedirect("/bidList");
    }

    // -- Beans --

    @NonNull private final MockMvc mockMvc;

    @NonNull private final UserRepository userRepository;

    @Autowired
    HomeControllerTest(
            @NonNull final MockMvc mockMvc, @NonNull final UserRepository userRepository) {
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
    }
}
