package com.poseidoncapitalsolutions.aggregator.security;

import com.poseidoncapitalsolutions.aggregator.repositories.UserRepository;

import lombok.AllArgsConstructor;

import net.xyzsd.dichotomy.Maybe;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DbUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(final String nullableUsername)
            throws UsernameNotFoundException {
        final var username = Maybe.ofNullable(nullableUsername)
                .getOrThrow(() -> new UsernameNotFoundException("Field is null"));

        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    // -- Beans --

    private final UserRepository userRepository;
}
