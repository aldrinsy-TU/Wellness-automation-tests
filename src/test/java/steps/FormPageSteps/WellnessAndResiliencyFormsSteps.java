package steps.FormPageSteps;


import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import pageobjects.BoostModalPage;
import pageobjects.BoostPage;
import pageobjects.FormPages.WellnessRegistrationFormPage;

public class WellnessAndResiliencyFormsSteps {

    BoostPage boostPage;

    BoostModalPage boostModalPage;

    WellnessRegistrationFormPage wellnessRegistrationFormPage;

    @Step("User Shift/schedule and RD ")
    public void userInsertShiftSchedule(String text) {
        wellnessRegistrationFormPage.userInsertShiftSchedule(text);
    }

    @Step("How did you learn about Life Coaching?")
    public void userClicksOnTaskUsAnnouncementCheckBox() {
        wellnessRegistrationFormPage.userClicksOnTaskUsAnnouncementCheckBox();
    }

    @Step("Rate your desire to achieve an outcome/result at the end of the coaching session?")
    public void userClicksOnDesireRate5RadioBtn() {
        wellnessRegistrationFormPage.userClicksOnDesireRate5RadioBtn();
    }

    @Step("Which aspect of your life would you like to focus on?")
    public void userClicksOnSelfCheckBox() {
        wellnessRegistrationFormPage.userClicksOnSelfCheckBox();
    }

    @Step("Please elaborate why you chose that \"life aspect\" or add a life aspect you wish to focus on that's not indicated in the list")
    public void userInserttextAreaLifeAspect(String text) {
        wellnessRegistrationFormPage.userInserttextAreaLifeAspect(text);
    }

    @Step("Read coach Agreement")
    public void userClicksOnReadCoachAgreementBtn() {
        wellnessRegistrationFormPage.userClicksOnReadCoachAgreementBtn();
    }

    @Step("Click Submit btn")
    public void userClicksOnSubmitBtn() {
        wellnessRegistrationFormPage.userClicksOnSubmitBtn();
    }

    @Step("Check if Form is displayed")
    public boolean checkIfFormIsDisplayed() {
        return wellnessRegistrationFormPage.isRegistrationFormPageDisplayed();
    }

    public void waitPageToLoad(){
        wellnessRegistrationFormPage.switchPageThenWaitToLoad();
    }

    @Step("Validate that the Session Request has been submitted.")
    public void validateSessionRequest(){
        Assert.assertTrue(wellnessRegistrationFormPage.validateSessionRequest());
    }
}
