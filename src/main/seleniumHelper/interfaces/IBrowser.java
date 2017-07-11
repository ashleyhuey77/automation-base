package seleniumHelper.interfaces;

import org.openqa.selenium.WebElement;

import seleniumHelper.enums.BrowserObject;

public interface IBrowser {
	
	public void switchTo(BrowserObject object, WebElement element);
	
	public void switchTo(BrowserObject object, String selectorString, String by);
	
	public void switchTo(BrowserObject object, String name);
	
	public void switchTo(BrowserObject object) throws Exception;
	
	public void close(BrowserObject object);
	
	public void open(BrowserObject object);
	
	public void navigateTo(String url);
	
	public void waitForWindowCount(int i, int expectedCount);

}
