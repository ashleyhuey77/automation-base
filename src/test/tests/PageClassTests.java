package tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.driver.WebDriverListener;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.settings.LocalTest;
import com.warnermedia.page.core.DaysOfTheWeek;
import com.warnermedia.page.core.Entity;
import com.warnermedia.page.core.Timezones;
import com.warnermedia.page.utils.date.Date;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.utils.ConsoleHelper;
import com.warnermedia.utils.TestUtils;
import pages.TestInitialization;
import pages.TestPage;

@Listeners(WebDriverListener.class)
public class PageClassTests extends TestInitialization {
	
	@Test
	public void verifyTheExpectedValueContainsTheActualValue() throws Exception {
		TestPage page = new TestPage();

		page.testActualVersusExpected("Test", "Test", "Test");
	}

	@Test
	public void verifyTheExpectedValueNull_contains() throws Exception {
		String filePath = LocalReport.getFilePath() + 
				File.separator + "HTML Results" + 
				File.separator + LocalTest.getTestName()  + 
				"_" + LocalTest.getEnvironment().getBrowser() + ".html";
		try {
			TestPage page = new TestPage();

			page.testActualVersusExpected("Test", "Test", "Test");

			page.testActualVersusExpected(null, "Test", "Test");
			Assert.fail("Expected an AssertionFailedError to be thrown");
		} catch (Exception ex) {
			FileReader reader = new FileReader(filePath);
    		String newIS = extractText(reader);

			Assert.assertTrue(newIS.trim()
					.contains("Test returned null. Variable was not expected to return null. FAIL"));

			ex.toString();
		}
	}

	@Test
	public void verifyTheActualValueNull_contains() throws Exception {
		try {
			TestPage page = new TestPage();

			page.testActualVersusExpected("Test", null, "Test");
			Assert.fail("Expected an AssertionFailedError to be thrown");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifyTheActualValueContainsTheExpectedValue"));
			Assert.assertTrue(m.trim().contains(
					"ErrorMessage: Test returned null in the actual variable that was set. Check the test to verify all variables are being assigned a value appropriately."));
		}
	}

	@Test
	public void verifyTheActualValueDoesntContainExpectedValue() throws Exception {
		try {
			TestPage page = new TestPage();

			page.testActualVersusExpected("Test", "Nothing", "Test");
			Assert.fail("Expected an AssertionFailedError to be thrown");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifyTheActualValueContainsTheExpectedValue"));
			Assert.assertTrue(m.trim().contains("ErrorMessage: Test is not set as expected. Nothing is set instead."));
		}
	}

	@Test
	public void verifySubtractDays_DayStartsWith0() throws Exception {
		TestPage page = new TestPage();

		String actualDate = page.testSubtractDays("05-JAN-2017", 1, 1);

		Assert.assertEquals("4-Jan-2017", actualDate);
	}

	@Test
	public void verifySubtractDays_DayStartsWith1() throws Exception {
		TestPage page = new TestPage();

		String actualDate = page.testSubtractDays("15-JAN-2017", 1, 1);

		Assert.assertEquals("14-Jan-2017", actualDate);
	}

	@Test
	public void verifySubtractDays_IncorrectDateFormat() throws Exception {
		try {
			TestPage page = new TestPage();

			String actualDate = page.testSubtractDays("123456789", 1, 1);

			Assert.fail("Expected AssertionError exception to be thrown." + actualDate);
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: subtractDays"));
			Assert.assertTrue(m.trim().contains("ErrorMessage : Unparseable date:"));
		}
	}

	@Test
	public void verifyCleanUp() throws Exception {
		TestPage page = new TestPage();
		String value = null;
		value = "SomeTestValue";

		page.testCleanUp(value);

		Assert.assertTrue(!TestUtils.isNullOrBlank(value));

		value = null;
		page.testCleanUp(null);

		Assert.assertTrue(TestUtils.isNullOrBlank(value));
	}

	@Test
	public void verifyTheExpectedValueMatchesTheActualValue() throws Exception, IOException {
		TestPage page = new TestPage();

		page.testActualVersusExpectedExact("Test", "Test", "Test");
	}

