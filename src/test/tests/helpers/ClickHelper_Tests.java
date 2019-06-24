package tests.helpers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import common.base.helpers.ClickHelper;
import common.base.helpers.ClickHelper.ClickBuilder;
import common.base.vobjects.ReportInfo;
import common.utils.WebDriverListener;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import log.TestException;
import pages.TestInitialization;
import shelper.enums.Via;
import shelper.vobjects.By;
import shelper.vobjects.Locator;
import shelper.vobjects.TestElement;

@Listeners(WebDriverListener.class)
public class ClickHelper_Tests extends TestInitialization {
	
	ThreadLocal<ByteArrayOutputStream> baos = new ThreadLocal<>();
	ThreadLocal<PrintStream> ps = new ThreadLocal<>();
	
	@BeforeMethod
	public void initializeStream() {
		baos.set(new ByteArrayOutputStream());
		ps.set(new PrintStream(baos.get()));
	}
	
	@Test(groups= {"click"}, alwaysRun=true)
	public void verifyClickSomeElement_ElementFound() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		Locator locator = new Locator("input[id='Test']");
		By by = new By("css");

		PrintStream old = System.out;
		System.setOut(ps.get());

		new ClickHelper(new ClickBuilder(new ReportInfo("Test"))
				.clickOn(new TestElement(locator, by)));

		String inputString = getByteStreamMessage(baos.get(), old);

		Assert.assertNotNull(
				inputString);

		closeByteStream(ps.get(), baos.get());
	}
	
	@Test(groups= {"click"}, alwaysRun=true)
	public void verifyClickSomeElement_WithVia() throws InterruptedException, Exception, IOException {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		Locator locator = new Locator("input[id='Test']");
		By by = new By("css");

		PrintStream old = System.out;
		System.setOut(ps.get());

		new ClickHelper(new ClickBuilder(new ReportInfo("Test"))
				.clickOn(new TestElement(locator, by))
				.how(Via.JAVASCRIPT));

		String inputString = getByteStreamMessage(baos.get(), old);

		Assert.assertTrue(
				inputString.trim().contains("Step: clickSomeTestElement has passed. Test clicked successfully."));

		closeByteStream(ps.get(), baos.get());
	}
	
	@Test(groups= {"click"}, alwaysRun=true)
	public void verifyClickSomeElement_WithIndex() throws InterruptedException, Exception, IOException {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		Locator locator = new Locator("input[id='Test']");
		By by = new By("css");

		PrintStream old = System.out;
		System.setOut(ps.get());

		new ClickHelper(new ClickBuilder(new ReportInfo("Test"))
				.clickOn(new TestElement(locator, by))
				.withAnIndexOf(1));

		String inputString = getByteStreamMessage(baos.get(), old);

		Assert.assertNotNull(
				inputString);

		closeByteStream(ps.get(), baos.get());
	}
	
	@Test(groups= {"click"}, alwaysRun=true)
	public void verifyClickSomeElement_ElementFound_PredefinedElement() throws InterruptedException, Exception, IOException {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		Locator locator = new Locator("input[id='Test']");
		By by = new By("css");
		WebElement element = SHelper.get().element().get(new TestElement(locator, by));

		PrintStream old = System.out;
		System.setOut(ps.get());

		new ClickHelper(new ClickBuilder(new ReportInfo("Test"))
				.clickOn(element));

		String inputString = getByteStreamMessage(baos.get(), old);

		Assert.assertTrue(
				inputString.trim().contains("Step: clickSomeWebElement has passed. Test clicked successfully."));

		closeByteStream(ps.get(), baos.get());
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
	
	@AfterMethod
	public void closeStream() throws IOException {
		closeByteStream(ps.get(), baos.get());
	}

	private String getByteStreamMessage(ByteArrayOutputStream baos, PrintStream old) {
/*		System.out.flush();
		System.setOut(old);*/
		String inputString = baos.toString();
		return inputString;
	}

	private void closeByteStream(PrintStream ps, ByteArrayOutputStream baos) throws IOException {
		ps.close();
		baos.close();
	}

}
