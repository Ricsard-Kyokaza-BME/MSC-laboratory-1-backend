package hu.bme.msc.agiletool.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardResolving {

    private String id;
    private ArrayList<BacklogItem> backlog;
    private ArrayList<BacklogItem> todo;
    private ArrayList<BacklogItem> inprogress;
    private ArrayList<BacklogItem> done;


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

    public ArrayList<BacklogItem> getInprogress() {
        return inprogress;
    }

    public void setInprogress(ArrayList<BacklogItem> inprogress) {
        this.inprogress = inprogress;
    }

    public ArrayList<BacklogItem> getDone() {
        return done;
    }

    public void setDone(ArrayList<BacklogItem> done) {
        this.done = done;
    }
}
