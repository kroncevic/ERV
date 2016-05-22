package hr.tvz.rome.services.employee;

import hr.tvz.rome.model.Employee;

import java.util.List;

/**
 * Created by Marko on 22.5.2016..
 */
public interface EmployeeService {
    List<Employee> fetchAllEmployees();
}
