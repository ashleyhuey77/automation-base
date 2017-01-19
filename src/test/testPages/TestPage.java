package testPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commonClasses.sharedPageClasses.Page;
import commonClasses.sharedUtils.*;
import reporting.framework.utilities.FrameworkException;


public class TestPage extends Page {

	public TestPage(WebDriver browser, Report report, Validations validations) throws FrameworkException {
		super(browser, report, validations);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void WaitForPageLoad() throws FrameworkException {
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
	
	public void testActualVersusExpectedExact(String expectedValue, String actualValue, String variableBeingTested) throws FrameworkException
	{
		verifyTheActualValueMatchesTheExpectedValue(expectedValue, actualValue, variableBeingTested);
	}
	
	public String testSubtractDays(String date, int totalDays, int daysToSubtract) throws FrameworkException
	{
		return subtractDays(date, totalDays, daysToSubtract);
	}
	
	public void testCleanUp(String value)
	{
		cleanUp(value);
	}
	
	public String testRemoveOrChangeUnwantedCharacter(String variable, String character, String replaceValue) throws FrameworkException
	{
		return removeOrChangeUnwantedCharacter(variable, character, replaceValue);
	}
	
	public void testFailIfNull(String variable, String passMessage, String failMessage) throws FrameworkException
	{
		failTestIfVariableIsNull(variable, passMessage, failMessage);
	}
	
	public void enums()
	{
		System.out.println(Entity.CrashRecords);
		System.out.println(Entity.MS2);
		System.out.println(Entity.Requests);
		System.out.println(Entity.Scheduler);
		
		System.out.println(Timezones.Central);
		System.out.println(Timezones.Dubai);
		System.out.println(Timezones.Eastern);
		System.out.println(Timezones.GMT);
		System.out.println(Timezones.HK);
		System.out.println(Timezones.Pacific);
		
		System.out.println(DaysOfTheWeek.ALL);
		System.out.println(DaysOfTheWeek.WEEKDAYS);
		System.out.println(DaysOfTheWeek.WEEKENDS);
		
	}
	
	public String[] testGetCurrentSplitDate() throws FrameworkException
	{
		return getCurrentSplitDate();
	}
	
	public int testGetFutureDate(int daysOutFromCurrentDay) throws FrameworkException
	{
		return getFutureDate(daysOutFromCurrentDay);
	}
	
	public void testEnterAValueIntoATextField(String value, String webElement, String byValue, String elementBeingTested) throws FrameworkException
	{
		enterAvalueIntoATextField(value, webElement, byValue, elementBeingTested);
	}
	
	public void testEnterAValueIntoATextField(String value, WebElement webElement, String elementBeingTested) throws FrameworkException
	{
		enterAvalueIntoATextField(value, webElement, elementBeingTested);
	}
	
	public void testFindOptionInListAndSelectIt(List<WebElement> webElements, String webelementListHtml, String expectedOption, Boolean clickViaJQuery) throws FrameworkException
	{
		findOptionInListAndSelectIt(webElements, webelementListHtml, expectedOption, clickViaJQuery);
	}
	
	public void testClickSomeElement(String html, String byValue, String elementBeingTested) throws FrameworkException
	{
		clickSomeElement(html, byValue, elementBeingTested);
	}
	
	public int testGetTotalDaysInMonth() throws FrameworkException
	{
		return getTotalDaysInMonth();
	}
	
	public void testSelectSomeOptionFromNonDropdown(String option, String clickElement, String clickByValue, String searchElement, String searchByValue, String optionsElement, String optionsByValue, String elementBeingTested, Boolean clickViaJQuery) throws FrameworkException, InterruptedException
	{
		selectSomeOptionFromNonDropdown(option, clickElement, clickByValue, searchElement, searchByValue, optionsElement, optionsByValue, elementBeingTested, clickViaJQuery);
	}
	
	public void testSelectSomeOptionFromNonDropdown(String option, WebElement clickElement, WebElement searchElement, String optionsElement, String optionsByValue, String elementBeingTested, Boolean clickViaJQuery) throws FrameworkException, InterruptedException
	{
		selectSomeOptionFromNonDropdown(option, clickElement, searchElement, optionsElement, optionsByValue, elementBeingTested, clickViaJQuery);
	}
	
	public void testVerifySomeElementContainsTheExpectedText(String elementHtml, String byValue, String expectedText, String elementBeingTested, Boolean removeAllSpaces) throws FrameworkException
	{
		verifySomeElementContainsTheExpectedText(elementHtml, byValue, expectedText, elementBeingTested, removeAllSpaces);
	}
	
	public void testVerifySomeElementContainsTheExpectedText(WebElement element, String expectedText, String elementBeingTested, Boolean removeAllSpaces) throws FrameworkException
	{
		verifySomeElementContainsTheExpectedText(element, expectedText, elementBeingTested, removeAllSpaces);
	}

}
