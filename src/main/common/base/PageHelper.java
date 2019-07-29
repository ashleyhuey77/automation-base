package common.base;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.base.helpers.ClickHelper;
import common.base.helpers.EnterTextHelper;
import common.base.helpers.ClickHelper.ClickBuilder;
import common.base.helpers.EnterTextHelper.EnterTextBuilder;
import common.base.interfaces.DatePicker;
import common.base.methods.PresentDate;
import common.base.vobjects.ReportInfo;
import common.utils.TestUtils;
import common.utils.helpers.CookieHelper;
import common.utils.managers.*;
import log.Log;
import log.TestException;
import shelper.builders.WaitBuilder;
import shelper.enums.*;
import shelper.vobjects.By;
import shelper.vobjects.Locator;
import shelper.vobjects.TestElement;
import org.openqa.selenium.support.How;

/**
 * <h1>PageHelper</h1>
 * <p>
 * The abstract PageHelper class was created to
 * hold all methods that are shared between page
 * classes in order to reduce repetitive code so
 * that when a page extends the PageHelper class,
 * it has access to shared methods.
 * </p>
 * <p>
 * If code is used between two or more page
 * classes regardless of the method it is used in,
 * it should be consolidated into a shared void
 * method and called from the PageHelper class.
 * </p>
 * <p>
 * PageHelper methods should not return any page
 * class. It is acceptible for a PageHelper method
 * to return void or a data type object or a list,
 * but only in the event that the method is used
 * between multiple page classes.
 * </p>
 * 
 * @author ashleyhuey
 */
public abstract class PageHelper {

	protected Locator locator(String locator) {
		return new Locator(locator);
	}

	protected By by(String by) {
		return new By(by);
	}

	protected By by(How by) {
		return new By(by);
	}

