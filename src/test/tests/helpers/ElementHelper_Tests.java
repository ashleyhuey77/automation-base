package tests.helpers;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import common.base.helpers.ElementHelper;
import common.base.helpers.ElementHelper.ElementBuilder;
import common.utils.WebDriverListener;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import log.TestException;
import pages.TestInitialization;
import shelper.enums.Condition;
import shelper.vobjects.By;
import shelper.vobjects.Locator;
import shelper.vobjects.TestElement;

@Listeners(WebDriverListener.class)
public class ElementHelper_Tests extends TestInitialization {
	
	@Test(groups= {"element"}, alwaysRun=true)
	public void verifyElementHelper_ListOfElements_ElementFound() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Correct</div>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Incorrect</div>');");
		Thread.sleep(500);
		Locator locator = new Locator("div[id='Test']");
		By by = new By("css");
		List<WebElement> elements = SHelper.get().element().getListOf(new TestElement(locator, by));

		WebElement element = new ElementHelper(new ElementBuilder(elements).text("Correct")).get();

		Assert.assertNotNull(element);
	}
	
	@Test(groups= {"element"}, alwaysRun=true)
	public void verifyElementHelper_ListOfElements_ElementNotFound() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Correct</div>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Incorrect</div>');");
		Thread.sleep(500);
		Locator locator = new Locator("div[id='Test']");
		By by = new By("css");
		List<WebElement> elements = SHelper.get().element().getListOf(new TestElement(locator, by));

		WebElement element = new ElementHelper(new ElementBuilder(elements).text("Noncorrect")).get();

		Assert.assertNull(element);
	}
	
	@Test(groups= {"element"}, alwaysRun=true)
	public void verifyElementHelper_ListOfElements_ElementFound_Equals() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Correct</div>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Incorrect</div>');");
		Thread.sleep(500);
		Locator locator = new Locator("div[id='Test']");
		By by = new By("css");
		List<WebElement> elements = SHelper.get().element().getListOf(new TestElement(locator, by));

		WebElement element = new ElementHelper(new ElementBuilder(elements).that(Condition.EQUAL).text("Correct")).get();

		Assert.assertNotNull(element);
	}
	
	@Test(groups= {"element"}, alwaysRun=true)
	public void verifyElementHelper_ListOfElements_ElementFound_Contains() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Correct</div>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Wrong</div>');");
		Thread.sleep(500);
		Locator locator = new Locator("div[id='Test']");
		By by = new By("css");
		List<WebElement> elements = SHelper.get().element().getListOf(new TestElement(locator, by));

		WebElement element = new ElementHelper(new ElementBuilder(elements).that(Condition.CONTAIN).text("corr")).get();

		Assert.assertNotNull(element);
	}
	
	@Test(groups= {"element"}, alwaysRun=true)
	public void verifyElementHelper_SingleElement_ElementFound() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Correct</div>');");
		Thread.sleep(500);
		Locator locator = new Locator("div[id='Test']");
		By by = new By("css");

		WebElement element = new ElementHelper(new ElementBuilder(new TestElement(locator, by))).get();

		Assert.assertNotNull(element);
	}
	
	@Test(groups= {"element"}, expectedExceptions=TestException.class, alwaysRun=true)
	public void verifyNoElementProvided_ExceptionThrown() throws Exception {
		List<WebElement> list = null;
		@SuppressWarnings("unused")
		WebElement element = new ElementHelper(new ElementBuilder(list).text("Correct")).get();
	}
	
	@Test(groups= {"element"}, expectedExceptions=TestException.class, alwaysRun=true)
	public void verifyEmptyList_ExceptionThrown() throws Exception {
		List<WebElement> list = new ArrayList<>();
		@SuppressWarnings("unused")
		WebElement element = new ElementHelper(new ElementBuilder(list).text("Correct")).get();
	}

}
