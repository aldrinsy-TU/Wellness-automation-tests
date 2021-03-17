package testdataobjects;

public class RecentRequestSession {

    String coacheeName;
    String site;
    String campaign;
    String registrationDate;
    String status;
    String coach;

    public String getCoacheeName() {
        return coacheeName;
    }

    public void setCoacheeName(String coacheeName) {
        this.coacheeName = coacheeName;
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    @Override
    public String toString() {
        return "RecentSession{" +
                "coacheeName='" + coacheeName + '\'' +
                ", site='" + site + '\'' +
                ", campaign='" + campaign + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", status='" + status + '\'' +
                ", coach='" + coach + '\'' +
                '}';
    }
}