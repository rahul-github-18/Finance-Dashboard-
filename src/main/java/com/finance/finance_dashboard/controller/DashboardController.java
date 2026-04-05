package com.finance.finance_dashboard.controller;

import com.finance.finance_dashboard.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service){
        this.service=service;
    }
    @GetMapping("/monthly-trends")
    public Map<String,Double> monthlyTrends(){
        return service.monthlyTrends();
    }
    @GetMapping("/category-summary")
    public Map<String,Double> summary(){

        return service.categorySummary();
    }
}