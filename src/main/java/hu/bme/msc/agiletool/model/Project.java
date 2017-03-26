package hu.bme.msc.agiletool.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Project {

    @Id
    private String id;

    private String dashboardId;

    private String name;
    private String description;
    private List<String> usersInProject;

    public Project(){}

    public Project(String id, String dashboardId, String name, String description, List<String> usersInProject) {
        this.id = id;
        this.dashboardId = dashboardId;
        this.name = name;
        this.description = description;
        this.usersInProject = usersInProject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getUsersInProject() {
        return usersInProject;
    }

    public void setUsersInProject(List<String> usersInProject) {
        this.usersInProject = usersInProject;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", dashboardId='" + dashboardId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", usersInProject=" + usersInProject +
                '}';
    }
}
