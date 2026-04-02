package com.finance.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.demo.model.FinancialRecord;
import com.finance.demo.model.RecordType;
import com.finance.demo.model.Role;
import com.finance.demo.model.User;
import com.finance.demo.repository.FinancialRecordRepository;
import com.finance.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialRecordService {

    @Autowired
    private FinancialRecordRepository recordRepository;

    @Autowired
    private UserRepository userRepository;

    // CREATE RECORD (Only ADMIN)
    public FinancialRecord createRecord(FinancialRecord record, Long userId) {

        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        if (user.getRole() != Role.ADMIN) {
            throw new RuntimeException("Access Denied: Only ADMIN can create records");
        }

        record.setCreatedBy(userId);
        return recordRepository.save(record);
    }

    // GET ALL RECORDS
    public List<FinancialRecord> getAllRecords() {
        return recordRepository.findAll();
    }

    // DELETE RECORD (Only ADMIN)
    public void deleteRecord(Long recordId, Long userId) {

        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        if (user.getRole() != Role.ADMIN) {
            throw new RuntimeException("Access Denied: Only ADMIN can delete");
        }

        recordRepository.deleteById(recordId);
    }
    
    
    
    
    public Double getTotalIncome() {
        return recordRepository.findAll().stream()
                .filter(r -> r.getType() == RecordType.INCOME)
                .mapToDouble(FinancialRecord::getAmount)
                .sum();
    }

    public Double getTotalExpense() {
        return recordRepository.findAll().stream()
                .filter(r -> r.getType() == RecordType.EXPENSE)
                .mapToDouble(FinancialRecord::getAmount)
                .sum();
    }

    public Double getBalance() {
        return getTotalIncome() - getTotalExpense();
    }
    
    
    
}