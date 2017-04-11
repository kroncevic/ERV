package hr.tvz.rome.repository;

import hr.tvz.rome.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employee, Long> {


    Employee findByUsername(String username);

}
