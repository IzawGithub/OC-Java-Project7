package com.poseidoncapitalsolutions.aggregator.controller;

import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.MethodNotAllowed;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler({
        AccessDeniedException.class,
        NoResourceFoundException.class,
        MethodNotAllowed.class
    })
    public @NonNull ModelAndView handle403And404And405Error() {
        return new ModelAndView("redirect:/");
    }
}
