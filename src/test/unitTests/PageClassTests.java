package unitTests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import commonClasses.sharedUtils.*;
import junit.framework.AssertionFailedError;
import reporting.framework.reporting.*;
import reporting.framework.utilities.FrameworkException;
import seleniumHelper.seleniumHelper.SeleniumHelper;
import testPages.TestPage;

public class PageClassTests {
	
	private static MsTESTReport msTestReport = new MsTESTReport();
	protected static commonClasses.sharedUtils.HtmlReport htmlReport = new commonClasses.sharedUtils.HtmlReport(new ReportSettings("NA", "NOPE"), new ReportTheme());
	private WebDriver driver;
	private Validations validations = new Validations(htmlReport, msTestReport);
	private commonClasses.sharedUtils.Report report = new commonClasses.sharedUtils.Report(htmlReport, msTestReport);
	public static String value;
	SeleniumHelper sHelp;
	
	@Before
	public void before()
	{
		sHelp = new SeleniumHelper();
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/ExternalLibraries/chromedriver");
        driver = new ChromeDriver();
		driver.get("http://www.google.com");
		sHelp.browser = driver;
	}

	@Test
	public void randomString_enteredLengthSameAsStringLength() throws FrameworkException {
		
		TestPage page = new TestPage(driver, report, validations);
		
		String test = page.someRandomString(5, "Hello");
		page.enums();
		
		assertEquals("These numbers don't equal each other", 5, test.length());
		assertNotEquals("The length matches", 7, test.length());
	}
	
	@Test
	public void randomString_enteredLengthLongerThanStringLength() throws FrameworkException {
		
		TestPage page = new TestPage(driver, report, validations);
		
		String value = "Test";
		String test = page.someRandomString(50, value);
		
		assertEquals("The length does not match", 50, test.length());
		assertNotEquals("The length matches", test.length(), value);
	}
	
	@Test
	public void randomString_enteredLengthShorterThanStringLength() throws FrameworkException {
		
		TestPage page = new TestPage(driver, report, validations);
		
		String value = "ReallyLongTestStringForAGoodReason";
		String test = page.someRandomString(2, value);
		
		assertEquals("The length does not match", 2, test.length());
		assertNotEquals("The length matches", test.length(), value);
	}
	
	@Test
	public void randomString_CorrectLetters() throws FrameworkException {
		
		TestPage page = new TestPage(driver, report, validations);
		
		String test = page.someRandomString(7, "Testing");
		
		String[] charactersNotInString = { "a", "b", "c", "d", "f", "h", "j", "k", "l", "m", "o", "p", "q", "r", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4",
											"5", "6", "7", "8", "9", "0", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "-", "+", "=", "{", "}", "[", "]",
											";", ":", "'", "<", ">", ".", ",", "?", "/", "|", "~", "`" };
		verifyCharactersNotInString(charactersNotInString, test);
		
	}
	
