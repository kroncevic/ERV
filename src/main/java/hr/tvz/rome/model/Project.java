package hr.tvz.rome.model;

import javax.persistence.CascadeType;
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

	 @ManyToOne(cascade=CascadeType.MERGE)
	private Employee leaderEmployee;
	 @ManyToOne(cascade=CascadeType.MERGE)
	private DatePresentation startDate;
	 @ManyToOne(cascade=CascadeType.MERGE)
	private DatePresentation endDate;

	private String name;
	private String note;

	public long getId() {
		return id;
	}

	public Employee getLeaderEmployee() {
		return leaderEmployee;
	}

	public void setLeaderEmployee(Employee leaderEmployee) {
		this.leaderEmployee = leaderEmployee;
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
