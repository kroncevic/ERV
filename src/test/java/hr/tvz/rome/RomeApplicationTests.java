package hr.tvz.rome;

import hr.tvz.rome.model.DatePresentation;
import hr.tvz.rome.model.Employee;
import hr.tvz.rome.model.EvidenceNew;
import hr.tvz.rome.model.EvidenceType;
import hr.tvz.rome.model.Vacation;
import hr.tvz.rome.repository.EmployeesRepository;
import hr.tvz.rome.repository.EvidenceRepository;
import hr.tvz.rome.repository.EvidenceTypeRepository;
import hr.tvz.rome.service.DatePresentationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RomeApplication.class)
@WebAppConfiguration
public class RomeApplicationTests {

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    EvidenceTypeRepository evidenceTypeRepository;


    @Autowired
    DatePresentationService datePresentationService;

    @Autowired
    EvidenceRepository evidenceRepository;

    @Test
    public void test() {
    	Employee employee = new Employee();
    	employee.setVacations(new ArrayList<Vacation>());
    	employee.getVacations().add(new Vacation());
    	employee = employeesRepository.save(employee);
    	Employee employee2 = employeesRepository.findOne(employee.getId());
    	Assert.assertNotNull(employee2);
    	Assert.assertNotNull(employee2.getVacations());
    }
    
    @Test
    public void test1() {
        List<Employee> employees = employeesRepository.findAll();
        Assert.assertNotNull(employees);
        Employee employee = employeesRepository.findByUsername("dlovric");
        Assert.assertTrue(new BCryptPasswordEncoder().matches("password", employee.getPassword()));
        Assert.assertNotNull(employee);
    }

