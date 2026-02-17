package com.example.concurrentcsv.service;

import com.example.concurrentcsv.model.Employee;

import java.time.LocalDate;
import java.time.Period;


public class SalaryCalculator {

    public static double calculateIncrement(Employee employee) {

        if (employee.getProjectCompletionPercentage() < 60) {
            return 0;
        }

        double increment = 0;

        // Years worked
        LocalDate joined = employee.getJoinedDate();
        LocalDate today = LocalDate.now();

        int yearsWorked = Period.between(joined, today).getYears();

        if (yearsWorked >= 1) {
            increment += yearsWorked * 0.02;
        }

        // Role-based increment
        switch (employee.getRole()) {
            case "Director":
                increment += 0.05;
                break;
            case "Manager":
                increment += 0.02;
                break;
            case "Employee":
                increment += 0.01;
                break;
        }

        return increment;
    }
}
