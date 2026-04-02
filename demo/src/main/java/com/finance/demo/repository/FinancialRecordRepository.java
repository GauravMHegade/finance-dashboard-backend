package com.finance.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.demo.model.FinancialRecord;
import com.finance.demo.model.RecordType;

import java.time.LocalDate;
import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    List<FinancialRecord> findByType(RecordType type);

    List<FinancialRecord> findByCategory(String category);

    List<FinancialRecord> findByDateBetween(LocalDate start, LocalDate end);
}