package common.base.helpers;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import common.base.helpers.ClickHelper.ClickBuilder;
import common.base.vobjects.ReportInfo;
import common.utils.TestUtils;
import common.utils.managers.LocalReport;
import common.utils.managers.LocalValidation;
import common.utils.managers.SHelper;
import log.TestException;
import shelper.enums.Via;
import shelper.vobjects.TestElement;

public class EnterTextHelper {

		public EnterTextHelper(EnterTextBuilder builder) throws TestException {
			try {
	           if (builder.element != null) {
	        	   		enterTextIntoTestElement(builder);
	           } else if (builder.webElement != null) {
	        	   		enterTextIntoWebElement(builder);
	           } else {
	        	   		throw LocalValidation.getValidations().assertionFailed("An Element was not provided. Unable to enter text into an undefined element. "
	        	   				+ "Add the into() method to the EnterTextBuilder chain and privide either  TestElement or WebElement.");
	           }
	        } catch (Exception ex) {
	            throw LocalReport.getReport().reportException(ex);
	        }
		}
		
		/**
	     * <p>This is a shared generic method to enter text into a text field
	     * of an element.</p>
	     * <p>The WebElement will be defined in this method provided the selector string
	     * and by value are provided as params.</p>
	     * <p>This method will wait for the text box element to display on the 
	     * page before attempting to enter text into it. If the element is not 
	     * found the test is failed.</p>
	     * <p>If the ValueToBeEntered param is null or an empty string, then
	     * no value will be entered into the text field and the test will continue
	     * without entereing anything.</p>
	     * @param valueToBeEntered
	     *            the text that will be entered into the text field
	     * @param selector
	     *            the webelement selector string for the field that the text
	     *            will be entered into
	     * @param byValue
	     *            the type of selector being used (i.e id, name, cssSelector,
	     *            xpath, etc.)
	     * @param elementBeingTested
	     *            the name of the element being tested. This is used for
	     *            reporting so that when it is called the report will reflect an
	     *            element that is unique to the method
		 * @throws TestException 
	     */

		private void enterTextIntoWebElement(EnterTextBuilder builder) throws TestException {
			try {
	            if (SHelper.get().element().isDisplayed(builder.webElement, 5)) {
	                SHelper.get().click(Via.SELENIUM).on(builder.webElement);
	                clearAllTextByBackspacing(builder.webElement);

	                if (!TestUtils.isNullOrBlank(builder.value)) {
	                    SHelper.get().enter().clear(builder.webElement);
	                    SHelper.get().click(Via.SELENIUM).on(builder.webElement);
	                    SHelper.get().enter().textInto(builder.webElement, builder.value);
	                }
	                LocalReport.getReport().reportDoneEvent(builder.info.elementTitle() + " has been entered successfully");
	            } else {
	                throw LocalValidation.getValidations().assertionFailed(
	                		builder.info.elementTitle() + " does not display as expected. Unable to enter text in this field.");
	            }
	        } catch (Exception ex) {
	            throw LocalReport.getReport().reportException(ex);
	        }
		}
		
		/**
	     * <p>This is a shared generic method to enter text into a text field
	     * of a pre-defined web element.</p>
	     * <p>This method will wait for the text box element to display on the 
	     * page before attempting to enter text into it. If the element is not 
	     * found the test is failed.</p>
	     * <p>If the valueToBeEntered param is null or an empty string, then
	     * no value will be entered into the text field and the test will continue
	     * without entereing anything.</p>
	     * @param valueToBeEntered
	     *            the text that will be entered into the text field
	     * @param webElement
	     *            the webelement selector string for the field that the text
	     *            will be entered into
	     * @param elementBeingTested
	     *            the name of the element being tested. This is used for
	     *            reporting so that when it is called the report will record an
	     *            element description that is unique to the method
		 * @throws TestException 
	     */

		private void enterTextIntoTestElement(EnterTextBuilder builder) throws TestException {
			try {
	            if (SHelper.get().element().isDisplayed(builder.element, 5)) {
	            		new ClickHelper(new ClickBuilder(new ReportInfo(builder.info.elementTitle())).clickOn(new TestElement(builder.element.locator(), builder.element.by())));
	                clearAllTextByBackspacing(builder.element);
	                if (!TestUtils.isNullOrBlank(builder.value)) {
	                    SHelper.get().enter().clear(builder.element);
	                    new ClickHelper(new ClickBuilder(new ReportInfo(builder.info.elementTitle())).clickOn(new TestElement(builder.element.locator(), builder.element.by())));
	                    SHelper.get().enter().textInto(builder.element, builder.value);
	                }
	                LocalReport.getReport().reportDoneEvent(builder.info.elementTitle() + " has been entered successfully");
	            } else {
	                throw LocalValidation.getValidations().assertionFailed(
	                		builder.info.elementTitle() + " does not display as expected. Unable to enter text in this field.");
	            }
	        } catch (Exception ex) {
	            throw LocalReport.getReport().reportException(ex);
	        }
		}
		
	    /**
	     * <p> Method to clear all text in a field by selecting all text and selecting 
	     * the backspace key on the keyboard.
	     * </p>
	     * @param element - a webelement that is defined and found in the calling method
	     * @throws TestException
	     */
	    private void clearAllTextByBackspacing(WebElement element) throws TestException {
	            SHelper.get().enter().textInto(element, Keys.CONTROL + "a");
	            SHelper.get().enter().textInto(element, Keys.BACK_SPACE);
	    }
	    

	    /**
	     * <p> Method to clear all text in a field by selecting all text and selecting 
	     * the backspace key on the keyboard.
	     * </p>
	     * @param locatorString - the webelement locator string necessary for the webelement to
	     * be found
	     * @throws TestException
	     */
	    private void clearAllTextByBackspacing(TestElement element) throws TestException {
	            SHelper.get().enter().textInto(element, Keys.CONTROL + "a");
	            SHelper.get().enter().textInto(element, Keys.BACK_SPACE);
	    }
		
		public static class EnterTextBuilder {
			//required params
			private ReportInfo info;
			
			//optional params
			private TestElement element;
			private WebElement webElement;
			private String value;
			
			public EnterTextBuilder(ReportInfo info) {
				this.info = info;
			}
			
			public EnterTextBuilder enterText(String value) {
				if (TestUtils.isNullOrBlank(value)) {
					this.value = "";
				} else {
					this.value = value;
				}
				return this;
			}
			
			public EnterTextBuilder into(TestElement element) {
				this.element = element;
				return this;
			}
			
			public EnterTextBuilder into(WebElement element) {
				this.webElement = element;
				return this;
			}
			
		}
}
