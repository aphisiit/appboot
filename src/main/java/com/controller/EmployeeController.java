package com.controller;

import com.model.Employee;
import com.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.standard.expression.GreaterOrEqualToExpression;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aphisit on 02-Aug-17.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }


    @CrossOrigin("*")
    @GetMapping("/list")
    public List<Employee> getEmployees(@RequestParam(required = false) String firstName,
                                       @RequestParam(required = false) String lastName,
                                       @RequestParam(required = false) String des){

        return  employeeRepository.findAll();
    }

    @CrossOrigin("*")
    @GetMapping("/findById")
    public Employee getEmployess(@RequestParam("id") Long id){
        return employeeRepository.findById(id);
    }

    @CrossOrigin("*")
    @PostMapping(value = "/save",produces = "text/html;charset=utf-8", headers = "Accept=application/json; charset=utf-8")
    public ResponseEntity postEmployee(@RequestParam String firstName,
                                       @RequestParam String lastName,
                                       @RequestParam String description){
        Employee employee = new Employee(firstName,lastName,description);

        try{
            employeeRepository.save(employee);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }

////        employeeRepository.save( employee);
//
//        if(true){
//            return new ResponseEntity(HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
    }
}
