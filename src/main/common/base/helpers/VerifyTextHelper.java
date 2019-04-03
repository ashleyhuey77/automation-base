package common.base.helpers;

import java.util.Objects;
import org.openqa.selenium.WebElement;
import common.base.vobjects.ReportInfo;
import common.utils.TestUtils;
import common.utils.Validator;
import common.utils.managers.LocalReport;
import common.utils.managers.LocalValidation;
import common.utils.managers.SHelper;
import log.TestException;
import shelper.enums.Variable;
import shelper.enums.Via;
import shelper.vobjects.TestElement;

public class VerifyTextHelper {

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
     * {@code new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element"))
	 *	.verify(Generic.ELEMENT.element())
	 *	.contains("Test 123")
	 *	.how(Via.JAVASCRIPT));}
     * </pre>
	 * @throws TestException
	 */
	public VerifyTextHelper(VerifyTextBuilder builder) throws TestException {
		try {
			Validator.of(builder.expectedText)
					.validate(Objects::nonNull, result -> builder.expectedText != null, "Expected Text is null. Add the contains() method.").get();
			if (builder.via.equals(Via.JAVASCRIPT)) {
				verifyTextInTextField(builder);
			} else if (builder.element != null) {
				verifySomeElementContainsTheExpectedText(builder);
			} else if (builder.webElement != null) {
				verifySomeWebElementContainsTheExpectedText(builder);
			} else {
				throw LocalValidation.getValidations()
						.assertionFailed("An Element was not provided. Unable to enter text into an undefined element. "
								+ "Add the verify() method to the VerifyTextBuilder chain and provide either TestElement or WebElement.");
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}
	
	private void verifySomeWebElementContainsTheExpectedText(VerifyTextBuilder builder) throws TestException {
		try {
			String actualText = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(builder.webElement);

			if (builder.removeAllSpaces) {
				actualText = actualText.replace(" ", "");
				builder.expectedText = builder.expectedText.replace(" ", "");
			}

			if (actualText.toLowerCase().trim().contains(builder.expectedText.toLowerCase().trim())) {
				LocalValidation.getValidations()
						.assertionPass(builder.info.elementTitle() + " contains the correct text: " + actualText);
			} else {
				throw LocalValidation.getValidations().assertionFailed(
						builder.info.elementTitle() + " does not contain the correct text. Expected text: "
								+ builder.expectedText + ". Actual text: " + actualText);
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}
	
	private void verifySomeElementContainsTheExpectedText(VerifyTextBuilder builder) throws TestException {
		try {
			WebElement element = SHelper.get().element().get(builder.element);
			String actualText = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(element);

			if (builder.removeAllSpaces) {
				actualText = actualText.replace(" ", "");
				builder.expectedText = builder.expectedText.replace(" ", "");
			}

			if (actualText.toLowerCase().trim().contains(builder.expectedText.toLowerCase().trim())) {
				LocalValidation.getValidations()
						.assertionPass(builder.info.elementTitle() + " contains the correct text: " + actualText);
			} else {
				throw LocalValidation.getValidations().assertionFailed(
						builder.info.elementTitle() + " does not contain the correct text. Expected text: "
								+ builder.expectedText + ". Actual text: " + actualText);
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}
	
	private void verifyTextInTextField(VerifyTextBuilder builder) throws TestException {
		try {
			String index = null;
			if (!TestUtils.isNullOrBlank(builder.index)) {
				index = builder.index;
			}
			String actualValueInTextBox = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom(builder.element,
					index);
			if (actualValueInTextBox.contains("\n")) {
				actualValueInTextBox = actualValueInTextBox.replace("\n", " ");
			}

			if (builder.removeAllSpaces) {
				actualValueInTextBox = actualValueInTextBox.replace(" ", "");
				builder.expectedText = builder.expectedText.replace(" ", "");
			}

			if (actualValueInTextBox.toLowerCase().trim().contains(builder.expectedText.toLowerCase().trim())) {
				LocalValidation.getValidations().assertionPass(
						builder.info.elementTitle() + " contains " + actualValueInTextBox + " as expected.");
			} else {
				throw LocalValidation.getValidations()
						.assertionFailed(builder.info.elementTitle() + " should contain " + builder.expectedText
								+ " but is retaining an incorrect value instead. The value being retained is "
								+ actualValueInTextBox);
			}
		} catch (Exception ex) {
			throw LocalReport.getReport().reportException(ex);
		}
	}

	public static class VerifyTextBuilder {

		// required params
		private ReportInfo info;

		// optional params
		private TestElement element;
		private WebElement webElement;
		private String expectedText;
		private String index;
		private Boolean removeAllSpaces;
		private Via via;

		/**
		 * <p> This creates an instance of VerifyTextBuilder which utilizes
		 * a builder pattern to assign values to important variables used in
		 * the VerifyTextHelper class.
		 * </p>
		 * <pre>
		 * Ex: 
		 * {@code new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element"))
		 *	.verify(Generic.ELEMENT.element())
		 *	.contains("Test 123")
		 *	.how(Via.JAVASCRIPT));}
		 * </pre>
		 * @param info - the text that will be written to the test results report
		 */
		public VerifyTextBuilder(ReportInfo info) {
			this.info = info;
			if (this.removeAllSpaces == null) {
				this.removeAllSpaces = false;
			}

			if (this.via == null) {
				this.via = Via.SELENIUM;
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
		 * {@code new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element"))
		 *	.verify(Generic.ELEMENT.element())
		 *	.contains("Test 123"));}
		 * </pre>
		 * @param element - the TestElement to be clicked
		 */
		public VerifyTextBuilder verify(TestElement element) {
			this.element = element;
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
		 * new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element"))
		 *	.verify(div)
		 *	.contains("Test 123"));}
		 * </pre>
		 * @param element - the TestElement to be clicked
		 */
		public VerifyTextBuilder verify(WebElement element) {
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
		 * new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element"))
		 *	.verify(div)
		 *	.contains("Test 123"));}
		 * </pre>
		 * @param element - the element to be clicked to open the menu
		 */
		public VerifyTextBuilder contains(String value) {
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
		 * {@code new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element"))
		 *	.verify(Generic.ELEMENT.element())
		 *	.contains("Test 123")
		 *	.removeAllSpaces(true));}
		 * </pre>
		 * @param value - the index of the element to be clicked
		 */
		public VerifyTextBuilder removeAllSpaces(Boolean value) {
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
		 * {@code new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element"))
		 *	.verify(Generic.ELEMENT.element())
		 *	.contains("Test 123")
		 *	.how(Via.JAVASCRIPT));}
		 * </pre>
		 * @param via - the Via by which the element should be clicked
		 */
		public VerifyTextBuilder how(Via via) {
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
		 * {@code new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element"))
		 *	.verify(Generic.ELEMENT.element())
		 *	.contains("Test 123")
		 *	.how(Via.JAVASCRIPT)
		 *	.withIndexOf(0));}
		 * </pre>
		 * @param value - the index of the element to be clicked
		 */
		public VerifyTextBuilder withIndexOf(String index) {
			this.index = index;
			return this;
		}
	}

}
