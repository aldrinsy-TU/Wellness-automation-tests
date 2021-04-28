package steps.FormPageSteps;


import net.thucydides.core.annotations.Step;
import pageobjects.FormPages.BoostSatisfactionSurveyFormPage;

public class BoostSatisfactionSurveyFormsSteps {

    BoostSatisfactionSurveyFormPage boostSatisfactionSurveyFormPage;

    @Step("Check if Form is displayed")
    public boolean checkIfFormIsDisplayed() {
        return boostSatisfactionSurveyFormPage.isSatisfactionSurveyFormPageDisplayed();
    }

    public void waitPageToLoad(){
        boostSatisfactionSurveyFormPage.switchPageThenWaitToLoad();
    }


    public void clickSatisfactionOnSurveyQuestion(String Satisfactory,int QuestionNum) {
        boostSatisfactionSurveyFormPage.clickSatisfactionOnSurveyQuestion(Satisfactory,QuestionNum);

    }

    public void clickSubmitBtn() {
        boostSatisfactionSurveyFormPage.userClicksOnSubmitBtn();
    }

    public void userFillsTextArea() {
        boostSatisfactionSurveyFormPage.userFillsTextArea();
    }

    public void clickStatisfactionOnSurveyQuestion() {
        boostSatisfactionSurveyFormPage.clickStatisfactionOnSurveyQuestion();
    }
}
