package hr.tvz.rome.controllers.entities;

/**
 * Created by marko on 6/6/2016.
 */
public class EvidenceRequest {

    private String username;
    private String type;
    private String location;
    private String project;

    public EvidenceRequest() {
    }

    public EvidenceRequest(String username, String type, String location, String project) {
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
