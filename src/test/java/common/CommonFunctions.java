package common;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.lang.reflect.InvocationTargetException;

public class CommonFunctions extends PageObject {

    public void moveClickBtn(WebElement element){
        Actions action = new Actions(getDriver());
        action.moveToElement(element);
        action.click();
        try{
            action.perform();
        }catch(Exception ex){
            JavascriptExecutor exec = (JavascriptExecutor) this.getDriver();
            exec.executeScript("arguments[0].click();", element);
        }

    }


}
