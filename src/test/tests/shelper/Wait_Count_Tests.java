package tests.shelper;

import org.openqa.selenium.JavascriptExecutor;
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

@Listeners(WebDriverListener.class)
public class Wait_Count_Tests {
	
	Locator locator = new Locator("Test");
	By by = new By(How.ID);
	TestElement element = new TestElement(locator, by);

	@BeforeMethod
	public void beforeScenario() {
		SHelper.set(new SeleniumHelper());
		System.setProperty("webdriver.chrome.driver",
				TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
		LocalDriver.getDriver().get("http://www.google.com");
	}
	
	@Test
	public void verifyWaitOnElementCount() throws Exception {
		Locator locator2 = new Locator("About");
		By by2 = new By(How.LINK_TEXT);
		TestElement element2 = new TestElement(locator2, by2);
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder().withACountOf(1).forAMaxTimeOf(5)).on(element2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitOnElementCount_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder().withACountOf(1).forAMaxTimeOf(1)).on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForCountInvalidWaitTime_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder().withACountOf(1))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForCountInvalidTotalCount_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder()
				.forAMaxTimeOf(1))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForCountNonNullValue_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder()
				.forAMaxTimeOf(1)
				.withACountOf(1)
				.value("Test"))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForCountNonNullCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder()
				.forAMaxTimeOf(1)
				.withACountOf(1)
				.to(Condition.CONTAIN))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForCountNonNullAttribute_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder()
				.forAMaxTimeOf(1)
				.withACountOf(1)
				.forAttribute(""))
				.on(element);
	}
	
	@Test(expectedExceptions=UnsupportedOperationException.class)
	public void verifyWaitForCountPredefinedWebElement_ExceptionThrown() throws Exception {
		Locator locator2 = new Locator("About");
		By by2 = new By(How.LINK_TEXT);
		WebElement element2 = SHelper.get().element().get(new TestElement(locator2, by2));
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder()
				.forAMaxTimeOf(2)
				.withACountOf(1))
				.on(element2);
	}
	
	@AfterMethod
	public void afterScenario() {
		
	}

}
