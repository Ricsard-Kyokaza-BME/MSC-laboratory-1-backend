package hu.bme.msc.agiletool.model;

public enum Roles {
    USER ("USER"),
    PO ("PO");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

    public boolean isRoleEquales(String o) {
        if(o == null){
            return false;
        }
        return role.equals(o);
    }

    public String toString() {
        return this.role;
    }
}
