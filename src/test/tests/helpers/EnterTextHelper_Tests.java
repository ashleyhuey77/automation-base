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
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.driver.WebDriverListener;
import com.warnermedia.page.utils.EnterTextHelper;
import com.warnermedia.page.utils.ReportInfo;
import com.warnermedia.page.utils.EnterTextHelper.EnterTextBuilder;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import pages.TestInitialization;

@Listeners(WebDriverListener.class)
public class EnterTextHelper_Tests extends TestInitialization {
	
	ThreadLocal<ByteArrayOutputStream> baos = new ThreadLocal<>();
	ThreadLocal<PrintStream> ps = new ThreadLocal<>();
	
	@BeforeMethod
	public void initializeStream() {
		baos.set(new ByteArrayOutputStream());
		ps.set(new PrintStream(baos.get()));
	}
	
	@Test(groups= {"etext"}, alwaysRun=true)
	public void verifyEnterAValueIntoATextField() throws Exception, IOException, InterruptedException {
		Thread.sleep(800);
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		Locator locator = new Locator("#Test");
		By by = new By("css");

		PrintStream old = System.out;
		System.setOut(ps.get());

		new EnterTextHelper(new EnterTextBuilder(new ReportInfo("Test Element"))
				.enterText("Test123")
				.into(new TestElement(locator, by)));
		;

		String inputString = getByteStreamMessage(baos.get(), old);

		Assert.assertNotNull(inputString);

		closeByteStream(ps.get(), baos.get());
	}
	
	@Test(groups= {"etext"}, alwaysRun=true)
	public void verifyEnterAValueIntoATextField_NullValue() throws Exception, IOException, InterruptedException {
		Thread.sleep(800);
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		Locator locator = new Locator("#Test");
		By by = new By("css");

		PrintStream old = System.out;
		System.setOut(ps.get());

		new EnterTextHelper(new EnterTextBuilder(new ReportInfo("Test Element"))
				.enterText(null)
				.into(new TestElement(locator, by)));

		String inputString = getByteStreamMessage(baos.get(), old);
		String newIS = inputString.replaceAll(" ", "");

		Assert.assertNotNull(newIS);

		closeByteStream(ps.get(), baos.get());
	}
	
	@Test(groups= {"etext"}, alwaysRun=true)
	public void verifyEnterAValueIntoATextField_ElementNotFound() throws Exception, IOException, InterruptedException {
		try {
			Locator locator = new Locator("#Test");
			By by = new By("css");

			new EnterTextHelper(new EnterTextBuilder(new ReportInfo("Test Element"))
					.enterText("Test123")
					.into(new TestElement(locator, by)));
			Assert.fail("Error expected to be thrown");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim()
					.contains("Test Element does not display as expected. Unable to enter text in this field."));
		}
	}
	
	@Test(groups= {"etext"}, alwaysRun=true)
	public void verifyEnterAValueIntoATextField_ElementDefined() throws Exception, IOException, InterruptedException {
		Thread.sleep(800);
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);

		PrintStream old = System.out;
		System.setOut(ps.get());
		WebElement el = SHelper.get().element().get(element);

		new EnterTextHelper(new EnterTextBuilder(new ReportInfo("Test Element"))
				.enterText("Test123")
				.into(el));

		String inputString = getByteStreamMessage(baos.get(), old);

		Assert.assertTrue(inputString.trim()
				.contains("enterTextIntoWebElement has passed. Test Element has been entered successfully"));

		closeByteStream(ps.get(), baos.get());
	}
	
	@Test(groups= {"etext"}, alwaysRun=true)
	public void verifyEnterAValueIntoATextField_ElementNotFound_ElementDefined()
			throws Exception, IOException, InterruptedException {
		try {
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<input id=Test > </input>');");
			Locator locator = new Locator("#Test");
			By by = new By("css");
			TestElement element = new TestElement(locator, by);
			Thread.sleep(500);
			WebElement test = SHelper.get().element().get(element);
			LocalDriver.getDriver().navigate().refresh();
			Thread.sleep(500);

			new EnterTextHelper(new EnterTextBuilder(new ReportInfo("Test"))
					.enterText("Test123")
					.into(test));
			Assert.fail("Error expected to be thrown");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("enterTextIntoWebElement"));
		}
	}
	
	@Test(groups= {"etext"}, expectedExceptions=TestException.class, alwaysRun=true)
	public void verifyNoElementProvided_ExceptionThrown() throws Exception {
		new EnterTextHelper(new EnterTextBuilder(new ReportInfo("Test Element"))
				.enterText("Value"));;
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
