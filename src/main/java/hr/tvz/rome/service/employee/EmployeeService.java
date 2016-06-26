package hr.tvz.rome.service.employee;

import hr.tvz.rome.controllers.entities.ChangePasswordRequest;
import hr.tvz.rome.model.Employee;

import java.util.List;

/**
 * Created by Marko on 12.6.2016..
 */
public interface EmployeeService {

    List<Employee> findAll();
    Employee findByUsername(String username);
    void changePassword(ChangePasswordRequest request);
    Employee create(Employee employee);

}
