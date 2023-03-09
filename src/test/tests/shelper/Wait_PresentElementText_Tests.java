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
				new WaitBuilder().to(Condition.CONTAIN).value("Test").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}
	
	@Test
	public void verifyWaitForElementToContainACertainValue_List() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClassValue>Test</button>');");
		((JavascriptExecutor) LocalDriver.getDriver())
		.executeScript("document.write('<button id=Test class=someClassValue>Test 2</button>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.CONTAIN).value("Test").indexOf("0").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}
	
	@Test
	public void verifyWaitForElementToEqualACertainValue_List() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClassValue>Test</button>');");
		((JavascriptExecutor) LocalDriver.getDriver())
		.executeScript("document.write('<button id=Test class=someClassValue>Nothing</button>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.EQUAL).value("Test").indexOf("0").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}
	
	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToContainACertainValue_List_Exception() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClassValue>Test</button>');");
		((JavascriptExecutor) LocalDriver.getDriver())
		.executeScript("document.write('<button id=Test class=someClassValue>Test 2</button>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.CONTAIN).value("Hello").indexOf("0").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}
	
	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToEqualACertainValue_List_Exception() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClassValue>Test</button>');");
		((JavascriptExecutor) LocalDriver.getDriver())
		.executeScript("document.write('<button id=Test class=someClassValue>Nothing</button>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.EQUAL).value("Hello").indexOf("0").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToContainACertainValue_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.CONTAIN).value("Nope").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}

	@Test
	public void verifyWaitForElementToContainACertainValue_PredefinedElement() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.CONTAIN).value("Test").forAMaxTimeOf(Duration.ofSeconds(2))).on(test);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToContainACertainValue__PredefinedElement_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.CONTAIN).value("Nope").forAMaxTimeOf(Duration.ofSeconds(2))).on(test);
	}

	@Test
	public void verifyWaitForElementToEqualACertainValue() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test class=someClassValue>Test</button>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.EQUAL).value("Test").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToEqualACertainValue_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.EQUAL).value("Tes").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}

	@Test
	public void verifyWaitForElementToEqualACertainValue_PredefinedElement() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.EQUAL).value("Test").forAMaxTimeOf(Duration.ofSeconds(2))).on(test);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToEqualACertainValue__PredefinedElement_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.EQUAL).value("Tes").forAMaxTimeOf(Duration.ofSeconds(2))).on(test);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToHaveValueInvalidCondition_PreDefinedWebElement_ExceptionThrown()
			throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.INVALID_CONDITION).value("help").forAMaxTimeOf(Duration.ofSeconds(2))).on(test);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToHaveValueInvalidCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
				new WaitBuilder().to(Condition.INVALID_CONDITION).value("help").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToHaveValueNullCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder().value("help").forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForElementToHaveValueNullCondition_PreDefinedWebElement_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder().value("help").forAMaxTimeOf(Duration.ofSeconds(2)))
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
				.forAMaxTimeOf(Duration.ofSeconds(2))
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
				.forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementToBePresentNonNullCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
				.forAMaxTimeOf(Duration.ofSeconds(2))
				.to(Condition.CONTAIN))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForElementToBePresentNonNullTotalCount_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
				.value("String")
				.to(Condition.EQUAL)
				.forAMaxTimeOf(Duration.ofSeconds(2))
				.withACountOf(2))
				.on(element);
	}

	@AfterMethod
	public void afterScenario() {
		
	}
}
