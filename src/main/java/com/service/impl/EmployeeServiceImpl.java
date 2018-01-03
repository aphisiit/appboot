package com.service.impl;

import com.model.Employee;
import com.repo.EmployeeRepository;
import com.service.EmployeeService;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository repository;

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(String json) {
        Employee employee = new JSONDeserializer<Employee>()
                .use(null,Employee.class)
                .use("value",Employee.class)
                .deserialize(json);
        repository.save(employee);
    }

    @Override
    public List<Employee> findByFistNameAndLastName(String firstName, String lastName) {
        return repository.findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContainingOrderByFirstName(firstName,lastName);
    }
}
