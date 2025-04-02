package com.poseidoncapitalsolutions.aggregator.services;

import com.poseidoncapitalsolutions.aggregator.domains.User;
import com.poseidoncapitalsolutions.aggregator.repositories.UserRepository;

import net.xyzsd.dichotomy.Maybe;

import java.util.Objects;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User, UserRepository> {
    @Override
    public void update(@NonNull final User user) {
        // Make it so that only admin can edit the role
        // Otherwise any user could give themselves admin
        final var userDb = repository.getReferenceById(user.getId());
        if (!Objects.isNull(userDb.getRole()) &&
            Maybe.ofNullable(userDb)
                .map(User::getRole)
                .map(it -> !it.equals("ROLE_ADMIN"))
                .orElse(true)
            ) {
            user.setRole("ROLE_USER");
        }
        super.update(user);
    }

    public UserService(@NonNull final UserRepository userRepository) {
        super(userRepository);
    }
}
