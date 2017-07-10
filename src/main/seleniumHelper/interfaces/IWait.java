package seleniumHelper.interfaces;

import java.util.List;

import org.openqa.selenium.WebElement;

import seleniumHelper.enums.Condition;

public interface IWait {
	
	public void waitOn(String selectorString, String by, int i, String... attribute);
	
	public void waitOn(WebElement element, int i, String... attribute);
	
	public void waitOn(String selectorString, String by, Condition condition, String expectedValue, int i, String... attribute) throws Exception;

	public void waitOn(WebElement element, Condition condition, String expectedValue, int i, String... attribute) throws Exception;
	
	public void waitOn(String selectorString, String by, int expectedTotalCount, int i);
	
	public void waitOn(List<WebElement> element, int expectedTotalCount, int i);
}
