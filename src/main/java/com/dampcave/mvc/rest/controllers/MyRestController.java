package com.dampcave.mvc.rest.controllers;

import com.dampcave.mvc.rest.entity.Employee;
import com.dampcave.mvc.rest.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployee(){

        List<Employee> allEmployees = employeeService.getAllEmployee();
        return allEmployees;

    }






}
