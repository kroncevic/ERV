package hr.tvz.rome.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToMany
	private Employee employee;
	@ManyToOne
	private DatePresentation startDate;
	@ManyToOne
	private DatePresentation endDate;

	private String name;
	private String note;

	public long getId() {
		return id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public DatePresentation getStartDate() {
		return startDate;
	}

	public void setStartDate(DatePresentation startDate) {
		this.startDate = startDate;
	}

	public DatePresentation getEndDate() {
		return endDate;
	}

	public void setExpirationDate(DatePresentation endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
