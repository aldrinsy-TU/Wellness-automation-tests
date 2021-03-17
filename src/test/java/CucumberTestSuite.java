import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty",
                "rerun:reports/rerun.txt"},
        features = "src/test/resources/features",
        glue = "stepdefinitions"
)
public class CucumberTestSuite {}
