package com.poseidoncapitalsolutions.aggregator.controller;

import com.poseidoncapitalsolutions.aggregator.domains.Rating;
import com.poseidoncapitalsolutions.aggregator.services.RatingService;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(RatingController.PATH)
public class RatingController extends GenericController<Rating, RatingService> {
    static final String PATH = "/rating";

    @Override
    public String getPATH() {
        return PATH;
    }

    public RatingController(@NonNull final RatingService ratingService) {
        super(ratingService, PATH);
    }
}
