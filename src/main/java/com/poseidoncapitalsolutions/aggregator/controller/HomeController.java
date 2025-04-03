package com.poseidoncapitalsolutions.aggregator.controller;

import net.xyzsd.dichotomy.Maybe;

import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@PreAuthorize("permitAll()")
public class HomeController {
    @GetMapping
    public @NonNull ModelAndView home(@AuthenticationPrincipal final UserDetails userAuth) {
        return Maybe.ofNullable(userAuth)
                .map(lambda -> new ModelAndView("redirect:/bidList"))
                .orElse(new ModelAndView("home"));
    }
}
