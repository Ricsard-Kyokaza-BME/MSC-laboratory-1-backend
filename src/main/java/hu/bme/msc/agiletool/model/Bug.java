package hu.bme.msc.agiletool.model;

import java.util.ArrayList;
import java.util.Date;

public class Bug extends AbstractTask{
    public Bug() {
    }

    public Bug(String id, String title, Date createDate, ArrayList<String> keywords, String description, ArrayList<String> assignee, Complexity complexity, ArrayList<String> depending, BacklogStatus status) {
        super(id, title, createDate, keywords, description, assignee, complexity, depending, status, BacklogItemType.BUG);
    }
}
