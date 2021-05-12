package steps.FormPageSteps;


import common.ReadCSVUtil;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.BoostModalPage;
import pageobjects.FormPages.LeadershipAndDepartmentalTrainingLogFormPage;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class LeadershipAndDepartmentalTrainingLogFormsSteps {

    LeadershipAndDepartmentalTrainingLogFormPage leadershipAndDepartmentalTrainingLogFormPage;

    BoostModalPage boostModalPage;

    @Step("Click Submit btn")
    public void userClicksOnSubmitBtn() {
        leadershipAndDepartmentalTrainingLogFormPage.userClicksOnSubmitBtn();
    }

    public void waitPageToLoad() {
        leadershipAndDepartmentalTrainingLogFormPage.waitPageToLoad();
    }

    public void userClicksOnSiteBtn() {
        leadershipAndDepartmentalTrainingLogFormPage.userClicksOnSiteBtn();
    }

    public void userSelectPreferredSessionDate() {
        leadershipAndDepartmentalTrainingLogFormPage.clickPreferredSessionDateDTPicker();
        leadershipAndDepartmentalTrainingLogFormPage.selectDateToday();
    }

    public void userSelectGeography(String geography) {
        leadershipAndDepartmentalTrainingLogFormPage.clickComboBox("//mat-select[@id='optionemployeecountry_142']");
        leadershipAndDepartmentalTrainingLogFormPage.selectOptionForComboBox(geography);
    }

    public void userSelectSite(String site) {
        leadershipAndDepartmentalTrainingLogFormPage.clickComboBox("//mat-select[@id='optionsitebycountry_143']");
        leadershipAndDepartmentalTrainingLogFormPage.selectOptionForComboBox(site);
    }

    public void userSelectPresentation(String presentation) {
        leadershipAndDepartmentalTrainingLogFormPage.clickComboBox("//mat-select[@id='option_145']");
        leadershipAndDepartmentalTrainingLogFormPage.selectOptionForComboBox(presentation);
    }

    public void userSelectDuration(String duration) {
        leadershipAndDepartmentalTrainingLogFormPage.clickComboBox("//mat-select[@id='option_146']");
        leadershipAndDepartmentalTrainingLogFormPage.selectOptionForComboBox(duration);
    }

    public void insertTextInSubjectTextfield(String sampleText) {
        leadershipAndDepartmentalTrainingLogFormPage.insertTextInSubjectTextfield(sampleText);
    }

    public void verifyReponseIsSubmitted() {
        Assert.assertTrue("Verify response is submitted", leadershipAndDepartmentalTrainingLogFormPage.verifyReponseIsSubmitted());
        SimpleDateFormat dtFormat = new SimpleDateFormat("dd-MM-yy");
        dtFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String DateStr = dtFormat.format(new Date());
        Serenity.setSessionVariable("GroupFormDateCreated").to(DateStr);
    }

    public int validateIndividualSessionFormSession() {
        List<WebElement> listoftickets =  leadershipAndDepartmentalTrainingLogFormPage.getDriver().findElements(By.xpath("//tr[@role='row']"));
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
                        && "Leadership/Departmental Training Log".equalsIgnoreCase(sessionType)
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

    public void verifyFormSessionModal() throws ParseException {
        List<String> column = ReadCSVUtil.getExpectedResult("Validate Leadership and Departmental Training Log Form Session");

        String Date = Serenity.sessionVariableCalled("GroupFormDateCreated");
        String date = boostModalPage.getTextFromFormModalElement("Date");
        String geography = boostModalPage.getTextFromFormModalElement("Geography");
        String site = boostModalPage.getTextFromFormModalElement("Site");
        String presentation = boostModalPage.getTextFromFormModalElement("Presentation");
        String duration = boostModalPage.getTextFromFormModalElement("Duration");
        String subject = boostModalPage.getTextFromFormModalElement("Subject");
        Date date1=new SimpleDateFormat("dd-MM-yy").parse(Date);
        SimpleDateFormat dtFormat = new SimpleDateFormat("M/d/yyyy");
        Date = dtFormat.format(date1);

        if(column.get(0).equalsIgnoreCase(site)
                && Date.equalsIgnoreCase(date)
                && column.get(1).equalsIgnoreCase(geography)
                && column.get(2).equalsIgnoreCase(presentation.replace(",",""))
                && column.get(3).equalsIgnoreCase(duration)
                && column.get(4).equalsIgnoreCase(subject)){
            Assert.assertTrue("Form Validated",true);
        }
        else{
            Assert.assertTrue("Form Invalid",false);
        }
    }
}
