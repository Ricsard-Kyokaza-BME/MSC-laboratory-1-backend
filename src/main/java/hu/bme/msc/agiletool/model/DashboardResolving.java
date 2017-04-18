package hu.bme.msc.agiletool.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardResolving {

    HashMap<String, Map<Integer, BacklogItem>> dashboardCollection;

    public DashboardResolving() {
        dashboardCollection = new HashMap<>();
    }

    public DashboardResolving(HashMap<String, Map<Integer, BacklogItem>> dashboardCollection) {
        this.dashboardCollection = dashboardCollection;
    }

    public HashMap<String, Map<Integer, BacklogItem>> getDashboardCollection() {
        return dashboardCollection;
    }

    public void setDashboardCollection(HashMap<String, Map<Integer, BacklogItem>> dashboardCollection) {
        this.dashboardCollection = dashboardCollection;
    }

    public void add(String key, Map<Integer, BacklogItem> value){
        dashboardCollection.put(key, value);
    }
}
