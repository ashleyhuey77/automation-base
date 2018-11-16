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
public class Wait_NonPresentElementText_Tests {

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

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToNotHaveValueInvalidCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.INVALID_CONDITION).value("help").forAMaxTimeOf(2)).on(element);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToNotHaveValueInvalidCondition_PreDefinedWebElement_ExceptionThrown()
			throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.INVALID_CONDITION).value("help").forAMaxTimeOf(2)).on(test);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToNotHaveValueNullCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder().value("help").forAMaxTimeOf(2))
				.on(element);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToNotHaveValueNullCondition_PreDefinedWebElement_ExceptionThrown()
			throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder().value("help").forAMaxTimeOf(2))
				.on(test);
	}

	@Test
	public void verifyWaitForElementValueToNotContainACertainValue() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.CONTAIN).value("help").forAMaxTimeOf(2)).on(element);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToNotContainACertainValue_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.CONTAIN).value("Test").forAMaxTimeOf(1)).on(element);
	}

	@Test
	public void verifyWaitForElementValueToNotEqualACertainValue() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.EQUAL).value("help").forAMaxTimeOf(2)).on(element);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToNotEqualACertainValue_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.EQUAL).value("Test").forAMaxTimeOf(1)).on(element);
	}

	@Test
	public void verifyWaitForElementToNotContainACertainValue_PredefinedElement() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=SomeClass>Help</button>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.CONTAIN).value("Test").forAMaxTimeOf(2)).on(test);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToNotContainACertainValue_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.CONTAIN).value("Test").forAMaxTimeOf(1)).on(test);
	}

	@Test
	public void verifyWaitForElementToNotEqualACertainValue_PredefinedElement() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=SomeClass>Help</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.EQUAL).value("Test").forAMaxTimeOf(2)).on(test);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToNotEqualACertainValue_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.EQUAL).value("Test").forAMaxTimeOf(1)).on(test);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementTextNotToBePresentInvalidWaitTime_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.to(Condition.EQUAL)
				.value("someClassValue"))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementTextNotToBePresentNullValue_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.to(Condition.EQUAL)
				.forAMaxTimeOf(1))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementTextNotToBePresentEmptyValue_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.value("")
				.to(Condition.EQUAL)
				.forAMaxTimeOf(1))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementTextNotToBePresentNonNullAttribute_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("Something")
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(1))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementTextNotToBePresentNonNullExpectedCount_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(1)
				.withACountOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=UnsupportedOperationException.class)
	public void verifyWaitForElementTextNotToBePresentPredefinedWebElement_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(1))
				.on(new ArrayList<WebElement>());
	}

	@AfterMethod
	public void afterScenario() {

	}

}
