package seleniumHelper.abstracts;

import java.util.List;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import common.utils.TestUtils;
import common.utils.managers.LocalDriver;
import seleniumHelper.enums.Condition;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;
import seleniumHelper.valueObjects.TestElement;

public class Commands {

    /**	
     *	<summary>
     *	method to get the by value based on user input
     *	</summary>
     *	@return By
     *	@param selectorString the webelement selector string necessary for the webelement to be found
     *	@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
     *			  WebElement to be found
     */
    public org.openqa.selenium.By getByValue(TestElement element) {
        try {
            switch (element.by.value()) {
                case ID:
                    return org.openqa.selenium.By.id(element.locator.value());
                case CSS:
                    return org.openqa.selenium.By.cssSelector(element.locator.value());
                case XPATH:
                    return org.openqa.selenium.By.xpath(element.locator.value());
                case CLASS_NAME:
                    return org.openqa.selenium.By.className(element.locator.value());
                case TAG_NAME:
                    return org.openqa.selenium.By.tagName(element.locator.value());
                case LINK_TEXT:
                    return org.openqa.selenium.By.linkText(element.locator.value());
                case PARTIAL_LINK_TEXT:
                    return org.openqa.selenium.By.partialLinkText(element.locator.value());
                case NAME:
                    return org.openqa.selenium.By.name(element.locator.value());
                default:
                    return null;

            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**	
     *	<summary>
     *	Method to call the by switch statement and return the correct by value
     *	based on user input
     *	</summary>
     *	@return By
     *	@param selectorString the webelement selector string necessary for the webelement to be found
     *	@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
     *			  WebElement to be found
     */
    protected org.openqa.selenium.By getByValueBasedOnUserInput(Locator locator, By by) {
        try {
        		TestElement element = new TestElement(locator, by);
            return getByValue(element);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /** 
     * <summary>
     	Element that is being defined based on the html string parameter 
     	and the By value that indicates which type of element is being defined
     	</summary>
     * @param selectorString the webelement selector string necessary for the webelement to be found
     * @param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				 WebElement to be found
     * @return WebElement
     */
    public WebElement getElement(Locator locator, By by) {
        try {
            return LocalDriver.getDriver().findElement(getByValueBasedOnUserInput(locator, by));
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    /**
     * <summary> 
     * method to return a list of webElements
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @return List<\WebElement>
     */
    public List < WebElement > getElements(Locator locator, By by) {
        try {
            return LocalDriver.getDriver().findElements(getByValueBasedOnUserInput(locator, by));
        } catch (Exception ex) {
            throw ex;
        }
    }
    
	protected void verifyMaxWaitTimeIsNotZero(int time) throws Exception {
		try {
			if (time == 0) {
				throw new Exception("A max wait time was not provided. When waiting, a max wait time must be provided.");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	protected void verifyExpectedCountIsNotZero(int expectedTotalCount) throws Exception {
		try {
			if (expectedTotalCount == 0) {
				throw new Exception("An expected total count was not provided. When waiting for the total expected count "
						+ "to be a certain number, that expected count must be provided.");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	protected void verifyValueIsNotNull(String value) throws Exception {
		try {
			if (TestUtils.isNullOrBlank(value)) {
				throw new Exception("The text value was not provided. If waiting on a text value to be present, that text value must be provided.");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	protected void verifyAttributeIsNotNull(String attribute) throws Exception {
		try {
			if (TestUtils.isNullOrBlank(attribute)) {
				throw new Exception("The attribute was not provided. If waiting on an attribute value to be present, that attribute value must be provided.");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	protected void failIfValueIsNotNull(String value) throws Exception {
		try {
			if (!TestUtils.isNullOrBlank(value)) {
				throw new Exception("This method's Wait enum is set to a value that does not contain any text validation. "
						+ "Please check the Wait enum and verify it is set to the correct value or remove the value() method from the wait chain.");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	protected void failIfConditionIsNotNull(Condition condition) throws Exception {
		try {
			if (condition != null) {
				throw new Exception("This method's Wait enum is set to a value that does not contain any condition validation. "
						+ "Please check the Wait enum and verify it is set to the correct value or remove the to() method from the wait chain.");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	protected void failIfExpectedCountIsNotZero(int expectedTotalCount) throws Exception {
		try {
			if (expectedTotalCount != 0) {
				throw new Exception("This method's Wait enum is set to a value that does not require an expected count validation. "
						+ "Please check the Wait enum and verify it is set to the correct value or remove the withACountOf() method from the wait chain.");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	protected void failIfAttributeIsNotNull(String attribute) throws Exception {
		try {
			if (!TestUtils.isNullOrBlank(attribute)) {
				throw new Exception("This method's Wait enum is set to a value that does not require an attribute validation. "
						+ "Please check the Wait enum and verify it is set to the correct value or remove the forAttribute() method from the wait chain.");
			}
		} catch (Exception e) {
			throw e;
		}
	}
}