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

public class PresentAttributeText extends Commands implements IWait {

	protected int time = 0;
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
		verifyAttributeIsNotNull(attribute);
		verifyMaxWaitTimeIsNotZero(time);
		verifyValueIsNotNull(value);
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
		verifyAttributeIsNotNull(attribute);
		verifyMaxWaitTimeIsNotZero(time);
		verifyValueIsNotNull(value);
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
	public void on(List<WebElement> element) throws TestException {
		throw new UnsupportedOperationException();
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
			int i) {
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
	 * @param element TODO
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
	private void waitForAttributeToEqualACertainValue(TestElement element, String attribute, String expectedValue, int i) {
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
	 * @param element TODO
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
	private void waitForAttributeToContainACertainValue(TestElement element, String attribute, String expectedValue, int i) {
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

		wait.until((WebDriver driver) -> {
			Boolean result = false;
			WebElement elementToBeTested = getElement(element);
			String actualValue = elementToBeTested.getAttribute(attribute);
			if (actualValue != null && actualValue.trim().toLowerCase().contains(expectedValue.toLowerCase().trim())) {
				result = true;
			}
			return result;
		});
	}

	/**
	 * <summary> method to wait for an attribute to
	 * equal a certain value </summary>
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
			int i) {
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
		private int time;
		private Condition condition;
		private String value;
		private String attribute;

		public LocalWaitBuilder(WaitBuilder base) throws TestException {
			this.time = base.baseTime;
			this.condition = base.baseCondition();
			this.value = base.baseValue;
			this.attribute = base.baseAttribute;
			failIfExpectedCountIsNotZero(base.baseExpectedTotalCount);
		}

	}

}
