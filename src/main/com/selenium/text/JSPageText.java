package com.selenium.text;

import com.config.TestException;
import com.config.setup.browser.LocalDriver;
import com.selenium.Locator;
import com.selenium.TestElement;
import com.selenium.shared.Commands;
import com.utils.TestUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.google.errorprone.annotations.DoNotCall;

public class JSPageText extends Commands implements IText {

	private String getValueViaJavascriptIndexNoIndex(String javascriptStartTxt, TestElement element, String index,
													 Boolean requiresIndex) {
		String textBoxText = null;
		if (requiresIndex) {
			textBoxText = (String) ((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript(javascriptStartTxt + element.locator().value() + "')[" + index + "].value");
		} else {
			textBoxText = (String) ((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript(javascriptStartTxt + element.locator().value() + "').value");
		}
		return textBoxText;
	}

	/**
	 * <summary> Method to get text from a textbox via
	 * javascript based on webelement type </summary>
	 * @param element TODO
	 * @param index
	 *            the index used to identify a
	 *            particular element in a list of
	 *            elements tat contain more than one
	 *            element with a similar selector
	 * @param requiresIndex
	 *            boolean value to toggle whether the
	 *            index is necessary. True uses an
	 *            index and index cannot be blank or
	 *            null. False does not use an index
	 *            and index can be blank or null.
	 * 
	 * @return
	 */
	private String getTxtBoxValueViaJavascriptScript(TestElement element, String index, Boolean requiresIndex) {
		String textBoxText = null;
		switch (element.by().value()) {
			case ID:
				textBoxText = getValueViaJavascriptIndexNoIndex("return document.getElementById('", element, index,
						requiresIndex);
				break;
			case CLASS_NAME:
				textBoxText = getValueViaJavascriptIndexNoIndex("return document.getElementsByClassName('", element,
						index, requiresIndex);
				break;
			case NAME:
				textBoxText = getValueViaJavascriptIndexNoIndex("return document.getElementsByName('", element, index,
						requiresIndex);
				break;
			case TAG_NAME:
				textBoxText = getValueViaJavascriptIndexNoIndex("return document.getElementsByTagName('", element,
						index, requiresIndex);
				break;
			case CSS:
				textBoxText = getValueViaJavascriptIndexNoIndex("return document.querySelector('", element, index,
						requiresIndex);
				break;
			default:
				break;
		}
		return textBoxText;
	}

	@Override
	public String getFrom(TestElement element, String... attribute) throws TestException {
		if (element.locator().value().contains("'")) {
			 element.setLocator(new Locator(element.locator().value().replace("'", "")));
		} 
		Boolean requiresIndex = false;
		int count = attribute.length;
		String attr = null;
		if (count > 0) {
			if (!TestUtils.isNullOrBlank(attribute[0])) {
				requiresIndex = true;
				attr = attribute[0];
			} else {
				attr = "";
			}
		} else {
			attr = "";
		}

		return getTxtBoxValueViaJavascriptScript(element, attr, requiresIndex);
	}

	@Override
	public String getFrom(WebElement element, String... attribute) throws TestException {
		return (String) ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("return arguments[0].value",
				element);
	}

	@Override
	@DoNotCall
	public Boolean isDisplayed(TestElement element, String expectedText, String... attribute) throws TestException {
		return null;
	}

	@Override
	@DoNotCall
	public Boolean isDisplayed(WebElement element, String expectedText, String... attribute) throws TestException {
		return null;
	}

}