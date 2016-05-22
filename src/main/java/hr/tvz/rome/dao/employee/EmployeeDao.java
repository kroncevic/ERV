package hr.tvz.rome.dao.employee;

import hr.tvz.rome.model.Employee;

import java.util.List;

/**
 * Created by Marko on 22.5.2016..
 */
public interface EmployeeDao {

    List<Employee> fetchAllWorkers();

}
