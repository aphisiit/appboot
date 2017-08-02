package com.repo;

import com.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by aphisit on 01-Aug-17.
 */
public interface EmployeeRepository extends JpaSpecificationExecutor<Employee>,
        JpaRepository<Employee,Long>,PagingAndSortingRepository<Employee,Long> {

    Employee findEmployeeByFirstName(@Param("firstName") String firstName);
    Employee findById(@Param("id") Long id);
}
