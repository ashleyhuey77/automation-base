package seleniumHelper.methods;

import java.util.List;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.errorprone.annotations.DoNotCall;

import commonClasses.sharedUtils.managers.LocalDriver;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.interfaces.IElement;

public class Element extends Commands implements IElement {

    @Override
    public WebElement get(String selectorString, String by) throws Exception {
        return getElement(selectorString, by);
    }

    @Override
    public List < WebElement > getListOf(String selectorString, String by) throws Exception {
        return getElements(selectorString, by);
    }

    @Override
    public Boolean isDisplayed(String selectorString, String by, int i) throws Exception {
        try {
            new WebDriverWait(LocalDriver.getDriver(), i).until(ExpectedConditions.presenceOfElementLocated(getByValueBasedOnUserInput(selectorString, by)));
            return true;
        } catch (WebDriverException ex) {
            return false;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    @DoNotCall
    public Boolean isDisplayed(WebElement element, int i) throws Exception {
        try {
            //waitForPresenceOfElementLocated(element, i);
            return true;
        } catch (WebDriverException ex) {
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public WebElement find(WebElement firstElement, String selectorString, String by) throws Exception {
        try {
            return firstElement.findElement(getByValueBasedOnUserInput(selectorString, by));
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public WebElement find(String firstESelectorString, String firstEBy, String selectorString, String by) throws Exception {
        try {
            return getElement(firstESelectorString, firstEBy).findElement(getByValueBasedOnUserInput(selectorString, by));
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public List < WebElement > findListOf(WebElement firstElement, String selectorString, String by) throws Exception {
        try {
            return firstElement.findElements(getByValueBasedOnUserInput(selectorString, by));
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public List < WebElement > findListOf(String firstESelectorString, String firstEBy, String selectorString, String by) throws Exception {
        try {
            return getElement(firstESelectorString, firstEBy).findElements(getByValueBasedOnUserInput(selectorString, by));
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public Boolean isAttributePresent(WebElement element, String attribute) throws Exception {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null) {
                result = true;
            }
        } catch (Exception e) {
            return result;
        }

        return result;
    }

    @Override
    public Boolean isAttributePresent(String selectorString, String by, String attribute) throws Exception {
        Boolean result = false;
        try {
            String value = getElement(selectorString, by).getAttribute(attribute);
            if (value != null) {
                result = true;
            }
        } catch (Exception e) {
            return result;
        }

        return result;
    }

    @Override
    public Boolean isEnabled(String selectorString, String by) throws Exception {
        try {
            Boolean result = false;
            WebElement element = getElement(selectorString, by);
            if (element.isEnabled()) {
                result = true;
            }
            return result;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Boolean isEnabled(WebElement element) throws Exception {
        try {
            Boolean result = false;
            WebElement webElement = element;
            if (webElement.isEnabled()) {
                result = true;
            }
            return result;
        } catch (Exception ex) {
            throw ex;
        }
    }

}