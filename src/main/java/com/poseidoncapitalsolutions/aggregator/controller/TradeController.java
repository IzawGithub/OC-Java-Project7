package com.poseidoncapitalsolutions.aggregator.controller;

import com.poseidoncapitalsolutions.aggregator.domains.Trade;
import com.poseidoncapitalsolutions.aggregator.services.TradeService;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(TradeController.PATH)
public class TradeController extends GenericController<Trade, TradeService> {
    static final String PATH = "/trade";

    @Override
    public String getPATH() {
        return PATH;
    }

    public TradeController(@NonNull final TradeService tradeService) {
        super(tradeService, TradeController.PATH);
    }
}
