package steps.FormPageSteps;


import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.BoostModalPage;
import pageobjects.FormPages.IndividualSessionFormPage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class YogiIndividualSessionFormsSteps {

    IndividualSessionFormPage individualSessionFormPage;

    BoostModalPage boostModalPage;

    @Step("Click Submit btn")
    public void userClicksOnSubmitBtn() {
        individualSessionFormPage.userClicksOnSubmitBtn();
    }

    public void waitPageToLoad() {
        individualSessionFormPage.waitPageToLoad();
    }

    public void userClicksOnSiteBtn() {
        individualSessionFormPage.userClicksOnSiteBtn();
    }


    public void userSelectSite(String site) {
        individualSessionFormPage.userSelectSite(site);
    }

    public void userClicksOnCampaignTextBox() {
        individualSessionFormPage.userClicksOnCampaignTextBox();
    }

    public void userSelectCampaign(String campaign) {
        individualSessionFormPage.userSelectCampaign(campaign);
    }

    public int validateIndividualSessionFormSession() {
        List<WebElement> listoftickets =  individualSessionFormPage.getDriver().findElements(By.xpath("//tr[@role='row']"));
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
                        && "Individual Session Form".equalsIgnoreCase(sessionType)
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

    public void verifyIndividualSessionFormSessionFormModal() {
        String site = boostModalPage.getTextFromFormModalElement("Site");
        String campaign = boostModalPage.getTextFromFormModalElement("Campaign");
        String sessionNumber = boostModalPage.getTextFromFormModalElement("Session number");
        String aspect = boostModalPage.getTextFromFormModalElement("Aspect");
        String personal = boostModalPage.getTextFromFormModalElement("Personal");
        if("Adventures Intelligence".equalsIgnoreCase(site)
                && "Sephora-Customer Support-Blended-TUT".equalsIgnoreCase(campaign)
                && "1st".equalsIgnoreCase(sessionNumber)
                && "Personal".equalsIgnoreCase(aspect)
                && "Relationship(s) problems".equalsIgnoreCase(personal)){
            Assert.assertTrue("Individual Session Form Validated",true);
        }
        else{
            Assert.assertTrue("Individual Session Form Invalid",false);
        }
    }

    public void verifyReponseIsSubmitted() {
        Assert.assertTrue("Verify response is submitted", individualSessionFormPage.verifyReponseIsSubmitted());
        SimpleDateFormat dtFormat = new SimpleDateFormat("dd-MM-yy");
        dtFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String DateStr = dtFormat.format(new Date());
        Serenity.setSessionVariable("GroupFormDateCreated").to(DateStr);
    }

    public void userClicksOnSessionNumberComboBox() {
        individualSessionFormPage.userClicksOnSessionNumberComboBox();
    }

    public void userSelect1stOnSessionNumberComboBox() {
        individualSessionFormPage.userSelect1stOnSessionNumberComboBox();
    }

    public void userClicksOnAspectComboBox() {
        individualSessionFormPage.userClicksOnAspectComboBox();
    }

    public void userSelectPersonalOnAspectComboBox() {
        individualSessionFormPage.userSelectPersonalOnAspectComboBox();
    }

    public void userClicksRelationshipProblem() {
        individualSessionFormPage.userClicksRelationshipProblem();
    }
}
