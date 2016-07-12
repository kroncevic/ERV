package hr.tvz.rome.repository;

import hr.tvz.rome.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Marko on 3.6.2016..
 */
public interface EmployeesRepository extends JpaRepository<Employee, Long> {

    Employee findByUsername(String username);

}
