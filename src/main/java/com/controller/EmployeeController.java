package com.controller;

import com.model.Employee;
import flexjson.JSONSerializer;
import com.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by aphisit on 02-Aug-17.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static Logger logger = Logger.getLogger(EmployeeController.class.getName());

    private final EmployeeRepository employeeRepository;

    @Autowired
    EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }


    @CrossOrigin("*")
    @GetMapping("/list")
    public ResponseEntity<String> getEmployees(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json; charset=utf-8");
        try{
            logger.info("[getEmployee] -> Get data from list");
            return  new ResponseEntity<String>(new JSONSerializer().exclude("*.class").serialize(employeeRepository.findAll()),headers,HttpStatus.OK);
        }catch (Exception e) {
            logger.info("[postEmployee] -> Exception >>> " + e.getMessage());
            return new ResponseEntity<String>("{\"ERROR\":" +e.getMessage()+ "\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("*")
    @GetMapping("/findById")
    public ResponseEntity<String> findById(@RequestParam("id") Long id){
        logger.info("[getEmployee] -> Get Id");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json; charset=utf-8");
        try{
            logger.info("[getEmployee] -> Get Id");
            return new ResponseEntity<String>((new JSONSerializer().exclude("*.class").serialize(employeeRepository.findById(id))),headers,HttpStatus.OK);
        }catch (Exception e){
            logger.info("[getEmployee] -> " + e.getMessage());
            return new ResponseEntity<String>("{\"ERROR\":" +e.getMessage()+ "\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("*")
    @GetMapping("/findByFirstNameAndLastName")
    public ResponseEntity<String> findByFirstNameAndLastName(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName){
        logger.info("[findByfirstName] -> Get firstNameAndLastName");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json; charset=utf-8");
        try{
            logger.info("[getEmployee] -> Get Id");
            return new ResponseEntity<String>((new JSONSerializer().exclude("*.class")
                    .serialize(employeeRepository.findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining(firstName,lastName))),headers,HttpStatus.OK);
        }catch (Exception e){
            logger.info("[getEmployee] -> " + e.getMessage());
            return new ResponseEntity<String>("{\"ERROR\":" +e.getMessage()+ "\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("*")
    @PostMapping(value = "/save",produces = "text/html;charset=utf-8", headers = "Accept=application/json; charset=utf-8")
    public ResponseEntity<String> postEmployee(@RequestParam String firstName,
                                       @RequestParam String lastName,
                                       @RequestParam String description){
        Employee employee = new Employee(firstName,lastName,description);
        logger.info("[postEmployee] -> dataIN ==> firstName : " + firstName +
                " ,lastName : " + lastName + " ,description : " + description);

        try{
            logger.info("[postEmployee] -> Insert data successful");
            employeeRepository.save(employee);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            logger.info("[postEmployee] -> Exception >>> " + e.getMessage());
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }
}
