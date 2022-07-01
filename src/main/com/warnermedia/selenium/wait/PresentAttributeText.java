package com.warnermedia.selenium.wait;

import java.time.Duration;
import java.util.Objects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Commands;
import com.warnermedia.utils.Validator;

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
	public void on(TestElement element) throws TestException {
		switch (condition) {
			case EQUAL:
				waitForAttributeToEqualACertainValue(element, attribute, value, time);
				break;
			case CONTAIN:
				waitForAttributeToContainACertainValue(element, attribute, value, time);
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
				waitForAttributeToEqualACertainValue(element, attribute, value, time);
				break;
			case CONTAIN:
				waitForAttributeToContainACertainValue(element, attribute, value, time);
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
	 * @param i
	 *            the total amount of time allotted to
	 *            wait for the condition to return
	 *            true
	 * @return void
	 */
	private void waitForAttributeToEqualACertainValue(WebElement element, String attribute, String expectedValue,
			Duration i) {
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

		wait.until((WebDriver driver) -> {
			Boolean result = false;
			try {
				WebElement elementToBeTested = element;
				String actualValue = elementToBeTested.getAttribute(attribute);
				if (actualValue.toLowerCase().trim().equals(expectedValue.toLowerCase().trim())) {
					result = true;
				}
			} catch (Exception e) {
				result = false;
			}
			return result;
		});
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
	private void waitForAttributeToEqualACertainValue(TestElement element, String attribute, String expectedValue, Duration i) {
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

		wait.until((WebDriver driver) -> {
			Boolean result = false;
			try {
				WebElement elementToBeTested = getElement(element);
				String actualValue = elementToBeTested.getAttribute(attribute);
				if (actualValue.toLowerCase().trim().equals(expectedValue.toLowerCase().trim())) {
					result = true;
				}
			} catch (Exception e) {
				result = false;
			}
			return result;
		});
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
	private void waitForAttributeToContainACertainValue(TestElement element, String attribute, String expectedValue, Duration i) throws TestException {
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
				if (actualValue != null && actualValue.trim().toLowerCase().contains(expectedValue.toLowerCase().trim())) {
					result = true;
				}
				return result;
			});
		} catch (Exception e) {
			throw e;
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
	private void waitForAttributeToContainACertainValue(WebElement element, String attribute, String expectedValue,
			Duration i) {
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

		wait.until((WebDriver driver) -> {
			Boolean result = false;
			WebElement elementToBeTested = element;
			String actualValue = elementToBeTested.getAttribute(attribute);
			if (actualValue != null && actualValue.trim().toLowerCase().contains(expectedValue.toLowerCase().trim())) {
				result = true;
			}
			return result;
		});
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
