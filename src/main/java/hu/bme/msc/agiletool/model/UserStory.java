package hu.bme.msc.agiletool.model;

import hu.bme.msc.agiletool.model.wrappers.CheckList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserStory extends BacklogItem {

    private ArrayList<String> subtasks;
    private List<CheckList> definitionOfDone;
    private List<CheckList> acceptanceCriteria;

    public UserStory() {
    }

//    public UserStory(String id, String title, Date createDate, ArrayList<String> keywords, String description, ArrayList<String> assignee, Complexity complexity, ArrayList<String> depending, BacklogStatus status, ArrayList<String> subtasks, String definitionOfDone, String acceptanceCriteria) {
//        super(id, title, createDate, keywords, description, assignee, complexity, depending, status, BacklogItemType.USER_STORY);
//        this.subtasks = subtasks;
//        this.definitionOfDone = definitionOfDone;
//        this.acceptanceCriteria = acceptanceCriteria;
//    }


    public UserStory(String id, String title, Date createDate, ArrayList<String> keywords, String description, ArrayList<String> assignee, Complexity complexity, ArrayList<String> depending, BacklogStatus status, List<CheckList> checkList, ArrayList<String> subtasks, List<CheckList> definitionOfDone, List<CheckList> acceptanceCriteria) {
        super(id, title, createDate, keywords, description, assignee, complexity, depending, status, BacklogItemType.USER_STORY, checkList);
        this.subtasks = subtasks;
        this.definitionOfDone = definitionOfDone;
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public ArrayList<String> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(ArrayList<String> subtasks) {
        this.subtasks = subtasks;
    }

    public List<CheckList> getDefinitionOfDone() {
        return definitionOfDone;
    }

    public void setDefinitionOfDone(List<CheckList> definitionOfDone) {
        this.definitionOfDone = definitionOfDone;
    }

    public List<CheckList> getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(List<CheckList> acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }
}
