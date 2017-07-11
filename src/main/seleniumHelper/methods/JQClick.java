package seleniumHelper.methods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import commonClasses.sharedUtils.managers.LocalDriver;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.interfaces.IClick;

public class JQClick extends Commands implements IClick {

	@Override
	public void on(String selectorString, String by) {
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
        	((JavascriptExecutor)LocalDriver.getDriver()).executeScript("$('" + webElement + "').click();");
        }
        catch (Exception ex)
        {
            throw ex;
        }
	}

	@Override
	public void on(WebElement element) {
        try
        {
        	((JavascriptExecutor)LocalDriver.getDriver()).executeScript("$arguments[0].click();", element);
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
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("$('" + selectorString + "')["+index+"].click();");
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
    		String webElement = null;
    		if (selectorString.contains("'"))
    		{
    			webElement = selectorString.replace("'", "");
    		}
    		else
    		{
    			webElement = selectorString;
    		}
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("$('" + webElement + "')["+Integer.toString(index)+"].click();");
		} 
		catch (Exception ex) 
		{
			throw ex;
		}
	}

}
