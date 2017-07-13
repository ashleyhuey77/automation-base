package seleniumHelper.methods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.google.errorprone.annotations.DoNotCall;

import commonClasses.sharedUtils.TestUtils;
import commonClasses.sharedUtils.managers.LocalDriver;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.interfaces.IText;

public class JSPageText extends Commands implements IText {
	
	private String getValueViaJavascriptIndexNoIndex(String javascriptStartTxt, String selectorString, String index, Boolean requiresIndex)
    {
    	String textBoxText = null;
    	try
    	{
			if (requiresIndex)
			{
        		textBoxText = (String)((JavascriptExecutor)LocalDriver.getDriver()).executeScript(javascriptStartTxt + selectorString + "')[" + index + "].value"); 
        	}
        	else
        	{
        		textBoxText = (String)((JavascriptExecutor)LocalDriver.getDriver()).executeScript(javascriptStartTxt + selectorString + "').value");
        	}
    	}
    	catch (Exception ex)
    	{
    		throw ex;
    	}
    	return textBoxText;
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
	try
	{
		switch (webElements)
		{
			case ID:
				textBoxText = getValueViaJavascriptIndexNoIndex("return document.getElementById('", selectorString, index, requiresIndex);
				break;
	        case CLASSNAME:
	        	textBoxText = getValueViaJavascriptIndexNoIndex("return document.getElementsByClassName('", selectorString, index, requiresIndex);
	        	break;
	        case NAME:
	        	textBoxText = getValueViaJavascriptIndexNoIndex("return document.getElementsByName('", selectorString, index, requiresIndex);
	        	break;
	        case TAGNAME:
	        	textBoxText = getValueViaJavascriptIndexNoIndex("return document.getElementsByTagName('", selectorString, index, requiresIndex);
	        	break;
	        case CSSSELECTOR:
	        	textBoxText = getValueViaJavascriptIndexNoIndex("return document.querySelector('", selectorString, index, requiresIndex);
	        	break;
		default:
			break;
	    }
	}
	catch (Exception ex)
	{
		throw ex;
	}
	return textBoxText;
	}


	@Override
	public String getFrom(String selectorString, String by, String... attribute) {
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
    		Boolean requiresIndex = false;
    		int count = attribute.length;
    		String attr = null;
    		if (count > 0)
    		{
    			if (!TestUtils.isNullOrBlank(attribute[0]))
    			{
    				requiresIndex = true;
    				attr = attribute[0];
    			}
    			else {
    				attr = "";
				}
    		}
    		else {
    			attr = "";
			}
    	
    		return getTxtBoxValueViaJavascriptScript(webElement, attr, requiresIndex, (by.toLowerCase().contains(id) ? LocatorTypes.ID : 
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
	public String getFrom(WebElement element, String... attribute) {
    	try
    	{
    		return (String)((JavascriptExecutor)LocalDriver.getDriver()).executeScript("return argument[0].value", element);
    	}
    	catch (Exception ex)
    	{
    		throw ex;
    	}
	}

	@Override
	@DoNotCall
	public Boolean isDisplayed(String selectorString, String by, String expectedText, String... attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@DoNotCall
	public Boolean isDisplayed(WebElement element, String expectedText, String... attribute) {
		// TODO Auto-generated method stub
		return null;
	}

}
