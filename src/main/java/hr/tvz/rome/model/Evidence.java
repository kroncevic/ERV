package hr.tvz.rome.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Marko on 4.6.2016..
 */
@Entity
public class Evidence {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String username;
    private String type;
    private String location;
    private String project;

    public Evidence() {
    }

    public Evidence(String username, String type, String location, String project) {
        this.username = username;
        this.type = type;
        this.location = location;
        this.project = project;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
