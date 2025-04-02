package com.poseidoncapitalsolutions.aggregator.domains;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.poseidoncapitalsolutions.aggregator.domains.helper.IDomain;
import com.poseidoncapitalsolutions.aggregator.domains.internal.Password;
import com.poseidoncapitalsolutions.aggregator.repositories.BidListRepository;
import com.poseidoncapitalsolutions.aggregator.repositories.CurvePointRepository;
import com.poseidoncapitalsolutions.aggregator.repositories.RatingRepository;
import com.poseidoncapitalsolutions.aggregator.repositories.UserRepository;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;
import java.util.stream.Stream;

@ActiveProfiles("test")
@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
class DomainTest {
    Stream<Arguments> repositoryProvider() {
        return Stream.of(
                Arguments.of(
                        bidListRepository,
                        BidList.builder()
                                .account("Account Test")
                                .type("Type test")
                                .bidQuantity(10d)
                                .build()),
                Arguments.of(
                        curvePointRepository,
                        CurvePoint.builder().curveId(10).term(10d).value(30d).build()),
                Arguments.of(
                        ratingRepository,
                        Rating.builder()
                                .moodysRating("Moodys Rating")
                                .sandPRating("Sand PRating")
                                .fitchRating("Fich Rating")
                                .orderNumber(10)
                                .build()),
                Arguments.of(
                        userRepository,
                        User.builder()
                                .username("Username")
                                .password(Password.builder()
                                        .password("Val1dPassword!")
                                        .build())
                                .fullname("Fullname")
                                .role("Role")
                                .build()));
    }

    @ParameterizedTest
    @MethodSource("repositoryProvider")
    <T extends IDomain> void domain(
            @NonNull final JpaRepository<T, UUID> repository, @NonNull T model) {
        final var id = model.getId();

        // Saved
        model = repository.save(model);
        assertEquals(model, repository.findById(id).get());

        // Updated
        model.setId(UUID.randomUUID());

        // Deleted
        repository.delete(model);

        final var maybeDomainList = repository.findById(id);
        assertFalse(maybeDomainList.isPresent());
    }

    // -- Beans --

    @NonNull private final BidListRepository bidListRepository;

    @NonNull private final CurvePointRepository curvePointRepository;

    @NonNull private final RatingRepository ratingRepository;

    @NonNull private final UserRepository userRepository;

    @Autowired
    DomainTest(
            @NonNull final BidListRepository bidListRepository,
            @NonNull final CurvePointRepository curvePointRepository,
            @NonNull final RatingRepository ratingRepository,
            @NonNull final UserRepository userRepository) {
        this.bidListRepository = bidListRepository;
        this.curvePointRepository = curvePointRepository;
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }
}