	@Test
	public void verifyTheExpectedValueNull_exactMatch() throws Exception {
		try {
			TestPage page = new TestPage();

			page.testActualVersusExpectedExact(null, "Test", "Test");
			Assert.fail("Expected an AssertionFailedError to be thrown");
		} catch (Exception ex) {
			String m = ex.toString();
			String newM = m.replaceAll(" ", "");
			Assert.assertTrue(newM.trim().contains("StepName:verifyTheActualValueMatchesTheExpectedValue"));
			Assert.assertTrue(newM.trim().contains(
					"ErrorMessage:Testreturnednullineithertheactualortheexpectedvariablethatwasset.Checkthetesttoverifyallvariablesarebeingassignedavalueappropriately.Actual:Test.Expected:null"));
		}
	}

	@Test
	public void verifyTheActualValueNull_exactMatch() throws Exception, IOException {
		try {
			TestPage page = new TestPage();

			page.testActualVersusExpectedExact("Test", null, "Test");
			Assert.fail("Expected an AssertionFailedError to be thrown");
		} catch (Exception ex) {
			String m = ex.toString();
			String newM = m.replaceAll(" ", "");
			Assert.assertTrue(newM.trim().contains("StepName:verifyTheActualValueMatchesTheExpectedValue"));
			Assert.assertTrue(newM.trim().contains(
					"ErrorMessage:Testreturnednullineithertheactualortheexpectedvariablethatwasset.Checkthetesttoverifyallvariablesarebeingassignedavalueappropriately.Actual:null.Expected:Test"));
		}
	}

	@Test
	public void verifyTheActualValueDoesntMatchExpectedValue() throws Exception, IOException {
		try {
			TestPage page = new TestPage();

			page.testActualVersusExpectedExact("Test", "Nothing", "Test");
			Assert.fail("Expected an AssertionFailedError to be thrown");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifyTheActualValueMatchesTheExpectedValue"));
			Assert.assertTrue(m.trim().contains("ErrorMessage: Test is not set as expected. Nothing is set instead."));
		}
	}

	@Test
	public void verifyRemovingCharacters_containsCharacter() throws Exception {
		TestPage page = new TestPage();

		String actualValue = page.testRemoveOrChangeUnwantedCharacter("Test!", "!", ".");

		Assert.assertEquals("Test.", actualValue);
	}

	@Test
	public void verifyRemovingCharacters_doesntContainCharacter() throws Exception {
		TestPage page = new TestPage();

		String actualValue = page.testRemoveOrChangeUnwantedCharacter("Test", "!", ".");

		Assert.assertEquals("Test", actualValue);
	}

