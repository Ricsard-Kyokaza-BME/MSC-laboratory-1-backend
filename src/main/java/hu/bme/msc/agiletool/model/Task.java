package hu.bme.msc.agiletool.model;

import java.util.ArrayList;

public class Task extends AbstractTask{
    private ArrayList<String>   history;
    private ArrayList<String>   progressInfo;

    public Task() {
    }

    public Task(ArrayList<String> history, ArrayList<String> progressInfo) {
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
