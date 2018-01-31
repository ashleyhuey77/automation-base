package common.basePage.helpers;

import org.openqa.selenium.WebElement;
import common.basePage.helpers.ClickHelper.ClickBuilder;
import common.basePage.helpers.EnterTextHelper.EnterTextBuilder;
import common.basePage.helpers.OptionSelector.OptionSelectorBuilder;
import common.basePage.valueObjects.ReportInfo;
import common.utils.NullReportTool;
import common.utils.NullReportTool.NullReportBuilder;
import common.utils.managers.LocalReport;
import common.utils.managers.LocalValidation;
import common.utils.managers.SHelper;
import seleniumHelper.builders.WaitBuilder;
import seleniumHelper.enums.Condition;
import seleniumHelper.enums.Via;
import seleniumHelper.enums.Wait;
import seleniumHelper.valueObjects.TestElement;

public class DropdownHelper {
	
	public DropdownHelper(DropdownBuilder builder) throws Exception {
		try {
			new NullReportTool(new NullReportBuilder(new ReportInfo("Option"))
					.value(builder.option)
					.verifyValueIsNotNull(true));
			if (builder.clickElement != null &&
					builder.searchElement != null &&
						builder.optionsElement != null) {
				selectTestElementFromNonDropdown(builder);
			} else if (builder.clickWebElement != null &&
					   		builder.searchWebElement != null &&
					   			builder.optionsElement != null) {
				selectWebElementFromNonDropdown(builder);
			} else {
				throw LocalValidation.getValidations().assertionFailed("An Element was not provided. Unable to select an undefined drop down element. "
    	   				+ "Assure that the following elements have been provided: click element, search element, and options element.");
			}
		} catch (Exception e) {
			throw LocalReport.getReport().reportException(e);
		}	
	}
	
    /**	
     *	<p>
     *	Method to select some option from a drop down based on the text value
     * 	listed as options inside the dropdown.
     *	</p>
     *	<p>The selenium select method would not work for some of the dropdowns
     *	in the newstron application as they are not traditional dropdowns. A new method
     *	had to be created in order to select an option and it uses the 
     *	findEqualOptionInListAndSelectIt method in order to do so.</p>
     *	<p>Method waits for the clicklocator element to display before continuing the
     *	test. If the element is not found, the test fails.</p>
     *	@param option - the text value of the option that needs to be selected from the dropdown
     *	@param clicklocator - the element locator string of the element that will be clicked
     *	@param clickby - the type of locator being used (i.e id, name, csslocator, xpath, etc.) 
     *	for the click element
     *	@param searchlocator - the element locator string of the search box element
     *	@param searchby - the type of locator being used (i.e id, name, csslocator, xpath, etc.) 
     *	for the search box element
     *	@param optionslocator - the element locator string of the options element in the drop down
     *	@param optionsby - the type of locator being used (i.e id, name, csslocator, xpath, etc.) 
     *	for the options element
     *	@param elementBeingTested - the name of the element being tested. This is used for 
     *	reporting so that when it is called the report will reflect an element that is unique to the method 
     *	@param via - the method by which to click a web element (e.g click via 
     * selenium's built in click functionality, javascript, jquery, etc.)
     * @throws Exception 
     */
    protected void selectTestElementFromNonDropdown(DropdownBuilder builder) throws Exception {
        try {
            if (SHelper.get().element().isDisplayed(builder.clickElement.locator, builder.clickElement.by, 10)) {
            		new ClickHelper(new ClickBuilder(new ReportInfo(builder.info.elementTitle()))
            				.clickOn(new TestElement(builder.clickElement.locator, builder.clickElement.by)));
                Thread.sleep(600);
                new EnterTextHelper(new EnterTextBuilder(new ReportInfo(builder.info.elementTitle()))
                		.enterText(builder.option)
                		.into(new TestElement(builder.searchElement.locator, builder.searchElement.by)));
                SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
                		new WaitBuilder().forAMaxTimeOf(30)).on(builder.optionsElement.locator, builder.optionsElement.by);
                Thread.sleep(900);
                new OptionSelector(new OptionSelectorBuilder()
                		.findOption(builder.option)
                		.thatIs(Condition.EQUAL)
                		.For(new TestElement(builder.optionsElement.locator, builder.optionsElement.by)));
            } else {
                throw LocalValidation.getValidations().assertionFailed("Element is not available. Unable to select the " 
                		+ builder.option + " option from the drop down list.");
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }
    
    /**	
     *	<p>
     *	Method to select some option from a drop down based on the text value
     * 	listed as options inside the dropdown.
     *	</p>
     *	<p>The selenium select method would not work for some of the dropdowns
     *	in the newstron application as they are not traditional dropdowns. A new method
     *	had to be created in order to select an option and it uses the 
     *	findEqualOptionInListAndSelectIt method in order to do so.</p>
     *	<p>Method waits for the clicklocator element to display before continuing the
     *	test. If the element is not found, the test fails.</p>
     *	@param option - the text value of the option that needs to be selected from the dropdown
     *	@param clickElement - the element locator string of the element that will be clicked
     *	@param clickby - the type of locator being used (i.e id, name, csslocator, xpath, etc.) for the click element
     *	@param searchElement - the element locator string of the search box element
     *	@param searchby - the type of locator being used (i.e id, name, csslocator, xpath, etc.) for the search box element
     *	@param optionslocator - the element locator string of the options element in the drop down
     *	@param optionsby - the type of locator being used (i.e id, name, csslocator, xpath, etc.) for the options element
     *	@param elementBeingTested - the name of the element being tested. This is used for 
     *							  reporting so that when it is called the report will reflect an
     *							  element that is unique to the method
     * @param via - the method by which to click a web element (e.g click via 
     * selenium's built in click functionality, javascript, jquery, etc.)
     * @throws Exception 
     */
    protected void selectWebElementFromNonDropdown(DropdownBuilder builder) throws Exception {
        try {
            if (SHelper.get().element().isDisplayed(builder.clickWebElement, 10)) {
                SHelper.get().click(Via.SELENIUM).on(builder.clickWebElement);
                Thread.sleep(600);
                new EnterTextHelper(new EnterTextBuilder(new ReportInfo(builder.info.elementTitle()))
                		.enterText(builder.option)
                		.into(builder.searchWebElement));
                SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
                		new WaitBuilder().forAMaxTimeOf(10)).on(builder.optionsElement.locator, builder.optionsElement.by);
                Thread.sleep(900);
                new OptionSelector(new OptionSelectorBuilder()
                		.findOption(builder.option)
                		.thatIs(Condition.EQUAL)
                		.For(new TestElement(builder.optionsElement.locator, builder.optionsElement.by)));
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
		
		public DropdownBuilder(ReportInfo info) {
			this.info = info;
		}
		
		public DropdownBuilder clickMenuToOpen(TestElement element) {
			this.clickElement = element;
			return this;
		}
		
		public DropdownBuilder clickMenuToOpen(WebElement element) {
			this.clickWebElement = element;
			return this;
		}
		
		public DropdownBuilder searchForOption(TestElement element) {
			this.searchElement = element;
			return this;
		}
		
		public DropdownBuilder searchForOption(WebElement element) {
			this.searchWebElement = element;
			return this;
		}
		
		public DropdownBuilder selectOption(String value) {
			this.option = value;
			return this;
		}
		
		public DropdownBuilder fromOptionList(TestElement element) {
			this.optionsElement = element;
			return this;
		}
		
	}

}