	/**
	 * <p>
	 * The cssSelector identifier is used to specify
	 * that the locator string entered as a param for
	 * a particular method is written in cssSelector
	 * format.
	 * </p>
	 * <p>
	 * It is turned to string format from the
	 * IdentifyBy enum defined in the seleniumHelper
	 * package in order to prevent unknown selenium
	 * "By" types. Which in turn prevents unnecessary
	 * exceptions.
	 * </p>
	 * <p>
	 * Examples of cssSelectors in the correct format:
	 * </p>
	 * 1. {@code div[class='Test']} </br>
	 * 2. {@code div > div > p} </br>
	 * 3. {@code #TestId}
	 */
	protected static String cssSelector = How.CSS.toString();
	/**
	 * <p>
	 * The id identifier is used to specify that the
	 * locator string entered as a param for a
	 * particular method is written in the id
	 * attribute value format.
	 * </p>
	 * <p>
	 * It is turned to string format from the
	 * IdentifyBy enum defined in the seleniumHelper
	 * package in order to prevent unknown selenium
	 * "By" types. Which in turn prevents unnecessary
	 * exceptions.
	 * </p>
	 * <p>
	 * Examples of Ids in the correct format:
	 * </p>
	 * 1. {@code testId}
	 */
	/*-?|TestReview|ashleyhuey|c1|?Testing123*/
	protected static String id = How.ID.toString();
	/**
	 * <p>
	 * The name identifier is used to specify that the
	 * locator string entered as a param for a
	 * particular method is written in the name
	 * attribute value format.
	 * </p>
	 * <p>
	 * It is turned to string format from the
	 * IdentifyBy enum defined in the seleniumHelper
	 * package in order to prevent unknown selenium
	 * "By" types. Which in turn prevents unnecessary
	 * exceptions.
	 * </p>
	 * <p>
	 * Examples of names in the correct format:
	 * </p>
	 * 1. {@code testName}
	 */
	protected static String name = How.NAME.toString();
	/**
	 * <p>
	 * The xpath identifier is used to specify that
	 * the locator string entered as a param for a
	 * particular method is written in xpath format.
	 * </p>
	 * <p>
	 * It is turned to string format from the
	 * IdentifyBy enum defined in the seleniumHelper
	 * package in order to prevent unknown selenium
	 * "By" types. Which in turn prevents unnecessary
	 * exceptions.
	 * </p>
	 * <p>
	 * Examples of xpath in the correct format:
	 * </p>
	 * 1. {@code /bookstore/book[position()<3]} </br>
	 * 2. {@code //title[@lang]} </br>
	 * 3. {@code //title[@lang='en']}
	 */
	protected static String xpath = How.XPATH.toString();
	/**
	 * <p>
	 * The ClassName identifier is used to specify
	 * that the locator string entered as a param for
	 * a particular method is written in the Class
	 * attribute value format.
	 * </p>
	 * <p>
	 * It is turned to string format from the
	 * IdentifyBy enum defined in the seleniumHelper
	 * package in order to prevent unknown selenium
	 * "By" types. Which in turn prevents unnecessary
	 * exceptions.
	 * </p>
	 * <p>
	 * Examples of class names in the correct format:
	 * </p>
	 * 1. {@code testClass}
	 */
	protected static String className = How.CLASS_NAME.toString();
	/**
	 * <p>
	 * The TagName identifier is used to specify that
	 * the locator string entered as a param for a
	 * particular method is written in the html tag
	 * format.
	 * </p>
	 * <p>
	 * It is turned to string format from the
	 * IdentifyBy enum defined in the seleniumHelper
	 * package in order to prevent unknown selenium
	 * "By" types. Which in turn prevents unnecessary
	 * exceptions.
	 * </p>
	 * <p>
	 * Examples of tag names in the correct format:
	 * </p>
	 * 1. {@code p} </br>
	 * 2. {@code button} </br>
	 * 3. {@code select}
	 */
	protected static String tagName = How.TAG_NAME.toString();
	/**
	 * <p>
	 * The LinkText identifier is used to specify that
	 * the locator string entered as a param for a
	 * particular method is written in the Link text
	 * value format.
	 * </p>
	 * <p>
	 * It is turned to string format from the
	 * IdentifyBy enum defined in the seleniumHelper
	 * package in order to prevent unknown selenium
	 * "By" types. Which in turn prevents unnecessary
	 * exceptions.
	 * </p>
	 * <p>
	 * Examples of link text in the correct format:
	 * </p>
	 * 1. {@code Link to Page}
	 */
	protected static String linkText = How.LINK_TEXT.toString();
	/**
	 * <p>
	 * The PartialLinkText identifier is used to
	 * specify that the locator string entered as a
	 * param for a particular method is written in a
	 * portion of the Link text value format.
	 * </p>
	 * <p>
	 * It is turned to string format from the
	 * IdentifyBy enum defined in the seleniumHelper
	 * package in order to prevent unknown selenium
	 * "By" types. Which in turn prevents unnecessary
	 * exceptions.
	 * </p>
	 * <p>
	 * Examples of partial link text in the correct
	 * format:
	 * </p>
	 * 1. {@code Link to}
	 */
	protected static String partialLinkText = How.PARTIAL_LINK_TEXT.toString();

	/**
	 * <h1>PageHelper</h1>
	 * <p>
	 * The abstract PageHelper class was created to
	 * hold all void methods that are shared between
	 * page classes in order to reduce repetitive code
	 * so that when a page extends the PageHelper
	 * class, it has access to shared methods.
	 * </p>
	 * <p>
	 * If code is used between two or more page
	 * classes regardless of the method it is used in,
	 * it should be consolidated into a shared void
	 * method and called from the PageHelper class.
	 * </p>
	 * <p>
	 * PageHelper methods should not return any page
	 * class. It is acceptible for a PageHelper method
	 * to return void or a data type object or a list,
	 * but only in the event that the method is used
	 * between multiple page classes.
	 * </p>
	 * 
	 * @author ashleyhuey
	 */
	public PageHelper() throws TestException {

	}

