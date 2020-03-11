package tests.helpers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
import com.warnermedia.page.utils.ClickHelper;
import com.warnermedia.page.utils.ReportInfo;
import com.warnermedia.page.utils.ClickHelper.ClickBuilder;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Via;
import pages.TestInitialization;

@Listeners(WebDriverListener.class)
public class ClickHelper_Tests extends TestInitialization {
	
	@Test(alwaysRun=true)
	public void verifyClickSomeElement_ElementFound() throws Exception {
		String filePath = LocalReport.getFilePath() + 
				File.separator + "HTML Results" + 
				File.separator + LocalTest.getTestName()  + 
				"_" + LocalTest.getEnvironment().getBrowser() + ".html";
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		Locator locator = new Locator("input[id='Test']");
		By by = new By("css");

		new ClickHelper(new ClickBuilder(new ReportInfo("Test"))
				.clickOn(new TestElement(locator, by)));

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
				.clickOn(new TestElement(locator, by))
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
				.clickOn(new TestElement(locator, by))
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
				.clickOn(element));

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
					.clickOn(new TestElement(locator, by)));
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
					.clickOn(new TestElement(locator, by)));
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
					.clickOn(element));
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
