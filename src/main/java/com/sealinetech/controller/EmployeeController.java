package com.sealinetech.controller;

import com.sealinetech.pojo.Employee;
import com.sealinetech.service.EmployeeService;
import com.sealinetech.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public class EmployeeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath=req.getServletPath().substring(1);
        String methodName=servletPath.substring(0,servletPath.indexOf('.'));
        try{
            Method method = getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeService service = new EmployeeServiceImpl();
        Employee employee = new Employee();
        String name=req.getParameter("name");
        String no=req.getParameter("no");
        String note=req.getParameter("note");
        name = new String(name.getBytes("ISO-8859-1"), "utf-8");
        no = new String(no.getBytes("ISO-8859-1"), "utf-8");
        note = new String(note.getBytes("ISO-8859-1"), "utf-8");
        employee.setNo(no);
        employee.setName(name);
        employee.setNote(note);
        service.addEmployee(employee);
        query(req,resp);
    }
    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeService service = new EmployeeServiceImpl();
        List<Employee> employeeList = service.loadAllEmployee();
        req.setAttribute("employeeList",employeeList);
        req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/employee.jsp").forward(req,resp);
    }
}
