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

public class CheckInFormPage extends FormPageCommonMethods {

    public CheckInFormPage() {
        super("Check-in Form");
    }

    public boolean validateSessionRequest(){
       return find(By.xpath("//mat-card-title[contains(text(),'Response submitted')]")).isVisible();
    }

    public void insertAnswerToQ1(String Num) {
        find(By.xpath("//input[@id='mat-input-0']")).sendKeys(Num);
    }

    public void insertAnswerToQ2(String Num) {
        find(By.xpath("//input[@id='mat-input-1']")).sendKeys(Num);
    }

    public void selectNoToAll(){
        List<WebElement> elements = getDriver().findElements(By.xpath("//label[contains(text(),'No')]"));
        for(int i = 0;i < elements.size(); i++){
            moveClickBtn(elements.get(i));
            waitForAngularRequestsToFinish();
        }
    }

    public void clickAnswerToQ5(String args){
        WebElement element = getDriver().findElement(By.xpath("//label[contains(text(),'"+args+"')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public boolean validateCheckinBtnIsDisabled() {
        WebElementFacade element = find(By.xpath("//button[contains(text(),'Check-In')]"));
        return element.isDisabled();
    }

    public void clickNoRadionBtn() {
        List<WebElement> ListElement = getDriver().findElements(By.xpath("//span[contains(text(),'No')]"));
        for(WebElement element : ListElement){
            moveClickBtn(element);
        }
    }

    public void clickHighlySatisfiedRadioBtn() {
        List<WebElement> ListElement = getDriver().findElements(By.xpath("//span[contains(text(),'Highly Satisfied')]"));
        for(WebElement element : ListElement){
            moveClickBtn(element);
        }
    }

    public void userFillsTextArea() {
        List<WebElement> elements = getDriver().findElements(By.xpath("//textarea"));
        for(WebElement WE : elements){
            WE.sendKeys("Test");
        }
    }
}
