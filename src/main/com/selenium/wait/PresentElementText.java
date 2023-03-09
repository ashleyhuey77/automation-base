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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PresentElementText extends Commands implements IWait {

	protected Duration time = Duration.ofMinutes(0);
	protected Condition condition;
	protected String value;
	protected String indexOf;

	public PresentElementText(WaitBuilder base) throws TestException {
		LocalWaitBuilder builder = new LocalWaitBuilder(base);
		this.time = builder.time;
		this.condition = builder.condition;
		this.value = builder.value;
		this.indexOf = builder.indexOf;
	}

	@Override
	public void on(TestElement... element) throws TestException {
		switch (condition) {
			case EQUAL:
				waitForElementToEqualText(Arrays.stream(element).findFirst().get(), value);
				break;
			case CONTAIN:
				waitForElementToContainText(Arrays.stream(element).findFirst().get(), value);
				break;
			default:
				throw new TestException(
						"Please provide a valid condition. Unable to wait for text presence because condition has not been provided.");
		}
	}

	@Override
	public void on(WebElement element) throws TestException {
		switch (condition) {
			case EQUAL:
				waitForElementToEqualText(element, value);
				break;
			case CONTAIN:
				waitForElementToContainText(element, value);
				break;
			default:
				throw new TestException(
						"Please provide a valid condition. Unable to wait for text presence because condition has not been provided.");
		}
	}

	/**
	 * <summary> method to wait for a particular text
	 * to be present in a web element </summary>
	 * @param element TODO
	 * @param expectedText
	 *            the text that is expected to be in
	 *            the webelement
	 * 
	 * @return void
	 */
	private void waitForElementToContainText(TestElement element, String expectedText) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
			wait.until(ExpectedConditions.textToBePresentInElement(getElement(element), expectedText));
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for element with locator " + element.locator().value() +
					" to " + condition.name()
					+ " " + value, ErrorCode.WAIT);
		}
	}

	/**
	 * <summary> method to wait for a particular text
	 * to be present in a web element </summary>
	 * @param element TODO
	 * @param expectedText
	 *            the text that is expected to be in
	 *            the webelement
	 * @return void
	 */
	private void waitForElementToEqualText(TestElement element, String expectedText) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
			wait.until(ExpectedConditions.textToBe(getByValueBasedOnUserInput(element), expectedText));
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for element with locator " + element.locator().value() +
					" to " + condition.name()
					+ " " + value, ErrorCode.WAIT);
		}
	}

	/**
	 * <summary> method to wait for a particular text
	 * to be present in a web element </summary>
	 *
	 * @param expectedText
	 *            the text that is expected to be in
	 *            the webelement
	 * @return void
	 */
	private void waitForElementToContainText(WebElement element, String expectedText) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
			wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for the element" +
					" to " + condition.name()
					+ " " + value, ErrorCode.WAIT);
		}
	}

	/**
	 * <summary> method to wait for a particular text
	 * to be present in a web element </summary>
	 * @param expectedText
	 *            the text that is expected to be in
	 *            the webelement
	 * @return void
	 */
	private void waitForElementToEqualText(WebElement element, String expectedText) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
			ExpectedCondition<Boolean> cond = new ExpectedCondition<Boolean>() {
				private String currentValue = null;

				@Override
				public Boolean apply(WebDriver driver) {
					try {
						currentValue = element.getText();
						return currentValue.equals(expectedText);
					} catch (Exception e) {
						return false;
					}
				}
			};
			wait.until(cond);
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for the element" +
					" to " + condition.name()
					+ " " + value, ErrorCode.WAIT);
		}
	}

	public static class LocalWaitBuilder extends Commands {
		private Duration time;
		private Condition condition;
		private String value;
		private String indexOf;

		public LocalWaitBuilder(WaitBuilder base) throws TestException {
			this.time = base.baseTime;
			this.condition = base.baseCondition();
			this.value = base.baseValue;
			this.indexOf = base.indexOf;
			//Validator.of(base.baseTime).validate(String::valueOf, result -> !result.equals("0"), "Time is null. Add the 'forAMaxTimeOf' method.").get();
			Validator.of(base.baseValue).validate(Objects::nonNull, result -> base.baseValue != null, "Value is null. Add the 'value' method.")
										.validate(String::valueOf, result -> !result.isEmpty(), "Value is empty. Add a value to the 'value' method.").get();
			Validator.of(base.baseExpectedTotalCount).validate(String::valueOf, result -> result.equals("0"), "Expected total count is not null. Remove the 'withACountOf' method.").get();
			Validator.of(base.baseAttribute).validate(Objects::nonNull, result -> base.baseAttribute == null, "Attribute is not null. Remove the 'forAttribute' method.").get();
		}

	}

}
