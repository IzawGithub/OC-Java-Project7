package com.poseidoncapitalsolutions.aggregator.services;

import com.poseidoncapitalsolutions.aggregator.domains.User;
import com.poseidoncapitalsolutions.aggregator.repositories.UserRepository;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User, UserRepository> {
    public UserService(@NonNull final UserRepository userRepository) {
        super(userRepository);
    }
}
