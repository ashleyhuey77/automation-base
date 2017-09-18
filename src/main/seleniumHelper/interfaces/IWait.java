package seleniumHelper.interfaces;

import java.util.List;

import org.openqa.selenium.WebElement;

import seleniumHelper.enums.Condition;

public interface IWait {
	
	public void on(String selectorString, String by, int i, String... attribute) throws Exception;
	
	public void on(WebElement element, int i, String... attribute) throws Exception;

	public void on(String selectorString, String by, int expectedTotalCount, int i) throws Exception;
	
	public void on(List<WebElement> element, int expectedTotalCount, int i) throws Exception;
	
	public void on(String selectorString, String by, Condition condition, String expectedValue, int i, String... attribute) throws Exception;

	public void on(WebElement element, Condition condition, String expectedValue, int i, String... attribute) throws Exception;
}
