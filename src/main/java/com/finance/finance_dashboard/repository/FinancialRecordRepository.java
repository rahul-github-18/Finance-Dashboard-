package com.finance.finance_dashboard.repository;

import com.finance.finance_dashboard.entity.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.*;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord,Long> {

    Page<FinancialRecord> findByType(String type, Pageable pageable);

    Page<FinancialRecord> findByCategory(String category, Pageable pageable);
}