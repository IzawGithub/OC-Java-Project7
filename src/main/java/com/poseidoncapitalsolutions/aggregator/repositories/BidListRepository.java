package com.poseidoncapitalsolutions.aggregator.repositories;

import com.poseidoncapitalsolutions.aggregator.domains.BidList;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BidListRepository extends JpaRepository<BidList, UUID> {}
