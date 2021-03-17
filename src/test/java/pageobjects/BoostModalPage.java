package pageobjects;

import common.CommonFunctions;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class BoostModalPage extends CommonFunctions {
    //Start : Wellness and Resiliency Forms
    @FindBy(xpath = "//h2[@id='mat-dialog-title-0']")
    private WebElementFacade wellnessAndResiliencyFormsModal;

    @FindBy(xpath = "//button[@class='app-button-color mat-button mat-raised-button mat-button-base']")
    private WebElementFacade agreeBtn;

    //End : Wellness and Resiliency Forms

    //Start : Type of care modal
    @FindBy(xpath = "//span[contains(text(),'Select type of care')]")
    private WebElementFacade careTypeModal;

    @FindBy(xpath = "//button[contains(text(),'Wellness Coaching with a Yogi')]")
    private WebElementFacade coachYogiBtn;
    //End : Type of care modal

    //Start : Assign Coach
    @FindBy(xpath = "//h1[contains(text(),'Assign Coach')]")
    private WebElementFacade assignCoachModal;

    @FindBy(xpath = "//input[@aria-label='Yogis']")
    private WebElementFacade assignCoachModalTextField;

    @FindBy(xpath = "//span[@class='mat-button-wrapper'][contains(text(),'Assign')]")
    private WebElement assignCoachBtn;

    private WebElement coachSuggestName;
    //End : Assign Coach

    public boolean isCareTypeModalDisplayed(){
        return careTypeModal.isDisplayed();
    }

    public boolean iswellnessAndResiliencyFormsModalDisplayed(){
        return wellnessAndResiliencyFormsModal.isVisible();
    }

    public boolean isAssignCoachModalDisplayed(){
        return assignCoachModal.isVisible();
    }

    public boolean isAssignCoachModalCurrentlyVisible(){
        return assignCoachModal.isCurrentlyVisible();
    }

    public void clickCoachYogiBtn(){
        if(!coachYogiBtn.isClickable()){
            withTimeoutOf(2, TimeUnit.MINUTES).waitFor(ExpectedConditions.elementToBeClickable(coachYogiBtn));
        }
        coachYogiBtn.click();
        waitForAngularRequestsToFinish();
    }

    public void clickAgreeBtn(){
        if(!find(By.xpath("//button[@class='app-button-color mat-button mat-raised-button mat-button-base']")).isClickable()){
            WebElement element = find(By.xpath("//button[@class='app-button-color mat-button mat-raised-button mat-button-base']"));
            withTimeoutOf(2, TimeUnit.MINUTES).waitFor(ExpectedConditions.elementToBeClickable(element));
        }
        find(By.xpath("//button[@class='app-button-color mat-button mat-raised-button mat-button-base']")).click();
        waitForAngularRequestsToFinish();
    }

    public void typeCoachNameInModal(String coachName){
        assignCoachModalTextField.sendKeys(coachName);
    }

    public void clickAssignCoachBtn(){
        moveClickBtn(assignCoachBtn);
        waitForAngularRequestsToFinish();
    }

    public void clickSuggestedCoachName(String coachName){
        coachSuggestName = getDriver().findElement(By.xpath("//mat-option[@ng-reflect-value='"+coachName+"']"));
        moveClickBtn(coachSuggestName);
        waitForAngularRequestsToFinish();
    }

    public void clickStatusComboOnModal() {
        WebElement element = getDriver().findElement(By.xpath("//mat-select[@id='status']"));
//        moveClickBtn(element);
        JavascriptExecutor exec = (JavascriptExecutor) this.getDriver();
        exec.executeScript("arguments[0].click();", element);
        waitForAngularRequestsToFinish();
    }

    public void selectStatusOnModal(String args) {
        WebElement element = getDriver().findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'"+args+"')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public void clickUpdateStatusModalButton() {
        WebElement element = getDriver().findElement(By.xpath("//span[contains(text(),'Update')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public String getTextFromFormModalElement(String Label) {
        return getDriver().findElement(org.openqa.selenium.By.xpath("//label[contains(text(),'"+Label+"')]//following::p[1]")).getText();
    }
}
