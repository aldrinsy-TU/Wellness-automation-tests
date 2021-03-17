package steps;

import common.CSVReader;
import common.DBHelper;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import pageobjects.LoginPage;
import testdataobjects.EmployeeProfile;
import testdataobjects.RecentRequestSession;
import testdataobjects.wellnessLoginKeys;

import java.util.HashMap;

public class LoginSteps {

    LoginPage loginpage;
    EmployeeProfile omuser;
    DBHelper dbHelper;
    HashMap<String, EmployeeProfile> employeeList;
    HashMap<String, wellnessLoginKeys> wellnessLoginKeysList;
    wellnessLoginKeys WellnessLoginKeys;

    @Step("Open the boost login page")
    public void theBoostHomePage() {
        setSessionVariableforWellnessLoginKey();
        loginpage.getDriver().manage().deleteAllCookies();
        loginpage.openUrl("https://app1-test.taskus.com/");
        loginpage.getDriver().navigate().refresh();
    }

    @Step("Open the Wellness login page")
    public void theWellnessHomePage() {
        setSessionVariableforWellnessLoginKey();
        loginpage.getDriver().manage().deleteAllCookies();
        loginpage.openUrl("https://app2-test.taskus.com/");
          loginpage.getDriver().navigate().refresh();
    }

    public void setSessionVariableforWellnessLoginKey(){
        wellnessLoginKeysList = Serenity.sessionVariableCalled("wellnessLoginKeysList");
        if(wellnessLoginKeysList == null){
            wellnessLoginKeysList = CSVReader.readAndLoadCSVDataForWellness();
            Serenity.setSessionVariable("wellnessLoginKeysList").to(wellnessLoginKeysList);
        }
    }

    @Step("Enter username and password")
    public void enterUsernameAndPassword(String username, String password) {
        loginpage.enterUserName(username);
        loginpage.enterPassword(password);
    }

    @Step("Click the Sign In button")
    public void clickTheSignInBtn() {
        loginpage.clickSignInBtn();
    }

    public EmployeeProfile UserLogsInWithExpiredOTP(String user){
        dbHelper = new DBHelper();

        employeeList =
                Serenity.sessionVariableCalled("employeeList");

        omuser = employeeList.get(user);
        Serenity.setSessionVariable("omuser").to(omuser);
        Serenity.setSessionVariable("EmpNo").to(omuser.getEmpno());

        /**OLD WAY of LOGGING IN ****/
        //enterUsernameAndPassword( omuser.getUsername(), omuser.getPassword());
        //clickTheSignInBtn();
        /***END***/

        loginpage.getDriver().manage().deleteAllCookies();
        loginpage.getDriver().navigate().refresh();

        enterEmailAndClickBtn(omuser.getEmail());
        String otp = dbHelper.getExpiredOTPGivenEID(omuser.getEmpno());
        enterOtpAndClickBtn(otp);
        return omuser;
    }

    @Step("Open the Time Warp Editor login page US")
    public void theTimeWarpEditorHomePageUS() {
        employeeList =
                Serenity.sessionVariableCalled("employeeList");
        if(employeeList==null){
            employeeList = CSVReader.readAndLoadCSVData();
            Serenity.setSessionVariable("employeeList").to(employeeList);
        }
        loginpage.getDriver().manage().deleteAllCookies();
        //TW EDIT US LINK
        loginpage.openUrl("https://phrzlwp02dev01:30702/editv2/#/");
        loginpage.getDriver().navigate().refresh();
    }

    @Step("Enter email and click button")
    public void enterEmailAndClickBtn(String email) {
        loginpage.clickGenerateOtpLoginBtn();
        loginpage.enterEmail(email);
        loginpage.clickGenerateOtpLoginBtn();
    }

    @Step("Enter test login creds and click button")
    public void entertestlogincredsAndClickBtn(String loginCreds) {
        loginpage.enterTestLogin(loginCreds);
        loginpage.clicktestLoginBtn();
    }

    @Step("Enter test login creds and click button")
    public void entertestlogincredsAndClickBtn(String loginCreds,String role) {
        loginpage.enterWellnessTestLogin(loginCreds,role);
        loginpage.clicktestLoginBtn(role);
    }

    @Step("Enter Otp and click button")
    public void enterOtpAndClickBtn(String otp) {
        loginpage.enterOtp(otp);
        loginpage.clickOtpLoginBtn();
//        EmployeeProfile omuser = Serenity.sessionVariableCalled("omuser");
//        userAuthenticate = loginpage.getUserAuthenticate(omuser.getUsername(),omuser.getPassword());
//        Serenity.setSessionVariable("userAuthenticate").to(userAuthenticate);
    }

    public void wellnessUserLogsIn() {
        setSessionVariableforWellnessLoginKey();

        WellnessLoginKeys = wellnessLoginKeysList.get("Boost");
        Serenity.setSessionVariable("WellnessLoginKeys").to(WellnessLoginKeys);
        loginpage.getDriver().manage().deleteAllCookies();
        loginpage.getDriver().navigate().refresh();

        entertestlogincredsAndClickBtn(WellnessLoginKeys.getLoginKey());
    }

    public void wellnessUserLogsIn(String userRole) {
        setSessionVariableforWellnessLoginKey();

        WellnessLoginKeys = wellnessLoginKeysList.get(userRole);
        Serenity.setSessionVariable("WellnessLoginKeys").to(WellnessLoginKeys);
        loginpage.getDriver().manage().deleteAllCookies();
        loginpage.getDriver().navigate().refresh();

        entertestlogincredsAndClickBtn(WellnessLoginKeys.getLoginKey(),WellnessLoginKeys.getEmployeeRole());
    }

    public void saveCSVDataForSessionRequest(String status,String coach, Boolean updateDate) {
        CSVReader.saveCSVDataForSessionRequest(status, coach, updateDate);
    }

    public RecentRequestSession readCSVDataForSessionRequest(){
        return CSVReader.readCSVDataForSessionRequest();
    }
}
