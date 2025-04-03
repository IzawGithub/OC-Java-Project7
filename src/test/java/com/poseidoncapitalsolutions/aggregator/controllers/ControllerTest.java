package com.poseidoncapitalsolutions.aggregator.controllers;

import static com.diffplug.selfie.Selfie.expectSelfie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.poseidoncapitalsolutions.aggregator.HelperTest;
import com.poseidoncapitalsolutions.aggregator.controller.BidListController;
import com.poseidoncapitalsolutions.aggregator.controller.CurveController;
import com.poseidoncapitalsolutions.aggregator.controller.GenericController;
import com.poseidoncapitalsolutions.aggregator.controller.RatingController;
import com.poseidoncapitalsolutions.aggregator.controller.RuleNameController;
import com.poseidoncapitalsolutions.aggregator.controller.TradeController;
import com.poseidoncapitalsolutions.aggregator.controller.UserController;
import com.poseidoncapitalsolutions.aggregator.domains.BidList;
import com.poseidoncapitalsolutions.aggregator.domains.CurvePoint;
import com.poseidoncapitalsolutions.aggregator.domains.Rating;
import com.poseidoncapitalsolutions.aggregator.domains.RuleName;
import com.poseidoncapitalsolutions.aggregator.domains.Trade;
import com.poseidoncapitalsolutions.aggregator.domains.User;
import com.poseidoncapitalsolutions.aggregator.domains.helper.IDomain;
import com.poseidoncapitalsolutions.aggregator.repositories.UserRepository;
import com.poseidoncapitalsolutions.aggregator.services.GenericService;

import jakarta.transaction.Transactional;

import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Objects;
import java.util.stream.Stream;

@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.ANY)
@ActiveProfiles("test")
@ExtensionMethod({HelperTest.class})
@SpringBootTest
@Transactional
@WithUserDetails(value = "John Doe")
@TestInstance(Lifecycle.PER_CLASS)
class ControllerTest<T extends IDomain, Service extends GenericService<T, ?>> {
    private User userJohnDoe = HelperTest.johnDoe();

    @SneakyThrows
    Stream<Arguments> controllerProvider() {
        return Stream.of(
                Arguments.of(
                        bidListController,
                        BidList.builder()
                                .id(HelperTest.uuid())
                                .account("Account Test")
                                .type("Type test")
                                .bidQuantity(10d)
                                .build()),
                Arguments.of(
                        curveController,
                        CurvePoint.builder()
                                .id(HelperTest.uuid())
                                .curveId(10)
                                .term(10d)
                                .value(30d)
                                .build()),
                Arguments.of(
                        ratingController,
                        Rating.builder()
                                .id(HelperTest.uuid())
                                .moodysRating("Moodys Rating")
                                .sandPRating("Sand PRating")
                                .fitchRating("Fich Rating")
                                .orderNumber(10)
                                .build()),
                Arguments.of(
                        ruleNameController,
                        RuleName.builder()
                                .id(HelperTest.uuid())
                                .name("Rule Name")
                                .description("Description")
                                .json("Json")
                                .template("Template")
                                .sqlStr("SQL")
                                .sqlPart("SQL Part")
                                .build()),
                Arguments.of(
                        tradeController,
                        Trade.builder()
                                .id(HelperTest.uuid())
                                .account("Trade Account")
                                .type("Type")
                                .build()),
                Arguments.of(
                        userController,
                        User.builder()
                                .id(HelperTest.uuid())
                                .username("Username")
                                .fullname("Fullname")
                                .build()));
    }

    @BeforeAll
    void setUpFixture() {
        if (!userRepository.existsById(HelperTest.johnDoe().getId())) {
            userRepository.save(HelperTest.johnDoe());
        }
    }

    MockHttpServletRequestBuilder requestGetIndex(
            @NonNull final GenericController<T, Service> controller) {
        return get(controller.getPATH());
    }

    @ParameterizedTest
    @MethodSource("controllerProvider")
    @WithAnonymousUser
    void indexNotAuthenticated(
            @NonNull final GenericController<T, Service> controller, @NonNull final T model)
            throws Exception {
        mockMvc.perform(requestGetIndex(controller)).andExpectRedirectAuth();
    }

