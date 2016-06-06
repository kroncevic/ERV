package hr.tvz.rome.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Certificate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private Employee employee;
	@ManyToOne
	private DatePresentation issueDate;
	@ManyToOne
	private DatePresentation expirationDate;

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

	public DatePresentation getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(DatePresentation issueDate) {
		this.issueDate = issueDate;
	}

	public DatePresentation getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(DatePresentation expirationDate) {
		this.expirationDate = expirationDate;
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
