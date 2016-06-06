package hr.tvz.rome.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BusinessUnit {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    
    private String name;
    private String note;
    
    @ManyToOne(cascade=CascadeType.MERGE)
	private Employee executive;
    @ManyToOne(cascade=CascadeType.MERGE)
	private BusinessUnit parent;
    
	public long getId() {
		return id;
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
	public Employee getExecutive() {
		return executive;
	}
	public void setExecutive(Employee executive) {
		this.executive = executive;
	}
	public BusinessUnit getParent() {
		return parent;
	}
	public void setParent(BusinessUnit parent) {
		this.parent = parent;
	}

}
