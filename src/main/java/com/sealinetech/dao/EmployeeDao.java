package com.sealinetech.dao;

import com.sealinetech.pojo.Employee;

import java.util.List;

public interface EmployeeDao {
    Integer insert(Employee employee);
    List<Employee> query();
}
