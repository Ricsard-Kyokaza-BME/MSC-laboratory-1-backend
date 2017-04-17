package hu.bme.msc.agiletool.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dashboard {

    @Id
    private String id;

    private Map<Integer, String> backlog;
    private Map<Integer, String> todo;
    private Map<Integer, String> inProgress;
    private Map<Integer, String> done;

    public Dashboard(){
        backlog = new HashMap<>();
        todo = new HashMap<>();
        inProgress = new HashMap<>();
        done = new HashMap<>();
    }

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

    public void addToBacklog(Integer i, String value){
        this.backlog.put(i, value);
    }

    public void addToTodo(Integer i, String value){
        this.todo.put(i, value);
    }

    public void addToInProgress(Integer i, String value){
        this.inProgress.put(i, value);
    }

    public void addToDone(Integer i, String value){
        this.done.put(i, value);
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
