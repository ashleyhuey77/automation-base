package tests.shelper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import shelper.vobjects.By;
import shelper.vobjects.Locator;

@Listeners(WebDriverListener.class)
public class EnterTests {
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifySendKeys_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("#Ha");
		By by = new By("css");
		
		SHelper.get().enter().textInto(locator, by, Keys.ENTER);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifySendKeys_ElementPreDefined_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		Thread.sleep(500);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().enter().textInto(test, Keys.ENTER);
	}
	
	@Test
	public void verifySendKeys() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("#Test");
		By by = new By("css");
		
		SHelper.get().enter().textInto(locator, by, Keys.ENTER);
	}
	
	@Test
	public void verifySendKeys_ElementPreDefined() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		
		SHelper.get().enter().textInto(test, Keys.ENTER);
	}
	
	@Test
	public void verifyEnterText() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("#Test");
		By by = new By("css");
		
		SHelper.get().enter().textInto(locator, by, "SomeText");
	}
	
	@Test
	public void verifyEnterText_ElementPreDefined() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		
		SHelper.get().enter().textInto(test, "SomeText");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyEnterText_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("#Ha");
		By by = new By("css");
		
		SHelper.get().enter().textInto(locator, by, "SomeText");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyEnterText_ElementPreDefined_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		Thread.sleep(500);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().enter().textInto(test, "SomeText");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyClear_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("#Ha");
		By by = new By("css");
		
		SHelper.get().enter().clear(locator, by);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyClear_ElementPreDefined_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		Thread.sleep(500);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().enter().clear(test);
	}
	
	@Test
	public void verifyClear() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("#Test");
		By by = new By("css");
		
		SHelper.get().enter().clear(locator, by);
	}
	
	@Test
	public void verifyClear_ElementPreDefined() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		
		SHelper.get().enter().clear(test);
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
