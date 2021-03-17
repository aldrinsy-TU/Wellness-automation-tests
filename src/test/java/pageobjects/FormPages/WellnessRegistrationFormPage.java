package pageobjects.FormPages;

import common.CommonFunctions;
import common.FormPageCommonMethods;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.BoostModalPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WellnessRegistrationFormPage extends FormPageCommonMethods {

    BoostModalPage boostModalPage;

    //Shift/schedule and RD
    @FindBy(xpath = "//input[@id='mat-input-0']")
    private WebElementFacade shiftAndScheduleTextBox;

    //Rate your desire to achieve an outcome/result at the end of the coaching session *
    @FindBy(xpath = "//input[@id='radiohorizontal_option_324']")
    private WebElementFacade desireRate5RadioBtn;

    //Which aspect of your life would you like to focus on?
    @FindBy(xpath = "//input[@id='checkbox_option_59']")
    private WebElementFacade selfCheckBox;

    //Please elaborate why you chose that "life aspect" or add a life aspect you wish to focus on that's not indicated in the list *
    @FindBy(xpath = "//textarea[@id='mat-input-1']")
    private WebElementFacade textAreaLifeAspect;

    @FindBy(xpath = "//button[@class='mat-button mat-raised-button mat-button-base']")
    private WebElementFacade readCoachAgreementBtn;

    //How did you learn about Life Coaching?
    @FindBy(xpath = "//input[@id='checkbox_option_40']")
    private WebElementFacade TaskUsAnnouncementCheckBox;

    public WellnessRegistrationFormPage() {
        super("Registration Form");
    }

    public boolean isRegistrationFormPageDisplayed(){
        return isFormPagePageDisplayed();
    }

    public void clickReadCoachAgreement(){
        clickBtnWithWait(find(By.xpath("//button[@class='mat-button mat-raised-button mat-button-base']")));
    }

    public void userInsertShiftSchedule(String text){
        shiftAndScheduleTextBox.sendKeys(text);
    }

    public void userClicksOnTaskUsAnnouncementCheckBox(){
//        clickBtnWithWait("//input[@id='checkbox_option_40'][@name='checkbox_29']");
//        find(By.xpath("//input[@id='checkbox_option_40']")).click();

        clickBtnWithWait(find(By.xpath("//input[@id='checkbox_option_40']")));

    }

    public void userClicksOnDesireRate5RadioBtn(){
        clickBtnWithWait(find(By.xpath("//label[@for='radiohorizontal_option_324']")));
    }

    public void userClicksOnSelfCheckBox(){
        clickBtnWithWait(find(By.xpath("//input[@id='checkbox_option_59']")));
    }

    public void userInserttextAreaLifeAspect(String text){
        textAreaLifeAspect.sendKeys(text);
    }

    public void userClicksOnReadCoachAgreementBtn(){
        clickBtnWithWait(find(By.xpath("//button[@class='mat-button mat-raised-button mat-button-base']")));
    }

    public void clickBtnWithWait(WebElement element){
        moveClickBtn(element);
    }


    public boolean validateSessionRequest(){
        return find(By.xpath("//mat-card-title[contains(text(),'Response submitted')]")).isVisible();
    }
}
