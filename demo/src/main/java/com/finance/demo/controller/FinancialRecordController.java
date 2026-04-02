package com.finance.demo.controller;

import com.finance.demo.model.FinancialRecord;
import com.finance.demo.service.FinancialRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class FinancialRecordController {

    @Autowired
    private FinancialRecordService recordService;

    // Create Record (pass userId manually for now)
    @PostMapping
    public FinancialRecord createRecord(@RequestBody FinancialRecord record,
                                        @RequestParam Long userId) {
        return recordService.createRecord(record, userId);
    }

    // Get All Records
    @GetMapping
    public List<FinancialRecord> getAllRecords() {
        return recordService.getAllRecords();
    }

    // Delete Record
    @DeleteMapping("/{id}")
    public String deleteRecord(@PathVariable Long id,
                               @RequestParam Long userId) {
        recordService.deleteRecord(id, userId);
        return "Record deleted successfully";
    }
}