package unitTests;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import common.utils.*;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;
import testPages.TestInitialization;
import testPages.TestPage;

@Listeners(WebDriverListener.class)
public class PageClassTests extends TestInitialization {

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
			Assert.assertTrue(m.contains("StepName: randomStringAtSetLength"));
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
	    baos.toString();
			
		//Assert.assertTrue(inputString.trim().contains("Step: verifyTheActualValueContainsTheExpectedValue has passed. Test is set correctly in Test"));
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
		catch (Exception ex)
		{
			 System.out.flush();
			 System.setOut(old);
			 String inputString = baos.toString();
			 String newInputString = inputString.replaceAll(" ", "");
			 
			Assert.assertTrue(newInputString.trim().contains("verifyTheActualValueContainsTheExpectedValuehaspassed.TestissetcorrectlyinTest"));
			ps.close();
			baos.close();
			    
			ex.toString();
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
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifyTheActualValueContainsTheExpectedValue"));
			Assert.assertTrue(m.trim().contains("ErrorMessage: Test returned null in the actual variable that was set. Check the test to verify all variables are being assigned a value appropriately."));
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
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifyTheActualValueContainsTheExpectedValue"));
			Assert.assertTrue(m.trim().contains("ErrorMessage: Test is not set as expected. Nothing is set instead."));
		}
	}
	
	@Test
	public void verifySubtractDays_DayStartsWith0() throws Exception
	{
		TestPage page = new TestPage();
		
		String actualDate = page.testSubtractDays("05-JAN-2017", 1, 1);
		
		Assert.assertEquals("4-Jan-2017", actualDate);
	}
	
	@Test
	public void verifySubtractDays_DayStartsWith1() throws Exception
	{
		TestPage page = new TestPage();
		
		String actualDate = page.testSubtractDays("15-JAN-2017", 1, 1);
		
		Assert.assertEquals("14-Jan-2017", actualDate);
	}
	
	@Test
	public void verifySubtractDays_IncorrectDateFormat() throws Exception
	{
		try
		{
			TestPage page = new TestPage();
		
			String actualDate = page.testSubtractDays("123456789", 1, 1);
		
			Assert.fail("Expected AssertionError exception to be thrown." + actualDate);
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: subtractDays"));
			Assert.assertTrue(m.trim().contains("ErrorMessage : Unparseable date:"));
		}
	}
	
	@Test
	public void verifyCleanUp() throws Exception
	{
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
	public void verifyTheExpectedValueMatchesTheActualValue() throws Exception, IOException
	{
		TestPage page = new TestPage();
		
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
		
		page.testActualVersusExpectedExact("Test", "Test", "Test");
		
	    System.out.flush();
	    System.setOut(old);
	    baos.toString();
			
		//Assert.assertTrue(inputString.trim().contains("Step: verifyTheActualValueMatchesTheExpectedValue has passed. Test is set correctly in Test"));
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
		catch (Exception ex)
		{			    
			String m = ex.toString();
			String newM = m.replaceAll(" ", "");
			Assert.assertTrue(newM.trim().contains("StepName:verifyTheActualValueMatchesTheExpectedValue"));
			Assert.assertTrue(newM.trim().contains("ErrorMessage:Testreturnednullineithertheactualortheexpectedvariablethatwasset.Checkthetesttoverifyallvariablesarebeingassignedavalueappropriately.Actual:Test.Expected:null"));
		}
	}
	
	@Test
	public void verifyTheActualValueNull_exactMatch() throws Exception, IOException
	{
		try
		{
			TestPage page = new TestPage();
		
			page.testActualVersusExpectedExact("Test", null, "Test");
			Assert.fail("Expected an AssertionFailedError to be thrown");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			String newM = m.replaceAll(" ", "");
			Assert.assertTrue(newM.trim().contains("StepName:verifyTheActualValueMatchesTheExpectedValue"));
			Assert.assertTrue(newM.trim().contains("ErrorMessage:Testreturnednullineithertheactualortheexpectedvariablethatwasset.Checkthetesttoverifyallvariablesarebeingassignedavalueappropriately.Actual:null.Expected:Test"));
		}
	}
	
	@Test
	public void verifyTheActualValueDoesntMatchExpectedValue() throws Exception, IOException
	{
		try
		{
			TestPage page = new TestPage();
		
			page.testActualVersusExpectedExact("Test", "Nothing", "Test");
			Assert.fail("Expected an AssertionFailedError to be thrown");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifyTheActualValueMatchesTheExpectedValue"));
			Assert.assertTrue(m.trim().contains("ErrorMessage: Test is not set as expected. Nothing is set instead."));
		}
	}
	
	@Test
	public void verifyRemovingCharacters_containsCharacter() throws Exception
	{
		TestPage page = new TestPage();
		
		String actualValue = page.testRemoveOrChangeUnwantedCharacter("Test!", "!", ".");
		
		Assert.assertEquals("Test.", actualValue);
	}
	
	@Test
	public void verifyRemovingCharacters_doesntContainCharacter() throws Exception
	{
		TestPage page = new TestPage();
		
		String actualValue = page.testRemoveOrChangeUnwantedCharacter("Test", "!", ".");
		
		Assert.assertEquals("Test", actualValue);
	}
	
	@Test
	public void verifyRemovingCharacters_nullException() throws Exception
	{
		try
		{
			TestPage page = new TestPage();
		
			String actualValue = page.testRemoveOrChangeUnwantedCharacter(null, "!", ".");
			Assert.fail("Test expected to throw exception" + actualValue);
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("Object must not be null"));
		}
	}
	
	@Test
	public void failTestIfVariableIsNull_nullVariable() throws Exception
	{
		try
		{
			TestPage page = new TestPage();
			
			page.testFailIfNull(null, "Passed", "Failed");;
			Assert.fail("Test expected to throw exception");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: failTestIfVariableIsNull"));
		}
	}
	
	@Test
	public void failTestIfVariableIsNull_notNullVariable() throws Exception, IOException
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
				
			Assert.assertTrue(inputString.trim().contains("Step: failTestIfVariableIsNull has passed. Passed"));
			ps.close();
			baos.close();
		
	}
