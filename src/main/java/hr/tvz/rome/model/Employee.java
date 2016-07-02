package hr.tvz.rome.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hr.tvz.rome.model.serialization.JsonDateDeserializer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Marko on 22.5.2016..
 */
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String authorization;

    private String qualifications;

    private String vocation;

    private String university;

    private String department;

    private String workingPlace;

    private String contract;

    private String email;

    private String phoneNumber;

    private String address;

    private String country;

    private String city;

    private String citizenship;


    @Column(columnDefinition = "date")
    private Date birthDate;

    @Column(columnDefinition = "date")
    private Date employmentDate;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonManagedReference
    private List<Vacation> vacations;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String username, String password, String authorization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.authorization = authorization;
    }

    public Employee(String firstName, String lastName, String username, String password, String authorization,
                    String qualifications, String vocation, String university, String department,
                    String workingPlace, String contract, String email, String phoneNumber, String address,
                    String city, String country, String citizenship, Date birthDate, Date employmentDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.authorization = authorization;
        this.qualifications = qualifications;
        this.vocation = vocation;
        this.university = university;
        this.department = department;
        this.workingPlace = workingPlace;
        this.contract = contract;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.citizenship = citizenship;
        this.birthDate = birthDate;
        this.employmentDate = employmentDate;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getcitizenship() {
        return citizenship;
    }

    public void setcitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public List<Vacation> getVacations() {
        return vacations;
    }

    public void setVacations(List<Vacation> vacations) {
        this.vacations = vacations;
    }


}
