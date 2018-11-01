package shelper.methods;

import org.openqa.selenium.WebElement;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.interfaces.IText;
import shelper.vobjects.TestElement;

public class AttributeText extends Commands implements IText {

	/**
	 * <p>
	 * This is a test to see how this works.
	 * </p>
	 * @param attribute
	 *            - test
	 */
	@Override
	public String getFrom(TestElement element, String... attribute) throws TestException {
		return getElement(element).getAttribute(attribute[0]);
	}

	@Override
	public String getFrom(WebElement element, String... attribute) throws TestException {
		return element.getAttribute(attribute[0]);
	}

	@Override
	public Boolean isDisplayed(TestElement element, String expectedText, String... attribute) throws TestException {
		Boolean result = false;
		String value = getElement(element).getAttribute(attribute[0]);
		if (value != null && value.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
			result = true;
		}
		return result;
	}

	@Override
	public Boolean isDisplayed(WebElement element, String expectedText, String... attribute) throws TestException {
		Boolean result = false;
		String value = element.getAttribute(attribute[0]);
		if (value != null && value.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
			result = true;
		}
		return result;
	}

}