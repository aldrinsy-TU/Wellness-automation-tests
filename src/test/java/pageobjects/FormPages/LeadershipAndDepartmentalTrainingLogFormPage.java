package pageobjects.FormPages;

import common.FormPageCommonMethods;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class LeadershipAndDepartmentalTrainingLogFormPage extends FormPageCommonMethods {

    //Site comboBox
    @FindBy(xpath = "//mat-select[@id='siteId']")
    private WebElementFacade siteComboBox;

    public LeadershipAndDepartmentalTrainingLogFormPage() {
        super("Leadership/Departmental Training Log");
    }

    public void userClicksOnSiteBtn() {
        moveClickBtn(siteComboBox);
    }

    public void clickPreferredSessionDateDTPicker() {
        moveClickBtn(find(By.xpath("//mat-datepicker-toggle")));
    }

    public void selectDateToday() {
        String DateStr;
        SimpleDateFormat dtFormat = new SimpleDateFormat("MMMMM d, yyyy");
        dtFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        DateStr = dtFormat.format(new Date());

        moveClickBtn(find(By.xpath("//td[@aria-label='"+DateStr+"']")));
    }

    public void clickComboBox(String xpath) {
        moveClickBtn(find(By.xpath(xpath)));
        waitForAngularRequestsToFinish();
    }

    public void selectOptionForComboBox(String option) {
        moveClickBtn(find(By.xpath("//mat-option[@ng-reflect-value='"+option+"']")));
        waitForAngularRequestsToFinish();
    }

    public void insertTextInSubjectTextfield(String sampleText) {
        find(By.xpath("//textarea[@id='mat-input-1']")).sendKeys(sampleText);
    }
}
