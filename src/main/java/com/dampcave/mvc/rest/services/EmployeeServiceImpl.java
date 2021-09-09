package com.dampcave.mvc.rest.services;

import com.dampcave.mvc.rest.dao.EmployeeDAO;
import com.dampcave.mvc.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional // открывает и закрывает сессии
    public List<Employee> getAllEmployee() {
        return employeeDAO.getAllEmployee();
    }

    @Override
    @Transactional // открывает и закрывает сессии
    public void saveEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee);

    }

    @Override
    @Transactional // открывает и закрывает сессии
    public Employee getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }

    @Override
    @Transactional // открывает и закрывает сессии
    public void deleteEmployee(int id) {
       employeeDAO.deleteEmployee(id);
    }
}
