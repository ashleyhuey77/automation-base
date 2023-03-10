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
	
	@Test
	public void verifyWaitForElementToNotContainACertainValue_List() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClassValue>Test</button>');");
		((JavascriptExecutor) LocalDriver.getDriver())
		.executeScript("document.write('<button id=Test class=someClassValue>Test 2</button>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.CONTAIN).value("Cool").indexOf("0").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}
	
	@Test
	public void verifyWaitForElementToNotEqualACertainValue_List() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClassValue>Test</button>');");
		((JavascriptExecutor) LocalDriver.getDriver())
		.executeScript("document.write('<button id=Test class=someClassValue>Nothing</button>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.EQUAL).value("Cool").indexOf("0").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}
	
	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToNotContainACertainValue_List_Exception() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClassValue>Test</button>');");
		((JavascriptExecutor) LocalDriver.getDriver())
		.executeScript("document.write('<button id=Test class=someClassValue>Test 2</button>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.CONTAIN).value("Test").indexOf("0").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}
	
	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToNotEqualACertainValue_List_Exception() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClassValue>Test</button>');");
		((JavascriptExecutor) LocalDriver.getDriver())
		.executeScript("document.write('<button id=Test class=someClassValue>Nothing</button>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.EQUAL).value("Test").indexOf("0").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToNotHaveValueInvalidCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.INVALID_CONDITION).value("help").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToNotHaveValueInvalidCondition_PreDefinedWebElement_ExceptionThrown()
			throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.INVALID_CONDITION).value("help").forAMaxTimeOf(Duration.ofSeconds(2))).on(test);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToNotHaveValueNullCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder().value("help").forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToNotHaveValueNullCondition_PreDefinedWebElement_ExceptionThrown()
			throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder().value("help").forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(test);
	}

	@Test
	public void verifyWaitForElementValueToNotContainACertainValue() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.CONTAIN).value("help").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToNotContainACertainValue_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.CONTAIN).value("Test").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}

	@Test
	public void verifyWaitForElementValueToNotEqualACertainValue() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.EQUAL).value("help").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToNotEqualACertainValue_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.EQUAL).value("Test").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}

	@Test
	public void verifyWaitForElementToNotContainACertainValue_PredefinedElement() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=SomeClass>Help</button>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.CONTAIN).value("Test").forAMaxTimeOf(Duration.ofSeconds(2))).on(test);
	}

	@Test
	public void verifyWaitForElementToNotContainACertainValue() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=SomeClass>Help</button>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.CONTAIN).value("Test").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToNotContainACertainValue_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.CONTAIN).value("Test").forAMaxTimeOf(Duration.ofSeconds(2))).on(test);
	}

	@Test
	public void verifyWaitForElementToNotEqualACertainValue_PredefinedElement() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=SomeClass>Help</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.EQUAL).value("Test").forAMaxTimeOf(Duration.ofSeconds(2))).on(test);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToNotEqualACertainValue_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().to(Condition.EQUAL).value("Test").forAMaxTimeOf(Duration.ofSeconds(2))).on(test);
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
				.forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementTextNotToBePresentEmptyValue_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.value("")
				.to(Condition.EQUAL)
				.forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementTextNotToBePresentNonNullAttribute_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("Something")
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementTextNotToBePresentNonNullExpectedCount_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(Duration.ofSeconds(2))
				.withACountOf(2))
				.on(element);
	}

	@AfterMethod
	public void afterScenario() {

	}

}
