# Concurrent CSV Data Processor

A Spring Boot application for **concurrently processing employee data** from a CSV file.  
The application reads employee information, calculates salary increments based on **years worked, role, and project completion**, and updates the salaries using **multithreading**.

---

## ⭐ Features

- Read employee data from a CSV file.
- Apply salary increments concurrently using **thread pools**.
- Handle concurrency safely with `CountDownLatch` and other mechanisms.
- Apply business rules:
  - Employees with less than 60% project completion **do not receive an increase**.
  - Employees receive **2% per year worked** if completed at least one year.
  - Role-based increment:
    - Director → 5%
    - Manager → 2%
    - Employee → 1%
- Return updated employees in a csv file.

---

## 📂 CSV Template

A template file `employe.csv` is provided:

```csv
name,salary,joinedDate,role,projectCompletionPercentage
John Doe,5000,2023-01-01,Employee,75.0
```
- Headers must remain unchanged.
- Users can add multiple employees.
  
##  API Endpoints
- Increase Salaries
- URL: /employees/increase-salaries
- Method: POST
- Request: Multipart file upload (CSV)

Parameters:

| Name | Type          | Description                 |
| ---- | ------------- | --------------------------- |
| file | MultipartFile | CSV file with employee data |