	/**
	 * <p>
	 * Method to verify some element is present on the
	 * page using the SHelper isDisplayed method.
	 * </p>
	 * <p>
	 * If the element is not displayed on te page, the
	 * test fails.
	 * </p>
	 * @param element TODO
	 * @param elementBeingTested
	 *            - the name of the element being
	 *            tested. This is used for reporting
	 *            so that when it is called the report
	 *            will reflect an element that is
	 *            unique to the method
	 * 
	 * @throws TestException 
	 */
	protected void verifySomeElementIsPresent(TestElement element, String elementBeingTested) throws TestException {
		try {
			if (SHelper.get().element().isDisplayed(element, 10)) {
				LocalValidation.getValidations()
						.assertionPass(elementBeingTested + " displays in the page as expected.");
			} else {
				throw LocalValidation.getValidations().assertionFailed(
						elementBeingTested + " should display in the page. It does not display as expected.");
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}

	/**
	 * <p>
	 * Method to verify some element is not present on
	 * the page using the SHelper isDisplayed method.
	 * </p>
	 * <p>
	 * If the element is displayed on te page, the
	 * test fails.
	 * </p>
	 * @param element TODO
	 * @param elementBeingTested
	 *            - the name of the element being
	 *            tested. This is used for reporting
	 *            so that when it is called the report
	 *            will reflect an element that is
	 *            unique to the method
	 * 
	 * @throws TestException 
	 */
	protected void verifySomeElementIsNotPresent(TestElement element, String elementBeingTested) throws TestException {
		try {
			if (!SHelper.get().element().isDisplayed(element, 5)) {
				LocalValidation.getValidations().assertionPass(elementBeingTested + " does not display in the page.");
			} else {
				throw LocalValidation.getValidations().assertionFailed(
						elementBeingTested + " should not display in the page. It does display. This is not expected.");
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}

	/**
	 * <p>
	 * Method to get the current date and split it
	 * into a string array
	 * </p>
	 * 
	 * @return String[]
	 */
	protected String[] getCurrentSplitDate() throws TestException {
		String[] currentDate = null;
		DatePicker picker = new PresentDate();
		String date = picker.getDate("dd-MM-yyyy");
		currentDate = date.split("-");
		return currentDate;
	}

	/**
	 * <p>
	 * Method to get the total days in the current
	 * month of the current year - taking into account
	 * leap year
	 * </p>
	 */

	protected int getTotalDaysInMonth() throws TestException {
		int totalDaysInMonth = 0;
		int currentYear = Integer.parseInt(getCurrentSplitDate()[2]);
		int currentDay = Integer.parseInt(getCurrentSplitDate()[0]);
		int currentMonth = Integer.parseInt(getCurrentSplitDate()[1]);

		Calendar cal = new GregorianCalendar(currentYear, currentMonth - 1, currentDay);

		totalDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return totalDaysInMonth;
	}

	/**
	 * <p>
	 * Method to get the future date based on user
	 * input of how many days they wish to select from
	 * today's date
	 * </p>
	 * 
	 * @param daysOutFromCurrentDay
	 *            - the number of days out from today
	 *            to select
	 * @return int
	 */

	protected int getFutureDate(int daysOutFromCurrentDay) throws TestException {
		int futureDate = 0;
		int currentDay = Integer.parseInt(getCurrentSplitDate()[0]);
		futureDate = currentDay + daysOutFromCurrentDay;
		return futureDate;
	}

	/**
	 * <p>
	 * Method to clean up static global variables.
	 * </p>
	 * 
	 * @param value
	 *            - the static global value that needs
	 *            to be cleaned up. Is set to null if
	 *            it is not already null or empty
	 */
	protected void cleanUp(String value) {
		if (!TestUtils.isNullOrBlank(value)) {
			value = null;
		}
	}

	/**
	 * <p>
	 * Method to evaluate whether a variable is null
	 * and fail the test case if it is null. Used
	 * primarily in for loops to assure the loop is
	 * executed properly and fails at the right place
	 * if not.
	 * </p>
	 * 
	 * @param variable
	 *            - the string variable being
	 *            evaluated
	 * @param passMessage
	 *            - the message for reporting if the
	 *            test passes
	 * @param failMessage
	 *            - the message for reporting if the
	 *            test fails
	 * @throws TestException 
	 */
	protected void failTestIfVariableIsNull(String variable, String passMessage, String failMessage) throws TestException {
		if (variable != null) {
			LocalValidation.getValidations().assertionPass(passMessage);
		} else {
			throw LocalValidation.getValidations().assertionFailed(failMessage);
		}
	}

	/**
	 * <p>
	 * Method to remove an unwanted character or to
	 * replace it with a different character if it is
	 * present in the String.
	 * </p>
	 * 
	 * @param variable
	 *            - the string variable that is to be
	 *            modified
	 * @param character
	 *            - the character or string that is
	 *            unwanted that will be removed or
	 *            replaced if present in the variable
	 * @param replaceValue
	 *            - the value to replace the
	 *            character. Leave as empty string to
	 *            remove character completely.
	 * @return String
	 * @throws TestException 
	 */
	protected String removeOrChangeUnwantedCharacter(String variable, String character, String replaceValue)
			throws TestException {
		String result = null;
		try {
			if (variable.contains(character)) {
				result = variable.replace(character, replaceValue);
			} else {
				result = variable;
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
		return result;
	}

	/**
	 * <p>
	 * Method to verify an expected value matches an
	 * actual value.
	 * </p>
	 * <p>
	 * Method is primarily used to compare api actual
	 * values with expected values, but can also be
	 * used for other comparisons.
	 * </p>
	 * <p>
	 * Method first evaluates whether the expected
	 * value and/or the actual value is null or an
	 * empty string. If one of them are null or empty
	 * the test is failed. Then the comparison is
	 * made.
	 * </p>
	 * <p>
	 * The text must exactly equal the expected option
	 * text (letter casing is not a factor), but
	 * spelling and in some cases spacing could cause
	 * the option to not match.
	 * </p>
	 * 
	 * @param expectedValue
	 *            - the value that is expected to have
	 *            returned in the api response
	 * @param actualValue
	 *            - the actual value that returned in
	 *            the api response
	 * @param variableBeingTested
	 *            - the name of the variable being
	 *            tested. This is used for reporting
	 *            so that when it is called the report
	 *            will reflect wording that is unique
	 *            to the variable it is testing.
	 * @throws TestException 
	 */
	protected void verifyTheActualValueMatchesTheExpectedValue(String expectedValue, String actualValue,
			String variableBeingTested) throws TestException {
		try {
			if (!TestUtils.isNullOrBlank(expectedValue) && !TestUtils.isNullOrBlank(actualValue)) {
				if (actualValue.toLowerCase().trim().equals(expectedValue.toLowerCase().trim())) {
					LocalValidation.getValidations()
							.assertionPass(actualValue + " is set correctly in " + variableBeingTested);
				} else {
					throw LocalValidation.getValidations().assertionFailed(
							expectedValue + " is not set as expected. " + actualValue + " is set instead.");
				}
			} else {
				throw LocalValidation.getValidations().assertionFailed(variableBeingTested
						+ " returned null in either the actual or the expected variable that was set. "
						+ "Check the test to verify all variables are being assigned a value appropriately. Actual: "
						+ actualValue + ". Expected: " + expectedValue);
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}

	/**
	 * <p>
	 * Method to add i number of days to a date
	 * </p>
	 * 
	 * @param date
	 *            - the date that needs to be
	 *            subtracted from
	 * @param totalDays
	 *            - the total number of days in the
	 *            month
	 * @param daysToAdd
	 *            - the number of days to add to the
	 *            date
	 * @return String
	 * @throws TestException 
	 */
	protected String subtractDays(String date, int totalDays, int daysToAdd) throws TestException {
		String addedDay = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
			Date myDate = format.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(myDate);
			int days = totalDays - daysToAdd - 1;
			cal.add(Calendar.DATE, days);
			myDate = cal.getTime();
			String dummyDate = format.format(myDate);
			addedDay = dummyDate.startsWith("0") ? dummyDate.substring(1) : dummyDate;
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
		return addedDay;
	}

	/**
	 * <p>
	 * Method to verify an expected value contains an
	 * actual value.
	 * </p>
	 * <p>
	 * Method is primarily used to compare api actual
	 * values with expected values, but can also be
	 * used for other comparisons.
	 * </p>
	 * <p>
	 * Method first evaluates whether the expected
	 * value and/or the actual value is null or an
	 * empty string. If one of them are null or empty
	 * the test is failed. Then the comparison is
	 * made.
	 * </p>
	 * <p>
	 * The text must only contain the expected text
	 * (letter casing is not a factor).
	 * </p>
	 * 
	 * @param expectedValue
	 *            - the value that is expected to have
	 *            returned in the api response
	 * @param actualValue
	 *            - the actual value that returned in
	 *            the api response
	 * @param variableBeingTested
	 *            - the name of the variable being
	 *            tested. This is used for reporting
	 *            so that when it is called the report
	 *            will reflect wording that is unique
	 *            to the variable it is testing.
	 * @throws TestException 
	 */
	protected void verifyTheActualValueContainsTheExpectedValue(String expectedValue, String actualValue,
			String variableBeingTested) throws TestException {
		try {
			if (!TestUtils.isNullOrBlank(expectedValue) && !TestUtils.isNullOrBlank(actualValue)) {
				if (actualValue.toLowerCase().trim().contains(expectedValue.toLowerCase().trim()))

				{
					LocalValidation.getValidations()
							.assertionPass(actualValue + " is set correctly in " + variableBeingTested);
				} else {
					throw LocalValidation.getValidations().assertionFailed(
							expectedValue + " is not set as expected. " + actualValue + " is set instead.");
				}
			} else {
				if (TestUtils.isNullOrBlank(expectedValue)) {
					throw LocalValidation.getValidations().assertionFailed(
							variableBeingTested + " returned null. Variable was not expected to return null.");
				} else if (TestUtils.isNullOrBlank(actualValue)) {
					throw LocalValidation.getValidations().assertionFailed(variableBeingTested
							+ " returned null in the actual variable that was set. Check the test to verify all "
							+ "variables are being assigned a value appropriately.");
				}
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}

	/**
	 * <p>
	 * This method refreshes the page and waits for an
	 * element to appear on the page. The wait is
	 * explicit and will wait up to the specified
	 * amount of time for the condition to be met (i.e
	 * the element to be displayed after the page is
	 * refreshed). If the element is not found in the
	 * specified amount of time, the test will fail.
	 * If the element is found sooner than the
	 * specified time, the test will not longer wait
	 * and will continue executing the next method in
	 * the chain.
	 * </p>
	 * @param i
	 *            - the total amount of time the test
	 *            should wait for the element to be
	 *            found.
	 * @param locatorString
	 *            - the webelement locator string
	 *            necessary for the webelement to be
	 *            found
	 */
	public void refreshPageAndWaitForElementToDisplay(TestElement element, int i) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

			wait.until((WebDriver driver) -> {
				Boolean result = false;
				try {
					if (LocalTest.getEnvironment().isHeadlessEnabled()) {
						SHelper.get().element().isDisplayed(BaseGeneric.LOG_IN_BOX.element(), 2);
						checkCookiesAndAddRequiredOnesIfNecessary(true);
						SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, new WaitBuilder().forAMaxTimeOf(7))
								.on(BaseGeneric.LOG_IN_BOX.element());
					} else {
						SHelper.get().page().refresh();
					}
					SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(15)).on(element);
					if (SHelper.get().element().isDisplayed(SHelper.get().element().get(element), 1)) {
						result = true;
					} else {
						result = false;
					}
				} catch (Exception e) {
					result = false;
				}
				return result;
			});
		} catch (Exception e) {
			throw LocalReport.getReport().reportException(e);
		}
	}

	/**
	 * <p>
	 * This method refreshes the page and waits for an
	 * element to appear on the page. The wait is
	 * explicit and will wait up to the specified
	 * amount of time for the condition to be met (i.e
	 * the element to be displayed after the page is
	 * refreshed). If the element is not found in the
	 * specified amount of time, the test will fail.
	 * If the element is found sooner than the
	 * specified time, the test will not longer wait
	 * and will continue executing the next method in
	 * the chain.
	 * </p>
	 * 
	 * @param locatorString
	 *            - the webelement locator string
	 *            necessary for the webelement to be
	 *            found
	 * @param by
	 *            - the type of locator being used
	 *            (i.e id, name, csslocator, xpath,
	 *            etc.). Necessary for the WebElement
	 *            to be found
	 * @param i
	 *            - the total amount of time the test
	 *            should wait for the element to be
	 *            found.
	 */
	public void refreshPageAndWaitForElementToDisplay(WebElement element, int i) {
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

		wait.until((WebDriver driver) -> {
			Boolean result = false;
			try {
				SHelper.get().page().refresh();
				SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(15)).on(element);
				if (SHelper.get().element().isDisplayed(element, 10)) {
					result = true;
				} else {
					result = false;
				}
			} catch (Exception e) {
				result = false;
			}
			return result;
		});
	}

	/**
	 * <p>
	 * Method to extract digits from strings
	 * </p>
	 * 
	 * @return String
	 * @throws TestException 
	 */
	protected String getNumbersFromString(TestElement element) throws TestException {
		String z = null;
		try {
			z = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(element);
			return z.replaceAll("[^0-9]", "");
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}

	/**
	 * <p>
	 * Method to extract digits from strings
	 * </p>
	 * 
	 * @param element
	 *            - the element from which the digits
	 *            need to be extracted.
	 * @return String
	 * @throws TestException 
	 */
	protected String getNumbersFromString(WebElement element) throws TestException {
		try {
			String z = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(element);
			return z.replaceAll("[^0-9]", "");
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}
	
	protected void handleSignInModal() throws TestException {
		try {
			LocalValidation.getValidations().assertionPass("User is able to sign in successfully.");
			if (SHelper.get().element().isDisplayed(BaseGeneric.EA_SIGNIN_BOX.element(), 10)) {
				String pwd = LocalTest.getCredentials().getNewstronPWord();
				new EnterTextHelper(new EnterTextBuilder(new ReportInfo(BaseGeneric.ANYWHERE_PWD_TEXT_FIELD.name())).enterText(pwd)
						.into(BaseGeneric.ANYWHERE_PWD_TEXT_FIELD.element()));
				new ClickHelper(new ClickBuilder(new ReportInfo(BaseGeneric.ANYWHERE_SIGN_IN_BTN.name()))
						.clickOn(BaseGeneric.ANYWHERE_SIGN_IN_BTN.element()));
				LocalValidation.getValidations().assertionPass("User is able to sign in successfully.");
				SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, new WaitBuilder().forAMaxTimeOf(10)).on(BaseGeneric.EA_SIGNIN_BOX.element());
				CookieManager.setCookies(LocalDriver.getDriver().manage().getCookies());
			}
		} catch (Exception e) {
			throw LocalReport.getReport().reportException(e);
		}
	}
	
	protected void checkCookiesAndAddRequiredOnesIfNecessary(Boolean refreshPageAfter) throws TestException {
		try {
			if (LocalTest.getEnvironment().isHeadlessEnabled()) {
    			if (SHelper.get().element().isDisplayed(BaseGeneric.CORE_APPS_TOGGLE.element(), 1)) {
    			}
    			CookieHelper.newHelper().getCookies().setCookies().build();
			}
			if (refreshPageAfter) {
				SHelper.get().page().refresh();
			}
		} catch (Exception e) {
			Log.get().log(Level.INFO, "An error occurred. Cookies were not created.");
		}
	}

}