/*	
	@Test
	public void verifySetActualStartTime() throws Exception
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
	public void verifyGetActualStartTime() throws Exception
	{
		TestPage page = new TestPage();
		
		String value = page.testGetActualStartTime("Test", true);
		
		Assert.assertTrue("Start time contains a value", ExtensionMethods.isNullOrBlank(value));
		
		value = page.testGetActualStartTime("Test", false);
		
		Assert.assertEquals("The two times do not match", "Test", value);
	}*/
	
	@Test
	public void verifyGetCurrentSplitDate() throws Exception
	{
		TestPage page = new TestPage();
		
		String[] value = page.testGetCurrentSplitDate();
		
		Assert.assertTrue(TestUtils.GetCurrentDateTime("dd-MM-yyyy").contains(value[0]));
		Assert.assertTrue(TestUtils.GetCurrentDateTime("dd-MM-yyyy").contains(value[1]));
		Assert.assertTrue(TestUtils.GetCurrentDateTime("dd-MM-yyyy").contains(value[2]));
	}
	
/*	@Test
	public void verifyActualSlug_ContainsValue() throws Exception
	{
		TestPage page = new TestPage();
		
		String value = page.testActualSlug("Test");
		
		Assert.assertEquals("The two slug names do not match", "Test", value);
		
		value = page.testActualSlug(null);
		
		Assert.assertTrue("Start time contains a value", ExtensionMethods.isNullOrBlank(Generic.cnnId));
	}
	
	@Test
	public void verifySetScheduledRecordingDate_IsStartDateRegularFormat() throws Exception
	{
		TestPage page = new TestPage();
		
		String time = TestUtils.getTimeStamp();
		
		page.testSetScheduledRecordingDate(time, true, false);
		
		Assert.assertEquals("The two dates do not match", time, Generic.scheduledRecordingStartDate);
	}
	
	@Test
	public void verifySetScheduledRecordingDate_IsStartDateAltFormat() throws Exception
	{
		TestPage page = new TestPage();
		
		String time = TestUtils.getTimeStamp();
		
		page.testSetScheduledRecordingDate(time, true, true);
		
		Assert.assertEquals("The two dates do not match", time, Generic.scheduledRecordingStartDateAlt);
	}
	
	@Test
	public void verifySetScheduledRecordingDate_IsNotStartDateAltFormat() throws Exception
	{
		TestPage page = new TestPage();
		
		String time = TestUtils.getTimeStamp();
		
		page.testSetScheduledRecordingDate(time, false, true);
		
		Assert.assertEquals("The two dates do not match", time, Generic.scheduledRecordingEndDateAlt);
	}
	
	@Test
	public void verifySetScheduledRecordingDate_IsNotStartDateRegularFormat() throws Exception
	{
		TestPage page = new TestPage();
		
		String time = TestUtils.getTimeStamp();
		
		page.testSetScheduledRecordingDate(time, false, false);
		
		Assert.assertEquals("The two dates do not match", time, Generic.scheduledRecordingEndDate);
	}
	*/
	@Test
	public void verifyGetFutureDate() throws Exception
	{
		TestPage page = new TestPage();
		
		String day = TestUtils.GetCurrentDateTime("dd");
		
		int value = page.testGetFutureDate(1);
		
		Assert.assertEquals(value, Integer.parseInt(day) + 1);
	}
	
