package unitTests;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonClasses.sharedUtils.*;
import reporting.framework.reporting.*;
import reporting.framework.utilities.FrameworkException;
import seleniumHelper.seleniumHelper.SeleniumHelper;
import testPages.TestPage;

@Listeners(WebDriverListener.class)
public class PageClassTests {
	
	private static MsTESTReport msTestReport = new MsTESTReport();
	protected static commonClasses.sharedUtils.HtmlReport htmlReport = new commonClasses.sharedUtils.HtmlReport(new ReportSettings("NA", "NOPE"), new ReportTheme());
	//private WebDriver browser;
	private Validations validations = new Validations(htmlReport, msTestReport);
	private commonClasses.sharedUtils.Report report = new commonClasses.sharedUtils.Report(htmlReport, msTestReport);
	public static String value;
	SeleniumHelper sHelp;

	@BeforeMethod
	public void beforeScenario()
	{
		sHelp = new SeleniumHelper();
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/ExternalLibraries/chromedriver");
        LocalDriverManager.getDriver().get("http://www.google.com");;
	}

	@Test
	public void randomString_enteredLengthSameAsStringLength() throws Exception {
		
		TestPage page = new TestPage();
		
		String test = page.someRandomString(5, "Hello");
		page.enums();
		
		Assert.assertEquals(5, test.length());
		Assert.assertNotEquals(7, test.length());
	}
	
	@Test
	public void randomString_enteredLengthLongerThanStringLength() throws Exception {
		
		TestPage page = new TestPage();
		
		String value = "Test";
		String test = page.someRandomString(50, value);
		
		Assert.assertEquals(50, test.length());
		Assert.assertNotEquals(test.length(), value);
	}
	
	@Test
	public void randomString_enteredLengthShorterThanStringLength() throws Exception {
		
		TestPage page = new TestPage();
		
		String value = "ReallyLongTestStringForAGoodReason";
		String test = page.someRandomString(2, value);
		
		Assert.assertEquals(2, test.length());
		Assert.assertNotEquals(test.length(), value);
	}
	
	@Test
	public void randomString_CorrectLetters() throws Exception {
		
		TestPage page = new TestPage();
		
		String test = page.someRandomString(7, "Testing");
		
		String[] charactersNotInString = { "a", "b", "c", "d", "f", "h", "j", "k", "l", "m", "o", "p", "q", "r", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4",
											"5", "6", "7", "8", "9", "0", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "-", "+", "=", "{", "}", "[", "]",
											";", ":", "'", "<", ">", ".", ",", "?", "/", "|", "~", "`" };
		verifyCharactersNotInString(charactersNotInString, test);
		
	}
	
