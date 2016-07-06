package hr.tvz.rome.model.decorators;

import hr.tvz.rome.model.EvidenceNew;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private String signInTimestamp;
    private String signOutTimestamp;
    private String uniqueId;

    public EvidenceDecorator() {
    }

    public EvidenceDecorator(EvidenceNew signIn) {
        if (signIn != null) {
            this.username = signIn.getEmployee().getUsername();
            this.name = String.format("%s %s", signIn.getEmployee().getLastName(), signIn.getEmployee().getFirstName());
            this.location = signIn.getLocation().getName();
            this.project = signIn.getProject().getName();
            if (signIn.getType() != null) {
                this.type = signIn.getType().getName();
            }
            this.signInTimestamp = new SimpleDateFormat(dateFormat).format(signIn.getTimestamp());
            this.signOutTimestamp = "-";
            this.uniqueId = signIn.getUniqueId();
        }
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

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getSignInTimestamp() {
        return signInTimestamp;
    }

    public void setSignInTimestamp(String signInTimestamp) {
        this.signInTimestamp = signInTimestamp;
    }

    public String getSignOutTimestamp() {
        return signOutTimestamp;
    }

    public void setSignOutTimestamp(String signOutTimestamp) {
        this.signOutTimestamp = signOutTimestamp;
    }

    public void setSignOutTimestampFromDate(Date signOutTimestamp) {
        this.signOutTimestamp = new SimpleDateFormat(dateFormat).format(signOutTimestamp);
    }
}
