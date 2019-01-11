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
public class Wait_PresentAttributeText_Tests {

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
	public void verifyWaitForAttributeToHaveValueInvalidCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT,
				new WaitBuilder().forAttribute("class").to(Condition.INVALID_CONDITION).value("help").forAMaxTimeOf(2))
				.on(element);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForAttributeToHaveValueInvalidCondition_PreDefinedWebElement_ExceptionThrown()
			throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT,
				new WaitBuilder().forAttribute("class").to(Condition.INVALID_CONDITION).value("help").forAMaxTimeOf(2))
				.on(test);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForAttributeToHaveValueNullCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT,
				new WaitBuilder().forAttribute("class").to(null).value("help").forAMaxTimeOf(2)).on(element);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForAttributeToHaveValueNullCondition_PreDefinedWebElement_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT,
				new WaitBuilder().forAttribute("class").to(null).value("help").forAMaxTimeOf(2)).on(test);
	}

	@Test
	public void verifyWaitForAttributeToContainACertainValue() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT,
				new WaitBuilder().forAttribute("class").to(Condition.CONTAIN).value("someClassValue").forAMaxTimeOf(2))
				.on(element);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForAttributeToContainACertainValue_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Locator locator2 = new Locator("NotHere");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT,
				new WaitBuilder().forAttribute("class").to(Condition.CONTAIN).value("someClassValue").forAMaxTimeOf(1))
				.on(element2);
	}

	@Test
	public void verifyWaitForAttributeToContainACertainValue_PredefinedElement() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT,
				new WaitBuilder().forAttribute("class").to(Condition.CONTAIN).value("someClassValue").forAMaxTimeOf(2))
				.on(test);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForAttributeToContainACertainValue__PredefinedElement_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		SHelper.get().page().refresh();
		Thread.sleep(900);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT,
				new WaitBuilder().forAttribute("class").to(Condition.CONTAIN).value("someClassValue").forAMaxTimeOf(1))
				.on(test);
	}

	@Test
	public void verifyWaitForAttributeToEqualACertainValue() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT,
				new WaitBuilder().forAttribute("class").to(Condition.EQUAL).value("someClassValue").forAMaxTimeOf(2))
				.on(element);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForAttributeToEqualACertainValue_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Locator locator2 = new Locator("NotHere");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT,
				new WaitBuilder().forAttribute("class").to(Condition.EQUAL).value("someClassValue").forAMaxTimeOf(1))
				.on(element2);
	}

	@Test
	public void verifyWaitForAttributeToEqualACertainValue_PredefinedElement() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT,
				new WaitBuilder().forAttribute("class").to(Condition.EQUAL).value("someClassValue").forAMaxTimeOf(2))
				.on(test);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForAttributeToEqualACertainValue_PredefinedElement_ExceptionThrown() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);

		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT,
				new WaitBuilder().forAttribute("class").to(Condition.EQUAL).value("someClassValue").forAMaxTimeOf(1))
				.on(test);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForAttributeTextToBePresentInvalidWaitTime_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.EQUAL)
				.value("someClassValue"))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForAttributeTextToBePresentNullValue_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.EQUAL)
				.forAMaxTimeOf(1))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForAttributeTextToBePresentEmptyValue_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.forAttribute("class")
				.value("")
				.to(Condition.EQUAL)
				.forAMaxTimeOf(1))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForAttributeTextToBePresentNullAttribute_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(1))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForAttributeTextToBePresentEmptyAttribute_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.forAttribute("")
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(1))
				.on(element);
	}
	
	@Test(expectedExceptions=UnsupportedOperationException.class)
	public void verifyWaitForAttributeTextToBePresentPredefinedWebElement_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(1))
				.on(new ArrayList<WebElement>());
	}

	@AfterMethod
	public void afterScenario() {

	}

}