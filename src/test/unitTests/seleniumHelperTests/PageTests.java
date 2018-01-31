package unitTests.seleniumHelperTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import common.utils.TestUtils;
import common.utils.WebDriverListener;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import seleniumHelper.SeleniumHelper;
import seleniumHelper.builders.WaitBuilder;
import seleniumHelper.enums.Location;
import seleniumHelper.enums.Via;
import seleniumHelper.enums.Wait;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;

@Listeners(WebDriverListener.class)
public class PageTests {
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	
	@Test
	public void verifyScrollToBottomOfPage() throws Exception
	{
		Locator locator = new Locator("input[name='q']");
		By by = new By("css");
		Locator locator2 = new Locator("input[value='Google Search']");
		By by2 = new By("css");
		Locator locator3 = new Locator("table[id='nav']");
		By by3 = new By("css");
		SHelper.get().enter().textInto(locator, by, "Test");
		Thread.sleep(400);
		SHelper.get().click(Via.SELENIUM).on(locator2, by2);
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
				new WaitBuilder().forAMaxTimeOf(30)).on(locator3, by3);
		String before = ((JavascriptExecutor)LocalDriver.getDriver()).executeScript("return window.pageYOffset").toString();
		SHelper.get().page().scrollTo(Location.BOTTOM_OF_PAGE);
		Thread.sleep(900);
		String after = ((JavascriptExecutor)LocalDriver.getDriver()).executeScript("return window.pageYOffset").toString();
		Assert.assertTrue(before != after, "Scroll did not work as expected");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyScrollToBottomOfPage_ThrowsException() throws Exception
	{
		LocalDriver.getDriver().close();
		SHelper.get().page().scrollTo(Location.BOTTOM_OF_PAGE);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyScrollOnPage_InvalidLocation() throws Exception
	{
		LocalDriver.getDriver().close();
		SHelper.get().page().scrollTo(Location.TESTVARIABLE);
	}
	
	@Test
	public void verifyScrollToRightOfPage() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<TABLE BORDER=0 CELLPADDING=50 CELLSPACING=50><TR>"
				+ "<TD> First Half of Text</TD><TD> Image</TD><TD> Second Half of Text</TD></TR></TABLE>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<TABLE BORDER=0 CELLPADDING=1000 CELLSPACING=1000>"
				+ "<TR><TD> First Half of Text</TD><TD> Image</TD><TD> Second Half of Text</TD></TR></TABLE>');");
		Thread.sleep(500);
		String before = ((JavascriptExecutor)LocalDriver.getDriver()).executeScript("return window.pageXOffset").toString();
		SHelper.get().page().scrollTo(Location.RIGHT_OF_PAGE);
		Thread.sleep(900);
		String after = ((JavascriptExecutor)LocalDriver.getDriver()).executeScript("return window.pageXOffset").toString();
		Assert.assertTrue(before != after, "Scroll did not work as expected");
	}
	
	@Test
	public void verifyScrollToLeftOfPage() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<TABLE BORDER=0 CELLPADDING=50 CELLSPACING=50><TR>"
				+ "<TD> First Half of Text</TD><TD> Image</TD><TD> Second Half of Text</TD></TR></TABLE>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<TABLE BORDER=0 CELLPADDING=1000 CELLSPACING=1000>"
				+ "<TR><TD> First Half of Text</TD><TD> Image</TD><TD> Second Half of Text</TD></TR></TABLE>');");
		Thread.sleep(500);
		SHelper.get().page().scrollTo(Location.RIGHT_OF_PAGE);
		Thread.sleep(900);
		String before = ((JavascriptExecutor)LocalDriver.getDriver()).executeScript("return window.pageXOffset").toString();
		SHelper.get().page().scrollTo(Location.LEFT_OF_PAGE);
		Thread.sleep(900);
		String after = ((JavascriptExecutor)LocalDriver.getDriver()).executeScript("return window.pageXOffset").toString();
		Assert.assertTrue(before != after, "Scroll did not work as expected");
	}
	
	@Test
	public void verifyScrollToTopOfPage() throws Exception
	{
		Locator locator = new Locator("input[name='q']");
		By by = new By("css");
		Locator locator2 = new Locator("input[value='Google Search']");
		By by2 = new By("css");
		Locator locator3 = new Locator("table[id='nav']");
		By by3 = new By("css");
		SHelper.get().enter().textInto(locator, by, "Test");
		Thread.sleep(400);
		SHelper.get().click(Via.SELENIUM).on(locator2, by2);
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
				new WaitBuilder().forAMaxTimeOf(30)).on(locator3, by3);
		SHelper.get().page().scrollTo(Location.BOTTOM_OF_PAGE);
		Thread.sleep(900);
		String before = ((JavascriptExecutor)LocalDriver.getDriver()).executeScript("return window.pageYOffset").toString();
		SHelper.get().page().scrollTo(Location.TOP_OF_PAGE);
		Thread.sleep(900);
		String after = ((JavascriptExecutor)LocalDriver.getDriver()).executeScript("return window.pageYOffset").toString();
		Assert.assertTrue(before != after, "Scroll did not work as expected");
	}
	
	@Test
	public void verifyRefreshThePage() throws Exception
	{
		SHelper.get().page().refresh();
		Assert.assertTrue(LocalDriver.getDriver().getWindowHandles().size() == 1, "Total tabs open is not correct");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyRefreshThePage_ThrowsException() throws Exception
	{
		LocalDriver.getDriver().close();
		SHelper.get().page().refresh();
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
