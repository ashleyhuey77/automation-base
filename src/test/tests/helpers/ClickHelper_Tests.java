/*
package tests.helpers;

import java.io.File;
import java.io.FileReader;

import com.warnermedia.selenium.SeleniumHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.driver.WebDriverListener;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.settings.LocalTest;
import pages.TestInitialization;
import utils.Variables;

@Listeners(WebDriverListener.class)
public class ClickHelper_Tests extends TestInitialization {

	@BeforeMethod
	public void setup() {
		SHelper.set(new SeleniumHelper());
	}
	
	@Test(alwaysRun=true)
	public void verifyClickSomeElement_ElementFound() throws Exception {
		String filePath = LocalReport.getFilePath() + 
				File.separator + "HTML Results" + 
				File.separator + LocalTest.getTestName()  + 
				"_" + LocalTest.getEnvironment().getBrowser() + ".html";
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);

		click().on(Variables.INPUT_ID_TEST).start();

		FileReader reader = new FileReader(filePath);
		String newIS = extractText(reader);

		Assert.assertNotNull(
				newIS);
	}
	
	@Test(groups= {"click"}, alwaysRun=true)
	public void verifyClickSomeElement_WithVia() throws InterruptedException, Exception, IOException {
		String filePath = LocalReport.getFilePath() + 
				File.separator + "HTML Results" + 
				File.separator + LocalTest.getTestName()  + 
				"_" + LocalTest.getEnvironment().getBrowser() + ".html";
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		Locator locator = new Locator("input[id='Test']");
		By by = new By("css");

		new ClickHelper(new ClickBuilder(new ReportInfo("Test"))
				.on(new TestElement(locator, by))
				.how(Via.JAVASCRIPT));

		FileReader reader = new FileReader(filePath);
		String newIS = extractText(reader);

		Assert.assertTrue(
				newIS.contains("clickSomeTestElement Test clicked successfully. DONE"));
	}
	
	@Test(groups= {"click"}, alwaysRun=true)
	public void verifyClickSomeElement_WithIndex() throws InterruptedException, Exception, IOException {
		String filePath = LocalReport.getFilePath() + 
				File.separator + "HTML Results" + 
				File.separator + LocalTest.getTestName()  + 
				"_" + LocalTest.getEnvironment().getBrowser() + ".html";
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		Locator locator = new Locator("input[id='Test']");
		By by = new By("css");

		new ClickHelper(new ClickBuilder(new ReportInfo("Test"))
				.on(new TestElement(locator, by))
				.withAnIndexOf(1));

		FileReader reader = new FileReader(filePath);
		String newIS = extractText(reader);

		Assert.assertNotNull(
				newIS);
	}
	
	@Test(groups= {"click"}, alwaysRun=true)
	public void verifyClickSomeElement_ElementFound_PredefinedElement() throws Exception {
		String filePath = LocalReport.getFilePath() + 
				File.separator + "HTML Results" + 
				File.separator + LocalTest.getTestName()  + 
				"_" + LocalTest.getEnvironment().getBrowser() + ".html";
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		Locator locator = new Locator("input[id='Test']");
		By by = new By("css");
		WebElement element = SHelper.get().element().get(new TestElement(locator, by));

		new ClickHelper(new ClickBuilder(new ReportInfo("Test"))
				.on(element));

		FileReader reader = new FileReader(filePath);
		String newIS = extractText(reader);

		Assert.assertTrue(
				newIS.contains("clickSomeWebElement Test clicked successfully. DONE"));
	}

	@Test(groups= {"click"}, alwaysRun=true)
	public void verifyClickSomeElement_ElementNotFound() throws InterruptedException, Exception {
		try {
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<input id=Test > </input>');");
			Thread.sleep(500);
			Locator locator = new Locator("input[id='NotHere']");
			By by = new By("css");

			new ClickHelper(new ClickBuilder(new ReportInfo("Test Element"))
					.on(new TestElement(locator, by)));
			Assert.fail("Exception was expected to be thrown for a failed test case");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("Element is not on the page. Unable to click the Test Element"));
		}
	}

	@Test(groups= {"click"}, alwaysRun=true)
	public void verifyClickSomeElement_ThrowsException() throws InterruptedException {
		try {
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<input id=Test > </input>');");
			Thread.sleep(500);
			Locator locator = new Locator("#NotHere");
			By by = new By("id");

			new ClickHelper(new ClickBuilder(new ReportInfo("Test"))
					.on(new TestElement(locator, by)));
			Assert.fail("Exception was expected to be thrown for a failed test case");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: clickSomeTestElement"));
		}
	}
	
	@Test(groups= {"click"}, alwaysRun=true)
	public void verifyClickSomeElement_PredefinedElement_ThrowsException() throws InterruptedException {
		try {
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<input id=Test > </input>');");
			Thread.sleep(500);
			Locator locator = new Locator("Test");
			By by = new By("id");
			WebElement element = SHelper.get().element().get(new TestElement(locator, by));
			SHelper.get().page().refresh();
			Thread.sleep(800);

			new ClickHelper(new ClickBuilder(new ReportInfo("Test"))
					.on(element));
			Assert.fail("Exception was expected to be thrown for a failed test case");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: clickSomeWebElement"));
		}
	}
	
	@Test(groups= {"click"}, expectedExceptions=TestException.class, alwaysRun=true)
	public void verifyNoElementProvided_ExceptionThrown() throws Exception {
		new ClickHelper(new ClickBuilder(new ReportInfo("Test")));
	}
}
*/
