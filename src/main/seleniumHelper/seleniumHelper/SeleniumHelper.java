package seleniumHelper.seleniumHelper;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import reporting.framework.utilities.FrameworkException;

public class SeleniumHelper {

	public WebDriver browser;

    //defining the different webelement strings to use when calling switch statements
    private String cssSelector = "cssselector";
    private String id = "id";
    private String xPath = "xpath";
    private String className = "classname";
    private String tagName = "tagname";
    private String linkText = "linktext";
    private String partialLinkText = "partiallinktext";
    private String name = "name";

	
	/**	
  		<summary>
		defining the different webelement strings to use when defining switch statements
		</summary>
	*/
    private enum LocatorTypes
    {
        ID,
        CSSSELECTOR,
        XPATH,
        CLASSNAME,
        TAGNAME,
        LINKTEXT,
        PARTIALLINKTEXT,
        NAME
    }

	/**	
  		<summary>
		method to get the by value based on user input
		</summary>
		@return By
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
				  WebElement to be found
	*/
    private By getByValue(String selectorString, LocatorTypes by)
    {
    	try
    	{
    		switch(by)
    		{
    			case ID:
    				return By.id(selectorString);
    			case CSSSELECTOR:
    				return By.cssSelector(selectorString);
    			case XPATH:
    				return By.xpath(selectorString);
    			case CLASSNAME:
    				return By.className(selectorString);
	    		case TAGNAME:
	    			return By.tagName(selectorString);
	    		case LINKTEXT:
	    			return By.linkText(selectorString);
	    		case PARTIALLINKTEXT:
	    			return By.partialLinkText(selectorString);
	    		case NAME:
	    			return By.name(selectorString);
	    		default:
	    			return null;
	    			
	    	}
    	}
    	catch (WebDriverException ex)
    	{
    		throw ex;
    	}
    }
    
	/**	
  		<summary>
		Method to call the by switch statement and return the correct by value
		based on user input
		</summary>
		@return By
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
				  WebElement to be found
	*/
    private By getByValueBasedOnUserInput(String selectorString, String by)
    {
    	try
    	{
    		return getByValue(selectorString, (by.toLowerCase().contains(id) ? LocatorTypes.ID : 
    				(by.toLowerCase().contains(cssSelector)) ? LocatorTypes.CSSSELECTOR : 
    				(by.toLowerCase().contains(xPath)) ? LocatorTypes.XPATH : 
    				(by.toLowerCase().contains(className)) ? LocatorTypes.CLASSNAME : 
    				(by.toLowerCase().contains(tagName)) ? LocatorTypes.TAGNAME : 
    				(by.toLowerCase().contains(linkText)) ? LocatorTypes.LINKTEXT : 
    				(by.toLowerCase().contains(partialLinkText)) ? LocatorTypes.PARTIALLINKTEXT : 
    				(by.toLowerCase().contains(name)) ? LocatorTypes.NAME : LocatorTypes.ID));
    	}
    	catch (WebDriverException ex)
    	{
    		throw ex;
    	}
    }

    /** <summary>
     	Method to get text from a textbox via javascript based on
     	webelement type
     	</summary>
     	* @param selectorString the webelement selector string necessary for the webelement to be found
     	* @param index the index used to identify a particular element in a list of elements tat contain
     	* 				more than one element with a similar selector
     	* @param requiresIndex boolean value to toggle whether the index is necessary. True uses an index and index
     	* 				       cannot be blank or null. False does not use an index and index can be blank or null.
     	* @param webElements the locator type used to identify webelements on the page. Based on the values in the
     	* 					LocatorTypes enum.
     	* @return
     */
    private String getTxtBoxValueViaJavascriptScript(String selectorString, String index, Boolean requiresIndex, LocatorTypes webElements)
    {
        String textBoxText = null;
        switch (webElements)
        {
            case ID:
            	if (requiresIndex)
            	{
            		textBoxText = (String)((JavascriptExecutor)browser).executeScript("return document.getElementById('" + selectorString + "')[" + index + "].value"); 
            	}
            	else
            	{
            		textBoxText = (String)((JavascriptExecutor)browser).executeScript("return document.getElementById('" + selectorString + "').value");
            	}
                return textBoxText;
            case CLASSNAME:
            	if (requiresIndex)
            	{
            		textBoxText = (String)((JavascriptExecutor)browser).executeScript("return document.getElementsByClassName('" + selectorString + "')[" + index + "].value");
            	}
            	else
            	{
            		textBoxText = (String)((JavascriptExecutor)browser).executeScript("return document.getElementsByClassName('" + selectorString + "').value");
            	}
                return textBoxText;
            case NAME:
            	if (requiresIndex)
            	{
            		textBoxText = (String)((JavascriptExecutor)browser).executeScript("return document.getElementsByName('" + selectorString + "')[" + index + "].value");
            	}
            	else
            	{
            		textBoxText = (String)((JavascriptExecutor)browser).executeScript("return document.getElementsByName('" + selectorString + "').value");
            	}
                return textBoxText;
            case TAGNAME:
            	if (requiresIndex)
            	{
            		textBoxText = (String)((JavascriptExecutor)browser).executeScript("return document.getElementsByTagName('" + selectorString + "')[" + index + "].value");
            	}
            	else
            	{
            		textBoxText = (String)((JavascriptExecutor)browser).executeScript("return document.getElementsByTagName('" + selectorString + "').value");
            	}
            	return textBoxText;
            case CSSSELECTOR:
            	if (requiresIndex)
            	{
            		textBoxText = (String)((JavascriptExecutor)browser).executeScript("return document.querySelector('" + selectorString + "')[" + index + "].value");
            	}
            	else
            	{
            		textBoxText = (String)((JavascriptExecutor)browser).executeScript("return document.querySelector('" + selectorString + "').value");
            	}
                return textBoxText;
            default:
                return null;
        }
    }