	@Test
	public void verifyRemovingCharacters_nullException() throws Exception {
		try {
			TestPage page = new TestPage();

			String actualValue = page.testRemoveOrChangeUnwantedCharacter(null, "!", ".");
			Assert.fail("Test expected to throw exception" + actualValue);
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("ErrorMessage : NullPointerException was thrown. Please check for null values."));
		}
	}

	@Test
	public void failTestIfVariableIsNull_nullVariable() throws Exception {
		try {
			TestPage page = new TestPage();

			page.testFailIfNull(null, "Passed", "Failed");
			;
			Assert.fail("Test expected to throw exception");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: failTestIfVariableIsNull"));
		}
	}

	@Test
	public void failTestIfVariableIsNull_notNullVariable() throws Exception, IOException {
		String filePath = LocalReport.getFilePath() + 
				File.separator + "HTML Results" + 
				File.separator + LocalTest.getTestName()  + 
				"_" + LocalTest.getEnvironment().getBrowser() + ".html";
		TestPage page = new TestPage();
		
		page.testFailIfNull("var", "Passed", "Failed");

		FileReader reader = new FileReader(filePath);
		String newIS = extractText(reader);

		Assert.assertTrue(newIS.contains("failTestIfVariableIsNull Passed PASS"));

	}

	@Test
	public void verifySomeElementContainsTheExpectedText_PredefinedWebElement_RemoveAllSpacesFalse()
			throws Exception, InterruptedException, IOException {
		ConsoleHelper.analyzeLog();
		TestPage page = new TestPage();
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test >Testing 123</div>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement textEl = SHelper.get().element().get(element);

		page.testVerifySomeElementContainsTheExpectedText(textEl, "Testing 123", "Test Element", false);

	}

	@Test
	public void verifySomeElementContainsTheExpectedText_PredefinedWebElement_RemoveAllSpacesTrue()
			throws Exception, InterruptedException, IOException {
		TestPage page = new TestPage();
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test >Testing 123</div>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement textEl = SHelper.get().element().get(element);

		page.testVerifySomeElementContainsTheExpectedText(textEl, "Testing 123", "Test Element", true);
	}

	@Test
	public void verifySomeElementContainsTheExpectedText_PredefinedWebElement_ElementDoesNotContainCorrectText()
			throws Exception, InterruptedException, IOException {
		try {
			TestPage page = new TestPage();
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<div id=Test >Testing 123</div>');");
			Locator locator = new Locator("Test");
			By by = new By("id");
			TestElement element = new TestElement(locator, by);
			Thread.sleep(500);
			WebElement textEl = SHelper.get().element().get(element);

			page.testVerifySomeElementContainsTheExpectedText(textEl, "Not correct", "Test Element", false);
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifySomeWebElementContainsTheExpectedText"));
			Assert.assertTrue(m.trim().contains(
					"Test Element does not contain the correct text. Expected text: Not correct. Actual text: Testing 123"));
		}
	}

	@Test
	public void verifySomeElementContainsTheExpectedText_PredefinedWebElement_NullExpectedValue()
			throws Exception, InterruptedException, IOException {
		try {
			TestPage page = new TestPage();
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<div id=Test >Testing 123</div>');");
			Locator locator = new Locator("Test");
			By by = new By("id");
			TestElement element = new TestElement(locator, by);
			Thread.sleep(500);
			WebElement textEl = SHelper.get().element().get(element);

			page.testVerifySomeElementContainsTheExpectedText(textEl, null, "Test Element", false);
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim()
					.contains("Expected Text is null."));
		}
	}

	@Test
	public void verifySomeElementIsNotPresent_ElementNotPresent() throws Exception {
		TestPage page = new TestPage();
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test >Testing 123</div>');");
		Thread.sleep(500);
		Locator locator = new Locator("NotHere");
		By by = new By("id");

		page.testverifySomeElementIsNotPresent(locator, by, "Test Element");

	}

	@Test
	public void verifySomeElementIsNotPresent_ElementPresent() throws InterruptedException {
		try {
			TestPage page = new TestPage();
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			Locator locator = new Locator("Test");
			By by = new By("id");

			page.testverifySomeElementIsNotPresent(locator, by, "Test Element");
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifySomeElementIsNotPresent"));
			Assert.assertTrue(m.trim().contains(
					"ErrorMessage: Test Element should not display in the page. It does display. This is not expected."));

		}
	}

	@Test
	public void verifySomeElementIsNotPresent_ExceptionThrown() throws InterruptedException {
		try {
			TestPage page = new TestPage();
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			Locator locator = new Locator("Test");
			By by = new By("id");

			page.testverifySomeElementIsNotPresent(locator, by, "Test Element");
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifySomeElementIsNotPresent"));
		}
	}

	@Test
	public void verifySomeElementIsPresent_ElementPresent() throws Exception {
		String filePath = LocalReport.getFilePath() + 
				File.separator + "HTML Results" + 
				File.separator + LocalTest.getTestName()  + 
				"_" + LocalTest.getEnvironment().getBrowser() + ".html";
		TestPage page = new TestPage();
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test >Testing 123</div>');");
		Thread.sleep(500);
		Locator locator = new Locator("Test");
		By by = new By("id");

		page.testVerifySomeElementIsPresent(locator, by, "Test Element");

		FileReader reader = new FileReader(filePath);
		String newIS = extractText(reader);

		Assert.assertTrue(newIS.contains(
				"Test Element displays in the page as expected. PASS"));
	}

	@Test
	public void verifySomeElementIsPresent_ElementNotPresent() throws InterruptedException {
		try {
			TestPage page = new TestPage();
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			Locator locator = new Locator("NotHere");
			By by = new By(How.ID);

			page.testVerifySomeElementIsPresent(locator, by, "Test Element");
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifySomeElementIsPresent"));
			Assert.assertTrue(m.trim().contains(
					"ErrorMessage: Test Element should display in the page. It does not display as expected."));

		}
	}

	@Test
	public void verifySomeElementIsPresent_ExceptionThrown() throws InterruptedException {
		try {
			TestPage page = new TestPage();
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			Locator locator = new Locator("NotHere");
			By by = new By("id");

			page.testVerifySomeElementIsPresent(locator, by, "Test Element");
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifySomeElementIsPresent"));
		}
	}
	
	@Test
	public void verifyCheckCookiesMethod() throws Exception {
		try {
			TestPage page = new TestPage();
			page.testCookiesThing();
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	@Test
	public void verifyGetFutureDate() throws Exception {
		try {
			TestPage page = new TestPage();
			int date = page.testGetFutureDate(1);
			Assert.assertNotNull(date);
			Assert.assertTrue(date > 0);
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	@Test
	public void verifyGetTotalDaysInMonth() throws Exception {
		try {
			TestPage page = new TestPage();
			int date = page.testGetTotalDaysInMonth();
			Assert.assertNotNull(date);
			Assert.assertTrue(date > 27);
			Assert.assertTrue(date < 32);
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyRefreshPageAndWaitForElementToDisplay_ExceptionThrown() throws Exception {
			TestPage page = new TestPage();
			Locator locator = new Locator("nope");
			By by = new By("id");

			page.refreshPageAndWaitForElementToDisplay(new TestElement(locator, by), 1);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyRefreshPageAndWaitForElementToDisplay_PredefinedWebelement_ExceptionThrown() throws Exception {
			TestPage page = new TestPage();
			((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<div id=Test >Testing123</div>');");
			Locator locator = new Locator("Test");
			By by = new By("id");

			page.refreshPageAndWaitForElementToDisplay(new TestElement(locator, by), 1);
	}
	
	@Test
	public void verifyGetNumbersFromString() throws Exception {
		try {
			TestPage page = new TestPage();
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			Locator locator = new Locator("Test");
			By by = new By(How.ID);

			String result = page.testGetNumbersFromString(new TestElement(locator, by));
			Assert.assertNotNull(result);
			Assert.assertTrue(result.equals("123"));
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyGetNumbersFromString_throwsException() throws Exception {
			TestPage page = new TestPage();
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			Locator locator = new Locator("Nope");
			By by = new By(How.ID);

			page.testGetNumbersFromString(new TestElement(locator, by));
	}
	
	@Test
	public void verifyGetNumbersFromString_PredefinedWebElement() throws Exception {
		try {
			TestPage page = new TestPage();
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			Locator locator = new Locator("Test");
			By by = new By(How.ID);
			WebElement element = SHelper.get().element().get(new TestElement(locator, by));

			String result = page.testGetNumbersFromString(element);
			Assert.assertNotNull(result);
			Assert.assertTrue(result.equals("123"));
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyGetNumbersFromString__PredefinedWebElement_throwsException() throws Exception {
			TestPage page = new TestPage();
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			Locator locator = new Locator("Test");
			By by = new By(How.ID);
			WebElement element = SHelper.get().element().get(new TestElement(locator, by));
			SHelper.get().page().refresh();

			page.testGetNumbersFromString(element);
	}

	@Test
	public void verifyPageEnums() throws Exception {
		try {
			System.out.println(Date.FUTURE_DATE);
			System.out.println(Date.PAST_DATE);
			System.out.println(Date.PRESENT_DATE);
			System.out.println(DaysOfTheWeek.ALL);
			System.out.println(DaysOfTheWeek.WEEKDAYS);
			System.out.println(DaysOfTheWeek.WEEKENDS);
			System.out.println(Entity.CRASH_RECORDS);
			System.out.println(Entity.DRAFTS);
			System.out.println(Entity.ENCODERS);
			System.out.println(Entity.MS2);
			System.out.println(Entity.MS2_METADATA);
			System.out.println(Entity.RECURRENCES);
			System.out.println(Entity.REQUESTS);
			System.out.println(Entity.SCHEDULER);
			System.out.println(Timezones.Central);
			System.out.println(Timezones.Dubai);
			System.out.println(Timezones.Eastern);
			System.out.println(Timezones.GMT);
			System.out.println(Timezones.HK);
			System.out.println(Timezones.Pacific);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@SuppressWarnings("unused")
	private void createListOfLinks(int i, String tagName, String id, String linkText) throws Exception {
		try {
			for (int j = 0; j < i; j++) {
				String html = "<a href=http://www.google.com id=" + id + " > " + linkText + j + "</a>";
				((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('" + html + "');");
			}
		} catch (Exception ex) {
			throw ex;
		}
	}

	@SuppressWarnings("unused")
	private void makeJQueryActive() throws Exception {
		try {
			String string = "var jq = document.createElement('script'); jq.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js'; document.getElementsByTagName('head')[0].appendChild(jq);";
			((JavascriptExecutor) LocalDriver.getDriver()).executeScript(string);
		} catch (Exception ex) {
			throw ex;
		}
	}

}
