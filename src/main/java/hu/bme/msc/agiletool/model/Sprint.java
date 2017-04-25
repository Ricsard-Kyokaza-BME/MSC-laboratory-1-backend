package hu.bme.msc.agiletool.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sprint {
    @Id
    private String id;

    private Date startTime;
    private Date endTime;
    private List<String> backlogItemsInvolved;

    public Sprint() {
        this.backlogItemsInvolved = new ArrayList<>();
    }

    public Sprint(String id, Date startTime, Date endTime, List<String> backlogItemsInvolved) {
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

    public List<String> getBacklogItemsInvolved() {
        return backlogItemsInvolved;
    }

    public void setBacklogItemsInvolved(List<String> backlogItemsInvolved) {
        this.backlogItemsInvolved = backlogItemsInvolved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sprint sprint = (Sprint) o;

        if (id != null ? !id.equals(sprint.id) : sprint.id != null) return false;
        if (startTime != null ? !startTime.equals(sprint.startTime) : sprint.startTime != null) return false;
        if (endTime != null ? !endTime.equals(sprint.endTime) : sprint.endTime != null) return false;
        return backlogItemsInvolved != null ? backlogItemsInvolved.equals(sprint.backlogItemsInvolved) : sprint.backlogItemsInvolved == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (backlogItemsInvolved != null ? backlogItemsInvolved.hashCode() : 0);
        return result;
    }
}