	@Test
	public void randomString_Integers() throws FrameworkException {
		
		TestPage page = new TestPage(driver, report, validations);
		
		String test = page.someRandomString(9, "123456789");
		
		assertEquals("These numbers don't equal each other", 9, test.length());
		
		String[] charactersNotInString = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", 
									       "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "-", "+", "=", "{", "}", "[", "]",
										   ";", ":", "'", "<", ">", ".", ",", "?", "/", "|", "~", "`" };
		verifyCharactersNotInString(charactersNotInString, test);
	}
	
	@Test
	public void randomString_SpecialCharacters() throws FrameworkException {
		
		TestPage page = new TestPage(driver, report, validations);
		
		String test = page.someRandomString(9, "!@#$%^&*()_+-=~`{[}]|/:;'<,>.?");
		
		assertEquals("These numbers don't equal each other", 9, test.length());
		
		String[] charactersNotInString = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
										   "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",};
		verifyCharactersNotInString(charactersNotInString, test);
	}
	
	@Test
	public void randomString_NullString() throws FrameworkException {

		try
		{	
			TestPage page = new TestPage(driver, report, validations);
			String value = null;
			page.someRandomString(9, value);
			fail("Expected a NumberFormatException to be thrown");
		}
		catch (AssertionFailedError ex)
		{
			String m = ex.toString();
			assertTrue(m.contains("junit.framework.AssertionFailedError: StepName: randomString"));
		}
	}
	
	@Test
	public void verifyTheExpectedValueContainsTheActualValue() throws FrameworkException, IOException
	{
		TestPage page = new TestPage(driver, report, validations);
		
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
		
		page.testActualVersusExpected("Test", "Test", "Test");
		
	    System.out.flush();
	    System.setOut(old);
	    String inputString = baos.toString();
			
		assertEquals("Step: verifyTheActualValueMatchesTheExpectedValue has passed. Test is set correctly in Test", inputString.trim());
		ps.close();
		baos.close();
	}
	
	@Test
	public void verifyTheExpectedValueNull_contains() throws FrameworkException, IOException
	{
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
		try
		{
			TestPage page = new TestPage(driver, report, validations);
	
		    System.setOut(ps);
			
			page.testActualVersusExpected("Test", "Test", "Test");
		
			page.testActualVersusExpected(null, "Test", "Test");
			fail("Expected an AssertionFailedError to be thrown");
		}
		catch (AssertionError ex)
		{
			 System.out.flush();
			 System.setOut(old);
			 String inputString = baos.toString();
			 
			assertTrue("Console Message does not contain the correct value.", inputString.trim().contains("junit.framework.AssertionFailedError: StepName: verifyTheActualValueMatchesTheExpectedValue"));
			assertTrue("Console Message does not contain the correct value.", inputString.trim().contains("ErrorMessage : Test returned null. Variable was not expected to return null."));
			ps.close();
			baos.close();
			    
			String m = ex.toString();
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("junit.framework.AssertionFailedError: StepName: verifyTheActualApiValueMatchesTheExpectedApiValue"));
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("ErrorMessage : Test returned null. Variable was not expected to return null."));
		}
	}
	
	@Test
	public void verifyTheActualValueNull_contains() throws FrameworkException, IOException
	{
		try
		{
			TestPage page = new TestPage(driver, report, validations);
		
			page.testActualVersusExpected("Test", null, "Test");
			fail("Expected an AssertionFailedError to be thrown");
		}
		catch (AssertionError ex)
		{
			String m = ex.toString();
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("junit.framework.AssertionFailedError: StepName: verifyTheActualApiValueMatchesTheExpectedApiValue"));
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("ErrorMessage : Test returned null in the actual variable that was set. Check the test to verify all variables are being assigned a value appropriately."));
		}
	}
	
	@Test
	public void verifyTheActualValueDoesntContainExpectedValue() throws FrameworkException, IOException
	{
		try
		{
			TestPage page = new TestPage(driver, report, validations);
		
			page.testActualVersusExpected("Test", "Nothing", "Test");
			fail("Expected an AssertionFailedError to be thrown");
		}
		catch (AssertionError ex)
		{
			String m = ex.toString();
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("junit.framework.AssertionFailedError: StepName: verifyTheActualApiValueMatchesTheExpectedApiValue"));
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("ErrorMessage : Test is not set as expected. Nothing is set instead."));
		}
	}
	
	@Test
	public void verifySubtractDays_DayStartsWith0() throws FrameworkException
	{
		TestPage page = new TestPage(driver, report, validations);
		
		String actualDate = page.testSubtractDays("05-JAN-2017", 1, 1);
		
		assertEquals("These dates don't equal each other", "4-Jan-2017", actualDate);
	}
	
	@Test
	public void verifySubtractDays_DayStartsWith1() throws FrameworkException
	{
		TestPage page = new TestPage(driver, report, validations);
		
		String actualDate = page.testSubtractDays("15-JAN-2017", 1, 1);
		
		assertEquals("These dates don't equal each other", "14-Jan-2017", actualDate);
	}
	
	@Test
	public void verifySubtractDays_IncorrectDateFormat() throws FrameworkException
	{
		try
		{
			TestPage page = new TestPage(driver, report, validations);
		
			String actualDate = page.testSubtractDays("123456789", 1, 1);
		
			fail("Expected AssertionError exception to be thrown.");
		}
		catch (AssertionError ex)
		{
			String m = ex.toString();
			assertTrue("The expected message was not available in the exception thrown", m.trim().contains("junit.framework.AssertionFailedError: StepName: addDays"));
			assertTrue("The expected message was not available in the exception thrown", m.trim().contains("ErrorMessage : Unparseable date:"));
		}
	}
	
	@Test
	public void verifyCleanUp() throws FrameworkException
	{
			TestPage page = new TestPage(driver, report, validations);
			value = "SomeTestValue";
		
			page.testCleanUp(value);
		
			assertTrue(!ExtensionMethods.isNullOrBlank(value));
			
			value = null;
			page.testCleanUp(null);
			
			assertTrue(ExtensionMethods.isNullOrBlank(value));
	}
	
	@Test
	public void verifyTheExpectedValueMatchesTheActualValue() throws FrameworkException, IOException
	{
		TestPage page = new TestPage(driver, report, validations);
		
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
		
		page.testActualVersusExpectedExact("Test", "Test", "Test");
		
	    System.out.flush();
	    System.setOut(old);
	    String inputString = baos.toString();
			
		assertEquals("Step: verifyTheActualValueMatchesTheExpectedValue has passed. Test is set correctly in Test", inputString.trim());
		ps.close();
		baos.close();
	}
	
	@Test
	public void verifyTheExpectedValueNull_exactMatch() throws FrameworkException, IOException
	{
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
		try
		{
			TestPage page = new TestPage(driver, report, validations);
	
		    System.setOut(ps);
			
			page.testActualVersusExpectedExact("Test", "Test", "Test");
		
			page.testActualVersusExpected(null, "Test", "Test");
			fail("Expected an AssertionFailedError to be thrown");
		}
		catch (AssertionError ex)
		{
			 System.out.flush();
			 System.setOut(old);
			 String inputString = baos.toString();
			 
			assertTrue("Console Message does not contain the correct value.", inputString.trim().contains("junit.framework.AssertionFailedError: StepName: verifyTheActualValueMatchesTheExpectedValue"));
			assertTrue("Console Message does not contain the correct value.", inputString.trim().contains("ErrorMessage : Test returned null. Variable was not expected to return null."));
			ps.close();
			baos.close();
			    
			String m = ex.toString();
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("junit.framework.AssertionFailedError: StepName: verifyTheActualApiValueMatchesTheExpectedApiValue"));
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("ErrorMessage : Test returned null. Variable was not expected to return null."));
		}
	}
	
	@Test
	public void verifyTheActualValueNull_exactMatch() throws FrameworkException, IOException
	{
		try
		{
			TestPage page = new TestPage(driver, report, validations);
		
			page.testActualVersusExpectedExact("Test", null, "Test");
			fail("Expected an AssertionFailedError to be thrown");
		}
		catch (AssertionError ex)
		{
			String m = ex.toString();
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("junit.framework.AssertionFailedError: StepName: verifyTheActualApiValueMatchesTheExpectedApiValue"));
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("ErrorMessage : Test returned null in the actual variable that was set. Check the test to verify all variables are being assigned a value appropriately."));
		}
	}
	
	@Test
	public void verifyTheActualValueDoesntMatchExpectedValue() throws FrameworkException, IOException
	{
		try
		{
			TestPage page = new TestPage(driver, report, validations);
		
			page.testActualVersusExpectedExact("Test", "Nothing", "Test");
			fail("Expected an AssertionFailedError to be thrown");
		}
		catch (AssertionError ex)
		{
			String m = ex.toString();
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("junit.framework.AssertionFailedError: StepName: verifyTheActualApiValueMatchesTheExpectedApiValue"));
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("ErrorMessage : Test is not set as expected. Nothing is set instead."));
		}
	}
	
	@Test
	public void verifyRemovingCharacters_containsCharacter() throws FrameworkException
	{
		TestPage page = new TestPage(driver, report, validations);
		
		String actualValue = page.testRemoveOrChangeUnwantedCharacter("Test!", "!", ".");
		
		assertEquals("The values do not match", "Test.", actualValue);
	}
	
	@Test
	public void verifyRemovingCharacters_doesntContainCharacter() throws FrameworkException
	{
		TestPage page = new TestPage(driver, report, validations);
		
		String actualValue = page.testRemoveOrChangeUnwantedCharacter("Test", "!", ".");
		
		assertEquals("The values do not match", "Test", actualValue);
	}
	
	@Test
	public void verifyRemovingCharacters_nullException() throws FrameworkException
	{
		try
		{
			TestPage page = new TestPage(driver, report, validations);
		
			String actualValue = page.testRemoveOrChangeUnwantedCharacter(null, "!", ".");
			fail("Test expected to throw exception");
		}
		catch (AssertionError ex)
		{
			String m = ex.toString();
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("junit.framework.AssertionFailedError: StepName: removeUnwantedCharacter"));
		}
	}
	
	@Test
	public void failTestIfVariableIsNull_nullVariable() throws FrameworkException
	{
		try
		{
			TestPage page = new TestPage(driver, report, validations);
			
			page.testFailIfNull(null, "Passed", "Failed");;
			fail("Test expected to throw exception");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("junit.framework.AssertionFailedError: StepName: failTestIfVariableIsNull"));
		}
	}
	
	@Test
	public void failTestIfVariableIsNull_notNullVariable() throws FrameworkException, IOException
	{
			TestPage page = new TestPage(driver, report, validations);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    PrintStream ps = new PrintStream(baos);
		    PrintStream old = System.out;
		    System.setOut(ps);
			
		    page.testFailIfNull("var", "Passed", "Failed");
			
		    System.out.flush();
		    System.setOut(old);
		    String inputString = baos.toString();
				
			assertEquals("Step: failTestIfVariableIsNull has passed. Passed", inputString.trim());
			ps.close();
			baos.close();
		
	}
