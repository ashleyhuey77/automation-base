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
public class Wait_NonPresentAttributeText_Tests {

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
	public void verifyWaitForAttributeToNotHaveValueInvalidCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAttribute("class").to(Condition.INVALID_CONDITION).value("help").forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForAttributeToNotHaveValueInvalidCondition_PreDefinedWebElement_ExceptionThrown()
			throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");

		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAttribute("class").to(Condition.INVALID_CONDITION).value("help").forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(test);
	}
	
	@Test
	public void verifyWaitForAttributeToNotEqualValue_PageAttrIsNull_PreDefinedWebElement_ExceptionThrown()
			throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test></input>');");

		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAttribute("testing").to(Condition.EQUAL).value("SomeClass").forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(test);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForAttributeToNotHaveValueNullCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAttribute("class").to(null).value("help").forAMaxTimeOf(Duration.ofSeconds(2))).on(element);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForAttributeToNotHaveValueNullCondition_PreDefinedWebElement_ExceptionThrown()
			throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAttribute("class").to(null).value("help").forAMaxTimeOf(Duration.ofSeconds(2))).on(test);
	}

	@Test
	public void verifyWaitForAttributeToNotContainACertainValue_PredefinedElement() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get()
				.waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
						new WaitBuilder().forAttribute("class").to(Condition.CONTAIN).value("help").forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(test);
	}
	
	@Test
	public void verifyWaitForAttributeToNotContainACertainValue() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);

		SHelper.get()
				.waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
						new WaitBuilder().forAttribute("class").to(Condition.CONTAIN).value("help").forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForAttributeToNotContainACertainValue_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAttribute("class").to(Condition.CONTAIN).value("someClassValue").forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(test);
	}
	
	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForAttributeToNotContainACertainValue_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAttribute("class").to(Condition.CONTAIN).value("someClassValue").forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}

	@Test
	public void verifyWaitForAttributeToNotEqualACertainValue_PredefinedElement() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get()
				.waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
						new WaitBuilder().forAttribute("class").to(Condition.EQUAL).value("help").forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(test);
	}
	
	@Test
	public void verifyWaitForAttributeToNotEqualACertainValue() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);

		SHelper.get()
				.waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
						new WaitBuilder().forAttribute("class").to(Condition.EQUAL).value("help").forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForAttributeToNotEqualACertainValue_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAttribute("class").to(Condition.EQUAL).value("someClassValue").forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(test);
	}
	
	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForAttributeToNotEqualACertainValue_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAttribute("class").to(Condition.EQUAL).value("someClassValue").forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForAttributeTextNotToBePresentInvalidWaitTime_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.EQUAL)
				.value("someClassValue"))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForAttributeTextNotToBePresentNullValue_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.EQUAL)
				.forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForAttributeTextNotToBePresentEmptyValue_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.value("")
				.to(Condition.EQUAL)
				.forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForAttributeTextNotToBePresentNullAttribute_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForAttributeTextNotToBePresentEmptyAttribute_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("")
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(Duration.ofSeconds(2)))
				.on(element);
	}

	@AfterMethod
	public void afterScenario() {

	}

}
