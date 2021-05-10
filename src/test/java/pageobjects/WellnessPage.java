package pageobjects;

import common.CommonFunctions;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WellnessPage extends CommonFunctions {

    @FindBy(xpath = "//a[@href='/new-requests']")
    private WebElementFacade requestsLink;

    @FindBy(xpath = "//mat-select[@id='mat-select-1']")
    private WebElementFacade requestsFilterStatus;

    @FindBy(xpath = "//input[@id='mat-input-0']")
    private WebElementFacade requestsFilterCampaign;

    @FindBy(xpath = "//mat-select[@id='mat-select-0']")
    private WebElementFacade requestsFilterSite;

    @FindBy(xpath = "//mat-icon[contains(text(),'person_add')]")
    private WebElementFacade assignACoachBtn;

    @FindAll({ @org.openqa.selenium.support.FindBy(xpath = "//tr[@role='row']"),})
    List<WebElement> TicketRow;

    @FindAll({ @org.openqa.selenium.support.FindBy(xpath = "//tr[@style='cursor: pointer;']"),})
    List<WebElement> wellnessSessionTicketRow;

    @FindBy(xpath = "//span[contains(text(),'10')]")
    private WebElementFacade itemsPerPageDropDown;

    @FindBy(xpath = "//span[contains(text(),'Go to Group Form')]")
    private WebElementFacade groupFormLink;

    @FindBy(xpath = "//span[contains(text(),'Reports')]")
    private WebElementFacade reportingLink;

    public void clickRequestsLink(){
        if(!requestsLink.isClickable()){
            withTimeoutOf(2, TimeUnit.MINUTES).waitFor(ExpectedConditions.elementToBeClickable(requestsLink));
        }
        JavascriptExecutor exec = (JavascriptExecutor) this.getDriver();
        exec.executeScript("arguments[0].click();", requestsLink);
//        waitForAngularRequestsToFinish();
    }

    public void clickGroupFormLink(){
        if(!groupFormLink.isClickable()){
            withTimeoutOf(2, TimeUnit.MINUTES).waitFor(ExpectedConditions.elementToBeClickable(groupFormLink));
        }
        groupFormLink.click();
//        waitForAngularRequestsToFinish();
    }

    public void clickOnRequestFilterStatus(){
//        moveClickBtn(find(By.xpath("//mat-select[@id='mat-select-1']")));
        WebElement element = find(By.xpath("//mat-select[@id='mat-select-1']"));

        JavascriptExecutor exec = (JavascriptExecutor) this.getDriver();
        exec.executeScript("arguments[0].click();", element);
        waitForAngularRequestsToFinish();
    }

    public void clickOnRequestFilterCampaign(){
        WebElement element = getDriver().findElement(By.xpath("//input[@id='mat-input-0']"));
        moveClickBtn(element);

        waitForAngularRequestsToFinish();
    }

    public void clickOnRequestFilterSite(){
        WebElement element = getDriver().findElement(By.xpath("//mat-select[@id='mat-select-0']"));
        moveClickBtn(element);

        waitForAngularRequestsToFinish();
    }

    public void selectFilter(String args){
        WebElement select = getDriver().findElement(By.xpath("//span[contains(text(),'"+args+"')]"));
        if(!select.isDisplayed()){
            select = getDriver().findElement(By.xpath("//mat-option[@ng-reflect-value='"+args+"'][@class='mat-option ng-star-inserted']"));
        }
        moveClickBtn(select);

        waitForAngularRequestsToFinish();
    }

    public void selectStatusFilter(String args){
        int combo = 0;
        if(args.equalsIgnoreCase("New")){
            combo = 6;
        }
        else if(args.equalsIgnoreCase("Assigned")){
            combo = 7;
        }
        else if(args.equalsIgnoreCase("Ongoing")){
            combo = 8;
        }
        else if(args.equalsIgnoreCase("Completed")){
            combo = 9;
        }
        else if(args.equalsIgnoreCase("Reassigned")){
            combo = 10;
        }
        else if(args.equalsIgnoreCase("No Show")){
            combo = 11;
        }

        WebElement element = getDriver().findElement(By.xpath("//mat-option[@ng-reflect-value='"+(combo - 5)+"']"));
        JavascriptExecutor exec = (JavascriptExecutor) this.getDriver();
        exec.executeScript("arguments[0].click();", element);

        waitForAngularRequestsToFinish();
    }

    public void clickActionDropDown(int requestIndex){
        WebElement element = getDriver().findElement(By.xpath("//tr[@role='row']["+requestIndex+"]//td[9]//span[1]"));
        JavascriptExecutor exec = (JavascriptExecutor) this.getDriver();
        exec.executeScript("arguments[0].click();", element);
    }

    public void clickActionViewResponse(int requestIndex){
        WebElement element = getDriver().findElement(By.xpath("//tr[@role='row']["+requestIndex+"]//td[7]//span[1]"));
        moveClickBtn(element);
    }

    public void clickAssignACoachBtn(){
        moveClickBtn(assignACoachBtn);
    }

    public List<WebElement> getTicketRow(){
        return TicketRow;
    }

    public List<WebElement> getWellnessSessionTicketRow(){
        return wellnessSessionTicketRow;
    }

    public void clickItemsPerPageDropDown(){
        moveClickBtn(itemsPerPageDropDown);
    }

    public void filter100items(){
        WebElement select = getDriver().findElement(By.xpath("//span[contains(text(),'100')]"));
        moveClickBtn(select);
        waitForAngularRequestsToFinish();
    }

    public void clickUpdateStatusButton(){
        WebElement element = getDriver().findElement(By.xpath("//span[contains(text(),'Update status')]"));
//        moveClickBtn(element);
        JavascriptExecutor exec = (JavascriptExecutor) this.getDriver();
        exec.executeScript("arguments[0].click();", element);
        waitForAngularRequestsToFinish();
    }

    public void clickCreateSessionLog() {
        WebElement element = getDriver().findElement(By.xpath("//span[contains(text(),'Create session log')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public void userClicksPastFormLink() {
        WebElement element = getDriver().findElement(By.xpath("//a[@href='/past-forms']"));
        moveClickBtn(element);
    }

    public void clickDateIconFromPastForm() {
        waitForAngularRequestsToFinish();
        WebElement element = getDriver().findElement(By.xpath("//mat-datepicker-toggle[1]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public void selectDateFromDateModal(String date) {
        WebElement element = getDriver().findElement(By.xpath("//td[@aria-label='"+date+"']"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public void clickTakeTheSurvey(int requestIndex) {
        WebElement element = getDriver().findElement(By.xpath("//tr[@style='cursor: pointer;']["+requestIndex+"]//td[6]//div[1]//button[1]"));
        JavascriptExecutor exec = (JavascriptExecutor) this.getDriver();
        exec.executeScript("arguments[0].click();", element);
    }

    public void clickOnCheckIn() {
        waitForAngularRequestsToFinish();
        WebElementFacade element = find(By.xpath("//button[contains(text(),'Check-In')]"));
        if(!element.isEnabled()){
            withTimeoutOf(4, TimeUnit.MINUTES).waitFor(element);
        }
        JavascriptExecutor exec = (JavascriptExecutor) this.getDriver();
        exec.executeScript("arguments[0].click();", element);
        waitForAngularRequestsToFinish();
    }

    public boolean hasTabsLinkInWellnessPage(String role) {
        return find(By.xpath("//a[@href='/new-requests']")).isDisplayed() && find(By.xpath("//a[@href='/past-forms']")).isDisplayed()
        && find(By.xpath("//a[@href='/']")).isDisplayed() && (!role.equalsIgnoreCase("Master Avatar") || find(By.xpath("//span[contains(text(),'Reports')]")).isDisplayed());
    }

    public void clickViewRegistrationBtn() {
        WebElement element = getDriver().findElement(By.xpath("//span[contains(text(),'View registration')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public void userClicksOnTeamMeetingWithTLForm() {
        WebElement element = find(By.xpath("//span[contains(text(),'Go to Team Meeting with TL Form')]"));
        moveClickBtn(element);
//        waitForAngularRequestsToFinish();
    }

    public void userClicksOnIndividualSessionForm() {
        WebElement element = find(By.xpath("//span[contains(text(),'Go to Individual Session Form')]"));
        moveClickBtn(element);
//        waitForAngularRequestsToFinish();
    }

    public void clickDateIconFromWellnessPage() {
        WebElement element = find(By.xpath("//span[@style='font-size: 0.9em;']"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public void selectDateFromDateModalInWellnessPage(String dateAndChangeFormatFromRecentRequest) {
        WebElement element = find(By.xpath("//span[contains(@aria-label,'"+dateAndChangeFormatFromRecentRequest+"')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public void userClicksOnReportingLink() {
        moveClickBtn(reportingLink);
        waitForAngularRequestsToFinish();
    }

    public void userClicksCoachReportLink(String reportName) {
        WebElement element = find(By.xpath("//span[contains(text(),'"+reportName+"')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public void clickCoachNameFilter(){
        WebElement Element = find(By.xpath("//mat-select[@id='mat-select-1']"));
        moveClickBtn(Element);
        waitForAngularRequestsToFinish();
    }
    public void changeCoachNameFilterTo(String coachName) {
        WebElement Element = find(By.xpath("//span[contains(text(),'"+coachName+"')]"));
        moveClickBtn(Element);
        waitForAngularRequestsToFinish();
    }

    public void switchToThisPage() {
        Set<String> handles = getDriver().getWindowHandles();
        List<String> list = new ArrayList<String>(handles);
        getDriver().switchTo().window(list.get(list.size() - 1));
    }

    public void userclickAccountDropDown() {
        WebElement element = find(By.xpath("//mat-icon[contains(text(),'more_vert')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public void userclickslogoutBtn() {
        WebElement element = find(By.xpath("//span[contains(text(),'Sign out')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public void userclicksYes() {
        WebElement element = find(By.xpath("//span[contains(text(),'Yes')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public boolean isCoacheeEIDVisible() {
        List<WebElement> elements = getDriver().findElements(By.xpath("//button[contains(text(),'Coachee EID')]"));
        if(elements.size() > 0){
            return true;
        }
        return false;
    }

    public void clickCoacheeEIDColumnHeader() {
        WebElement element = find(By.xpath("//button[contains(text(),'Coachee EID')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public void clickStatusColumnHeader() {
        WebElement element = find(By.xpath("//button[contains(text(),'Status')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public String fetchRequestRows() {
        return  find(By.xpath("//tr[@role='row'][1]//td[7]")).getText();
    }

    public void userClicksOnGoToLeadershipDepartmentalTrainingLog() {
        WebElement element = find(By.xpath("//span[contains(text(),'Go to Leadership/Departmental Training Log')]"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

    public void userClicksOnGoToDebriefStandUpSkill() {
        moveClickBtn(find(By.xpath("//span[contains(text(),'Go to Debrief/Stand-Up Skill')]")));
    }
}
