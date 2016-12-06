package hu.bme.msc.agiletool.model;


import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;

public abstract class BacklogItem {

    @Id
    private String id;

    private String              title;
    private Date                createDate;
    private ArrayList<String>   keywords;
    private String              description;
    private ArrayList<User>     assignee;
    private Complexity          complexity;
    private ArrayList<BacklogItem> depending;
    private BacklogStatus       status;


    public BacklogItem() { }

    public BacklogItem(String title, Date createDate, ArrayList<String> keywords, String description, ArrayList<User> assignee, Complexity complexity, ArrayList<BacklogItem> depending, BacklogStatus status) {
        this.title = title;
        this.createDate = createDate;
        this.keywords = keywords;
        this.description = description;
        this.assignee = assignee;
        this.complexity = complexity;
        this.depending = depending;
        this.status = status;
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

    public ArrayList<User> getAssignee() {
        return assignee;
    }

    public void setAssignee(ArrayList<User> assignee) {
        this.assignee = assignee;
    }

    public Complexity getComplexity() {
        return complexity;
    }

    public void setComplexity(Complexity complexity) {
        this.complexity = complexity;
    }

    public ArrayList<BacklogItem> getDepending() {
        return depending;
    }

    public void setDepending(ArrayList<BacklogItem> depending) {
        this.depending = depending;
    }

    public BacklogStatus getStatus() {
        return status;
    }

    public void setStatus(BacklogStatus status) {
        this.status = status;
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
                '}';
    }
}
