package com.poseidoncapitalsolutions.aggregator.repositories;

import com.poseidoncapitalsolutions.aggregator.domains.Rating;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID> {}
