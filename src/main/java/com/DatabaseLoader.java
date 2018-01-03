package com;

import com.model.Employee;
import com.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by aphisit on 01-Aug-17.
 */
@Component
public class DatabaseLoader implements CommandLineRunner{

    private final EmployeeRepository repository;

    @Autowired
    public DatabaseLoader(EmployeeRepository repository){
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new Employee("Frodo","Baggines","Ring brear"));
        this.repository.save(new Employee("Jim","Kerk","Captain"));
    }
}
