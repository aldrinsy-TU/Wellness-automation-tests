package stepdefinitions;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import pageobjects.BoostModalPage;
import steps.BoostSteps;
import steps.LoginSteps;
import steps.FormPageSteps.WellnessAndResiliencyFormsSteps;

public class BoostStepDefinitions {

    BoostModalPage boostModalPage;

    @Steps
    BoostSteps boostSteps;

    @Steps
    LoginSteps loginSteps;

    @Steps
    WellnessAndResiliencyFormsSteps wellnessAndResiliencyFormsSteps;

    @Then("User clicks on Wellness")
    public void userClicksOnWellness() {
        boostSteps.userClicksOnWellness();
    }

    @Then("User clicks Session Request")
    public void userClickSessionRequest(){
        boostSteps.userClickRequestSession();
    }

    @And("User create Session Request")
    public void userCreateSessionRequest(){
        boostSteps.userClicksOnWellness();
        boostSteps.userClickRequestSession();
        boostSteps.userSelectYogiCoach();
        userInsertSampleDataToWellnessAndResilencyForm();
        loginSteps.saveCSVDataForSessionRequest("New","N/A",true);
    }

    @And("User insert sample data to wellness and resilency form")
    public void userInsertSampleDataToWellnessAndResilencyForm() {

            wellnessAndResiliencyFormsSteps.waitPageToLoad();
            wellnessAndResiliencyFormsSteps.userInsertShiftSchedule("Test");
            wellnessAndResiliencyFormsSteps.userSelectPreferredSessionDate();
            wellnessAndResiliencyFormsSteps.uuserSelectPreferredSessionTime();
            wellnessAndResiliencyFormsSteps.userClicksOnTaskUsAnnouncementCheckBox();
            wellnessAndResiliencyFormsSteps.userClicksOnDesireRate5RadioBtn();
            wellnessAndResiliencyFormsSteps.userClicksOnSelfCheckBox();
            wellnessAndResiliencyFormsSteps.userInserttextAreaLifeAspect("Test");
            wellnessAndResiliencyFormsSteps.userClicksOnReadCoachAgreementBtn();

            if(boostModalPage.iswellnessAndResiliencyFormsModalDisplayed()){
                boostModalPage.clickAgreeBtn();
                wellnessAndResiliencyFormsSteps.userClicksOnSubmitBtn();
                wellnessAndResiliencyFormsSteps.validateSessionRequest();
            }
    }
}