/*	@Test
	public void verifyNavigateViaNewsAppsToggle() throws Exception, IOException
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test > </input>');");
		Generic.newsAppsToggle = "input[id='Test']";
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    
		page.testNavigateViaNewsappsToggle(locator, by, "TestElement");
		
	    System.out.flush();
	    System.setOut(old);
	    String inputString = baos.toString();
	    
	    Assert.assertTrue("Console messages do not match", inputString.trim().contains("Step: clickSomeElement has passed. NewsApps Toggle clicked successfully."));
	    Assert.assertTrue("Console messages do not match", inputString.trim().contains("Step: clickSomeElement has passed. TestElement clicked successfully."));
	
	    ps.close();
		baos.close();
	}*/
	
/*	@Test
	public void verifyNavigateViaNewsAppsToggle_cantFindElement() throws Exception, IOException
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
	public void verifyEnterAValueIntoATextField() throws Exception, IOException, InterruptedException
	{
		TestPage page = new TestPage();
		Thread.sleep(800);
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		Locator locator = new Locator("#Test");
		By by = new By("css");
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    
		page.testEnterAValueIntoATextField("Test123", locator, by, "Test Element");;
		
	    String inputString = getByteStreamMessage(baos, old);
	    
	    Assert.assertTrue(inputString.trim().contains("enterTextIntoTestElement has passed. Test Element has been entered successfully"));
	
	    closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyEnterAValueIntoATextField_NullValue() throws Exception, IOException, InterruptedException
	{	
		TestPage page = new TestPage();
		Thread.sleep(800);
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		Locator locator = new Locator("#Test");
		By by = new By("css");
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testEnterAValueIntoATextField(null, locator, by, "Test Element");;
	
		String inputString = getByteStreamMessage(baos, old);
		String newIS = inputString.replaceAll(" ", "");
    
		Assert.assertTrue(newIS.trim().contains("Step:clickSomeTestElementhaspassed.TestElementclickedsuccessfully."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyEnterAValueIntoATextField_ElementNotFound() throws Exception, IOException, InterruptedException
	{
	    try
		{
			TestPage page = new TestPage();
			Locator locator = new Locator("#Test");
			By by = new By("css");
			
			page.testEnterAValueIntoATextField(null, locator, by, "Test Element");
			Assert.fail("Error expected to be thrown");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("Test Element does not display as expected. Unable to enter text in this field."));
		}
	}
	
	
	@Test
	public void verifyEnterAValueIntoATextField_ElementDefined() throws Exception, IOException, InterruptedException
	{
		TestPage page = new TestPage();
		Thread.sleep(800);
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		Locator locator = new Locator("Test");
		By by = new By("id");
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);
	    WebElement el = SHelper.get().element().get(locator, by);
	    
		page.testEnterAValueIntoATextField("Test123", el, "Test Element");;
		
	    String inputString = getByteStreamMessage(baos, old);
	    
	    Assert.assertTrue(inputString.trim().contains("enterTextIntoWebElement has passed. Test Element has been entered successfully"));
	
	    closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyEnterAValueIntoATextField_NullValue_ElementDefined() throws Exception, IOException, InterruptedException
	{	
		TestPage page = new TestPage();
		Thread.sleep(800);
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		Locator locator = new Locator("input[id='Test']");
		By by = new By("css");
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testEnterAValueIntoATextField(null, SHelper.get().element().get(locator, by), "Test Element");;
	
		String inputString = getByteStreamMessage(baos, old);
    
		Assert.assertTrue(inputString.trim().contains("enterTextIntoWebElement has passed. Test Element has been entered successfully"));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyEnterAValueIntoATextField_ElementNotFound_ElementDefined() throws Exception, IOException, InterruptedException
	{
	    try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
			Locator locator = new Locator("#Test");
			By by = new By("css");
			Thread.sleep(500);
			WebElement test = SHelper.get().element().get(locator, by);
			LocalDriver.getDriver().navigate().refresh();
			Thread.sleep(500);
			
			page.testEnterAValueIntoATextField("Test", test, "Test Element");
			Assert.fail("Error expected to be thrown");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("enterTextIntoWebElement"));
		}
	}
	
	@Test
	public void verifyClickSomeElement_ElementFound() throws InterruptedException, Exception, IOException
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
		Thread.sleep(500);
		Locator locator = new Locator("input[id='Test']");
		By by = new By("css");
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testClickSomeElement(locator, by, "Test Element");
	
		String inputString = getByteStreamMessage(baos, old);
    
		Assert.assertTrue(inputString.trim().contains("clickSomeTestElement has passed. Test Element clicked successfully."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyClickSomeElement_ElementNotFound() throws InterruptedException, Exception
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
			Thread.sleep(500);
			Locator locator = new Locator("input[id='NotHere']");
			By by = new By("css");
	    
			page.testClickSomeElement(locator, by, "Test Element");
			Assert.fail("Exception was expected to be thrown for a failed test case");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("Element is not on the page. Unable to click the Test Element"));
		}
	}
	
	@Test
	public void verifyClickSomeElement_ThrowsException() throws InterruptedException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test > </input>');");
			Thread.sleep(500);
			Locator locator = new Locator("#NotHere");
			By by = new By("id");
	    
			page.testClickSomeElement(locator, by, "Test Element");
			Assert.fail("Exception was expected to be thrown for a failed test case");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: clickSomeTestElement"));
		}
	}
	
	@Test
	public void getTotalDaysInMonth() throws Exception
	{
		TestPage page = new TestPage();
		
		int totalDays = page.testGetTotalDaysInMonth();
		String month = TestUtils.GetCurrentDateTime("MMM");
		int expectedTotal = getTotalExpectedMonths(month);
		
		Assert.assertEquals(expectedTotal, totalDays);
	}
	
	@Test 
	public void verifySomeElementContainsTheExpectedText_PredefinedWebElement_RemoveAllSpacesFalse() throws Exception, InterruptedException, IOException
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		WebElement textEl = SHelper.get().element().get(locator, by);
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testVerifySomeElementContainsTheExpectedText(textEl, "Testing 123", "Test Element", false);
	
		getByteStreamMessage(baos, old);
    
		//Assert.assertTrue(inputString.trim().contains("Step: verifySomeElementContainsTheExpectedText has passed. Test Element contains the correct text: Testing 123"));

		closeByteStream(ps, baos);
	}
	
	@Test 
	public void verifySomeElementContainsTheExpectedText_PredefinedWebElement_RemoveAllSpacesTrue() throws Exception, InterruptedException, IOException
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		WebElement textEl = SHelper.get().element().get(locator, by);
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testVerifySomeElementContainsTheExpectedText(textEl, "Testing 123", "Test Element", true);
	
		getByteStreamMessage(baos, old);
    
		//Assert.assertTrue(inputString.trim().contains("Step: verifySomeElementContainsTheExpectedText has passed. Test Element contains the correct text: Testing123"));

		closeByteStream(ps, baos);
	}
	
	@Test 
	public void verifySomeElementContainsTheExpectedText_PredefinedWebElement_ElementDoesNotContainCorrectText() throws Exception, InterruptedException, IOException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
			Locator locator = new Locator("Test");
			By by = new By("id");
			Thread.sleep(500);
			WebElement textEl = SHelper.get().element().get(locator, by);
    
			page.testVerifySomeElementContainsTheExpectedText(textEl, "Not correct", "Test Element", false);
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifySomeWebElementContainsTheExpectedText"));
			Assert.assertTrue(m.trim().contains("Test Element does not contain the correct text. Expected text: Not correct. Actual text: Testing 123"));
		}
	}
	
	@Test 
	public void verifySomeElementContainsTheExpectedText_PredefinedWebElement_NullExpectedValue() throws Exception, InterruptedException, IOException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
			Locator locator = new Locator("Test");
			By by = new By("id");
			Thread.sleep(500);
			WebElement textEl = SHelper.get().element().get(locator, by);
    
			page.testVerifySomeElementContainsTheExpectedText(textEl, null, "Test Element", false);
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("The Expected Text was not provided. Please add the appropriate method to the chain."));
		}
	}
	
	@Test
	public void verifySomeElementIsNotPresent_ElementNotPresent() throws Exception
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
		Thread.sleep(500);
		Locator locator = new Locator("NotHere");
		By by = new By("id");
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testverifySomeElementIsNotPresent(locator, by, "Test Element");
	
		getByteStreamMessage(baos, old);
    
		//Assert.assertTrue(inputString.trim().contains("Step: verifySomeElementIsNotPresent has passed. Test Element does not display in the page."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifySomeElementIsNotPresent_ElementPresent() throws InterruptedException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			Locator locator = new Locator("Test");
			By by = new By("id");
    
			page.testverifySomeElementIsNotPresent(locator, by, "Test Element");
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifySomeElementIsNotPresent"));
			Assert.assertTrue(m.trim().contains("ErrorMessage: Test Element should not display in the page. It does display. This is not expected."));
			
		}
	}
	
	@Test
	public void verifySomeElementIsNotPresent_ExceptionThrown() throws InterruptedException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			Locator locator = new Locator("Test");
			By by = new By("id");
    
			page.testverifySomeElementIsNotPresent(locator, by, "Test Element");
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifySomeElementIsNotPresent"));
		}
	}
	
	@Test
	public void verifySomeElementIsPresent_ElementPresent() throws Exception
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
		Thread.sleep(500);
		Locator locator = new Locator("Test");
		By by = new By("id");
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testVerifySomeElementIsPresent(locator, by, "Test Element");
	
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
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			Locator locator = new Locator("NotHere");
			By by = new By("id");
    
			page.testVerifySomeElementIsPresent(locator, by, "Test Element");
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifySomeElementIsPresent"));
			Assert.assertTrue(m.trim().contains("ErrorMessage: Test Element should display in the page. It does not display as expected."));
			
		}
	}
	
	@Test
	public void verifySomeElementIsPresent_ExceptionThrown() throws InterruptedException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test >Testing 123</div>');");
			Thread.sleep(500);
			Locator locator = new Locator("NotHere");
			By by = new By("id");
    
			page.testVerifySomeElementIsPresent(locator, by, "Test Element");
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifySomeElementIsPresent"));
		}
	}
	
	@Test
	public void verifyTextFieldIsBlank_TextFieldIsBlank() throws Exception
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value= ></input>');");
		Thread.sleep(500);
		Locator locator = new Locator("#Test");
		By by = new By("css");
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testVerifyTextFieldIsBlank(locator, by, false, null, "Test Element");
	
		String inputString = getByteStreamMessage(baos, old);
		
		String newIS = inputString.replaceAll(" ", "");
    
		Assert.assertTrue(newIS.trim().contains("Step:verifyTextFieldIsBlankhaspassed.TestElementisblankasexpected."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyTextFieldIsBlank_TextFieldIsNotBlank() throws Exception
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing123 ></input>');");
			Thread.sleep(500);
			Locator locator = new Locator("#Test");
			By by = new By("css");
			
			page.testEnterAValueIntoATextField("Test123", locator, by, "Test Element");
			Thread.sleep(500);
    
			page.testVerifyTextFieldIsBlank(locator, by, false, null, "Test Element");
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifyTextFieldIsBlank"));
			Assert.assertTrue(m.trim().contains("ErrorMessage: Test Element should be blank but is retaining a value instead. The value being retained is Test123"));
			
		}
	}
	
	@Test
	public void verifyTextFieldIsBlank_ThrowsException() throws Exception
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=></input>');");
			Thread.sleep(500);
			Locator locator = new Locator("#Test");
			By by = new By("css");
    
			page.testVerifyTextFieldIsBlank(locator, by, true, "1", "Test Element");
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifyTextFieldIsBlank"));
		}
	}
	
	@Test
	public void verifyTextInTextField_ContainsInvalidChar_RemoveAllSpacesFalse_TextIsPresent() throws Exception
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<textarea id=Test value=></textarea>');");
		Thread.sleep(500);
		Locator locator = new Locator("textarea[id='Test']");
		By by = new By("css");
		
		page.testEnterAValueIntoATextField("Test\n123", locator, by, "Test Element");
		Thread.sleep(500);
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testVerifyTextInTextField(locator, by, null, "Test 123", "Test Element", false);
	
		String inputString = getByteStreamMessage(baos, old);
		String newIS = inputString.replaceAll(" ", "");
    
		Assert.assertTrue(newIS.trim().contains("Step:verifyTextInTextFieldhaspassed.TestElementcontainsTest123asexpected."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyTextInTextField_DoesntContainInvalidChar_RemoveAllSpacesTrue_TextIsPresent() throws Exception
	{
		TestPage page = new TestPage();
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=\"Test\" value=\"Test 123\"></input>');");
		Thread.sleep(500);
		Locator locator = new Locator("#Test");
		By by = new By("css");
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
    
		page.testVerifyTextInTextField(locator, by, null, "Test 123", "Test Element", true);
	
		String inputString = getByteStreamMessage(baos, old);
		String newIS = inputString.replaceAll(" ", "");
    
		Assert.assertTrue(newIS.trim().contains("Step:verifyTextInTextFieldhaspassed.TestElementcontainsTest123asexpected."));

		closeByteStream(ps, baos);
	}
	
	@Test
	public void verifyTextInTextField_DoesntContainInvalidChar_RemoveAllSpacesFalse_TextIsNotPresent() throws Exception
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing123 ></input>');");
			Thread.sleep(500);
			Locator locator = new Locator("#Test");
			By by = new By("css");
    
			page.testVerifyTextInTextField(locator, by, null, "Test 123", "Test Element", false);
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifyTextInTextField"));
			Assert.assertTrue(m.trim().contains("ErrorMessage: Test Element should contain Test 123 but is retaining an incorrect value instead. The value being retained is Testing123"));		
		}
	}
	
	@Test
	public void verifyTextInTextField_ExceptionThrown() throws InterruptedException
	{
		try
		{
			TestPage page = new TestPage();
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing 123 ></input>');");
			Thread.sleep(500);
			Locator locator = new Locator("#Test");
			By by = new By("css");
    
			page.testVerifyTextInTextField(locator, by, "1", "Test 123", "Test Element", false);
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		}
		catch (Exception ex)
		{
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifyTextInTextField"));
		}
	}
	
	@SuppressWarnings("unused")
	private void createListOfLinks(int i, String tagName, String id, String linkText) throws Exception
	{
		try
		{
			for (int j = 0; j < i; j++)
			{
				String html = "<a href=http://www.google.com id=" +id +" > " + linkText + j + "</a>";
				((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('" + html + "');");
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
	
	@SuppressWarnings("unused")
	private void makeJQueryActive() throws Exception
	{
		try
		{
			String string = "var jq = document.createElement('script'); jq.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js'; document.getElementsByTagName('head')[0].appendChild(jq);";
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript(string);
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
				break;
			case "FEB":
				totalDays = 28;
				break;
			case "MAR":
				totalDays = 31;
				break;
			case "APR":
				totalDays = 30;
				break;
			case "MAY":
				totalDays = 31;
				break;
			case "JUN":
				totalDays = 30;
				break;
			case "JUL":
				totalDays = 31;
				break;
			case "AUG":
				totalDays = 31;
				break;
			case "SEP":
				totalDays = 30;
				break;
			case "OCT":
				totalDays = 31;
				break;
			case "NOV":
				totalDays = 30;
				break;
			case "DEC":
				totalDays = 31;
				break;
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
	
}
