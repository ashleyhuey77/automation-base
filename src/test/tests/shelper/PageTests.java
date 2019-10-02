package tests.shelper;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.driver.WebDriverListener;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.SeleniumHelper;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.page.Location;
import com.warnermedia.selenium.shared.Via;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;
import com.warnermedia.utils.TestUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;

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
		TestElement element = new TestElement(locator, by);
		Locator locator2 = new Locator("input[value='Google Search']");
		By by2 = new By("css");
		TestElement element2 = new TestElement(locator2, by2);
		Locator locator3 = new Locator("table[id='nav']");
		By by3 = new By("css");
		TestElement element3 = new TestElement(locator3, by3);
		SHelper.get().enter(Via.SELENIUM).textInto(element, "Test");
		Thread.sleep(400);
		SHelper.get().click(Via.SELENIUM).on(element2);
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
				new WaitBuilder().forAMaxTimeOf(30)).on(element3);
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
		TestElement element = new TestElement(locator, by);
		Locator locator2 = new Locator("input[value='Google Search']");
		By by2 = new By("css");
		TestElement element2 = new TestElement(locator2, by2);
		Locator locator3 = new Locator("table[id='nav']");
		By by3 = new By("css");
		TestElement element3 = new TestElement(locator3, by3);
		SHelper.get().enter(Via.SELENIUM).textInto(element, "Test");
		Thread.sleep(400);
		SHelper.get().click(Via.SELENIUM).on(element2);
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
				new WaitBuilder().forAMaxTimeOf(30)).on(element3);
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
