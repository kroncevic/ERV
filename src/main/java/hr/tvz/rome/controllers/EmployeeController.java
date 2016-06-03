package hr.tvz.rome.controllers;

import hr.tvz.rome.model.Employee;
import hr.tvz.rome.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Marko on 22.5.2016..
 */
@RestController
@RequestMapping("/rest")
public class EmployeeController {

    @Autowired
    private EmployeesRepository employeesRepository;

    @RequestMapping(value = "/employee/", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> listAllUsers() {
        List<Employee> employees = employeesRepository.findAll();
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            employees.forEach(employee -> {

            });
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }
    }

}
