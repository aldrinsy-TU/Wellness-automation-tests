package steps.FormPageSteps;


import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.BoostModalPage;
import pageobjects.FormPages.TeamMeetingWithTLFormPage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class TeamMeetingWithTLFormsSteps {

    TeamMeetingWithTLFormPage teamMeetingWithTLFormPage;

    BoostModalPage boostModalPage;

    @Step("Click Submit btn")
    public void userClicksOnSubmitBtn() {
        teamMeetingWithTLFormPage.userClicksOnSubmitBtn();
    }

    public void waitPageToLoad() {
        teamMeetingWithTLFormPage.waitPageToLoad();
    }

    public void userClicksOnSiteBtn() {
        teamMeetingWithTLFormPage.userClicksOnSiteBtn();
    }


    public void userSelectSite(String site) {
        teamMeetingWithTLFormPage.userSelectSite(site);
    }

    public void userClicksOnCampaignTextBox() {
        teamMeetingWithTLFormPage.userClicksOnCampaignTextBox();
    }

    public void userSelectCampaign(String campaign) {
        teamMeetingWithTLFormPage.userSelectCampaign(campaign);
    }

    public void userInputNumberOfAttendees(String numOfAttendess) {
        teamMeetingWithTLFormPage.userInputNumberOfAttendees(numOfAttendess);
    }

    public int validateTeamMeetingWithTLFormFormSession() {
        List<WebElement> listoftickets =  teamMeetingWithTLFormPage.getDriver().findElements(By.xpath("//tr[@role='row']"));
        WebElement rowElement;
        String date = Serenity.sessionVariableCalled("GroupFormDateCreated");
        if(listoftickets.size() > 1){
            rowElement = listoftickets.get(1);
            for(int i = 1;i < listoftickets.size();i++){
                String sessionType = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[2]")).getText();
                String sessionNumber  = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[3]")).getText();
                String coacheeName = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[4]")).getText();
                String lastSessionDate = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[5]")).getText();
                String nextSessionDate = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[6]")).getText();

                if("N/A".equalsIgnoreCase(coacheeName.replace(",",""))
                        && "Team Meeting with TL Form".equalsIgnoreCase(sessionType)
                        && "N/A".equalsIgnoreCase(sessionNumber)
                        && date.equalsIgnoreCase(lastSessionDate)
                        && "N/A".equalsIgnoreCase(nextSessionDate))
                {
                    Assert.assertTrue("Request found",true);
                    return i;
                }
            }
            Assert.assertTrue("No Request found", false);
        }
        else
            Assert.assertTrue("No request found",false);

        return 0;
    }

    public void verifyTeamMeetingWithTLFormSessionFormModal() {
        String site = boostModalPage.getTextFromFormModalElement("Site");
        String campaign = boostModalPage.getTextFromFormModalElement("Campaign");
        String numberOfAttendessPresent = boostModalPage.getTextFromFormModalElement("Number of attendees present");
        String nameOfTL = boostModalPage.getTextFromFormModalElement("Name of TL");
        String textArea = boostModalPage.getTextFromFormModalElement("Please provide a description of identified concerns or opportunities for growth of the team by TL");
        if("Adventures Intelligence".equalsIgnoreCase(site)
                && "Sephora-Customer Support-Blended-TUT".equalsIgnoreCase(campaign)
                && "1".equalsIgnoreCase(numberOfAttendessPresent)
                && "test".equalsIgnoreCase(nameOfTL)
                && "test".equalsIgnoreCase(textArea)){
            Assert.assertTrue("Team Meeting with TL Form Validated",true);
        }
        else{
            Assert.assertTrue("Team Meeting with TL Form Invalid",false);
        }
    }

    public void userInputNameOfTL(String TLName) {
        teamMeetingWithTLFormPage.userInputNameOfTL(TLName);
    }

    public void userInputDescriptionOfIdentifiedConcernsOrOpportunitiesForGrowth(String testDesc) {
        teamMeetingWithTLFormPage.userInputDescriptionOfIdentifiedConcernsOrOpportunitiesForGrowth(testDesc);
    }

    public void verifyReponseIsSubmitted() {
        Assert.assertTrue("Verify response is submitted",teamMeetingWithTLFormPage.verifyReponseIsSubmitted());
        SimpleDateFormat dtFormat = new SimpleDateFormat("dd-MM-yy");
        dtFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String DateStr = dtFormat.format(new Date());
        Serenity.setSessionVariable("GroupFormDateCreated").to(DateStr);
    }

    public void userRefreshThePage(){
        boostModalPage.getDriver().navigate().refresh();
        boostModalPage.waitForAngularRequestsToFinish();
    }
}
