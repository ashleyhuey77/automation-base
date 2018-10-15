package tests.shelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import common.utils.TestUtils;
import common.utils.WebDriverListener;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import shelper.SeleniumHelper;
import shelper.enums.BrowserObject;
import shelper.enums.Via;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

@Listeners(WebDriverListener.class)
public class BrowserTests {
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
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
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().switchTo(BrowserObject.WINDOW);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifySwitchToUndefinedBrowserObject() throws Exception
	{
		SHelper.get().browser().open();
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
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(300);
		
		SHelper.get().browser().switchTo(BrowserObject.FRAME, locator, by);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifySwitchToIFrame_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<iframe id=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Locator locator = new Locator("NotHere");
		By by = new By("id");
		Thread.sleep(300);
		
		SHelper.get().browser().switchTo(BrowserObject.FRAME,locator, by);
	}
	
	@Test
	public void verifySwitchToIFrame_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<iframe id=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(locator, by);
		
		SHelper.get().browser().switchTo(BrowserObject.FRAME, test);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifySwitchToIFrame_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<iframe id=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(locator, by);
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
		SHelper.get().browser().open();
	}
	
	@Test
	public void verifyCloseOpenTabs() throws Exception {
		
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);
		
		SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
		SHelper.get().browser().close();
		SHelper.get().browser().waitForWindowCount(30, 1);
		assertTrue("Total tabs open is not correct", LocalDriver.getDriver().getWindowHandles().size() == 1);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyCloseOpenTabs_ThrowsException() throws Exception {
		
		LocalDriver.getDriver().close();
		SHelper.get().browser().close();
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
	
	@Test
	public void verifySwitchToSpecificWindow() throws Exception {
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);
		
		SHelper.get().browser().switchTo(BrowserObject.WINDOW, 0);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifySwitchToSpecificWindow_ThrowException() throws Exception {
		SHelper.get().browser().open();
		Thread.sleep(500);
		
		SHelper.get().browser().switchTo(BrowserObject.WINDOW, 8);
	}
	
	@Test
	public void verifySwitchToAlertAndAccept() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<p>Click the button to display an alert box.</p>" + 
				"<button id=Test onclick=myFunction()>Try it</button>" + 
				"<script>" + 
				"function myFunction() {" + 
				"    alert(\"Hello! I am an alert box!\");" + 
				"}" + 
				"</script>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(700);
		SHelper.get().click(Via.SELENIUM).on(locator, by);
		Thread.sleep(500);
		SHelper.get().browser().switchTo(BrowserObject.ALERT);
	}
	
	@Test
	public void verifyBrowserBackFunctionality() throws Exception {
		SHelper.get().browser().navigateTo("https://www.facebook.com/");
		Thread.sleep(400);
		SHelper.get().browser().back();
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyBack_ThrowsException() throws Exception
	{
		SHelper.get().browser().navigateTo("https://www.facebook.com/");
		Thread.sleep(400);
		LocalDriver.getDriver().close();
		SHelper.get().browser().navigateTo("Test");
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