    @ParameterizedTest
    @MethodSource("controllerProvider")
    void index(@NonNull final GenericController<T, Service> controller) throws Exception {
        userRepository.save(userJohnDoe);
        final var actualUnsanitized = mockMvc.perform(requestGetIndex(controller))
                .andReturn()
                .getResponse()
                .getContentAsString();
        final var actual = HelperTest.sanitizedHtml(actualUnsanitized);

        expectSelfie(actual).toMatchDisk(controller.getClass().getName());
    }

    MockHttpServletRequestBuilder requestGetCreateForm(
            @NonNull final GenericController<T, Service> controller) {
        return get(controller.getPATH() + GenericController.getCREATE());
    }

    @ParameterizedTest
    @MethodSource("controllerProvider")
    @WithAnonymousUser
    void createFormNotAuthenticated(@NonNull final GenericController<T, Service> controller)
            throws Exception {
        mockMvc.perform(requestGetCreateForm(controller)).andExpectRedirectAuth();
    }

    @ParameterizedTest
    @MethodSource("controllerProvider")
    void createForm(@NonNull final GenericController<T, Service> controller) throws Exception {
        userRepository.save(userJohnDoe);
        final var actualUnsanitized = mockMvc.perform(requestGetCreateForm(controller))
                .andReturn()
                .getResponse()
                .getContentAsString();
        final var actual = HelperTest.sanitizedHtml(actualUnsanitized);

        expectSelfie(actual).toMatchDisk(controller.getClass().getName());
    }

