package pageobjects.FormPages;

import common.CommonFunctions;
import common.FormPageCommonMethods;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.BoostModalPage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WellnessIndividualSessionFormPage extends FormPageCommonMethods {
    BoostModalPage boostModalPage;

    //Session Number
    @FindBy(xpath = "//mat-select[@id='option_86']")
    private WebElementFacade sessionNumberComboBox;

    //Aspect
    @FindBy(xpath = "//mat-select[@id='option_90']")
    private WebElementFacade aspectComboBox;

    //Action Item
    @FindBy(xpath = "//input[@type='text']")
    private WebElementFacade actionItemTextBox;

    //Action Item due date
    @FindBy(xpath = "//input[@ng-reflect-name='date_80']")
    private WebElementFacade actionItemDatePicker;

    public WellnessIndividualSessionFormPage() {
        super("Individual Session Form");
    }

    public boolean validateSessionRequest(){
       return find(By.xpath("//mat-card-title[contains(text(),'Response submitted')]")).isVisible();
    }

    public void clickSessionNumberComboBox() {
        moveClickBtn(sessionNumberComboBox);
        waitForAngularRequestsToFinish();
    }

    public void clickAspectComboBox() {
        moveClickBtn(aspectComboBox);
        waitForAngularRequestsToFinish();
    }

    public void selectItemInSessionNumberComboBox(String selection) {
        waitForAngularRequestsToFinish();
        WebElement element = getDriver().findElement(By.xpath("//span[contains(text(),'"+selection+"')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public void selectItemAspectComboBox(String selection) {
        waitForAngularRequestsToFinish();
        WebElement element = getDriver().findElement(By.xpath("//span[contains(text(),'"+selection+"')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();

        while (!find(By.xpath("//mat-select[@id='option_90']")).getText().equalsIgnoreCase(selection)){
            WebElement element1 = getDriver().findElement(By.xpath("//span[contains(text(),'"+selection+"')]"));
            JavascriptExecutor exec = (JavascriptExecutor) this.getDriver();
            exec.executeScript("arguments[0].click();", element1);
        }
        waitForAngularRequestsToFinish();
    }

    public void insertTextToActionItems(String test) {
        actionItemTextBox.sendKeys(test);
        waitForAngularRequestsToFinish();
    }

    public void insertTextToActionItemsDueDate(String s) {
        actionItemDatePicker.sendKeys(s);
        waitForAngularRequestsToFinish();
    }

    public void clickEmergentConcernsCheckBox(String s) {
        WebElement element = getDriver().findElement(By.xpath("//label[contains(text(),'"+s+"')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }
}
