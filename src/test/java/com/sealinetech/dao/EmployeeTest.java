package com.sealinetech.dao;

import com.sealinetech.dao.impl.EmployeeDaoImpl;
import com.sealinetech.pojo.Employee;
import com.sealinetech.until.DatabaseHelper;
import org.junit.Test;

public class EmployeeTest {
    @Test
    public void connectionTest(){
        DatabaseHelper helper = new DatabaseHelper();
        System.out.println(helper.getConnection());
    }
    @Test
    public void insertEmployeeTest(){
        Employee employee = new Employee();
        employee.setName("zhangsan");
        employee.setNo("10001");
        employee.setNote("nothing");
        EmployeeDao dao=new EmployeeDaoImpl();
        System.out.println(dao.insert(employee));
    }
}
