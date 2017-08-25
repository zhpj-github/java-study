package com.sealinetech.dao.impl;

import com.sealinetech.dao.EmployeeDao;
import com.sealinetech.pojo.Employee;
import com.sealinetech.until.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
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
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Employee> query() {
        List<Employee> employeeList = new ArrayList<>();
        DatabaseHelper helper=new DatabaseHelper();
        Connection connection=helper.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id,no,name,note FROM employee");
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setNo(resultSet.getString("no"));
                employee.setName(resultSet.getString("name"));
                employee.setNote(resultSet.getString("note"));
                employeeList.add(employee);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}
