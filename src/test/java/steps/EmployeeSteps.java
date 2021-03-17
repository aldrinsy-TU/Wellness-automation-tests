package steps;

import common.APIHelper;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static net.thucydides.core.pages.components.HtmlTable.rowsFrom;

public class EmployeeSteps {

    APIHelper apiHelper = new APIHelper();
    private EnvironmentVariables environmentVariables;
    String selectedEmpFullName;
    String selectedEmpEmployeeId;


    public String getSelectedEmpEmployeeId() {
        return selectedEmpEmployeeId;
    }

}
