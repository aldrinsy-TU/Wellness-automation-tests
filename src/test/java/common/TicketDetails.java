package common;

public class TicketDetails
{
    String TicketNumber;

    @Override
    public String toString() {
        return "TicketDetails{" +
                "TicketNumber='" + TicketNumber + '\'' +
                ", RequestorName='" + RequestorName + '\'' +
                ", Campaign='" + Campaign + '\'' +
                ", LOB='" + LOB + '\'' +
                ", Status='" + Status + '\'' +
                ", Date='" + Date + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }

    String RequestorName;
    String Campaign;
    String LOB;
    String Status;
    String Date;
    String updateDate;

    public TicketDetails(){
    }

    public TicketDetails(String ticketNumber, String requestorName, String campaign, String LOB, String status, String date, String updateDate) {
        TicketNumber = ticketNumber;
        RequestorName = requestorName;
        Campaign = campaign;
        this.LOB = LOB;
        Status = status;
        Date = date;
        this.updateDate = updateDate;
    }

    public String getTicketNumber() {
        return TicketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        TicketNumber = ticketNumber;
    }

    public String getRequestorName() {
        return RequestorName;
    }

    public void setRequestorName(String requestorName) {
        RequestorName = requestorName;
    }

    public String getCampaign() {
        return Campaign;
    }

    public void setCampaign(String campaign) {
        Campaign = campaign;
    }

    public String getLOB() {
        return LOB;
    }

    public void setLOB(String LOB) {
        this.LOB = LOB;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }


}
