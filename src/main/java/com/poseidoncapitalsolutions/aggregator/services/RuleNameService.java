package com.poseidoncapitalsolutions.aggregator.services;

import com.poseidoncapitalsolutions.aggregator.domains.RuleName;
import com.poseidoncapitalsolutions.aggregator.repositories.RuleNameRepository;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class RuleNameService extends GenericService<RuleName, RuleNameRepository> {
    public RuleNameService(@NonNull final RuleNameRepository ruleNameRepository) {
        super(ruleNameRepository);
    }
}
