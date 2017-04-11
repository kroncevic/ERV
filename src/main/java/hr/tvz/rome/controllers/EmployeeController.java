package hr.tvz.rome.controllers;

import hr.tvz.rome.controllers.entities.ChangePasswordRequest;
import hr.tvz.rome.model.Employee;
import hr.tvz.rome.model.Project;
import hr.tvz.rome.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/employee/", method = RequestMethod.GET)
    public List<Employee> listAllUsers() {
        return employeeService.findAll();
    }

    @RequestMapping(value = "/employee/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getUser(@PathVariable("username") String username) {
        return employeeService.findByUsername(username);
    }

    @RequestMapping(value = "/employee/password", method = RequestMethod.POST)
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            employeeService.changePassword(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BadCredentialsException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee request) {
        return new ResponseEntity<>(employeeService.create(request), HttpStatus.OK);
    }

}
