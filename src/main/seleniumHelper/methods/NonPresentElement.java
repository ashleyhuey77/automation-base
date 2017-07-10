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

public class NonPresentElement extends Commands implements IWait {

	@Override
	public void waitOn(String selectorString, String by, int i, String... attribute) {
		try 
		{
			new WebDriverWait(LocalDriver.getDriver(), i).until(ExpectedConditions.invisibilityOfElementLocated(getByValueBasedOnUserInput(selectorString, by)));
/*			
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(),i);
	
			wait.until(new ExpectedCondition<Boolean>() {
	            	public Boolean apply(WebDriver driver) {
	            				Boolean result = false;
	                         	By byValue = getByValue(selectorString, (by.toLowerCase().contains(id) ? LocatorTypes.ID : 
	                         		(by.toLowerCase().contains(cssSelector)) ? LocatorTypes.CSSSELECTOR : 
	                         			(by.toLowerCase().contains(xPath)) ? LocatorTypes.XPATH : 
	                         				(by.toLowerCase().contains(className)) ? LocatorTypes.CLASSNAME : 
	                         					(by.toLowerCase().contains(tagName)) ? LocatorTypes.TAGNAME : 
	                         						(by.toLowerCase().contains(linkText)) ? LocatorTypes.LINKTEXT : 
	                         							(by.toLowerCase().contains(partialLinkText)) ? LocatorTypes.PARTIALLINKTEXT : 
	                         								(by.toLowerCase().contains(name)) ? LocatorTypes.NAME : LocatorTypes.ID));
	                         	try
	                         	{
	                         		Boolean result1 = LocalDriver.getDriver().findElement(byValue).isDisplayed();
	                         		if(!result1)
	                         		{
	                         			result = true;
	                         			return result;
	                         		}
	                         	}
	                         	catch (Exception ex)
	                         	{
	                         		return result;
	                         	}
								return result;
			   		};
			});*/
		}
		catch (WebDriverException ex)
		{
			throw ex;
		}
	}

	@Override
	public void waitOn(WebElement element, int i, String... attribute) {
    	try
    	{
    		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(),i);

    		wait.until(new ExpectedCondition<Boolean>() {
	            	public Boolean apply(WebDriver driver) {
	            				Boolean result = false;
	                         	try
	                         	{
	                         		if(!element.isDisplayed())
	                         		{
	                         			result = true;
	                         			return result;
	                         		}
	                         	}
	                         	catch (StaleElementReferenceException ex)
	                         	{
	                         		return result;
	                         	}
								return result;
    		   		};
    		});
    	}
    	catch (WebDriverException ex)
    	{
    		throw ex;
    	}
	}

	@Override
	public void waitOn(String selectorString, String by, Condition condition, String expectedValue, int i,
			String... attribute) throws Exception {
		switch(condition) {
			case EQUALS:
				waitForElementToNoLongerEqualText(selectorString, by, expectedValue, i);
				break;
			case CONTAINS:
				waitForElementToNoLongerContainText(selectorString, by, expectedValue, i);
				break;
			default:
				throw new Exception("Please select a valid condition. Unable to execute because condition is not valid.");
		}	
	}

	@Override
	public void waitOn(WebElement element, Condition condition, String expectedValue, int i, String... attribute)
			throws Exception {
		switch(condition) {
			case EQUALS:
				waitForElementToNoLongerEqualText(element, expectedValue, i);
				break;
			case CONTAINS:
				waitForElementToNoLongerContainText(element, expectedValue, i);
				break;
			default:
				throw new Exception("Please select a valid condition. Unable to execute because condition is not valid.");
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
    private void waitForElementToNoLongerContainText(String selectorString, String by, String expectedText, int i)
    {
    	try
    	{
    		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(),i);

    		wait.until(new ExpectedCondition<Boolean>() {
    		            public Boolean apply(WebDriver driver) {
    		                         WebElement elementToBeTested = getElement(selectorString, by);
    		                         String actualText = elementToBeTested.getText();
    		                         if(actualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim()))
    		                         {
    		                        	 return true;
    		                         }
    		                         else
    		                         {
    		                            return false;
    		                         }
    		                    }
    		   });
    	}
    	catch (WebDriverException ex)
    	{
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
    private void waitForElementToNoLongerEqualText(String selectorString, String by, String expectedText, int i)
    {
    	try
    	{
    		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(),i);

    		wait.until(new ExpectedCondition<Boolean>() {
    		            public Boolean apply(WebDriver driver) {
    		                         WebElement elementToBeTested = getElement(selectorString, by);
    		                         String actualText = elementToBeTested.getText();
    		                         if(actualText.toLowerCase().trim().equals(expectedText.toLowerCase().trim()))
    		                         {
    		                        	 return true;
    		                         }
    		                         else
    		                         {
    		                            return false;
    		                         }
    		                    }
    		   });
    	}
    	catch (WebDriverException ex)
    	{
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
    private void waitForElementToNoLongerContainText(WebElement element, String expectedText, int i)
    {
    	try
    	{
    		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(),i);

    		wait.until(new ExpectedCondition<Boolean>() {
    		            public Boolean apply(WebDriver driver) {
    		                         WebElement elementToBeTested = element;
    		                         String actualText = elementToBeTested.getText();
    		                         if(actualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim()))
    		                         {
    		                        	 return true;
    		                         }
    		                         else
    		                         {
    		                            return false;
    		                         }
    		                    }
    		   });
    	}
    	catch (WebDriverException ex)
    	{
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
    private void waitForElementToNoLongerEqualText(WebElement element, String expectedText, int i)
    {
    	try
    	{
    		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(),i);

    		wait.until(new ExpectedCondition<Boolean>() {
    		            public Boolean apply(WebDriver driver) {
    		                         WebElement elementToBeTested = element;
    		                         String actualText = elementToBeTested.getText();
    		                         if(actualText.toLowerCase().trim().equals(expectedText.toLowerCase().trim()))
    		                         {
    		                        	 return true;
    		                         }
    		                         else
    		                         {
    		                            return false;
    		                         }
    		                    }
    		   });
    	}
    	catch (WebDriverException ex)
    	{
    		throw ex;
    	}
    }

	@Override
	@DoNotCall
	public void waitOn(String selectorString, String by, int expectedTotalCount, int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@DoNotCall
	public void waitOn(List<WebElement> element, int expectedTotalCount, int i) {
		// TODO Auto-generated method stub
		
	}

}