/*	
	@Test
	public void verifySetActualStartTime() throws FrameworkException
	{
		TestPage page = new TestPage(driver, report, validations);
		
		page.testSetActualStartTime(true);
		
		assertEquals("The two times do not match", TestUtils.GetCurrentDateTime("HH:mm"), Generic.startTime);
		
		page.testSetActualStartTime(false);
		
		assertTrue("Start time contains a value", ExtensionMethods.isNullOrBlank(Generic.startTime));
	}*/
	
/*	@Test
	public void verifyGetDateInformationAndSetScheduledRecordingDate_DateHasZero()
	{
		
	}
	
	@Test
	public void verifyGetDateInformationAndSetScheduledRecordingDate_DateDoesntHaveZero()
	{
		
	}*/
	
/*	@Test
	public void verifyGetActualStartTime() throws FrameworkException
	{
		TestPage page = new TestPage(driver, report, validations);
		
		String value = page.testGetActualStartTime("Test", true);
		
		assertTrue("Start time contains a value", ExtensionMethods.isNullOrBlank(value));
		
		value = page.testGetActualStartTime("Test", false);
		
		assertEquals("The two times do not match", "Test", value);
	}*/
	
	@Test
	public void verifyGetCurrentSplitDate() throws FrameworkException
	{
		TestPage page = new TestPage(driver, report, validations);
		
		String[] value = page.testGetCurrentSplitDate();
		
		assertTrue("The current date was not retrived in the expected format", TestUtils.GetCurrentDateTime("dd-MM-yyyy").contains(value[0]));
		assertTrue("The current date was not retrived in the expected format", TestUtils.GetCurrentDateTime("dd-MM-yyyy").contains(value[1]));
		assertTrue("The current date was not retrived in the expected format", TestUtils.GetCurrentDateTime("dd-MM-yyyy").contains(value[2]));
	}
	
