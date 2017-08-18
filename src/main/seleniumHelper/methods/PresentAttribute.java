package seleniumHelper.methods;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.errorprone.annotations.DoNotCall;

import commonClasses.sharedUtils.managers.LocalDriver;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.enums.Condition;
import seleniumHelper.interfaces.IWait;

public class PresentAttribute extends Commands implements IWait {

    @Override
    public void waitOn(String selectorString, String by, int i, String...attribute) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);
            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    WebElement elementToBeTested = getElement(selectorString, by);
                    String value = elementToBeTested.getAttribute(attribute[0]);
                    if (value != null && !value.equals("")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void waitOn(WebElement element, int i, String...attribute) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);
            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    WebElement elementToBeTested = element;
                    String value = elementToBeTested.getAttribute(attribute[0]);
                    if (value != null && !value.equals("")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void waitOn(String selectorString, String by, Condition condition, String expectedValue, int i,
        String...attribute) throws Exception {
        try {
            switch (condition) {
                case EQUALS:
                    waitForAttributeToEqualACertainValue(selectorString, by, attribute[0], expectedValue, i);
                    break;
                case CONTAINS:
                    waitForAttributeToContainACertainValue(selectorString, by, attribute[0], expectedValue, i);
                    break;
                default:
                    throw new Exception("Please select a valid condition. Unable to execute because condition is not valid.");
            }
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void waitOn(WebElement element, Condition condition, String expectedValue, int i, String...attribute)
    throws Exception {
        try {
            switch (condition) {
                case EQUALS:
                    waitForAttributeToEqualACertainValue(element, attribute[0], expectedValue, i);
                    break;
                case CONTAINS:
                    waitForAttributeToContainACertainValue(element, attribute[0], expectedValue, i);
                    break;
                default:
                    throw new Exception("Please select a valid condition. Unable to execute because condition is not valid.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**<summary> method to wait for an attribute to equal a certain value
     * </summary>
     * @param element a webelement that is defined and found in the calling method
     * @param attribute the html attribute whose value is to be evaluated and obtained
     * @param expectedValue the expected value of the html attribute
     * @param i the total amount of time allotted to wait for the condition to return true
     * @return void
     */
    private void waitForAttributeToEqualACertainValue(WebElement element, String attribute, String expectedValue, int i) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    WebElement elementToBeTested = element;
                    String actualValue = elementToBeTested.getAttribute(attribute);
                    if (actualValue.toLowerCase().trim().equals(expectedValue.toLowerCase().trim())) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        } catch (WebDriverException ex) {
            throw ex;
        }
    }


    /**
	   * <summary> 
	   * method to wait for an attribute to equal a certain value
	   * </summary>
			@param selectorString the webelement selector string necessary for the webelement to be found
			@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	  				  WebElement to be found
	   * @param attribute the html attribute whose value is to be evaluated and obtained
	   * @param expectedValue the expected value of the html attribute
	   * @param i the total amount of time allotted to wait for the condition to return true
	   * @return void
	   */
    private void waitForAttributeToEqualACertainValue(String selectorString, String by, String attribute, String expectedValue, int i) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    WebElement elementToBeTested = getElement(selectorString, by);
                    String actualValue = elementToBeTested.getAttribute(attribute);
                    if (actualValue.toLowerCase().trim().equals(expectedValue.toLowerCase().trim())) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    /**
	   * <summary> 
	   * method to wait for an attribute to equal a certain value
	   * </summary>
			@param selectorString the webelement selector string necessary for the webelement to be found
			@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	  				  WebElement to be found
	   * @param attribute the html attribute whose value is to be evaluated and obtained
	   * @param expectedValue the expected value of the html attribute
	   * @param i the total amount of time allotted to wait for the condition to return true
	   * @return void
	   */
    private void waitForAttributeToContainACertainValue(String selectorString, String by, String attribute, String expectedValue, int i) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    WebElement elementToBeTested = getElement(selectorString, by);
                    String actualValue = elementToBeTested.getAttribute(attribute);
                    //returning false if attribute is null.
                    if (actualValue != null) {
                        if (actualValue.trim().toLowerCase().contains(expectedValue.toLowerCase().trim())) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            });
        } catch (WebDriverException ex) {
            throw ex;
        }
    }


    /**
	   * <summary> 
	   * method to wait for an attribute to equal a certain value
	   * </summary>
			@param selectorString the webelement selector string necessary for the webelement to be found
			@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	  				  WebElement to be found
	   * @param attribute the html attribute whose value is to be evaluated and obtained
	   * @param expectedValue the expected value of the html attribute
	   * @param i the total amount of time allotted to wait for the condition to return true
	   * @return void
	   */
    private void waitForAttributeToContainACertainValue(WebElement element, String attribute, String expectedValue, int i) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    WebElement elementToBeTested = element;
                    String actualValue = elementToBeTested.getAttribute(attribute);
                    //returning false if attribute is null.
                    if (actualValue != null) {
                        if (actualValue.trim().toLowerCase().contains(expectedValue.toLowerCase().trim())) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            });
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    @DoNotCall
    public void waitOn(String selectorString, String by, int expectedTotalCount, int i) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    @DoNotCall
    public void waitOn(List < WebElement > element, int expectedTotalCount, int i) throws Exception {
        // TODO Auto-generated method stub

    }

}