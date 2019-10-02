package tests.shelper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
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
import com.warnermedia.selenium.shared.Via;
import com.warnermedia.utils.TestUtils;

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
		TestElement element = new TestElement(locator, by);
		
		SHelper.get().enter(Via.SELENIUM).textInto(element, Keys.ENTER);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifySendKeys_ElementPreDefined_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		Thread.sleep(500);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().enter(Via.SELENIUM).textInto(test, Keys.ENTER);
	}
	
	@Test
	public void verifySendKeys() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("#Test");
		By by = new By("css");
		TestElement element = new TestElement(locator, by);
		
		SHelper.get().enter(Via.SELENIUM).textInto(element, Keys.ENTER);
	}
	
	@Test
	public void verifySendKeys_Javascript() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test></input>');");
		Locator locator = new Locator("#Test");
		By by = new By("css");
		TestElement element = new TestElement(locator, by);
		
		SHelper.get().enter(Via.JAVASCRIPT).textInto(element, "New Text");
	}
	
	@Test
	public void verifySendKeys_ElementPreDefined() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().enter(Via.SELENIUM).textInto(test, Keys.ENTER);
	}
	
	@Test
	public void verifyEnterText() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("#Test");
		By by = new By("css");
		TestElement element = new TestElement(locator, by);
		
		SHelper.get().enter(Via.SELENIUM).textInto(element, "SomeText");
	}
	
	@Test
	public void verifyEnterText_ElementPreDefined() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().enter(Via.SELENIUM).textInto(test, "SomeText");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyEnterText_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("#Ha");
		By by = new By("css");
		TestElement element = new TestElement(locator, by);
		
		SHelper.get().enter(Via.SELENIUM).textInto(element, "SomeText");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyEnterText_ElementPreDefined_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		Thread.sleep(500);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().enter(Via.SELENIUM).textInto(test, "SomeText");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyClear_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("#Ha");
		By by = new By("css");
		TestElement element = new TestElement(locator, by);
		
		SHelper.get().enter(Via.SELENIUM).clear(element);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyClear_ElementPreDefined_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		Thread.sleep(500);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().enter(Via.SELENIUM).clear(test);
	}
	
	@Test
	public void verifyClear() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("#Test");
		By by = new By("css");
		TestElement element = new TestElement(locator, by);
		
		SHelper.get().enter(Via.SELENIUM).clear(element);
	}
	
	@Test
	public void verifyClear_ElementPreDefined() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().enter(Via.SELENIUM).clear(test);
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
