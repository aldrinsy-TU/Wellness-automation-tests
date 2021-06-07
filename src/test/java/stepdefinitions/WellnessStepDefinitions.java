package stepdefinitions;


import common.CSVReader;
import common.DBHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import steps.*;
import steps.FormPageSteps.*;
import testdataobjects.RecentRequestSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WellnessStepDefinitions {

    @Steps
    WellnessSteps wellnessSteps;

    @Steps
    LoginStepDefinitions loginStepDefinitions;

    @Steps
    IndividualSessionFormsSteps individualSessionFormsSteps;

    @Steps
    BoostSatisfactionSurveyFormsSteps boostSatisfactionSurveyFormsSteps;

    @Steps
    CheckInFormSteps checkInFormSteps;

    @Steps
    GroupFormsSteps groupFormsSteps;

    @Steps
    TeamMeetingWithTLFormsSteps teamMeetingWithTLFormsSteps;

    @Steps
    YogiIndividualSessionFormsSteps yogiIndividualSessionFormsSteps;

    @Steps
    LeadershipAndDepartmentalTrainingLogFormsSteps leadershipAndDepartmentalTrainingLogFormsSteps;

    @Steps
    DebriefAndStandUpSkillFormsSteps debriefAndStandUpSkillFormsSteps;

    int requestIndex;
    String loginUserRole;

    DBHelper dbHelper;

    @Then("User clicks on Requests link")
    public void userClicksRequestsLink(){
        wellnessSteps.userClickOnRequestsLink();
    }

    @Then("User clicks on Group Form link")
    public void userClicksGroupFormsLink(){
        wellnessSteps.clickGroupFormLink();
    }

    @Then("User clicks on Past Forms Link")
    public void userClicksPastFormLink(){
        wellnessSteps.userClicksPastFormLink();
    }

    @Then("Validate newly added record")
    public void validateNewlyAddedRecord(){
        requestIndex = wellnessSteps.validateRequestRecord();
    }

    @And("Assign {string} coach to a request")
    public void assignCoachToRequest(String userRole){
        wellnessSteps.clickActionDropDown(requestIndex);
        wellnessSteps.clickAssignACoachBtn();
        if(wellnessSteps.validateAssignCoachModalDisplayed()){
            wellnessSteps.typeCoachNameInModal(userRole);
            wellnessSteps.selectCoachSuggest(userRole);
            wellnessSteps.clickAssignCoachBtn();
            loginStepDefinitions.updateCSVRequest("Assigned",userRole,false);
        }
    }

    @And("Reassign {string} coach to a request")
    public void reassignCoachToRequest(String userRole){
        wellnessSteps.clickActionDropDown(requestIndex);
        wellnessSteps.clickAssignACoachBtn();
        if(wellnessSteps.validateAssignCoachModalDisplayed()){
            wellnessSteps.typeCoachNameInModal(userRole);
            wellnessSteps.selectCoachSuggest(userRole);
            wellnessSteps.clickAssignCoachBtn();
            loginStepDefinitions.updateCSVRequest("Reassigned",userRole,false);
        }
    }

    @And("Validate that status is changed to {string}")
    public void validateStatusChangeTo(String args){
        RecentRequestSession recentRequestSession = CSVReader.readCSVDataForSessionRequest();
        userFilterRequestStatus(args);
        changeSiteTo(recentRequestSession.getSite());
        changeCampaignTo(recentRequestSession.getCampaign());
        changeItemsPerPage();
        requestIndex = wellnessSteps.validateRequestRecord();
    }

    @Then("Validate that there's no assigned session")
    public void validateNoAssignedSession() {
        wellnessSteps.validateNoAssignedSession();
    }

    @And("Update request status to {string}")
    public void updateRequestStatusTo(String args){
        wellnessSteps.clickActionDropDown(requestIndex);
        wellnessSteps.clickUpdateStatusButton();
        wellnessSteps.clickStatusComboOnModal();
        wellnessSteps.selectStatusOnModal(args);
        wellnessSteps.clickUpdateStatusModalButton();
        loginStepDefinitions.updateCSVRequest(args,loginUserRole != null ? loginUserRole : "Master Avatar",false);
    }

    @And("Create session log on request")
    public void createSessionLogOnRequest(){
        RecentRequestSession recentRequestSession = CSVReader.readCSVDataForSessionRequest();
        userFilterRequestStatus(recentRequestSession.getStatus());
        changeSiteTo(recentRequestSession.getSite());
        changeCampaignTo(recentRequestSession.getCampaign());
        changeItemsPerPage();
        requestIndex = wellnessSteps.validateRequestRecord();
        wellnessSteps.clickActionDropDown(requestIndex);
        wellnessSteps.clickCreateSessionLog();
    }

    @Then("Change item per page to 100")
    public void changeItemsPerPage(){
        wellnessSteps.changeItemsto100PerPage();
    }

    @And("User select {string} site for request filter")
    public void changeSiteTo(String args){
        wellnessSteps.userClicksOnRequestFilterSite();
        wellnessSteps.selectfilterComboBox(args);
    }

    @And("User select {string} campaign for request filter")
    public void changeCampaignTo(String args){
        wellnessSteps.userClicksOnRequestFilterCampaign();
        wellnessSteps.selectfilterComboBox(args);
    }

    @And("User select {string} status for request filter")
    public void userFilterRequestStatus(String args){
        wellnessSteps.userClicksOnRequestFilterStatus();
        wellnessSteps.selectfilterStatusComboBox(args);
    }

    @And("User insert sample data to Individual Session form")
    public void userInsertSampleDataToIndividualSessionForm() {
        createIndividualSessionForm("1st");
//        individualSessionFormsSteps.validateFormRequest();
        CSVReader.saveCSVDataForIndividualSessionForm(true);
    }

    private void createIndividualSessionForm(String sessionNumber) {
        String DateStr;
        SimpleDateFormat dtFormat = new SimpleDateFormat("d/MM/yyyy");
        DateStr = dtFormat.format(new Date());
        individualSessionFormsSteps.waitPageToLoad();
        individualSessionFormsSteps.clickSessionNumberComboBox();
        individualSessionFormsSteps.selectItemInSessionNumberComboBox(sessionNumber);
        individualSessionFormsSteps.clickAspectComboBox();
        individualSessionFormsSteps.selectItemAspectComboBox("Emergent - Safety Concerns");
        individualSessionFormsSteps.clickEmergentConcernsCheckBox("Concerned about the ability to remain safe");
        individualSessionFormsSteps.insertTextToActionItems("test");
//        individualSessionFormsSteps.insertTextToActionItemsDueDate(DateStr);
        individualSessionFormsSteps.selectActionItemsDueDate();
        individualSessionFormsSteps.clickEmployeeCurrentStatusComboBox();
        individualSessionFormsSteps.selectEmployeeStatus();
        individualSessionFormsSteps.clickSubmitBtn();
    }

    @And("Select date in Past Form using recent request date")
    public void selectDateFromDateModal() throws ParseException {
        wellnessSteps.clickDateIconFromPastForm();
        wellnessSteps.selectDateFromDateModal(getDateAndChangeFormatFromRecentRequest());
        changeItemsPerPage();
    }

    @And("Validate past created session")
    public void validatePastCreatedSession() {
        requestIndex = wellnessSteps.validatePastCreatedSession();
        wellnessSteps.clickActionViewFromResponse(requestIndex);
        wellnessSteps.verifyIndividualSessionFormModal();
    }

    @And("User take the survey at wellness")
    public void userTakeTheSurveyAtWellness() {
        requestIndex = wellnessSteps.validateWellnessSessions(true);
        wellnessSteps.clickTakeTheSurvey(requestIndex);
        boostSatisfactionSurveyFormsSteps.waitPageToLoad();
        if(boostSatisfactionSurveyFormsSteps.checkIfFormIsDisplayed()){
//            for(int i = 1;i<=8;i++){
//                boostSatisfactionSurveyFormsSteps.clickSatisfactionOnSurveyQuestion("5",i);
//            }
            boostSatisfactionSurveyFormsSteps.clickStatisfactionOnSurveyQuestion();
            boostSatisfactionSurveyFormsSteps.userFillsTextArea();
            boostSatisfactionSurveyFormsSteps.clickSubmitBtn();
        }
    }


    @And("User clicks on check-in and insert sample data on check-in Form")
    public void userClicksOnCheckIn() {
        wellnessSteps.userClicksOnCheckIn();
        checkInFormSteps.waitForCheckInFormPageToLoad();
//        checkInFormSteps.insertAnswerToQ1("1");
//        checkInFormSteps.insertAnswerToQ2("1");
//        checkInFormSteps.selectNoToAll();
//        checkInFormSteps.clickAnswerToQ5("Neutral");
        checkInFormSteps.clickNoRadioBtn();
        checkInFormSteps.clickHighlySatisfiedRadioBtn();
        checkInFormSteps.userFillsTextArea();
        checkInFormSteps.clickSubmitBtn();
    }

    @Then("User clicks checkin")
    public void userClicksCheckIn(){
        wellnessSteps.userClicksOnCheckIn();
    }

    @Then("A {string} validates tabs link in Wellness page")
    public void validateTabsLinkInWellnessPage(String role) {
        wellnessSteps.validateTabsLinkInWellnessPage(role);
    }

    @And("Validate request Registration")
    public void validateRequestRegistration() {
        wellnessSteps.clickActionDropDown(requestIndex);
        wellnessSteps.clickViewRegistrationBtn();
        wellnessSteps.verifyRegistrationFormModal();
    }

    @Given("that fetch session assigned for {string}")
    public void thatFetchSessionAssignedFor(String arg0) {
        RecentRequestSession recentRequestSession = CSVReader.readCSVDataForSessionRequest();
        loginStepDefinitions.updateCSVRequest(recentRequestSession.getStatus(),arg0,false);
        loginUserRole = arg0;
    }

    @And("Input data on Group Form")
    public void inputDataOnGroupForm() {
        groupFormsSteps.waitPageToLoad();
        groupFormsSteps.userClicksOnSiteBtn();
        groupFormsSteps.userSelectSite("Adventures Intelligence");
        groupFormsSteps.userClicksOnCampaignTextBox();
        groupFormsSteps.userSelectCampaign("Sephora-Customer Support-Blended-TUT");
        groupFormsSteps.userInputNumberOfAttendees("1");
        groupFormsSteps.userClicksGroupCategoryOperationsLeadership();
        groupFormsSteps.userClicksTopicOfGroupSelfRegulation();
        groupFormsSteps.userClicksOnFirstRadionBtnInSuccessOrChallengingQuestion();
        groupFormsSteps.userClicksOnSubmitBtn();

        teamMeetingWithTLFormsSteps.userRefreshThePage();
        groupFormsSteps.verifyReponseIsSubmitted();
        teamMeetingWithTLFormsSteps.userRefreshThePage();
    }

    @Then("test go to individual form")
    public void testGoToIndividualForm() {
        wellnessSteps.testGoToIndividualForm();
    }

    @And("Validate Group Form Session")
    public void validateGroupFormSession() {
        requestIndex = groupFormsSteps.validateGroupFormSession();
        wellnessSteps.clickActionViewFromResponse(requestIndex);
        groupFormsSteps.verifyGroupSessionFormModal();
    }

    @Then("User clicks on Team Meeting with TL Form")
    public void userClicksOnTeamMeetingWithTLForm() {
        wellnessSteps.userClicksOnTeamMeetingWithTLForm();
    }

    @And("Input data on Team Meeting with TL Form")
    public void inputDataOnTeamMeetingWithTLForm() {
        teamMeetingWithTLFormsSteps.waitPageToLoad();
        teamMeetingWithTLFormsSteps.userClicksOnSiteBtn();
        teamMeetingWithTLFormsSteps.userSelectSite("Adventures Intelligence");
        teamMeetingWithTLFormsSteps.userClicksOnCampaignTextBox();
        teamMeetingWithTLFormsSteps.userSelectCampaign("Sephora-Customer Support-Blended-TUT");
        teamMeetingWithTLFormsSteps.userInputNumberOfAttendees("1");
        teamMeetingWithTLFormsSteps.userInputNameOfTL("test");
        teamMeetingWithTLFormsSteps.userInputDescriptionOfIdentifiedConcernsOrOpportunitiesForGrowth("test");
        teamMeetingWithTLFormsSteps.userClicksOnSubmitBtn();

        teamMeetingWithTLFormsSteps.userRefreshThePage();

        teamMeetingWithTLFormsSteps.verifyReponseIsSubmitted();
        teamMeetingWithTLFormsSteps.userRefreshThePage();
    }

    @And("Validate Team Meeting with TL Form Session")
    public void validateTeamMeetingWithTLFormSession() {
        requestIndex = teamMeetingWithTLFormsSteps.validateTeamMeetingWithTLFormFormSession();
        wellnessSteps.clickActionViewFromResponse(requestIndex);
        teamMeetingWithTLFormsSteps.verifyTeamMeetingWithTLFormSessionFormModal();
    }

    @Then("User clicks on Individual Session Form")
    public void userClicksOnIndividualSessionForm() {
        wellnessSteps.userClicksOnIndividualSessionForm();
    }

    @And("Input data on Individual Session Form")
    public void inputDataOnIndividualSessionForm() {
        yogiIndividualSessionFormsSteps.waitPageToLoad();
        yogiIndividualSessionFormsSteps.userClicksOnSiteBtn();
        yogiIndividualSessionFormsSteps.userSelectSite("Adventures Intelligence");
        yogiIndividualSessionFormsSteps.userClicksOnCampaignTextBox();
        yogiIndividualSessionFormsSteps.userSelectCampaign("Sephora-Customer Support-Blended-TUT");
        yogiIndividualSessionFormsSteps.userClicksOnSessionNumberComboBox();
        yogiIndividualSessionFormsSteps.userSelect1stOnSessionNumberComboBox();
        yogiIndividualSessionFormsSteps.userClicksOnAspectComboBox();
        yogiIndividualSessionFormsSteps.userSelectPersonalOnAspectComboBox();
        yogiIndividualSessionFormsSteps.userClicksRelationshipProblem();
        yogiIndividualSessionFormsSteps.userClicksOnSubmitBtn();

        teamMeetingWithTLFormsSteps.userRefreshThePage();
        yogiIndividualSessionFormsSteps.verifyReponseIsSubmitted();
        teamMeetingWithTLFormsSteps.userRefreshThePage();
    }

    @And("Validate Individual Session Form Session")
    public void validateIndividualSessionFormSession() {
        requestIndex = yogiIndividualSessionFormsSteps.validateIndividualSessionFormSession();
        wellnessSteps.clickActionViewFromResponse(requestIndex);
        yogiIndividualSessionFormsSteps.verifyIndividualSessionFormSessionFormModal();
    }

    @And("Select date in Past Form using session request date")
    public void selectDateInPastFormUsingSessionRequestDate() throws ParseException {
        wellnessSteps.clickDateIconFromPastForm();
        wellnessSteps.selectDateFromDateModal(getDateAndChangeFormatFromSessionRequest());
        changeItemsPerPage();
    }

    public String getDateAndChangeFormatFromRecentRequest() throws ParseException {
        RecentRequestSession recentRequestSession = CSVReader.readCSVDataForSessionRequest();
        String OLD_FORMAT = recentRequestSession.getRegistrationDate();
        String NEW_FORMAT = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        Date d = sdf.parse(OLD_FORMAT);
        sdf.applyPattern("MMMMM d, YYYY");
        NEW_FORMAT = sdf.format(d);
        return NEW_FORMAT;
    }

    public String getDateAndChangeFormatFromSessionRequest() throws ParseException {
        String OLD_FORMAT = Serenity.sessionVariableCalled("GroupFormDateCreated");
        String NEW_FORMAT = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        Date d = sdf.parse(OLD_FORMAT);
        sdf.applyPattern("MMMMM d, YYYY");
        NEW_FORMAT = sdf.format(d);
        return NEW_FORMAT;
    }

    @And("Select date in wellness page")
    public void selectDateInWellnessPage() throws ParseException {
        wellnessSteps.clickDateIconFromWellnessPage();
        wellnessSteps.selectDateFromDateModalInWellnessPage(getDateAndChangeFormatFromRecentRequest());
    }

    @Then("User clicks on Reporting Link")
    public void userClicksOnReportingLink() {
        wellnessSteps.userClicksOnReportingLink();
    }

    @Then("User clicks {string} Report Link")
    public void userClicksCoachReportLink(String reportName) {
        wellnessSteps.userClicksCoachReportLink(reportName);
    }

    @Then("Validate Coach report for {string} is valid")
    public void validateCoachReportIsValid(String coachName) {
        wellnessSteps.validateCoachReportIsValid(coachName,"monthly");
        wellnessSteps.validateCoachReportIsValid(coachName,"weekly");
    }

    @And("Validate status report")
    public void validateStatusReportForCoach() {
        teamMeetingWithTLFormsSteps.userRefreshThePage();
        RecentRequestSession recentRequestSession = CSVReader.readCSVDataForSessionRequest();
        wellnessSteps.clickCoachNameFilter();
        wellnessSteps.changeCoachNameFilterTo(recentRequestSession.getCoach().replaceFirst(" ",", "));
        changeItemsPerPage();
        wellnessSteps.validateStatusReport();
    }

    @Then("User refresh the page")
    public void userRefreshThePage() {
        teamMeetingWithTLFormsSteps.userRefreshThePage();
    }

    @And("Validate survey button is removed")
    public void validateSurveyButtonIsRemoved() throws ParseException {
        wellnessSteps.switchToBoostPage();
        userRefreshThePage();
        selectDateInWellnessPage();
        wellnessSteps.validateWellnessSessions(false);
    }

    @Given("Clear wellness database")
    public void clearWellnessDatabase() {
        dbHelper = new DBHelper();
        dbHelper.truncateWellnessTBL();
    }

    @And("Validate check-in button is disabled")
    public void validateCheckInButtonIsDisabled() {
        checkInFormSteps.switchToThisPage();
        teamMeetingWithTLFormsSteps.userRefreshThePage();
        checkInFormSteps.validateCheckinBtnIsDisabled();
    }

    @Then("Validate Site report for {string} is valid")
    public void validateSiteReportForIsValid(String Site) {
        wellnessSteps.validateSiteReportIsValid(Site,"monthly");
        wellnessSteps.validateSiteReportIsValid(Site,"weekly");
    }

    @And("Create {int} individual session log")
    public void createSessionLog(int numberOfSessions) {
        int i = 1;
        while(numberOfSessions >= i){
            createSessionLogOnRequest();
            createIndividualSessionForm(convertNumberToNumberWithSuffix(Integer.toString(i)));
            userClicksRequestsLink();
            userRefreshThePage();
            wellnessSteps.switchToBoostPage();
            userRefreshThePage();
            wellnessSteps.validateWellnessSessions(i);
            wellnessSteps.switchBackToWellness();
            i++;
        }

    }

    private String convertNumberToNumberWithSuffix(String number){
        if(number.length() > 1){
            number = Character.toString(number.charAt(number.length() - 1));
        }

        String numberWithSuffix ="";
        switch (number){
            case "1":
                numberWithSuffix = number + "st";
                break;
            case "2":
                numberWithSuffix = number + "nd";
                break;
            case "3":
                numberWithSuffix = number + "rd";
                break;
            default:
                numberWithSuffix = number + "th";
        }

        return numberWithSuffix;
    }

    @And("Logout account in Wellness")
    public void logoutAccountInWellness() {
        wellnessSteps.logoutAccountInWellness();
    }

    @Then("Validate that coachee EID column is now available")
    public void validateThatCoacheeEIDColumnIsNowAvailable() {
        wellnessSteps.fetchRequestRows();
        wellnessSteps.validateThatCoacheeEIDColumnIsNowAvailable();
    }

    @Then("Click coachee EID column header")
    public void clickCoacheeEIDColumnHeader() {
        wellnessSteps.clickCoacheeEIDColumnHeader();
    }

    @Then("Click status column header")
    public void clickStatusColumnHeader() {
        wellnessSteps.clickStatusColumnHeader();
    }

    @Then("Validate first row is changed")
    public void validateFirstRowIsChanged() {
        wellnessSteps.validateFirstRowIsChanged();
    }

    @Then("User clicks on Go to Leadership and Departmental Training Log")
    public void userClicksOnGoToLeadershipDepartmentalTrainingLog() {
        wellnessSteps.userClicksOnGoToLeadershipDepartmentalTrainingLog();
    }

    @And("User insert sample data to Leadership and Departmental Training Log")
    public void userInsertSampleDataToLeadershipAndDepartmentalTrainingLog() {
        leadershipAndDepartmentalTrainingLogFormsSteps.waitPageToLoad();
        leadershipAndDepartmentalTrainingLogFormsSteps.userSelectPreferredSessionDate();
        leadershipAndDepartmentalTrainingLogFormsSteps.userSelectGeography("India");
        leadershipAndDepartmentalTrainingLogFormsSteps.userSelectSite("Bhopal");
        leadershipAndDepartmentalTrainingLogFormsSteps.userSelectPresentation("447");
        leadershipAndDepartmentalTrainingLogFormsSteps.userSelectDuration("453");
        leadershipAndDepartmentalTrainingLogFormsSteps.insertTextInSubjectTextfield("test");
        leadershipAndDepartmentalTrainingLogFormsSteps.userClicksOnSubmitBtn();
        leadershipAndDepartmentalTrainingLogFormsSteps.verifyReponseIsSubmitted();
    }

    @And("Validate Leadership and Departmental Training Log Form Session")
    public void validateLeadershipAndDepartmentalTrainingLogFormSession() throws ParseException {
        requestIndex = leadershipAndDepartmentalTrainingLogFormsSteps.validateIndividualSessionFormSession();
        wellnessSteps.clickActionViewFromResponse(requestIndex);
        leadershipAndDepartmentalTrainingLogFormsSteps.verifyFormSessionModal();
    }

    @Then("User clicks on  Go to Debrief and Stand-Up Skill")
    public void userClicksOnGoToDebriefStandUpSkill() {
        wellnessSteps.userClicksOnGoToDebriefStandUpSkill();
    }

    @And("User insert sample data to Debrief and Stand-Up Skill")
    public void userInsertSampleDataToDebriefAndStandUpSkill() {
        debriefAndStandUpSkillFormsSteps.waitPageToLoad();
        debriefAndStandUpSkillFormsSteps.userSelectGeography("India");
        debriefAndStandUpSkillFormsSteps.userSelectSite("Bhopal");
        debriefAndStandUpSkillFormsSteps.userSelectDuration();
        debriefAndStandUpSkillFormsSteps.WastheGroupSuccessfulOrChallenging("442");
        debriefAndStandUpSkillFormsSteps.userClicksOnSubmitBtn();
        debriefAndStandUpSkillFormsSteps.verifyReponseIsSubmitted();
    }

    @And("Validate Debrief and Stand-Up Skill Log Form Session")
    public void validateDebriefAndStandUpSkillLogFormSession(){
        requestIndex = debriefAndStandUpSkillFormsSteps.validateIndividualSessionFormSession();
        wellnessSteps.clickActionViewFromResponse(requestIndex);
        debriefAndStandUpSkillFormsSteps.verifyFormSessionModal();
    }

    @Then("Select Weekly Site Report Date and Site")
    public void selectWeeklySiteReportDateAndSite() throws ParseException {
        wellnessSteps.selectWeeklySiteReportDateAndSite();
    }

    @Then("Select Monthly Site Report Date and Site")
    public void selectMonthlySiteReportDateAndSite() throws ParseException {
        wellnessSteps.selectMonthlySiteReportDateAndSite();
    }

    @Then("Select Global Site Report Date From and To")
    public void selectGlobalSiteReportDateAndSite() throws ParseException {
        wellnessSteps.selectGlobalSiteReportDateAndSite();
    }

    @Then("Select {string} Report From Global Reports")
    public void selectReportFromGlobalReports(String Report) {
        wellnessSteps.clickReportDropDown();
        wellnessSteps.selectReport(Report);
    }

    @Then("Validate Check in V2 Reports")
    public void validateCheckInVReports() {
        wellnessSteps.validateGlobalReports("Validate Check in V2 Reports");
    }

    @Then("Validate Coaches and Wellness and Resiliency Manager Sessions Reports")
    public void validateCoachesAndWellnessAndResiliencyManagerSessionsReports() {
        wellnessSteps.validateGlobalReports("Validate Coaches and Wellness and Resiliency Manager Sessions Reports");
    }

    @Then("Validate Group Form Sessions Reports")
    public void validateGroupFormSessionsReports() {
        wellnessSteps.validateGlobalReports("Validate Group Form Sessions Reports");
    }

    @Then("Validate Mental Health Professional Sessions Reports")
    public void validateMentalHealthProfessionalSessionsReports() {
        wellnessSteps.validateGlobalReports("Validate Mental Health Professional Sessions Reports");
    }

    @Then("Validate Survey Reports")
    public void validateSurveyReports() {
        wellnessSteps.validateGlobalReports("Validate Survey Reports");
    }

    @Then("Validate Team Meeting with TL Sessions Reports")
    public void validateTeamMeetingWithTLSessionsReports() {
        wellnessSteps.validateGlobalReports("Validate Team Meeting with TL Sessions Reports");
    }
}
