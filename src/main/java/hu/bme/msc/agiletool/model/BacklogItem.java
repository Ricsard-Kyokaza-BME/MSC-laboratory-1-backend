package hu.bme.msc.agiletool.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hu.bme.msc.agiletool.model.wrappers.CheckList;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BacklogItem {

    @Id
    private String id;

    private String                title;
    private Date                  createDate;
    private ArrayList<String>     keywords;
    private String                description;
    private ArrayList<String>     assignee;
    private Complexity            complexity;
    private ArrayList<String>     depending;
    private BacklogStatus         status;
    private BacklogItemType       type;
    private List<CheckList>       checkList;

    public BacklogItem() { }

    public BacklogItem(String id, String title, Date createDate, ArrayList<String> keywords, String description, ArrayList<String> assignee, Complexity complexity, ArrayList<String> depending, BacklogStatus status, BacklogItemType type, List<CheckList> checkList) {
        this.id = id;
        this.title = title;
        this.createDate = createDate;
        this.keywords = keywords;
        this.description = description;
        this.assignee = assignee;
        this.complexity = complexity;
        this.depending = depending;
        this.status = status;
        this.type = type;
        this.checkList = checkList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getAssignee() {
        return assignee;
    }

    public void setAssignee(ArrayList<String> assignee) {
        this.assignee = assignee;
    }

    public Complexity getComplexity() {
        return complexity;
    }

    public void setComplexity(Complexity complexity) {
        this.complexity = complexity;
    }

    public ArrayList<String> getDepending() {
        return depending;
    }

    public void setDepending(ArrayList<String> depending) {
        this.depending = depending;
    }

    public BacklogStatus getStatus() {
        return status;
    }

    public void setStatus(BacklogStatus status) {
        this.status = status;
    }

    public BacklogItemType getType() {
        return type;
    }

    public void setType(BacklogItemType type) {
        this.type = type;
    }

    public List<CheckList> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<CheckList> checkList) {
        this.checkList = checkList;
    }

    @Override
    public String toString() {
        return "BacklogItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", createDate=" + createDate +
                ", keywords=" + keywords +
                ", description='" + description + '\'' +
                ", assignee=" + assignee +
                ", complexity=" + complexity +
                ", depending=" + depending +
                ", status=" + status +
                ", type=" + type +
                ", checkList=" + checkList +
                '}';
    }
}
