package seleniumHelper.methods;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import seleniumHelper.abstracts.Commands;
import seleniumHelper.interfaces.IText;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;

public class AttributeText extends Commands implements IText {

	/**
	 * <p>This is  a test to see how this works.</p>
	 * @param locator - test
	 * @param by - test
	 * @param attribute - test
	 */
    @Override
    public String getFrom(Locator locator, By by, String...attribute) throws Exception {
        return getElement(locator, by).getAttribute(attribute[0]);
    }

    @Override
    public String getFrom(WebElement element, String...attribute) throws Exception {
        return element.getAttribute(attribute[0]);
    }

    @Override
    public Boolean isDisplayed(Locator locator, By by, String expectedText, String...attribute) throws Exception {
        Boolean result = false;
        try {
            String value = getElement(locator, by).getAttribute(attribute[0]);
            if (value != null) {
                if (value.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
                    result = true;
                }
            }
        } catch (WebDriverException ex) {
            throw ex;
        }
        return result;
    }

    @Override
    public Boolean isDisplayed(WebElement element, String expectedText, String...attribute) throws Exception {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute[0]);
            if (value != null) {
                if (value.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
                    result = true;
                }
            }
        } catch (WebDriverException ex) {
            throw ex;
        }
        return result;
    }

}