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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PresentAttributeText extends Commands implements IWait {

	protected Duration time;
	protected Condition condition;
	protected String value;
	protected String attribute;

	public PresentAttributeText(WaitBuilder build) throws TestException {
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
				waitForAttributeToEqualACertainValue(Arrays.stream(element).findFirst().get(), attribute, value);
				break;
			case CONTAIN:
				waitForAttributeToContainACertainValue(Arrays.stream(element).findFirst().get(), attribute, value);
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
				waitForAttributeToEqualACertainValue(element, attribute, value);
				break;
			case CONTAIN:
				waitForAttributeToContainACertainValue(element, attribute, value);
				break;
			default:
				throw new TestException(
						"Please select a valid condition. Unable to execute because condition is not valid.");
		}
	}

	/**
	 * <summary> method to wait for an attribute to
	 * equal a certain value </summary>
	 * 
	 * @param element
	 *            a webelement that is defined and
	 *            found in the calling method
	 * @param attribute
	 *            the html attribute whose value is to
	 *            be evaluated and obtained
	 * @param expectedValue
	 *            the expected value of the html
	 *            attribute
	 * @return void
	 */
	private void waitForAttributeToEqualACertainValue(WebElement element, String attribute, String expectedValue) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
			wait.until(ExpectedConditions.attributeToBe(element, attribute, expectedValue));
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for the element" +
					" to " + condition.name() +
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
	 * 
	 * @return void
	 */
	private void waitForAttributeToEqualACertainValue(TestElement element, String attribute, String expectedValue) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
			wait.until(ExpectedConditions.attributeToBe(getByValueBasedOnUserInput(element), attribute, expectedValue));
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for element with locator " + element.locator().value() +
					" to " + condition.name() +
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
	 * 
	 * @return void
	 */
	private void waitForAttributeToContainACertainValue(TestElement element, String attribute, String expectedValue) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
			wait.until(ExpectedConditions.attributeContains(getByValueBasedOnUserInput(element), attribute, expectedValue));
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for element with locator " + element.locator().value() +
					" to " + condition.name() +
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
	 * @return void
	 */
	private void waitForAttributeToContainACertainValue(WebElement element, String attribute, String expectedValue) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
			wait.until(ExpectedConditions.attributeContains(element, attribute, expectedValue));
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for the element" +
					" to " + condition.name() +
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
