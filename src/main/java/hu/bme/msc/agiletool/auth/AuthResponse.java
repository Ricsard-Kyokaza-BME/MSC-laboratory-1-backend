package hu.bme.msc.agiletool.auth;

public class AuthResponse {

    private String status;

    public AuthResponse() {}

    public AuthResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
