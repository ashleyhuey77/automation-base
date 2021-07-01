package com.warnermedia.page.utils;

import java.util.Objects;

import com.warnermedia.page.core.web.Fetch;
import com.warnermedia.page.core.web.Type;
import org.openqa.selenium.WebElement;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.report.LocalValidation;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Via;
import com.warnermedia.utils.Validator;

public class ClickHelper {

	private Fetch fetch;

	// optional params
	private Type type;
	private WebElement webElement;
	private Via via;
	private int index;

	public String wasExecuted;

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
	public ClickHelper() throws TestException {
		fetch = new Fetch();
		if (this.via == null) {
			this.via = Via.SELENIUM;
		}
	}

	private void clickSomeTestElement() throws TestException {
		try {
			if (SHelper.get().element().isDisplayed(fetch.element(type), 5)) {
				tryAllClicks(fetch.element(type), via);
				LocalReport.getReport().reportDoneEvent(fetch.name(type) + " clicked successfully.");
			} else {
				throw LocalValidation.getValidations().assertionFailed(
						"Element is not on the page. Unable to click the " + fetch.name(type));
			}
		} catch (Exception e) {
			throw LocalReport.getReport().reportException(e);
		}
	}

	private void clickSomeWebElement() throws TestException {
		try {
			SHelper.get().click(via).on(webElement);
			LocalReport.getReport().reportDoneEvent(fetch.name(type) + " clicked successfully.");
		} catch (Exception e) {
			throw LocalReport.getReport().reportException(e);
		}
	}

	private void tryAllClicks(TestElement element, Via via) throws TestException {
		try {
			indexCheckClick(via, element);
		} catch (Exception ex) {
			try {
				indexCheckClick(Via.SELENIUM, element);
			} catch (Exception e2) {
				try {
					indexCheckClick(Via.JAVASCRIPT, element);
				} catch (Exception e3) {
					try {
						indexCheckClick(Via.JQUERY, element);
					} catch (Exception e4) {
						throw LocalValidation.getValidations().assertionFailed("Test has exhausted all different click "
								+ "methods. Not able to click element with the specified locator.");
					}
				}
			}
		}
	}

	private void indexCheckClick(Via via, TestElement element) throws TestException {
		SHelper.get().click(via).on(element);
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
	public ClickHelper on(Type element) {
		this.type = element;
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
	public ClickHelper using(WebElement element) {
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
	public ClickHelper how(Via via) {
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
	public ClickHelper withAnIndexOf(int value) {
		this.index = value;
		return this;
	}

	public ClickHelper start() throws TestException {
		if (webElement != null) {
			clickSomeWebElement();
		}else if (type != null) {
			clickSomeTestElement();
		} else {
			Validator.of(type)
					.validate(Objects::nonNull, result -> type != null, "Test element is null.").get();
		}
		wasExecuted = "YES";
		return this;
	}

}
