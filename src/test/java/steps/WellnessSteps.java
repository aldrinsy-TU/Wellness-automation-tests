package steps;


import common.CSVReader;
import common.CommonFunctions;
import common.ReadCSVUtil;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.BoostModalPage;
import pageobjects.BoostPage;
import pageobjects.WellnessPage;
import testdataobjects.ExpectedResults;
import testdataobjects.RecentIndividualSession;
import testdataobjects.RecentRequestSession;
import testdataobjects.wellnessLoginKeys;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class WellnessSteps extends CommonFunctions {

    WellnessPage wellnessPage;

    BoostModalPage boostModalPage;

    LoginSteps loginSteps;

    BoostPage boostPage;

    String firstRowStatusValue;

    @Step("Click requests link session")
    public void userClickOnRequestsLink(){
        wellnessPage.clickRequestsLink();
    }

    @Step("Clicks on the requests status filter")
    public void userClicksOnRequestFilterStatus(){
        wellnessPage.clickOnRequestFilterStatus();
    }

    @Step("Clicks on the requests campaign filter")
    public void userClicksOnRequestFilterCampaign(){
        wellnessPage.clickOnRequestFilterCampaign();
    }

    @Step("Clicks on the requests site filter")
    public void userClicksOnRequestFilterSite(){
        wellnessPage.clickOnRequestFilterSite();
    }

    @Step("Selects filter in combo box")
    public void selectfilterComboBox(String args){
        wellnessPage.selectFilter(args);
    }

    @Step("Select filter in status combo box")
    public void selectfilterStatusComboBox(String args){
        wellnessPage.selectStatusFilter(args);
    }

    @Step("Validate newly added record")
    public int validateRequestRecord(){
        List<WebElement> listoftickets =  wellnessPage.getTicketRow();
        WebElement rowElement;
        RecentRequestSession recentRequestSession = CSVReader.readCSVDataForSessionRequest();
        if(listoftickets.size() > 1){
            rowElement = listoftickets.get(1);
            for(int i = 1;i < listoftickets.size();i++){
                String coachee = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[3]")).getText();
                String site  = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[4]")).getText();
                String campaign = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[5]")).getText();
                String regDate = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[6]")).getText();
                String status = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[7]")).getText();
                String coach = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[8]")).getText();

                if(recentRequestSession.getCoacheeName().equalsIgnoreCase(coachee.replace(",",""))
                    && recentRequestSession.getSite().equalsIgnoreCase(site)
                    && recentRequestSession.getCampaign().equalsIgnoreCase(campaign)
                    && recentRequestSession.getRegistrationDate().equalsIgnoreCase(regDate)
                    && recentRequestSession.getStatus().equalsIgnoreCase(status)
                    && recentRequestSession.getCoach().equalsIgnoreCase(coach.replace(",","")))
                {
                    Assert.assertTrue("Request found",true);
                    return i;
                }
            }
            Assert.assertTrue("No Request found", false);
        }
        else
            Assert.assertTrue("No request found",false);

        return 0;
    }

    @Step("Validate that there's no assigned session")
    public void validateNoAssignedSession(){
        List<WebElement> listoftickets =  wellnessPage.getTicketRow();
        if(listoftickets.size() > 1){
            Assert.assertTrue("Request found", false);
        }
        else
            Assert.assertTrue("No request found",true);
    }

    @Step("Click action drop down")
    public void clickActionDropDown(int requestIndex){
        wellnessPage.clickActionDropDown(requestIndex);
    }

    @Step("Click action view from response")
    public void clickActionViewFromResponse(int requestIndex){
        wellnessPage.clickActionViewResponse(requestIndex);
    }


    @Step("Click assign a coach btn")
    public void clickAssignACoachBtn(){
        wellnessPage.clickAssignACoachBtn();
    }

    @Step("Validate assign coach modal is displayed")
    public boolean validateAssignCoachModalDisplayed(){
        return boostModalPage.isAssignCoachModalDisplayed();
    }

    @Step("Type coach name in the modals coach text field")
    public void typeCoachNameInModal(String userRole){
        HashMap<String, wellnessLoginKeys> wellnessLoginKeysList = Serenity.sessionVariableCalled("wellnessLoginKeysList");
        wellnessLoginKeys WellnessLoginKeys = wellnessLoginKeysList.get(userRole);
        boostModalPage.typeCoachNameInModal(WellnessLoginKeys.getFullname());
    }

    @Step("Select coach suggestion")
    public void selectCoachSuggest(String userRole){
        HashMap<String, wellnessLoginKeys> wellnessLoginKeysList = Serenity.sessionVariableCalled("wellnessLoginKeysList");
        wellnessLoginKeys WellnessLoginKeys = wellnessLoginKeysList.get(userRole);
        boostModalPage.clickSuggestedCoachName(WellnessLoginKeys.getFullname());
    }

    @Step("Click Assign coach")
    public void clickAssignCoachBtn(){
        boostModalPage.clickAssignCoachBtn();
    }

    @Step("Assign coach is visible")
    public void isAssignCoachModalCurrentlyVisible(){
        Assert.assertFalse("Assign Coach modal is closed",boostModalPage.isAssignCoachModalCurrentlyVisible());
    }

    @Step("Change item to 100 per page")
    public void changeItemsto100PerPage(){
        wellnessPage.clickItemsPerPageDropDown();
        wellnessPage.filter100items();
    }

    @Step("Click update status button")
    public void clickUpdateStatusButton(){
        wellnessPage.clickUpdateStatusButton();
    }

    @Step("Click update status modal combo box")
    public void clickStatusComboOnModal() {
        boostModalPage.clickStatusComboOnModal();
    }

    public void selectStatusOnModal(String args) {
        boostModalPage.selectStatusOnModal(args);
    }

    public void clickUpdateStatusModalButton() {
        boostModalPage.clickUpdateStatusModalButton();
    }

    public void clickCreateSessionLog() {
        wellnessPage.clickCreateSessionLog();
    }

    public void userClicksPastFormLink() {
        wellnessPage.userClicksPastFormLink();
    }

    public void clickDateIconFromPastForm() {
        wellnessPage.clickDateIconFromPastForm();
    }

    public void selectDateFromDateModal(String date) {
        wellnessPage.selectDateFromDateModal(date);
    }

    @Step("Validate past created session")
    public int validatePastCreatedSession(){
        List<WebElement> listoftickets =  wellnessPage.getTicketRow();
        WebElement rowElement;
        RecentIndividualSession recentIndividualSession = CSVReader.readCSVDataForIndividualSessionForm();
        if(listoftickets.size() > 1){
            rowElement = listoftickets.get(1);
            for(int i = 1;i < listoftickets.size();i++){
                String sessionType = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[2]")).getText();
                String sessionNumber  = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[3]")).getText();
                String coacheeName = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[4]")).getText();
                String lastSessionDate = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[5]")).getText();
                String nextSessionDate = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[6]")).getText();

                if(recentIndividualSession.getCoacheeName().equalsIgnoreCase(coacheeName.replace(",",""))
                        && recentIndividualSession.getSessionType().equalsIgnoreCase(sessionType)
                        && recentIndividualSession.getSessionNumber().equalsIgnoreCase(sessionNumber)
                        && recentIndividualSession.getLastSessionDate().equalsIgnoreCase(lastSessionDate)
                        && recentIndividualSession.getNextSessionDate().equalsIgnoreCase(nextSessionDate))
                {
                    Assert.assertTrue("Request found",true);
                    return i;
                }
            }
            Assert.assertTrue("No Request found", false);
        }
        else
            Assert.assertTrue("No request found",false);

        return 0;
    }

    public void verifyIndividualSessionFormModal() {
        RecentIndividualSession recentIndividualSession = CSVReader.readCSVDataForIndividualSessionForm();
        String site = boostModalPage.getTextFromFormModalElement("Site");
        String campaign = boostModalPage.getTextFromFormModalElement("Campaign");
        String sessionNumber = boostModalPage.getTextFromFormModalElement("Session number");
        String aspect = boostModalPage.getTextFromFormModalElement("Aspect");
        String aspect_checkbox = boostModalPage.getTextFromFormModalElement("Emergent - Safety Concerns");
        String actionItems = boostModalPage.getTextFromFormModalElement("Action items");
        String actionItemsDueDate = boostModalPage.getTextFromFormModalElement("Action items due date (1 per item)");

        if(recentIndividualSession.getSite().equalsIgnoreCase(site)
          && recentIndividualSession.getCampaign().equalsIgnoreCase(campaign)
          && recentIndividualSession.getSessionNumber().equalsIgnoreCase(sessionNumber)
          && recentIndividualSession.getAspect().equalsIgnoreCase(aspect)
          && recentIndividualSession.getAspect_checkbox().equalsIgnoreCase(aspect_checkbox)
          && recentIndividualSession.getActionItems().equalsIgnoreCase(actionItems)
          && recentIndividualSession.getActionItemsDate().equalsIgnoreCase(actionItemsDueDate)){
            Assert.assertTrue("Individual Session Form Validated",true);
        }
            else{
            Assert.assertTrue("Individual Session Form Invalid",false);
        }
    }

    public void verifyRegistrationFormModal() {
        List<String> column = ReadCSVUtil.getExpectedResult("Validate request Registration");

        String site = boostModalPage.getTextFromFormModalElement("Site");
        String campaign = boostModalPage.getTextFromFormModalElement("Campaign");
        String shiftNSchedule = boostModalPage.getTextFromFormModalElement("Shift/schedule and RD");
        String lifeCoaching = boostModalPage.getTextFromFormModalElement("How did you learn about Life Coaching?");
        String outcomeNResult = boostModalPage.getTextFromFormModalElement("Rate your desire to achieve an outcome/result at the end of the coaching session");
        String aspect = boostModalPage.getTextFromFormModalElement("Which aspect of your life would you like to focus on?");
        String lifeAspect = boostModalPage.getTextFromFormModalElement("Please elaborate why you chose that");
        String coachingAgreement = boostModalPage.getTextFromFormModalElement("Read the Coaching Agreement");
        if(column.get(0).equalsIgnoreCase(site)
                && column.get(1).equalsIgnoreCase(campaign)
                && column.get(2).equalsIgnoreCase(shiftNSchedule)
                && column.get(3).equalsIgnoreCase(lifeCoaching)
                && column.get(4).equalsIgnoreCase(outcomeNResult)
                && column.get(5).equalsIgnoreCase(aspect)
                && column.get(6).equalsIgnoreCase(lifeAspect)
                && column.get(7).equalsIgnoreCase(coachingAgreement)){
            Assert.assertTrue("Registration Form Validated",true);
        }
        else{
            Assert.assertTrue("Registration Form Invalid",false);
        }
    }

    public int validateWellnessSessions(Boolean getSurveyBtn) {
        List<WebElement> listoftickets =  wellnessPage.getWellnessSessionTicketRow();
        WebElement rowElement;
        RecentRequestSession recentRequestSession = CSVReader.readCSVDataForSessionRequest();
        if(listoftickets.size() >= 1){
            rowElement = listoftickets.get(0);
            for(int i = 1;i <= listoftickets.size();i++){
                String yogi = rowElement.findElement(By.xpath("//tr[@style='cursor: pointer;']["+i+"]//td[2]")).getText();
                String campaign = rowElement.findElement(By.xpath("//tr[@style='cursor: pointer;']["+i+"]//td[3]")).getText();
                String date = rowElement.findElement(By.xpath("//tr[@style='cursor: pointer;']["+i+"]//td[4]")).getText();
                List<WebElement> takeSurveyBtnText = rowElement.findElements(By.xpath("//tr[@style='cursor: pointer;']["+i+"]//td[6]//div[1]//button[1]"));
                String recRequestSessionDate = changeDateFormat(recentRequestSession.getRegistrationDate());

                if(recentRequestSession.getCoach().equalsIgnoreCase(yogi.replace(",",""))
                     && recentRequestSession.getCampaign().equalsIgnoreCase(campaign)
                     && recRequestSessionDate.equalsIgnoreCase(date)
                     && takeSurveyBtnText.size() >= 1 && getSurveyBtn.equals(true))
                {
                    Assert.assertTrue("Request found",true);
                    return i;
                }

                if(recentRequestSession.getCoach().equalsIgnoreCase(yogi.replace(",",""))
                        && recentRequestSession.getCampaign().equalsIgnoreCase(campaign)
                        && recRequestSessionDate.equalsIgnoreCase(date)
                        && takeSurveyBtnText.size() == 0 && getSurveyBtn.equals(false))
                {
                    Assert.assertTrue("Request found",true);
                    return i;
                }
            }
            Assert.assertTrue("No Request found", false);
        }
        else
            Assert.assertTrue("No request found",false);

        return 0;
    }

    public void validateWellnessSessions(int sessionNumber) {
        List<WebElement> listoftickets =  wellnessPage.getWellnessSessionTicketRow();
        WebElement rowElement;
        int numberOfRecords = 0;
        int expecteNumberOfRecords = 1;
        RecentRequestSession recentRequestSession = CSVReader.readCSVDataForSessionRequest();

        Assert.assertTrue("No request found",listoftickets.size() >= 1);

        rowElement = listoftickets.get(0);
        for(int i = 1;i <= listoftickets.size();i++) {
            String yogi = rowElement.findElement(By.xpath("//tr[@style='cursor: pointer;'][" + i + "]//td[2]")).getText();
            String campaign = rowElement.findElement(By.xpath("//tr[@style='cursor: pointer;'][" + i + "]//td[3]")).getText();
            String date = rowElement.findElement(By.xpath("//tr[@style='cursor: pointer;'][" + i + "]//td[4]")).getText();
            List<WebElement> takeSurveyBtnText = rowElement.findElements(By.xpath("//tr[@style='cursor: pointer;'][" + i + "]//td[6]//div[1]//button[1]"));
            String recRequestSessionDate = changeDateFormat(recentRequestSession.getRegistrationDate());

            if (!recentRequestSession.getCoach().equalsIgnoreCase(yogi.replace(",", ""))){
                return;
            }
            if (!recentRequestSession.getCampaign().equalsIgnoreCase(campaign))
            {
                return;
            }
            if(!recRequestSessionDate.equalsIgnoreCase(date)){
                return;
            }
            if(i == 1 && (sessionNumber  == 2 || (sessionNumber % 3 != 0 && sessionNumber > 3))){
                 Assert.assertTrue("Survey button should be invisible" , takeSurveyBtnText.size() == 0);
            }else{
                 Assert.assertTrue("Survey button not found" , takeSurveyBtnText.size() >= 1);
            }

            numberOfRecords++;
        }

        Assert.assertTrue("Records not found", numberOfRecords >= 1);
        if(sessionNumber > 1) {
            expecteNumberOfRecords = computeExpecteNumberOfRecords(sessionNumber,expecteNumberOfRecords);
        }

        Assert.assertTrue("Doesn't meet expected number of records",numberOfRecords == expecteNumberOfRecords);
    }

    public int computeExpecteNumberOfRecords(int sessionNumber,int i){
        if(sessionNumber - 3 > 0) {
            i++;
            sessionNumber -= 3;
            return computeExpecteNumberOfRecords(sessionNumber, i);
        }
        i++;
        return i;
    }

    public String changeDateFormat(String oldDateFormat) {
        String OLD_FORMAT = oldDateFormat;
        String NEW_date_FORMAT = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        Date d = null;
        try {
            d = sdf.parse(OLD_FORMAT);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern("MM/dd/yy");
        NEW_date_FORMAT = sdf.format(d);
        return NEW_date_FORMAT;
    }

    public void clickTakeTheSurvey(int requestIndex) {
        wellnessPage.clickTakeTheSurvey(requestIndex);
    }

    public void switchToBoostPage() {
        boostPage.switchToThisPage();
    }

    public void switchBackToWellness() {
        wellnessPage.switchToThisPage();
    }

    public void userClicksOnCheckIn() {
        wellnessPage.clickOnCheckIn();
    }

    public void validateTabsLinkInWellnessPage(String role) {
        Assert.assertTrue("Links are available", wellnessPage.hasTabsLinkInWellnessPage(role));
    }

    public void clickViewRegistrationBtn() {
        wellnessPage.clickViewRegistrationBtn();
    }

    public void clickGroupFormLink() {
        wellnessPage.clickGroupFormLink();
    }

    public void testGoToIndividualForm() {
        wellnessPage.openUrl("https://app2-test.taskus.com/form-submitted/Life_Coaching_Form");
    }

    public void userClicksOnTeamMeetingWithTLForm() {
        wellnessPage.userClicksOnTeamMeetingWithTLForm();
    }

    public void userClicksOnIndividualSessionForm() {
        wellnessPage.userClicksOnIndividualSessionForm();
    }

    public void clickDateIconFromWellnessPage() {
        wellnessPage.clickDateIconFromWellnessPage();
    }

    public void selectDateFromDateModalInWellnessPage(String dateAndChangeFormatFromRecentRequest) {
        wellnessPage.selectDateFromDateModalInWellnessPage(dateAndChangeFormatFromRecentRequest);
    }

    public void userClicksOnReportingLink() {
        wellnessPage.userClicksOnReportingLink();
    }

    public void userClicksCoachReportLink(String reportName) {
        wellnessPage.userClicksCoachReportLink(reportName);
    }

    public void validateCoachReportIsValid(String Coach,String reportType) {
        List<String> column = ReadCSVUtil.getExpectedResult("Verify if Master Avatar is able to Filter Coach Monthly Report");

        List<WebElement> listoftickets =  wellnessPage.getDriver().findElements(By.xpath("//app-coach-"+reportType+"//tr[@role='row']"));
        WebElement rowElement;
        if(listoftickets.size() >= 1){
            for(int i = 1;i < listoftickets.size();i++){
                rowElement = listoftickets.get(i);
                String coachName = rowElement.findElement(By.xpath("//app-coach-"+reportType+"//tr[@role='row']["+i+"]//td[3]")).getText();
                String coacheeCount = rowElement.findElement(By.xpath("//app-coach-"+reportType+"//tr[@role='row']["+i+"]//td[4]")).getText();
                String surveyCount = rowElement.findElement(By.xpath("//app-coach-"+reportType+"//tr[@role='row']["+i+"]//td[5]")).getText();
                if(Coach.equalsIgnoreCase(coachName)
                        && column.get(0).equalsIgnoreCase(coacheeCount)
                        && column.get(1).equalsIgnoreCase(surveyCount))
                {
                    Assert.assertTrue("Record found ", true);
                    return;
                }
            }
            Assert.assertTrue("No Record found",false);
        }
        else
            Assert.assertTrue("No Record found",false);

    }

    public void validateSiteReportIsValid(String site,String reportType) {
        List<String> column = ReadCSVUtil.getExpectedResult("Verify if Master Avatar is able to Filter Site Monthly/Weekly Report");

        List<WebElement> listoftickets =  wellnessPage.getDriver().findElements(By.xpath("//app-site-"+reportType+"//tr[@role='row']"));
        WebElement rowElement;
        if(listoftickets.size() >= 1){
            for(int i = 1;i < listoftickets.size();i++){
                rowElement = listoftickets.get(i);
                String siteName = rowElement.findElement(By.xpath("//app-site-"+reportType+"//tr[@role='row']["+i+"]//td[1]")).getText();
                String coachCount = rowElement.findElement(By.xpath("//app-site-"+reportType+"//tr[@role='row']["+i+"]//td[2]")).getText();
                String coacheeCount = rowElement.findElement(By.xpath("//app-site-"+reportType+"//tr[@role='row']["+i+"]//td[3]")).getText();
                String surveyCount = rowElement.findElement(By.xpath("//app-site-"+reportType+"//tr[@role='row']["+i+"]//td[4]")).getText();
                if(site.equalsIgnoreCase(siteName)
                        && column.get(0).equalsIgnoreCase(coacheeCount)
                        && column.get(1).equalsIgnoreCase(surveyCount)
                        && column.get(2).equalsIgnoreCase(coachCount))
                {
                    Assert.assertTrue("Record found ", true);
                    return;
                }
            }
            Assert.assertTrue("No Record found",false);
        }
        else
            Assert.assertTrue("No Record found",false);

    }

    public void changeCoachNameFilterTo(String coachName) {
        wellnessPage.changeCoachNameFilterTo(coachName);
    }

    public void clickCoachNameFilter(){
        wellnessPage.clickCoachNameFilter();
    }

    public void validateStatusReport() {
        List<WebElement> listoftickets =  wellnessPage.getTicketRow();
        WebElement rowElement;
        RecentRequestSession recentRequestSession = CSVReader.readCSVDataForSessionRequest();
        if(listoftickets.size() >= 1){
            rowElement = listoftickets.get(1);
            for(int i = 1;i < listoftickets.size();i++){
                String coachee = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[2]")).getText().trim();
                String site  = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[3]")).getText().trim();
                String campaign = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[4]")).getText().trim();
                String regDate = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[6]")).getText().trim();
                String status = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[5]")).getText().trim();
                String coach = rowElement.findElement(By.xpath("//tr[@role='row']["+i+"]//td[1]")).getText().trim();

                if(recentRequestSession.getCoacheeName().equalsIgnoreCase(coachee.replace(",",""))
                        && recentRequestSession.getSite().equalsIgnoreCase(site)
                        && recentRequestSession.getCampaign().equalsIgnoreCase(campaign)
                        && recentRequestSession.getRegistrationDate().equalsIgnoreCase(regDate)
                        && recentRequestSession.getStatus().equalsIgnoreCase(status)
                        && recentRequestSession.getCoach().equalsIgnoreCase(coach.replace(",","")))
                {
                    Assert.assertTrue("Request found",true);
                    return;
                }
            }
            Assert.assertTrue("No Request found", false);
        }
        else
            Assert.assertTrue("No request found",false);

    }

    public void logoutAccountInWellness() {
        wellnessPage.userclickAccountDropDown();
        wellnessPage.userclickslogoutBtn();
        wellnessPage.userclicksYes();
    }

    public void validateThatCoacheeEIDColumnIsNowAvailable() {
        Assert.assertTrue("Validate Coachee EID column header",wellnessPage.isCoacheeEIDVisible());
    }

    public void clickCoacheeEIDColumnHeader() {
        wellnessPage.clickCoacheeEIDColumnHeader();
    }

    public void clickStatusColumnHeader() {
        wellnessPage.clickStatusColumnHeader();
    }

    public void fetchRequestRows() {
        firstRowStatusValue = wellnessPage.fetchRequestRows();
    }

    public void validateFirstRowIsChanged() {
        Assert.assertNotEquals("Validate if first row status column is changed.",firstRowStatusValue,wellnessPage.fetchRequestRows());
    }

    public void userClicksOnGoToLeadershipDepartmentalTrainingLog() {
        wellnessPage.userClicksOnGoToLeadershipDepartmentalTrainingLog();
    }

    public void userClicksOnGoToDebriefStandUpSkill() {
        wellnessPage.userClicksOnGoToDebriefStandUpSkill();
    }

    public void selectWeeklySiteReportDateAndSite() throws ParseException {
        wellnessPage.clickDateBtnInSiteReport("weekly");
        wellnessPage.selectDate();
    }

    public void selectMonthlySiteReportDateAndSite() throws ParseException {
        wellnessPage.clickDateBtnInSiteReport("monthly");
        wellnessPage.selectDate();
    }

    public void selectGlobalSiteReportDateAndSite() throws ParseException {
        wellnessPage.selectDateRange();
//        wellnessPage.ClickFromDateGlobalReportSite();
        wellnessPage.SelectFromDateGlobalReportSite();
//        wellnessPage.ClickToDateGlobalReportSite();
        wellnessPage.selectDate();
    }

    public void clickReportDropDown() {
        wellnessPage.clickReportDropDown();
    }

    public void selectReport(String report) {
        wellnessPage.selectReport(report);
    }

    public void validateGlobalReports(String reportName) {
        List<String> column = ReadCSVUtil.getExpectedResult(reportName);
        String reportDate = Serenity.sessionVariableCalled("ReportDate");
        if(column.get(0).equals("Multi Column")){
            for(String colName : column){
                if(colName.equals("Multi Column")){
                    continue;
                }
                validateGlobalReports(colName);
            }
            return;
        }
        List<WebElement> listofreports = getDriver().findElements(By.xpath("//tr[contains(@class,'cursor')]"));
        if(listofreports.size() >= 1){
            for(int i = 1;i <= listofreports.size();i++) {
                String coach = getDriver().findElement(By.xpath("//tr[@role='row'][" + i + "]//td[2]")).getText().trim();
                String coachee = getDriver().findElement(By.xpath("//tr[@role='row'][" + i + "]//td[3]")).getText().trim();
                String site = getDriver().findElement(By.xpath("//tr[@role='row'][" + i + "]//td[4]")).getText().trim();
                String campaign = getDriver().findElement(By.xpath("//tr[@role='row'][" + i + "]//td[5]")).getText().trim();
                String date = getDriver().findElement(By.xpath("//tr[@role='row'][" + i + "]//td[6]")).getText().trim();

                if(column.get(0).equalsIgnoreCase(coach.replace(",",""))
                        && column.get(1).equalsIgnoreCase(coachee.replace(",",""))
                        && column.get(2).equalsIgnoreCase(site)
                        && column.get(3).equalsIgnoreCase(campaign)
                        && reportDate.equalsIgnoreCase(date)
                )
                {
                    Assert.assertTrue("Record found ", true);
                    return;
                }
            }
            Assert.assertTrue("No reports found",false);
        }else{
            Assert.assertTrue("No reports found",false);
        }
    }
}
