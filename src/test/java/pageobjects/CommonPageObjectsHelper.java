package pageobjects;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import java.time.Duration;

public class CommonPageObjectsHelper extends PageObject {

    @FindBy(xpath = "//div[@class='overlay']")
    private WebElementFacade overLay;

    @FindBy(xpath = "//div[@class='overlay ng-hide']")
    private WebElementFacade overLayHide;


    public void waitForOvelayToBeGone() {
        if(overLay.isPresent() && overLay.isVisible()) {
          withTimeoutOf(Duration.ofMinutes(3)).waitForPresenceOf(By.xpath("//div[@class='overlay ng-hide']"));
        }
    }

}
