package unitTests.seleniumHelperTests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

@Listeners(WebDriverListener.class)
public class EnterTests {
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/ExternalLibraries/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifySendKeys_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		String test = "#Ha";
		
		SHelper.get().enter().textInto(test, "cssSelector", Keys.ENTER);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifySendKeys_ElementPreDefined_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		Thread.sleep(500);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().enter().textInto(test, Keys.ENTER);
	}
	
	@Test
	public void verifySendKeys() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		String test = "#Test";
		
		SHelper.get().enter().textInto(test, "cssSelector", Keys.ENTER);
	}
	
	@Test
	public void verifySendKeys_ElementPreDefined() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().enter().textInto(test, Keys.ENTER);
	}
	
	@Test
	public void verifyEnterText() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		String test = "#Test";
		
		SHelper.get().enter().textInto(test, "cssSelector", "SomeText");
	}
	
	@Test
	public void verifyEnterText_ElementPreDefined() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().enter().textInto(test, "SomeText");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyEnterText_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		String test = "#Ha";
		
		SHelper.get().enter().textInto(test, "cssSelector", "SomeText");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyEnterText_ElementPreDefined_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		Thread.sleep(500);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().enter().textInto(test, "SomeText");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyClear_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		String test = "#Ha";
		
		SHelper.get().enter().clear(test, "cssSelector");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyClear_ElementPreDefined_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		Thread.sleep(500);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().enter().clear(test);
	}
	
	@Test
	public void verifyClear() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		String test = "#Test";
		
		SHelper.get().enter().clear(test, "cssSelector");
	}
	
	@Test
	public void verifyClear_ElementPreDefined() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		
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
