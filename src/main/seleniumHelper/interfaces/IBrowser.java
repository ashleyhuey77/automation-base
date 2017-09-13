package seleniumHelper.interfaces;

import org.openqa.selenium.WebElement;

import seleniumHelper.enums.BrowserObject;

public interface IBrowser {
	
	public void switchTo(BrowserObject object, WebElement element) throws Exception;
	
	public void switchTo(BrowserObject object, String selectorString, String by) throws Exception;
	
	public void switchTo(BrowserObject object, String name) throws Exception;
	
	public void switchTo(BrowserObject object) throws Exception;
	
	public void close(BrowserObject object) throws Exception;
	
	public void open(BrowserObject object) throws Exception;
	
	public void navigateTo(String url) throws Exception;
	
	public void waitForWindowCount(int i, int expectedCount) throws Exception;
	
	public void switchTo(BrowserObject object, int i) throws Exception;

}
