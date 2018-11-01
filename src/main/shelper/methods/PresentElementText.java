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
import shelper.vobjects.TestElement;

public class PresentElementText extends Commands implements IWait {

	protected int time = 0;
	protected Condition condition;
	protected String value;

	public PresentElementText(WaitBuilder base) throws TestException {
		LocalWaitBuilder builder = new LocalWaitBuilder(base);
		this.time = builder.time;
		this.condition = builder.condition;
		this.value = builder.value;
	}

	@Override
	public void on(TestElement element) throws TestException {
		verifyValueIsNotNull(value);
		verifyMaxWaitTimeIsNotZero(time);
		switch (condition) {
			case EQUAL:
				waitForElementToEqualText(element, value, time);
				break;
			case CONTAIN:
				waitForElementToContainText(element, value, time);
				break;
			default:
				throw new TestException(
						"Please provide a valid condition. Unable to wait for text presence because condition has not been provided.");
		}
	}

	@Override
	public void on(WebElement element) throws TestException {
		verifyValueIsNotNull(value);
		verifyMaxWaitTimeIsNotZero(time);
		switch (condition) {
			case EQUAL:
				waitForElementToEqualText(element, value, time);
				break;
			case CONTAIN:
				waitForElementToContainText(element, value, time);
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
	 * @param i
	 *            the total amount of time allotted to
	 *            wait for the condition to return
	 *            true
	 * 
	 * @return void
	 */
	private void waitForElementToContainText(TestElement element, String expectedText, int i) {
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

		wait.until((WebDriver driver) -> {
			Boolean result = false;
			WebElement elementToBeTested = getElement(element);
			String actualText = elementToBeTested.getText();
			if (actualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
				result = true;
			}
			return result;
		});
	}

	/**
	 * <summary> method to wait for a particular text
	 * to be present in a web element </summary>
	 * @param element TODO
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
	private void waitForElementToEqualText(TestElement element, String expectedText, int i) {
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

		wait.until((WebDriver driver) -> {
			Boolean result = false;
			WebElement elementToBeTested = getElement(element);
			String actualText = elementToBeTested.getText();
			if (actualText.equalsIgnoreCase(expectedText.trim())) {
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
	private void waitForElementToContainText(WebElement element, String expectedText, int i) {
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

		wait.until((WebDriver driver) -> {
			Boolean result = false;
			WebElement elementToBeTested = element;
			String actualText = elementToBeTested.getText();
			if (actualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
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
	private void waitForElementToEqualText(WebElement element, String expectedText, int i) {
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

		wait.until((WebDriver driver) -> {
			Boolean result = false;
			WebElement elementToBeTested = element;
			String actualText = elementToBeTested.getText();
			if (actualText.equalsIgnoreCase(expectedText.trim())) {
				result = true;
			}
			return result;
		});
	}

	@Override
	public void on(List<WebElement> element) throws TestException {
		throw new UnsupportedOperationException();
	}

	public static class LocalWaitBuilder extends Commands {
		private int time;
		private Condition condition;
		private String value;

		public LocalWaitBuilder(WaitBuilder base) throws TestException {
			this.time = base.baseTime;
			this.condition = base.baseCondition();
			this.value = base.baseValue;
			failIfAttributeIsNotNull(base.baseAttribute);
			failIfExpectedCountIsNotZero(base.baseExpectedTotalCount);
		}

	}

}
