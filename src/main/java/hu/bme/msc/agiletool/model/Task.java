package hu.bme.msc.agiletool.model;

import hu.bme.msc.agiletool.model.wrappers.CheckList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task extends AbstractTask{
    private ArrayList<String>   history;
    private ArrayList<String>   progressInfo;

    public Task() {
    }

//    public Task(String id, String title, Date createDate, ArrayList<String> keywords, String description, ArrayList<String> assignee, Complexity complexity, ArrayList<String> depending, BacklogStatus status, ArrayList<String> history, ArrayList<String> progressInfo) {
//        super(id, title, createDate, keywords, description, assignee, complexity, depending, status, BacklogItemType.TASK);
//        this.history = history;
//        this.progressInfo = progressInfo;
//    }


    public Task(String id, String title, Date createDate, ArrayList<String> keywords, String description, ArrayList<String> assignee, Complexity complexity, ArrayList<String> depending, BacklogStatus status, List<CheckList> checkList, ArrayList<String> history, ArrayList<String> progressInfo) {
        super(id, title, createDate, keywords, description, assignee, complexity, depending, status, BacklogItemType.TASK, checkList);
        this.history = history;
        this.progressInfo = progressInfo;
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<String> history) {
        this.history = history;
    }

    public ArrayList<String> getProgressInfo() {
        return progressInfo;
    }

    public void setProgressInfo(ArrayList<String> progressInfo) {
        this.progressInfo = progressInfo;
    }

    @Override
    public String toString() {
        return "Task{" +
                "history=" + history +
                ", progressInfo=" + progressInfo +
                '}';
    }
}
