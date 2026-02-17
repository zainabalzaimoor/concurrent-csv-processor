package com.example.concurrentcsv.model;

import lombok.*;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;

@Data
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    private String name;
    private AtomicReference<Double> salary;
    private LocalDate joinedDate;
    private String role;
    private double projectCompletionPercentage;
    // Explicit all-args constructor
    public Employee(String name, double salary, LocalDate joinedDate, String role, double projectCompletionPercentage) {
        this.name = name;
        this.salary = new AtomicReference<>(salary);
        this.joinedDate = joinedDate;
        this.role = role;
        this.projectCompletionPercentage = projectCompletionPercentage;
    }

    // Helper method to get the current salary as double
    public double getSalaryValue() {
        return salary.get();
    }

    // Helper method to update salary safely
    public void setSalaryValue(double newSalary) {
        salary.set(newSalary);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AtomicReference<Double> getSalary() {
        return salary;
    }

    public void setSalary(AtomicReference<Double> salary) {
        this.salary = salary;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getProjectCompletionPercentage() {
        return projectCompletionPercentage;
    }

    public void setProjectCompletionPercentage(double projectCompletionPercentage) {
        this.projectCompletionPercentage = projectCompletionPercentage;
    }
}
