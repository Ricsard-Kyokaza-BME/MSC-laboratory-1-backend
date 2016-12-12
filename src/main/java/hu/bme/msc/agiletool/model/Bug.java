package hu.bme.msc.agiletool.model;

import java.util.ArrayList;
import java.util.Date;

public class Bug extends AbstractTask{
    public Bug() {
    }

    public Bug(String title, Date createDate, ArrayList<String> keywords, String description, ArrayList<String> assignee, Complexity complexity, ArrayList<String> depending, BacklogStatus status) {
        super(title, createDate, keywords, description, assignee, complexity, depending, status);
    }
}
