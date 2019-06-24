package common.base.helpers;

import java.util.Objects;
import org.openqa.selenium.WebElement;
import common.base.vobjects.ReportInfo;
import common.utils.Validator;
import common.utils.managers.LocalReport;
import common.utils.managers.LocalValidation;
import common.utils.managers.SHelper;
import log.TestException;
import shelper.enums.Via;
import shelper.vobjects.TestElement;

public class ClickHelper {

	/**
	 * <p> This is a shared generic helper used to click on an
	 * element on the page.
	 * </p>
	 * <p> This helper will catch any exceptions thrown and
	 * try to execute/exhaust every method of clicking
	 * possible (e.g via selenium, javascript, jquery,
	 * etc.) before throwing the exception and failing
	 * the test. This is to reduce flimsy click
	 * failures that have been proven to happen
	 * infrequently in the NewsApps application.
	 * </p>
	 * <p> This helper will wait for the click element to
	 * display on the page before attempting to click
	 * it. If the element is not found the test is
	 * failed.
	 * </p>
     * <pre>
     * Ex: 
     * {@code new ClickHelper(new ClickBuilder("Element 1")
     * 	.clickOn(Generic.BUTTON.element()));}
     * </pre>
	 * @throws TestException
	 */
	public ClickHelper(ClickBuilder builder) throws TestException {
		if (builder.element != null) {
			clickSomeTestElement(builder);
		} else if (builder.webElement != null) {
			clickSomeWebElement(builder);
		} else {
			Validator.of(builder.element)
					.validate(Objects::nonNull, result -> builder.element != null, "Test element is null.").get();
		}
	}

	private void clickSomeTestElement(ClickBuilder builder) throws TestException {
		try {
			if (SHelper.get().element().isDisplayed(builder.element, 5)) {
				tryAllClicks(builder.element, builder.via, builder.index);
				LocalReport.getReport().reportDoneEvent(builder.info.elementTitle() + " clicked successfully.");
			} else {
				throw LocalValidation.getValidations().assertionFailed(
						"Element is not on the page. Unable to click the " + builder.info.elementTitle());
			}
		} catch (Exception e) {
			throw LocalReport.getReport().reportException(e);
		}
	}

	private void clickSomeWebElement(ClickBuilder builder) throws TestException {
		try {
			SHelper.get().click(builder.via).on(builder.webElement);
			LocalReport.getReport().reportDoneEvent(builder.info.elementTitle() + " clicked successfully.");
		} catch (Exception e) {
			throw LocalReport.getReport().reportException(e);
		}
	}

	private void tryAllClicks(TestElement element, Via via, int... index) throws TestException {
		try {
			indexCheckClick(via, element, index);
		} catch (Exception ex) {
			try {
				indexCheckClick(Via.SELENIUM, element, index);
			} catch (Exception e2) {
				try {
					indexCheckClick(Via.JAVASCRIPT, element, index);
				} catch (Exception e3) {
					try {
						indexCheckClick(Via.JQUERY, element, index);
					} catch (Exception e4) {
						throw LocalValidation.getValidations().assertionFailed("Test has exhausted all different click "
								+ "methods. Not able to click element with the specified locator.");
					}
				}
			}
		}
	}

	private void indexCheckClick(Via via, TestElement element, int... index) throws TestException {
		SHelper.get().click(via).on(element, index[0]);
	}

	public static class ClickBuilder {

		// required params
		private ReportInfo info;

		// optional params
		private TestElement element;
		private WebElement webElement;
		private Via via;
		private int index;

		/**
		 * <p> This creates an instance of ClickBuilder which utilizes
		 * a builder pattern to assign values to important variables used in
		 * the ClickHelper class.
		 * </p>
		 * <pre>
		 * Ex: 
		 * {@code new ClickHelper(new ClickBuilder("Element 1")
		 * 	.clickOn(Generic.BUTTON.element()));}
		 * </pre>
		 * @param info - the text that will be written to the test results report
		 */
		public ClickBuilder(ReportInfo info) {
			this.info = info;
			if (this.via == null) {
				this.via = Via.SELENIUM;
			}
		}

		/**
		 * <p> Tells the ClickHelper which TestElement needs to be
		 * clicked.
		 * </p>
		 * <p> This is a required method. If a TestElement or a WebElement is not 
		 * given, then ClickHelper will throw an error and request for the 
		 * clickOn() method to be added.
		 * </p>
		 * <pre>
		 * Ex: 
		 * {@code new ClickHelper(new ClickBuilder("Element 1")
		 * 	.clickOn(Generic.BUTTON.element()));}
		 * </pre>
		 * @param element - the TestElement to be clicked
		 */
		public ClickBuilder clickOn(TestElement element) {
			this.element = element;
			return this;
		}

		/**
		 * <p> Tells the ClickHelper which WebElement needs to be
		 * clicked.
		 * </p>
		 * <p> This is a required method. If a TestElement or a WebElement is not 
		 * given, then ClickHelper will throw an error and request for the 
		 * clickOn() method to be added.
		 * </p>
		 * <pre>
		 * Ex: 
		 * {@code WebElement someElement = SHelper.get().element().get(Generic.BUTTON.element());
		 * new ClickHelper(new ClickBuilder("Element 1")
		 * 	.clickOn(someElement)); }
		 * </pre>
		 * @param element - the WebElement to be clicked
		 */
		public ClickBuilder clickOn(WebElement element) {
			this.webElement = element;
			return this;
		}

		/**
		 * <p> Tells the ClickHelper how the element should be clicked
		 * via a Selenium, Javascript, Jquery, or alternative(right) click.
		 * </p>
		 * <p> This is not a required method. Unless an element should be clicked
		 * via a method that is not Selenium. The default is set to click
		 * via Selenium.
		 * </p>
		 * <pre>
		 * Ex: 
		 * {@code new ClickHelper(new ClickBuilder("Element 1")
		 * 	.clickOn(Generic.ELEMENT.element())
		 * 	.via(Via.JAVASCRIPT)); }
		 * </pre>
		 * @param via - the Via by which the element should be clicked
		 */
		public ClickBuilder how(Via via) {
			this.via = via;
			return this;
		}

		/**
		 * <p> Tells the ClickHelper the index of the element that needs to
		 * be clicked if the element is in a list of elements.
		 * </p>
		 * <p> This is not a required method. Unless an element is being clicked
		 * via Javascript or Jquery and is in a list of similarly
		 * identified elements.
		 * </p>
		 * <pre>
		 * Ex: 
		 * {@code new ClickHelper(new ClickBuilder("Element 1")
		 * 	.clickOn(Generic.ELEMENT.element())
		 * 	.via(Via.JAVASCRIPT)
		 * 	.withAnIndexOf(0)); }
		 * </pre>
		 * @param value - the index of the element to be clicked
		 */
		public ClickBuilder withAnIndexOf(int value) {
			this.index = value;
			return this;
		}

	}
}
