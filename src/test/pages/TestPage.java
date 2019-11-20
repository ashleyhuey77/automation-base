package pages;

import org.openqa.selenium.WebElement;
import com.warnermedia.config.TestException;
import com.warnermedia.page.core.DaysOfTheWeek;
import com.warnermedia.page.core.Entity;
import com.warnermedia.page.core.PageTemplate;
import com.warnermedia.page.core.Timezones;
import com.warnermedia.page.utils.ReportInfo;
import com.warnermedia.page.utils.VerifyTextHelper;
import com.warnermedia.page.utils.VerifyTextHelper.VerifyTextBuilder;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;


public class TestPage extends PageTemplate {

	public TestPage() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void WaitForPageLoad() throws TestException {
		// TODO Auto-generated method stub
		
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
		System.out.println(Entity.CRASH_RECORDS);
		System.out.println(Entity.valueOf(Entity.CRASH_RECORDS.toString()));
		System.out.println(Entity.MS2);
		System.out.println(Entity.valueOf(Entity.MS2.toString()));
		System.out.println(Entity.REQUESTS);
		System.out.println(Entity.valueOf(Entity.REQUESTS.toString()));
		System.out.println(Entity.SCHEDULER);
		System.out.println(Entity.valueOf(Entity.SCHEDULER.toString()));
		
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
	
	public int testGetFutureDate(int daysOutFromCurrentDay) throws Exception
	{
		return getFutureDate(daysOutFromCurrentDay);
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
		TestElement element = new TestElement(locator, by);
		verifySomeElementIsNotPresent(element, elementBeingTested);
	}
	
	public void testVerifySomeElementIsPresent(Locator locator, By by, String elementBeingTested) throws Exception
	{
		TestElement element = new TestElement(locator(locator.value()), by(by.value()));
		verifySomeElementIsPresent(element, elementBeingTested);
	}
	
	public void testCookiesThing() throws Exception {
		checkCookiesAndAddRequiredOnesIfNecessary(true);
		checkCookiesAndAddRequiredOnesIfNecessary(false);
	}
	
	public void testRefreshPageAndWaitForElementToDisplay(TestElement element, int i) throws Exception {
		refreshPageAndWaitForElementToDisplay(element, i);
	}
	
	public void testRefreshPageAndWaitForElementToDisplay(WebElement element, int i) throws Exception {
		refreshPageAndWaitForElementToDisplay(element, i);
	}
	
	public String testGetNumbersFromString(TestElement element) throws Exception {
		return getNumbersFromString(element);
	}
	
	public String testGetNumbersFromString(WebElement element) throws Exception {
		return getNumbersFromString(element);
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
