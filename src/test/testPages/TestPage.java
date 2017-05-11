package testPages;

import java.util.List;

import org.openqa.selenium.WebElement;

import commonClasses.sharedPageClasses.Page;
import commonClasses.sharedUtils.*;


public class TestPage extends Page {

	public TestPage() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void WaitForPageLoad() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public String someRandomString(int length, String value) throws Exception
	{
		String someValue = null;
		someValue = randomString(length, value);
		return someValue;
	}
	
	public void testActualVersusExpected(String expectedValue, String actualValue, String variableBeingTested) throws Exception
	{
		verifyTheActualValueContainsTheExpectedValue(expectedValue, actualValue, variableBeingTested);
	}
	
	public void testActualVersusExpectedExact(String expectedValue, String actualValue, String variableBeingTested) throws Exception
	{
		verifyTheActualValueMatchesTheExpectedValue(expectedValue, actualValue, variableBeingTested);
	}
	
	public String testSubtractDays(String date, int totalDays, int daysToSubtract) throws Exception
	{
		return subtractDays(date, totalDays, daysToSubtract);
	}
	
	public void testCleanUp(String value)
	{
		cleanUp(value);
	}
	
	public String testRemoveOrChangeUnwantedCharacter(String variable, String character, String replaceValue) throws Exception
	{
		return removeOrChangeUnwantedCharacter(variable, character, replaceValue);
	}
	
	public void testFailIfNull(String variable, String passMessage, String failMessage) throws Exception
	{
		failTestIfVariableIsNull(variable, passMessage, failMessage);
	}
	
	public void enums()
	{
		System.out.println(Entity.CrashRecords);
		System.out.println(Entity.valueOf(Entity.CrashRecords.toString()));
		System.out.println(Entity.MS2);
		System.out.println(Entity.valueOf(Entity.MS2.toString()));
		System.out.println(Entity.Requests);
		System.out.println(Entity.valueOf(Entity.Requests.toString()));
		System.out.println(Entity.Scheduler);
		System.out.println(Entity.valueOf(Entity.Scheduler.toString()));
		
		System.out.println(Timezones.Central);
		System.out.println(Timezones.valueOf(Timezones.Central.toString()));
		System.out.println(Timezones.Dubai);
		System.out.println(Timezones.valueOf(Timezones.Dubai.toString()));
		System.out.println(Timezones.Eastern);
		System.out.println(Timezones.valueOf(Timezones.Eastern.toString()));
		System.out.println(Timezones.GMT);
		System.out.println(Timezones.valueOf(Timezones.GMT.toString()));
		System.out.println(Timezones.HK);
		System.out.println(Timezones.valueOf(Timezones.HK.toString()));
		System.out.println(Timezones.Pacific);
		System.out.println(Timezones.valueOf(Timezones.Pacific.toString()));
		
		System.out.println(DaysOfTheWeek.ALL);
		System.out.println(DaysOfTheWeek.valueOf(DaysOfTheWeek.ALL.toString()));
		System.out.println(DaysOfTheWeek.WEEKDAYS);
		System.out.println(DaysOfTheWeek.valueOf(DaysOfTheWeek.WEEKDAYS.toString()));
		System.out.println(DaysOfTheWeek.WEEKENDS);
		System.out.println(DaysOfTheWeek.valueOf(DaysOfTheWeek.WEEKENDS.toString()));
	}
	
	public String[] testGetCurrentSplitDate() throws Exception
	{
		return getCurrentSplitDate();
	}
	
	public int testGetFutureDate(int daysOutFromCurrentDay) throws Exception
	{
		return getFutureDate(daysOutFromCurrentDay);
	}
	
	public void testEnterAValueIntoATextField(String value, String webElement, String byValue, String elementBeingTested) throws Exception
	{
		enterAvalueIntoATextField(value, webElement, byValue, elementBeingTested);
	}
	
	public void testEnterAValueIntoATextField(String value, WebElement webElement, String elementBeingTested) throws Exception
	{
		enterAvalueIntoATextField(value, webElement, elementBeingTested);
	}
	
	public void testFindOptionInListAndSelectIt(List<WebElement> webElements, String webelementListHtml, String expectedOption, Boolean clickViaJQuery) throws Exception
	{
		findOptionInListAndSelectIt(webElements, webelementListHtml, expectedOption, clickViaJQuery);
	}
	
	public void testClickSomeElement(String html, String byValue, String elementBeingTested) throws Exception
	{
		clickSomeElement(html, byValue, elementBeingTested);
	}
	
	public int testGetTotalDaysInMonth() throws Exception
	{
		return getTotalDaysInMonth();
	}
	
	public void testSelectSomeOptionFromNonDropdown(String option, String clickElement, String clickByValue, String searchElement, String searchByValue, String optionsElement, String optionsByValue, String elementBeingTested, Boolean clickViaJQuery) throws Exception, InterruptedException
	{
		selectSomeOptionFromNonDropdown(option, clickElement, clickByValue, searchElement, searchByValue, optionsElement, optionsByValue, elementBeingTested, clickViaJQuery);
	}
	
	public void testSelectSomeOptionFromNonDropdown(String option, WebElement clickElement, WebElement searchElement, String optionsElement, String optionsByValue, String elementBeingTested, Boolean clickViaJQuery) throws Exception, InterruptedException
	{
		selectSomeOptionFromNonDropdown(option, clickElement, searchElement, optionsElement, optionsByValue, elementBeingTested, clickViaJQuery);
	}
	
	public void testVerifySomeElementContainsTheExpectedText(String elementHtml, String byValue, String expectedText, String elementBeingTested, Boolean removeAllSpaces) throws Exception
	{
		verifySomeElementContainsTheExpectedText(elementHtml, byValue, expectedText, elementBeingTested, removeAllSpaces);
	}
	
	public void testVerifySomeElementContainsTheExpectedText(WebElement element, String expectedText, String elementBeingTested, Boolean removeAllSpaces) throws Exception
	{
		verifySomeElementContainsTheExpectedText(element, expectedText, elementBeingTested, removeAllSpaces);
	}
	
	public void testverifySomeElementIsNotPresent(String elementHtml, String byValue, String elementBeingTested) throws Exception
	{
		verifySomeElementIsNotPresent(elementHtml, byValue, elementBeingTested);
	}
	
	public void testVerifySomeElementIsPresent(String elementHtml, String byValue, String elementBeingTested) throws Exception
	{
		verifySomeElementIsPresent(elementHtml, byValue, elementBeingTested);
	}
	
	public void testVerifyTextFieldIsBlank(String html, String byValue, Boolean requiresIndex, String webElementIndex, String elementBeingTested) throws Exception
	{
		verifyTextFieldIsBlank(html, byValue, requiresIndex, webElementIndex, elementBeingTested);
	}
	
	public void testVerifyTextInTextField(String html, String byValue, Boolean requiresIndex, String webElementIndex, String expectedText, String elementBeingTested, Boolean removeAllSpaces) throws Exception
	{
		verifyTextInTextField(html, byValue, requiresIndex, webElementIndex, expectedText, elementBeingTested, removeAllSpaces);
	}
	
	 public static void superficialEnumCodeCoverage(Class<? extends Enum<?>> enumClass) {
		    try {
		      for (Object o : (Object[])enumClass.getMethod("values").invoke(null)) {
		        enumClass.getMethod("valueOf", String.class).invoke(null, o.toString());
		      }
		    }
		    catch (Throwable e) {
		      throw new RuntimeException(e);
		    }
		  }

}
