package seleniumHelper.abstracts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import commonClasses.sharedUtils.managers.LocalDriver;

public abstract class Commands {
	

    //defining the different webelement strings to use when calling switch statements
    protected String cssSelector = "cssselector";
    protected String id = "id";
    protected String xPath = "xpath";
    protected String className = "classname";
    protected String tagName = "tagname";
    protected String linkText = "linktext";
    protected String partialLinkText = "partiallinktext";
    protected String name = "name";

	
	/**	
  	 *	<summary>
	 *	defining the different webelement strings to use when defining switch statements
	 *	</summary>
	*/
    protected enum LocatorTypes
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
  	 *	<summary>
	 *	method to get the by value based on user input
	 *	</summary>
	 *	@return By
	 *	@param selectorString the webelement selector string necessary for the webelement to be found
	 *	@param by the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	 *			  WebElement to be found
	*/
    public By getByValue(String selectorString, LocatorTypes by)
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
    	catch (Exception ex)
    	{
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
    protected By getByValueBasedOnUserInput(String selectorString, String by)
    {
    	try
    	{
    		        return getByValue(selectorString,by.equalsIgnoreCase(id) ? LocatorTypes.ID:
    		        	   (by.equalsIgnoreCase(cssSelector) ? LocatorTypes.CSSSELECTOR : 
    	    			   (by.equalsIgnoreCase(xPath)) ? LocatorTypes.XPATH : 
    	    			   (by.equalsIgnoreCase(className)) ? LocatorTypes.CLASSNAME : 
    	    			   (by.equalsIgnoreCase(tagName)) ? LocatorTypes.TAGNAME :
    	    			   (by.equalsIgnoreCase(partialLinkText)) ? LocatorTypes.PARTIALLINKTEXT :
    	    			   (by.equalsIgnoreCase(linkText)) ? LocatorTypes.LINKTEXT :
    	    			   (by.equalsIgnoreCase(name)) ? LocatorTypes.NAME : LocatorTypes.ID));
    	}
    	catch (Exception ex)
    	{
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
    public WebElement getElement(String selectorString, String by)
    {
    	try
    	{
    		return LocalDriver.getDriver().findElement(getByValueBasedOnUserInput(selectorString, by));
    	}
    	catch (WebDriverException ex)
    	{
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
    public List<WebElement> getElements(String selectorString, String by)
    {
    	try
    	{
    		return LocalDriver.getDriver().findElements(getByValueBasedOnUserInput(selectorString, by));
    	}
    	catch (Exception ex)
    	{
    		throw ex;
    	}
    }

}