	@Test
	public void randomString_Integers() throws Exception {
		
		TestPage page = new TestPage();
		
		String test = page.someRandomString(9, "123456789");
		
		Assert.assertEquals(9, test.length());
		
		String[] charactersNotInString = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", 
									       "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "-", "+", "=", "{", "}", "[", "]",
										   ";", ":", "'", "<", ">", ".", ",", "?", "/", "|", "~", "`" };
		verifyCharactersNotInString(charactersNotInString, test);
	}
	
	@Test
	public void randomString_SpecialCharacters() throws Exception {
		
		TestPage page = new TestPage();
		
		String test = page.someRandomString(9, "!@#$%^&*()_+-=~`{[}]|/:;'<,>.?");
		
		Assert.assertEquals(9, test.length());
		
		String[] charactersNotInString = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
										   "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",};
		verifyCharactersNotInString(charactersNotInString, test);
	}
	
	@Test
	public void randomString_NullString() throws Exception {

		try
		{	
			TestPage page = new TestPage();
			String value = null;
			page.someRandomString(9, value);
			Assert.fail("Expected a NumberFormatException to be thrown");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.contains("reporting.framework.utilities.FrameworkException: StepName: randomString"));
		}
	}
	
	@Test
	public void verifyTheExpectedValueContainsTheActualValue() throws Exception
	{
		TestPage page = new TestPage();
		
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
		
		page.testActualVersusExpected("Test", "Test", "Test");
		
	    System.out.flush();
	    System.setOut(old);
	    String inputString = baos.toString();
			
		Assert.assertEquals("Step: verifyTheActualValueContainsTheExpectedValue has passed. Test is set correctly in Test", inputString.trim());
		ps.close();
		baos.close();
	}
	
	@Test
	public void verifyTheExpectedValueNull_contains() throws Exception
	{
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
		try
		{
			TestPage page = new TestPage();
	
		    System.setOut(ps);
			
			page.testActualVersusExpected("Test", "Test", "Test");
		
			page.testActualVersusExpected(null, "Test", "Test");
			Assert.fail("Expected an AssertionFailedError to be thrown");
		}
		catch (FrameworkException ex)
		{
			 System.out.flush();
			 System.setOut(old);
			 String inputString = baos.toString();
			 
			Assert.assertTrue(inputString.trim().contains("Step: verifyTheActualValueContainsTheExpectedValue has passed. Test is set correctly in Test"));
			Assert.assertTrue(inputString.trim().contains("ErrorMessage : Test returned null. Variable was not expected to return null."));
			ps.close();
			baos.close();
			    
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: verifyTheActualValueContainsTheExpectedValue"));
			Assert.assertTrue(m.trim().contains("ErrorMessage : Test returned null. Variable was not expected to return null."));
		}
	}
	
	@Test
	public void verifyTheActualValueNull_contains() throws Exception
	{
		try
		{
			TestPage page = new TestPage();
		
			page.testActualVersusExpected("Test", null, "Test");
			Assert.fail("Expected an AssertionFailedError to be thrown");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: verifyTheActualValueContainsTheExpectedValue"));
			Assert.assertTrue(m.trim().contains("ErrorMessage : Test returned null in the actual variable that was set. Check the test to verify all variables are being assigned a value appropriately."));
		}
	}
	
	@Test
	public void verifyTheActualValueDoesntContainExpectedValue() throws Exception
	{
		try
		{
			TestPage page = new TestPage();
		
			page.testActualVersusExpected("Test", "Nothing", "Test");
			Assert.fail("Expected an AssertionFailedError to be thrown");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: verifyTheActualValueContainsTheExpectedValue"));
			Assert.assertTrue(m.trim().contains("ErrorMessage : Test is not set as expected. Nothing is set instead."));
		}
	}
	
	@Test
	public void verifySubtractDays_DayStartsWith0() throws FrameworkException
	{
		TestPage page = new TestPage();
		
		String actualDate = page.testSubtractDays("05-JAN-2017", 1, 1);
		
		Assert.assertEquals("4-Jan-2017", actualDate);
	}
	
	@Test
	public void verifySubtractDays_DayStartsWith1() throws FrameworkException
	{
		TestPage page = new TestPage();
		
		String actualDate = page.testSubtractDays("15-JAN-2017", 1, 1);
		
		Assert.assertEquals("14-Jan-2017", actualDate);
	}
	
	@Test
	public void verifySubtractDays_IncorrectDateFormat() throws FrameworkException
	{
		try
		{
			TestPage page = new TestPage();
		
			String actualDate = page.testSubtractDays("123456789", 1, 1);
		
			Assert.fail("Expected AssertionError exception to be thrown." + actualDate);
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: subtractDays"));
			Assert.assertTrue(m.trim().contains("ErrorMessage : Unparseable date:"));
		}
	}
	
	@Test
	public void verifyCleanUp() throws FrameworkException
	{
			TestPage page = new TestPage();
			value = "SomeTestValue";
		
			page.testCleanUp(value);
		
			Assert.assertTrue(!ExtensionMethods.isNullOrBlank(value));
			
			value = null;
			page.testCleanUp(null);
			
			Assert.assertTrue(ExtensionMethods.isNullOrBlank(value));
	}
	
	@Test
	public void verifyTheExpectedValueMatchesTheActualValue() throws FrameworkException, IOException
	{
		TestPage page = new TestPage();
		
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
		
		page.testActualVersusExpectedExact("Test", "Test", "Test");
		
	    System.out.flush();
	    System.setOut(old);
	    String inputString = baos.toString();
			
		Assert.assertEquals("Step: verifyTheActualValueMatchesTheExpectedValue has passed. Test is set correctly in Test", inputString.trim());
		ps.close();
		baos.close();
	}
	
	@Test
	public void verifyTheExpectedValueNull_exactMatch() throws Exception
	{
		try
		{
			TestPage page = new TestPage();
			
			page.testActualVersusExpectedExact(null, "Test", "Test");
			Assert.fail("Expected an AssertionFailedError to be thrown");
		}
		catch (FrameworkException ex)
		{			    
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: verifyTheActualValueMatchesTheExpectedValue"));
			Assert.assertTrue(m.trim().contains("ErrorMessage : Test returned null in either the actual or the expected variable that was set. Check the test to verify all variables are being assigned a value appropriately. Actual: Test. Expected: null"));
		}
	}
	
	@Test
	public void verifyTheActualValueNull_exactMatch() throws FrameworkException, IOException
	{
		try
		{
			TestPage page = new TestPage();
		
			page.testActualVersusExpectedExact("Test", null, "Test");
			Assert.fail("Expected an AssertionFailedError to be thrown");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: verifyTheActualValueMatchesTheExpectedValue"));
			Assert.assertTrue(m.trim().contains("ErrorMessage : Test returned null in either the actual or the expected variable that was set. Check the test to verify all variables are being assigned a value appropriately. Actual: null. Expected: Test"));
		}
	}
	
	@Test
	public void verifyTheActualValueDoesntMatchExpectedValue() throws FrameworkException, IOException
	{
		try
		{
			TestPage page = new TestPage();
		
			page.testActualVersusExpectedExact("Test", "Nothing", "Test");
			Assert.fail("Expected an AssertionFailedError to be thrown");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: verifyTheActualValueMatchesTheExpectedValue"));
			Assert.assertTrue(m.trim().contains("ErrorMessage : Test is not set as expected. Nothing is set instead."));
		}
	}
	
	@Test
	public void verifyRemovingCharacters_containsCharacter() throws FrameworkException
	{
		TestPage page = new TestPage();
		
		String actualValue = page.testRemoveOrChangeUnwantedCharacter("Test!", "!", ".");
		
		Assert.assertEquals("Test.", actualValue);
	}
	
	@Test
	public void verifyRemovingCharacters_doesntContainCharacter() throws FrameworkException
	{
		TestPage page = new TestPage();
		
		String actualValue = page.testRemoveOrChangeUnwantedCharacter("Test", "!", ".");
		
		Assert.assertEquals("Test", actualValue);
	}
	
	@Test
	public void verifyRemovingCharacters_nullException() throws FrameworkException
	{
		try
		{
			TestPage page = new TestPage();
		
			String actualValue = page.testRemoveOrChangeUnwantedCharacter(null, "!", ".");
			Assert.fail("Test expected to throw exception" + actualValue);
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: removeOrChangeUnwantedCharacter"));
		}
	}
	
	@Test
	public void failTestIfVariableIsNull_nullVariable() throws FrameworkException
	{
		try
		{
			TestPage page = new TestPage();
			
			page.testFailIfNull(null, "Passed", "Failed");;
			Assert.fail("Test expected to throw exception");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("junit.framework.AssertionFailedError: StepName: failTestIfVariableIsNull"));
		}
	}
	
	@Test
	public void failTestIfVariableIsNull_notNullVariable() throws FrameworkException, IOException
	{
			TestPage page = new TestPage();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    PrintStream ps = new PrintStream(baos);
		    PrintStream old = System.out;
		    System.setOut(ps);
			
		    page.testFailIfNull("var", "Passed", "Failed");
			
		    System.out.flush();
		    System.setOut(old);
		    String inputString = baos.toString();
				
			Assert.assertEquals("Step: failTestIfVariableIsNull has passed. Passed", inputString.trim());
			ps.close();
			baos.close();
		
	}
