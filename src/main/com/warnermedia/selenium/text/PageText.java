package com.warnermedia.selenium.text;

import org.openqa.selenium.WebElement;
import com.warnermedia.config.TestException;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Commands;
import com.warnermedia.utils.StateManager;

public class PageText extends Commands implements IText {
	@Override
	public String getFrom(TestElement element, String... attribute) throws TestException {
		return getElement(element).getText().trim();
	}

	@Override
	public String getFrom(WebElement element, String... attribute) throws TestException {
		return element.getText().trim();
	}

	@Override
	public Boolean isDisplayed(TestElement element, String expectedText, String... attribute) throws TestException {
		Boolean result = false;
		String actualText = getElement(element).getText();
		String modifiedActualText = actualText.replace("\r\n", " ");
		if (modifiedActualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
			result = true;
		}
		return result;
	}

	@Override
	public Boolean isDisplayed(WebElement element, String expectedText, String... attribute) throws TestException {
		Boolean result = false;
		String actualText = element.getText();
		String modifiedActualText = actualText.replace("\r\n", " ");
		if (modifiedActualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
			result = true;
		}
		return result;
	}

}