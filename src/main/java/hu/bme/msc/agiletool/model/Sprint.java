package hu.bme.msc.agiletool.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sprint {
    @Id
    private String id;

    private Date startTime;
    private Date endTime;
    private ArrayList<String> backlogItemsInvolved;

    public Sprint() {
        this.backlogItemsInvolved = new ArrayList<>();
    }

    public Sprint(String id, Date startTime, Date endTime, ArrayList<String> backlogItemsInvolved) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.backlogItemsInvolved = backlogItemsInvolved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public ArrayList<String> getBacklogItemsInvolved() {
        return backlogItemsInvolved;
    }

    public void setBacklogItemsInvolved(ArrayList<String> backlogItemsInvolved) {
        this.backlogItemsInvolved = backlogItemsInvolved;
    }
}
