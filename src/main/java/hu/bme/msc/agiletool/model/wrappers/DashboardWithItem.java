package hu.bme.msc.agiletool.model.wrappers;

import hu.bme.msc.agiletool.model.Dashboard;

public class DashboardWithItem {
    private Dashboard dashboard;
    private String backlogItem;

    public DashboardWithItem() {}

    public DashboardWithItem(Dashboard dashboard, String backlogItem) {
        this.dashboard = dashboard;
        this.backlogItem = backlogItem;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public String getBacklogItem() {
        return backlogItem;
    }

    public void setBacklogItem(String backlogItem) {
        this.backlogItem = backlogItem;
    }
}
