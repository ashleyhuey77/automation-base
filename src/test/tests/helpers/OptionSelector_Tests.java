/*
package tests.helpers;

import java.io.File;
import java.io.FileReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.driver.WebDriverListener;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.settings.LocalTest;
import com.warnermedia.page.utils.OptionSelector;
import com.warnermedia.page.utils.OptionSelector.OptionSelectorBuilder;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.wait.Condition;
import pages.TestInitialization;

@Listeners(WebDriverListener.class)
public class OptionSelector_Tests extends TestInitialization {
	
	@Test(groups= {"option"}, alwaysRun=true)
	public void verifyOptionSelector_Equals_OptionFound() throws Exception {
		String filePath = LocalReport.getFilePath() + 
				File.separator + "HTML Results" + 
				File.separator + LocalTest.getTestName()  + 
				"_" + LocalTest.getEnvironment().getBrowser() + ".html";
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test >Test 1</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test >Test 2</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test >Test 3</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test >Test 4</a></br>');");
		Thread.sleep(500);
		Locator locator = new Locator("a[id='Test']");
		By by = new By("css");

		new OptionSelector(new OptionSelectorBuilder()
				.findOption("Test 2")
				.thatIs(Condition.EQUAL)
				.in(new TestElement(locator, by)));

		FileReader reader = new FileReader(filePath);
		String newIS = extractText(reader);

		Assert.assertNotNull(
				newIS);
	}
	
	@Test(groups= {"option"}, alwaysRun=true)
	public void verifyOptionSelector_Equals_OptionNotFound() throws Exception {
		try {
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test >Test 1</a></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test >Test 2</a></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test >Test 3</a></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test >Test 4</a></br>');");
			Thread.sleep(500);
			Locator locator = new Locator("a[id='Test']");
			By by = new By("css");

			new OptionSelector(new OptionSelectorBuilder().findOption("Test 8").thatIs(Condition.EQUAL)
					.in(new TestElement(locator, by)));
			Assert.fail("Exception was expected to be thrown for a failed test case");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("Test 8 is not found in the list of available options. Unable to select the expected option."));
		}
	}
	
	@Test(groups= {"option"}, alwaysRun=true)
	public void verifyOptionSelector_Equals_ThrowsException() throws Exception {
		try {
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test >Test 1</a></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test >Test 2</a></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test >Test 3</a></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test >Test 4</a></br>');");
			Thread.sleep(500);
			Locator locator = new Locator("a[id='no']");
			By by = new By("css");

			new OptionSelector(new OptionSelectorBuilder().findOption("Test 1").thatIs(Condition.EQUAL)
					.in(new TestElement(locator, by)));
			Assert.fail("Exception was expected to be thrown for a failed test case");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: findOptionThatIsEqualToOptionInAList"));
		}
	}
	
	@Test(groups= {"option"}, alwaysRun=true)
	public void verifyOptionSelector_Contains_OptionFound() throws Exception {
		String filePath = LocalReport.getFilePath() + 
				File.separator + "HTML Results" + 
				File.separator + LocalTest.getTestName()  + 
				"_" + LocalTest.getEnvironment().getBrowser() + ".html";
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test >Yes</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test >Yeah</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test >Maybe</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test >Nah</a></br>');");
		Thread.sleep(500);
		Locator locator = new Locator("a[id='Test']");
		By by = new By("css");

		new OptionSelector(new OptionSelectorBuilder()
				.findOption("Yea")
				.thatIs(Condition.CONTAIN)
				.in(new TestElement(locator, by)));

		FileReader reader = new FileReader(filePath);
		String newIS = extractText(reader);

		Assert.assertNotNull(newIS);
	}
	
	@Test(groups= {"option"}, alwaysRun=true)
	public void verifyOptionSelector_Contains_OptionNotFound() throws Exception {
		try {
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test >Test 1</a></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test >Test 2</a></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test >Test 3</a></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test >Test 4</a></br>');");
			Thread.sleep(500);
			Locator locator = new Locator("a[id='Test']");
			By by = new By("css");

			new OptionSelector(new OptionSelectorBuilder().findOption("Test 8").thatIs(Condition.CONTAIN)
					.in(new TestElement(locator, by)));
			Assert.fail("Exception was expected to be thrown for a failed test case");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("Test 8 is not found in the list of available options. Unable to select the expected option."));
		}
	}
	
	@Test(groups= {"option"}, alwaysRun=true)
	public void verifyOptionSelector_Contains_ThrowsException() throws Exception {
		try {
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test >Test 1</a></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test >Test 2</a></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test >Test 3</a></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test >Test 4</a></br>');");
			Thread.sleep(500);
			Locator locator = new Locator("a[id='no']");
			By by = new By("css");

			new OptionSelector(new OptionSelectorBuilder().findOption("Test 1").thatIs(Condition.CONTAIN)
					.in(new TestElement(locator, by)));
			Assert.fail("Exception was expected to be thrown for a failed test case");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: findTheOptionContainedInAList"));
		}
	}
	
	@Test(groups= {"option"}, expectedExceptions=TestException.class, alwaysRun=true)
	public void verifyNoElementProvided_ExceptionThrown() throws Exception {
		new OptionSelector(new OptionSelectorBuilder().findOption("Test 1").thatIs(Condition.CONTAIN));
	}
	
	@Test(groups= {"option"}, expectedExceptions=TestException.class, alwaysRun=true)
	public void verifyNoOptionProvided_ExceptionThrown() throws Exception {
		new OptionSelector(new OptionSelectorBuilder().thatIs(Condition.CONTAIN).in(new TestElement(new Locator("string"), new By(How.CSS))));
	}
	
	@Test(groups= {"option"}, expectedExceptions=TestException.class, alwaysRun=true)
	public void verifyNoConditionProvided_ExceptionThrown() throws Exception {
		new OptionSelector(new OptionSelectorBuilder().findOption("Option").in(new TestElement(new Locator("string"), new By(How.CSS))));
	}

}
*/
