package common.base.helpers;

import java.util.Objects;
import org.openqa.selenium.WebElement;
import common.base.helpers.ClickHelper.ClickBuilder;
import common.base.helpers.EnterTextHelper.EnterTextBuilder;
import common.base.helpers.OptionSelector.OptionSelectorBuilder;
import common.base.vobjects.ReportInfo;
import common.utils.Validator;
import common.utils.managers.LocalReport;
import common.utils.managers.LocalValidation;
import common.utils.managers.SHelper;
import log.TestException;
import shelper.builders.WaitBuilder;
import shelper.enums.Condition;
import shelper.enums.Via;
import shelper.enums.Wait;
import shelper.vobjects.TestElement;

public class DropdownHelper {
	
	/**
	 * <p> Helper to select some option from a drop down based on the text value
     * 	listed as options inside the dropdown.
     *	</p>
     *	<p> The selenium select method would not work for some of the dropdowns
     *	in the newstron application as they are not traditional dropdowns. A new method
     *	had to be created in order to select an option and it uses the 
     *	findEqualOptionInListAndSelectIt method in order to do so.</p>
     *	<p> Method waits for the clicklocator element to display before continuing the
     *	test. If the element is not found, the test fails.</p>
	 * <pre>
	 * Ex:
	 * {@code new DropdownHelper(new DropdownBuilder(new ReportInfo("Test"))
	 *	.clickMenuToOpen(Generic.CLICK_ELEMENT.element())
	 *	.searchForOption(Generic.SEARCH_ELEMENT.element())
	 *	.selectOption("Test 3")
	 *	.fromOptionList(Generic.OPTIONS_ELEMENT.element())); }
	 * </pre>
	 * @throws TestException
	 */
	public DropdownHelper(DropdownBuilder builder) throws TestException {
		try {
			Validator.of(builder.option).validate(Objects::nonNull, result -> builder.option != null, "Options is null. Add selectOption() method.").get();
			Validator.of(builder.optionsElement).validate(Objects::nonNull, result -> builder.optionsElement != null, "Options element is null. Add fromOptionList() method.").get();
			if (builder.clickElement != null &&
						builder.optionsElement != null) {
				selectTestElementFromNonDropdown(builder);
			} else if (builder.clickWebElement != null &&
					   			builder.optionsElement != null) {
				selectWebElementFromNonDropdown(builder);
			} else {
				Validator.of(builder.clickElement).validate(Objects::nonNull, result -> builder.clickElement != null, "Click element is null. Add clickMenuToOpen() method.").get();
				Validator.of(builder.clickWebElement).validate(Objects::nonNull, result -> builder.clickWebElement != null, "Click element is null. Add clickMenuToOpen() method.").get();
			}
		} catch (Exception e) {
			throw LocalReport.getReport().reportException(e);
		}	
	}
	
