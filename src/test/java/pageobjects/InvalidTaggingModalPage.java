package pageobjects;

import common.TimeCalendar;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;

import java.util.List;

public class InvalidTaggingModalPage extends PageObject {

    @FindBy(css="#invalid-tagging")
    public WebElementFacade invalidTaggingModal;

    @FindAll({ @org.openqa.selenium.support.FindBy(css="tr.odd"),})
    List<WebElement> invalidTaggingLists;

    @FindBy(css="button.close")
    private WebElementFacade closeBtn;

    @FindBy(xpath="//button[contains(@class,'btn-primary')]")
    private WebElementFacade actionBtn;

   public boolean verifyAnActivityIsInTheInvalidTaggingList(String deletedActivity){
        System.out.println("deletedActivity: [" + deletedActivity + "]");
        String rowText;
        boolean found = false;
        for (WebElement invalidTaggingList : invalidTaggingLists) {
            rowText = invalidTaggingList.getText();
            System.out.println("rowText: [" + rowText + "]");
            if (rowText.contains(deletedActivity)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public void clickCloseBtn(){
        closeBtn.click();
    }

    public int getInvalidTaggingListRowCount(){
        int cnt = invalidTaggingLists.size();
        if(cnt == 1){
            String rowText = invalidTaggingLists.get(0).getText();
            if(rowText.equalsIgnoreCase("No data available in table")){
                cnt = 0;
            }
        }
        return cnt;
    }

    public void getTextToCompareWithInvalidTaggingListGivenActivityType(String activityType,
            String defaultDateDisplayedOnStartTimeFld, String defaultDateDisplayedOnEndTimeFld){

        String txtToCompare = "";
        if(activityType.equalsIgnoreCase("No Start Shift")){
            String endtime = defaultDateDisplayedOnStartTimeFld;
            String starttime = TimeCalendar.getDateOneHourBeforeGiveDateInyyyyMMddhhmmssaFormat(endtime);
            txtToCompare = activityType + " " + starttime + " " + endtime;
       }
        if(activityType.equalsIgnoreCase("No End Shift")){
            txtToCompare = activityType + " " + defaultDateDisplayedOnEndTimeFld;
         }
        assert verifyAnActivityIsInTheInvalidTaggingList(txtToCompare);

    }

    public void clickOnActionBtn(){
      actionBtn.click();
      waitForAngularRequestsToFinish();
    }

}
