package hr.tvz.rome.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class BusinessUnit {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    
    private String name;
    
	private String executive;
    
    @OneToMany(mappedBy = "businessUnit", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List <Employee> employees;
    
	public long getId() {
		return id;
	}
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public String getExecutive() {
		return executive;
	}

	public void setExecutive(String executive) {
		this.executive = executive;
	}

}
