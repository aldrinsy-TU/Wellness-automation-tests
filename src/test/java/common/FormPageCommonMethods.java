package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class FormPageCommonMethods extends CommonFunctions {

    WebElement formPageHeader;

    WebElement submitBtn;

    String formName;


    public FormPageCommonMethods(String formName){
        this.formName = formName;
    }

    public void FetchElements(){
        formPageHeader = find(By.xpath("//h3[contains(text(),'"+this.formName+"')]"));
        submitBtn = find(By.xpath("//span[contains(text(),'Submit')]"));
    }

    public boolean isFormPagePageDisplayed(){
        FetchElements();
        return formPageHeader.isDisplayed();
    }

    public void userClicksOnSubmitBtn(){
        submitBtn.click();
        waitForAngularRequestsToFinish();
    }

    public void waitPageToLoad(){
        FetchElements();
        withTimeoutOf(2, TimeUnit.MINUTES).waitFor(ExpectedConditions.visibilityOf(formPageHeader));
    }

    public void switchPageThenWaitToLoad(){
        getDriver().navigate().refresh();
        FetchElements();
        Set<String> handles = getDriver().getWindowHandles();
        List<String> list = new ArrayList<String>(handles);
        getDriver().switchTo().window(list.get(list.size() - 1));
        withTimeoutOf(2, TimeUnit.MINUTES).waitFor(ExpectedConditions.visibilityOf(formPageHeader));
    }

    public boolean verifyReponseIsSubmitted() {
        waitForAngularRequestsToFinish();
        WebElement element = find(By.xpath("//mat-card-title[contains(text(),'Response submitted')]"));
        return element.isDisplayed();
    }

    public void switchToThisPage(){
        Set<String> handles = getDriver().getWindowHandles();
        List<String> list = new ArrayList<String>(handles);
        getDriver().switchTo().window(list.get(list.size() - 2));
    }
}
