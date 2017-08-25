package com.sealinetech.service.impl;

import com.sealinetech.dao.EmployeeDao;
import com.sealinetech.dao.impl.EmployeeDaoImpl;
import com.sealinetech.pojo.Employee;
import com.sealinetech.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao dao=new EmployeeDaoImpl();
    @Override
    public Integer addEmployee(Employee employee) {
        return dao.insert(employee);
    }

    @Override
    public List<Employee> loadAllEmployee() {
        return dao.query();
    }
}
