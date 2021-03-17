package stepdefinitions;

import common.CSVReader;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.LoginSteps;
import testdataobjects.EmployeeProfile;
import testdataobjects.wellnessLoginKeys;

import java.util.HashMap;

public class LoginStepDefinitions {
   HashMap<String, EmployeeProfile> employeeList;
    EmployeeProfile omuser;

    @Steps
    LoginSteps loginSteps;

    @Given("User access the Boost Home page")
    public void userAccessTheBoostHomePage() {
        loginSteps.theBoostHomePage();
    }

    @Given("User access the Wellness Home page")
    public void userAccessTheWellnessHomePage() {
        loginSteps.theWellnessHomePage();
    }

    @Given("User access the Time Warp Editor Home page US")
    public void userAccessTheTimeWarpEditorHomePageUS() {
        loginSteps.theTimeWarpEditorHomePageUS();
    }

    @When("^User login with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void loginWithAnd(String username, String password) throws Throwable {
        loginSteps.enterUsernameAndPassword(username, password);
        loginSteps.clickTheSignInBtn();
    }

    @When("A team mate user logs in with expired OTP")
    public void aTeamMateUserLogsInWithExpiredOTP() {
        omuser = loginSteps.UserLogsInWithExpiredOTP("TM");
    }


    @When("A NAM team mate user logs in")
    public void aNAMTeamMateUserLogsIn() {
        //The account still need to be added on the employee_profile.csv under NAMTM. Add NAMTM
        //omuser = loginSteps.UserLogsIn("NAMTM");
    }

    @When("A wellness employee user logs in")
    public void aWellnessEmployeeUserLogsIn() {
        loginSteps.wellnessUserLogsIn();
    }

    @When("A wellness {string} user logs in")
    public void userlogsinWel(String args) {
        loginSteps.wellnessUserLogsIn(args);
    }

    @Given("Test CSVWriter")
    public void testCSVWriter(){
        loginSteps.saveCSVDataForSessionRequest("New","N/A",true);
    }

    @Given("test update CSV {string} role {string} update-date: {string} ")
    public void updateCSVRequestStr(String status,String userRole, String updateDate){
        updateCSVRequest(status,userRole,updateDate.equalsIgnoreCase("true") ? true : false);
    }

    public void updateCSVRequest(String status,String userRole, Boolean updateDate){
        //Use this only if the request has coach, else use LoginSteps.saveCSVDataForSessionRequest()
        HashMap<String, wellnessLoginKeys> wellnessLoginKeysList = CSVReader.readAndLoadCSVDataForWellness();
        wellnessLoginKeys WellnessLoginKeys = wellnessLoginKeysList.get(userRole);
        loginSteps.saveCSVDataForSessionRequest(status,WellnessLoginKeys.getFullname(),updateDate);
    }


}
