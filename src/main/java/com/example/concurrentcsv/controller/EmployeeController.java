package com.example.concurrentcsv.controller;

import com.example.concurrentcsv.model.Employee;
import com.example.concurrentcsv.service.ConcurrentProcessingService;
import com.example.concurrentcsv.service.CsvReaderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    private final ConcurrentProcessingService processingService;
    private final CsvReaderService csvReaderService;

    public EmployeeController(CsvReaderService csvReaderService,
                              ConcurrentProcessingService processingService) {
        this.csvReaderService = csvReaderService;
        this.processingService = processingService;
    }

    @PostMapping("/increase-salaries")
    public ResponseEntity<String> increaseSalaries(@RequestParam("file") MultipartFile file) throws Exception {

        List<Employee> employees = csvReaderService.readEmployees(file);
        processingService.processEmployees(employees);

        String outputPath = "updated_employees.csv";
        csvReaderService.saveUpdatedEmployees(employees, outputPath);

        return ResponseEntity.ok("Salaries updated successfully. File saved at " + outputPath);
    }

}