    MockHttpServletRequestBuilder requestPostCreate(
            @NonNull final GenericController<T, Service> controller, @NonNull final T expected) {
        final var request =
                post(controller.getPATH()).contentType(MediaType.APPLICATION_FORM_URLENCODED);
        // This is an horrible hack.
        // From the documentation, `param()` should be able to take an Object and convert it to it's
        // `FORM_URLENCODED` version.
        // But it's somehow broken.
        // So we need to iter on all the fields and convert them manually.
        // This is terrible.
        //
        // At the very least, maybe call the getters instead of the private fields directly?
        for (final var field : expected.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            final var key = field.getName();
            Object value;
            try {
                value = field.get(expected);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (Objects.nonNull(value)) {
                request.param(key, value.toString());
            }
        }

        return request;
    }

    @ParameterizedTest
    @MethodSource("controllerProvider")
    @WithAnonymousUser
    void createNotAuthenticated(
            @NonNull final GenericController<T, Service> controller, @NonNull final T expected)
            throws Exception {
        mockMvc.perform(requestPostCreate(controller, expected).with(csrf()))
                .andExpectRedirectAuth();
    }

    @ParameterizedTest
    @MethodSource("controllerProvider")
    void createNoCsrf(
            @NonNull final GenericController<T, Service> controller, @NonNull final T expected)
            throws Exception {
        userRepository.save(userJohnDoe);
        mockMvc.perform(requestPostCreate(controller, expected)).andExpect(status().isForbidden());

        assertTrue(controller.getService().maybeRead(expected).isNone());
    }

    @ParameterizedTest
    @MethodSource("controllerProvider")
    void createError(
            @NonNull final GenericController<T, Service> controller, @NonNull final T expected)
            throws Exception {
        userRepository.save(userJohnDoe);
        mockMvc.perform(post(controller.getPATH())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "0xDEADBEEF")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("controllerProvider")
    void create(@NonNull final GenericController<T, Service> controller, @NonNull final T expected)
            throws Exception {
        userRepository.save(userJohnDoe);

        mockMvc.perform(requestPostCreate(controller, expected).with(csrf()))
                .andExpectRedirect(controller.getBasePath());

        final var actual = controller.getService().maybeRead(expected).expect();
        assertEquals(expected, actual);
    }

    MockHttpServletRequestBuilder requestGetUpdateForm(
            @NonNull final GenericController<T, Service> controller, @NonNull final T expected) {
        return get(controller.getPATH()
                + GenericController.getUPDATE()
                + "/"
                + expected.getId().toString());
    }

    @ParameterizedTest
    @MethodSource("controllerProvider")
    @WithAnonymousUser
    void updateFormNotAuthenticated(
            GenericController<T, Service> controller, @NonNull final T expected) throws Exception {
        mockMvc.perform(requestGetUpdateForm(controller, expected)).andExpectRedirectAuth();
    }

    @ParameterizedTest
    @MethodSource("controllerProvider")
    void updateForm(GenericController<T, Service> controller, @NonNull final T expected)
            throws Exception {
        userJohnDoe = userRepository.save(userJohnDoe);
        controller.getService().create(expected);

        final var actualUnsanitized = mockMvc.perform(requestGetUpdateForm(controller, expected))
                .andReturn()
                .getResponse()
                .getContentAsString();
        final var actual = HelperTest.sanitizedHtml(actualUnsanitized);

        expectSelfie(actual).toMatchDisk(controller.getClass().getName());
    }

    MockHttpServletRequestBuilder requestPutUpdate(
            @NonNull final GenericController<T, Service> controller, @NonNull final T expected) {
        return put(controller.getPATH())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", expected.getId().toString());
    }

    @ParameterizedTest
    @MethodSource("controllerProvider")
    @WithAnonymousUser
    void updateNotAuthenticated(GenericController<T, Service> controller, @NonNull final T expected)
            throws Exception {
        controller.getService().create(expected);

        mockMvc.perform(requestPutUpdate(controller, expected).with(csrf()))
                .andExpectRedirectAuth();
    }

    @ParameterizedTest
    @MethodSource("controllerProvider")
    void updateNoCsrf(GenericController<T, Service> controller, @NonNull final T expected)
            throws Exception {
        userJohnDoe = userRepository.save(userJohnDoe);
        controller.getService().create(expected);

        mockMvc.perform(requestPutUpdate(controller, expected)).andExpect(status().isForbidden());
        assertFalse(controller.getService().maybeRead(expected).isNone());
    }

    @ParameterizedTest
    @MethodSource("controllerProvider")
    void updateError(
            @NonNull final GenericController<T, Service> controller, @NonNull final T expected)
            throws Exception {
        userRepository.save(userJohnDoe);
        mockMvc.perform(put(controller.getPATH())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "0xDEADBEEF")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("controllerProvider")
    void update(GenericController<T, Service> controller, @NonNull final T base) throws Exception {
        userJohnDoe = userRepository.save(userJohnDoe);
        controller.getService().create(base);
        final var expected = (T) base.getClass().getDeclaredConstructor().newInstance();
        expected.setId(base.getId());

        mockMvc.perform(requestPutUpdate(controller, expected).with(csrf()))
                .andExpectRedirect(controller.getBasePath());

        final var actual = controller.getService().maybeRead(base).expect();
        assertEquals(expected, actual);
    }

    MockHttpServletRequestBuilder requestDelete(
            @NonNull final GenericController<T, Service> controller, @NonNull final T expected) {
        return delete(controller.getPATH() + "/" + expected.getId());
    }

    @ParameterizedTest
    @MethodSource("controllerProvider")
    void deleteNoCsrf(GenericController<T, Service> controller, @NonNull final T expected)
            throws Exception {
        userJohnDoe = userRepository.save(userJohnDoe);
        controller.getService().create(expected);

        mockMvc.perform(requestDelete(controller, expected)).andExpect(status().isForbidden());

        assertFalse(controller.getService().maybeRead(expected).isNone());
    }

    @ParameterizedTest
    @MethodSource("controllerProvider")
    void deleteController(GenericController<T, Service> controller, @NonNull final T expected)
            throws Exception {
        userJohnDoe = userRepository.save(userJohnDoe);
        controller.getService().create(expected);

        mockMvc.perform(requestDelete(controller, expected).with(csrf()))
                .andExpectRedirect(controller.getPATH());

        assertTrue(controller.getService().maybeRead(expected).isNone());
    }

    // -- Beans --

    @NonNull private final MockMvc mockMvc;

    @NonNull private final UserRepository userRepository;

    @NonNull private final BidListController bidListController;

    @NonNull private final CurveController curveController;

    @NonNull private final RatingController ratingController;

    @NonNull private final RuleNameController ruleNameController;

    @NonNull private final TradeController tradeController;

    @NonNull private final UserController userController;

    @Autowired
    ControllerTest(
            @NonNull final MockMvc mockMvc,
            @NonNull final UserRepository userRepository,
            @NonNull final BidListController bidListController,
            @NonNull final CurveController curveController,
            @NonNull final RatingController ratingController,
            @NonNull final RuleNameController ruleNameController,
            @NonNull final TradeController tradeController,
            @NonNull final UserController userController) {
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
        this.bidListController = bidListController;
        this.curveController = curveController;
        this.ratingController = ratingController;
        this.ruleNameController = ruleNameController;
        this.tradeController = tradeController;
        this.userController = userController;
    }
}
