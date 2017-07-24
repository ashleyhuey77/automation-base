package unitTests.seleniumHelperTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;

import commonClasses.sharedUtils.TestUtils;
import commonClasses.sharedUtils.WebDriverListener;
import commonClasses.sharedUtils.managers.LocalDriver;
import commonClasses.sharedUtils.managers.SHelper;
import seleniumHelper.SeleniumHelper;
import seleniumHelper.enums.Location;

@Listeners(WebDriverListener.class)
public class PageTests {
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/ExternalLibraries/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	
	@Test
	public void verifyScrollToBottomOfPage() throws Exception
	{
		SHelper.get().page().scrollTo(Location.BOTTOM_OF_PAGE);
		Assert.assertTrue(LocalDriver.getDriver().getWindowHandles().size() == 1, "Total tabs open is not correct");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyScrollToBottomOfPage_ThrowsException() throws Exception
	{
		LocalDriver.getDriver().close();
		SHelper.get().page().scrollTo(Location.BOTTOM_OF_PAGE);
	}
	
	@Test
	public void verifyScrollToTopOfPage() throws Exception
	{
		SHelper.get().page().scrollTo(Location.TOP_OF_PAGE);
		Assert.assertTrue(LocalDriver.getDriver().getWindowHandles().size() == 1, "Total tabs open is not correct");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyScrollToTopOfPage_ThrowsException() throws Exception
	{
		LocalDriver.getDriver().close();
		SHelper.get().page().scrollTo(Location.TOP_OF_PAGE);
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
