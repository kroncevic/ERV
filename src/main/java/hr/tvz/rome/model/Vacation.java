package hr.tvz.rome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    private Employee employee;

    private Integer vacationDaysAllowed;

    private Integer vacationDaysUsedAll;

    private Integer vacationDaysLeft;

    private Integer vacationDaysUsed;

    @Column(columnDefinition = "date")
    private Date vacationStart;

    @Column(columnDefinition = "date")
    private Date vacationEnd;


    public Vacation() {
    }

    public Vacation(Integer vacationDaysAllowed, Integer vacationDaysUsedAll, Integer vacationDaysLeft, Integer vacationDaysUsed, Date vacationStart, Date vacationEnd) {
        this.vacationDaysAllowed = vacationDaysAllowed;
        this.vacationDaysUsedAll = vacationDaysUsedAll;
        this.vacationDaysLeft = vacationDaysLeft;

        this.setVacationDaysUsed(vacationDaysUsed);
        this.vacationStart = vacationStart;
        this.vacationEnd = vacationEnd;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getVacationDaysAllowed() {
        return vacationDaysAllowed;
    }

    public void setVacationDaysAllowed(Integer vacationDaysAllowed) {
        this.vacationDaysAllowed = vacationDaysAllowed;
    }

    public Integer getVacationDaysUsedAll() {
        return vacationDaysUsedAll;
    }

    public void setVacationDaysUsedAll(Integer vacationDaysUsedAll) {
        this.vacationDaysUsedAll = vacationDaysUsedAll;
    }

    public Integer getVacationDaysLeft() {
        return vacationDaysLeft;
    }

    public void setVacationDaysLeft(Integer vacationDaysLeft) {
        this.vacationDaysLeft = vacationDaysLeft;
    }

    public Integer getVacationDaysUsed() {
        return vacationDaysUsed;
    }

    public void setVacationDaysUsed(Integer vacationDaysUsed) {
        this.vacationDaysUsed = vacationDaysUsed;
    }

    public Date getVacationStart() {
        return vacationStart;
    }

    public void setVacationStart(Date vacationStart) {
        this.vacationStart = vacationStart;
    }

    public Date getVacationEnd() {
        return vacationEnd;
    }

    public void setVacationEnd(Date vacationEnd) {
        this.vacationEnd = vacationEnd;
    }
}
