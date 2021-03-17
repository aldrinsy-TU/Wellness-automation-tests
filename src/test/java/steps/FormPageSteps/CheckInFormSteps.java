package steps.FormPageSteps;


import common.CommonFunctions;
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
}
