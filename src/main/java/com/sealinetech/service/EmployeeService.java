package com.sealinetech.service;

import com.sealinetech.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    Integer addEmployee(Employee employee);
    List<Employee> loadAllEmployee();
}
