package com.poseidoncapitalsolutions.aggregator.controller;

import com.poseidoncapitalsolutions.aggregator.domains.helper.IDomain;
import com.poseidoncapitalsolutions.aggregator.services.GenericService;

import jakarta.transaction.Transactional;

import lombok.Getter;

import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

public abstract class GenericController<T extends IDomain, Service extends GenericService<T, ?>> {
    @GetMapping
    public @NonNull ModelAndView home() {
        return new ModelAndView(basePath + "/index.html").addObject("listItems", service.readAll());
    }

    @GetMapping(CREATE)
    public ModelAndView createForm(final T item) {
        return new ModelAndView(basePath + CREATE).addObject(item);
    }

    @PostMapping
    @Transactional
    public ModelAndView create(@Validated final T item, @NonNull final BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView(basePath + CREATE);
        }
        service.create(item);
        return new ModelAndView("redirect:" + basePath);
    }

    @GetMapping(UPDATE + "/{id}")
    public ModelAndView updateForm(@PathVariable @NonNull final UUID id) {
        return new ModelAndView(basePath + UPDATE)
                .addObject(service.maybeReadById(id).expect());
    }

    @PutMapping
    @Transactional
    public ModelAndView update(@Validated final T item, @NonNull final BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView(basePath + UPDATE);
        }
        service.update(item);
        return new ModelAndView("redirect:" + basePath);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ModelAndView delete(@PathVariable @NonNull final UUID id) {
        service.delete(id);
        return new ModelAndView("redirect:" + basePath);
    }

    public abstract String getPATH();

    // -- Beans --

    @Getter
    @NonNull private Service service;

    @Getter
    private final String basePath;

    @Getter
    private static final String CREATE = "/create";

    @Getter
    private static final String UPDATE = "/update";

    protected GenericController(
            @NonNull final Service genericService, @NonNull final String basePath) {
        this.service = genericService;
        this.basePath = basePath;
    }
}
