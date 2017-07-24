package seleniumHelper.methods;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import seleniumHelper.abstracts.Commands;
import seleniumHelper.interfaces.IText;

public class AttributeText extends Commands implements IText {

	@Override
	public String getFrom(String selectorString, String by, String... attribute) throws Exception {
		return getElement(selectorString, by).getAttribute(attribute[0]);
	}

	@Override
	public String getFrom(WebElement element, String... attribute) throws Exception {
		return element.getAttribute(attribute[0]);
	}

	@Override
	public Boolean isDisplayed(String selectorString, String by, String expectedText, String... attribute) throws Exception {
		Boolean result = false;
    	try
    	{
    		String value = getElement(selectorString, by).getAttribute(attribute[0]);
    		if (value != null)
    		{
    			if (value.toLowerCase().trim().contains(expectedText.toLowerCase().trim()))
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

	@Override
	public Boolean isDisplayed(WebElement element, String expectedText, String... attribute) throws Exception {
    	Boolean result = false;
    	try
    	{
    		String value = element.getAttribute(attribute[0]);
    		if (value != null)
    		{
    			if (value.toLowerCase().trim().contains(expectedText.toLowerCase().trim()))
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

}
