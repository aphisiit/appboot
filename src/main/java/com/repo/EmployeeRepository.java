package com.repo;

import com.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by aphisit on 01-Aug-17.
 */
@CrossOrigin("*")
public interface EmployeeRepository extends JpaSpecificationExecutor<Employee>,
        JpaRepository<Employee,Long>,PagingAndSortingRepository<Employee,Long> {

//    Employee findByFirstName(@Param("firstName") String firstName);
    @CrossOrigin("*")
    List<Employee> findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContainingOrderByFirstName(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName);

    @CrossOrigin("*")
    Employee findById(@Param("id") Long id);
}
