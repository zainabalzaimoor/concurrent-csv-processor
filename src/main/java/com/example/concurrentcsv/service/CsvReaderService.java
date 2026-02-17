package com.example.concurrentcsv.service;

import com.example.concurrentcsv.model.Employee;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvReaderService {

    public List<Employee> readEmployees(MultipartFile file) throws Exception {

        List<Employee> employees = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] line;
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                employees.add(new Employee(
                        line[0],
                        Double.parseDouble(line[1]),
                        LocalDate.parse(line[2]),
                        line[3],
                        Double.parseDouble(line[4])
                ));

            }
        }

        return employees;
    }
}