/*	
	@Test
	public void verifySetActualStartTime() throws FrameworkException
	{
		TestPage page = new TestPage();
		
		page.testSetActualStartTime(true);
		
		Assert.assertEquals("The two times do not match", TestUtils.GetCurrentDateTime("HH:mm"), Generic.startTime);
		
		page.testSetActualStartTime(false);
		
		Assert.assertTrue("Start time contains a value", ExtensionMethods.isNullOrBlank(Generic.startTime));
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
		TestPage page = new TestPage();
		
		String value = page.testGetActualStartTime("Test", true);
		
		Assert.assertTrue("Start time contains a value", ExtensionMethods.isNullOrBlank(value));
		
		value = page.testGetActualStartTime("Test", false);
		
		Assert.assertEquals("The two times do not match", "Test", value);
	}*/
	
	@Test
	public void verifyGetCurrentSplitDate() throws FrameworkException
	{
		TestPage page = new TestPage();
		
		String[] value = page.testGetCurrentSplitDate();
		
		Assert.assertTrue(TestUtils.GetCurrentDateTime("dd-MM-yyyy").contains(value[0]));
		Assert.assertTrue(TestUtils.GetCurrentDateTime("dd-MM-yyyy").contains(value[1]));
		Assert.assertTrue(TestUtils.GetCurrentDateTime("dd-MM-yyyy").contains(value[2]));
	}
	
/*	@Test
	public void verifyActualSlug_ContainsValue() throws FrameworkException
	{
		TestPage page = new TestPage();
		
		String value = page.testActualSlug("Test");
		
		Assert.assertEquals("The two slug names do not match", "Test", value);
		
		value = page.testActualSlug(null);
		
		Assert.assertTrue("Start time contains a value", ExtensionMethods.isNullOrBlank(Generic.cnnId));
	}
	
	@Test
	public void verifySetScheduledRecordingDate_IsStartDateRegularFormat() throws FrameworkException
	{
		TestPage page = new TestPage();
		
		String time = TestUtils.getTimeStamp();
		
		page.testSetScheduledRecordingDate(time, true, false);
		
		Assert.assertEquals("The two dates do not match", time, Generic.scheduledRecordingStartDate);
	}
	
	@Test
	public void verifySetScheduledRecordingDate_IsStartDateAltFormat() throws FrameworkException
	{
		TestPage page = new TestPage();
		
		String time = TestUtils.getTimeStamp();
		
		page.testSetScheduledRecordingDate(time, true, true);
		
		Assert.assertEquals("The two dates do not match", time, Generic.scheduledRecordingStartDateAlt);
	}
	
	@Test
	public void verifySetScheduledRecordingDate_IsNotStartDateAltFormat() throws FrameworkException
	{
		TestPage page = new TestPage();
		
		String time = TestUtils.getTimeStamp();
		
		page.testSetScheduledRecordingDate(time, false, true);
		
		Assert.assertEquals("The two dates do not match", time, Generic.scheduledRecordingEndDateAlt);
	}
	
	@Test
	public void verifySetScheduledRecordingDate_IsNotStartDateRegularFormat() throws FrameworkException
	{
		TestPage page = new TestPage();
		
		String time = TestUtils.getTimeStamp();
		
		page.testSetScheduledRecordingDate(time, false, false);
		
		Assert.assertEquals("The two dates do not match", time, Generic.scheduledRecordingEndDate);
	}
	*/
	@Test
	public void verifyGetFutureDate() throws FrameworkException
	{
		TestPage page = new TestPage();
		
		String day = TestUtils.GetCurrentDateTime("dd");
		
		int value = page.testGetFutureDate(1);
		
		Assert.assertEquals(value, Integer.parseInt(day) + 1);
	}
	
