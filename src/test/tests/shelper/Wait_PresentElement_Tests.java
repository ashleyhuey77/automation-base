package tests.shelper;

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

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToLoad_ExceptionThrown() throws Exception {
		TestElement element2 = new TestElement(new Locator(""), new By("id"));
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(1)).on(element2);
	}

	@Test
	public void verifyElementIsPresent() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(2)).on(element);
	}

	@Test
	public void verifyElementIsPresent_PredefinedElement() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		WebElement element2 = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(1)).on(element2);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyElementIsPresent_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Locator locator2 = new Locator("NotHere");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(1)).on(element2);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyElementIsPresent_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		WebElement element2 = SHelper.get().element().get(element);
		SHelper.get().page().refresh();
		Thread.sleep(900);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(1)).on(element2);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementToBePresentInvalidWaitTime_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder())
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementToBePresentNonNullAttribute_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder()
				.forAMaxTimeOf(1)
				.forAttribute("Test"))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementToBePresentNonNullValue_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder()
				.forAMaxTimeOf(1)
				.value("Test"))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementToBePresentNonNullCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder()
				.forAMaxTimeOf(1)
				.to(Condition.CONTAIN))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementToBePresentNonNullTotalCount_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder()
				.forAMaxTimeOf(1)
				.withACountOf(2))
				.on(element);
	}

	@AfterMethod
	public void afterScenario() {

	}

}
