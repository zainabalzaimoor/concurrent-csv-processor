package com.example.concurrentcsv.controller;

import com.example.concurrentcsv.model.Employee;
import com.example.concurrentcsv.service.ConcurrentProcessingService;
import com.example.concurrentcsv.service.CsvReaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    private final CsvReaderService csvReaderService;
    private final ConcurrentProcessingService processingService;

    public EmployeeController(CsvReaderService csvReaderService,
                              ConcurrentProcessingService processingService) {
        this.csvReaderService = csvReaderService;
        this.processingService = processingService;
    }

    @GetMapping("/process")
    public List<Employee> processEmployees() throws Exception {

        List<Employee> employees =
                csvReaderService.readEmployees("employees.csv");

        processingService.processEmployees(employees);

        return employees;
    }
}
