package unitTests.seleniumHelperTests;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonClasses.sharedUtils.TestUtils;
import commonClasses.sharedUtils.WebDriverListener;
import commonClasses.sharedUtils.managers.LocalDriver;
import commonClasses.sharedUtils.managers.SHelper;
import seleniumHelper.SeleniumHelper;
import seleniumHelper.enums.Condition;
import seleniumHelper.enums.Wait;

@Listeners(WebDriverListener.class)
public class WaitTests {
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/ExternalLibraries/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToLoad_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on("Test", "id", 1);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForAttributeToNotHaveValueInvalidCondition_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on("Test", "id", Condition.TESTCONDITION, "help", 2, "class");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForAttributeToNotHaveValueInvalidCondition_PreDefinedWebElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on(test, Condition.TESTCONDITION, "help", 2, "class");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForAttributeToNotHaveValueNullCondition_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on("Test", "id", null, "help", 2, "class");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForAttributeToNotHaveValueNullCondition_PreDefinedWebElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on(test, null, "help", 2, "class");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForAttributeToHaveValueInvalidCondition_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on("Test", "id", Condition.TESTCONDITION, "help", 2, "class");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForAttributeToHaveValueInvalidCondition_PreDefinedWebElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on(test, Condition.TESTCONDITION, "help", 2, "class");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForAttributeToHaveValueNullCondition_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on("Test", "id", null, "help", 2, "class");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForAttributeToHaveValueNullCondition_PreDefinedWebElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on(test, null, "help", 2, "class");
	}
	
	@Test
	public void verifyWaitForAttributeValueToNotContainACertainValue() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on("Test", "id", Condition.CONTAINS, "help", 2, "class");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToNotContainACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on("notHere", "id", Condition.CONTAINS, "someClassValue", 1, "class");
	}
	
	@Test
	public void verifyWaitForAttributeValueToNotEqualACertainValue() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on("Test", "id", Condition.EQUALS, "help", 2, "class");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToNotEqualACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on("notHere", "id", Condition.EQUALS, "someClassValue", 1, "class");
	}
	
	@Test
	public void verifyWaitForAttributeToNotContainACertainValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on(test, Condition.CONTAINS, "help", 2, "class");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToNotContainACertainValue_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on(test, Condition.CONTAINS, "someClassValue", 1, "class");
	}
	
	@Test
	public void verifyWaitForAttributeToNotEqualACertainValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on(test, Condition.EQUALS, "help", 2, "class");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToNotEqualACertainValue_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on(test, Condition.EQUALS, "someClassValue", 1, "class");
	}

	@Test
	public void verifyWaitForAttributeToContainACertainValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on("Test", "id", Condition.CONTAINS, "someClassValue", 2, "class");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToContainACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on("notHere", "id", Condition.CONTAINS, "someClassValue", 1, "class");
	}
	
	@Test
	public void verifyWaitForAttributeToContainACertainValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on(test, Condition.CONTAINS, "someClassValue", 2, "class");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToContainACertainValue__PredefinedElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		SHelper.get().page().refresh();
		Thread.sleep(900);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on(test, Condition.CONTAINS, "someClassValue", 1, "class");
	}
	
	@Test
	public void verifyWaitForElementToDisappear() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test></input>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on("Help", "id", 1);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToDisappear_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test></button>');");
		Thread.sleep(600);
		
		SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on("Test", "id", 1);
	}
	
	@Test
	public void verifyWaitForElementToDisappear_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		SHelper.get().page().refresh();
		Thread.sleep(1000);
		
		SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on(test, 2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToDisappear_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClassValue></button>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on(test, 1);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForElementToNotHaveValueInvalidCondition_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on("Test", "id", Condition.TESTCONDITION, "help", 2);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForElementToNotHaveValueInvalidCondition_PreDefinedWebElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on(test, Condition.TESTCONDITION, "help", 2);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForElementToNotHaveValueNullCondition_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on("Test", "id", null, "help", 2);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForElementToNotHaveValueNullCondition_PreDefinedWebElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on(test, null, "help", 2);
	}
	
	@Test
	public void verifyWaitForElementValueToNotContainACertainValue() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on("Test", "id", Condition.CONTAINS, "help", 2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToNotContainACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on("Test", "id", Condition.CONTAINS, "Test", 1);
	}
	
	@Test
	public void verifyWaitForElementValueToNotEqualACertainValue() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on("Test", "id", Condition.EQUALS, "help", 2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToNotEqualACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on("Test", "id", Condition.EQUALS, "Test", 1);
	}
	
	@Test
	public void verifyWaitForElementToNotContainACertainValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=SomeClass>Help</button>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on(test, Condition.CONTAINS, "Test", 2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToNotContainACertainValue_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on(test, Condition.CONTAINS, "Test", 1);
	}
	
	@Test
	public void verifyWaitForElementToNotEqualACertainValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=SomeClass>Help</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on(test, Condition.EQUALS, "Test", 2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToNotEqualACertainValue_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on(test, Condition.EQUALS, "Test", 1);
	}
	
	@Test
	public void verifyWaitForAttributeToDisappear() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test></input>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on("Test", "id", 1, "value");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToDisappear_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on("Test", "id", 1, "class");
	}
	
	@Test
	public void verifyWaitForAttributeToDisappear_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on(test, 1, "value");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToDisappear_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on(test, 1, "class");
	}
	
	@Test
	public void verifyWaitForAttributeToEqualACertainValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on("Test", "id", Condition.EQUALS, "someClassValue", 2, "class");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToEqualACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on("notHere", "id", Condition.EQUALS, "someClassValue", 1, "class");
	}
	
	@Test
	public void verifyWaitForAttributeToEqualACertainValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on(test, Condition.EQUALS, "someClassValue", 2, "class");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToEqualACertainValue_PredefinedElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on(test, Condition.EQUALS, "someClassValue", 1, "class");
	}
	
	@Test
	public void verifyWaitForElementToBeClickable() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT).on("Test", "id", 1);
		
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToBeClickable_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT).on("NotHere", "id", 1);
		
	}
	
	@Test
	public void verifyWaitForElementToBeClickable_PredefinedWebElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		WebElement button = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT).on(button, 1);
		
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToBeClickable__PredefinedWebElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		WebElement button = SHelper.get().element().get("Test", "id");
		SHelper.get().page().refresh();
		Thread.sleep(1000);
		
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT).on(button, 1);
		
	}
	
	@Test
	public void verifyElementIsPresent() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on("Test", "id", 1);
	}
	
	@Test
	public void verifyElementIsPresent_PredefinedElement() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		WebElement element = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on(element, 1);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyElementIsPresent_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on("Help", "id", 1);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyElementIsPresent_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		WebElement element = SHelper.get().element().get("Test", "id");
		SHelper.get().page().refresh();
		Thread.sleep(900);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on(element, 1);
	}
	
	@Test
	public void verifyWaitForElementToContainACertainValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClassValue>Test</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on("Test", "id", Condition.CONTAINS, "Test", 2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToContainACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on("Test", "id", Condition.CONTAINS, "Nope", 1);
	}
	
	@Test
	public void verifyWaitForElementToContainACertainValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on(test, Condition.CONTAINS, "Test", 2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToContainACertainValue__PredefinedElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on(test, Condition.CONTAINS, "Nope", 1);
	}
	
	@Test
	public void verifyWaitForElementToEqualACertainValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClassValue>Test</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on("Test", "id", Condition.EQUALS, "Test", 2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToEqualACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on("Test", "id", Condition.EQUALS, "Nope", 1);
	}
	
	@Test
	public void verifyWaitForElementToEqualACertainValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on(test, Condition.EQUALS, "Test", 2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToEqualACertainValue__PredefinedElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on(test, Condition.EQUALS, "Nope", 1);
	}
	
	@Test
	public void verifyAttributeIsPresent() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on("Test", "id", 1, "class");
	}
	
	@Test
	public void verifyAttributeIsPresent_PredefinedElement() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		WebElement element = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on(element, 1, "class");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyAttributeIsPresent_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on("Test", "id", 1, "style");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyAttributeIsPresent_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		WebElement element = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on(element, 1, "style");
	}
	
	@Test
	public void verifyWaitOnElementCount() throws Exception {
		
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS).on("input[type='submit']", "cssSelector", 2, 5);
	}
	
	@Test
	public void verifyWaitOnElementCount_PredefinedElementList() throws Exception {
		List<WebElement> element = SHelper.get().element().getListOf("input[type='submit']", "cssSelector");
		
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS).on(element, 2, 1);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitOnElementCount_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS).on("Test", "id", 1, 1);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitOnElementCount_PredefinedElementList_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		List<WebElement> element = SHelper.get().element().getListOf("Test", "id");
		
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS).on(element, 1, 1);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForElementToHaveValueInvalidCondition_PreDefinedWebElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on(test, Condition.TESTCONDITION, "help", 2, "class");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForElementToHaveValueInvalidCondition_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on("Test", "id", Condition.TESTCONDITION, "help", 2);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForElementToHaveValueNullCondition_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on("Test", "id", null, "help", 2);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForElementToHaveValueNullCondition_PreDefinedWebElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on(test, null, "help", 2);
	}
	
	@AfterMethod
	public void afterScenario() {
/*		try {
			if(LocalDriver.getDriver() != null) {
				LocalDriver.getDriver().quit();
			}
		} catch (Exception e) {
			if(LocalDriver.getDriver() != null) {
				LocalDriver.getDriver().quit();
			}
		}
		finally {
			if(LocalDriver.getDriver() != null) {
				LocalDriver.getDriver().quit();
			}
		}*/
	}
}
