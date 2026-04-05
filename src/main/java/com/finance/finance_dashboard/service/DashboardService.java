package com.finance.finance_dashboard.service;

import com.finance.finance_dashboard.entity.FinancialRecord;
import com.finance.finance_dashboard.repository.FinancialRecordRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashboardService {

    private final FinancialRecordRepository repo;

    public DashboardService(FinancialRecordRepository repo){
        this.repo=repo;
    }

    public Map<String,Double> categorySummary(){

        List<FinancialRecord> records=repo.findAll();

        Map<String,Double> map=new HashMap<>();

        for(FinancialRecord r:records){

            map.put(
                    r.getCategory(),
                    map.getOrDefault(r.getCategory(),0.0)+r.getAmount()
            );
        }

        return map;
    }
    public Map<String,Double> monthlyTrends(){

        List<FinancialRecord> records=repo.findAll();

        Map<String,Double> map=new HashMap<>();

        for(FinancialRecord r:records){

            String month=r.getDate().getMonth().toString();

            map.put(month,
                    map.getOrDefault(month,0.0)+r.getAmount());
        }

        return map;
    }
}