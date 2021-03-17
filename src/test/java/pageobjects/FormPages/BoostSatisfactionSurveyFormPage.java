package pageobjects.FormPages;

import common.CommonFunctions;
import common.FormPageCommonMethods;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BoostSatisfactionSurveyFormPage extends FormPageCommonMethods {

    public BoostSatisfactionSurveyFormPage() {
        super("Satisfaction Survey Form");
    }

    public boolean isSatisfactionSurveyFormPageDisplayed(){
        return isFormPagePageDisplayed();
    }

    public void clickSatisfactionOnSurveyQuestion(String Satisfactory,int QuestionNum) {
        int index = 0;
        if(QuestionNum == 1){
            index = 65;
        }else{
            index = 65 + (5 * QuestionNum);
        }
        index = index + Integer.parseInt(Satisfactory);
        WebElement element = getDriver().findElement(By.xpath("//label[@for='radiohorizontal_option_"+index+"']"));
        moveClickBtn(element);
        waitForAngularRequestsToFinish();
    }

}
