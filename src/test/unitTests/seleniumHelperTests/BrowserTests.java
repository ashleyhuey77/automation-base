package unitTests.seleniumHelperTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonClasses.sharedUtils.TestUtils;
import commonClasses.sharedUtils.WebDriverListener;
import commonClasses.sharedUtils.managers.LocalDriver;
import commonClasses.sharedUtils.managers.SHelper;
import seleniumHelper.SeleniumHelper;
import seleniumHelper.enums.BrowserObject;

@Listeners(WebDriverListener.class)
public class BrowserTests {
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/ExternalLibraries/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	@Test
	public void verifySwitchToDefaultContent() throws Exception {
		
		SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
		assertTrue("Total tabs open is not correct", LocalDriver.getDriver().getWindowHandles().size() == 1);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifySwitchToDefaultContent_ThrowsException() throws Exception {
		
		LocalDriver.getDriver().close();
		SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
	}
	
	@Test
	public void verifySwitchToNewWindow() throws Exception
	{
		SHelper.get().browser().open(BrowserObject.TAB);
		Thread.sleep(500);
		SHelper.get().browser().switchTo(BrowserObject.WINDOW);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifySwitchToUndefinedBrowserObject() throws Exception
	{
		SHelper.get().browser().open(BrowserObject.TAB);
		Thread.sleep(500);
		SHelper.get().browser().switchTo(BrowserObject.TAB);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifySwitchToNewWindow_ThrowsException() throws Exception
	{
		LocalDriver.getDriver().close();
		SHelper.get().browser().switchTo(BrowserObject.WINDOW);
	}
	
	@Test
	public void verifySwitchToIFrame() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<iframe id=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Thread.sleep(300);
		
		SHelper.get().browser().switchTo(BrowserObject.FRAME, "Test", "id");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifySwitchToIFrame_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<iframe id=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Thread.sleep(300);
		
		SHelper.get().browser().switchTo(BrowserObject.FRAME,"NotHere", "id");
	}
	
	@Test
	public void verifySwitchToIFrame_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<iframe id=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().browser().switchTo(BrowserObject.FRAME, test);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifySwitchToIFrame_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<iframe id=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().browser().switchTo(BrowserObject.FRAME, test);
	}
	
	@Test
	public void verifySwitchToFrameByName() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<iframe name=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Thread.sleep(300);
		
		SHelper.get().browser().switchTo(BrowserObject.FRAME, "Test");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifySwitchToFrameByName_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<iframe name=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Thread.sleep(300);
		
		SHelper.get().browser().switchTo(BrowserObject.FRAME,"NotHere");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyOpenNewTab_ThrowsException() throws Exception {
		
		LocalDriver.getDriver().close();
		SHelper.get().browser().open(BrowserObject.TAB);
	}
	
	@Test
	public void verifyCloseOpenTabs() throws Exception {
		
		Thread.sleep(500);
		SHelper.get().browser().open(BrowserObject.TAB);
		Thread.sleep(500);
		SHelper.get().browser().open(BrowserObject.TAB);
		Thread.sleep(500);
		SHelper.get().browser().open(BrowserObject.TAB);
		Thread.sleep(500);
		SHelper.get().browser().open(BrowserObject.TAB);
		Thread.sleep(500);
		
		SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
		SHelper.get().browser().close(BrowserObject.TAB);
		SHelper.get().browser().waitForWindowCount(30, 1);
		assertTrue("Total tabs open is not correct", LocalDriver.getDriver().getWindowHandles().size() == 1);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyCloseOpenTabs_ThrowsException() throws Exception {
		
		LocalDriver.getDriver().close();
		SHelper.get().browser().close(BrowserObject.TAB);
	}
	
	@Test
	public void verifyNavigateToUrl() throws Exception
	{
		SHelper.get().browser().navigateTo("https://www.facebook.com/");
		Thread.sleep(400);
		
		assertEquals("The url values do not match", "https://www.facebook.com/", LocalDriver.getDriver().getCurrentUrl());
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyNavigateToUrl_ThrowsException() throws Exception
	{
		LocalDriver.getDriver().close();
		SHelper.get().browser().navigateTo("Test");
	}
	
	@Test
	public void verifyWaitForWindowCount() throws Exception
	{
		SHelper.get().browser().waitForWindowCount(1, 1);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForWindowCount_ThrowsException() throws Exception
	{
		SHelper.get().browser().waitForWindowCount(1, 2);
	}
	
	@AfterMethod
	public void afterScenario() {
/*		try {
			if(LocalDriver.getDriver() != null) {
				LocalDriver.getDriver().quit();
			}
		} catch (Exception e) {
			if(LocalDriver.getDriver() != null) {
				LocalDriver.getDriver().quit();
			}
		}
		finally {
			if(LocalDriver.getDriver() != null) {
				LocalDriver.getDriver().quit();
			}
		}*/
	}

}
