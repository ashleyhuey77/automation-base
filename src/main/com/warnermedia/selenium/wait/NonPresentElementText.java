package com.warnermedia.selenium.wait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.warnermedia.utils.ex.ErrorCode;
import com.warnermedia.utils.ex.SeleniumException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Commands;
import com.warnermedia.utils.Validator;

public class NonPresentElementText extends Commands implements IWait {

	protected Duration time;
	protected Condition condition;
	protected String value;
	protected String indexOf;

	public NonPresentElementText(WaitBuilder build) throws TestException {
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
		this.condition = builder.condition;
		this.value = builder.value;
		this.indexOf = builder.indexOf;
	}

	@Override
	public void on(TestElement... element) throws TestException {
		switch (condition) {
			case EQUAL:
				if (this.indexOf != null) {
					waitForListElementToNoLongerEqualText(Arrays.stream(element).findFirst().get());
				} else {
					waitForElementToNoLongerEqualText(Arrays.stream(element).findFirst().get(), value, time);
				}
				break;
			case CONTAIN:
				if (this.indexOf != null) {
					waitForListElementToNoLongerContainText(Arrays.stream(element).findFirst().get());
				} else {
					waitForElementToNoLongerContainText(Arrays.stream(element).findFirst().get(), value, time);
				}
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
				waitForElementToNoLongerEqualText(element, value, time);
				break;
			case CONTAIN:
				waitForElementToNoLongerContainText(element, value, time);
				break;
			default:
				throw new TestException(
						"Please select a valid condition. Unable to execute because condition is not valid.");
		}
	}
	
	private void waitForListElementToNoLongerEqualText(TestElement element) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), this.time);

			wait.until((WebDriver driver) -> {
				Boolean result = false;
				try {
					List<WebElement> elementList = SHelper.get().element().getListOf(element);
					int intIndex = Integer.parseInt(this.indexOf);
					WebElement elementToBeTested = elementList.get(intIndex);
					String actualText = elementToBeTested.getText();
					if (!actualText.equalsIgnoreCase(this.value.trim())) {
						result = true;
					}
				} catch (Exception e) {
					return result;
				}
				return result;
			});
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + String.valueOf(time.getSeconds()) + " seconds for element with locator " + element.locator().value() +
					" to no longer " + condition.name()
					+ " " + value, ErrorCode.WAIT);
		}
	}
	
	private void waitForListElementToNoLongerContainText(TestElement element) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), this.time);

			wait.until((WebDriver driver) -> {
				Boolean result = false;
				try {
					List<WebElement> elementList = SHelper.get().element().getListOf(element);
					int intIndex = Integer.parseInt(this.indexOf);
					WebElement elementToBeTested = elementList.get(intIndex);
					String actualText = elementToBeTested.getText();
					if (!actualText.contains(this.value.trim())) {
						result = true;
					}
				} catch (Exception e) {
					return result;
				}
				return result;
			});
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + String.valueOf(time.getSeconds()) + " seconds for element with locator " + element.locator().value() +
					" to no longer " + condition.name()
					+ " " + value, ErrorCode.WAIT);
		}
	}

	/**
	 * <summary> method to wait for a particular text
	 * to be present in a web element </summary>
	 * @param element
	 * @param expectedText
	 *            the text that is expected to be in
	 *            the webelement
	 * @param i
	 *            the total amount of time allotted to
	 *            wait for the condition to return
	 *            true
	 * 
	 * @return void
	 */
	private void waitForElementToNoLongerContainText(TestElement element, String expectedText, Duration i) throws TestException {
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
				String actualText = elementToBeTested.getText();
				if (!actualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
					result = true;
				}
				return result;
			});
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + String.valueOf(time.getSeconds()) + " seconds for element with locator " + element.locator().value() +
					" to no longer " + condition.name()
					+ " " + value, ErrorCode.WAIT);
		}
	}

	/**
	 * <summary> method to wait for a particular text
	 * to be present in a web element </summary>
	 * @param element
	 * @param expectedText
	 *            the text that is expected to be in
	 *            the webelement
	 * @param i
	 *            the total amount of time allotted to
	 *            wait for the condition to return
	 *            true
	 * 
	 * @return void
	 */
	private void waitForElementToNoLongerEqualText(TestElement element, String expectedText, Duration i) throws TestException {
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
				String actualText = elementToBeTested.getText();
				if (!actualText.toLowerCase().trim().equals(expectedText.toLowerCase().trim())) {
					result = true;
				}
				return result;
			});
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + String.valueOf(time.getSeconds()) + " seconds for element with locator " + element.locator().value() +
					" to no longer " + condition.name()
					+ " " + value, ErrorCode.WAIT);
		}
	}

	/**
	 * <summary> method to wait for a particular text
	 * to be present in a web element </summary>

	 * @param expectedText
	 *            the text that is expected to be in
	 *            the webelement
	 * @param i
	 *            the total amount of time allotted to
	 *            wait for the condition to return
	 *            true
	 * @return void
	 */
	private void waitForElementToNoLongerContainText(WebElement element, String expectedText, Duration i) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

			wait.until((WebDriver driver) -> {
				Boolean result = false;
				WebElement elementToBeTested = element;
				String actualText = elementToBeTested.getText();
				if (!actualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
					result = true;
				}
				return result;
			});
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + String.valueOf(time.getSeconds()) + " seconds for the element" +
					" to no longer " + condition.name()
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
	 * @param i
	 *            the total amount of time allotted to
	 *            wait for the condition to return
	 *            true
	 * @return void
	 */
	private void waitForElementToNoLongerEqualText(WebElement element, String expectedText, Duration i) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

			wait.until((WebDriver driver) -> {
				Boolean result = false;
				WebElement elementToBeTested = element;
				String actualText = elementToBeTested.getText();
				if (!actualText.toLowerCase().trim().equals(expectedText.toLowerCase().trim())) {
					result = true;
				}
				return result;
			});
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + String.valueOf(time.getSeconds()) + " seconds for an element" +
					" to no longer " + condition.name()
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