    /** 
     * <summary>
     	Method to click an element via javascript based on
     	webelement type
     	</summary>
     * @param selectorString the webelement selector string necessary for the webelement to be found
     * @param index the index used to identify a particular element in a list of elements tat contain
     	 			more than one element with a similar selector
     * @param webelements the locator type used to identify webelements on the page. Based on the values in the
     						LocatorTypes enum.
     	@return void
     */
    private void clickViaJavascriptElementType(String selectorString, String index, LocatorTypes webelements)
    {
        switch (webelements)
        {
            case ID:
                ((JavascriptExecutor)browser).executeScript("document.getElementById('" + selectorString + "').click();");
                break;
            case CLASSNAME:
                ((JavascriptExecutor)browser).executeScript("document.getElementsByClassName('" + selectorString + "')[" + index + "].click();");
                break;
            case NAME:
                ((JavascriptExecutor)browser).executeScript("document.getElementsByName('" + selectorString + "')[" + index + "].click();");
                break;
            case TAGNAME:
                ((JavascriptExecutor)browser).executeScript("document.getElementsByTagName('" + selectorString + "')[" + index + "].click();");
                break;
            case CSSSELECTOR:
                ((JavascriptExecutor)browser).executeScript("document.querySelector('" + selectorString + "')[" + index + "].click();");
                break;
            default:
                break;
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
    public WebElement getElement(String selectorString, String by)
    {
    	try
    	{
    		return browser.findElement(getByValueBasedOnUserInput(selectorString, by));
    	}
    	catch (WebDriverException ex)
    	{
    		throw ex;
    	}
    }
    
    /** 
     * <summary>
     	Check if the element is present in the page. Returns true if element displays.
       </summary>
     * @param selectorString the webelement selector string necessary for the webelement to be found
     * @param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				 WebElement to be found
     * @return Boolean
     */
    public Boolean isElementPresent(String selectorString, String by)
    {
        try
        {
            return getElement(selectorString, by).isDisplayed();
        }
        catch (WebDriverException ex)
        {
            return false;
        }
    }

    /** 
     * <summary>
     	Method to Check if a particular IWebElement is displayed in the Page and return true if displayed or False if not 
     * </summary>
     * @param selectorString the webelement selector string necessary for the webelement to be found
     * @param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				 WebElement to be found
     * @param i the total amount of time alotted to wait for the condition to return true
     * @return Boolean
     */
    public Boolean isElementDisplayedInThePage(String selectorString, String by, int i)
    {
        try
        {
        	new WebDriverWait(browser, i).until(ExpectedConditions.presenceOfElementLocated(getByValueBasedOnUserInput(selectorString, by)));
        	return true;
        }
        catch (WebDriverException ex)
        {
            return false;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
    

    /**
     * <summary>
     	Method to Check if a particular WebElement is displayed in the Page and return true if displayed or False if not 
     * </summary>
     * @param element a webelement that is defined and found in the calling method
     * @param i the total amount of time alotted to wait for the condition to return true
     * @return Boolean
     */
    public Boolean isElementDisplayedInThePage(WebElement element, int i)
    {
        try
        {
        	waitForPresenceOfElementLocated(element, i);
        	return true;
        }
        catch (WebDriverException ex)
        {
            return false;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
    

    /// <summary>
    /// Method to Check if a particular IWebElement is displayed in the Page and return true if displayed or False if not </summary>
    /// <returns> boolean </returns>
    /*public Boolean isElementDisplayedInThePage(WebElement expectedWebElement, int i)
    {
        try
        {
        	waitForPageLoad(expectedWebElement, i, (byValue.toLowerCase().contains(id) ? WebElements.ID : (byValue.toLowerCase().contains(cssSelector)) ? WebElements.CSSSELECTOR : (byValue.toLowerCase().contains(xPath)) ? WebElements.XPATH : (byValue.toLowerCase().contains(className)) ? WebElements.CLASSNAME : (byValue.toLowerCase().contains(tagName)) ? WebElements.TAGNAME : (byValue.toLowerCase().contains(linkText)) ? WebElements.LINKTEXT : (byValue.toLowerCase().contains(partialLinkText)) ? WebElements.PARTIALLINKTEXT : (byValue.toLowerCase().contains(name)) ? WebElements.NAME : WebElements.ID));
            return true;
        }
        catch (WebDriverException ex)
        {
            return false;
        }
        catch (Exception ex)
        {
            return false;
        }
    }*/

    /** 
     * <summary>
     	Click the element 
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @return void
     */
    public void click(String selectorString, String by)
    {
        try
        {
            getElement(selectorString, by).click();
        }
        catch (ElementNotVisibleException ex)
        {
            throw ex;
        }
        catch (StaleElementReferenceException ex)
        {
            throw ex;
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }


    /** 
     * <summary>
     	Click the element 
     * </summary>
		@param element a webelement that is defined and found in the calling method
     * @return void
     */
    public void click(WebElement element)
    {
        try
        {
            element.click();
        }
        catch (ElementNotVisibleException ex)
        {
            throw ex;
        }
        catch (StaleElementReferenceException ex)
        {
            throw ex;
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }

    /** <summary>
     	Method to move to an element
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @return void
     */
    public void moveToElement(String selectorString, String by)
    {
        try
        {
            Actions actions = new Actions(browser);
            actions.moveToElement(getElement(selectorString, by)).click().perform();
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }
    

    /** <summary>
     	Method to mouse over an element
     * </summary>
		@param element a webelement that is defined and found in the calling method
     * @return void
     */ 
    public void mouseOver(WebElement element)
    {
        try
        {
            Actions actions = new Actions(browser);
            actions.clickAndHold(element).perform();
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }

    /** <summary>
 		Method to type inside a particular element
      * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
				  WebElement to be found
		@param text the text value to type into the element
     * @return void
     */
    public void sendKeys(String selectorString, String by, String text)
    {
        try
        {
            getElement(selectorString, by).sendKeys(text);
        }
        catch (InvalidElementStateException ex)
        {
            throw ex;
        }
        catch (StaleElementReferenceException ex)
        {
            throw ex;
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }
    
    /** <summary>
 		Method to type inside a particular element
      * </summary>
		@param element a webelement that is defined and found in the calling method
		@param text the text value to type into the element
     * @return void
     */
    public void sendKeys(WebElement element, String text)
    {
        try
        {
            element.sendKeys(text);
        }
        catch (InvalidElementStateException ex)
        {
            throw ex;
        }
        catch (StaleElementReferenceException ex)
        {
            throw ex;
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }

    /** 
     * <summary>
     	Clear a particular element of text
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @return void
     */
    public void clear(String selectorString, String by)
    {
        try
        {
            getElement(selectorString, by).clear();
        }
        catch (ElementNotVisibleException ex)
        {
            throw ex;
        }
        catch (StaleElementReferenceException ex)
        {
            throw ex;
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }

    /** 
     * <summary>
     	Clear a particular element of text
     * </summary>
		@param element a webelement that is defined and found in the calling method
     * @return void
     */
    public void clear(WebElement element)
    {
        try
        {
            element.clear();
        }
        catch (ElementNotVisibleException ex)
        {
            throw ex;
        }
        catch (StaleElementReferenceException ex)
        {
            throw ex;
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }

    /** 
     * <summary>
     	Wait for an element to load 
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
				  WebElement to be found
      * @param i the total amount of time alotted to wait for the condition to return true
     * @return void
     */
    public void waitForElementToLoad(String selectorString, String by, int i)
    {
        try
        {
        	new WebDriverWait(browser, i).until(ExpectedConditions.visibilityOfElementLocated(getByValueBasedOnUserInput(selectorString, by)));
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }

    /// <summary>
    /// Wait for an element to load </summary>
    /// <returns> void </returns>
    ///
    /*public void waitForElementToLoad(WebElement webElement, int waitTime)
    {
        try
        {
            Object d;
			new WebDriverWait(browser, waitTime).until((com.google.common.base.Predicate<WebDriver>) ((d) = webElement.isDisplayed()));
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }*/

    /** 
     * <summary>
     	Method to return whether the expected text displays inside an element 
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @param expectedText the text that is expected to be found inside the element
     * @return Boolean
     */
    public Boolean isTextDisplayedInSpecifiedElement(String selectorString, String by, String expectedText)
    {
        String actualText = getElement(selectorString, by).getText();
        String modifiedActualText = actualText.replace("\r\n", " ");
        if (modifiedActualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /** 
     * <summary>
     	Method to return whether the expected text displays inside an element 
     * </summary>
		@param element a webelement that is defined and found in the calling method
     * @param expectedText the text that is expected to be found inside the element
     * @return Boolean
     */
    public Boolean isTextDisplayedInSpecifiedElement(WebElement element, String expectedText)
    {
        if (element.getText().trim().contains(expectedText))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * <summary>
     	Method to get text from the webelement 
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  			      WebElement to be found
     * @return String
     */
    public String getTextFromWebElement(String selectorString, String by)
    {
        if (!(getElement(selectorString, by) == (null)))
        {
            return getElement(selectorString, by).getText().trim();
        }
        else
        {
            return null;
        }
    }


    /**
     * <summary>
     	Method to get text from the webelement 
     * </summary>
		@param element a webelement that is defined and found in the calling method
     * @return String
     */
    public String getTextFromWebElement(WebElement element)
    {
        if (!(element == null))
        {
            return element.getText().trim();
        }
        else
        {
            return null;
        }
    }

    /**
     * <summary> 
     * 	method to jquery click
     * </summary>
     * @param selectorString the webelement selector string necessary for the webelement to be found
     * @return void
     */
    public void clickViaJQuery(String selectorString)
    {
        try
        {
        	String webElement = null;
        	if (selectorString.contains("'"))
        	{
        		webElement = selectorString.replace("'", "");
        	}
        	else
        	{
        		webElement = selectorString;
        	}
        	
            ((JavascriptExecutor)browser).executeScript("$('" + webElement + "').click();");
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }
    

    /**
     * <summary> 
     * 	method to jquery click
     * </summary>
     * @param selectorString the webelement selector string necessary for the webelement to be found
     * @param index the index used to identify a particular element in a list of elements that contain
     * 				more than one element with a similar selector
     * @return void
     */
    public void clickViaJQuery(String selectorString, String index)
    {
        try
        {
        	String webElement = null;
        	if (selectorString.contains("'"))
        	{
        		webElement = selectorString.replace("'", "");
        	}
        	else
        	{
        		webElement = selectorString;
        	}
        	
        	if (index == null ||
        		index == "")
        	{
        		((JavascriptExecutor)browser).executeScript("$('" + webElement + "').click();");
        	}
        	else
        	{
        		((JavascriptExecutor)browser).executeScript("$('" + webElement + "')["+index+"].click();");
        	}
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }

    /**<summary> 
     * method to javascript click
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @param index the index used to identify a particular element in a list of elements that contain
     * 				more than one element with a similar selector
     * @return void
     */
    public void clickViaJavascript(String selectorString, String by, String index)
    {
        try
        {
            clickViaJavascriptElementType(selectorString, index, (by.toLowerCase().contains(id) ? LocatorTypes.ID : 
            	(by.toLowerCase().contains(cssSelector)) ? LocatorTypes.CSSSELECTOR : 
            		(by.toLowerCase().contains(xPath)) ? LocatorTypes.XPATH : 
            			(by.toLowerCase().contains(className)) ? LocatorTypes.CLASSNAME : 
            				(by.toLowerCase().contains(tagName)) ? LocatorTypes.TAGNAME : 
            					(by.toLowerCase().contains(linkText)) ? LocatorTypes.LINKTEXT : 
            						(by.toLowerCase().contains(partialLinkText)) ? LocatorTypes.PARTIALLINKTEXT : 
            							(by.toLowerCase().contains(name)) ? LocatorTypes.NAME : LocatorTypes.ID));
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }

    /**
     * <summary>
     * Method to see if the element contains any text. Returns true if text is present and
     * false if no text is present.
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  			      WebElement to be found
     * @return Boolean
     */
    public Boolean isAnyTextDisplayedInElement(String selectorString, String by)
    {
        String actualValue = getElement(selectorString, by).getText();
        if (!(actualValue == null))
        {
        	if (!(actualValue == ""))
        	{
        		return true;
        	}
        	else
        	{
        		return false;
        	}    	
        }
        else
        {
            return false;
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
    public List<WebElement> getElements(String selectorString, String by)
    {
    	try
    	{
    		return browser.findElements(getByValueBasedOnUserInput(selectorString, by));
    	}
    	catch (WebDriverException ex)
    	{
    		throw ex;
    	}
    }

    /**<summary> method to wait for page to load
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @param i the total amount of time alotted to wait for the condition to return true
     * @return void
     */
    public void WaitForPageToLoad(String selectorString, String by, int i)
    {
    	try
    	{
    	new WebDriverWait(browser, i).until(ExpectedConditions.presenceOfElementLocated(getByValueBasedOnUserInput(selectorString, by)));
    	}
    	catch (WebDriverException ex)
    	{
    		throw ex;
    	}
    }

    /**<summary> method to navigate to another page
     * </summary>
     * @param url the url to navigate to
     * @return void
     */
    public void navigateToUrl(String url)
    {
        try
        {
            browser.navigate().to(url);
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }

    /**
     * <summary> 
     * method to wait for element to be clickable
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @param i the total amount of time alotted to wait for the condition to return true
     * @return void
     */
    public void waitForElementToBeClickable(String selectorString, String by, int i)
    {
        try
        {
        	new WebDriverWait(browser, i).until(ExpectedConditions.elementToBeClickable(getByValueBasedOnUserInput(selectorString, by)));
        }
        catch (StaleElementReferenceException ex)
        {
            throw ex;
        }
    }

    /**<summary> method to select an option from the dropdown menu
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @param optionToSelect the option to select from the dropdown menu
     * @return void
     */
    public void selectOptionFromDropDownMenu(String selectorString, String by, String optionToSelect)
    {
        try
        {
        	Select select = new Select(browser.findElement(getByValueBasedOnUserInput(selectorString, by)));
            select.selectByVisibleText(optionToSelect);
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }

    /**<summary> method to get the string text value from a text field via javascript
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  			      WebElement to be found
     * @param requiresIndex boolean value to toggle whether the index is necessary. True uses an index and index
     * 				       cannot be blank or null. False does not use an index and index can be blank or null.
     * @param index the index used to identify a particular element in a list of elements that contain
     * 				more than one element with a similar selector
     * @return String
     */
    public String getTextInTextBoxViaJavascript(String selectorString, String by, Boolean requiresIndex, String index)
    {
    	String webElement = null;
    	if (selectorString.contains("'"))
    	{
    		webElement = selectorString.replace("'", "");
    	}
    	else
    	{
    		webElement = selectorString;
    	}
    	
        return getTxtBoxValueViaJavascriptScript(webElement, index, requiresIndex, (by.toLowerCase().contains(id) ? LocatorTypes.ID : 
        	(by.toLowerCase().contains(cssSelector)) ? LocatorTypes.CSSSELECTOR : 
        		(by.toLowerCase().contains(xPath)) ? LocatorTypes.XPATH : 
        			(by.toLowerCase().contains(className)) ? LocatorTypes.CLASSNAME : 
        				(by.toLowerCase().contains(tagName)) ? LocatorTypes.TAGNAME : 
        					(by.toLowerCase().contains(linkText)) ? LocatorTypes.LINKTEXT : 
        						(by.toLowerCase().contains(partialLinkText)) ? LocatorTypes.PARTIALLINKTEXT : 
        							(by.toLowerCase().contains(name)) ? LocatorTypes.NAME : LocatorTypes.ID));
    }

    /**
     * <summary> 
     * method to get an attribute
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @param attribute the html attribute whose value is to be evaluated and obtained
     * @return String
     */
    public String getWebElementAttribute(String selectorString, String by, String attribute)
    {
        return getElement(selectorString, by).getAttribute(attribute);
    }


    /**
     * <summary> 
     * method to get an attribute
     * </summary>
		@param element a webelement that is defined and found in the calling method
     * @param attribute the html attribute whose value is to be evaluated and obtained
     * @return String
     */
    public String getWebElementAttribute(WebElement element, String attribute)
    {
        return element.getAttribute(attribute).toString();
    }

    /**
     * <summary> 
     * method to clear a field by selecting all text and backspacing
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  			      WebElement to be found
     * @return void
     */
    public void clearAllTextByBackspacing(String selectorString, String by)
    {
        try
        {
            getElement(selectorString, by).sendKeys(Keys.CONTROL + "a");
            getElement(selectorString, by).sendKeys(Keys.BACK_SPACE);
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }
    

    /**
     * <summary> 
     * method to clear a field by selecting all text and backspacing
     * </summary>
		@param element a webelement that is defined and found in the calling method
     * @return void
     */
    public void clearAllTextByBackspacing(WebElement element)
    {
        try
        {
        	element.sendKeys(Keys.CONTROL + "a");
        	element.sendKeys(Keys.BACK_SPACE);
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }

    /**<summary> method to get the width of a webElement
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  			      WebElement to be found
     * @return int
     */
    public int getWidthOfWebElement(String selectorString, String by)
    {
        return getElement(selectorString, by).getSize().getWidth();
    }

    /** 
     * <summary>
     	Method to find a declared element by a particular method 
     * </summary>
     * @param element a webelement that is defined and found in the calling method
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  			      WebElement to be found
     * @return WebElement
     */
    public WebElement findWebElement(WebElement element, String by, String selectorString)
    {
    	try
    	{
    		return element.findElement(getByValueBasedOnUserInput(selectorString, by));
    	}
    	catch (WebDriverException ex)
    	{
    		throw ex;
    	}
    }
    
    /**
     * <summary>
     * Method to find multiple declared elements by a particular method 
     * </summary>
     * @param element a webelement that is defined and found in the calling method
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  			      WebElement to be found
     * @return List<\WebElement>
     */
    public List<WebElement> findWebElements(WebElement element, String by, String selectorString)
    {
    	try
    	{
    		return element.findElements(getByValueBasedOnUserInput(selectorString, by));
    	}
    	catch (WebDriverException ex)
    	{
    		throw ex;
    	}
    }
    
    /**
     * <summary>
     * Method to get the text from an element 
     * </summary>
     * @param element a webelement that is defined and found in the calling method
     * @return String
     */
    public String getText(WebElement element)
    {
        try
        {
            if (!(element == null))
            {
                return element.getText().trim();
            }
            else
            {
                return null;
            }
        }
        catch (StaleElementReferenceException ex)
        {
            throw ex;
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }

    /**
     * <summary>
     * Method to get the text from an element 
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @return String
     */
    public String getText(String selectorString, String by)
    {
        try
        {
            if (!(getElement(selectorString, by) == null))
            {
                return getElement(selectorString, by).getText().trim();
            }
            else
            {
                return null;
            }
        }
        catch (StaleElementReferenceException ex)
        {
            throw ex;
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }
    
    /**
     * <summary>
     * Method to drag and drop an element via actions 
     * </summary>
     * @param element a webelement that is defined and found in the calling method
     * @param xPixels the number of pixels on the x axis
     * @param yPixels the number of pixels on the y axis
     * @return void
     */
    public void DragAndDropByElement(WebElement element, int xPixels, int yPixels)
    {
        Actions dragger = new Actions(browser);
        dragger.moveToElement(element).clickAndHold().moveByOffset(xPixels, yPixels).release().perform();
    }

    /**
     * <summary>
     * Method to drag and drop an element via actions 
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  			      WebElement to be found
     * @param x the number of pixels on the x axis
     * @param y the number of pixels on the y axis
     * @return void
     */
    public void DragAndDropByElement(String selectorString, String by, int x, int y)
    {
        Actions dragger = new Actions(browser);
        dragger.moveToElement(getElement(selectorString, by)).clickAndHold().moveByOffset(x, y).release().perform();
    }

    /**<summary>Method to wait for the element to be invisible in the page
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @param i the total amount of time alotted to wait for the condition to return true
     * @return void
     */
    public void waitUntilElementIsInvisible(String selectorString, String by, int i)
    {
        try
        {
        	new WebDriverWait(browser, i).until(ExpectedConditions.invisibilityOfElementLocated(getByValueBasedOnUserInput(selectorString, by)));
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }

    /** 
     * <summary>
     * Method to scroll on the page
     * </summary>
     * @param x the number of pixels on the x axis
     * @param y the number of pixels on the y axis
     * @return void
     */
    public void scroll(String x, String y)
    {
        try
        {
            ((JavascriptExecutor)browser).executeScript("scroll(" + x + "," + y + ")");
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }

    /**
     * <summary>
     * Method to switch to default content
     * </summary>
     * @return void
     */
    public void switchToDefaultContent()
    {
        try
        {
            browser.switchTo().defaultContent();
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }

    /**
     * <summary>
     * Method to refresh the page
     * </summary>
     * @return void
     */
    public void refreshThePage()
    {
        try
        {
            browser.navigate().refresh();
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }

    /**
     * <summary> 
     * method to open a new tab
     * </summary>
     * @return void
     */
    public void openANewTab()
    {
        try
        {
            ((JavascriptExecutor)browser).executeScript("window.open();");
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
    }
    
    /**
     * <summary> 
     * method to evaluate whether an attribute is present or not and contains the expected value
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @param attribute the html attribute whose value is to be evaluated and obtained
     * @param expectedValue the value that is expected to be in the html webelement attribute
     * @return Boolean
     */
    public Boolean doesAttributeContainTheExpectedValue(String selectorString, String by, String attribute, String expectedValue)
    {
    	Boolean result = false;
    	try
    	{
    		String value = getElement(selectorString, by).getAttribute(attribute);
    		if (value != null)
    		{
    			if (value.toLowerCase().trim().equals(expectedValue.toLowerCase().trim()))
    			{
    				result = true;
    			}
    		}
    	}
    	catch (WebDriverException ex)
    	{
    		throw ex;
    	}
    	return result;
    }
    
    
    /**
     * <summary>
     *  method to drag and drop an item to another element
     * </summary>
     * @param firstElementSelectorString the webelement selector string necessary for the webelement to be found for the
     * 							         "from" element
     * @param firstElementBy the type of selector being used (i.e id, name, cssSelector, xpath, etc.) for the 
     * 					     "from" element. Necessary for the WebElement to be found. 
     * @param secondElementSelectorString the webelement selector string necessary for the webelement to be found for the
     * 							         "to" element
     * @param secondElementBy the type of selector being used (i.e id, name, cssSelector, xpath, etc.) for the 
     * 					     "to" element. Necessary for the WebElement to be found. 
     * @param stepWidth
     * @param stepDelay
     * @param dx
     * @param dy
     * @throws Exception
     */
    public void dragAndDropElementToAnotherElement(String firstElementSelectorString, String firstElementBy, String secondElementSelectorString, String secondElementBy, String stepWidth, String stepDelay, String dx, String dy) throws Exception
    {
    	try
    	{
    		WebElement LocatorFrom = getElement(firstElementSelectorString, firstElementBy);
        	int xFrom=LocatorFrom.getLocation().x;
        	int yFrom=LocatorFrom.getLocation().y;
        	int[] difference = {0 , 0};
        	addDragNDropScriptsToPage();
        	
        	if (dx == null &&
        		dy == null)
        	{
        		WebElement LocatorTo = getElement(secondElementSelectorString, secondElementBy);
            	int xto=LocatorTo.getLocation().x;
            	int yto=LocatorTo.getLocation().y;
        		ArrayList<String> values = dXAnddYValues(xto, yto, xFrom, yFrom, LocatorTo.getSize().height, LocatorFrom.getSize().height, difference);
            	
        		executeDragNDrop(firstElementSelectorString, values.get(0), values.get(1), stepWidth, stepDelay);
        	}
        	else
        	{
        		executeDragNDrop(firstElementSelectorString, dx, dy, stepWidth, stepDelay);
        	}
    	}
    	catch (Exception ex)
    	{
    		throw ex;
    	}
    }
    
    /**
     * <summary>
     * Method to get the dx and dy values and store them in an arrayList
     * </summary>
     * @param x1 the first element's x value
     * @param y1 the second element's y value
     * @param x2 the second element's x value
     * @param y2 the second element' y value
     * @param y1Height the first element's height
     * @param y2Height the second element's height
     * @param difference
     * @return ArrayList<\String>
     */
    private ArrayList<String> dXAnddYValues(int x1, int y1, int x2, int y2, int y1Height, int y2Height, int[] difference)
    {
    	ArrayList<String> values = new ArrayList<String>();
    	try
    	{
              y1 = y1 + (y1Height / 2);
              x2 = x2 - difference[0];
              y2 = y2 - difference[1];
              y2 = y2 + (y2Height / 2);
              int dx = x2 - x1;
              int dy = y2 - y1;
              
              values.add(Integer.toString(dx));
              values.add(Integer.toString(dy));
    	}
    	catch (Exception ex)
    	{
    		throw ex;
    	}
    	return values;
    }
    
    
    /**
     * <summary> 
     * method to drag and drop an item to another element
     * </summary>
     * @param firstElement
     * @param secondElement
     * @param thirdElement
     * @return void
     * @throws Exception 
     */
    public void dragAndDropElementToAnotherElement(WebElement firstElement, WebElement secondElement, String firstElementSelectorString, String index, String stepWidth, String stepDelay, String dx, String dy) throws Exception
    {
    	try
    	{ 		
    		WebElement LocatorFrom = firstElement;
        	int xFrom=LocatorFrom.getLocation().x;
        	int yFrom=LocatorFrom.getLocation().y;
        	int[] difference = {0 , 0};
        	addDragNDropScriptsToPage();
        	
        	if (dx == null &&
        		dy == null)
        	{
        		WebElement LocatorTo = secondElement;
            	int xto=LocatorTo.getLocation().x;
            	int yto=LocatorTo.getLocation().y;
        		ArrayList<String> values = dXAnddYValues(xto, yto, xFrom, yFrom, LocatorTo.getSize().height, LocatorFrom.getSize().height, difference);
            	
        		executeDragNDrop(firstElementSelectorString, values.get(0), values.get(1), stepWidth, stepDelay, index);
        	}
        	else
        	{
        		executeDragNDrop(firstElementSelectorString, dx, dy, stepWidth, stepDelay, index);
        	}
    	}
    	catch (Exception ex)
    	{
    		throw ex;
    	}
    }
    
    /**
     * <summary>
     * Method to execute the drag and drop javascript command
     * </summary>
     * @param from the selector string of the "from" element
     * @param dx
     * @param dy
     * @param stepWidth
     * @param stepDelay
     */
    private void executeDragNDrop(String from, String dx, String dy, String stepWidth, String stepDelay)
    {
    	try
    	{
    		((JavascriptExecutor)browser).executeScript("$('"+ from + "').simulate('drag-n-drop', {dx: "+ dx +", dy: "+ dy +", interpolation: {stepWidth: "+ stepWidth +", stepDelay: "+ stepDelay +"}})");
    	}
    	catch (Exception ex)
    	{
    		throw ex;
    	}
    }
    
    
    /**
     * <summary>
     * Method to execute the drag and drop javascript command
     * </summary>
     * @param from the selector string of the "from" element
     * @param dx
     * @param dy
     * @param stepWidth
     * @param stepDelay
     * @param index the index of the element to be selected from a list of similar elements
     */
    private void executeDragNDrop(String from, String dx, String dy, String stepWidth, String stepDelay, String index)
    {
    	try
    	{
    		((JavascriptExecutor)browser).executeScript("$('"+ from + "')["+ index + "].simulate('drag-n-drop', {dx: "+ dx +", dy: "+ dy +", interpolation: {stepWidth: "+ stepWidth +", stepDelay: "+ stepDelay +"}})");
    	}
    	catch (Exception ex)
    	{
    		throw ex;
    	}
    }
    
    /**
     * <summary>
     * Method to inject the drag and drop scripts into the page so that the drag and
     * drop functionality can be used.
     * </summary>
     * @return void
     */
    private void addDragNDropScriptsToPage() throws Exception
    {
    	try
    	{
        	addScriptToPage("http://j-ulrich.github.io/jquery-simulate-ext/jquery.simulate.js");
        	Thread.sleep(500);
        	addScriptToPage("http://j-ulrich.github.io/jquery-simulate-ext/jquery.simulate.ext.js");
        	Thread.sleep(500);
        	addScriptToPage("http://j-ulrich.github.io/jquery-simulate-ext/jquery.simulate.drag-n-drop.js");
        	Thread.sleep(500);
    	}
    	catch (Exception ex)
    	{
    		throw ex;
    	}
    }
    
    /**
     * <summary>
     * Method to inject a script to a webpage
     * </summary>
     * @param script the script that needs to be injected
     * @return void
     */
    private void addScriptToPage(String script)
    {
    	try
    	{
    		((JavascriptExecutor)browser).executeScript("$.getScript('" + script + "')");
    	}
    	catch (Exception ex)
    	{
    		throw ex;
    	}
    }
    
    
    /**
     * <summary> 
     * method to wait for an attribute to disappear
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @param attribute the html attribute whose value is to be evaluated and obtained
     * @param i the total amount of time alotted to wait for the condition to return true
     * @return void
     */
    public void waitForAttributeToDisappear(String selectorString, String by, String attribute, int i)
    {
    	try
    	{
    		WebDriverWait wait = new WebDriverWait(browser,i);
    		wait.until(new ExpectedCondition<Boolean>() {
    		            public Boolean apply(WebDriver driver) {
    		                         WebElement elementToBeTested = getElement(selectorString, by);
    		                         String value = elementToBeTested.getAttribute(attribute);
    		                         if(value == null || value == "")
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
    
    
    /**
     * <summary> 
     * method to wait for an attribute to disappear
     * </summary>
     * @param element a webelement that is defined and found in the calling method
     * @param attribute the html attribute whose value is to be evaluated and obtained
     * @param i the total amount of time alotted to wait for the condition to return true
     * @return void
     */
    public void waitForAttributeToDisappear(WebElement element, String attribute, int i)
    {
    	try
    	{
    		WebDriverWait wait = new WebDriverWait(browser,i);

    		wait.until(new ExpectedCondition<Boolean>() {
    		            public Boolean apply(WebDriver driver) {
    		                         WebElement elementToBeTested = element;
    		                         String value = elementToBeTested.getAttribute(attribute);
    		                         if(value == null || value == "")
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
    
  /**<summary> method to wait for an attribute to equal a certain value
   * </summary>
   * @param element a webelement that is defined and found in the calling method
   * @param attribute the html attribute whose value is to be evaluated and obtained
   * @param expectedValue the expected value of the html attribute
   * @param i the total amount of time allotted to wait for the condition to return true
   * @return void
   */
    public void waitForAttributeToEqualACertainValue(WebElement element, String attribute, String expectedValue, int i)
    {
    	try
    	{
    		WebDriverWait wait = new WebDriverWait(browser,i);

    		wait.until(new ExpectedCondition<Boolean>() {
    		            public Boolean apply(WebDriver driver) {
    		                         WebElement elementToBeTested = element;
    		                         String actualValue = elementToBeTested.getAttribute(attribute);
    		                         if(actualValue.toLowerCase().trim().equals(expectedValue.toLowerCase().trim()))
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
    public void waitForAttributeToEqualACertainValue(String selectorString, String by, String attribute, String expectedValue, int i)
    {
    	try
    	{
    		WebDriverWait wait = new WebDriverWait(browser,i);

    		wait.until(new ExpectedCondition<Boolean>() {
    		            public Boolean apply(WebDriver driver) {
    		                         WebElement elementToBeTested = getElement(selectorString, by);
    		                         String actualValue = elementToBeTested.getAttribute(attribute);
    		                         if(actualValue.toLowerCase().trim().equals(expectedValue.toLowerCase().trim()))
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
    public void waitForAttributeToContainACertainValue(String selectorString, String by, String attribute, String expectedValue, int i)
    {
    	try
    	{
    		WebDriverWait wait = new WebDriverWait(browser,i);

    		wait.until(new ExpectedCondition<Boolean>() {
    		            public Boolean apply(WebDriver driver) {
    		                         WebElement elementToBeTested = getElement(selectorString, by);
    		                         String actualValue = elementToBeTested.getAttribute(attribute);
    		                         if(actualValue.toLowerCase().trim().contains(expectedValue.toLowerCase().trim()))
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
    
  /**
   * <summary> 
   * method to return if an attribute is present in an element or not
   * </summary>
   * @param element a webelement that is defined and found in the calling method
   * @param attribute the html attribute whose value is to be evaluated and obtained
   * @return Boolean
   */
    public Boolean isAttributePresentInElement(WebElement element, String attribute)
    {
    	Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null){
                result = true;
            }
        } catch (Exception e) {
        	return result;
        }

        return result;
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
    public void waitForTextToExistInElement(String selectorString, String by, String expectedText, int i)
    {
    	try
    	{
    		WebDriverWait wait = new WebDriverWait(browser,i);

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
    
    
    /**
     * <summary> 
     * method to wait for a particular text to be present in a web element
     * </summary>
		@param selectorString the webelement selector string necessary for the webelement to be found
		@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
  				  WebElement to be found
     * @param i the total amount of time allotted to wait for the condition to return true
     * @return void
     */
    public void waitForPresenceOfElementLocated(String selectorString, String by, int i)
    {
    	try
    	{
    		WebDriverWait wait = new WebDriverWait(browser,i);

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
	                         		if(browser.findElement(byValue).isDisplayed())
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
    
    public void waitForWindowCount(int i, int expectedNumberOfWindows)
    {
    	try
    	{
    		WebDriverWait wait = new WebDriverWait(browser,i);

    		wait.until(new ExpectedCondition<Boolean>() {
	            	public Boolean apply(WebDriver driver) {
	            				Boolean result = false;
	                         	int actualNumberofWindows = browser.getWindowHandles().size();
	                         	try
	                         	{
	                         		if (actualNumberofWindows == expectedNumberOfWindows)
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
    
    public void switchToNewWindow()
    {
    	try
    	{
    		for (String window : browser.getWindowHandles())
    		{
    			browser.switchTo().window(window);
    		}
    	}
    	catch (WebDriverException ex)
    	{
    		throw ex;
    	}
    }
    
    
    /**
     * <summary> 
     * method to wait for a particular text to be present in a web element
     * </summary>
     * @param element a webelement that is defined and found in the calling method
     * @param i the total amount of time allotted to wait for the condition to return true
     * @return void
     */
    public void waitForPresenceOfElementLocated(WebElement element, int i)
    {
    	try
    	{
    		WebDriverWait wait = new WebDriverWait(browser,i);

    		wait.until(new ExpectedCondition<Boolean>() {
	            	public Boolean apply(WebDriver driver) {
	            				Boolean result = false;
	                         	try
	                         	{
	                         		if(element.isDisplayed())
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
    
    /**
     * <summary> 
     * method to scroll to the bottom of a page
     * </summary>
     * @throws Exception
     * @return void
     */
    public void scrollToBottomOfPage() throws Exception
    {
    	try
    	{
    		JavascriptExecutor js = ((JavascriptExecutor) browser);

    		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    		
    		Thread.sleep(600);
    	}
    	catch (WebDriverException | InterruptedException ex)
    	{
    		throw ex;
    	}
    }
    
    /**
     * <summary>
     * Method to scroll to a particular element in the page
     * </summary>
     * @param selectorString  the webelement selector string necessary for the webelement to be found
     * @param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
				 WebElement to be found
     */
    public void scrollToElement(String selectorString, String by)
    {
    	try
    	{
    		WebElement element = getElement(selectorString, by);
    		((JavascriptExecutor) browser).executeScript(
              "arguments[0].scrollIntoView();", element);
    	}
    	catch (WebDriverException ex)
    	{
    		throw ex;
    	}
    }
    
    /**
     * <summary>
     *  Method to scroll to a particular element in the page
     * </summary>
     * @param element the pre-defined webelement to scroll to
     */
    public void scrollToElement(WebElement element)
    {
    	try
    	{
    		((JavascriptExecutor) browser).executeScript(
              "arguments[0].scrollIntoView();", element);
    	}
    	catch (WebDriverException ex)
    	{
    		throw ex;
    	}
    }
    
    /**
     * <summary>
     * Method to switch to an iframe in the page
     * </summary>
     * @param selectorString the webelement selector string necessary for the webelement to be found
     * @param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
     * 			 WebElement to be found
     */
    public void switchToIFrame(String selectorString, String by)
    {
    	try
    	{
    		browser.switchTo().frame(getElement(selectorString, by));
    	}
    	catch (WebDriverException ex)
    	{
    		throw ex;
    	}
    }
    
    /**
     * <summary>
     * Method to switch to an iframe in the page
     * </summary>
     * @param element the pre-defined webelement for the iframe
     */
    public void switchToIFrame(WebElement element)
    {
    	try
    	{
    		browser.switchTo().frame(element);
    	}
    	catch (WebDriverException ex)
    	{
    		throw ex;
    	}
    }
    
    /**
     * <summary>
     * Method to switch back to the main window after having switched to 
     * an iFrame.
     * </summary>
     */
    public void switchBackToDefaultContent()
    {
    	try
    	{
    		browser.switchTo().defaultContent();
    	}
    	catch (WebDriverException ex)
    	{
    		throw ex;
    	}
    }
  public void dragAndDropViaSelenium(String dragable , String dragableby,  String dropable, String dropableby) throws FrameworkException, InterruptedException {
    	
    	//.sleep(3000);
      	WebElement dragElement = null;
      	WebElement dropElement = null;
     
      	
      	waitForElementToBeClickable(dragable, dragableby, 20);
      	List<WebElement> locate = getElements(dragable, dragableby);
      	dragElement = locate.get(1);
      	waitForElementToBeClickable(dropable,dropableby,20);
      	List<WebElement> locateDroppable = getElements(dropable,dropableby);
	try {
	        Actions act = new Actions(browser);
	        dropElement = locateDroppable.get(0);
	       	act.clickAndHold(dragElement).build().perform();
	       	Thread.sleep(1000);
	       	act.moveToElement(dropElement ).build().perform();
	        Thread.sleep(1000);
	       	act.release();
	       	act.release(dragElement).build().perform();
	        Thread.sleep(5000);
	       	System.out.print(dropElement.getText() + "and dropped was: "+ dropElement.getText());
	} catch (WebDriverException ex) {
	        throw ex;
	}
    
} 
    
}
