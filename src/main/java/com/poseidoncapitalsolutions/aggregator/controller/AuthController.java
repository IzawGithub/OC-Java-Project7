package com.poseidoncapitalsolutions.aggregator.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
@PreAuthorize("isAnonymous()")
public class AuthController {
    @GetMapping("log-in")
    public @NonNull ModelAndView login() {
        return new ModelAndView("auth/log-in");
    }
}
