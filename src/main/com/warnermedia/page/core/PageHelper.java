package com.warnermedia.page.core;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.logging.Level;

import com.warnermedia.config.data.UserHelper;
import com.warnermedia.utils.ConsoleDecoration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.utils.CredentialsType;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.report.LocalValidation;
import com.warnermedia.config.settings.LocalTest;

import com.warnermedia.page.core.web.BaseGeneric;
import com.warnermedia.page.utils.date.DatePicker;
import com.warnermedia.page.utils.date.PresentDate;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Via;
import com.warnermedia.selenium.text.Variable;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;
import com.warnermedia.utils.CookieHelper;
import com.warnermedia.utils.CookieManager;
import com.warnermedia.utils.TestUtils;
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
@Slf4j
public abstract class PageHelper extends PageUtils {

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
			if (SHelper.get().element().isDisplayed(element, Duration.ofSeconds(10))) {
				LocalValidation.getValidations()
						.assertionPass(log, elementBeingTested + " displays in the page as expected.");
			} else {
				throw LocalValidation.getValidations().assertionFailed(log,
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
			if (!SHelper.get().element().isDisplayed(element, Duration.ofSeconds(5))) {
				LocalValidation.getValidations().assertionPass(log, elementBeingTested + " does not display in the page.");
			} else {
				throw LocalValidation.getValidations().assertionFailed(log,
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
			LocalValidation.getValidations().assertionPass(log, passMessage);
		} else {
			throw LocalValidation.getValidations().assertionFailed(log, failMessage);
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
							.assertionPass(log, actualValue + " is set correctly in " + variableBeingTested);
				} else {
					throw LocalValidation.getValidations().assertionFailed(
							log, expectedValue + " is not set as expected. " + actualValue + " is set instead.");
				}
			} else {
				throw LocalValidation.getValidations().assertionFailed(log, variableBeingTested
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
							.assertionPass(log, actualValue + " is set correctly in " + variableBeingTested);
				} else {
					throw LocalValidation.getValidations().assertionFailed(
							log, expectedValue + " is not set as expected. " + actualValue + " is set instead.");
				}
			} else {
				if (TestUtils.isNullOrBlank(expectedValue)) {
					throw LocalValidation.getValidations().assertionFailed(
							log, variableBeingTested + " returned null. Variable was not expected to return null.");
				} else if (TestUtils.isNullOrBlank(actualValue)) {
					throw LocalValidation.getValidations().assertionFailed(log, variableBeingTested
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
	 */
	public void refreshPageAndWaitForElementToDisplay(TestElement element, Duration i) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

			wait.until((WebDriver driver) -> {
				Boolean result = false;
				try {
					if (LocalTest.getEnvironment().isHeadlessEnabled()) {
						SHelper.get().element().isDisplayed(BaseGeneric.LOG_IN_BOX.element(), Duration.ofSeconds(2));
						checkCookiesAndAddRequiredOnesIfNecessary(true);
						SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(7)))
								.on(BaseGeneric.LOG_IN_BOX.element());
					} else {
						SHelper.get().page().refresh();
					}
					SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(15))).on(element);
					if (SHelper.get().element().isDisplayed(SHelper.get().element().get(element), Duration.ofSeconds(3))) {
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
	 * @param i
	 *            - the total amount of time the test
	 *            should wait for the element to be
	 *            found.
	 */
	public void refreshPageAndWaitForElementToDisplay(WebElement element, Duration i) {
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

		wait.until((WebDriver driver) -> {
			Boolean result = false;
			try {
				SHelper.get().page().refresh();
				SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(15))).on(element);
				if (SHelper.get().element().isDisplayed(element, Duration.ofSeconds(10))) {
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
			LocalValidation.getValidations().assertionPass(log, "User is able to sign in successfully.");
			if (SHelper.get().element().isDisplayed(BaseGeneric.EA_SIGNIN_BOX.element(),Duration.ofSeconds(10))) {
				enter().text(new String(UserHelper.getPassword(CredentialsType.BASE))).into(BaseGeneric.ANYWHERE_PWD_TEXT_FIELD).start();
				click().on(BaseGeneric.ANYWHERE_SIGN_IN_BTN).start();
				LocalValidation.getValidations().assertionPass(log, "User is able to sign in successfully.");
				SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(10))).on(BaseGeneric.EA_SIGNIN_BOX.element());
				CookieManager.setCookies(LocalDriver.getDriver().manage().getCookies());
			}
		} catch (Exception e) {
			throw LocalReport.getReport().reportException(e);
		}
	}
	
	protected void checkCookiesAndAddRequiredOnesIfNecessary(Boolean refreshPageAfter) throws TestException {
		try {
			if (LocalTest.getEnvironment().isHeadlessEnabled()) {
    			if (SHelper.get().element().isDisplayed(BaseGeneric.CORE_APPS_TOGGLE.element(), Duration.ofSeconds(1))) {
    			}
    			CookieHelper.newHelper().getCookies().setCookies().build();
			}
			if (refreshPageAfter) {
				SHelper.get().page().refresh();
			}
		} catch (Exception e) {
			log.info("{}{}An error occurred. Cookies were not created.{}", ConsoleDecoration.RED_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, ConsoleDecoration.RESET.value);
		}
	}
	
	protected Boolean isAlertPresent(Duration waitTime) throws TestException {
		Boolean foundAlert = false;
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), waitTime);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		} catch (Exception e) {
			foundAlert = false;
		}
		return foundAlert;
	}

}