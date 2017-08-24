package com.sealinetech.dao.impl;

import com.sealinetech.dao.EmployeeDao;
import com.sealinetech.pojo.Employee;
import com.sealinetech.until.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public Integer insert(Employee employee) {
        DatabaseHelper helper=new DatabaseHelper();
        Connection connection=helper.getConnection();
        int result=0;
        try {
            PreparedStatement statement= connection.prepareStatement(
                    "INSERT INTO employee(no,name ,note) VALUES(?,?,?)");
            statement.setString(1,employee.getNo());
            statement.setString(2,employee.getName());
            statement.setString(3,employee.getNote());
            result = statement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Employee> query() {
        return null;
    }
}
