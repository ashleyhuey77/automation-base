package tests.shelper;

import com.warnermedia.utils.ex.SeleniumException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.driver.WebDriverListener;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.SeleniumHelper;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.wait.Condition;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;
import com.warnermedia.utils.TestUtils;

import java.time.Duration;

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
        		.forAMaxTimeOf(Duration.ofSeconds(2)))
			.on(element);
	}
	
	@Test
	public void verifyAttributeIsPresent_PredefinedElement() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		WebElement element2 = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
			.forAttribute("class")
        		.forAMaxTimeOf(Duration.ofSeconds(2)))
			.on(element2);
	}
	
	@Test(expectedExceptions= SeleniumException.class)
	public void verifyAttributeIsPresent_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
			.forAttribute("style")
        		.forAMaxTimeOf(Duration.ofSeconds(2)))
			.on(element);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyAttributeIsPresent_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		WebElement element2 = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
			.forAttribute("style")
        		.forAMaxTimeOf(Duration.ofSeconds(2)))
			.on(element2);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForPresentAttributeToHaveNullBaseAttribute_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
				.forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForPresentAttributeToHaveEmptyStringBaseAttribute_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
				.forAttribute("")
				.forAMaxTimeOf(Duration.ofSeconds(2)))
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
				.forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForPresentAttributeValueNotNull_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
				.forAttribute("class")
				.value("Something")
				.forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForPresentAttributeTotalCountNotNull_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
				.forAttribute("class")
				.forAMaxTimeOf(Duration.ofSeconds(2))
				.withACountOf(4))
				.on(element);
	}
	
	@AfterMethod
	public void afterScenario() {
		
	}

}
