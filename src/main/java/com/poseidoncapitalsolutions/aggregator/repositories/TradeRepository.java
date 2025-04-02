package com.poseidoncapitalsolutions.aggregator.repositories;

import com.poseidoncapitalsolutions.aggregator.domains.Trade;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TradeRepository extends JpaRepository<Trade, UUID> {}
