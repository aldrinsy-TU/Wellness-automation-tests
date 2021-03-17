package steps;

import net.thucydides.core.annotations.Step;
import pageobjects.InvalidTaggingModalPage;

public class InvalidTaggingModalSteps {

    InvalidTaggingModalPage invalidTaggingModalPage;

    @Step("Check that deleted activity is in the invalid tagging list")
    public void checkThatDeletedActivityIsInInvalidTaggingList(String deletedActivityDtls){
        assert invalidTaggingModalPage.verifyAnActivityIsInTheInvalidTaggingList(deletedActivityDtls);
    }

    @Step("Click the X button")
    public void clickTheCloseBtn(){
        invalidTaggingModalPage.clickCloseBtn();
    }

    @Step("Click on an action button")
    public void clickOnAnActionBtn(){
        invalidTaggingModalPage.clickOnActionBtn();
    }
}
