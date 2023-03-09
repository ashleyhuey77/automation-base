package tests.shelper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.config.SHelper;
import com.config.TestException;
import com.config.setup.browser.LocalDriver;
import com.config.setup.browser.WebDriverListener;
import com.selenium.By;
import com.selenium.Locator;
import com.selenium.SeleniumHelper;
import com.selenium.TestElement;
import com.selenium.wait.Condition;
import com.selenium.wait.Wait;
import com.selenium.wait.WaitBuilder;
import com.utils.TestUtils;

import java.time.Duration;

@Listeners(WebDriverListener.class)
public class Wait_NonPresentElement_Tests {

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
	public void verifyWaitForElementToDisappear() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test></input>');");
		Locator locator2 = new Locator("Help");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(2))).on(element2);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToDisappear_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<button id=Test></button>');");
		Thread.sleep(600);

		SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}

	@Test
	public void verifyWaitForElementToDisappear_PredefinedElement() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		SHelper.get().page().refresh();
		Thread.sleep(1000);

		SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(2))).on(test);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToDisappear_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClassValue></button>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(2))).on(test);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementNotToBePresentInvalidWaitTime_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, new WaitBuilder())
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementNotToBePresentNonNullAttribute_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAMaxTimeOf(Duration.ofSeconds(2))
				.forAttribute("Test"))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementNotToBePresentNonNullValue_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAMaxTimeOf(Duration.ofSeconds(2))
				.value("Test"))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementNotToBePresentNonNullCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAMaxTimeOf(Duration.ofSeconds(2))
				.to(Condition.CONTAIN))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementNotToBePresentNonNullTotalCount_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAMaxTimeOf(Duration.ofSeconds(2))
				.withACountOf(2))
				.on(element);
	}

	@AfterMethod
	public void afterScenario() {

	}

}
