package tests.shelper;

import com.utils.ex.SeleniumException;
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
public class Wait_PresentElement_Tests {

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

	@Test(expectedExceptions = SeleniumException.class)
	public void verifyWaitForElementToLoad_ExceptionThrown() throws Exception {
		TestElement element2 = new TestElement(new Locator(""), new By("id"));
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(2))).on(element2);
	}

	@Test
	public void verifyElementIsPresent() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}

	@Test
	public void verifyElementIsPresent_MultipleElements() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		Locator locator2 = new Locator("#nope");
		By by2 = new By(How.CSS);
		TestElement element2 = new TestElement(locator2, by2);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(2))).on(element, element2);
	}

	@Test(expectedExceptions = SeleniumException.class)
	public void verifyElementIsPresent_MultipleElements_NoElementsFound() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		Locator locator2 = new Locator("#nope");
		By by2 = new By(How.CSS);
		TestElement element2 = new TestElement(locator2, by2);
		Locator locator3 = new Locator("#blah");
		By by3 = new By(How.CSS);
		TestElement element3 = new TestElement(locator3, by3);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(2))).on(element2, element3);
	}

	@Test
	public void verifyElementIsPresent_PredefinedElement() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		WebElement element2 = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(2))).on(element2);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyElementIsPresent_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Locator locator2 = new Locator("NotHere");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(2))).on(element2);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyElementIsPresent_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		WebElement element2 = SHelper.get().element().get(element);
		SHelper.get().page().refresh();
		Thread.sleep(900);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(2))).on(element2);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementToBePresentInvalidWaitTime_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder())
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementToBePresentNonNullAttribute_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder()
				.forAMaxTimeOf(Duration.ofSeconds(2))
				.forAttribute("Test"))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementToBePresentNonNullValue_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder()
				.forAMaxTimeOf(Duration.ofSeconds(2))
				.value("Test"))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementToBePresentNonNullCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder()
				.forAMaxTimeOf(Duration.ofSeconds(2))
				.to(Condition.CONTAIN))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementToBePresentNonNullTotalCount_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder()
				.forAMaxTimeOf(Duration.ofSeconds(2))
				.withACountOf(2))
				.on(element);
	}

	@AfterMethod
	public void afterScenario() {

	}

}
