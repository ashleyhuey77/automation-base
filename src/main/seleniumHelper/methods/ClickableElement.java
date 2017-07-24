package seleniumHelper.methods;

import java.util.List;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.errorprone.annotations.DoNotCall;

import commonClasses.sharedUtils.managers.LocalDriver;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.enums.Condition;
import seleniumHelper.interfaces.IWait;

public class ClickableElement extends Commands implements IWait {

	@Override
	public void waitOn(String selectorString, String by, int i, String... attribute) throws Exception {
        try
        {
        	new WebDriverWait(LocalDriver.getDriver(), i).until(ExpectedConditions.elementToBeClickable(getByValueBasedOnUserInput(selectorString, by)));
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
	}

	@Override
	public void waitOn(WebElement element, int i, String... attribute) throws Exception {
        try
        {
        	new WebDriverWait(LocalDriver.getDriver(), i).until(ExpectedConditions.elementToBeClickable(element));
        }
        catch (WebDriverException ex)
        {
            throw ex;
        }
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
	@DoNotCall
	public void waitOn(String selectorString, String by, int expectedTotalCount, int i) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	@DoNotCall
	public void waitOn(List<WebElement> element, int expectedTotalCount, int i) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
