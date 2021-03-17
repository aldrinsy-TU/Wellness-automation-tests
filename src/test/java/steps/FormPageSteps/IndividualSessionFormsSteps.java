package steps.FormPageSteps;


import org.junit.Assert;
import pageobjects.FormPages.WellnessIndividualSessionFormPage;

public class IndividualSessionFormsSteps {

    WellnessIndividualSessionFormPage wellnessIndividualSessionFormPage;

    public void waitPageToLoad() {
        wellnessIndividualSessionFormPage.switchPageThenWaitToLoad();
    }

    public void clickSessionNumberComboBox() {
        wellnessIndividualSessionFormPage.clickSessionNumberComboBox();
    }

    public void selectItemInSessionNumberComboBox(String selection) {
        wellnessIndividualSessionFormPage.selectItemInSessionNumberComboBox(selection);
    }

    public void selectItemAspectComboBox(String selection) {
        wellnessIndividualSessionFormPage.selectItemAspectComboBox(selection);
    }

    public void clickAspectComboBox() {
        wellnessIndividualSessionFormPage.clickAspectComboBox();
    }

    public void insertTextToActionItems(String test) {
        wellnessIndividualSessionFormPage.insertTextToActionItems(test);
    }

    public void insertTextToActionItemsDueDate(String s) {
        wellnessIndividualSessionFormPage.insertTextToActionItemsDueDate(s);
    }

    public void clickEmergentConcernsCheckBox(String s) {
        wellnessIndividualSessionFormPage.clickEmergentConcernsCheckBox(s);
    }

    public void validateFormRequest() {
        Assert.assertTrue(wellnessIndividualSessionFormPage.validateSessionRequest());
    }

    public void clickSubmitBtn() {
        wellnessIndividualSessionFormPage.userClicksOnSubmitBtn();
    }
}
