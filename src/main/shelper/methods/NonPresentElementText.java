package shelper.methods;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.utils.managers.LocalDriver;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.builders.WaitBuilder;
import shelper.enums.Condition;
import shelper.interfaces.IWait;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

public class NonPresentElementText extends Commands implements IWait {

	protected int time = 0;
	protected Condition condition;
	protected String value;
	protected int expectedTotalCount = 0;
	protected String attribute;

	public NonPresentElementText(WaitBuilder build) throws TestException {
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
		this.condition = builder.condition;
		this.value = builder.value;
	}

	@Override
	public void on(Locator locator, By by) throws TestException {
		verifyValueIsNotNull(value);
		verifyMaxWaitTimeIsNotZero(time);
		switch (condition) {
			case EQUAL:
				waitForElementToNoLongerEqualText(locator, by, value, time);
				break;
			case CONTAIN:
				waitForElementToNoLongerContainText(locator, by, value, time);
				break;
			default:
				throw new TestException(
						"Please select a valid condition. Unable to execute because condition is not valid.");
		}
	}

	@Override
	public void on(WebElement element) throws TestException {
		verifyValueIsNotNull(value);
		verifyMaxWaitTimeIsNotZero(time);
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

	@Override
	public void on(List<WebElement> element) throws TestException {
		// unused as of now
		throw new UnsupportedOperationException();
	}

	/**
	 * <summary> method to wait for a particular text
	 * to be present in a web element </summary>
	 * 
	 * @param locator
	 *            the webelement selector string
	 *            necessary for the webelement to be
	 *            found
	 * @param by
	 *            the type of selector being used (i.e
	 *            id, name, cssSelector, xpath, etc.).
	 *            Necessary for the WebElement to be
	 *            found
	 * @param expectedText
	 *            the text that is expected to be in
	 *            the webelement
	 * @param i
	 *            the total amount of time allotted to
	 *            wait for the condition to return
	 *            true
	 * @return void
	 */
	private void waitForElementToNoLongerContainText(Locator locator, By by, String expectedText, int i) {
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

		wait.until((WebDriver driver) -> {
			Boolean result = false;
			WebElement elementToBeTested = getElement(locator, by);
			String actualText = elementToBeTested.getText();
			if (!actualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
				result = true;
			}
			return result;
		});
	}

	/**
	 * <summary> method to wait for a particular text
	 * to be present in a web element </summary>
	 * 
	 * @param locator
	 *            the webelement selector string
	 *            necessary for the webelement to be
	 *            found
	 * @param by
	 *            the type of selector being used (i.e
	 *            id, name, cssSelector, xpath, etc.).
	 *            Necessary for the WebElement to be
	 *            found
	 * @param expectedText
	 *            the text that is expected to be in
	 *            the webelement
	 * @param i
	 *            the total amount of time allotted to
	 *            wait for the condition to return
	 *            true
	 * @return void
	 */
	private void waitForElementToNoLongerEqualText(Locator locator, By by, String expectedText, int i) {
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

		wait.until((WebDriver driver) -> {
			Boolean result = false;
			WebElement elementToBeTested = getElement(locator, by);
			String actualText = elementToBeTested.getText();
			if (!actualText.toLowerCase().trim().equals(expectedText.toLowerCase().trim())) {
				result = true;
			}
			return result;
		});
	}

	/**
	 * <summary> method to wait for a particular text
	 * to be present in a web element </summary>
	 * 
	 * @param locator
	 *            the webelement selector string
	 *            necessary for the webelement to be
	 *            found
	 * @param by
	 *            the type of selector being used (i.e
	 *            id, name, cssSelector, xpath, etc.).
	 *            Necessary for the WebElement to be
	 *            found
	 * @param expectedText
	 *            the text that is expected to be in
	 *            the webelement
	 * @param i
	 *            the total amount of time allotted to
	 *            wait for the condition to return
	 *            true
	 * @return void
	 */
	private void waitForElementToNoLongerContainText(WebElement element, String expectedText, int i) {
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
	}

	/**
	 * <summary> method to wait for a particular text
	 * to be present in a web element </summary>
	 * 
	 * @param locator
	 *            the webelement selector string
	 *            necessary for the webelement to be
	 *            found
	 * @param by
	 *            the type of selector being used (i.e
	 *            id, name, cssSelector, xpath, etc.).
	 *            Necessary for the WebElement to be
	 *            found
	 * @param expectedText
	 *            the text that is expected to be in
	 *            the webelement
	 * @param i
	 *            the total amount of time allotted to
	 *            wait for the condition to return
	 *            true
	 * @return void
	 */
	private void waitForElementToNoLongerEqualText(WebElement element, String expectedText, int i) {
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
	}

	public static class LocalWaitBuilder extends Commands {
		private int time;
		private Condition condition;
		private String value;

		public LocalWaitBuilder(WaitBuilder base) throws TestException {
			this.time = base.baseTime;
			this.condition = base.baseCondition();
			this.value = base.baseValue;
			failIfExpectedCountIsNotZero(base.baseExpectedTotalCount);
			failIfAttributeIsNotNull(base.baseAttribute);
		}

	}

}
