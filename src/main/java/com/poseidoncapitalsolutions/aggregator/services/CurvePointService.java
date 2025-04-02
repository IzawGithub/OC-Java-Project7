package com.poseidoncapitalsolutions.aggregator.services;

import com.poseidoncapitalsolutions.aggregator.domains.CurvePoint;
import com.poseidoncapitalsolutions.aggregator.repositories.CurvePointRepository;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class CurvePointService extends GenericService<CurvePoint, CurvePointRepository> {
    public CurvePointService(@NonNull final CurvePointRepository curvePointRepository) {
        super(curvePointRepository);
    }
}
