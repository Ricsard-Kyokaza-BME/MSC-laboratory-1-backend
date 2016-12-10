package hu.bme.msc.agiletool.model;

public class UserSearchRequest {
    private String keyword;

    public UserSearchRequest() {
    }

    public UserSearchRequest(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
