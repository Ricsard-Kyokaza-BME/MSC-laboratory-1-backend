package hu.bme.msc.agiletool.model.wrappers;

public class CheckList {
    private boolean done;
    private String content;

    public CheckList() {
    }

    public CheckList(boolean done, String content) {
        this.done = done;
        this.content = content;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
