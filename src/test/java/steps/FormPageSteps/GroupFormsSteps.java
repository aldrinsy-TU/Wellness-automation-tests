package steps.FormPageSteps;


import common.CSVReader;
import common.ReadCSVUtil;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.BoostModalPage;
import pageobjects.FormPages.GroupFormPage;
import testdataobjects.ExpectedResults;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

public class GroupFormsSteps {

    GroupFormPage groupFormPage;

    BoostModalPage boostModalPage;

    @Step("Click Submit btn")
    public void userClicksOnSubmitBtn() {
        groupFormPage.userClicksOnSubmitBtn();
    }

    public void waitPageToLoad() {
        groupFormPage.waitPageToLoad();
    }

    public void userClicksOnSiteBtn() {
        groupFormPage.userClicksOnSiteBtn();
    }


    public void userSelectSite(String site) {
        groupFormPage.userSelectSite(site);
    }

    public void userClicksOnCampaignTextBox() {
        groupFormPage.userClicksOnCampaignTextBox();
    }

    public void userSelectCampaign(String campaign) {
        groupFormPage.userSelectCampaign(campaign);
    }

    public void userInputNumberOfAttendees(String numOfAttendess) {
        groupFormPage.userInputNumberOfAttendees(numOfAttendess);
    }

    public void userClicksGroupCategoryOperationsLeadership() {
        groupFormPage.userClicksGroupCategoryOperationsLeadership();
    }

    public void userClicksTopicOfGroupSelfRegulation() {
        groupFormPage.userClicksTopicOfGroupSelfRegulation();
    }

    public void userClicksOnFirstRadionBtnInSuccessOrChallengingQuestion() {
        groupFormPage.userClicksOnFirstRadionBtnInSuccessOrChallengingQuestion();
    }

    public void verifyReponseIsSubmitted() {
        Assert.assertTrue("Verify response is submitted",groupFormPage.verifyReponseIsSubmitted());
        SimpleDateFormat dtFormat = new SimpleDateFormat("dd-MM-yy");
        dtFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String DateStr = dtFormat.format(new Date());
        Serenity.setSessionVariable("GroupFormDateCreated").to(DateStr);
    }

    public int validateGroupFormSession() {
        List<WebElement> listoftickets =  groupFormPage.getDriver().findElements(By.xpath("//tr[@role='row']"));
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
                        && "Group Form".equalsIgnoreCase(sessionType)
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

    public void verifyGroupSessionFormModal() {
        List<String> column = ReadCSVUtil.getExpectedResult("Validate Group Form Session");

        String site = boostModalPage.getTextFromFormModalElement("Site");
        String campaign = boostModalPage.getTextFromFormModalElement("Campaign");
        String numberOfAttendessPresent = boostModalPage.getTextFromFormModalElement("Number of attendees present");
        String groupCategory = boostModalPage.getTextFromFormModalElement("Group category");
        String topicOfGroup = boostModalPage.getTextFromFormModalElement("Topic of group");
        String wasTheGroupSuccessfulOrChallenging = boostModalPage.getTextFromFormModalElement("Was the group successful or challenging?");
        if(column.get(0).equalsIgnoreCase(site)
                && column.get(1).equalsIgnoreCase(campaign)
                && column.get(2).equalsIgnoreCase(numberOfAttendessPresent)
                && column.get(3).equalsIgnoreCase(groupCategory)
                && column.get(4).equalsIgnoreCase(topicOfGroup)
                && column.get(5).equalsIgnoreCase(wasTheGroupSuccessfulOrChallenging)){
            Assert.assertTrue("Group Session Form Validated",true);
        }
        else{
            Assert.assertTrue("Group Session Form Invalid",false);
        }
    }

    public void userSelectAttendee() {
        groupFormPage.userSelectAttendee();
    }
}
