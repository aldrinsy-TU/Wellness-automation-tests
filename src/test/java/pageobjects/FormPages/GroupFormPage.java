package pageobjects.FormPages;

import common.CommonFunctions;
import common.FormPageCommonMethods;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GroupFormPage extends FormPageCommonMethods {

    //Site comboBox
    @FindBy(xpath = "//mat-select[@id='optionsitebycountry_168']")
    private WebElementFacade siteComboBox;

    //Campaign textBox
    @FindBy(xpath = "//mat-select[@id='optioncampaignbysite_169']")
    private WebElementFacade campaignTextBox;

    //Number of attendees present
    @FindBy(xpath = "//input[@id='mat-input-1']")
    private WebElementFacade attendeesNoTextBox;

    //Group category (Operations - Leadership)
    @FindBy(xpath = "//label[contains(text(),'Operations - Leadership')]")
    private WebElementFacade GCOperationsLeadershipRadioBtn;

    //Topic of group
    @FindBy(xpath = "//span[contains(text(),'Self-regulation')]")
    private WebElementFacade TOGSelfRegulation;

    //Was the group successful or challenging?
    @FindBy(xpath = "//span[contains(text(),'Successful/Learning outcomes met')]")
    private WebElementFacade evalSuccessfulNLearningOutcomesMet;

    public GroupFormPage() {
        super("Group Form");
    }

    public void userClicksOnSiteBtn() {
        moveClickBtn(siteComboBox);
    }

    public void userSelectSite(String site) {
        WebElement element = find(By.xpath("//span[contains(text(),'"+site+"')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public void userClicksOnCampaignTextBox() {
        moveClickBtn(campaignTextBox);
    }

    public void userSelectCampaign(String campaign) {
        WebElement element = find(By.xpath("//span[contains(text(),'"+campaign+"')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public void userInputAttendee(String numOfAttendess) {
        attendeesNoTextBox.sendKeys(numOfAttendess);
        waitForAngularRequestsToFinish();
    }

    public void userClicksGroupCategoryOperationsLeadership() {
        moveClickBtn(GCOperationsLeadershipRadioBtn);
        waitForAngularRequestsToFinish();
    }

    public void userClicksTopicOfGroupSelfRegulation() {
        moveClickBtn(TOGSelfRegulation);
        waitForAngularRequestsToFinish();
    }

    public void userClicksOnFirstRadionBtnInSuccessOrChallengingQuestion() {
        moveClickBtn(evalSuccessfulNLearningOutcomesMet);
        waitForAngularRequestsToFinish();
    }

    public void userSelectAttendee() {
        waitForAngularRequestsToFinish();
        List<WebElement> Elements = getDriver().findElements(By.xpath("//div[@mat-line][1]"));
        moveClickBtn(Elements.get(0));
    }

    public void userClicksOnGeographyComboBox() {
        moveClickBtn(find(By.xpath("//mat-select[@id='optionemployeecountry_167']")));
        waitForAngularRequestsToFinish();
    }

    public void userSelectGeography(String geography) {
        moveClickBtn(find(By.xpath("//span[contains(text(),'"+geography+"')]")));
    }

    public void userClicksOnDateTimePicker() {
        moveClickBtn(find(By.xpath("//mat-datepicker-toggle")));
        waitForAngularRequestsToFinish();
    }
    
    public void selectDateToday() {
        String DateStr;
        SimpleDateFormat dtFormat = new SimpleDateFormat("MMMMM d, yyyy");
        DateStr = dtFormat.format(new Date());

        moveClickBtn(find(By.xpath("//td[@aria-label='"+DateStr+"']")));
    }
}
