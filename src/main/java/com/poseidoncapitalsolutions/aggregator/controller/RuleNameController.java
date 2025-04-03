package com.poseidoncapitalsolutions.aggregator.controller;

import com.poseidoncapitalsolutions.aggregator.domains.RuleName;
import com.poseidoncapitalsolutions.aggregator.services.RuleNameService;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(RuleNameController.PATH)
public class RuleNameController extends GenericController<RuleName, RuleNameService> {
    static final String PATH = "/ruleName";

    @Override
    public String getPATH() {
        return PATH;
    }

    public RuleNameController(@NonNull final RuleNameService ruleNameService) {
        super(ruleNameService, PATH);
    }
}
