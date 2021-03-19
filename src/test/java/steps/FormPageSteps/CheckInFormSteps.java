package steps.FormPageSteps;


import common.CommonFunctions;
import org.junit.Assert;
import pageobjects.FormPages.CheckInFormPage;

public class CheckInFormSteps extends CommonFunctions {

    CheckInFormPage checkInFormPage;

    public void insertAnswerToQ1(String Num) {
        checkInFormPage.insertAnswerToQ1(Num);
    }

    public void insertAnswerToQ2(String Num) {
        checkInFormPage.insertAnswerToQ2(Num);
    }

    public void selectNoToAll(){
        checkInFormPage.selectNoToAll();
    }

    public void clickAnswerToQ5(String args){
        checkInFormPage.clickAnswerToQ5(args);
    }

    public void clickSubmitBtn() {
        checkInFormPage.userClicksOnSubmitBtn();
    }

    public void waitForCheckInFormPageToLoad() {
        checkInFormPage.switchPageThenWaitToLoad();
    }

    public void validateCheckinBtnIsDisabled() {
        Assert.assertTrue("Validate if check-in button is disabled",checkInFormPage.validateCheckinBtnIsDisabled());
    }

    public void switchToThisPage(){
        checkInFormPage.switchToThisPage();
    }
}
