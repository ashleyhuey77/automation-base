package tests.helpers;

import java.util.ArrayList;
import java.util.List;

import com.warnermedia.selenium.TestElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.driver.WebDriverListener;
import com.warnermedia.selenium.wait.Condition;
import pages.TestInitialization;
import utils.Variables;

@Listeners(WebDriverListener.class)
public class ElementHelper_Tests extends TestInitialization {
	
	@Test(groups= {"element"}, alwaysRun=true)
	public void verifyElementHelper_ListOfElements_ElementFound() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Correct</div>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Incorrect</div>');");
		Thread.sleep(500);
		TestElement el = Variables.DIV_ID_TEST.element();
		List<WebElement> elements = SHelper.get().element().getListOf(el);

		WebElement element = find(elements).text("Correct").get();

		Assert.assertNotNull(element);
		Assert.assertTrue(element.getText().equals("Correct"));
	}
	
	@Test(groups= {"element"}, alwaysRun=true)
	public void verifyElementHelper_ListOfElements_ElementNotFound() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Correct</div>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Incorrect</div>');");
		Thread.sleep(500);
		List<WebElement> elements = SHelper.get().element().getListOf(Variables.DIV_ID_TEST.element());

		WebElement element = find(elements).text("NonCorrect").get();

		Assert.assertNull(element);
	}
	
	@Test(groups= {"element"}, alwaysRun=true)
	public void verifyElementHelper_ListOfElements_ElementFound_Equals() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Correct</div>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Incorrect</div>');");
		Thread.sleep(500);
		List<WebElement> elements = SHelper.get().element().getListOf(Variables.DIV_ID_TEST.element());

		WebElement element = find(elements).that(Condition.EQUAL).text("Correct").get();

		Assert.assertNotNull(element);
		Assert.assertTrue(element.getText().equals("Correct"));
	}
	
	@Test(groups= {"element"}, alwaysRun=true)
	public void verifyElementHelper_ListOfElements_ElementFound_Contains() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Correct</div>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Wrong</div>');");
		Thread.sleep(500);
		List<WebElement> elements = SHelper.get().element().getListOf(Variables.DIV_ID_TEST.element());

		WebElement element = find(elements).that(Condition.CONTAIN).text("corr").get();

		Assert.assertNotNull(element);
		Assert.assertTrue(element.getText().equals("Correct"));
	}
	
	@Test(groups= {"element"}, expectedExceptions=TestException.class, alwaysRun=true)
	public void verifyNoElementProvided_ExceptionThrown() throws Exception {
		List<WebElement> list = null;
		@SuppressWarnings("unused")
		WebElement element = find(list).text("Correct").get();
	}
	
	@Test(groups= {"element"}, expectedExceptions=TestException.class, alwaysRun=true)
	public void verifyEmptyList_ExceptionThrown() throws Exception {
		List<WebElement> list = new ArrayList<>();
		@SuppressWarnings("unused")
		WebElement element = find(list).text("Correct").get();
	}

}
