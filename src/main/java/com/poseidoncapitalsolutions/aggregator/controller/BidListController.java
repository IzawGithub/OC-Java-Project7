package com.poseidoncapitalsolutions.aggregator.controller;

import com.poseidoncapitalsolutions.aggregator.domains.BidList;
import com.poseidoncapitalsolutions.aggregator.services.BidListService;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(BidListController.PATH)
public class BidListController extends GenericController<BidList, BidListService> {
    static final String PATH = "/bidList";

    @Override
    public String getPATH() {
        return PATH;
    }

    public BidListController(@NonNull final BidListService bidListServices) {
        super(bidListServices, PATH);
    }
}
