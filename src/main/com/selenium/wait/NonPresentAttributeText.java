package com.selenium.wait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;

import com.config.TestException;
import com.config.setup.browser.LocalDriver;
import com.selenium.TestElement;
import com.selenium.shared.Commands;
import com.utils.Validator;
import com.utils.ex.ErrorCode;
import com.utils.ex.SeleniumException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NonPresentAttributeText extends Commands implements IWait {

	protected Duration time;
	protected Condition condition;
	protected String value;
	protected String attribute;

	public NonPresentAttributeText(WaitBuilder build) throws TestException {
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
		this.condition = builder.condition;
		this.value = builder.value;
		this.attribute = builder.attribute;
	}

	@Override
	public void on(TestElement... element) throws TestException {
		switch (condition) {
			case EQUAL:
				waitForAttributeToNoLongerEqualACertainValue(Arrays.stream(element).findFirst().get(), attribute, value, time);
				break;
			case CONTAIN:
				waitForAttributeToNoLongerContainACertainValue(Arrays.stream(element).findFirst().get(), attribute, value, time);
				break;
			default:
				throw new TestException(
						"Please select a valid condition. Unable to execute because condition is not valid.");
		}
	}

	@Override
	public void on(WebElement element) throws TestException {
		switch (condition) {
			case EQUAL:
				waitForAttributeToNoLongerEqualACertainValue(element, attribute, value, time);
				break;
			case CONTAIN:
				waitForAttributeToNoLongerContainACertainValue(element, attribute, value, time);
				break;
			default:
				throw new TestException(
						"Please select a valid condition. Unable to execute because condition is not valid.");
		}
	}

	/**
	 * <summary> method to wait for an attribute to
	 * equal a certain value </summary>
	 * @param element
	 * @param attribute
	 *            the html attribute whose value is to
	 *            be evaluated and obtained
	 * @param expectedValue
	 *            the expected value of the html
	 *            attribute
	 * @param i
	 *            the total amount of time allotted to
	 *            wait for the condition to return
	 *            true
	 * 
	 * @return void
	 */
	private void waitForAttributeToNoLongerContainACertainValue(TestElement element, String attribute, String expectedValue,
			Duration i) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

			wait.until((WebDriver driver) -> {
				Boolean result = false;
				WebElement elementToBeTested = null;
				try {
					elementToBeTested = getElement(element);
				} catch (TestException e) {
					return false;
				}
				String actualValue = elementToBeTested.getAttribute(attribute);
			/* returning true if attribute is null because it
			still means the attribute does not contain the
			desired value.*/
				if (actualValue == null
						|| !actualValue.trim().toLowerCase().contains(expectedValue.toLowerCase().trim())) {
					result = true;
				}
				return result;
			});
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for element with locator " + element.locator().value() +
					" to no longer " + condition.name() +
					" an attribute named " + attribute
					+ " with value " + expectedValue, ErrorCode.WAIT);
		}
	}

	/**
	 * <summary> method to wait for an attribute to
	 * equal a certain value </summary>
	 *
	 * @param attribute
	 *            the html attribute whose value is to
	 *            be evaluated and obtained
	 * @param expectedValue
	 *            the expected value of the html
	 *            attribute
	 * @param i
	 *            the total amount of time allotted to
	 *            wait for the condition to return
	 *            true
	 * @return void
	 */
	private void waitForAttributeToNoLongerContainACertainValue(WebElement element, String attribute,
			String expectedValue, Duration i) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

			wait.until((WebDriver driver) -> {
				Boolean result = false;
				WebElement elementToBeTested = element;
				String actualValue = elementToBeTested.getAttribute(attribute);
			/* returning true if attribute is null because it
			still means the attribute does not contain the
			desired value.*/
				if (actualValue == null
						|| !actualValue.trim().toLowerCase().contains(expectedValue.toLowerCase().trim())) {
					result = true;
				}
				return result;
			});
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for and element" +
					" to no longer " + condition.name() +
					" an attribute named " + attribute
					+ " with value " + expectedValue, ErrorCode.WAIT);
		}
	}

	/**
	 * <summary> method to wait for an attribute to
	 * equal a certain value </summary>
	 * @param element
	 * @param attribute
	 *            the html attribute whose value is to
	 *            be evaluated and obtained
	 * @param expectedValue
	 *            the expected value of the html
	 *            attribute
	 * @param i
	 *            the total amount of time allotted to
	 *            wait for the condition to return
	 *            true
	 * 
	 * @return void
	 */
	private void waitForAttributeToNoLongerEqualACertainValue(TestElement element, String attribute, String expectedValue,
			Duration i) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

			wait.until((WebDriver driver) -> {
				Boolean result = false;
				WebElement elementToBeTested = null;
				try {
					elementToBeTested = getElement(element);
				} catch (TestException e) {
					return false;
				}
				String actualValue = elementToBeTested.getAttribute(attribute);
			/* returning true if attribute is null because it
			   still means the attribute does not contain the
			   desired value.*/
				if (actualValue == null || !actualValue.equalsIgnoreCase(expectedValue)) {
					result = true;
				}
				return result;
			});
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for element with locator " + element.locator().value() +
					" to no longer " + condition.name() +
					" an attribute named " + attribute
					+ " with value " + expectedValue, ErrorCode.WAIT);
		}
	}

	/**
	 * <summary> method to wait for an attribute to
	 * equal a certain value </summary>
	 * @param attribute
	 *            the html attribute whose value is to
	 *            be evaluated and obtained
	 * @param expectedValue
	 *            the expected value of the html
	 *            attribute
	 * @param i
	 *            the total amount of time allotted to
	 *            wait for the condition to return
	 *            true
	 * @return void
	 */
	private void waitForAttributeToNoLongerEqualACertainValue(WebElement element, String attribute,
			String expectedValue, Duration i) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

			wait.until((WebDriver driver) -> {
				Boolean result = false;
				WebElement elementToBeTested = element;
				String actualValue = elementToBeTested.getAttribute(attribute);
			/* returning true if attribute is null because it
			still means the attribute does not contain the
			desired value.*/
				if (actualValue != null) {
					if (!actualValue.equalsIgnoreCase(expectedValue)) {
						result = true;
					}
				} else {
					result = true;
				}
				return result;
			});
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for an element" +
					" to no longer " + condition.name() +
					" an attribute named " + attribute
					+ " with value " + expectedValue, ErrorCode.WAIT);
		}
	}

	public static class LocalWaitBuilder extends Commands {
		private Duration time;
		private Condition condition;
		private String value;
		private String attribute;

		public LocalWaitBuilder(WaitBuilder base) throws TestException {
			this.time = base.baseTime;
			this.condition = base.baseCondition();
			this.value = base.baseValue;
			this.attribute = base.baseAttribute;
			Validator.of(base.baseAttribute).validate(Objects::nonNull, result -> base.baseAttribute != null, "Attribute is null. Add the 'forAttribute' method.")
											.validate(String::valueOf, result -> !result.isEmpty(), "Attribute is empty. Add a value to the 'forAttribute' method.").get();
			//Validator.of(base.baseTime).validate(String::valueOf, result -> !result.equals("0"), "Time is null. Add the 'forAMaxTimeOf' method.").get();
			Validator.of(base.baseValue).validate(Objects::nonNull, result -> base.baseValue != null, "Value is null. Add the 'value' method.")
										.validate(String::valueOf, result -> !result.isEmpty(), "Value is empty. Add a value to the 'value' method.").get();
			Validator.of(base.baseExpectedTotalCount).validate(String::valueOf, result -> result.equals("0"), "Expected total count is not null. Remove the 'withACountOf' method.").get();
		}

	}

}