/*	@Test
	public void verifyActualSlug_ContainsValue() throws FrameworkException
	{
		TestPage page = new TestPage(driver, report, validations);
		
		String value = page.testActualSlug("Test");
		
		assertEquals("The two slug names do not match", "Test", value);
		
		value = page.testActualSlug(null);
		
		assertTrue("Start time contains a value", ExtensionMethods.isNullOrBlank(Generic.cnnId));
	}
	
	@Test
	public void verifySetScheduledRecordingDate_IsStartDateRegularFormat() throws FrameworkException
	{
		TestPage page = new TestPage(driver, report, validations);
		
		String time = TestUtils.getTimeStamp();
		
		page.testSetScheduledRecordingDate(time, true, false);
		
		assertEquals("The two dates do not match", time, Generic.scheduledRecordingStartDate);
	}
	
	@Test
	public void verifySetScheduledRecordingDate_IsStartDateAltFormat() throws FrameworkException
	{
		TestPage page = new TestPage(driver, report, validations);
		
		String time = TestUtils.getTimeStamp();
		
		page.testSetScheduledRecordingDate(time, true, true);
		
		assertEquals("The two dates do not match", time, Generic.scheduledRecordingStartDateAlt);
	}
	
	@Test
	public void verifySetScheduledRecordingDate_IsNotStartDateAltFormat() throws FrameworkException
	{
		TestPage page = new TestPage(driver, report, validations);
		
		String time = TestUtils.getTimeStamp();
		
		page.testSetScheduledRecordingDate(time, false, true);
		
		assertEquals("The two dates do not match", time, Generic.scheduledRecordingEndDateAlt);
	}
	
	@Test
	public void verifySetScheduledRecordingDate_IsNotStartDateRegularFormat() throws FrameworkException
	{
		TestPage page = new TestPage(driver, report, validations);
		
		String time = TestUtils.getTimeStamp();
		
		page.testSetScheduledRecordingDate(time, false, false);
		
		assertEquals("The two dates do not match", time, Generic.scheduledRecordingEndDate);
	}
	*/
	@Test
	public void verifyGetFutureDate() throws FrameworkException
	{
		TestPage page = new TestPage(driver, report, validations);
		
		String day = TestUtils.GetCurrentDateTime("dd");
		
		int value = page.testGetFutureDate(1);
		
		assertEquals("The two dates do not match", value, Integer.parseInt(day) + 1);
	}
	
