package common.basePage.helpers;

import org.openqa.selenium.WebElement;
import common.basePage.valueObjects.ReportInfo;
import common.utils.NullReportTool;
import common.utils.TestUtils;
import common.utils.NullReportTool.NullReportBuilder;
import common.utils.managers.LocalReport;
import common.utils.managers.LocalValidation;
import common.utils.managers.SHelper;
import seleniumHelper.enums.Variable;
import seleniumHelper.enums.Via;
import seleniumHelper.valueObjects.TestElement;

public class VerifyTextHelper {
	
	public VerifyTextHelper(VerifyTextBuilder builder) throws Exception {
		try {
			new NullReportTool(new NullReportBuilder(new ReportInfo("Expected Text"))
					.value(builder.expectedText)
					.verifyValueIsNotNull(true));
	           if (builder.via != null &&
	        		   builder.via.equals(Via.JAVASCRIPT)) {
	        	   		verifyTextInTextField(builder);
	           } else if (builder.element != null) {
	        	   		verifySomeElementContainsTheExpectedText(builder);
	           } else if (builder.webElement != null) {
	        	   		verifySomeWebElementContainsTheExpectedText(builder);
	           } else {
	        	   		throw LocalValidation.getValidations().assertionFailed("An Element was not provided. Unable to enter text into an undefined element. "
	        	   				+ "Add the verify() method to the VerifyTextBuilder chain and provide either TestElement or WebElement.");
	           }
	        } catch (Exception ex) {
	            throw LocalReport.getReport().reportException(ex);
	        }
	}
	
