package com.service;

import com.model.Employee;

import java.util.List;

/**
 * Created by aphisit on 02-Aug-17.
 */
public interface EmployeeService {
    List<Employee> findAll();
    Employee findEmployeeById(Long id);
    void save(String json);
    List<Employee> findByFistNameAndLastName(String firstName,String lastName);

}
