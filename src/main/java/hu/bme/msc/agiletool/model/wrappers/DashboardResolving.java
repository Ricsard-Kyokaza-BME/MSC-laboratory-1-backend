package hu.bme.msc.agiletool.model.wrappers;

import hu.bme.msc.agiletool.model.BacklogItem;
import hu.bme.msc.agiletool.model.Sprint;

import java.util.ArrayList;
import java.util.List;

public class DashboardResolving {

    private String id;
    private ArrayList<BacklogItem> backlog;
    private ArrayList<BacklogItem> todo;
    private ArrayList<BacklogItem> inProgress;
    private ArrayList<BacklogItem> done;
//    private List<String> backlogItemsInTheSprint;
    private Sprint sprint;

    public DashboardResolving() {
        this.backlog = new ArrayList<>();
        this.todo = new ArrayList<>();
        this.inProgress = new ArrayList<>();
        this.done = new ArrayList<>();
    }

    public DashboardResolving(String id) {
        this.id = id;
        this.backlog = new ArrayList<>();
        this.todo = new ArrayList<>();
        this.inProgress = new ArrayList<>();
        this.done = new ArrayList<>();
    }

    public DashboardResolving(String id, ArrayList<BacklogItem> backlog, ArrayList<BacklogItem> todo, ArrayList<BacklogItem> inProgress, ArrayList<BacklogItem> done, ArrayList<String> backlogItemsInTheSprint) {
        this.id = id;
        this.backlog = backlog;
        this.todo = todo;
        this.inProgress = inProgress;
        this.done = done;
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

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }
}
