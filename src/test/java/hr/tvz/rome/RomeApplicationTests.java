package hr.tvz.rome;

import hr.tvz.rome.model.Employee;
import hr.tvz.rome.model.Evidence;
import hr.tvz.rome.repository.EmployeesRepository;
import hr.tvz.rome.repository.EvidenceRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RomeApplication.class)
@WebAppConfiguration
public class RomeApplicationTests {

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    EvidenceRepository evidenceRepository;


    @Test
    public void test1() {
        List<Employee> employees = employeesRepository.findAll();
        Assert.assertNotNull(employees);
        Employee employee = employeesRepository.findByUsername("dlovric");
        Assert.assertTrue(new BCryptPasswordEncoder().matches("password", employee.getPassword()));
        Assert.assertNotNull(employee);
    }

    @Test
    public void test2() {
        employeesRepository.deleteAll();
        Employee employee = new Employee("Valentina", "Kovac", "vkovac", new BCryptPasswordEncoder().encode("password"), "USER");
        employeesRepository.save(employee);
        employee = new Employee("Daniel", "Lovric", "dlovric", new BCryptPasswordEncoder().encode("password"), "USER");
        employeesRepository.save(employee);
        employee = new Employee("Matea", "Rabic", "mrabic", new BCryptPasswordEncoder().encode("password"), "USER");
        employeesRepository.save(employee);
        employee = new Employee("Nikola", "Lalic", "nlalic", new BCryptPasswordEncoder().encode("password"), "USER");
        employeesRepository.save(employee);
        employee = new Employee("Hrvoje", "Marinovic", "hmarinovic", new BCryptPasswordEncoder().encode("password"), "USER");
        employeesRepository.save(employee);
        employee = new Employee("Klara", "Dalic", "kdalic", new BCryptPasswordEncoder().encode("password"), "USER");
        employeesRepository.save(employee);
        employee = new Employee("Vlatko", "Horvat", "vhorvat", new BCryptPasswordEncoder().encode("password"), "USER");
        employeesRepository.save(employee);
        employee = new Employee("Sven", "Kota", "skota", new BCryptPasswordEncoder().encode("password"), "USER");
        employeesRepository.save(employee);
        employee = new Employee("Vilim", "Cavka", "vcavka", new BCryptPasswordEncoder().encode("password"), "ADMIN");
        employeesRepository.save(employee);
        employee = new Employee("Andrej", "Jug", "ajug", new BCryptPasswordEncoder().encode("password"), "USER");
        employeesRepository.save(employee);
        employee = new Employee("Lidija", "Maric", "lmaric", new BCryptPasswordEncoder().encode("password"), "ADMIN");
        employeesRepository.save(employee);
        employee = new Employee("Mila", "Petroci", "mpetroci", new BCryptPasswordEncoder().encode("password"), "ADMIN");
        employeesRepository.save(employee);
        employee = new Employee("Leo", "Jankovic", "ljankovic", new BCryptPasswordEncoder().encode("password"), "ADMIN");
        employeesRepository.save(employee);
        employee = new Employee("Igor", "Tomic", "itomic", new BCryptPasswordEncoder().encode("password"), "ADMIN");
        employeesRepository.save(employee);
        employee = new Employee("Miso", "Ilic", "milic", new BCryptPasswordEncoder().encode("password"), "ADMIN");
        employeesRepository.save(employee);
        employee = new Employee("John", "Doe", "admin", new BCryptPasswordEncoder().encode("password"), "ADMIN");
        employeesRepository.save(employee);
        List<Employee> employees = employeesRepository.findAll();
        Assert.assertNotNull(employees);

    }

    @Test
    public void test3(){
        List<Evidence> evidences =evidenceRepository.findAll();
        Assert.assertNotNull(evidences);
    }

}
