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
public class Wait_PresentElementText_Tests {
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
	public void verifyWaitForElementToContainACertainValue() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClassValue>Test</button>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.CONTAIN).value("Test").forAMaxTimeOf(2)).on(element);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToContainACertainValue_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.CONTAIN).value("Nope").forAMaxTimeOf(1)).on(element);
	}

	@Test
	public void verifyWaitForElementToContainACertainValue_PredefinedElement() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.CONTAIN).value("Test").forAMaxTimeOf(2)).on(test);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToContainACertainValue__PredefinedElement_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.CONTAIN).value("Nope").forAMaxTimeOf(1)).on(test);
	}

	@Test
	public void verifyWaitForElementToEqualACertainValue() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClassValue>Test</button>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.EQUAL).value("Test").forAMaxTimeOf(2)).on(element);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToEqualACertainValue_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.EQUAL).value("Tes").forAMaxTimeOf(1)).on(element);
	}

	@Test
	public void verifyWaitForElementToEqualACertainValue_PredefinedElement() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.EQUAL).value("Test").forAMaxTimeOf(2)).on(test);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToEqualACertainValue__PredefinedElement_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.EQUAL).value("Tes").forAMaxTimeOf(1)).on(test);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToHaveValueInvalidCondition_PreDefinedWebElement_ExceptionThrown()
			throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.INVALID_CONDITION).value("help").forAMaxTimeOf(2)).on(test);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToHaveValueInvalidCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.INVALID_CONDITION).value("help").forAMaxTimeOf(2)).on(element);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToHaveValueNullCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder().value("help").forAMaxTimeOf(2))
				.on(element);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToHaveValueNullCondition_PreDefinedWebElement_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder().value("help").forAMaxTimeOf(2))
				.on(test);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementToBePresentInvalidWaitTime_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder())
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementToBePresentNonNullAttribute_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
				.forAMaxTimeOf(1)
				.value("String")
				.to(Condition.EQUAL)
				.forAttribute("Test"))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementTextToBePresentEmptyValue_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
				.value("")
				.to(Condition.EQUAL)
				.forAMaxTimeOf(1))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementToBePresentNonNullCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
				.forAMaxTimeOf(1)
				.to(Condition.CONTAIN))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementToBePresentNonNullTotalCount_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
				.value("String")
				.to(Condition.EQUAL)
				.forAMaxTimeOf(1)
				.withACountOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=UnsupportedOperationException.class)
	public void verifyWaitForElementToBePresentList_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
				.to(Condition.EQUAL)
				.forAMaxTimeOf(2)
				.value("something"))
				.on(new ArrayList<WebElement>());
	}

	@AfterMethod
	public void afterScenario() {
		
	}
}