    private void selectTestElementFromNonDropdown(DropdownBuilder builder) throws TestException {
        try {
        	if (SHelper.get().element().isDisplayed(builder.clickElement, 3)) {
            		new ClickHelper(new ClickBuilder(new ReportInfo(builder.info.elementTitle()))
            				.clickOn(new TestElement(builder.clickElement.locator(), builder.clickElement.by())));
                Thread.sleep(600);
                if (builder.searchElement != null) {
                    new EnterTextHelper(new EnterTextBuilder(new ReportInfo(builder.info.elementTitle()))
                    		.enterText(builder.option)
                    		.into(new TestElement(builder.searchElement.locator(), builder.searchElement.by())));
                }
                SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
                		new WaitBuilder().forAMaxTimeOf(30)).on(builder.optionsElement);
                Thread.sleep(900);
                new OptionSelector(new OptionSelectorBuilder()
                		.findOption(builder.option)
                		.thatIs(Condition.EQUAL)
                		.in(new TestElement(builder.optionsElement.locator(), builder.optionsElement.by())));
            } else {
                throw LocalValidation.getValidations().assertionFailed("Element is not available. Unable to select the " 
                		+ builder.option + " option from the drop down list.");
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    private void selectWebElementFromNonDropdown(DropdownBuilder builder) throws TestException {
        try {
        	if (SHelper.get().element().isDisplayed(builder.clickWebElement, 10)) {
                SHelper.get().click(Via.SELENIUM).on(builder.clickWebElement);
                Thread.sleep(600);
                if (builder.searchWebElement != null) {
                    new EnterTextHelper(new EnterTextBuilder(new ReportInfo(builder.info.elementTitle()))
                    		.enterText(builder.option)
                    		.into(builder.searchWebElement));
                }
                SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
                		new WaitBuilder().forAMaxTimeOf(10)).on(builder.optionsElement);
                Thread.sleep(900);
                new OptionSelector(new OptionSelectorBuilder()
                		.findOption(builder.option)
                		.thatIs(Condition.EQUAL)
                		.in(new TestElement(builder.optionsElement.locator(), builder.optionsElement.by())));
            } else {
                throw LocalValidation.getValidations().assertionFailed("Element is not availale. Unable to select the " 
                		+ builder.option + " option from the drop down list.");
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

	
	public static class DropdownBuilder {
		
		//required params
		private ReportInfo info;
		
		//optional params
		private TestElement clickElement;
		private TestElement searchElement;
		private TestElement optionsElement;
		private WebElement clickWebElement;
		private WebElement searchWebElement;
		private String option;
		
		/**
		 * <p> This creates an instance of DropdownBuilder which utilizes
		 * a builder pattern to assign values to important variables used in
		 * the DropdownHelper class.
		 * </p>
		 * <pre>
		 * Ex:
		 * {@code new DropdownHelper(new DropdownBuilder(new ReportInfo("Test"))
		 *	.clickMenuToOpen(Generic.CLICK_ELEMENT.element())
		 *	.searchForOption(Generic.SEARCH_ELEMENT.element())
		 *	.selectOption("Test 3")
		 *	.fromOptionList(Generic.OPTIONS_ELEMENT.element())); }
		 * </pre>
		 * @param info - the text that will be written to the test results report
		 */
		public DropdownBuilder(ReportInfo info) {
			this.info = info;
		}
		
		/**
		 * <p> Tells the DropdownHelper which TestElement needs to be
		 * clicked in order to open the dropdown menu.
		 * </p>
		 * <p> This is a required method. If a TestElement or a WebElement is not 
		 * given, then DropdownHelper will throw an error and request for the 
		 * clickMenuToOpen() method to be added.
		 * </p>
		 * <pre>
		 * Ex:
		 * {@code new DropdownHelper(new DropdownBuilder(new ReportInfo("Test"))
		 *	.clickMenuToOpen(Generic.CLICK_ELEMENT.element())
		 *	.selectOption("Test 3")
		 *	.fromOptionList(Generic.OPTIONS_ELEMENT.element())); }
		 * </pre>
		 * @param element - the element to be clicked to open the menu
		 */
		public DropdownBuilder clickMenuToOpen(TestElement element) {
			this.clickElement = element;
			return this;
		}
		
		/**
		 * <p> Tells the DropdownHelper which WebElement needs to be
		 * clicked in order to open the dropdown menu.
		 * </p>
		 * <p> This is a required method. If a TestElement or a WebElement is not 
		 * given, then DropdownHelper will throw an error and request for the 
		 * clickMenuToOpen() method to be added.
		 * </p>
		 * <pre>
		 * Ex:
		 * {@code WebElement clickElement = SHelper.get().element().get(Generic.CLICK_ELEMENT.element());
		 * new DropdownHelper(new DropdownBuilder(new ReportInfo("Test"))
		 *	.clickMenuToOpen(clickElement)
		 *	.selectOption("Test 3")
		 *	.fromOptionList(Generic.OPTIONS_ELEMENT.element())); }
		 * </pre>
		 * @param element - the element to be clicked to open the menu
		 */
		public DropdownBuilder clickMenuToOpen(WebElement element) {
			this.clickWebElement = element;
			return this;
		}
		
		/**
		 * <p> Tells the DropdownHelper which TestElement is the 
		 * search element located in the dropdown menu in order to 
		 * narrow down results.
		 * </p>
		 * <p> This is not a required method. It is only needed when the options
		 * in the dropdown are exceedingly large and that being so causes issues
		 * when trying to select a particular option. This gives the ability to
		 * narrow down search results to a particular item.
		 * </p>
		 * <pre>
		 * Ex:
		 * {@code new DropdownHelper(new DropdownBuilder(new ReportInfo("Test"))
		 *	.clickMenuToOpen(Generic.CLICK_ELEMENT.element())
		 *	.searchForOption(Generic.SEARCH_ELEMENT.element())
		 *	.selectOption("Test 3")
		 *	.fromOptionList(Generic.OPTIONS_ELEMENT.element())); }
		 * </pre>
		 * @param element - the search element in the menu
		 */
		public DropdownBuilder searchForOption(TestElement element) {
			this.searchElement = element;
			return this;
		}
		
		/**
		 * <p> Tells the DropdownHelper which WebElement is the 
		 * search element located in the dropdown menu in order to 
		 * narrow down results.
		 * </p>
		 * <p> This is not a required method. It is only needed when the options
		 * in the dropdown are exceedingly large and that being so causes issues
		 * when trying to select a particular option. This gives the ability to
		 * narrow down search results to a particular item.
		 * </p>
		 * <pre>
		 * Ex:
		 * {@code WebElement searchElement = SHelper.get().element().get(Generic.SEARCH_ELEMENT.element());
		 * new DropdownHelper(new DropdownBuilder(new ReportInfo("Test"))
		 *	.clickMenuToOpen(Generic.CLICK_ELEMENT.element())
		 *	.searchForOption(searchElement)
		 *	.selectOption("Test 3")
		 *	.fromOptionList(Generic.OPTIONS_ELEMENT.element())); }
		 * </pre>
		 * @param element - the search element in the menu
		 */
		public DropdownBuilder searchForOption(WebElement element) {
			this.searchWebElement = element;
			return this;
		}
		
		/**
		 * <p> Tells the DropdownHelper the text value of the option that needs to be
		 * selected from the dropdown menu.
		 * </p>
		 * <p> This is a required method. If an option is not 
		 * given, then DropdownHelper will throw an error and request for the 
		 * selectOption() method to be added.
		 * </p>
		 * <pre>
		 * Ex:
		 * {@code new DropdownHelper(new DropdownBuilder(new ReportInfo("Test"))
		 *	.clickMenuToOpen(Generic.CLICK_ELEMENT.element())
		 *	.selectOption("Test 3")
		 *	.fromOptionList(Generic.OPTIONS_ELEMENT.element())); }
		 * </pre>
		 * @param element - the element to be clicked to open the menu
		 */
		public DropdownBuilder selectOption(String value) {
			this.option = value;
			return this;
		}
		
		/**
		 * <p> Tells the DropdownHelper the TestElement in order to gather the total list
		 * of option elements from the dropdown menu so that a single option can be selected 
		 * from the list.
		 * </p>
		 * <p> This is a required method. If the options TestElement is not 
		 * given, then DropdownHelper will throw an error and request for the 
		 * fromOptionList() method to be added.
		 * </p>
		 * <pre>
		 * Ex:
		 * {@code new DropdownHelper(new DropdownBuilder(new ReportInfo("Test"))
		 *	.clickMenuToOpen(Generic.CLICK_ELEMENT.element())
		 *	.selectOption("Test 3")
		 *	.fromOptionList(Generic.OPTIONS_ELEMENT.element())); }
		 * </pre>
		 * @param element - the element to be clicked to open the menu
		 */
		public DropdownBuilder fromOptionList(TestElement element) {
			this.optionsElement = element;
			return this;
		}
		
	}

}
