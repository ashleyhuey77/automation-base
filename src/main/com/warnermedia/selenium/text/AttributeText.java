package com.warnermedia.selenium.text;

import org.openqa.selenium.WebElement;
import com.warnermedia.config.TestException;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Commands;

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