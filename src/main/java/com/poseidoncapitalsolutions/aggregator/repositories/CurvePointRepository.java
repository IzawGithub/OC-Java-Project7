package com.poseidoncapitalsolutions.aggregator.repositories;

import com.poseidoncapitalsolutions.aggregator.domains.CurvePoint;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CurvePointRepository extends JpaRepository<CurvePoint, UUID> {}
