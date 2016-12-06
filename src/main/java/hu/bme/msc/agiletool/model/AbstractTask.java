package hu.bme.msc.agiletool.model;

import org.springframework.data.annotation.Id;

public abstract class AbstractTask extends BacklogItem {
    @Id
    private String id;

    public AbstractTask() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AbstractTask{" +
                "id='" + id + '\'' +
                '}';
    }
}
