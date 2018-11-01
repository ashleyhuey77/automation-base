package shelper.abstracts;

import java.util.List;
import org.openqa.selenium.WebElement;
import common.utils.TestUtils;
import common.utils.managers.LocalDriver;
import log.TestException;
import shelper.enums.Condition;
import shelper.vobjects.TestElement;

public class Commands {

	/**
	 * <summary> method to get the by value based on
	 * user input </summary>
	 * 
	 * @return By
	 * @param selectorString
	 *            the webelement selector string
	 *            necessary for the webelement to be
	 *            found
	 * @param by
	 *            the type of selector being used (i.e
	 *            id, name, cssSelector, xpath, etc.).
	 *            Necessary for the WebElement to be
	 *            found
	 */
	public org.openqa.selenium.By getByValue(TestElement element) {
		switch (element.by().value()) {
			case ID:
				return org.openqa.selenium.By.id(element.locator().value());
			case CSS:
				return org.openqa.selenium.By.cssSelector(element.locator().value());
			case XPATH:
				return org.openqa.selenium.By.xpath(element.locator().value());
			case CLASS_NAME:
				return org.openqa.selenium.By.className(element.locator().value());
			case TAG_NAME:
				return org.openqa.selenium.By.tagName(element.locator().value());
			case LINK_TEXT:
				return org.openqa.selenium.By.linkText(element.locator().value());
			case PARTIAL_LINK_TEXT:
				return org.openqa.selenium.By.partialLinkText(element.locator().value());
			case NAME:
				return org.openqa.selenium.By.name(element.locator().value());
			default:
				return null;

		}
	}

	/**
	 * <summary> Method to call the by switch
	 * statement and return the correct by value based
	 * on user input </summary>
	 * @param element TODO
	 * @param selectorString
	 *            the webelement selector string
	 *            necessary for the webelement to be
	 *            found
	 * 
	 * @return By
	 */
	protected org.openqa.selenium.By getByValueBasedOnUserInput(TestElement element) {
		return getByValue(element);
	}

	/**
	 * <summary> Element that is being defined based
	 * on the html string parameter and the By value
	 * that indicates which type of element is being
	 * defined </summary>
	 * @param element TODO
	 * @param selectorString
	 *            the webelement selector string
	 *            necessary for the webelement to be
	 *            found
	 * 
	 * @return WebElement
	 */
	public WebElement getElement(TestElement element) {
		return LocalDriver.getDriver().findElement(getByValueBasedOnUserInput(element));
	}

	/**
	 * <summary> method to return a list of
	 * webElements </summary>
	 * @param element TODO
	 * @param selectorString
	 *            the webelement selector string
	 *            necessary for the webelement to be
	 *            found
	 * 
	 * @return List<\WebElement>
	 */
	public List<WebElement> getElements(TestElement element) {
		return LocalDriver.getDriver().findElements(getByValueBasedOnUserInput(element));
	}

	protected void verifyMaxWaitTimeIsNotZero(int time) throws TestException {
		if (time == 0) {
			throw new TestException("A max wait time was not provided. When waiting, a max wait time must be provided.");
		}
	}

	protected void verifyExpectedCountIsNotZero(int expectedTotalCount) throws TestException {
		if (expectedTotalCount == 0) {
			throw new TestException("An expected total count was not provided. When waiting for the total expected count "
					+ "to be a certain number, that expected count must be provided.");
		}
	}

	protected void verifyValueIsNotNull(String value) throws TestException {
		if (TestUtils.isNullOrBlank(value)) {
			throw new TestException(
					"The text value was not provided. If waiting on a text value to be present, that text value must be provided.");
		}
	}

	protected void verifyAttributeIsNotNull(String attribute) throws TestException {
		if (TestUtils.isNullOrBlank(attribute)) {
			throw new TestException(
					"The attribute was not provided. If waiting on an attribute value to be present, that attribute value must be provided.");
		}
	}

	protected void failIfValueIsNotNull(String value) throws TestException {
		if (!TestUtils.isNullOrBlank(value)) {
			throw new TestException("This method's Wait enum is set to a value that does not contain any text validation. "
					+ "Please check the Wait enum and verify it is set to the correct value or remove the value() method from the wait chain.");
		}
	}

	protected void failIfConditionIsNotNull(Condition condition) throws TestException {
		if (condition != null) {
			throw new TestException(
					"This method's Wait enum is set to a value that does not contain any condition validation. "
							+ "Please check the Wait enum and verify it is set to the correct value or remove the to() method from the wait chain.");
		}
	}

	protected void failIfExpectedCountIsNotZero(int expectedTotalCount) throws TestException {
		if (expectedTotalCount != 0) {
			throw new TestException(
					"This method's Wait enum is set to a value that does not require an expected count validation. "
							+ "Please check the Wait enum and verify it is set to the correct value or remove the withACountOf() method from the wait chain.");
		}
	}

	protected void failIfAttributeIsNotNull(String attribute) throws TestException {
		if (!TestUtils.isNullOrBlank(attribute)) {
			throw new TestException(
					"This method's Wait enum is set to a value that does not require an attribute validation. "
							+ "Please check the Wait enum and verify it is set to the correct value or remove the forAttribute() method from the wait chain.");
		}
	}
}