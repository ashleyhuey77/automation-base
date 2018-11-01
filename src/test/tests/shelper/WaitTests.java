package tests.shelper;

import java.util.List;
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
import shelper.SeleniumHelper;
import shelper.builders.WaitBuilder;
import shelper.enums.Condition;
import shelper.enums.Wait;
import shelper.vobjects.By;
import shelper.vobjects.Locator;
import shelper.vobjects.TestElement;

@Listeners(WebDriverListener.class)
public class WaitTests {
	Locator locator = new Locator("Test");
	By by = new By(How.ID);
	TestElement element = new TestElement(locator, by);
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToLoad_ExceptionThrown() throws Exception
	{
		TestElement element2 = new TestElement(new Locator(""), new By("id"));
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
				new WaitBuilder().forAMaxTimeOf(1)).on(element2);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForAttributeToNotHaveValueInvalidCondition_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.INVALID_CONDITION)
				.value("help")
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForAttributeToNotHaveValueInvalidCondition_PreDefinedWebElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");

		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.INVALID_CONDITION)
				.value("help")
				.forAMaxTimeOf(2))
				.on(test);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForAttributeToNotHaveValueNullCondition_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.to(null)
				.value("help")
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForAttributeToNotHaveValueNullCondition_PreDefinedWebElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.to(null)
				.value("help")
				.forAMaxTimeOf(2))
				.on(test);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForAttributeToHaveValueInvalidCondition_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.INVALID_CONDITION)
				.value("help")
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForAttributeToHaveValueInvalidCondition_PreDefinedWebElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.INVALID_CONDITION)
				.value("help")
				.forAMaxTimeOf(2))
				.on(test);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForAttributeToHaveValueNullCondition_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.forAttribute("class")
				.to(null)
				.value("help")
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForAttributeToHaveValueNullCondition_PreDefinedWebElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.forAttribute("class")
				.to(null)
				.value("help")
				.forAMaxTimeOf(2))
				.on(test);
	}
	
	@Test
	public void verifyWaitForAttributeValueToNotContainACertainValue() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.CONTAIN)
				.value("help")
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToNotContainACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Locator locator2 = new Locator("NotHere");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.CONTAIN)
				.value("someClassValue")
				.forAMaxTimeOf(1))
				.on(element2);
	}
	
	@Test
	public void verifyWaitForAttributeValueToNotEqualACertainValue() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.EQUAL)
				.value("help")
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToNotEqualACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Locator locator2 = new Locator("NotHere");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(1))
				.on(element2);
	}
	
	@Test
	public void verifyWaitForAttributeToNotContainACertainValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.CONTAIN)
				.value("help")
				.forAMaxTimeOf(2))
				.on(test);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToNotContainACertainValue_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.CONTAIN)
				.value("someClassValue")
				.forAMaxTimeOf(1))
				.on(test);
	}
	
	@Test
	public void verifyWaitForAttributeToNotEqualACertainValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.EQUAL)
				.value("help")
				.forAMaxTimeOf(2))
				.on(test);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToNotEqualACertainValue_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(1))
				.on(test);
	}

	@Test
	public void verifyWaitForAttributeToContainACertainValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.CONTAIN)
				.value("someClassValue")
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToContainACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Locator locator2 = new Locator("NotHere");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.CONTAIN)
				.value("someClassValue")
				.forAMaxTimeOf(1))
				.on(element2);
	}
	
	@Test
	public void verifyWaitForAttributeToContainACertainValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.forAttribute("class")
        			.to(Condition.CONTAIN)
        			.value("someClassValue")
        			.forAMaxTimeOf(2))
				.on(test);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToContainACertainValue__PredefinedElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		SHelper.get().page().refresh();
		Thread.sleep(900);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.CONTAIN)
				.value("someClassValue")
				.forAMaxTimeOf(1))
				.on(test);
	}
	
	@Test
	public void verifyWaitForElementToDisappear() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test></input>');");
		Locator locator2 = new Locator("Help");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAMaxTimeOf(1)).on(element2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToDisappear_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test></button>');");
		Thread.sleep(600);
		
		SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAMaxTimeOf(1)).on(element);
	}
	
	@Test
	public void verifyWaitForElementToDisappear_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		SHelper.get().page().refresh();
		Thread.sleep(1000);
		
		SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAMaxTimeOf(2)).on(test);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToDisappear_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClassValue></button>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT,
				new WaitBuilder().forAMaxTimeOf(1)).on(test);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForElementToNotHaveValueInvalidCondition_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.to(Condition.INVALID_CONDITION)
				.value("help")
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForElementToNotHaveValueInvalidCondition_PreDefinedWebElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.to(Condition.INVALID_CONDITION)
				.value("help")
				.forAMaxTimeOf(2))
				.on(test);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForElementToNotHaveValueNullCondition_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.value("help")
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForElementToNotHaveValueNullCondition_PreDefinedWebElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.value("help")
				.forAMaxTimeOf(2))
				.on(test);
	}
	
	@Test
	public void verifyWaitForElementValueToNotContainACertainValue() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.to(Condition.CONTAIN)
				.value("help")
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToNotContainACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.to(Condition.CONTAIN)
				.value("Test")
				.forAMaxTimeOf(1))
				.on(element);
	}
	
	@Test
	public void verifyWaitForElementValueToNotEqualACertainValue() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.to(Condition.EQUAL)
				.value("help")
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToNotEqualACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.to(Condition.EQUAL)
				.value("Test")
				.forAMaxTimeOf(1))
				.on(element);
	}
	
	@Test
	public void verifyWaitForElementToNotContainACertainValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=SomeClass>Help</button>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.to(Condition.CONTAIN)
				.value("Test")
				.forAMaxTimeOf(2))
				.on(test);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToNotContainACertainValue_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.to(Condition.CONTAIN)
				.value("Test")
				.forAMaxTimeOf(1))
				.on(test);
	}
	
	@Test
	public void verifyWaitForElementToNotEqualACertainValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=SomeClass>Help</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.to(Condition.EQUAL)
				.value("Test")
				.forAMaxTimeOf(2))
				.on(test);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToNotEqualACertainValue_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT, new WaitBuilder()
				.to(Condition.EQUAL)
				.value("Test")
				.forAMaxTimeOf(1))
				.on(test);
	}
	
	@Test
	public void verifyWaitForAttributeToDisappear() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test></input>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("value")
        			.forAMaxTimeOf(1))
				.on(element);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToDisappear_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
    				.forAMaxTimeOf(1))
				.on(element);
	}
	
	@Test
	public void verifyWaitForAttributeToDisappear_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("value")
    				.forAMaxTimeOf(1))
				.on(test);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToDisappear_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.ATTRIBUTE_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
    				.forAMaxTimeOf(1))
				.on(test);
	}
	
	@Test
	public void verifyWaitForAttributeToEqualACertainValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToEqualACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Locator locator2 = new Locator("NotHere");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(1))
				.on(element2);
	}
	
	@Test
	public void verifyWaitForAttributeToEqualACertainValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(2))
				.on(test);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForAttributeToEqualACertainValue_PredefinedElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.EQUAL)
				.value("someClassValue")
				.forAMaxTimeOf(1))
				.on(test);
	}
	
	@Test
	public void verifyWaitForElementToBeClickable() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT,
				new WaitBuilder().forAMaxTimeOf(1)).on(element);
		
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToBeClickable_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button</button>');");
		Locator locator2 = new Locator("NotHere");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT,
				new WaitBuilder().forAMaxTimeOf(1)).on(element2);
		
	}
	
	@Test
	public void verifyWaitForElementToBeClickable_PredefinedWebElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		WebElement button = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT,
				new WaitBuilder().forAMaxTimeOf(1)).on(button);
		
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToBeClickable__PredefinedWebElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		WebElement button = SHelper.get().element().get(element);
		SHelper.get().page().refresh();
		Thread.sleep(1000);
		
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT,
				new WaitBuilder().forAMaxTimeOf(1)).on(button);
		
	}
	
	@Test
	public void verifyElementIsPresent() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
				new WaitBuilder().forAMaxTimeOf(2)).on(element);
	}
	
	@Test
	public void verifyElementIsPresent_PredefinedElement() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		WebElement element2 = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
				new WaitBuilder().forAMaxTimeOf(1)).on(element2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyElementIsPresent_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Locator locator2 = new Locator("NotHere");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
				new WaitBuilder().forAMaxTimeOf(1)).on(element2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyElementIsPresent_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		WebElement element2 = SHelper.get().element().get(element);
		SHelper.get().page().refresh();
		Thread.sleep(900);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
				new WaitBuilder().forAMaxTimeOf(1)).on(element2);
	}
	
	@Test
	public void verifyWaitForElementToContainACertainValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClassValue>Test</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
			.to(Condition.CONTAIN)
        		.value("Test")
        		.forAMaxTimeOf(2))
			.on(element);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToContainACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
			.to(Condition.CONTAIN)
        		.value("Nope")
        		.forAMaxTimeOf(1))
			.on(element);
	}
	
	@Test
	public void verifyWaitForElementToContainACertainValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
			.to(Condition.CONTAIN)
        		.value("Test")
        		.forAMaxTimeOf(2))
			.on(test);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToContainACertainValue__PredefinedElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
			.to(Condition.CONTAIN)
        		.value("Nope")
        		.forAMaxTimeOf(1))
			.on(test);
	}
	
	@Test
	public void verifyWaitForElementToEqualACertainValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClassValue>Test</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
			.to(Condition.EQUAL)
        		.value("Test")
        		.forAMaxTimeOf(2))
			.on(element);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToEqualACertainValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
			.to(Condition.EQUAL)
        		.value("Tes")
        		.forAMaxTimeOf(1))
			.on(element);
	}
	
	@Test
	public void verifyWaitForElementToEqualACertainValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
			.to(Condition.EQUAL)
        		.value("Test")
        		.forAMaxTimeOf(2))
			.on(test);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitForElementToEqualACertainValue__PredefinedElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=someClassValue>Test</div>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
			.to(Condition.EQUAL)
        		.value("Tes")
        		.forAMaxTimeOf(1))
			.on(test);
	}
	
	@Test
	public void verifyAttributeIsPresent() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
			.forAttribute("class")
        		.forAMaxTimeOf(1))
			.on(element);
	}
	
	@Test
	public void verifyAttributeIsPresent_PredefinedElement() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		WebElement element2 = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
			.forAttribute("class")
        		.forAMaxTimeOf(1))
			.on(element2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyAttributeIsPresent_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
			.forAttribute("style")
        		.forAMaxTimeOf(1))
			.on(element);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyAttributeIsPresent_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		WebElement element2 = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, new WaitBuilder()
			.forAttribute("style")
        		.forAMaxTimeOf(1))
			.on(element2);
	}
	
	@Test
	public void verifyWaitOnElementCount() throws Exception {
		Locator locator2 = new Locator("img[id='hplogo']");
		By by2 = new By(How.CSS);
		TestElement element2 = new TestElement(locator2, by2);
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder().withACountOf(1).forAMaxTimeOf(5)).on(element2);
	}

	
	@Test
	public void verifyWaitOnElementCount_PredefinedElementList() throws Exception {
		Locator locator2 = new Locator("img[id='hplogo']");
		By by2 = new By(How.CSS);
		TestElement element3 = new TestElement(locator2, by2);
		List<WebElement> element2 = SHelper.get().element().getListOf(element3);
		
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder().withACountOf(1).forAMaxTimeOf(5)).on(element2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitOnElementCount_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder().withACountOf(1).forAMaxTimeOf(1)).on(element);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyWaitOnElementCount_PredefinedElementList_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=someClass>Button</button>');");
		Thread.sleep(300);
		List<WebElement> element2 = SHelper.get().element().getListOf(element);
		
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder().withACountOf(1).forAMaxTimeOf(1)).on(element2);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForElementToHaveValueInvalidCondition_PreDefinedWebElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
			.to(Condition.INVALID_CONDITION)
        		.value("help")
        		.forAMaxTimeOf(2))
			.on(test);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForElementToHaveValueInvalidCondition_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
			.to(Condition.INVALID_CONDITION)
        		.value("help")
        		.forAMaxTimeOf(2))
			.on(element);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForElementToHaveValueNullCondition_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
        		.value("help")
        		.forAMaxTimeOf(2))
			.on(element);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyWaitForElementToHaveValueNullCondition_PreDefinedWebElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=SomeClass></input>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
        		.value("help")
        		.forAMaxTimeOf(2))
			.on(test);
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
