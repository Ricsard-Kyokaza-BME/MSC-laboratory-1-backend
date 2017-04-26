package hu.bme.msc.agiletool.model;

import java.util.ArrayList;
import java.util.List;

public class DashboardResolving {

    private String id;
    private ArrayList<BacklogItem> backlog;
    private ArrayList<BacklogItem> todo;
    private ArrayList<BacklogItem> inProgress;
    private ArrayList<BacklogItem> done;
    private List<String> backlogItemsInTheSprint;

    public DashboardResolving() {
        this.backlog = new ArrayList<>();
        this.todo = new ArrayList<>();
        this.inProgress = new ArrayList<>();
        this.done = new ArrayList<>();
        this.backlogItemsInTheSprint = new ArrayList<>();
    }

    public DashboardResolving(String id) {
        this.id = id;
        this.backlog = new ArrayList<>();
        this.todo = new ArrayList<>();
        this.inProgress = new ArrayList<>();
        this.done = new ArrayList<>();
        this.backlogItemsInTheSprint = new ArrayList<>();
    }

    public DashboardResolving(String id, ArrayList<BacklogItem> backlog, ArrayList<BacklogItem> todo, ArrayList<BacklogItem> inProgress, ArrayList<BacklogItem> done, ArrayList<String> backlogItemsInTheSprint) {
        this.id = id;
        this.backlog = backlog;
        this.todo = todo;
        this.inProgress = inProgress;
        this.done = done;
        this.backlogItemsInTheSprint = backlogItemsInTheSprint;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<BacklogItem> getBacklog() {
        return backlog;
    }

    public void setBacklog(ArrayList<BacklogItem> backlog) {
        this.backlog = backlog;
    }

    public ArrayList<BacklogItem> getTodo() {
        return todo;
    }

    public void setTodo(ArrayList<BacklogItem> todo) {
        this.todo = todo;
    }

    public ArrayList<BacklogItem> getInProgress() {
        return inProgress;
    }

    public void setInProgress(ArrayList<BacklogItem> inProgress) {
        this.inProgress = inProgress;
    }

    public ArrayList<BacklogItem> getDone() {
        return done;
    }

    public void setDone(ArrayList<BacklogItem> done) {
        this.done = done;
    }

    public List<String> getBacklogItemsInTheSprint() {
        return backlogItemsInTheSprint;
    }

    public void setBacklogItemsInTheSprint(List<String> backlogItemsInTheSprint) {
        this.backlogItemsInTheSprint = backlogItemsInTheSprint;
    }
}
