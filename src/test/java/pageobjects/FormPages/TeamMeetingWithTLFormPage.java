package pageobjects.FormPages;

import common.FormPageCommonMethods;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TeamMeetingWithTLFormPage extends FormPageCommonMethods {

    //Site comboBox
    @FindBy(xpath = "//mat-select[@id='siteId']")
    private WebElementFacade siteComboBox;

    //Campaign textBox
    @FindBy(xpath = "//input[@id='mat-input-1']")
    private WebElementFacade campaignTextBox;

    //Number of attendees present
    @FindBy(xpath = "//input[@id='mat-input-3']")
    private WebElementFacade attendeesNoTextBox;

    //Name of TL
    @FindBy(xpath = "//input[@id='mat-input-4']")
    private WebElementFacade nameOfTLTextBox;

    //Please provide a description of identified concerns or opportunities for growth of the team by TL
    @FindBy(xpath = "//textarea[@id='mat-input-5']")
    private WebElementFacade textarea;

    public TeamMeetingWithTLFormPage() {
        super("Team Meeting with TL Form");
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

    public void userInputNameOfTL(String tlName) {
        nameOfTLTextBox.sendKeys(tlName);
    }

    public void userInputDescriptionOfIdentifiedConcernsOrOpportunitiesForGrowth(String testDesc) {
        textarea.sendKeys(testDesc);
    }
}
