package hr.tvz.rome.service.employee;

import hr.tvz.rome.controllers.entities.ChangePasswordRequest;
import hr.tvz.rome.model.Employee;
import hr.tvz.rome.repository.BusinessUnitRepository;
import hr.tvz.rome.repository.EmployeesRepository;
import hr.tvz.rome.utilities.DateTimeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marko on 12.6.2016..
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private EmployeesRepository employeesRepository;
    
    @Autowired
    private BusinessUnitRepository businessUnitRepository;

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = employeesRepository.findAll();
        if (employees.isEmpty()) {
            return null;
        } else {
            return employees;
        }
    }

    @Override
    public Employee findByUsername(String username) {
        return employeesRepository.findByUsername(username);
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        Employee employee = employeesRepository.findByUsername(username);

        if (employee != null && passwordEncoder.matches(request.getOldPassword(), employee.getPassword())
                && request.getNewPassword().equals(request.getNewPasswordConfirm())){
            employee.setPassword(passwordEncoder.encode(request.getNewPassword()));
            employeesRepository.saveAndFlush(employee);
        } else{
            throw new BadCredentialsException("Credentials don't match.");
        }
    }

    @Override
    public Employee create(Employee employee) {
        if(employee.getPassword() == null || employee.getPassword().isEmpty()){
            employee.setPassword(passwordEncoder.encode("password"));
        }
        if(employee.getAuthorization() == null || employee.getAuthorization().isEmpty()){
            employee.setAuthorization("USER");
        }	
        
        return employeesRepository.saveAndFlush(employee);
    }
}
