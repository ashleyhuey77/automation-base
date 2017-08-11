package seleniumHelper.methods;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.errorprone.annotations.DoNotCall;

import commonClasses.sharedUtils.managers.LocalDriver;
import commonClasses.sharedUtils.managers.SHelper;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.enums.Condition;
import seleniumHelper.enums.WaitFor;
import seleniumHelper.interfaces.IWait;

public class ElementCount extends Commands implements IWait {

    @Override
    @DoNotCall
    public void waitOn(String selectorString, String by, int i, String... attribute) throws Exception {
	// TODO Auto-generated method stub

    }

    @Override
    @DoNotCall
    public void waitOn(WebElement element, int i, String... attribute) throws Exception {
	// TODO Auto-generated method stub

    }

    @Override
    @DoNotCall
    public void waitOn(String selectorString, String by, Condition condition, String expectedValue, int i,
	    String... attribute) throws Exception {
	// TODO Auto-generated method stub 
    }

    @Override
    @DoNotCall
    public void waitOn(WebElement element, Condition condition, String expectedValue, int i, String... attribute)
	    throws Exception {
	// TODO Auto-generated method stub

    }

	@Override
	public void waitOn(String selectorString, String by, int expectedTotalCount, int i) throws Exception {
		try
    	{
    		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(),i);
    		wait.until(new ExpectedCondition<Boolean>() {
    		            public Boolean apply(WebDriver driver) {
    		            	try {
								SHelper.get().page().refresh();
							} catch (Exception e1) {

							}
    		            	try {
								SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ELEMENT_OR_VALUE).waitOn(selectorString, by, 30);
							} catch (Exception e) {

							}
    		            	int actualElementCount = getElements(selectorString, by).size();
    		                if(actualElementCount == expectedTotalCount)
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
	public void waitOn(List<WebElement> element, int expectedTotalCount, int i) throws Exception {
		try
    	{
    		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(),i);
    		wait.until(new ExpectedCondition<Boolean>() {
    		            public Boolean apply(WebDriver driver) {
    		            	try {
								SHelper.get().page().refresh();
							} catch (Exception e1) {
								
							}
    		            	try {
								SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ELEMENT_OR_VALUE).waitOn(element.get(0), 30);
							} catch (Exception e) {
							}
    		            	int actualElementCount = element.size();
    		                if(actualElementCount == expectedTotalCount)
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

}
