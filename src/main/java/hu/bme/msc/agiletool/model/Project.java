package hu.bme.msc.agiletool.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    @Id
    private String id;

    private String dashboardId;

    private String sprintId;

    private String name;
    private String description;
    private List<String> usersInProject;

    public Project() {
    }

    public Project(String id, String dashboardId, String sprintId, String name, String description, List<String> usersInProject) {
        this.id = id;
        this.dashboardId = dashboardId;
        this.sprintId = sprintId;
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

    public String getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(String dashboardId) {
        this.dashboardId = dashboardId;
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

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
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
