package steps.FormPageSteps;


import common.ReadCSVUtil;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.BoostModalPage;
import pageobjects.FormPages.DebriefAndStandUpSkillFormPage;
import pageobjects.FormPages.LeadershipAndDepartmentalTrainingLogFormPage;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DebriefAndStandUpSkillFormsSteps {

    DebriefAndStandUpSkillFormPage debriefAndStandUpSkillFormPage;

    BoostModalPage boostModalPage;

    @Step("Click Submit btn")
    public void userClicksOnSubmitBtn() {
        debriefAndStandUpSkillFormPage.userClicksOnSubmitBtn();
    }

    public void waitPageToLoad() {
        debriefAndStandUpSkillFormPage.waitPageToLoad();
    }

    public void userClicksOnSiteBtn() {
        debriefAndStandUpSkillFormPage.userClicksOnSiteBtn();
    }

    public void userSelectPreferredSessionDate() {
        debriefAndStandUpSkillFormPage.clickPreferredSessionDateDTPicker();
        debriefAndStandUpSkillFormPage.selectDateToday();
    }

    public void userSelectGeography(String geography) {
        debriefAndStandUpSkillFormPage.clickComboBox("//mat-select[@id='optionemployeecountry_135']");
        debriefAndStandUpSkillFormPage.selectOptionForComboBox(geography);
    }

    public void userSelectSite(String site) {
        debriefAndStandUpSkillFormPage.clickComboBox("//mat-select[@id='optionsitebycountry_136']");
        debriefAndStandUpSkillFormPage.selectOptionForComboBox(site);
    }

    public void userSelectDuration() {
        debriefAndStandUpSkillFormPage.clickComboBox("//mat-select[@id='option_138']");
        debriefAndStandUpSkillFormPage.clickComboBox("//span[contains(text(),'15 min.')]");
    }

    public void verifyReponseIsSubmitted() {
        Assert.assertTrue("Verify response is submitted", debriefAndStandUpSkillFormPage.verifyReponseIsSubmitted());
        SimpleDateFormat dtFormat = new SimpleDateFormat("dd-MM-yy");
        dtFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String DateStr = dtFormat.format(new Date());
        Serenity.setSessionVariable("GroupFormDateCreated").to(DateStr);
    }

    public int validateIndividualSessionFormSession() {
        List<WebElement> listoftickets =  debriefAndStandUpSkillFormPage.getDriver().findElements(By.xpath("//tr[@role='row']"));
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
                        && "Debrief/Stand-Up Skill".equalsIgnoreCase(sessionType)
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

    public void verifyFormSessionModal(){
        List<String> column = ReadCSVUtil.getExpectedResult("Validate Debrief and Stand-Up Skill Log Form Session");

        String geography = boostModalPage.getTextFromFormModalElement("Geography");
        String site = boostModalPage.getTextFromFormModalElement("Site");
        String duration = boostModalPage.getTextFromFormModalElement("Duration");
        String subject = boostModalPage.getTextFromFormModalElement("Was the group successful or challenging?");

        if(column.get(0).equalsIgnoreCase(site)
                && column.get(1).equalsIgnoreCase(geography)
                && column.get(2).equalsIgnoreCase(duration)
                && column.get(3).equalsIgnoreCase(subject)){
            Assert.assertTrue("Form Validated",true);
        }
        else{
            Assert.assertTrue("Form Invalid",false);
        }
    }

    public void WastheGroupSuccessfulOrChallenging(String outcome) {
        debriefAndStandUpSkillFormPage.clickComboBox("//mat-select[@id='option_139']");
        debriefAndStandUpSkillFormPage.selectOptionForComboBox(outcome);
    }
}
