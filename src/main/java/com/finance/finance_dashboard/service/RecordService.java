package com.finance.finance_dashboard.service;

import com.finance.finance_dashboard.entity.FinancialRecord;
import com.finance.finance_dashboard.repository.FinancialRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

@Service
public class RecordService {

    private final FinancialRecordRepository repo;

    public RecordService(FinancialRecordRepository repo){
        this.repo=repo;
    }

    public Page<FinancialRecord> getRecords(int page,int size,String type,String category){

        Pageable pageable=PageRequest.of(page,size);

        if(type!=null)
            return repo.findByType(type,pageable);

        if(category!=null)
            return repo.findByCategory(category,pageable);

        return repo.findAll(pageable);
    }

    public FinancialRecord create(FinancialRecord record){

        return repo.save(record);
    }
}