package hu.bme.msc.agiletool.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardResolving {

    private String id;
    private Map<String, ArrayList<BacklogItem>> dashboardCollection;

    public DashboardResolving() {
        dashboardCollection = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, ArrayList<BacklogItem>> getDashboardCollection() {
        return dashboardCollection;
    }

    public void setDashboardCollection(Map<String, ArrayList<BacklogItem>> dashboardCollection) {
        this.dashboardCollection = dashboardCollection;
    }
    public void put(String typeInTheDashboardCollection, ArrayList<BacklogItem> itemsToBeAdded){
        this.dashboardCollection.put(typeInTheDashboardCollection, itemsToBeAdded);
    }
}
