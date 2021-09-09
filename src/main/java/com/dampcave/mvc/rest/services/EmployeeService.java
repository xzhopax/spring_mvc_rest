package com.dampcave.mvc.rest.services;

import com.dampcave.mvc.rest.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployee();

    public void saveEmployee(Employee employee);

    public Employee getEmployee(int id);

    public void deleteEmployee(int id);
}
