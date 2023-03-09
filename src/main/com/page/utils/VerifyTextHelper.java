package com.page.utils;

import java.util.Objects;

import com.config.SHelper;
import com.config.TestException;
import com.config.setup.report.LocalReport;
import com.config.setup.report.LocalValidation;
import com.selenium.TestElement;
import com.utils.devtools.CookieHelper;
import com.utils.Validator;
import com.page.core.web.Type;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import com.selenium.shared.Via;
import com.selenium.text.Variable;
import com.utils.TestUtils;

@Slf4j
public class VerifyTextHelper {

	// optional params
	private Type type;
	private WebElement webElement;
	private TestElement element;
	private String expectedText;
	private String index;
	private Boolean removeAllSpaces;
	private Via via;

	/**
	 * <p> Method to verify some element contains the
	 * expected text.
	 * </p>
	 * <p> This method contains the functionality to
	 * remove all spacing in the actual string pulled
	 * from the page as well as all spacing in the
	 * expected string in order to more accurately
	 * compare the two strings. This was added due to
	 * weird spacing issues found on some pages in the
	 * Newstron application. It seemed easier to just
	 * compare two strings with absolutely no spaces
	 * then try to make the strings match up in other
	 * ways.
	 * </p>
	 * <p> Verifying text via javascript is typically only used when
	 * validating a textbox or textarea contains the
	 * expected text. It is not typcially used to
	 * verify other elements such as divs or table
	 * rows/columns, etc.
	 * </p>
     * <pre>
     * Ex: 
     * {@code new VerifyTextHelper(new VerifyTextHelper(new ReportInfo("Test Element"))
	 *	.verify(Generic.ELEMENT.element())
	 *	.contains("Test 123")
	 *	.how(Via.JAVASCRIPT));}
     * </pre>
	 * @throws TestException
	 */
	public VerifyTextHelper() throws TestException {
		if (this.removeAllSpaces == null) {
			this.removeAllSpaces = false;
		}

		if (this.via == null) {
			this.via = Via.SELENIUM;
		}
	}
	
