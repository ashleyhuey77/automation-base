package tests.helpers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import common.base.helpers.OptionSelector;
import common.base.helpers.OptionSelector.OptionSelectorBuilder;
import common.utils.WebDriverListener;
import common.utils.managers.LocalDriver;
import log.TestException;
import pages.TestInitialization;
import shelper.enums.Condition;
import shelper.vobjects.By;
import shelper.vobjects.Locator;
import shelper.vobjects.TestElement;

@Listeners(WebDriverListener.class)
public class OptionSelector_Tests extends TestInitialization {
	
	ThreadLocal<ByteArrayOutputStream> baos = new ThreadLocal<>();
	ThreadLocal<PrintStream> ps = new ThreadLocal<>();
	
	@BeforeMethod
	public void initializeStream() {
		baos.set(new ByteArrayOutputStream());
		ps.set(new PrintStream(baos.get()));
	}
	
	@Test(groups= {"option"}, alwaysRun=true)
	public void verifyOptionSelector_Equals_OptionFound() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test >Test 1</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test >Test 2</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test >Test 3</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test >Test 4</a></br>');");
		Thread.sleep(500);
		Locator locator = new Locator("a[id='Test']");
		By by = new By("css");

		PrintStream old = System.out;
		System.setOut(ps.get());

		new OptionSelector(new OptionSelectorBuilder()
				.findOption("Test 2")
				.thatIs(Condition.EQUAL)
				.For(new TestElement(locator, by)));

		String inputString = getByteStreamMessage(baos.get(), old);

		Assert.assertNotNull(
				inputString);

		closeByteStream(ps.get(), baos.get());
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
					.For(new TestElement(locator, by)));
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
					.For(new TestElement(locator, by)));
			Assert.fail("Exception was expected to be thrown for a failed test case");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: findOptionThatIsEqualToOptionInAList"));
		}
	}
	
	@Test(groups= {"option"}, alwaysRun=true)
	public void verifyOptionSelector_Contains_OptionFound() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test >Test 1</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test >Test 2</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test >Test 3</a></br>');");
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a href=www.google.com id=Test >Test 4</a></br>');");
		Thread.sleep(500);
		Locator locator = new Locator("a[id='Test']");
		By by = new By("css");

		PrintStream old = System.out;
		System.setOut(ps.get());

		new OptionSelector(new OptionSelectorBuilder()
				.findOption("Test 2")
				.thatIs(Condition.CONTAIN)
				.For(new TestElement(locator, by)));

		String inputString = getByteStreamMessage(baos.get(), old);

		Assert.assertNotNull(inputString);

		closeByteStream(ps.get(), baos.get());
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
					.For(new TestElement(locator, by)));
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
					.For(new TestElement(locator, by)));
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
		new OptionSelector(new OptionSelectorBuilder().thatIs(Condition.CONTAIN).For(new TestElement(new Locator("string"), new By(How.CSS))));
	}
	
	@Test(groups= {"option"}, expectedExceptions=TestException.class, alwaysRun=true)
	public void verifyNoConditionProvided_ExceptionThrown() throws Exception {
		new OptionSelector(new OptionSelectorBuilder().findOption("Option").For(new TestElement(new Locator("string"), new By(How.CSS))));
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
