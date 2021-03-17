package steps;


import net.thucydides.core.annotations.Step;
import pageobjects.BoostModalPage;
import pageobjects.BoostPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BoostSteps {

    BoostPage boostPage;

    BoostModalPage boostModalPage;

    @Step("User clicks on wellness")
    public void userClicksOnWellness() {
        boostPage.clickWellnessButton();
    }

    @Step("Click request session")
    public void userClickRequestSession(){
        boostPage.clickRequestSessionBtn();
    }

    @Step("Select yogi coach")
    public void userSelectYogiCoach(){
        if(boostModalPage.isCareTypeModalDisplayed()){
            boostModalPage.clickCoachYogiBtn();
        }
    }

}
