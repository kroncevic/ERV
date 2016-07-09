package hr.tvz.rome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    private Employee employee;

    private String name;

    @Column(columnDefinition = "date")
    private Date examinationDate;

    @Column(columnDefinition = "date")
    private Date expirationDate;

    public Certificate() {

    }

    public Certificate(String name, Date examinationDate, Date expirationDate) {
        this.name = name;
        this.examinationDate = examinationDate;
        this.expirationDate = expirationDate;
    }

    public long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(Date examinationDate) {
        this.examinationDate = examinationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
