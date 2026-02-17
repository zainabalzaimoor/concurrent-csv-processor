package com.example.concurrentcsv.service;

import com.example.concurrentcsv.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ConcurrentProcessingService {

    private final ExecutorService executor =
            Executors.newFixedThreadPool(5);

    private final ReentrantLock lock = new ReentrantLock();

    private final Semaphore semaphore = new Semaphore(3);

    public void processEmployees(List<Employee> employees)
            throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(employees.size());

        for (Employee employee : employees) {

            executor.submit(() -> {
                try {
                    semaphore.acquire();  // limit concurrent access

                    processEmployee(employee);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    semaphore.release();
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();
    }

    private void processEmployee(Employee employee) {

        double incrementRate = SalaryCalculator.calculateIncrement(employee);

        if (incrementRate > 0) {
            lock.lock();
            try {
                double currentSalary = employee.getSalaryValue();
                double newSalary = currentSalary + (currentSalary * incrementRate);

                employee.setSalaryValue(newSalary);

            } finally {
                lock.unlock();
            }
        }
    }
}

