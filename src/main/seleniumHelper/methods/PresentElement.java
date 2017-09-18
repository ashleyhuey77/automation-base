package seleniumHelper.methods;

import java.util.List;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.errorprone.annotations.DoNotCall;

import commonClasses.sharedUtils.managers.LocalDriver;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.enums.Condition;
import seleniumHelper.interfaces.IWait;

public class PresentElement extends Commands implements IWait {

    @Override
    public void on(String selectorString, String by, int i, String...attribute) throws Exception {
        try {
            new WebDriverWait(LocalDriver.getDriver(), i).until(ExpectedConditions.visibilityOfElementLocated(getByValueBasedOnUserInput(selectorString, by)));
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void on(WebElement element, int i, String...attribute) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    Boolean result = false;
                    try {
                        if (element.isDisplayed()) {
                            result = true;
                            return result;
                        }
                    } catch (StaleElementReferenceException ex) {
                        return result;
                    }
                    return result;
                };
            });
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void on(String selectorString, String by, Condition condition, String expectedValue, int i,
        String...attribute) throws Exception {
        try {
            switch (condition) {
                case EQUALS:
                    waitForElementToEqualText(selectorString, by, expectedValue, i);
                    break;
                case CONTAINS:
                    waitForElementToContainText(selectorString, by, expectedValue, i);
                    break;
                default:
                    throw new Exception("Please select a valid condition. Unable to execute because condition is not valid.");
            }
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void on(WebElement element, Condition condition, String expectedValue, int i, String...attribute) throws Exception {
        try {
            switch (condition) {
                case EQUALS:
                    waitForElementToEqualText(element, expectedValue, i);
                    break;
                case CONTAINS:
                    waitForElementToContainText(element, expectedValue, i);
                    break;
                default:
                    throw new Exception("Please select a valid condition. Unable to execute because condition is not valid.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**<summary> method to wait for a particular text to be present in a web element
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @param expectedText the text that is expected to be in the webelement
     * @param i the total amount of time allotted to wait for the condition to return true
     * @return void
     */
    private void waitForElementToContainText(String selectorString, String by, String expectedText, int i) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    WebElement elementToBeTested = getElement(selectorString, by);
                    String actualText = elementToBeTested.getText();
                    if (actualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
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

    /**<summary> method to wait for a particular text to be present in a web element
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @param expectedText the text that is expected to be in the webelement
     * @param i the total amount of time allotted to wait for the condition to return true
     * @return void
     */
    private void waitForElementToEqualText(String selectorString, String by, String expectedText, int i) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    WebElement elementToBeTested = getElement(selectorString, by);
                    String actualText = elementToBeTested.getText();
                    if (actualText.toLowerCase().trim().equals(expectedText.toLowerCase().trim())) {
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

    /**<summary> method to wait for a particular text to be present in a web element
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @param expectedText the text that is expected to be in the webelement
     * @param i the total amount of time allotted to wait for the condition to return true
     * @return void
     */
    private void waitForElementToContainText(WebElement element, String expectedText, int i) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    WebElement elementToBeTested = element;
                    String actualText = elementToBeTested.getText();
                    if (actualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
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

    /**<summary> method to wait for a particular text to be present in a web element
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @param expectedText the text that is expected to be in the webelement
     * @param i the total amount of time allotted to wait for the condition to return true
     * @return void
     */
    private void waitForElementToEqualText(WebElement element, String expectedText, int i) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    WebElement elementToBeTested = element;
                    String actualText = elementToBeTested.getText();
                    if (actualText.toLowerCase().trim().equals(expectedText.toLowerCase().trim())) {
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
    @DoNotCall
    public void on(String selectorString, String by, int expectedTotalCount, int i) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    @DoNotCall
    public void on(List < WebElement > element, int expectedTotalCount, int i) throws Exception {
        // TODO Auto-generated method stub

    }

}