package seleniumHelper.methods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import commonClasses.sharedUtils.managers.LocalDriver;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.interfaces.IClick;

public class JSClick extends Commands implements IClick {
	
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
    	try
    	{
    		switch (webelements)
    		{
            	case ID:
            		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.getElementById('" + selectorString + "').click();");
            		break;
            	case CLASSNAME:
            		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.getElementsByClassName('" + selectorString + "')[" + index + "].click();");
            		break;
	            case NAME:
	                ((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.getElementsByName('" + selectorString + "')[" + index + "].click();");
	                break;
	            case TAGNAME:
	                ((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.getElementsByTagName('" + selectorString + "')[" + index + "].click();");
	                break;
	            case CSSSELECTOR:
	                ((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.querySelector('" + selectorString + "').click();");
	                break;
	            default:
	                break;
	        }
    	}
    	catch (Exception ex)
    	{
    		throw ex;
    	}
    }

	@Override
	public void on(String selectorString, String by) {
        try
        {
            clickViaJavascriptElementType(selectorString, "0", (by.toLowerCase().contains(id) ? LocatorTypes.ID : 
            	(by.toLowerCase().contains(cssSelector)) ? LocatorTypes.CSSSELECTOR :
            		(by.toLowerCase().contains(className)) ? LocatorTypes.CLASSNAME : 
            			(by.toLowerCase().contains(tagName)) ? LocatorTypes.TAGNAME :
            				(by.toLowerCase().contains(name)) ? LocatorTypes.NAME : 
            					LocatorTypes.ID));
        }
        catch (Exception ex)
        {
            throw ex;
        }
	}

	@Override
	public void on(WebElement element) throws Exception {
        try
        {
        	if (element.isEnabled() && element.isDisplayed()) 
        	{
        		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("arguments[0].click();", element);
        	}
        	else 
        	{
				throw new Exception("Unable to click on element via Javascript.");
			}
        }
        catch (Exception ex)
        {
            throw ex;
        }
	}

	@Override
	public void on(String selectorString, String by, String index) {
        try
        {
            clickViaJavascriptElementType(selectorString, index, (by.toLowerCase().contains(id) ? LocatorTypes.ID : 
            	(by.toLowerCase().contains(cssSelector)) ? LocatorTypes.CSSSELECTOR :
            		(by.toLowerCase().contains(className)) ? LocatorTypes.CLASSNAME : 
            			(by.toLowerCase().contains(tagName)) ? LocatorTypes.TAGNAME :
            				(by.toLowerCase().contains(name)) ? LocatorTypes.NAME : 
            					LocatorTypes.ID));
        }
        catch (Exception ex)
        {
            throw ex;
        }
	}

	@Override
	public void on(String selectorString, String by, int index) {
        try
        {
            clickViaJavascriptElementType(selectorString, Integer.toString(index), (by.toLowerCase().contains(id) ? LocatorTypes.ID : 
            	(by.toLowerCase().contains(cssSelector)) ? LocatorTypes.CSSSELECTOR :
            		(by.toLowerCase().contains(className)) ? LocatorTypes.CLASSNAME : 
            			(by.toLowerCase().contains(tagName)) ? LocatorTypes.TAGNAME :
            				(by.toLowerCase().contains(name)) ? LocatorTypes.NAME : 
            					LocatorTypes.ID));
        }
        catch (Exception ex)
        {
            throw ex;
        }
	}

}
