package hu.bme.msc.agiletool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    public Map<Integer, String> getInProgress() {
        return inProgress;
    }

    public void setInProgress(Map<Integer, String> inProgress) {
        this.inProgress = inProgress;
    }

    public Map<Integer, String> getDone() {
        return done;
    }

    public void setDone(Map<Integer, String> done) {
        this.done = done;
    }

    public void addToBacklog(String value){
        this.backlog.put(this.backlog.size()+1, value);
    }

    public void addToTodo(String value){
        this.todo.put(this.todo.size()+1, value);
    }

    public void addToInProgress(String value){
        this.inProgress.put(this.inProgress.size()+1, value);
    }

    public void addToDone(String value){
        this.done.put(this.done.size()+1, value);
    }

    public boolean removeItem(String id){
        if(removeEntryFromMap(backlog, id) || removeEntryFromMap(todo, id) ||
            removeEntryFromMap(inProgress, id) || removeEntryFromMap(done, id)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean removeEntryFromMap(Map<Integer, String> map, String id) {
        for (Map.Entry<Integer, String> e : map.entrySet()) {
            Integer key = e.getKey();
            String value = e.getValue();
            if(value.equals(id)){
                map.remove(key, value);
                return true;
            }
        }

        return false;
    }

    @JsonIgnore
    public HashMap<String, Map<Integer, String>> getAllCollectionsFromDashboard(){
        HashMap retVal = new HashMap<Integer, Map<Integer,String>>();
        retVal.put("backlog", this.getBacklog());
        retVal.put("todo", this.getTodo());
        retVal.put("inProgress", this.getInProgress());
        retVal.put("done", this.getDone());

        return retVal;
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
