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
				new WaitBuilder().forAttribute("class").to(Condition.INVALID_CONDITION).value("help").forAMaxTimeOf(2))
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
				new WaitBuilder().forAttribute("class").to(Condition.INVALID_CONDITION).value("help").forAMaxTimeOf(2))
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
				new WaitBuilder().forAttribute("testing").to(Condition.EQUAL).value("SomeClass").forAMaxTimeOf(1))
				.on(test);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForAttributeToNotHaveValueNullCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAttribute("class").to(null).value("help").forAMaxTimeOf(2)).on(element);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyWaitForAttributeToNotHaveValueNullCondition_PreDefinedWebElement_ExceptionThrown()
			throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAttribute("class").to(null).value("help").forAMaxTimeOf(2)).on(test);
	}

	@Test
	public void verifyWaitForAttributeToNotContainACertainValue_PredefinedElement() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get()
				.waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
						new WaitBuilder().forAttribute("class").to(Condition.CONTAIN).value("help").forAMaxTimeOf(2))
				.on(test);
	}
	
	@Test
	public void verifyWaitForAttributeToNotContainACertainValue() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);

		SHelper.get()
				.waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
						new WaitBuilder().forAttribute("class").to(Condition.CONTAIN).value("help").forAMaxTimeOf(2))
				.on(element);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForAttributeToNotContainACertainValue_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAttribute("class").to(Condition.CONTAIN).value("someClassValue").forAMaxTimeOf(1))
				.on(test);
	}
	
	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForAttributeToNotContainACertainValue_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAttribute("class").to(Condition.CONTAIN).value("someClassValue").forAMaxTimeOf(1))
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
						new WaitBuilder().forAttribute("class").to(Condition.EQUAL).value("help").forAMaxTimeOf(2))
				.on(test);
	}
	
	@Test
	public void verifyWaitForAttributeToNotEqualACertainValue() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);

		SHelper.get()
				.waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
						new WaitBuilder().forAttribute("class").to(Condition.EQUAL).value("help").forAMaxTimeOf(2))
				.on(element);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForAttributeToNotEqualACertainValue_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAttribute("class").to(Condition.EQUAL).value("someClassValue").forAMaxTimeOf(1))
				.on(test);
	}
	
	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForAttributeToNotEqualACertainValue_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAttribute("class").to(Condition.EQUAL).value("someClassValue").forAMaxTimeOf(1))
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
				.forAMaxTimeOf(1))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForAttributeTextNotToBePresentEmptyValue_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.value("")
				.to(Condition.EQUAL)
				.forAMaxTimeOf(1))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForAttributeTextNotToBePresentNullAttribute_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(1))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForAttributeTextNotToBePresentEmptyAttribute_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("")
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(1))
				.on(element);
	}

	@AfterMethod
	public void afterScenario() {

	}

}
