package com.poseidoncapitalsolutions.aggregator.domains;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.poseidoncapitalsolutions.aggregator.domains.helper.IDomain;
import com.poseidoncapitalsolutions.aggregator.domains.internal.Password;
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

    @NonNull private final UserRepository userRepository;

    @Autowired
    DomainTest(@NonNull final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
