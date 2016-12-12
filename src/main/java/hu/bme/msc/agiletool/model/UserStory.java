package hu.bme.msc.agiletool.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class UserStory extends BacklogItem {

    private ArrayList<AbstractTask> subtasks;
    private String                  definitionOfDone;
    private String                  acceptanceCriteria;

    public UserStory() {
    }

    public UserStory(String title, Date creationDate, ArrayList<String> keywords, String description, ArrayList<String> assignee, Complexity complexity, ArrayList<String> depending, BacklogStatus status, ArrayList<AbstractTask> subtasks, String definitionOfDone, String acceptanceCriteria) {
        super(title, creationDate, keywords, description, assignee, complexity, depending, status);
        this.subtasks = subtasks;
        this.definitionOfDone = definitionOfDone;
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public ArrayList<AbstractTask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(ArrayList<AbstractTask> subtasks) {
        this.subtasks = subtasks;
    }

    public String getDefinitionOfDone() {
        return definitionOfDone;
    }

    public void setDefinitionOfDone(String definitionOfDone) {
        this.definitionOfDone = definitionOfDone;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }
}