	private void verifySomeWebElementContainsTheExpectedText() throws TestException {
		try {
			String actualText = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(webElement);

			if (removeAllSpaces) {
				actualText = actualText.replace(" ", "");
				expectedText = expectedText.replace(" ", "");
			}

			if (actualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
				LocalValidation.getValidations()
						.assertionPass(log, type.name() + " contains the correct text: " + actualText);
			} else {
				throw LocalValidation.getValidations().assertionFailed(log,
						type.name() + " does not contain the correct text. Expected text: "
								+ expectedText + ". Actual text: " + actualText);
			}
			CookieHelper.newHelper().getCookies().setCookies().build();
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}
	
	private void verifySomeElementContainsTheExpectedText() throws TestException {
		try {
			WebElement element = SHelper.get().element().get(type.element());
			String actualText = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(element);

			if (removeAllSpaces) {
				actualText = actualText.replace(" ", "");
				expectedText = expectedText.replace(" ", "");
			}

			if (actualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
				LocalValidation.getValidations()
						.assertionPass(log, type.name() + " contains the correct text: " + actualText);
			} else {
				throw LocalValidation.getValidations().assertionFailed(log,
						type.name() + " does not contain the correct text. Expected text: "
								+ expectedText + ". Actual text: " + actualText);
			}
			CookieHelper.newHelper().getCookies().setCookies().build();
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}
	
	private void verifyTextInTextField() throws TestException {
		try {
			String index = null;
			if (!TestUtils.isNullOrBlank(index)) {
				index = index;
			}
			String actualValueInTextBox = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom(type.element(),
					index);
			if (actualValueInTextBox.contains("\n")) {
				actualValueInTextBox = actualValueInTextBox.replace("\n", " ");
			}

			if (removeAllSpaces) {
				actualValueInTextBox = actualValueInTextBox.replace(" ", "");
				expectedText = expectedText.replace(" ", "");
			}

			if (actualValueInTextBox.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
				LocalValidation.getValidations().assertionPass(log,
						type.name() + " contains " + actualValueInTextBox + " as expected.");
			} else {
				throw LocalValidation.getValidations()
						.assertionFailed(log, type.name() + " should contain " + expectedText
								+ " but is retaining an incorrect value instead. The value being retained is "
								+ actualValueInTextBox);
			}
			CookieHelper.newHelper().getCookies().setCookies().build();
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}

	private void verifyTextInWebElementTextField() throws TestException {
		try {
			String index = null;
			if (!TestUtils.isNullOrBlank(index)) {
				index = index;
			}
			String actualValueInTextBox = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom(webElement,
					index);
			if (actualValueInTextBox.contains("\n")) {
				actualValueInTextBox = actualValueInTextBox.replace("\n", " ");
			}

			if (removeAllSpaces) {
				actualValueInTextBox = actualValueInTextBox.replace(" ", "");
				expectedText = expectedText.replace(" ", "");
			}

			if (actualValueInTextBox.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
				LocalValidation.getValidations().assertionPass(log,
						type.name() + " contains " + actualValueInTextBox + " as expected.");
			} else {
				throw LocalValidation.getValidations()
						.assertionFailed(log, type.name() + " should contain " + expectedText
								+ " but is retaining an incorrect value instead. The value being retained is "
								+ actualValueInTextBox);
			}
			CookieHelper.newHelper().getCookies().setCookies().build();
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}

	/**
	 * <p> Tells the VerifyTextHelper which TestElement needs to be
	 * verified.
	 * </p>
	 * <p> This is a required method. If a TestElement or a WebElement is not 
	 * given, then VerifyTextHelper will throw an error and request for the 
	 * verify() method to be added.
	 * </p>
	 * <pre>
	 * Ex: 
	 * {@code new VerifyTextHelper(new VerifyTextHelper(new ReportInfo("Test Element"))
	 *	.verify(Generic.ELEMENT.element())
	 *	.contains("Test 123"));}
	 * </pre>
	 * @param element - the TestElement to be clicked
	 */
	public VerifyTextHelper verify(Type element) {
		this.type = element;
		return this;
	}

	/**
	 * <p> Tells the VerifyTextHelper which WebElement needs to be
	 * verified.
	 * </p>
	 * <p> This is a required method. If a TestElement or a WebElement is not 
	 * given, then VerifyTextHelper will throw an error and request for the 
	 * verify() method to be added.
	 * </p>
	 * <pre>
	 * Ex: 
	 * {@code WebElement div = SHelper.get().element().get(Generic.ELEMENT.element());
	 * new VerifyTextHelper(new VerifyTextHelper(new ReportInfo("Test Element"))
	 *	.verify(div)
	 *	.contains("Test 123"));}
	 * </pre>
	 * @param element - the TestElement to be clicked
	 */
	public VerifyTextHelper using(WebElement element) {
		this.webElement = element;
		return this;
	}

	/**
	 * <p> Tells the VerifyTextHelper which text value needs to be
	 * validated inside of an element.
	 * </p>
	 * <p> This is a required method. If a value is not 
	 * given, then VerifyTextHelper will throw an error and request for the 
	 * contains() method to be added.
	 * </p>
	 * <p> This method can utilize the functionality to
	 * remove all spacing in the actual string pulled
	 * from the page as well as all spacing in the
	 * expected string in order to more accurately
	 * compare the two strings. This was added due to
	 * weird spacing issues found on some pages in the
	 * Newstron application. It seemed easier to just
	 * compare two strings with absolutely no spaces
	 * then try to make the strings match up in other
	 * ways. Add removeAllSpaces(true) to the method chain 
	 * to do this.
	 * </p>
	 * <pre>
	 * Ex: 
	 * {@code WebElement div = SHelper.get().element().get(Generic.ELEMENT.element());
	 * new VerifyTextHelper(new VerifyTextHelper(new ReportInfo("Test Element"))
	 *	.verify(div)
	 *	.contains("Test 123"));}
	 * </pre>
	 */
	public VerifyTextHelper contains(String value) {
		this.expectedText = value;
		return this;
	}

	/**
	 * <p> Tells the VerifyTextHelper whether or not to remove all 
	 * spaces from both the actual text and the expected text for more
	 * accurate verification.
	 * </p>
	 * <p> This is not a required method. Unless an the test is failing due to
	 * weird spacing issues in the actual text or expected text that cannot
	 * be resolved in a different way. The default value is set to false.
	 * </p>
	 * <pre>
	 * Ex: 
	 * {@code new VerifyTextHelper(new VerifyTextHelper(new ReportInfo("Test Element"))
	 *	.verify(Generic.ELEMENT.element())
	 *	.contains("Test 123")
	 *	.removeAllSpaces(true));}
	 * </pre>
	 * @param value - the index of the element to be clicked
	 */
	public VerifyTextHelper removeAllSpaces(Boolean value) {
		this.removeAllSpaces = value;
		return this;
	}

	/**
	 * <p> Tells the VerifyTextHelper how the text should be verified
	 * via Selenium or Javascript.
	 * </p>
	 * <p> This is not a required method. Unless an element should be verified
	 * via a method that is not Selenium. The default is set to verify
	 * via Selenium.
	 * </p>
	 * <p> Verifying text via javascript is typically only used when
	 * validating a textbox or textarea contains the
	 * expected text. It is not typcially used to
	 * verify other elements such as divs or table
	 * rows/columns, etc.
	 * </p>
	 * <pre>
	 * Ex: 
	 * {@code new VerifyTextHelper(new VerifyTextHelper(new ReportInfo("Test Element"))
	 *	.verify(Generic.ELEMENT.element())
	 *	.contains("Test 123")
	 *	.how(Via.JAVASCRIPT));}
	 * </pre>
	 * @param via - the Via by which the element should be clicked
	 */
	public VerifyTextHelper how(Via via) {
		this.via = via;
		return this;
	}

	/**
	 * <p> Tells the VerifyTextHelper the index of the element that needs to be 
	 * verified in a list of elements.
	 * </p>
	 * <p> This is not a required method. Unless an element is being verified
	 * via Javascript and is in a list of similarly
	 * identified elements.
	 * </p>
	 * <pre>
	 * Ex: 
	 * {@code new VerifyTextHelper(new VerifyTextHelper(new ReportInfo("Test Element"))
	 *	.verify(Generic.ELEMENT.element())
	 *	.contains("Test 123")
	 *	.how(Via.JAVASCRIPT)
	 *	.withIndexOf(0));}
	 * </pre>
	 */
	public VerifyTextHelper withIndexOf(String index) {
		this.index = index;
		return this;
	}

	public VerifyTextHelper start() throws TestException {
		try {
			Validator.of(expectedText)
					.validate(Objects::nonNull, result -> expectedText != null, "Expected Text is null. Add the contains() method.").get();
			if (webElement != null) {
				if (via.equals(Via.JAVASCRIPT)) {
					verifyTextInWebElementTextField();
				} else {
					verifySomeWebElementContainsTheExpectedText();
				}
			} else if (type != null) {
				if (via.equals(Via.JAVASCRIPT)) {
					verifyTextInTextField();
				} else {
					verifySomeElementContainsTheExpectedText();
				}
			} else {
				throw LocalValidation.getValidations()
						.assertionFailed(log, "An Element was not provided. Unable to enter text into an undefined element. "
								+ "Add the verify() method to the VerifyTextHelper chain and provide either TestElement or WebElement.");
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
		return this;
	}

}