	   /**
     * <p>Method to verify some element contains the expected text.</p>
     * <p>This method contains the functionality to remove all spacing in
     * the actual string pulled from the page as well as all spacing in the expected
     * string in order to more accurately compare the two strings. This was added
     * due to weird spacing issues found on some pages in the Newston application.
     * It seemed easier to just compare two strings with absolutely no spaces then
     * try to make the strings match up in other ways.</p>
     * <p>The text must only contain the expected text (letter casing is not a factor).</p>
     * @param element - the webelement for the field that the text
     * will be compared against.
     * @param expectedText - the text that is expected to be in the element
     * @param elementBeingTested - the name of the element being tested. This is used for
     * reporting so that when it is called the report will reflect an element that is 
     * unique to the method
     * @param removeAllSpaces - boolean to determine whether validation needs to occur with
     * all spaces removed to produce a more accurate comparison. Not neccesary for all 
     * users of this method, however has been necessary for some instances.
     * @throws Exception
     */
    protected void verifySomeWebElementContainsTheExpectedText(VerifyTextBuilder builder) throws Exception {
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
                throw LocalValidation.getValidations()
                    .assertionFailed(builder.info.elementTitle() + " does not contain the correct text. Expected text: " +
                    		builder.expectedText + ". Actual text: " + actualText);
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    /**
     * <p>Method to verify some element contains the expected text.</p>
     * <p>This method contains the functionality to remove all spacing in
     * the actual string pulled from the page as well as all spacing in the expected
     * string in order to more accurately compare the two strings. This was added
     * due to weird spacing issues found on some pages in the Newston application.
     * It seemed easier to just compare two strings with absolutely no spaces then
     * try to make the strings match up in other ways.</p>
     * <p>The text must only contain the expected text (letter casing is not a factor).</p>
     * @param locator - the webelement locator string for the field that the text
     * will be compared against.
     * @param by - the type of locator being used (i.e id, name, csslocator,
     * xpath, etc.)
     * @param expectedText - the text that is expected to be in the element
     * @param elementBeingTested - the name of the element being tested. This is used for
     * reporting so that when it is called the report will reflect an
     * element that is unique to the method
     * @param removeAllSpaces - boolean to determine whether validation needs to occur with
     * all spaces removed to producce a more accurate comparison. Not
     * neccesary for all consumers of this method, however has been
     *  necessary for some instances.
     * @throws Exception
     */
    protected void verifySomeElementContainsTheExpectedText(VerifyTextBuilder builder) throws Exception {
        try {
            WebElement element = SHelper.get().element().get(builder.element.locator, builder.element.by);
            String actualText = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(element);

            if (builder.removeAllSpaces) {
                actualText = actualText.replace(" ", "");
                builder.expectedText = builder.expectedText.replace(" ", "");
            }

            if (actualText.toLowerCase().trim().contains(builder.expectedText.toLowerCase().trim())) {
                LocalValidation.getValidations()
                    .assertionPass(builder.info.elementTitle() + " contains the correct text: " + actualText);
            } else {
                throw LocalValidation.getValidations()
                    .assertionFailed(builder.info.elementTitle() + " does not contain the correct text. Expected text: " +
                    		builder.expectedText + ". Actual text: " + actualText);
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }
    
    /**
     * <p>Method to verify that text in a text field contains the expected text
     * utilizing the javascript function of getting a value from a text box webelement.</p>
     * <p>This method is typically only used for validating a textbox or textarea 
     * contains the expected text. It is not typcially used to verify other elements
     * such as divs or table rows/columns, etc.</p>
     * <p>The text must only contain the expected text (letter casing is not a factor).</p>
     * <p>This method contains the functionality to remove all spacing in
     * the actual string pulled from the page as well as all spacing in the expected
     * string in order to more accurately compare the two strings. This was added
     * due to weird spacing issues found on some pages in the Newston application.
     * It seemed easier to just compare two strings with absolutely no spaces then
     * try to make the strings match up in other ways.</p>
     * <p>This method also removes the \n character if it is at any point returned
     * in the actual text string that is retrieved from the element.</p>
     * @param locator - the webelement locator string for the field of the text
     * being verified
     * @param by - the type of locator being used (i.e id, name, csslocator,
     * xpath, etc.)
     * @param webElementIndex - the index of the element if there are more than one elements
     * with the same locator. Leave this value null to not include an index.
     * @param expectedText - the text that is expected to be in the element
     * @param elementBeingTested - the name of the element being tested. This is used for
     * reporting so that when it is called the report will reflect an
     * element that is unique to the method
     * @throws Exception
     */
    protected void verifyTextInTextField(VerifyTextBuilder builder) throws Exception {
        try {
            String index = null;
            if (!TestUtils.isNullOrBlank(builder.index)) {
                index = builder.index;
            }
            String actualValueInTextBox = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom(builder.element.locator, builder.element.by,
                index);
            if (actualValueInTextBox.contains("\n")) {
                actualValueInTextBox = actualValueInTextBox.replace("\n", " ");
            }

            if (builder.removeAllSpaces) {
                actualValueInTextBox = actualValueInTextBox.replace(" ", "");
                builder.expectedText = builder.expectedText.replace(" ", "");
            }

            if (actualValueInTextBox.toLowerCase().trim().contains(builder.expectedText.toLowerCase().trim())) {
                LocalValidation.getValidations()
                    .assertionPass(builder.info.elementTitle() + " contains " + actualValueInTextBox + " as expected.");
            } else {
                throw LocalValidation.getValidations()
                    .assertionFailed(builder.info.elementTitle() + " should contain " + builder.expectedText +
                        " but is retaining an incorrect value instead. The value being retained is " +
                        actualValueInTextBox);
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

	
	public static class VerifyTextBuilder {
		
		//required params
		private ReportInfo info;
		
		//optional params
		private TestElement element;
		private WebElement webElement;
		private String expectedText;
		private String index;
		private Boolean removeAllSpaces;
		private Via via;
		
		public VerifyTextBuilder(ReportInfo info) {
			this.info = info;
			if (this.removeAllSpaces == null) {
				this.removeAllSpaces = false;
			}
		}
		
		public VerifyTextBuilder verify(TestElement element) {
			this.element = element;
			return this;
		}
		
		public VerifyTextBuilder verify(WebElement element) {
			this.webElement = element;
			return this;
		}
		
		public VerifyTextBuilder contains(String value) {
			this.expectedText = value;
			return this;
		}
		
		public VerifyTextBuilder removeAllSpaces(Boolean value) {
			if(value == null) {
				this.removeAllSpaces = false;
			} else {
				this.removeAllSpaces = value;
			}
			return this;
		}
		
		public VerifyTextBuilder via(Via via) {
			if(via == null) {
				this.via = Via.SELENIUM;
			} else {
				this.via = via;
			}
			return this;
		}
		
		public VerifyTextBuilder withIndexOf(String index) {
			this.index = index;
			return this;
		}
	}

}
