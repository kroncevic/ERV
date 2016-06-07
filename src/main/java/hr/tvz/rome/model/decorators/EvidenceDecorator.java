package hr.tvz.rome.model.decorators;

import hr.tvz.rome.model.EvidenceNew;

import java.text.SimpleDateFormat;

/**
 * Created by marko on 6/7/2016.
 */
public class EvidenceDecorator {

    private final static String dateFormat = "dd.MM.yyyy HH:mm:ss";

    private String username;
    private String name;
    private String timestamp;
    private String location;
    private String project;
    private String type;

    public EvidenceDecorator(EvidenceNew evidenceNew) {
        this.username = evidenceNew.getEmployee().getUsername();
        this.name = String.format("%s %s", evidenceNew.getEmployee().getFirstName(), evidenceNew.getEmployee().getLastName());
        this.timestamp = new SimpleDateFormat(dateFormat).format(evidenceNew.getTimestamp());
        this.location = evidenceNew.getLocation().getName();
        this.project = evidenceNew.getProject().getName();
        this.type = evidenceNew.getType().getName();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
