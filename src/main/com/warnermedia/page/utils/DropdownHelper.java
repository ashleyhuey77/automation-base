package com.warnermedia.page.utils;

import java.util.Objects;

import com.warnermedia.page.core.PageUtils;
import com.warnermedia.page.core.web.Fetch;
import com.warnermedia.page.core.web.Type;
import com.warnermedia.selenium.wait.WaitBuilder;
import org.openqa.selenium.WebElement;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.report.LocalValidation;
import com.warnermedia.selenium.shared.Via;
import com.warnermedia.selenium.wait.Condition;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.utils.Validator;

public class DropdownHelper extends PageUtils {

	//required params
	private Fetch info;

	//optional params
	private Type clickElement;
	private Type searchElement;
	private Type optionsElement;
	private WebElement clickWebElement;
	private WebElement searchWebElement;
	private String option;
	
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
	 * {@code new DropdownHelper(new DropdownHelper(new ReportInfo("Test"))
	 *	.clickMenuToOpen(Generic.CLICK_ELEMENT.element())
	 *	.searchForOption(Generic.SEARCH_ELEMENT.element())
	 *	.selectOption("Test 3")
	 *	.fromOptionList(Generic.OPTIONS_ELEMENT.element())); }
	 * </pre>
	 * @throws TestException
	 */
	public DropdownHelper() throws TestException {
		this.info = new Fetch();
	}
	
    private void selectTestElementFromNonDropdown() throws TestException {
        try {
        	if (SHelper.get().element().isDisplayed(clickElement.element(), 3)) {
				click().on(clickElement).start();
				Thread.sleep(600);
                if (searchElement != null) {
                	enter().text(option).into(searchElement).start();
                }
                SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
                		new WaitBuilder().forAMaxTimeOf(30)).on(optionsElement.element());
                Thread.sleep(900);
                selector()
						.findOption(option)
						.thatIs(Condition.EQUAL)
						.in(optionsElement).start();
        	} else {
                throw LocalValidation.getValidations().assertionFailed("Element is not available. Unable to select the " 
                		+ option + " option from the drop down list.");
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    private void selectWebElementFromNonDropdown() throws TestException {
        try {
        	if (SHelper.get().element().isDisplayed(clickWebElement, 10)) {
                SHelper.get().click(Via.SELENIUM).on(clickWebElement);
                Thread.sleep(600);
                if (searchWebElement != null) {
                	enter()
							.text(option)
							.into(searchElement)
							.using(searchWebElement).start();
                }
                SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
                		new WaitBuilder().forAMaxTimeOf(10)).on(optionsElement.element());
                Thread.sleep(900);
                selector()
						.findOption(option)
						.thatIs(Condition.EQUAL)
						.in(optionsElement).start();
        	} else {
                throw LocalValidation.getValidations().assertionFailed("Element is not availale. Unable to select the " 
                		+ option + " option from the drop down list.");
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
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
	 * {@code new DropdownHelper(new DropdownHelper(new ReportInfo("Test"))
	 *	.clickMenuToOpen(Generic.CLICK_ELEMENT.element())
	 *	.selectOption("Test 3")
	 *	.fromOptionList(Generic.OPTIONS_ELEMENT.element())); }
	 * </pre>
	 * @param element - the element to be clicked to open the menu
	 */
	public DropdownHelper clickMenuToOpen(Type element) {
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
	 * new DropdownHelper(new DropdownHelper(new ReportInfo("Test"))
	 *	.clickMenuToOpen(clickElement)
	 *	.selectOption("Test 3")
	 *	.fromOptionList(Generic.OPTIONS_ELEMENT.element())); }
	 * </pre>
	 * @param element - the element to be clicked to open the menu
	 */
	public DropdownHelper usingMenu(WebElement element) {
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
	 * {@code new DropdownHelper(new DropdownHelper(new ReportInfo("Test"))
	 *	.clickMenuToOpen(Generic.CLICK_ELEMENT.element())
	 *	.searchForOption(Generic.SEARCH_ELEMENT.element())
	 *	.selectOption("Test 3")
	 *	.fromOptionList(Generic.OPTIONS_ELEMENT.element())); }
	 * </pre>
	 * @param element - the search element in the menu
	 */
	public DropdownHelper searchForOption(Type element) {
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
	 * new DropdownHelper(new DropdownHelper(new ReportInfo("Test"))
	 *	.clickMenuToOpen(Generic.CLICK_ELEMENT.element())
	 *	.searchForOption(searchElement)
	 *	.selectOption("Test 3")
	 *	.fromOptionList(Generic.OPTIONS_ELEMENT.element())); }
	 * </pre>
	 * @param element - the search element in the menu
	 */
	public DropdownHelper usingSearch(WebElement element) {
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
	 * {@code new DropdownHelper(new DropdownHelper(new ReportInfo("Test"))
	 *	.clickMenuToOpen(Generic.CLICK_ELEMENT.element())
	 *	.selectOption("Test 3")
	 *	.fromOptionList(Generic.OPTIONS_ELEMENT.element())); }
	 * </pre>
	 */
	public DropdownHelper selectOption(String value) {
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
	 * {@code new DropdownHelper(new DropdownHelper(new ReportInfo("Test"))
	 *	.clickMenuToOpen(Generic.CLICK_ELEMENT.element())
	 *	.selectOption("Test 3")
	 *	.fromOptionList(Generic.OPTIONS_ELEMENT.element())); }
	 * </pre>
	 * @param element - the element to be clicked to open the menu
	 */
	public DropdownHelper fromOptionList(Type element) {
		this.optionsElement = element;
		return this;
	}

	public DropdownHelper start() throws TestException {
		try {
			Validator.of(option).validate(Objects::nonNull, result -> option != null, "Options is null. Add selectOption() method.").get();
			Validator.of(optionsElement).validate(Objects::nonNull, result -> optionsElement != null, "Options element is null. Add fromOptionList() method.").get();
			if (clickWebElement != null &&
					optionsElement != null) {
				selectWebElementFromNonDropdown();
			} else if (clickElement != null &&
					optionsElement != null) {
				selectTestElementFromNonDropdown();
			} else {
				Validator.of(clickElement).validate(Objects::nonNull, result -> clickElement != null, "Click element is null. Add clickMenuToOpen() method.").get();
				Validator.of(clickWebElement).validate(Objects::nonNull, result -> clickWebElement != null, "Click element is null. Add clickMenuToOpen() method.").get();
			}
		} catch (Exception e) {
			throw LocalReport.getReport().reportException(e);
		}
		return this;
	}

}
