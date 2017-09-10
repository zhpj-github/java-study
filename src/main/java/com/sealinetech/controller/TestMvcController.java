package com.sealinetech.controller;

import com.sealinetech.pojo.Employee;
import com.sealinetech.service.EmployeeService;
import com.sealinetech.service.impl.EmployeeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class TestMvcController {
    @RequestMapping("/e.dis")
    public String getPath(HttpServletRequest req, HttpServletResponse resp){
        EmployeeService service = new EmployeeServiceImpl();
        List<Employee> employeeList = service.loadAllEmployee();
        req.setAttribute("employeeList",employeeList);
        return "employee";
    }

}
