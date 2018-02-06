package testPages;

import org.openqa.selenium.WebElement;
import common.basePage.PageTemplate;
import common.basePage.enums.DaysOfTheWeek;
import common.basePage.enums.Entity;
import common.basePage.enums.Timezones;
import common.basePage.helpers.ClickHelper;
import common.basePage.helpers.DropdownHelper;
import common.basePage.helpers.EnterTextHelper;
import common.basePage.helpers.EnterTextHelper.EnterTextBuilder;
import common.basePage.helpers.VerifyTextHelper;
import common.basePage.helpers.VerifyTextHelper.VerifyTextBuilder;
import common.basePage.helpers.DropdownHelper.DropdownBuilder;
import common.basePage.helpers.ClickHelper.ClickBuilder;
import common.basePage.valueObjects.ReportInfo;
import seleniumHelper.enums.Via;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;
import seleniumHelper.valueObjects.TestElement;


public class TestPage extends PageTemplate {

	public TestPage() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void WaitForPageLoad() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public String someRandomString(int length, String value) throws Exception
	{
		String someValue = null;
		someValue = randomStringAtSetLength(length, value);
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
	
	public void testEnterAValueIntoATextField(String value, Locator locator, By by, String elementBeingTested) throws Exception
	{
		new EnterTextHelper(new EnterTextBuilder(new ReportInfo(elementBeingTested))
				.enterText(value)
				.into(new TestElement(locator, by)));
	}
	
	public void testEnterAValueIntoATextField(String value, WebElement webElement, String elementBeingTested) throws Exception
	{
		new EnterTextHelper(new EnterTextBuilder(new ReportInfo(elementBeingTested))
				.enterText(value)
				.into(webElement));
	}
	
	public void testClickSomeElement(Locator locator, By by, String elementBeingTested) throws Exception
	{
		//clickSomeElement(Via.SELENIUM, locator, by, elementBeingTested);
		new ClickHelper(new ClickBuilder(new ReportInfo(elementBeingTested)).clickOn(new TestElement(locator, by)).via(Via.SELENIUM));
	}
	
	public int testGetTotalDaysInMonth() throws Exception
	{
		return getTotalDaysInMonth();
	}
	
	public void testVerifySomeElementContainsTheExpectedText(WebElement element, String expectedText, String elementBeingTested, Boolean removeAllSpaces) throws Exception
	{
		new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo(elementBeingTested))
				.verify(element)
				.contains(expectedText)
				.removeAllSpaces(removeAllSpaces));
	}
	
	public void testverifySomeElementIsNotPresent(Locator locator, By by, String elementBeingTested) throws Exception
	{
		verifySomeElementIsNotPresent(locator, by, elementBeingTested);
	}
	
	public void testVerifySomeElementIsPresent(Locator locator, By by, String elementBeingTested) throws Exception
	{
		verifySomeElementIsPresent(locator, by, elementBeingTested);
	}
	
	public void testVerifyTextFieldIsBlank(Locator locator, By by, Boolean requiresIndex, String webElementIndex, String elementBeingTested) throws Exception
	{
		//verifyTextFieldIsBlank(locator, by, webElementIndex, elementBeingTested);
	}
	
	public void testVerifyTextInTextField(Locator locator, By by, String webElementIndex, String expectedText, String elementBeingTested, Boolean removeAllSpaces) throws Exception
	{
		new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo(elementBeingTested))
				.verify(new TestElement(locator, by))
				.contains(expectedText)
				.via(Via.JAVASCRIPT)
				.withIndexOf(webElementIndex)
				.removeAllSpaces(removeAllSpaces));
	}
	
	public void testDropDownHelper() throws Exception {
		new DropdownHelper(new DropdownBuilder(new ReportInfo("Drop Down"))
				.clickMenuToOpen(new TestElement(locator("someLocator"), by(id)))
				.searchForOption(new TestElement(locator("searchLocator"), by(id)))
				.selectOption("Option to select")
				.fromOptionList(new TestElement(locator("optionListSelector"), by(xpath))));
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
