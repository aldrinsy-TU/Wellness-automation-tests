package pageobjects.FormPages;

import common.FormPageCommonMethods;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class IndividualSessionFormPage extends FormPageCommonMethods {

    //Site comboBox
    @FindBy(xpath = "//mat-select[@id='siteId']")
    private WebElementFacade siteComboBox;

    //Campaign textBox
    @FindBy(xpath = "//input[@id='mat-input-1']")
    private WebElementFacade campaignTextBox;

    //Session Number
    @FindBy(xpath = "//mat-select[@id='option_106']")
    private WebElementFacade sessionNumberComboBox;

    //Aspect
    @FindBy(xpath = "//mat-select[@id='option_107']")
    private WebElementFacade aspectComboBox;

    public IndividualSessionFormPage() {
        super("Individual Session Form");
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

    public void userClicksOnSessionNumberComboBox() {
        moveClickBtn(sessionNumberComboBox);
        waitForAngularRequestsToFinish();
    }

    public void userSelect1stOnSessionNumberComboBox() {
        WebElement element = find(By.xpath("//span[contains(text(),'1st')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public void userClicksOnAspectComboBox() {
        moveClickBtn(aspectComboBox);
        waitForAngularRequestsToFinish();
    }

    public void userSelectPersonalOnAspectComboBox() {
        WebElement element = find(By.xpath("//span[contains(text(),'Personal')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public void userClicksRelationshipProblem() {
        WebElement element = find(By.xpath("//label[contains(text(),'Relationship(s) problems')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }
}