    /*
    @Test
    public void test2() {
        employeesRepository.deleteAll();
        Employee employee = new Employee("Valentina", "Kovač", "vkovac", new BCryptPasswordEncoder().encode("password"), "USER","VSS","magistrica ekonomije","FFZG","Odjel prodaje","prodajna predstavnica","neodređeno","vkocac@adriait.hr","0985569847","Vonćinina 12","Zagreb","Hrvatska","hrvatsko",new Date(408409200000L),new Date(1304200800000L));
        employeesRepository.save(employee);
        employee = new Employee("Daniel", "Lovrić", "dlovric", new BCryptPasswordEncoder().encode("password"), "USER","VSS","magistar ekonomije","FFZG","Odjel prodaje","prodajni predstavnik","neodređeno","dlovric@adriait.hr","0954471258","Kustošijanska 141","Zagreb","Hrvatska","hrvatsko", new Date(-176263200000L), new Date(1275343200000L));
        employeesRepository.save(employee);
        employee = new Employee("Matea", "Rabić", "mrabic", new BCryptPasswordEncoder().encode("password"), "USER","VSŠ","inženjerka računarstva","TVZ","Aplikativni odjel","programerka","neodređeno","mrabic@adriait.hr","0995587444","Ilica 218","Zagreb","Hrvatska","hrvatsko", new Date(556149600000L), new Date(1399154400000L));
        employeesRepository.save(employee);
        employee = new Employee("Nikola", "Lalić", "nlalic", new BCryptPasswordEncoder().encode("password"), "USER","VSS","inženjer računarstva","FER","Aplikativni odjel","programer","neodređeno","nlalic@adriait.hr","0991142369","Frankopanska 9","Zagreb","Hrvatska", "hrvatsko", new Date(70153200000L), new Date(1275343200000L));
        employeesRepository.save(employee);
        employee = new Employee("Hrvoje", "Marinović", "hmarinovic", new BCryptPasswordEncoder().encode("password"), "USER","VSS","inženjer računarstva","FER","Aplikativni odjel","programer","neodređeno","hmarinovic@adriait.hr","0912191447","Lhotkina 21","Zagreb","Hrvatska","hrvatsko", new Date(16495200000L), new Date(1280613600000L));
        employeesRepository.save(employee);
        employee = new Employee("Klara", "Dalić", "kdalic", new BCryptPasswordEncoder().encode("password"), "USER","VSŠ","inženjerka računarstva","Algebra","Aplikativni odjel","programerka","neodređeno","kdalic@adriait.hr","0912236584","Krležina  11","Zagreb","Hrvatska","hrvatsko", new Date(666572400000L), new Date(1401573600000L));
        employeesRepository.save(employee);
        employee = new Employee("Vlatko", "Horvat", "vhorvat", new BCryptPasswordEncoder().encode("password"), "USER","VSŠ","inženjer računarstva","TVZ","Aplikativni odjel","programer","neodređeno","vhorvat@adriait.hr","0915541212","Mirogojska 122","Zagreb","Hrvatska","hrvatsko", new Date(752626800000L), new Date(1443650400000L));
        employeesRepository.save(employee);
        employee = new Employee("Sven", "Kota", "skota", new BCryptPasswordEncoder().encode("password"), "USER","VSS","inženjer računarstva","TVZ","Aplikativni odjel","programer","neodređeno","skota@adriait.hr","0998547123","Lipanjska 13","Zagreb","Hrvatska","hrvatsko", new Date(578354400000L), new Date(1401573600000L));
        employeesRepository.save(employee);
        employee = new Employee("Vilim", "Čavka", "vcavka", new BCryptPasswordEncoder().encode("password"), "ADMIN","VSS","inženjer računarstva","TVZ","Mrežni odjel","mrežni administrator","neodređeno","vcavka@adriait.hr","0954742192","Sarajevska 3","Zagreb","Hrvatska","hrvatsko", new Date(64364400000L), new Date(1275343200000L));
        employeesRepository.save(employee);
        employee = new Employee("Andrej", "Jug", "ajug", new BCryptPasswordEncoder().encode("password"), "USER","VSS","inženjer računarstva","TVZ","Mrežni odjel","mrežni administrator","neodređeno","ajug@adriait.hr","0958774121","Lopatinečka 15","Zagreb","Hrvatska","hrvatsko", new Date(583279200000L), new Date(1404165600000L));
        employeesRepository.save(employee);
        employee = new Employee("Lidija", "Marić", "lmaric", new BCryptPasswordEncoder().encode("password"), "ADMIN","VSS","magistrica ekonomije","FFZG","Odjel prodaje","tajnica","neodređeno","lmaric@adriait.hr","0914472195","Brezje 16","Zagreb","Hrvatska","hrvatsko", new Date(-27046800000L), new Date(1296514800000L));
        employeesRepository.save(employee);
        employee = new Employee("Mila", "Petroci", "mpetroci", new BCryptPasswordEncoder().encode("password"), "ADMIN","VSS","magistrica ekonomije","FFZG","Odjel prodaje","direktorica","neodređeno","mpetroci@adriait.hr","0978854741","Lašcinska 118","Zagreb","Hrvatska","hrvatsko", new Date(-136605600000L), new Date(1296514800000L));
        employeesRepository.save(employee);
        employee = new Employee("Leo", "Janković", "ljankovic", new BCryptPasswordEncoder().encode("password"), "ADMIN","VSS","inženjer računarstva","FER","Aplikativni odjel","direktor","neodređeno","ljankovic@adriait.hr","0978421558","Zagorska 101","Zagreb","Hrvatska","hrvatsko", new Date(-48823200000L), new Date(1296514800000L));
        employeesRepository.save(employee);
        employee = new Employee("Igor", "Tomić", "itomic", new BCryptPasswordEncoder().encode("password"), "ADMIN","VSS","inženjer računarstva","FER","Aplikativni odjel","direktor","neodređeno","itomic@adriait.hr","0958874482","Selska 6","Zagreb","Hrvatska","hrvatsko", new Date(52264800000L), new Date(1296514800000L));
        employeesRepository.save(employee);
        employee = new Employee("Miso", "Ilić", "milic", new BCryptPasswordEncoder().encode("password"), "ADMIN","VSS","inženjer računarstva","FER","","vlasnik","","milic@adriait.hr","0925472789","Krapinska 18","Zagreb","Hrvatska","hrvatsko", new Date(-285382800000L), new Date(1296514800000L));
        employeesRepository.save(employee);
        employee = new Employee("John", "Doe", "admin", new BCryptPasswordEncoder().encode("password"), "ADMIN");
        employeesRepository.save(employee);
        List<Employee> employees = employeesRepository.findAll();
        Assert.assertNotNull(employees);

    }
    */
    

    @Test
    public void test3() {
        evidenceTypeRepository.deleteAll();
        EvidenceType evidenceType = new EvidenceType("Prijava");
        evidenceTypeRepository.saveAndFlush(evidenceType);
        evidenceType = new EvidenceType("Odjava");
        evidenceTypeRepository.saveAndFlush(evidenceType);
    }

    @Test
    public void test4() {
        DatePresentation datePresentation = datePresentationService.getDatePresentation(LocalDate.now());
        Assert.assertNotNull(datePresentation);
    }

    @Test
    public void test5() {
        evidenceRepository.deleteAll();
    }

}
