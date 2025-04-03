package com.poseidoncapitalsolutions.aggregator.controller;

import com.poseidoncapitalsolutions.aggregator.domains.User;
import com.poseidoncapitalsolutions.aggregator.services.UserService;

import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UserController.PATH)
@PreAuthorize("permitAll()")
public class UserController extends GenericController<User, UserService> {
    static final String PATH = "/user";

    @Override
    public String getPATH() {
        return PATH;
    }

    public UserController(@NonNull final UserService userService) {
        super(userService, UserController.PATH);
    }
}
