package com.controller;

import com.model.Employee;
import com.service.EmployeeService;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.logging.Logger;

/**
 * Created by aphisit on 02-Aug-17.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static Logger logger = Logger.getLogger(EmployeeController.class.getName());

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @CrossOrigin("*")
    @GetMapping(value = "/list",produces = "text/html; charset=utf-8",headers = "Accept=applicatino/json; charset=utf-8")
    public ResponseEntity<String> getEmployees(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json; charset=utf-8");
        try{
            logger.info("[getEmployee] -> Get data from list");
            return  new ResponseEntity<String>(new JSONSerializer().exclude("*.class").serialize(employeeService.findAll()),headers,HttpStatus.OK);
        }catch (Exception e) {
            logger.info("[postEmployee] -> Exception >>> " + e.getMessage());
            return new ResponseEntity<String>("{\"ERROR\":" +e.getMessage()+ "\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("*")
    @GetMapping(value = "/findById/{id}",produces = "text/html; charset=utf-8",headers = "Accept=applicatino/json; charset=utf-8")
    public ResponseEntity<String> findById(@RequestParam("id") Long id){
        logger.info("[getEmployee] -> Get Id");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json; charset=utf-8");
        try{
            logger.info("[getEmployee] -> Get Id");
            return new ResponseEntity<String>((new JSONSerializer().exclude("*.class").serialize(employeeService.findEmployeeById(id))),headers,HttpStatus.OK);
        }catch (Exception e){
            logger.info("[getEmployee] -> " + e.getMessage());
            return new ResponseEntity<String>("{\"ERROR\":" +e.getMessage()+ "\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("*")
    @GetMapping(value = "/findByFirstNameAndLastName",produces = "text/html; charset=utf-8",headers = "Accept=applicatino/json; charset=utf-8")
    public ResponseEntity<String> findByFirstNameAndLastName(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName){
        logger.info("[findByfirstName] -> Get firstNameAndLastName");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json; charset=utf-8");
        try{
            logger.info("[getEmployee] -> Get Id");
            return new ResponseEntity<String>((new JSONSerializer().exclude("*.class")
                    .serialize(employeeService.findByFistNameAndLastName(firstName,lastName))),headers,HttpStatus.OK);
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

        try{
            logger.info("[postEmployee] -> Insert data successful");
            employeeService.save(employee.toString());
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            logger.info("[postEmployee] -> Exception >>> " + e.getMessage());
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }
}
