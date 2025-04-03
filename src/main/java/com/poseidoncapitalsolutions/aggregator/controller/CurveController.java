package com.poseidoncapitalsolutions.aggregator.controller;

import com.poseidoncapitalsolutions.aggregator.domains.CurvePoint;
import com.poseidoncapitalsolutions.aggregator.services.CurvePointService;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(CurveController.PATH)
public class CurveController extends GenericController<CurvePoint, CurvePointService> {
    static final String PATH = "/curvePoint";

    @Override
    public String getPATH() {
        return PATH;
    }

    public CurveController(@NonNull final CurvePointService curveService) {
        super(curveService, PATH);
    }
}
