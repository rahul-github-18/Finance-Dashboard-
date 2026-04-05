package com.finance.finance_dashboard.controller;

import com.finance.finance_dashboard.entity.FinancialRecord;
import com.finance.finance_dashboard.service.RecordService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    private final RecordService service;

    public RecordController(RecordService service){
        this.service=service;
    }

    @PostMapping
    public FinancialRecord create(@Valid @RequestBody FinancialRecord record){

        return service.create(record);
    }

    @GetMapping
    public Page<FinancialRecord> getRecords(

            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="10") int size,
            @RequestParam(required=false) String type,
            @RequestParam(required=false) String category){

        return service.getRecords(page,size,type,category);
    }
}