/*	@Test
	public void verifyNavigateViaNewsAppsToggle() throws FrameworkException, IOException
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test > </input>');");
		Generic.newsAppsToggle = "input[id='Test']";
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    
		page.testNavigateViaNewsappsToggle("Test", "id", "TestElement");
		
	    System.out.flush();
	    System.setOut(old);
	    String inputString = baos.toString();
	    
	    Assert.assertTrue("Console messages do not match", inputString.trim().contains("Step: clickSomeElement has passed. NewsApps Toggle clicked successfully."));
	    Assert.assertTrue("Console messages do not match", inputString.trim().contains("Step: clickSomeElement has passed. TestElement clicked successfully."));
	
	    ps.close();
		baos.close();
	}*/
	
/*	@Test
	public void verifyNavigateViaNewsAppsToggle_cantFindElement() throws FrameworkException, IOException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)browser).executeScript("document.write('<input id=Test > </input>');");
			Generic.newsAppsToggle = "input[id='Test']";
		
			page.testNavigateViaNewsappsToggle("OneTwoThree", "id", "TestElement");
			fail("Error expected to be thrown");
		}
		catch (AssertionError ex)
		{
			String m = ex.toString();
			Assert.assertTrue("Error Message does not contain the correct value.", m.trim().contains("junit.framework.AssertionFailedError: StepName: navigateViaTheNewsAppsToggle"));
		}
	}*/
	
	@Test
	public void verifyEnterAValueIntoATextField() throws FrameworkException, IOException, InterruptedException
	{
		TestPage page = new TestPage();
		Thread.sleep(800);
		((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		String test = "input[id='Test']";
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    
		page.testEnterAValueIntoATextField("Test123", test, "cssSelector", "Test Element");;
		
	    String inputString = getByteStreamMessage(baos, old);
	    
	    Assert.assertTrue(inputString.trim().contains("Step: enterAvalueIntoATextField has passed. Test Element has been entered successfully"));
	
	    closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyEnterAValueIntoATextField_NullValue() throws FrameworkException, IOException, InterruptedException
	{	
		TestPage page = new TestPage();
		Thread.sleep(800);
		((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		String test = "input[id='Test']";
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testEnterAValueIntoATextField(null, test, "cssSelector", "Test Element");;
	
		String inputString = getByteStreamMessage(baos, old);
    
		Assert.assertTrue(inputString.trim().contains("Step: enterAvalueIntoATextField has passed. Test Element has been entered successfully"));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyEnterAValueIntoATextField_ElementNotFound() throws FrameworkException, IOException, InterruptedException
	{
	    try
		{
			TestPage page = new TestPage();
			
			page.testEnterAValueIntoATextField(null, "input[id='Test']", "cssSelector", "Test Element");
			Assert.fail("Error expected to be thrown");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: enterAvalueIntoATextField"));
		}
	}
	
	@Test
	public void verifyEnterAValueIntoATextField_ByValueUndefined()
	{
		try
		{
			TestPage page = new TestPage();
			Thread.sleep(800);
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test > </input>');");
			Thread.sleep(500);
			String test = "input[id='Test']";
			
			page.testEnterAValueIntoATextField("Test", test, null, "Test Element");
			Assert.fail("Error expected to be thrown");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: enterAvalueIntoATextField"));
		}
	}
	
	
	@Test
	public void verifyEnterAValueIntoATextField_ElementDefined() throws FrameworkException, IOException, InterruptedException
	{
		TestPage page = new TestPage();
		Thread.sleep(800);
		((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		String test = "Test";
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    WebElement el = sHelp.getElement(test, "id");
	    
		page.testEnterAValueIntoATextField("Test123", el, "Test Element");;
		
	    String inputString = getByteStreamMessage(baos, old);
	    
	    Assert.assertTrue(inputString.trim().contains("Step: enterAvalueIntoATextField has passed. Test Element has been entered successfully"));
	
	    closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyEnterAValueIntoATextField_NullValue_ElementDefined() throws FrameworkException, IOException, InterruptedException
	{	
		TestPage page = new TestPage();
		Thread.sleep(800);
		((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		String test = "input[id='Test']";
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testEnterAValueIntoATextField(null, sHelp.getElement(test, "cssSelector"), "Test Element");;
	
		String inputString = getByteStreamMessage(baos, old);
    
		Assert.assertTrue(inputString.trim().contains("Step: enterAvalueIntoATextField has passed. Test Element has been entered successfully"));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyEnterAValueIntoATextField_ElementNotFound_ElementDefined() throws FrameworkException, IOException, InterruptedException
	{
	    try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test > </input>');");
			Thread.sleep(500);
			WebElement test = sHelp.getElement("input[id='Test']", "cssSelector");
			LocalDriverManager.getDriver().navigate().refresh();
			Thread.sleep(500);
			
			page.testEnterAValueIntoATextField("Test", test, "Test Element");
			Assert.fail("Error expected to be thrown");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: enterAvalueIntoATextField"));
		}
	}
	
	@Test
	public void verifyFindOptionInLitAndSelectIt_OptionAvailable_NoJquery() throws Exception
	{
		TestPage page = new TestPage();
		createListOfLinks(7, "a", "testLink", "Link");
		String test = "testLink";
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		List<WebElement> list = sHelp.getElements(test, "id");
    
		page.testFindOptionInListAndSelectIt(list, null, "Link3", false);
	
		String inputString = getByteStreamMessage(baos, old);
    
		Assert.assertTrue(inputString.trim().contains("Step: findOptionInListAndSelectIt has passed. Link3 has been selected successfully."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyFindOptionInListAndSelectIt_NoOptionsAvailable_NoJquery() throws Exception
	{
		try
		{
			TestPage page = new TestPage();
			createListOfLinks(7, "a", "testLink", "Link");
			String test = "testLink";
	
			List<WebElement> list = sHelp.getElements(test, "id");
    
			page.testFindOptionInListAndSelectIt(list, null, "Link10", false);
			Assert.fail("Exception was expected to have been thrown");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("junit.framework.AssertionFailedError: StepName: findOptionInListAndSelectIt"));
		}
	
	}
	
	@Test
	public void verifyFindOptionInListAndSelectIt_OptionAvailable_Jquery() throws Exception
	{
		TestPage page = new TestPage();
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
    
		Assert.assertTrue(inputString.trim().contains("Step: findOptionInListAndSelectIt has passed. Link3 has been selected successfully."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyFindOptionInListAndSelectIt_ExceptionThrown() throws Exception
	{
		try
		{
			TestPage page = new TestPage();
			createListOfLinks(7, "a", "testLink", "Link");
			String test = "testLink";
	
			List<WebElement> list = sHelp.getElements(test, "id");
    
			page.testFindOptionInListAndSelectIt(list, test, "Link10", true);
			Assert.fail("Exception was expected to have been thrown");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("junit.framework.AssertionFailedError: StepName: findOptionInListAndSelectIt"));
		}
	}
	
	@Test
	public void verifyClickSomeElement_ElementFound() throws InterruptedException, FrameworkException, IOException
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		String test = "input[id='Test']";
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testClickSomeElement(test, "cssSelector", "Test Element");;
	
		String inputString = getByteStreamMessage(baos, old);
    
		Assert.assertTrue(inputString.trim().contains("Step: clickSomeElement has passed. Test Element clicked successfully."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyClickSomeElement_ElementNotFound() throws InterruptedException, FrameworkException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test > </input>');");
			Thread.sleep(500);
			String test = "input[id='NotHere']";
	    
			page.testClickSomeElement(test, "cssSelector", "Test Element");
			Assert.fail("Exception was expected to be thrown for a failed test case");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("junit.framework.AssertionFailedError: StepName: clickSomeElement"));
		}
	}
	
	@Test
	public void verifyClickSomeElement_ThrowsException() throws InterruptedException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test > </input>');");
			Thread.sleep(500);
			String test = "input[id='NotHere']";
	    
			page.testClickSomeElement(test, null, "Test Element");
			Assert.fail("Exception was expected to be thrown for a failed test case");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: clickSomeElement"));
		}
	}
	
	@Test
	public void getTotalDaysInMonth() throws FrameworkException
	{
		TestPage page = new TestPage();
		
		int totalDays = page.testGetTotalDaysInMonth();
		String month = TestUtils.GetCurrentDateTime("MMM");
		int expectedTotal = getTotalExpectedMonths(month);
		
		Assert.assertEquals(expectedTotal, totalDays);
	}
	
	@Test
	public void verifySelectSomeOptionFromNonDropDown_ElementFound() throws Exception
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		createListOfLinks(7, "a", "testLink", "Link");
		String testLink = "testLink";
		String test = "input[id='Test']";
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testSelectSomeOptionFromNonDropdown("Link2", test, "cssSelector", test, "cssSelector", testLink, "id", "Test Element", false);
	
		String inputString = getByteStreamMessage(baos, old);
    
		Assert.assertTrue(inputString.trim().contains("Step: findOptionInListAndSelectIt has passed. Link2 has been selected successfully."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifySelectSomeOptionFromNonDropDown_ElementNotFound() throws Exception
	{
		try
		{
			TestPage page = new TestPage();
			createListOfLinks(7, "a", "testLink", "Link");
			String testLink = "testLink";
			String test = "input[id='Test']";
    
			page.testSelectSomeOptionFromNonDropdown("Link2", test, "cssSelector", test, "cssSelector", testLink, "id", "Test Element", false);
			Assert.fail("Test was supposed to throw an error for element not being found");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("junit.framework.AssertionFailedError: StepName: selectSomeOptionFromNonDropdown"));
		}
	}
	
	@Test
	public void verifySelectSomeOptionFromNonDropDown_ThrowsException() throws Exception
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test > </input>');");
			Thread.sleep(500);
			createListOfLinks(7, "a", "testLink", "Link");
			String testLink = "testLink";
			String test = "input[id='Test']";
    
			page.testSelectSomeOptionFromNonDropdown("Link2", test, "cssSelector", test, "cssSelector", testLink, null, "Test Element", false);
			Assert.fail("Test was supposed to throw an error for element not being found");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: selectSomeOptionFromNonDropdown"));
		}
	}
	
	@Test
	public void verifySelectSomeOptionFromNonDropDown_WebElementPredefined_ElementFound() throws Exception
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		createListOfLinks(7, "a", "testLink", "Link");
		String testLink = "testLink";
		WebElement clickSearch = sHelp.getElement("input[id='Test']", "cssSelector");
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testSelectSomeOptionFromNonDropdown("Link2", clickSearch, clickSearch, testLink, "id", "Test Element", false);
	
		String inputString = getByteStreamMessage(baos, old);
    
		Assert.assertTrue(inputString.trim().contains("Step: findOptionInListAndSelectIt has passed. Link2 has been selected successfully."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifySelectSomeOptionFromNonDropDown_WebElementPredefined_ElementNotFound() throws Exception
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test > </input>');");
			Thread.sleep(500);
			String testLink = "testLink";
			WebElement test = sHelp.getElement("Test", "id");
			WebElement test2 = sHelp.getElement("input[id='Test']", "cssSelector");
			LocalDriverManager.getDriver().navigate().refresh();
			Thread.sleep(500);
    
			page.testSelectSomeOptionFromNonDropdown("Link2", test, test2, testLink, "id", "Test Element", false);
			Assert.fail("Test was supposed to throw an error for element not being found");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: selectSomeOptionFromNonDropdown"));
			Assert.assertTrue(m.trim().contains("ErrorMessage : Element is not availale. Can not select the Test Element from the drop down list."));
		}
	}
	
	@Test 
	public void verifySomeElementContainsTheExpectedText_RemoveAllSpacesFalse() throws FrameworkException, InterruptedException, IOException
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
		Thread.sleep(500);
		String textEl = "Test";
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testVerifySomeElementContainsTheExpectedText(textEl, "id", "Testing 123", "Test Element", false);
	
		String inputString = getByteStreamMessage(baos, old);
    
		Assert.assertTrue(inputString.trim().contains("Step: verifySomeElementContainsTheExpectedText has passed. Test Element contains the correct text: Testing 123"));

		closeByteStream(ps, baos);
	}
	
	@Test 
	public void verifySomeElementContainsTheExpectedText_RemoveAllSpacesTrue() throws FrameworkException, InterruptedException, IOException
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
		Thread.sleep(500);
		String textEl = "Test";
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testVerifySomeElementContainsTheExpectedText(textEl, "id", "Testing 123", "Test Element", true);
	
		String inputString = getByteStreamMessage(baos, old);
    
		Assert.assertTrue(inputString.trim().contains("Step: verifySomeElementContainsTheExpectedText has passed. Test Element contains the correct text: Testing123"));

		closeByteStream(ps, baos);
	}
	
	@Test 
	public void verifySomeElementContainsTheExpectedText_ElementDoesNotContainCorrectText() throws FrameworkException, InterruptedException, IOException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			String textEl = "Test";
    
			page.testVerifySomeElementContainsTheExpectedText(textEl, "id", "Not correct", "Test Element", false);
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("junit.framework.AssertionFailedError: StepName: verifySomeElementContainsTheExpectedText"));
			Assert.assertTrue(m.trim().contains("ErrorMessage : Test Element does not contain the correct text. Expected text: Not correct. Actual text: Testing 123"));
		}
	}
	
	@Test 
	public void verifySomeElementContainsTheExpectedText_NullExpectedValue() throws FrameworkException, InterruptedException, IOException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			String textEl = "Test";
    
			page.testVerifySomeElementContainsTheExpectedText(textEl, "id", null, "Test Element", false);
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: verifySomeElementContainsTheExpectedText"));
		}
	}
	
	@Test 
	public void verifySomeElementContainsTheExpectedText_PredefinedWebElement_RemoveAllSpacesFalse() throws FrameworkException, InterruptedException, IOException
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
		Thread.sleep(500);
		WebElement textEl = sHelp.getElement("Test", "id");
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testVerifySomeElementContainsTheExpectedText(textEl, "Testing 123", "Test Element", false);
	
		String inputString = getByteStreamMessage(baos, old);
    
		Assert.assertTrue(inputString.trim().contains("Step: verifySomeElementContainsTheExpectedText has passed. Test Element contains the correct text: Testing 123"));

		closeByteStream(ps, baos);
	}
	
	@Test 
	public void verifySomeElementContainsTheExpectedText_PredefinedWebElement_RemoveAllSpacesTrue() throws FrameworkException, InterruptedException, IOException
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
		Thread.sleep(500);
		WebElement textEl = sHelp.getElement("Test", "id");
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testVerifySomeElementContainsTheExpectedText(textEl, "Testing 123", "Test Element", true);
	
		String inputString = getByteStreamMessage(baos, old);
    
		Assert.assertTrue(inputString.trim().contains("Step: verifySomeElementContainsTheExpectedText has passed. Test Element contains the correct text: Testing123"));

		closeByteStream(ps, baos);
	}
	
	@Test 
	public void verifySomeElementContainsTheExpectedText_PredefinedWebElement_ElementDoesNotContainCorrectText() throws FrameworkException, InterruptedException, IOException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			WebElement textEl = sHelp.getElement("Test", "id");
    
			page.testVerifySomeElementContainsTheExpectedText(textEl, "Not correct", "Test Element", false);
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("junit.framework.AssertionFailedError: StepName: verifySomeElementContainsTheExpectedText"));
			Assert.assertTrue(m.trim().contains("ErrorMessage : Test Element does not contain the correct text. Expected text: Not correct. Actual text: Testing 123"));
		}
	}
	
	@Test 
	public void verifySomeElementContainsTheExpectedText_PredefinedWebElement_NullExpectedValue() throws FrameworkException, InterruptedException, IOException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			WebElement textEl = sHelp.getElement("Test", "id");
    
			page.testVerifySomeElementContainsTheExpectedText(textEl, null, "Test Element", false);
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: verifySomeElementContainsTheExpectedText"));
		}
	}
	
	@Test
	public void verifySomeElementIsNotPresent_ElementNotPresent() throws InterruptedException, IOException
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
		Thread.sleep(500);
		String test = "NotHere";
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testverifySomeElementIsNotPresent(test, "id", "Test Element");
	
		String inputString = getByteStreamMessage(baos, old);
    
		Assert.assertTrue(inputString.trim().contains("Step: verifySomeElementIsNotPresent has passed. Test Element does not display in the page."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifySomeElementIsNotPresent_ElementPresent() throws InterruptedException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			String test = "Test";
    
			page.testverifySomeElementIsNotPresent(test, "id", "Test Element");
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: verifySomeElementIsNotPresent"));
			Assert.assertTrue(m.trim().contains("ErrorMessage : Test Element should not display in the page. It does display. This is not expected."));
			
		}
	}
	
	@Test
	public void verifySomeElementIsNotPresent_ExceptionThrown() throws InterruptedException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			String test = "Test";
    
			page.testverifySomeElementIsNotPresent(test, null, "Test Element");
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: verifySomeElementIsNotPresent"));
		}
	}
	
	@Test
	public void verifySomeElementIsPresent_ElementPresent() throws InterruptedException, IOException
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
		Thread.sleep(500);
		String test = "Test";
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testVerifySomeElementIsPresent(test, "id", "Test Element");
	
		String inputString = getByteStreamMessage(baos, old);
    
		Assert.assertTrue(inputString.trim().contains("Step: verifySomeElementIsPresent has passed. Test Element displays in the page as expected."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifySomeElementIsPresent_ElementNotPresent() throws InterruptedException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			String test = "NotHere";
    
			page.testVerifySomeElementIsPresent(test, "id", "Test Element");
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: verifySomeElementIsPresent"));
			Assert.assertTrue(m.trim().contains("ErrorMessage : Test Element should display in the page. It does not display as expected."));
			
		}
	}
	
	@Test
	public void verifySomeElementIsPresent_ExceptionThrown() throws InterruptedException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			String test = "Test";
    
			page.testVerifySomeElementIsPresent(test, null, "Test Element");
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: verifySomeElementIsPresent"));
		}
	}
	
	@Test
	public void verifyTextFieldIsBlank_TextFieldIsBlank() throws Exception
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test value= ></input>');");
		Thread.sleep(500);
		String test = "#Test";
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testVerifyTextFieldIsBlank(test, "cssSelector", false, null, "Test Element");
	
		String inputString = getByteStreamMessage(baos, old);
    
		Assert.assertTrue(inputString.trim().contains("Step: verifyTextFieldIsBlank has passed. Test Element is blank as expected."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyTextFieldIsBlank_TextFieldIsNotBlank() throws Exception
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test value=Testing123 ></input>');");
			Thread.sleep(500);
			String test = "#Test";
			
			page.testEnterAValueIntoATextField("Test123", test, "cssSelector", "Test Element");
			Thread.sleep(500);
    
			page.testVerifyTextFieldIsBlank(test, "cssSelector", false, null, "Test Element");
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: verifyTextFieldIsBlank"));
			Assert.assertTrue(m.trim().contains("ErrorMessage : Test Element should be blank but is retaining a value instead. The value being retained is Test123"));
			
		}
	}
	
	@Test
	public void verifyTextFieldIsBlank_ThrowsException() throws Exception
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test value=></input>');");
			Thread.sleep(500);
			String test = "#Test";
    
			page.testVerifyTextFieldIsBlank(test, "cssSelector", true, "1", "Test Element");
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: verifyTextFieldIsBlank"));
		}
	}
	
	@Test
	public void verifyTextInTextField_ContainsInvalidChar_RemoveAllSpacesFalse_TextIsPresent() throws Exception
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<textarea id=Test value=></textarea>');");
		Thread.sleep(500);
		String test = "#Test";
		
		page.testEnterAValueIntoATextField("Test\n123", test, "cssSelector", "Test Element");
		Thread.sleep(500);
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testVerifyTextInTextField(test, "cssSelector", false, null, "Test 123", "Test Element", false);
	
		String inputString = getByteStreamMessage(baos, old);
    
		Assert.assertTrue(inputString.trim().contains("Step: verifyTextInTextField has passed. Test Element contains Test 123 as expected."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyTextInTextField_DoesntContainInvalidChar_RemoveAllSpacesTrue_TextIsPresent() throws InterruptedException, IOException
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=\"Test\" value=\"Test 123\"></input>');");
		Thread.sleep(500);
		String test = "#Test";
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testVerifyTextInTextField(test, "cssSelector", false, null, "Test 123", "Test Element", true);
	
		String inputString = getByteStreamMessage(baos, old);
    
		Assert.assertTrue(inputString.trim().contains("Step: verifyTextInTextField has passed. Test Element contains Test123 as expected."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyTextInTextField_DoesntContainInvalidChar_RemoveAllSpacesFalse_TextIsNotPresent() throws Exception
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test value=Testing123 ></input>');");
			Thread.sleep(500);
			String test = "#Test";
    
			page.testVerifyTextInTextField(test, "cssSelector", false, null, "Test 123", "Test Element", false);
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (FrameworkException ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: verifyTextInTextField"));
			Assert.assertTrue(m.trim().contains("ErrorMessage : Test Element should contain Test 123 but is retaining an incorrect value instead. The value being retained is Testing123"));		
		}
	}
	
	@Test
	public void verifyTextInTextField_ExceptionThrown() throws InterruptedException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('<input id=Test value=Testing 123 ></input>');");
			Thread.sleep(500);
			String test = "#Test";
    
			page.testVerifyTextInTextField(test, "cssSelector", true, "1", "Test 123", "Test Element", false);
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("reporting.framework.utilities.FrameworkException: StepName: verifyTextInTextField"));
		}
	}
	
	private void createListOfLinks(int i, String tagName, String id, String linkText) throws Exception
	{
		try
		{
			for (int j = 0; j < i; j++)
			{
				String html = "<a href=\"http://www.google.com\" id=\"" +id +"\" > " + linkText + j + "</a>";
				((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("document.write('" + html + "');");
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
	
	private void makeJQueryActive() throws Exception
	{
		try
		{
			((JavascriptExecutor)LocalDriverManager.getDriver()).executeScript("var jq = document.createElement('script'); jq.src = \"https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js\"; document.getElementsByTagName('head')[0].appendChild(jq);");
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
	
	private void verifyCharactersNotInString(String[] characters, String value) throws Exception
	{
		try
		{
			for(int i = 0; i < characters.length; i++)
			{
				Assert.assertFalse(value.toLowerCase().trim().contains(characters[i].toLowerCase().trim()));
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
	
	@AfterMethod
	public void afterScenario() throws Exception
	{
		/*try
		{
			LocalDriverManager.getDriver().quit();
		}
		catch (Exception ex)
		{
			LocalDriverManager.getDriver().quit();
			throw ex;
		}
		finally
		{
			LocalDriverManager.getDriver().quit();
		}*/
	}


}
