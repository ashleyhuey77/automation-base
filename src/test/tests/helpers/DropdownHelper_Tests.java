package tests.helpers;

import java.io.File;
import java.io.FileReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.driver.WebDriverListener;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.settings.LocalTest;
import com.warnermedia.page.utils.DropdownHelper;
import com.warnermedia.page.utils.ReportInfo;
import com.warnermedia.page.utils.DropdownHelper.DropdownBuilder;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import pages.TestInitialization;

@Listeners(WebDriverListener.class)
public class DropdownHelper_Tests extends TestInitialization {
	
	@Test(groups= {"dropdown"}, alwaysRun=true)
	public void verifySelectSomeOptionFromDropdown_TestElement_OptionFound() throws Exception {
		String filePath = LocalReport.getFilePath() + 
				File.separator + "HTML Results" + 
				File.separator + LocalTest.getTestName()  + 
				"_" + LocalTest.getEnvironment().getBrowser() + ".html";
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test ></input></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test2 >Test 2</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test2 >Test 3</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test2 >Test 4</a></br>');");
		Thread.sleep(500);
		TestElement click = new TestElement(new Locator("input[id='Test']"), new By("css"));
		TestElement options = new TestElement(new Locator("a[id='Test2']"), new By("css"));

		new DropdownHelper(new DropdownBuilder(new ReportInfo("Test"))
				.clickMenuToOpen(click)
				.searchForOption(click)
				.selectOption("Test 3")
				.fromOptionList(options));

		FileReader reader = new FileReader(filePath);
		String newIS = extractText(reader);

		Assert.assertNotNull(
				newIS);
	}
	
	@Test(groups= {"dropdown"}, alwaysRun=true)
	public void verifySelectSomeOptionFromDropdown_TestElement_NoSearch_OptionFound() throws Exception {
		String filePath = LocalReport.getFilePath() + 
				File.separator + "HTML Results" + 
				File.separator + LocalTest.getTestName()  + 
				"_" + LocalTest.getEnvironment().getBrowser() + ".html";
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test ></input></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test2 >Test 2</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test2 >Test 3</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test2 >Test 4</a></br>');");
		Thread.sleep(500);
		TestElement click = new TestElement(new Locator("input[id='Test']"), new By("css"));
		TestElement options = new TestElement(new Locator("a[id='Test2']"), new By("css"));

		new DropdownHelper(new DropdownBuilder(new ReportInfo("Test"))
				.clickMenuToOpen(click)
				.selectOption("Test 3")
				.fromOptionList(options));

		FileReader reader = new FileReader(filePath);
		String newIS = extractText(reader);

		Assert.assertNotNull(
				newIS);
	}
	
