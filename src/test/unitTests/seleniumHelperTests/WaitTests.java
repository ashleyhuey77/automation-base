package unitTests.seleniumHelperTests;

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
import seleniumHelper.enums.Condition;
import seleniumHelper.enums.WaitFor;

@Listeners(WebDriverListener.class)
public class WaitTests {
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/ExternalLibraries/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToLoad_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ELEMENT_OR_VALUE).waitOn("Test", "id", 1);
	}

	@Test
	public void verifyWaitForAttributeToContainACertainValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ATTRIBUTE_OR_VALUE).waitOn("Test", "id", Condition.CONTAINS, "someClassValue", 2, "class");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToContainACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ATTRIBUTE_OR_VALUE).waitOn("notHere", "id", Condition.CONTAINS, "someClassValue", 1, "class");
	}
	
	@Test
	public void verifyWaitForAttributeToDisappear() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test></input>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(WaitFor.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).waitOn("Test", "id", 1, "value");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToDisappear_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(WaitFor.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).waitOn("Test", "id", 1, "class");
	}
	
	@Test
	public void verifyWaitForAttributeToDisappear_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(WaitFor.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).waitOn(test, 1, "value");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToDisappear_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(WaitFor.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).waitOn(test, 1, "class");
	}
	
	@Test
	public void verifyWaitForAttributeToEqualACertainValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ATTRIBUTE_OR_VALUE).waitOn("Test", "id", Condition.EQUALS, "someClassValue", 2, "class");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToEqualACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ATTRIBUTE_OR_VALUE).waitOn("notHere", "id", Condition.EQUALS, "someClassValue", 1, "class");
	}
	
	@Test
	public void verifyWaitForAttributeToEqualACertainValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ATTRIBUTE_OR_VALUE).waitOn(test, Condition.EQUALS, "someClassValue", 2, "class");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToEqualACertainValue_PredefinedElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ATTRIBUTE_OR_VALUE).waitOn(test, Condition.EQUALS, "someClassValue", 1, "class");
	}
	
	@Test
	public void verifyWaitForElementToBeClickable() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(WaitFor.CLICKABILITY_OF_ELEMENT).waitOn("Test", "id", 1);
		
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToBeClickable_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(WaitFor.CLICKABILITY_OF_ELEMENT).waitOn("NotHere", "id", 1);
		
	}
	
	@Test
	public void verifyWaitForPageLoad() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ELEMENT_OR_VALUE).waitOn("Test", "id", 1);
		
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForPageLoad_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ELEMENT_OR_VALUE).waitOn("NotHere", "id", 1);
		
	}
	
	@Test
	public void verifyWaitForPresenceOfElementLocated() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ELEMENT_OR_VALUE).waitOn("Test", "id", 1);
		
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForOfElementLocated_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ELEMENT_OR_VALUE).waitOn("NotHere", "id", 1);
		
	}
	
	@Test
	public void waitForTextToExistInElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ELEMENT_OR_VALUE).waitOn("Test", "id", Condition.CONTAINS, "Button", 1);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void waitForTextToExistInElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test></button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ELEMENT_OR_VALUE).waitOn("Test", "id", Condition.CONTAINS, "Button", 1);
	}
	
	@Test
	public void verifyWaitForElementToBeInvisible() throws Exception
	{	
		SHelper.get().waitMethod(WaitFor.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).waitOn("Test", "id", 1);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToBeInvisible_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test></button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(WaitFor.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).waitOn("Test", "id", 1);
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
