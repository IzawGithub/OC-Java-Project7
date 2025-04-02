package com.poseidoncapitalsolutions.aggregator.services;

import com.poseidoncapitalsolutions.aggregator.domains.Trade;
import com.poseidoncapitalsolutions.aggregator.repositories.TradeRepository;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class TradeService extends GenericService<Trade, TradeRepository> {
    public TradeService(@NonNull final TradeRepository tradeRepository) {
        super(tradeRepository);
    }
}
