package common.base.helpers;

import java.util.Objects;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import common.base.helpers.ClickHelper.ClickBuilder;
import common.base.vobjects.ReportInfo;
import common.utils.TestUtils;
import common.utils.Validator;
import common.utils.managers.LocalReport;
import common.utils.managers.LocalValidation;
import common.utils.managers.SHelper;
import log.TestException;
import shelper.enums.Via;
import shelper.vobjects.TestElement;

public class EnterTextHelper {

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
	 * {@code new EnterTextHelper(new EnterTextBuilder(new ReportInfo("Test Element"))
	 *	.enterText("Test123")
	 *	.into(Generic.ELEMENT.element()));}
	 * </pre>
	 * @throws TestException
	 */
	public EnterTextHelper(EnterTextBuilder builder) throws TestException {
		try {
			if (builder.element != null) {
				enterTextIntoTestElement(builder);
			} else if (builder.webElement != null) {
				enterTextIntoWebElement(builder);
			} else {
				Validator.of(builder.element).validate(Objects::nonNull, result -> builder.element != null, "Element is null. Add the into() method.")
					.get();
				Validator.of(builder.webElement).validate(Objects::nonNull, result -> builder.webElement != null, "Element is null. Add the into() method.")
					.get();
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}

	private void enterTextIntoWebElement(EnterTextBuilder builder) throws TestException {
		try {
			if (SHelper.get().element().isDisplayed(builder.webElement, 5)) {
				SHelper.get().click(Via.SELENIUM).on(builder.webElement);
				clearAllTextByBackspacing(builder.webElement);

				if (!TestUtils.isNullOrBlank(builder.value)) {
					SHelper.get().enter(Via.SELENIUM).clear(builder.webElement);
					SHelper.get().click(Via.SELENIUM).on(builder.webElement);
					SHelper.get().enter(Via.SELENIUM).textInto(builder.webElement, builder.value);
				}
				LocalReport.getReport().reportDoneEvent(builder.info.elementTitle() + " has been entered successfully");
			} else {
				throw LocalValidation.getValidations().assertionFailed(builder.info.elementTitle()
						+ " does not display as expected. Unable to enter text in this field.");
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}
	
	private void enterTextIntoTestElement(EnterTextBuilder builder) throws TestException {
		try {
			if (SHelper.get().element().isDisplayed(builder.element, 5)) {
				new ClickHelper(new ClickBuilder(new ReportInfo(builder.info.elementTitle()))
						.clickOn(new TestElement(builder.element.locator(), builder.element.by())));
				clearAllTextByBackspacing(builder.element);
				if (!TestUtils.isNullOrBlank(builder.value)) {
					SHelper.get().enter(Via.SELENIUM).clear(builder.element);
					new ClickHelper(new ClickBuilder(new ReportInfo(builder.info.elementTitle()))
							.clickOn(new TestElement(builder.element.locator(), builder.element.by())));
					SHelper.get().enter(Via.SELENIUM).textInto(builder.element, builder.value);
				}
				LocalReport.getReport().reportDoneEvent(builder.info.elementTitle() + " has been entered successfully");
			} else {
				throw LocalValidation.getValidations().assertionFailed(builder.info.elementTitle()
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
	 * @param locatorString
	 *            - the webelement locator string
	 *            necessary for the webelement to be
	 *            found
	 * @throws TestException
	 */
	private void clearAllTextByBackspacing(TestElement element) throws TestException {
		SHelper.get().enter(Via.SELENIUM).textInto(element, Keys.CONTROL + "a");
		SHelper.get().enter(Via.SELENIUM).textInto(element, Keys.BACK_SPACE);
	}

	public static class EnterTextBuilder {
		// required params
		private ReportInfo info;

		// optional params
		private TestElement element;
		private WebElement webElement;
		private String value;

		/**
		 * <p> This creates an instance of EnterTextBuilder which utilizes
		 * a builder pattern to assign values to important variables used in
		 * the EnterTextBuilder class.
		 * </p>
		 * <pre>
		 * Ex: 
		 * {@code new EnterTextHelper(new EnterTextBuilder(new ReportInfo("Test Element"))
		 *	.enterText("Test123")
		 *	.into(Generic.ELEMENT.element()));}
		 * </pre>
		 * @param info - the text that will be written to the test results report
		 */
		public EnterTextBuilder(ReportInfo info) {
			this.info = info;
		}

		/**
		 * <p> Tells the EnterTextBuilder which value to enter into an element.
		 * </p>
		 * <p> This is not a required method. If a value is not provided, 
		 * then nothing will be entered into the text field and the 
		 * test will continue executing.
		 * </p>
		 * <pre>
		 * Ex: 
		 * {@code new EnterTextHelper(new EnterTextBuilder(new ReportInfo("Test Element"))
		 *	.enterText("Test123")
		 *	.into(Generic.ELEMENT.element()));}
		 * </pre>
		 * @param value - the text value to be entered into the element
		 */
		public EnterTextBuilder enterText(String value) {
			if (TestUtils.isNullOrBlank(value)) {
				this.value = "";
			} else {
				this.value = value;
			}
			return this;
		}

		/**
		 * <p> Tells the EnterTextBuilder which TestElement to enter
		 * text into.
		 * </p>
		 * <p> This is a required method. If a TestElement or a WebElement is not 
		 * given, then EnterTextHelper will throw an error and request for the 
		 * into() method to be added.
		 * </p>
		 * <pre>
		 * Ex: 
		 * {@code new EnterTextHelper(new EnterTextBuilder(new ReportInfo("Test Element"))
		 *	.enterText("Test123")
		 *	.into(Generic.ELEMENT.element()));}
		 * </pre>
		 * @param value - the text value to be entered into the element
		 */
		public EnterTextBuilder into(TestElement element) {
			this.element = element;
			return this;
		}

		/**
		 * <p> Tells the EnterTextBuilder which TestElement to enter
		 * text into.
		 * </p>
		 * <p> This is a required method. If a TestElement or a WebElement is not 
		 * given, then EnterTextHelper will throw an error and request for the 
		 * into() method to be added.
		 * </p>
		 * <pre>
		 * Ex: 
		 * {@code WebElement field = SHelper.get().element().get(Generic.ELEMENT.element())
		 * new EnterTextHelper(new EnterTextBuilder(new ReportInfo("Test Element"))
		 *	.enterText("Test123")
		 *	.into(field));}
		 * </pre>
		 * @param value - the text value to be entered into the element
		 */
		public EnterTextBuilder into(WebElement element) {
			this.webElement = element;
			return this;
		}

	}
}
