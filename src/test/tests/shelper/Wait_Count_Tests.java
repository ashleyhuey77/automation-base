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
import log.TestException;
import shelper.SeleniumHelper;
import shelper.builders.WaitBuilder;
import shelper.enums.Condition;
import shelper.enums.Wait;
import shelper.vobjects.By;
import shelper.vobjects.Locator;
import shelper.vobjects.TestElement;

@Listeners(WebDriverListener.class)
public class Wait_Count_Tests {
	
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
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForCountInvalidWaitTime_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder().withACountOf(1))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForCountInvalidTotalCount_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder()
				.forAMaxTimeOf(1))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForCountNonNullValue_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder()
				.forAMaxTimeOf(1)
				.withACountOf(1)
				.value("Test"))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForCountNonNullCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder()
				.forAMaxTimeOf(1)
				.withACountOf(1)
				.to(Condition.CONTAIN))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForCountNonNullAttribute_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder()
				.forAMaxTimeOf(1)
				.withACountOf(1)
				.forAttribute(""))
				.on(element);
	}
	
	@Test(expectedExceptions=UnsupportedOperationException.class)
	public void verifyWaitForCountPredefinedWebElement_ExceptionThrown() throws Exception {
		Locator locator2 = new Locator("img[id='hplogo']");
		By by2 = new By(How.CSS);
		WebElement element2 = SHelper.get().element().get(new TestElement(locator2, by2));
		SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder()
				.forAMaxTimeOf(2)
				.withACountOf(1))
				.on(element2);
	}
	
	@AfterMethod
	public void afterScenario() {
		
	}

}
