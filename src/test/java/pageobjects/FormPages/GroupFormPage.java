package pageobjects.FormPages;

import common.CommonFunctions;
import common.FormPageCommonMethods;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GroupFormPage extends FormPageCommonMethods {

    //Site comboBox
    @FindBy(xpath = "//mat-select[@id='siteId']")
    private WebElementFacade siteComboBox;

//    @FindBy(xpath = "//span[contains(text(),'Adventures Intelligence')]")
//    private WebElementFacade getSiteComboBoxAndventuresIntelligence;

    //Campaign textBox
    @FindBy(xpath = "//input[@id='mat-input-1']")
    private WebElementFacade campaignTextBox;

    //Number of attendees present
    @FindBy(xpath = "//input[@id='mat-input-3']")
    private WebElementFacade attendeesNoTextBox;

    //Group category (Operations - Leadership)
    @FindBy(xpath = "//label[contains(text(),'Operations - Leadership')]")
    private WebElementFacade GCOperationsLeadershipRadioBtn;

    //Topic of group
    @FindBy(xpath = "//label[contains(text(),'Self-regulation')]")
    private WebElementFacade TOGSelfRegulation;

    //Was the group successful or challenging?
    @FindBy(xpath = "//label[contains(text(),'Successful/Learning outcomes met')]")
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

    public void userInputNumberOfAttendees(String numOfAttendess) {
        attendeesNoTextBox.sendKeys(numOfAttendess);
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
}
