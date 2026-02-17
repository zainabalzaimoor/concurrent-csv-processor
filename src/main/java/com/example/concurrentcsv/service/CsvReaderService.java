package com.example.concurrentcsv.service;

import com.example.concurrentcsv.model.Employee;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CsvReaderService {

    public List<Employee> readEmployees(String filePath) throws Exception {

        List<Employee> employees = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                employees.add(new Employee(
                        line[0],
                        Double.parseDouble(line[1]), // pass as double
                        LocalDate.parse(line[2]),
                        line[3],
                        Double.parseDouble(line[4])
                ));

            }
        }

        return employees;
    }
}