	@Test(groups= {"dropdown"}, alwaysRun=true)
	public void verifySelectSomeOptionFromDropdown_TestElement_ClickElementNotFound() throws Exception {
		try {
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<input id=Test ></input></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test2 >Test 2</a></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test2 >Test 3</a></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test2 >Test 4</a></br>');");
			Thread.sleep(500);
			TestElement click = new TestElement(new Locator("input[id='no']"), new By("css"));
			TestElement options = new TestElement(new Locator("a[id='Test2']"), new By("css"));

			new DropdownHelper(new DropdownBuilder(new ReportInfo("Test")).clickMenuToOpen(click).searchForOption(click)
					.selectOption("Test 3").fromOptionList(options));
			Assert.fail("Exception was expected to be thrown for a failed test case");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains(
					"Element is not available. Unable to select the Test 3 option from the drop down list."));
		}
	}
	
	@Test(groups= {"dropdown"}, alwaysRun=true)
	public void verifySelectSomeOptionFromDropdown_WebElement_OptionFound() throws Exception {
		String filePath = LocalReport.getFilePath() + 
				File.separator + "HTML Results" + 
				File.separator + LocalTest.getTestName()  + 
				"_" + LocalTest.getEnvironment().getBrowser() + ".html";
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test ></input></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test2 >Test 2</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test2 >Test 3</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test2 >Test 4</a></br>');");
		Thread.sleep(500);
		WebElement click = SHelper.get().element().get(new TestElement(new Locator("input[id='Test']"), new By("css")));
		TestElement options = new TestElement(new Locator("a[id='Test2']"), new By("css"));

		new DropdownHelper(new DropdownBuilder(new ReportInfo("Test"))
				.clickMenuToOpen(click)
				.searchForOption(click)
				.selectOption("Test 3")
				.fromOptionList(options));

		FileReader reader = new FileReader(filePath);
		String newIS = extractText(reader);

		Assert.assertTrue(
				newIS.contains("findOptionThatIsEqualToOptionInAList Test 3 has been selected successfully. DONE"));

	}
	
	@Test(groups= {"dropdown"}, alwaysRun=true)
	public void verifySelectSomeOptionFromDropdown_WebElement_NoSearch_OptionFound() throws Exception {
		String filePath = LocalReport.getFilePath() + 
				File.separator + "HTML Results" + 
				File.separator + LocalTest.getTestName()  + 
				"_" + LocalTest.getEnvironment().getBrowser() + ".html";
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test ></input></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test2 >Test 2</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test2 >Test 3</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test2 >Test 4</a></br>');");
		Thread.sleep(500);
		WebElement click = SHelper.get().element().get(new TestElement(new Locator("input[id='Test']"), new By("css")));
		TestElement options = new TestElement(new Locator("a[id='Test2']"), new By("css"));

		new DropdownHelper(new DropdownBuilder(new ReportInfo("Test"))
				.clickMenuToOpen(click)
				.selectOption("Test 3")
				.fromOptionList(options));

		FileReader reader = new FileReader(filePath);
		String newIS = extractText(reader);

		Assert.assertNotNull(
				newIS);
	}
	
	@Test(groups= {"dropdown"}, alwaysRun=true)
	public void verifySelectSomeOptionFromDropdown_WebElement_ClickElementNotFound() throws Exception {
		try {
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<input id=Test ></input></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test2 >Test 2</a></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test2 >Test 3</a></br>');");
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<a href=www.google.com id=Test2 >Test 4</a></br>');");
			Thread.sleep(500);
			WebElement click = SHelper.get().element().get(new TestElement(new Locator("input[id='Test']"), new By("css")));
			TestElement options = new TestElement(new Locator("a[id='Test2']"), new By("css"));
			SHelper.get().page().refresh();
			Thread.sleep(300);

			new DropdownHelper(new DropdownBuilder(new ReportInfo("Test")).clickMenuToOpen(click).searchForOption(click)
					.selectOption("Test 3").fromOptionList(options));
			Assert.fail("Exception was expected to be thrown for a failed test case");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains(
					"StepName: selectWebElementFromNonDropdown"));
		}
	}
	
	@Test(groups= {"dropdown"}, expectedExceptions=TestException.class, alwaysRun=true)
	public void verifyNoClickElementProvided_ExceptionThrown() throws Exception {
		TestElement options = new TestElement(new Locator("a[id='Test2']"), new By("css"));
		new DropdownHelper(new DropdownBuilder(new ReportInfo("Test"))
				.selectOption("Test 3").fromOptionList(options));
	}
	
	@Test(groups= {"dropdown"}, expectedExceptions=TestException.class, alwaysRun=true)
	public void verifyNoOptionsElementProvided_ExceptionThrown() throws Exception {
		TestElement click = new TestElement(new Locator("input[id='Test']"), new By("css"));
		new DropdownHelper(new DropdownBuilder(new ReportInfo("Test")).clickMenuToOpen(click).searchForOption(click)
				.selectOption("Test 3"));
	}
	
	@Test(groups= {"dropdown"}, expectedExceptions=TestException.class, alwaysRun=true)
	public void verifyNoOptionProvided_ExceptionThrown() throws Exception {
		TestElement click = new TestElement(new Locator("input[id='Test']"), new By("css"));
		TestElement options = new TestElement(new Locator("a[id='Test2']"), new By("css"));
		new DropdownHelper(new DropdownBuilder(new ReportInfo("Test")).clickMenuToOpen(click).fromOptionList(options));
	}

}
