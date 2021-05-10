package pageobjects;

import com.paulhammant.ngwebdriver.ByAngular;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.util.EnvironmentVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends PageObject{

    private EnvironmentVariables environmentVariables;

    @FindBy(xpath = "//input[@placeholder='Enter Email Address']")
    private WebElementFacade enterEmailAddressInputBox;



     public void enterUserName(String username){
         if(find(ByAngular.model("$ctrl.username")).isCurrentlyVisible())
             find(ByAngular.model("$ctrl.username")).sendKeys(username);
     }

    public void enterPassword(String password){
        if(find(ByAngular.model("$ctrl.password")).isCurrentlyVisible())
            find(ByAngular.model("$ctrl.password")).sendKeys(password);
    }


    public void clickSignInBtn(){
        waitForSignInBtnToBeClickable();
        find(ByAngular.buttonText("Sign in")).click();
        try {
            waitForAngularRequestsToFinish();
        }catch (ScriptTimeoutException ste){
            this.open();
        }
}

    private void waitForSignInBtnToBeClickable() {
       WebElementFacade signInBtn = find(ByAngular.buttonText("Sign in"));
        waitForCondition().until(
                ExpectedConditions.elementToBeClickable(signInBtn)
        );
    }

    public void enterEmail(String email) {
        if(find(By.name("email")).isCurrentlyVisible())
           find(By.name("email")).sendKeys(email);
    }

    public void enterEmailAddress(String email) {
        enterEmailAddressInputBox.sendKeys(email);
        // find(By.name("email")).sendKeys(email);
    }

//    public void enterEmail(String email){
//        //if(find(By.xpath("//div[@class='input-group']/input")).isCurrentlyVisible())
//        find(By.name("email")).sendKeys(email);
//            //find(By.name("//div[@class='input-group']/input")).sendKeys(email);
//    }

    public void clickGenerateOtpLoginBtn(){
        if(find(By.cssSelector("#otpLogin")).isCurrentlyVisible())
            find(By.cssSelector("#otpLogin")).click();
         waitForAngularRequestsToFinish();
    }

    public void enterOtp(String otp){
        if(find(ByAngular.model("$ctrl.otp")).isCurrentlyVisible())
            find(ByAngular.model("$ctrl.otp")).sendKeys(otp);
    }

    public void clickOtpLoginBtn(){
        if(find(By.xpath("(//button[contains(@class, 'btn-lg')])[2]")).isCurrentlyVisible())
            find(By.xpath("(//button[contains(@class, 'btn-lg')])[2]")).click();
        waitForAngularRequestsToFinish();
    }

    public void enterTestLogin(String loginCreds) {
        if(find(By.xpath("//input[@placeholder='EmployeeNo']")).isCurrentlyVisible())
            find(By.xpath("//input[@placeholder='EmployeeNo']")).sendKeys(loginCreds);
    }

    public void clicktestLoginBtn(){
        if(find(By.xpath("//Button[contains(text(),'Test Login')]")).isCurrentlyVisible())
            find(By.xpath("//Button[contains(text(),'Test Login')]")).click();
        waitForAngularRequestsToFinish();
    }

    public void enterWellnessTestLogin(String loginCreds,String role) {
        if("Test clinician".equalsIgnoreCase(role)) {
        if(find(By.xpath("//input[@id='mat-input-1']")).isCurrentlyVisible())
            find(By.xpath("//input[@id='mat-input-1']")).sendKeys(loginCreds);
        }
        else{
        if(find(By.xpath("//input[@id='mat-input-0']")).isCurrentlyVisible())
            find(By.xpath("//input[@id='mat-input-0']")).sendKeys(loginCreds);
        }
    }

    public void clicktestLoginBtn(String role){
        WebElement wellnessEmployeeLoginBtn = getDriver().findElements(By.xpath("//button[@class='app-button-signin mat-raised-button mat-button-base']")).get(0);
        WebElement wellnessClinicianLoginBtn = getDriver().findElements(By.xpath("//button[@class='app-button-signin mat-raised-button mat-button-base']")).get(1);

        if("Test clinician".equalsIgnoreCase(role)){
            if(wellnessClinicianLoginBtn.isDisplayed())
                wellnessClinicianLoginBtn.click();
        }
        else{
            if(wellnessEmployeeLoginBtn.isDisplayed())
                wellnessEmployeeLoginBtn.click();
        }
        
    }
}
