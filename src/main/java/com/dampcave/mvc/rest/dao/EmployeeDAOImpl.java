package com.dampcave.mvc.rest.dao;

import com.dampcave.mvc.rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
//    @Transactional // открывает и закрывает сессии
    public List<Employee> getAllEmployee() {

        Session session = sessionFactory.getCurrentSession();

//        Query<Employee> query = session.createQuery("from Employee", Employee.class);
//        List<Employee> allEmployees = query.getResultList();

        List<Employee> allEmployees = session.createQuery("from Employee", Employee.class).getResultList();

        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {

        Session session = sessionFactory.getCurrentSession();

//        if (employee.getId() == 0) {
//            session.save(employee);
//        }else {                               //вместо этого есть session.saveOrUpdate(employee);
//            session.update(employee);
//        }

        session.saveOrUpdate(employee);

    }

    @Override
    public Employee getEmployee(int id) {

        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();

//        Employee employee = session.get(Employee.class, id);
//        session.delete(employee);

        Query<Employee> query = session.createQuery("delete from Employee where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
