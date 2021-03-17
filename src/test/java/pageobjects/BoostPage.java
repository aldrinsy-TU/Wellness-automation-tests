package pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BoostPage extends PageObject{

    @FindBy(xpath = "//a[@href='/wellness']")
    private WebElementFacade wellnessBtn;

    @FindBy(id = "btnRequest")
    private WebElementFacade requestSession;

    public void clickWellnessButton(){
        if(!wellnessBtn.isClickable())
        {
            withTimeoutOf(2, TimeUnit.MINUTES).waitFor(ExpectedConditions.elementToBeClickable(wellnessBtn));
        }
        wellnessBtn.click();
        waitForAngularRequestsToFinish();
    }

    public void clickRequestSessionBtn(){
        if(!requestSession.isClickable()){
            withTimeoutOf(2, TimeUnit.MINUTES).waitFor(ExpectedConditions.elementToBeClickable(requestSession));
        }
        requestSession.click();

    }

    public void switchToThisPage(){
        Set<String> handles = getDriver().getWindowHandles();
        List<String> list = new ArrayList<String>(handles);
        getDriver().switchTo().window(list.get(list.size() - 2));
    }
}
