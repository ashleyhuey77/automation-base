package com.warnermedia.page.utils;

import java.util.Objects;

import com.warnermedia.page.core.PageUtils;
import com.warnermedia.page.core.web.Fetch;
import com.warnermedia.page.core.web.Type;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.report.LocalValidation;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Via;
import com.warnermedia.utils.TestUtils;
import com.warnermedia.utils.Validator;

public class EnterTextHelper extends PageUtils {

	// required params
	private Fetch info;

	// optional params
	private Type type;
	private WebElement webElement;
	private String value;

	/**
	 * <p> A shared generic helper used to enter text into an
	 * element on the page.
	 * </p>
	 * <p> This helper waits for the text box element
	 * to display on the page before attempting to
	 * enter text into it. If the element is not found
	 * the test is failed.
	 * </p>
	 * <p> If the value param is null or an
	 * empty string, then no value will be entered
	 * into the text field and the test will continue
	 * without entering anything.
	 * </p>
	 * <pre>
	 * Ex: 
	 * {@code new EnterTextHelper(new EnterTextHelper(new ReportInfo("Test Element"))
	 *	.enterText("Test123")
	 *	.into(Generic.ELEMENT.element()));}
	 * </pre>
	 * @throws TestException
	 */
	public EnterTextHelper() throws TestException {
		info = new Fetch();
	}

	private void enterTextIntoWebElement() throws TestException {
		try {
			if (SHelper.get().element().isDisplayed(webElement, 5)) {
				SHelper.get().click(Via.SELENIUM).on(webElement);
				clearAllTextByBackspacing(webElement);

				if (!TestUtils.isNullOrBlank(value)) {
					SHelper.get().enter(Via.SELENIUM).clear(webElement);
					SHelper.get().click(Via.SELENIUM).on(webElement);
					SHelper.get().enter(Via.SELENIUM).textInto(webElement, value);
				}
				LocalReport.getReport().reportDoneEvent(info.name(type) + " has been entered successfully");
			} else {
				throw LocalValidation.getValidations().assertionFailed(info.name(type)
						+ " does not display as expected. Unable to enter text in this field.");
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}
	
	private void enterTextIntoTestElement() throws TestException {
		try {
			if (SHelper.get().element().isDisplayed(type.element(), 5)) {
				click().on(type).start();
				clearAllTextByBackspacing(type.element());
				if (!TestUtils.isNullOrBlank(value)) {
					SHelper.get().enter(Via.SELENIUM).clear(type.element());
					click().on(type).start();
					SHelper.get().enter(Via.SELENIUM).textInto(type.element(), value);
				}
				LocalReport.getReport().reportDoneEvent(info.name(type) + " has been entered successfully");
			} else {
				throw LocalValidation.getValidations().assertionFailed(info.name(type)
						+ " does not display as expected. Unable to enter text in this field.");
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}

	/**
	 * <p> Method to clear all text in a field by
	 * selecting all text and selecting the backspace
	 * key on the keyboard.
	 * </p>
	 * 
	 * @param element
	 *            - a webelement that is defined and
	 *            found in the calling method
	 * @throws TestException
	 */
	private void clearAllTextByBackspacing(WebElement element) throws TestException {
		SHelper.get().enter(Via.SELENIUM).textInto(element, Keys.CONTROL + "a");
		SHelper.get().enter(Via.SELENIUM).textInto(element, Keys.BACK_SPACE);
	}

	/**
	 * <p> Method to clear all text in a field by
	 * selecting all text and selecting the backspace
	 * key on the keyboard.
	 * </p>
	 *
	 * @throws TestException
	 */
	private void clearAllTextByBackspacing(TestElement element) throws TestException {
		SHelper.get().enter(Via.SELENIUM).textInto(element, Keys.CONTROL + "a");
		SHelper.get().enter(Via.SELENIUM).textInto(element, Keys.BACK_SPACE);
	}

	/**
	 * <p> Tells the EnterTextHelper which value to enter into an element.
	 * </p>
	 * <p> This is not a required method. If a value is not provided, 
	 * then nothing will be entered into the text field and the 
	 * test will continue executing.
	 * </p>
	 * <pre>
	 * Ex: 
	 * {@code new EnterTextHelper(new EnterTextHelper(new ReportInfo("Test Element"))
	 *	.enterText("Test123")
	 *	.into(Generic.ELEMENT.element()));}
	 * </pre>
	 * @param value - the text value to be entered into the element
	 */
	public EnterTextHelper text(String value) {
		if (TestUtils.isNullOrBlank(value)) {
			this.value = "";
		} else {
			this.value = value;
		}
		return this;
	}

	/**
	 * <p> Tells the EnterTextHelper which TestElement to enter
	 * text into.
	 * </p>
	 * <p> This is a required method. If a TestElement or a WebElement is not
	 * given, then EnterTextHelper will throw an error and request for the
	 * into() method to be added.
	 * </p>
	 * <pre>
	 * Ex:
	 * {@code new EnterTextHelper(new EnterTextHelper(new ReportInfo("Test Element"))
	 *	.enterText("Test123")
	 *	.into(Generic.ELEMENT.element()));}
	 * </pre>
	 */
	public EnterTextHelper into(Type element) {
		this.type = element;
		return this;
	}

	/**
	 * <p> Tells the EnterTextHelper which TestElement to enter
	 * text into.
	 * </p>
	 * <p> This is a required method. If a TestElement or a WebElement is not 
	 * given, then EnterTextHelper will throw an error and request for the 
	 * into() method to be added.
	 * </p>
	 * <pre>
	 * Ex: 
	 * {@code WebElement field = SHelper.get().element().get(Generic.ELEMENT.element())
	 * new EnterTextHelper(new EnterTextHelper(new ReportInfo("Test Element"))
	 *	.enterText("Test123")
	 *	.into(field));}
	 * </pre>
	 */
	public EnterTextHelper using(WebElement element) {
		this.webElement = element;
		return this;
	}

	public EnterTextHelper start() throws TestException {
		try {
			if (webElement != null) {
				enterTextIntoWebElement();
			} else if (type != null) {
				enterTextIntoTestElement();
			} else {
				Validator.of(type).validate(Objects::nonNull, result -> type != null, "Element is null. Add the into() method.")
						.get();
				Validator.of(webElement).validate(Objects::nonNull, result -> webElement != null, "Element is null. Add the into() method.")
						.get();
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
		return this;
	}
}
