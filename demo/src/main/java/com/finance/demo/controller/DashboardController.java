package com.finance.demo.controller;


import com.finance.demo.service.FinancialRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private FinancialRecordService recordService;

    @GetMapping("/summary")
    public Map<String, Double> getSummary() {

        Map<String, Double> response = new HashMap<>();

        response.put("totalIncome", recordService.getTotalIncome());
        response.put("totalExpense", recordService.getTotalExpense());
        response.put("balance", recordService.getBalance());

        return response;
    }
}