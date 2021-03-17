package testdataobjects;

public class RecentIndividualSession {

    String sessionType;
    String sessionNumber;
    String coacheeName;
    String lastSessionDate;
    String nextSessionDate;
    String site;
    String campaign;
    String aspect;
    String aspect_checkbox;
    String actionItems;
    String actionItemsDate;

    @Override
    public String toString() {
        return "RecentIndividualSession{" +
                "sessionType='" + sessionType + '\'' +
                ", sessionNumber='" + sessionNumber + '\'' +
                ", coacheeName='" + coacheeName + '\'' +
                ", lastSessionDate='" + lastSessionDate + '\'' +
                ", nextSessionDate='" + nextSessionDate + '\'' +
                ", site='" + site + '\'' +
                ", campaign='" + campaign + '\'' +
                ", aspect='" + aspect + '\'' +
                ", aspect_checkbox='" + aspect_checkbox + '\'' +
                ", actionItems='" + actionItems + '\'' +
                ", actionItemsDate='" + actionItemsDate + '\'' +
                '}';
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getCoacheeName() {
        return coacheeName;
    }

    public void setCoacheeName(String coacheeName) {
        this.coacheeName = coacheeName;
    }

    public String getLastSessionDate() {
        return lastSessionDate;
    }

    public void setLastSessionDate(String lastSessionDate) {
        this.lastSessionDate = lastSessionDate;
    }

    public String getNextSessionDate() {
        return nextSessionDate;
    }

    public void setNextSessionDate(String nextSessionDate) {
        this.nextSessionDate = nextSessionDate;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getAspect() {
        return aspect;
    }

    public void setAspect(String aspect) {
        this.aspect = aspect;
    }

    public String getAspect_checkbox() {
        return aspect_checkbox;
    }

    public void setAspect_checkbox(String aspect_checkbox) {
        this.aspect_checkbox = aspect_checkbox;
    }

    public String getActionItems() {
        return actionItems;
    }

    public void setActionItems(String actionItems) {
        this.actionItems = actionItems;
    }

    public String getActionItemsDate() {
        return actionItemsDate;
    }

    public void setActionItemsDate(String actionItemsDate) {
        this.actionItemsDate = actionItemsDate;
    }
}