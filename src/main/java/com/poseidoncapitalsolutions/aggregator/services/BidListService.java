package com.poseidoncapitalsolutions.aggregator.services;

import com.poseidoncapitalsolutions.aggregator.domains.BidList;
import com.poseidoncapitalsolutions.aggregator.repositories.BidListRepository;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class BidListService extends GenericService<BidList, BidListRepository> {
    public BidListService(@NonNull final BidListRepository bidListRepository) {
        super(bidListRepository);
    }
}
