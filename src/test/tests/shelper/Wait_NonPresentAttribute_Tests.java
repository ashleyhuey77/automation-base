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
public class Wait_NonPresentAttribute_Tests {
	
	Locator locator = new Locator("Test");
	By by = new By(How.ID);
	TestElement element = new TestElement(locator, by);
	
	@BeforeMethod
	public void beforeScenario() {
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForNonPresenttAttributeToHaveNullBaseAttribute_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.ATTRIBUTE_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForNonPresenttAttributeToHaveEmptyStringBaseAttribute_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.ATTRIBUTE_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("")
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForNonPresentAttributeToHaveNullTime_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.ATTRIBUTE_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class"))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForNonPresentAttributeConditionNotNull_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.ATTRIBUTE_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.to(Condition.CONTAIN)
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=UnsupportedOperationException.class)
	public void verifyWaitForNonPresentAttributeList_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.ATTRIBUTE_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.forAMaxTimeOf(2))
				.on(new ArrayList<WebElement>());
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForNonPresentAttributeValueNotNull_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.ATTRIBUTE_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.value("Something")
				.forAMaxTimeOf(2))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForNonPresentAttributeTotalCountNotNull_ExceptionThrown() throws Exception
	{
		SHelper.get().waitMethod(Wait.ATTRIBUTE_NOT_TO_BE_PRESENT, new WaitBuilder()
				.forAttribute("class")
				.forAMaxTimeOf(1)
				.withACountOf(4))
				.on(element);
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
	
	
	@AfterMethod
	public void afterScenario() {
		
	}

}
