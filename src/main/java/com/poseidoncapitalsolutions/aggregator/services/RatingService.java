package com.poseidoncapitalsolutions.aggregator.services;

import com.poseidoncapitalsolutions.aggregator.domains.Rating;
import com.poseidoncapitalsolutions.aggregator.repositories.RatingRepository;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class RatingService extends GenericService<Rating, RatingRepository> {
    public RatingService(@NonNull final RatingRepository ratingRepository) {
        super(ratingRepository);
    }
}
