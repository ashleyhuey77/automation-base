package tests.shelper;

import java.util.ArrayList;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import common.utils.TestUtils;
import common.utils.WebDriverListener;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import log.TestException;
import shelper.SeleniumHelper;
import shelper.builders.WaitBuilder;
import shelper.enums.Condition;
import shelper.enums.Wait;
import shelper.vobjects.By;
import shelper.vobjects.Locator;
import shelper.vobjects.TestElement;

@Listeners(WebDriverListener.class)
public class Wait_PresentAttribute_Tests {
	
	Locator locator = new Locator("Test");
	By by = new By(How.ID);
	TestElement element = new TestElement(locator, by);
	
	@BeforeMethod
	public void beforeScenario() {
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	@Test
	public void verifyAttributeIsPresent() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
			.forAttribute("class")
        		.forAMaxTimeOf(1))
			.on(element);
	}
	
	@Test
	public void verifyAttributeIsPresent_PredefinedElement() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		WebElement element2 = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
			.forAttribute("class")
        		.forAMaxTimeOf(1))
			.on(element2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyAttributeIsPresent_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
			.forAttribute("style")
        		.forAMaxTimeOf(1))
			.on(element);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyAttributeIsPresent_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		WebElement element2 = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
			.forAttribute("style")
        		.forAMaxTimeOf(1))
			.on(element2);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForPresentAttributeToHaveNullBaseAttribute_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForPresentAttributeToHaveEmptyStringBaseAttribute_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
				.forAttribute("")
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForPresentAttributeToHaveNullTime_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
				.forAttribute("class"))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForPresentAttributeConditionNotNull_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.CONTAIN)
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=UnsupportedOperationException.class)
	public void verifyWaitForPresentAttributeList_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
				.forAttribute("class")
				.forAMaxTimeOf(2))
				.on(new ArrayList<WebElement>());
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForPresentAttributeValueNotNull_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
				.forAttribute("class")
				.value("Something")
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForPresentAttributeTotalCountNotNull_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
				.forAttribute("class")
				.forAMaxTimeOf(1)
				.withACountOf(4))
				.on(element);
	}
	
	@AfterMethod
	public void afterScenario() {
		
	}

}
