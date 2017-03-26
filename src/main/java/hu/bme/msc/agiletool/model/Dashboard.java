package hu.bme.msc.agiletool.model;

import org.springframework.data.annotation.Id;

import java.util.Map;

public class Dashboard {

    @Id
    private String id;

    private Map<Integer, String> backlog;
    private Map<Integer, String> todo;
    private Map<Integer, String> inProgress;
    private Map<Integer, String> done;

    public Dashboard(){}

    public Dashboard(String id, Map<Integer, String> backlog, Map<Integer, String> todo, Map<Integer, String> inProgress, Map<Integer, String> done) {
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

    public Map<Integer, String> getBacklog() {
        return backlog;
    }

    public void setBacklog(Map<Integer, String> backlog) {
        this.backlog = backlog;
    }

    public Map<Integer, String> getTodo() {
        return todo;
    }

    public void setTodo(Map<Integer, String> todo) {
        this.todo = todo;
    }

    public Map<Integer, String> getInprogress() {
        return inProgress;
    }

    public void setInprogress(Map<Integer, String> inProgress) {
        this.inProgress = inProgress;
    }

    public Map<Integer, String> getDone() {
        return done;
    }

    public void setDone(Map<Integer, String> done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Dashboard{" +
                "id='" + id + '\'' +
                ", backlog=" + backlog +
                ", todo=" + todo +
                ", inProgress=" + inProgress +
                ", done=" + done +
                '}';
    }
}
