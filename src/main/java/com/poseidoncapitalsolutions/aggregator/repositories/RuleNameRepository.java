package com.poseidoncapitalsolutions.aggregator.repositories;

import com.poseidoncapitalsolutions.aggregator.domains.RuleName;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RuleNameRepository extends JpaRepository<RuleName, UUID> {}
