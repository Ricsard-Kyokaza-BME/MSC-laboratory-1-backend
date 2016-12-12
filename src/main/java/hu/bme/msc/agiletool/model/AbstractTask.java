package hu.bme.msc.agiletool.model;

import java.util.ArrayList;
import java.util.Date;

public abstract class AbstractTask extends BacklogItem {

    public AbstractTask() {
    }

    public AbstractTask(String id, String title, Date createDate, ArrayList<String> keywords, String description, ArrayList<String> assignee, Complexity complexity, ArrayList<String> depending, BacklogStatus status, BacklogItemType type) {
        super(id, title, createDate, keywords, description, assignee, complexity, depending, status, type);
    }
}
