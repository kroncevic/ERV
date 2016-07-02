package hr.tvz.rome.model;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import hr.tvz.rome.model.serialization.JsonDateDeserializer;

@Entity
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Employee employee;
    //private Integer duration;
    //private Integer year;
    //private String note;
    
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
    
   public Vacation (Integer vacationDaysAllowed, Integer vacationDaysUsedAll, Integer vacationDaysLeft, Integer vacationDaysUsed, Date vacationStart, Date vacationEnd){
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

   /* public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }*/
    

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
	
	public Date getvacationStart() {
		return vacationStart;
	}
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void vacationStart(Date vacationStart) {
		this.vacationStart = vacationStart;
	}

	public Date getvacationEnd() {
		return vacationEnd;
	}
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void vacationEnd(Date vacationEnd) {
		this.vacationEnd = vacationEnd;
	}


}
