package seleniumHelper.methods;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.errorprone.annotations.DoNotCall;

import commonClasses.sharedUtils.managers.LocalDriver;
import commonClasses.sharedUtils.managers.SHelper;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.enums.Condition;
import seleniumHelper.enums.Wait;
import seleniumHelper.interfaces.IWait;

public class ElementCount extends Commands implements IWait {

    @Override
    @DoNotCall
    public void on(String selectorString, String by, int i, String...attribute) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    @DoNotCall
    public void on(WebElement element, int i, String...attribute) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    @DoNotCall
    public void on(String selectorString, String by, Condition condition, String expectedValue, int i,
        String...attribute) throws Exception {
        // TODO Auto-generated method stub 
    }

    @Override
    @DoNotCall
    public void on(WebElement element, Condition condition, String expectedValue, int i, String...attribute)
    throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void on(String selectorString, String by, int expectedTotalCount, int i) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);
            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                	Boolean result = false;
                    try {
                        SHelper.get().page().refresh();
                        SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on(selectorString, by, 30);
                        int actualElementCount = getElements(selectorString, by).size();
                        if (actualElementCount == expectedTotalCount) {
                            result = true;
                        } else {
                            result = false;
                        }
                    } catch (Exception e) {
						// TODO: handle exception
                    }
					return result;
                }
            });
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void on(List < WebElement > element, int expectedTotalCount, int i) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);
            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                	Boolean result = false;
                    try {
                    	//refresh can't live in this method. If refresh is necessary, then a new loop will need to be created that contains the refresh
                    	//The list of web elements needs to be refreshed every time the page is refreshed or this method will not work correctly.
                        //SHelper.get().page().refresh();
                        SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on(element.get(0), 30);
                        int actualElementCount = element.size();
                        if (actualElementCount == expectedTotalCount) {
                            result = true;
                        } else {
                            result = false;
                        }
                    } catch (Exception e) {
                    	
                    }
					return result;
                }
            });
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

}