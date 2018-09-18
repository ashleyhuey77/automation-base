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

public class PresentElementText extends Commands implements IWait  {

	protected int time = 0;
	protected Condition condition;
	protected String value;
	
	public PresentElementText(WaitBuilder base) throws Exception {
		LocalWaitBuilder builder = new LocalWaitBuilder(base);
		this.time = builder.time;
		this.condition = builder.condition;
		this.value = builder.value;
	}

	@Override
	public void on(Locator locator, By by) throws Exception {
		 try {
			 verifyValueIsNotNull(value);
			 verifyMaxWaitTimeIsNotZero(time);
	            switch (condition) {
	                case EQUAL:
	                    waitForElementToEqualText(locator, by, value, time);
	                    break;
	                case CONTAIN:
	                    waitForElementToContainText(locator, by, value, time);
	                    break;
	                default:
	                    throw new Exception("Please provide a valid condition. Unable to wait for text presence because condition has not been provided.");
	            }
	     } catch (Exception e) {
	    	 	throw e;
	     }
	}

	@Override
	public void on(WebElement element) throws Exception {
		 try {
			 verifyValueIsNotNull(value);
			 verifyMaxWaitTimeIsNotZero(time);
	            switch (condition) {
	                case EQUAL:
	                    waitForElementToEqualText(element, value, time);
	                    break;
	                case CONTAIN:
	                    waitForElementToContainText(element, value, time);
	                    break;
	                default:
	                    throw new Exception("Please provide a valid condition. Unable to wait for text presence because condition has not been provided.");
	            }
	     } catch (Exception e) {
	    	 	throw e;
	     }
	}
	
	 /**<summary> method to wait for a particular text to be present in a web element
     * </summary>
		@param locator the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @param expectedText the text that is expected to be in the webelement
     * @param i the total amount of time allotted to wait for the condition to return true
     * @return void
     */
    private void waitForElementToContainText(Locator locator, By by, String expectedText, int i) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                	try {
	                    WebElement elementToBeTested = getElement(locator, by);
	                    String actualText = elementToBeTested.getText();
	                    if (actualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
	                        return true;
	                    } else {
	                        return false;
	                    }
					} catch (Exception e) {
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
		@param locator the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @param expectedText the text that is expected to be in the webelement
     * @param i the total amount of time allotted to wait for the condition to return true
     * @return void
     */
    private void waitForElementToEqualText(Locator locator, By by, String expectedText, int i) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                	try {
	                    WebElement elementToBeTested = getElement(locator, by);
	                    String actualText = elementToBeTested.getText();
	                    if (actualText.toLowerCase().trim().equals(expectedText.toLowerCase().trim())) {
	                        return true;
	                    } else {
	                        return false;
	                    }
                	} catch (Exception e) {
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
		@param locator the webelement selector string necessary for the webelement to be found
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
                	try {
	                    WebElement elementToBeTested = element;
	                    String actualText = elementToBeTested.getText();
	                    if (actualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
	                        return true;
	                    } else {
	                        return false;
	                    }
                	} catch (Exception e) {
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
		@param locator the webelement selector string necessary for the webelement to be found
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
                	try {
	                    WebElement elementToBeTested = element;
	                    String actualText = elementToBeTested.getText();
	                    if (actualText.toLowerCase().trim().equals(expectedText.toLowerCase().trim())) {
	                        return true;
	                    } else {
	                        return false;
	                    }
                	} catch (Exception e) {
                		return false;
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
		
		public LocalWaitBuilder(WaitBuilder base) throws Exception {
			this.time = base.baseTime;
			this.condition = base.baseCondition();
			this.value = base.baseValue;
			failIfAttributeIsNotNull(base.baseAttribute);
			failIfExpectedCountIsNotZero(base.baseExpectedTotalCount);
		}
		
	}

}