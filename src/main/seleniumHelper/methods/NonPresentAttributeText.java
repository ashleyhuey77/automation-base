package seleniumHelper.methods;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.utils.managers.LocalDriver;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.builders.WaitBuilder;
import seleniumHelper.enums.Condition;
import seleniumHelper.interfaces.IWait;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;

public class NonPresentAttributeText extends Commands implements IWait {
	
	protected int time = 0;
	protected Condition condition;
	protected String value;
	protected String attribute;
	
	public NonPresentAttributeText(WaitBuilder build) throws Exception {
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
		this.condition = builder.condition;
		this.value = builder.value;
		this.attribute = builder.attribute;
	}

    @Override
    public void on(Locator locator, By by) throws Exception {
        try {
			verifyAttributeIsNotNull(attribute);
			verifyMaxWaitTimeIsNotZero(time);
			verifyValueIsNotNull(value);
            switch (condition) {
                case EQUAL:
                    waitForAttributeToNoLongerEqualACertainValue(locator, by, attribute, value, time);
                    break;
                case CONTAIN:
                    waitForAttributeToNoLongerContainACertainValue(locator, by, attribute, value, time);
                    break;
                default:
                    throw new Exception("Please select a valid condition. Unable to execute because condition is not valid.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void on(WebElement element) throws Exception {
        try {
			verifyAttributeIsNotNull(attribute);
			verifyMaxWaitTimeIsNotZero(time);
			verifyValueIsNotNull(value);
            switch (condition) {
                case EQUAL:
                    waitForAttributeToNoLongerEqualACertainValue(element, attribute, value, time);
                    break;
                case CONTAIN:
                    waitForAttributeToNoLongerContainACertainValue(element, attribute, value, time);
                    break;
                default:
                    throw new Exception("Please select a valid condition. Unable to execute because condition is not valid.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * <summary> 
     * method to wait for an attribute to equal a certain value
     * </summary>
  		@param locator the webelement selector string necessary for the webelement to be found
  		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
    				  WebElement to be found
     * @param attribute the html attribute whose value is to be evaluated and obtained
     * @param expectedValue the expected value of the html attribute
     * @param i the total amount of time allotted to wait for the condition to return true
     * @return void
     */
    private void waitForAttributeToNoLongerContainACertainValue(Locator locator, By by, String attribute, String expectedValue, int i) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    WebElement elementToBeTested = getElement(locator, by);
                    String actualValue = elementToBeTested.getAttribute(attribute);
                    //returning true if attribute is null because it still means the attribute does not contain the desired value.
                    if (actualValue != null) {
                        if (!actualValue.trim().toLowerCase().contains(expectedValue.toLowerCase().trim())) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return true;
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
    		@param locator the webelement selector string necessary for the webelement to be found
    		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
      				  WebElement to be found
       * @param attribute the html attribute whose value is to be evaluated and obtained
       * @param expectedValue the expected value of the html attribute
       * @param i the total amount of time allotted to wait for the condition to return true
       * @return void
       */
    private void waitForAttributeToNoLongerContainACertainValue(WebElement element, String attribute, String expectedValue, int i) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    WebElement elementToBeTested = element;
                    String actualValue = elementToBeTested.getAttribute(attribute);
                    //returning true if attribute is null because it still means the attribute does not contain the desired value.
                    if (actualValue != null) {
                        if (!actualValue.trim().toLowerCase().contains(expectedValue.toLowerCase().trim())) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return true;
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
      		@param locator the webelement selector string necessary for the webelement to be found
      		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
        				  WebElement to be found
         * @param attribute the html attribute whose value is to be evaluated and obtained
         * @param expectedValue the expected value of the html attribute
         * @param i the total amount of time allotted to wait for the condition to return true
         * @return void
         */
    private void waitForAttributeToNoLongerEqualACertainValue(Locator locator, By by, String attribute, String expectedValue, int i) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    WebElement elementToBeTested = getElement(locator, by);
                    String actualValue = elementToBeTested.getAttribute(attribute);
                    //returning true if attribute is null because it still means the attribute does not contain the desired value.
                    if (actualValue != null) {
                        if (!actualValue.trim().toLowerCase().equals(expectedValue.toLowerCase().trim())) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return true;
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
        		@param locator the webelement selector string necessary for the webelement to be found
        		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
          				  WebElement to be found
           * @param attribute the html attribute whose value is to be evaluated and obtained
           * @param expectedValue the expected value of the html attribute
           * @param i the total amount of time allotted to wait for the condition to return true
           * @return void
           */
    private void waitForAttributeToNoLongerEqualACertainValue(WebElement element, String attribute, String expectedValue, int i) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    WebElement elementToBeTested = element;
                    String actualValue = elementToBeTested.getAttribute(attribute);
                    //returning true if attribute is null because it still means the attribute does not contain the desired value.
                    if (actualValue != null) {
                        if (!actualValue.trim().toLowerCase().equals(expectedValue.toLowerCase().trim())) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return true;
                    }
                }
            });
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

	@Override
	public void on(List<WebElement> element) throws Exception {
	}
	
	public static class LocalWaitBuilder extends Commands {
		private int time;
		private Condition condition;
		private String value;
		private String attribute;
		
		public LocalWaitBuilder(WaitBuilder base) throws Exception {
			this.time = base.baseTime;
			this.condition = base.baseCondition();
			this.value = base.baseValue;
			this.attribute = base.baseAttribute;
			failIfExpectedCountIsNotZero(base.baseExpectedTotalCount);
		}
		
	}

}
