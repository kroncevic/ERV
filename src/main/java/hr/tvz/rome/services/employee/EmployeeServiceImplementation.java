package hr.tvz.rome.services.employee;

import hr.tvz.rome.dao.employee.EmployeeDao;
import hr.tvz.rome.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marko on 22.5.2016..
 */
@Service("employeeService")
@Transactional
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public List<Employee> fetchAllEmployees() {
        return employeeDao.fetchAllWorkers();
    }

}