/*	@Test
	public void verifyNavigateViaNewsAppsToggle() throws FrameworkException, IOException
	{
		TestPage page = new TestPage(driver, report, validations);
		((JavascriptExecutor)driver).executeScript("document.write('<input id=Test > </input>');");
		Generic.newsAppsToggle = "input[id='Test']";
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    
		page.testNavigateViaNewsappsToggle("Test", "id", "TestElement");
		
	    System.out.flush();
	    System.setOut(old);
	    String inputString = baos.toString();
	    
	    assertTrue("Console messages do not match", inputString.trim().contains("Step: clickSomeElement has passed. NewsApps Toggle clicked successfully."));
	    assertTrue("Console messages do not match", inputString.trim().contains("Step: clickSomeElement has passed. TestElement clicked successfully."));
	
	    ps.close();
		baos.close();
	}*/
	
/*	@Test
	public void verifyNavigateViaNewsAppsToggle_cantFindElement() throws FrameworkException, IOException
	{
		try
		{
			TestPage page = new TestPage(driver, report, validations);
			((JavascriptExecutor)driver).executeScript("document.write('<input id=Test > </input>');");
			Generic.newsAppsToggle = "input[id='Test']";
		
			page.testNavigateViaNewsappsToggle("OneTwoThree", "id", "TestElement");
			fail("Error expected to be thrown");
		}
		catch (AssertionError ex)
		{
			String m = ex.toString();
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("junit.framework.AssertionFailedError: StepName: navigateViaTheNewsAppsToggle"));
		}
	}*/
	
	@Test
	public void verifyEnterAValueIntoATextField() throws FrameworkException, IOException, InterruptedException
	{
		TestPage page = new TestPage(driver, report, validations);
		Thread.sleep(800);
		((JavascriptExecutor)driver).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		String test = "input[id='Test']";
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    
		page.testEnterAValueIntoATextField("Test123", test, "cssSelector", "Test Element");;
		
	    String inputString = getByteStreamMessage(baos, old);
	    
	    assertTrue("Console messages do not match", inputString.trim().contains("Step: enterAValueInATextField has passed. Test Element has been entered successfully"));
	
	    closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyEnterAValueIntoATextField_NullValue() throws FrameworkException, IOException, InterruptedException
	{	
		TestPage page = new TestPage(driver, report, validations);
		Thread.sleep(800);
		((JavascriptExecutor)driver).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		String test = "input[id='Test']";
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testEnterAValueIntoATextField(null, test, "cssSelector", "Test Element");;
	
		String inputString = getByteStreamMessage(baos, old);
    
		assertTrue("Console messages do not match", inputString.trim().contains("Step: enterAValueInATextField has passed. Test Element has been entered successfully"));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyEnterAValueIntoATextField_ElementNotFound() throws FrameworkException, IOException, InterruptedException
	{
	    try
		{
			TestPage page = new TestPage(driver, report, validations);
			
			page.testEnterAValueIntoATextField(null, "input[id='Test']", "cssSelector", "Test Element");
			fail("Error expected to be thrown");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("junit.framework.AssertionFailedError: StepName: enterAValueInATextField"));
		}
	}
	
	
	@Test
	public void verifyEnterAValueIntoATextField_ElementDefined() throws FrameworkException, IOException, InterruptedException
	{
		TestPage page = new TestPage(driver, report, validations);
		Thread.sleep(800);
		((JavascriptExecutor)driver).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		String test = "Test";
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    WebElement el = sHelp.getElement(test, "id");
	    
		page.testEnterAValueIntoATextField("Test123", el, "Test Element");;
		
	    String inputString = getByteStreamMessage(baos, old);
	    
	    assertTrue("Console messages do not match", inputString.trim().contains("Step: enterAValueInATextField has passed. Test Element has been entered successfully"));
	
	    closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyEnterAValueIntoATextField_NullValue_ElementDefined() throws FrameworkException, IOException, InterruptedException
	{	
		TestPage page = new TestPage(driver, report, validations);
		Thread.sleep(800);
		((JavascriptExecutor)driver).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		String test = "input[id='Test']";
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testEnterAValueIntoATextField(null, sHelp.getElement(test, "cssSelector"), "Test Element");;
	
		String inputString = getByteStreamMessage(baos, old);
    
		assertTrue("Console messages do not match", inputString.trim().contains("Step: enterAValueInATextField has passed. Test Element has been entered successfully"));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyEnterAValueIntoATextField_ElementNotFound_ElementDefined() throws FrameworkException, IOException, InterruptedException
	{
	    try
		{
			TestPage page = new TestPage(driver, report, validations);
			
			page.testEnterAValueIntoATextField(null, sHelp.getElement("input[id='Test']", "cssSelector"), "Test Element");
			fail("Error expected to be thrown");
		}
		catch (NoSuchElementException ex)
		{
			String m = ex.toString();
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element:"));
		}
	}
	
	@Test
	public void verifyFindOptionInLitAndSelectIt_OptionAvailable_NoJquery() throws FrameworkException, IOException
	{
		TestPage page = new TestPage(driver, report, validations);
		createListOfLinks(7, "a", "testLink", "Link");
		String test = "testLink";
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		List<WebElement> list = sHelp.getElements(test, "id");
    
		page.testFindOptionInListAndSelectIt(list, null, "Link3", false);
	
		String inputString = getByteStreamMessage(baos, old);
    
		assertTrue("Console messages do not match", inputString.trim().contains("Step: findOptionInListAndSelectIt has passed. Link3 has been selected successfully."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyFindOptionInLitAndSelectIt_NoOptionsAvailable_NoJquery() throws FrameworkException, IOException
	{
		try
		{
			TestPage page = new TestPage(driver, report, validations);
			createListOfLinks(7, "a", "testLink", "Link");
			String test = "testLink";
	
			List<WebElement> list = sHelp.getElements(test, "id");
    
			page.testFindOptionInListAndSelectIt(list, null, "Link10", false);
			fail("Exception was expected to have been thrown");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("junit.framework.AssertionFailedError: StepName: findOptionInListAndSelectIt"));
		}
	
	}
	
	@Test
	public void verifyFindOptionInLitAndSelectIt_OptionAvailable_Jquery() throws FrameworkException, IOException
	{
		TestPage page = new TestPage(driver, report, validations);
		createListOfLinks(7, "a", "testLink", "Link");
		makeJQueryActive();
		String test = "a[id='testLink']";
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		List<WebElement> list = sHelp.getElements(test, "cssSelector");
    
		page.testFindOptionInListAndSelectIt(list, test, "Link3", true);
	
		String inputString = getByteStreamMessage(baos, old);
    
		assertTrue("Console messages do not match", inputString.trim().contains("Step: findOptionInListAndSelectIt has passed. Link3 has been selected successfully."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyClickSomeElement_ElementFound() throws InterruptedException, FrameworkException, IOException
	{
		TestPage page = new TestPage(driver, report, validations);
		((JavascriptExecutor)driver).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		String test = "input[id='Test']";
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testClickSomeElement(test, "cssSelector", "Test Element");;
	
		String inputString = getByteStreamMessage(baos, old);
    
		assertTrue("Console messages do not match", inputString.trim().contains("Step: clickSomeElement has passed. Test Element clicked successfully."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyClickSomeElement_ElementNotFound() throws InterruptedException, FrameworkException
	{
		try
		{
			TestPage page = new TestPage(driver, report, validations);
			((JavascriptExecutor)driver).executeScript("document.write('<input id=Test > </input>');");
			Thread.sleep(500);
			String test = "input[id='NotHere']";
	    
			page.testClickSomeElement(test, "cssSelector", "Test Element");
			fail("Exception was expected to be thrown for a failed test case");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			assertTrue("Error Message does not contain the correct value.", m.trim().contains("junit.framework.AssertionFailedError: StepName: clickSomeElement"));
		}
	}
	
	@Test
	public void getTotalDaysInMonth() throws FrameworkException
	{
		TestPage page = new TestPage(driver, report, validations);
		
		int totalDays = page.testGetTotalDaysInMonth();
		String month = TestUtils.GetCurrentDateTime("MMM");
		int expectedTotal = getTotalExpectedMonths(month);
		
		assertEquals("The total days in month do not match", expectedTotal, totalDays);
	}
	
	private void createListOfLinks(int i, String tagName, String id, String linkText)
	{
		try
		{
			for (int j = 0; j < i; j++)
			{
				String html = "<a href=\"http://www.google.com\" id=\"" +id +"\" > " + linkText + j + "</a>";
				((JavascriptExecutor)driver).executeScript("document.write('" + html + "');");
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
	
	private void makeJQueryActive()
	{
		try
		{
			((JavascriptExecutor)driver).executeScript("var jq = document.createElement('script'); jq.src = \"https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js\"; document.getElementsByTagName('head')[0].appendChild(jq);");
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
	
	private void verifyCharactersNotInString(String[] characters, String value)
	{
		try
		{
			for(int i = 0; i < characters.length; i++)
			{
				assertFalse(characters[i] + " was found in the generated string", value.toLowerCase().trim().contains(characters[i].toLowerCase().trim()));
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
	
	private int getTotalExpectedMonths(String month)
	{
		int totalDays = 0;
		switch(month.toUpperCase())
		{
			case "JAN":
				totalDays = 31;
			case "FEB":
				totalDays = 28;
			case "MAR":
				totalDays = 31;
			case "APR":
				totalDays = 30;
			case "MAY":
				totalDays = 31;
			case "JUN":
				totalDays = 30;
			case "JUL":
				totalDays = 31;
			case "AUG":
				totalDays = 31;
			case "SEP":
				totalDays = 30;
			case "OCT":
				totalDays = 31;
			case "NOV":
				totalDays = 30;
			case "DEC":
				totalDays = 31;
		}
		return totalDays;
	}
	
	
	private String getByteStreamMessage(ByteArrayOutputStream baos, PrintStream old)
	{
		System.out.flush();
	    System.setOut(old);
	    String inputString = baos.toString();
	    return inputString;
	}
	
	private void closeByteStream(PrintStream ps, ByteArrayOutputStream baos) throws IOException
	{
		ps.close();
		baos.close();
	}
	
	@After
	public void after()
	{
		try
		{
			driver.quit();
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			driver.quit();
		}
	}


}
