package commonClasses.sharedPageClasses;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import commonClasses.sharedUtils.TestUtils;
import commonClasses.sharedUtils.managers.*;
import seleniumHelper.enums.Wait;
import seleniumHelper.enums.*;
import org.openqa.selenium.support.ui.*;

/**
 * <h1>PageHelper</h1>
 * <p>The abstract PageHelper class was created to hold all methods that 
 * are shared between page classes in order to reduce repetitive code so that
 * when a page extends the PageHelper class, it has access to shared methods.</p>
 * <p>If code is used between two or more page classes regardless of the 
 * method it is used in, it should be consolidated into a shared void method 
 * and called from the PageHelper class.</p>
 * <p>PageHelper methods should not return any page class. It is acceptible for a 
 * PageHelper method to return void or a data type object or a list, but only
 * in the event that the method is used between multiple page classes.</p>
 * @author ashleyhuey
 *
 */
public abstract class PageHelper {

	/**
	 * <p>The cssSelector identifier is used to specify that the selector string
	 * entered as a param for a particular method is written in cssSelector format.</p>
	 * <p>It is turned to string format from the IdentifyBy enum defined in the 
	 * seleniumHelper package in order to prevent unknown selenium "By" types. Which
	 * in turn prevents unnecessary exceptions.</p>
	 * <p>Examples of cssSelectors in the correct format: </p>
	 * 1. {@code div[class='Test']}
	 * </br>
	 * 2. {@code div > div > p}
	 * </br>
	 * 3. {@code #TestId}
	 */
    protected static String cssSelector = IdentifyBy.CssSelector.toString();
	/**
	 * <p>The id identifier is used to specify that the selector string
	 * entered as a param for a particular method is written in the id attribute 
	 * value format.</p>
	 * <p>It is turned to string format from the IdentifyBy enum defined in the 
	 * seleniumHelper package in order to prevent unknown selenium "By" types. Which
	 * in turn prevents unnecessary exceptions.</p>
	 * <p>Examples of Ids in the correct format: </p>
	 * 1. {@code testId}
	 */
    protected static String id = IdentifyBy.Id.toString();
	/**
	 * <p>The name identifier is used to specify that the selector string
	 * entered as a param for a particular method is written in the name attribute 
	 * value format.</p>
	 * <p>It is turned to string format from the IdentifyBy enum defined in the 
	 * seleniumHelper package in order to prevent unknown selenium "By" types. Which
	 * in turn prevents unnecessary exceptions.</p>
	 * <p>Examples of names in the correct format: </p>
	 * 1. {@code testName}
	 */
    protected static String name = IdentifyBy.Name.toString();
	/**
	 * <p>The xpath identifier is used to specify that the selector string
	 * entered as a param for a particular method is written in xpath format.</p>
	 * <p>It is turned to string format from the IdentifyBy enum defined in the 
	 * seleniumHelper package in order to prevent unknown selenium "By" types. Which
	 * in turn prevents unnecessary exceptions.</p>
	 * <p>Examples of xpath in the correct format: </p>
	 * 1. {@code /bookstore/book[position()<3]}
	 * </br>
	 * 2. {@code //title[@lang]}
	 * </br>
	 * 3. {@code //title[@lang='en']}
	 */
    protected static String xpath = IdentifyBy.Xpath.toString();
	/**
	 * <p>The ClassName identifier is used to specify that the selector string
	 * entered as a param for a particular method is written in the Class attribute 
	 * value format.</p>
	 * <p>It is turned to string format from the IdentifyBy enum defined in the 
	 * seleniumHelper package in order to prevent unknown selenium "By" types. Which
	 * in turn prevents unnecessary exceptions.</p>
	 * <p>Examples of class names in the correct format: </p>
	 * 1. {@code testClass}
	 */
    protected static String className = IdentifyBy.ClassName.toString();
	/**
	 * <p>The TagName identifier is used to specify that the selector string
	 * entered as a param for a particular method is written in the html tag format.</p>
	 * <p>It is turned to string format from the IdentifyBy enum defined in the 
	 * seleniumHelper package in order to prevent unknown selenium "By" types. Which
	 * in turn prevents unnecessary exceptions.</p>
	 * <p>Examples of tag names in the correct format: </p>
	 * 1. {@code p}
	 * </br>
	 * 2. {@code button}
	 * </br>
	 * 3. {@code select}
	 */
    protected static String tagName = IdentifyBy.TagName.toString();
	/**
	 * <p>The LinkText identifier is used to specify that the selector string
	 * entered as a param for a particular method is written in the Link text 
	 * value format.</p>
	 * <p>It is turned to string format from the IdentifyBy enum defined in the 
	 * seleniumHelper package in order to prevent unknown selenium "By" types. Which
	 * in turn prevents unnecessary exceptions.</p>
	 * <p>Examples of link text in the correct format: </p>
	 * 1. {@code Link to Page}
	 */
    protected static String linkText = IdentifyBy.LinkText.toString();
	/**
	 * <p>The PartialLinkText identifier is used to specify that the selector string
	 * entered as a param for a particular method is written in a portion of the Link text 
	 * value format.</p>
	 * <p>It is turned to string format from the IdentifyBy enum defined in the 
	 * seleniumHelper package in order to prevent unknown selenium "By" types. Which
	 * in turn prevents unnecessary exceptions.</p>
	 * <p>Examples of partial link text in the correct format: </p>
	 * 1. {@code Link to}
	 */
    protected static String partialLinkText = IdentifyBy.PartialLinkText.toString();

    /**
     * <h1>PageHelper</h1>
     * <p>The abstract PageHelper class was created to hold all void methods that 
     * are shared between page classes in order to reduce repetitive code so that
     * when a page extends the PageHelper class, it has access to shared methods.</p>
     * <p>If code is used between two or more page classes regardless of the 
     * method it is used in, it should be consolidated into a shared void method 
     * and called from the PageHelper class.</p>
     * <p>PageHelper methods should not return any page class. It is acceptible for a 
     * PageHelper method to return void or a data type object or a list, but only
     * in the event that the method is used between multiple page classes.</p>
     * @author ashleyhuey
     *
     */
    public PageHelper() throws Exception {

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
     * @throws Exception
     */
    protected void enterAvalueIntoATextField(String valueToBeEntered, String selector, String byValue, String elementBeingTested)
    throws Exception {
        try {
            if (SHelper.get().element().isDisplayed(selector, byValue, 5)) {
                clickSomeElement(Via.SELENIUM, selector, byValue, elementBeingTested);
                clearAllTextByBackspacing(selector, byValue);
                if (!TestUtils.isNullOrBlank(valueToBeEntered)) {
                    SHelper.get().enter().clear(selector, byValue);
                    clickSomeElement(Via.SELENIUM, selector, byValue, elementBeingTested);
                    SHelper.get().enter().textInto(selector, byValue, valueToBeEntered);
                }
                LocalReport.getReport().reportDoneEvent(elementBeingTested + " has been entered successfully");
            } else {
                throw LocalValidation.getValidations().assertionFailed(
                    elementBeingTested + " does not display as expected. Unable to enter text in this field.");
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
     * @throws Exception
     */
    protected void enterAvalueIntoATextField(String valueToBeEntered, WebElement element, String elementBeingTested)
    throws Exception {
        try {
            if (SHelper.get().element().isDisplayed(element, 5)) {
                SHelper.get().click(Via.SELENIUM).on(element);
                clearAllTextByBackspacing(element);

                if (!TestUtils.isNullOrBlank(valueToBeEntered)) {
                    SHelper.get().enter().clear(element);
                    SHelper.get().click(Via.SELENIUM).on(element);
                    SHelper.get().enter().textInto(element, valueToBeEntered);
                }
                LocalReport.getReport().reportDoneEvent(elementBeingTested + " has been entered successfully");
            } else {
                throw LocalValidation.getValidations().assertionFailed(
                    elementBeingTested + " does not display as expected. Unable to enter text in this field.");
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    /**	
     * <p>This s a shared generic method to click an element on the page.</p>
     * <p>The method will catch any exceptions thrown and try to execute/exhaust
     * every method of clicking possible (e.g via selenium, javascript, jquery, etc.)
     * before throwing the exception and failing the test. This is to reduce flimsy 
     * click failures that have been proven to happen infrequently in the NewsApps 
     * application.</p>
     * <p>This method will wait for the click element to display on the 
     * page before attempting to click it. If the element is not 
     * found the test is failed.</p>
     * <p></p>
     * <p></p>
     * @param via - the method by which to click a web element (e.g click via 
     * selenium's built in click functionality, javascript, jquery, etc.)
     * @param selector - the webelement selector string for the field that
     * the text will be entered into
     * @param byValue - the type of selector being used (i.e id, name, cssSelector, xpath, etc.)
     * @param elementBeingTested - the name of the element being tested. This is used for 
     * reporting so that when it is called the report will reflect an
     * element that is unique to the method
     * @param index - this is an optional parameter and should be specified when it is necessary to 
     * click on a web element that is stored in a list of webelements.
     * @throws Exception 
     */
    protected void clickSomeElement(Via via, String selector, String byValue, String elementBeingTested, int...index) throws Exception {
        try {
            if (SHelper.get().element().isDisplayed(selector, byValue, 5)) {
                tryAllClicks(selector, byValue, via, index);
                LocalReport.getReport().reportDoneEvent(elementBeingTested + " clicked successfully.");
            } else {
                throw LocalValidation.getValidations().assertionFailed("Element is not on the page. Unable to click the " 
                		+ elementBeingTested);
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    private void tryAllClicks(String html, String byValue, Via via, int...index) throws Exception {
        try {
            indexCheckClick(via, html, byValue, index);
        } catch (Exception ex) {
            try {
                indexCheckClick(Via.SELENIUM, html, byValue, index);
            } catch (Exception e2) {
                try {
                    indexCheckClick(Via.JAVASCRIPT, html, byValue, index);
                } catch (Exception e3) {
                    try {
                        indexCheckClick(Via.JQUERY, html, byValue, index);
                    } catch (Exception e4) {
                        LocalValidation.getValidations().assertionFailed("Test has exhausted all different click "
                        		+ "methods. Not able to click element with the specified selector.");
                        throw LocalReport.getReport().reportException(e4);
                    }
                }
            }
        }
    }

    private void indexCheckClick(Via via, String html, String byValue, int...index) throws Exception {
        try {
            if (index.length > 0) {
                SHelper.get().click(via).on(html, byValue, index[0]);
            } else {
                SHelper.get().click(via).on(html, byValue);
            }
        } catch (Exception e2) {
            throw e2;
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
     *	<p>Method waits for the clickSelector element to display before continuing the
     *	test. If the element is not found, the test fails.</p>
     *	@return void
     *	@param option - the text value of the option that needs to be selected from the dropdown
     *	@param clickSelector - the element selector string of the element that will be clicked
     *	@param clickByValue - the type of selector being used (i.e id, name, cssSelector, xpath, etc.) 
     *	for the click element
     *	@param searchSelector - the element selector string of the search box element
     *	@param searchByValue - the type of selector being used (i.e id, name, cssSelector, xpath, etc.) 
     *	for the search box element
     *	@param optionsSelector - the element selector string of the options element in the drop down
     *	@param optionsByValue - the type of selector being used (i.e id, name, cssSelector, xpath, etc.) 
     *	for the options element
     *	@param elementBeingTested - the name of the element being tested. This is used for 
     *	reporting so that when it is called the report will reflect an element that is unique to the method 
     *	@param via - the method by which to click a web element (e.g click via 
     * selenium's built in click functionality, javascript, jquery, etc.)
     * @throws Exception 
     */
    protected void selectSomeOptionFromNonDropdown(String option, String clickSelector, String clickByValue, String searchSelector, 
    		String searchByValue, String optionsSelector, String optionsByValue, String elementBeingTested, Via via) throws Exception {
        try {
            if (SHelper.get().element().isDisplayed(clickSelector, clickByValue, 10)) {
                clickSomeElement(Via.SELENIUM, clickSelector, clickByValue, elementBeingTested);
                Thread.sleep(600);
                enterAvalueIntoATextField(option, searchSelector, searchByValue, elementBeingTested);
                SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on(optionsSelector, optionsByValue, 10);
                Thread.sleep(900);
                findEqualOptionInListAndSelectIt(via, optionsSelector, optionsByValue, option);
            } else {
                throw LocalValidation.getValidations().assertionFailed("Element is not availale. Can not select the " 
                		+ elementBeingTested + " from the drop down list.");
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
     *	<p>Method waits for the clickSelector element to display before continuing the
     *	test. If the element is not found, the test fails.</p>
     *	@param option - the text value of the option that needs to be selected from the dropdown
     *	@param clickElement - the element selector string of the element that will be clicked
     *	@param clickByValue - the type of selector being used (i.e id, name, cssSelector, xpath, etc.) for the click element
     *	@param searchElement - the element selector string of the search box element
     *	@param searchByValue - the type of selector being used (i.e id, name, cssSelector, xpath, etc.) for the search box element
     *	@param optionsSelector - the element selector string of the options element in the drop down
     *	@param optionsByValue - the type of selector being used (i.e id, name, cssSelector, xpath, etc.) for the options element
     *	@param elementBeingTested - the name of the element being tested. This is used for 
     *							  reporting so that when it is called the report will reflect an
     *							  element that is unique to the method
     * @param via - the method by which to click a web element (e.g click via 
     * selenium's built in click functionality, javascript, jquery, etc.)
     * @throws Exception 
     */
    protected void selectSomeOptionFromNonDropdown(String option, WebElement clickElement, WebElement searchElement, String optionsSelector, 
    		String optionsByValue, String elementBeingTested, Via via) throws Exception {
        try {
            if (SHelper.get().element().isDisplayed(clickElement, 10)) {
                SHelper.get().click(Via.SELENIUM).on(clickElement);
                Thread.sleep(600);
                enterAvalueIntoATextField(option, searchElement, elementBeingTested);
                SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on(optionsSelector, optionsByValue, 10);
                Thread.sleep(900);
                findEqualOptionInListAndSelectIt(via, optionsSelector, optionsByValue, option);
            } else {
                throw LocalValidation.getValidations().assertionFailed("Element is not availale. Can not select the " 
                		+ elementBeingTested + " from the drop down list.");
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    /**
     * <p> Method to verify some element is present on the page using the SHelper isDisplayed 
     *  method.</p>
     * <p>If the element is not displayed on te page, the test fails.</p>
     * @param selector - the webelement selector string for the field that the text
     * will be entered into
     * @param byValue - the type of selector being used (i.e id, name, cssSelector,
     * xpath, etc.)
     * @param elementBeingTested - the name of the element being tested. This is used for
     * reporting so that when it is called the report will reflect an element that is 
     * unique to the method
     * @throws Exception
     */
    protected void verifySomeElementIsPresent(String selector, String byValue, String elementBeingTested)
    throws Exception {
        try {
            if (SHelper.get().element().isDisplayed(selector, byValue, 10)) {
                LocalValidation.getValidations()
                    .assertionPass(elementBeingTested + " displays in the page as expected.");
            } else {
                throw LocalValidation.getValidations().assertionFailed(
                    elementBeingTested + " should display in the page. It does not display as expected.");
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    /**
     * <p> Method to verify some element is not present on the page using the SHelper isDisplayed 
     *  method.</p>
     * <p>If the element is displayed on te page, the test fails.</p>
     * @param selector - the webelement selector string for the field that the text
     * will be entered into
     * @param byValue - the type of selector being used (i.e id, name, cssSelector,
     * xpath, etc.)
     * @param elementBeingTested - the name of the element being tested. This is used for
     * reporting so that when it is called the report will reflect an element that is 
     * unique to the method
     * @throws Exception
     */
    protected void verifySomeElementIsNotPresent(String selector, String byValue, String elementBeingTested)
    throws Exception {
        try {
            if (!SHelper.get().element().isDisplayed(selector, byValue, 5)) {
                LocalValidation.getValidations().assertionPass(elementBeingTested + " does not display in the page.");
            } else {
                throw LocalValidation.getValidations().assertionFailed(
                    elementBeingTested + " should not display in the page. It does display. This is not expected.");
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }


    /**	
     *	<p>
     *	Method to find some element in a list of elements and select it based on the visible 
     *	text that displays within the element.
     *	</p>
     *	<p>If the text is not found in any element in the list of elements, the test fails.</p>
     *	<p>The text must exactly equal the expected option text (letter casing is not a factor), but
     *	spelling and in some cases spacing could cause the option to not be found in the list.</p>
     *	@param via - the method by which to click a web element (e.g click via 
     * selenium's built in click functionality, javascript, jquery, etc.)
     * 	@param selector - the webelement selector string for the field that the text should
     * 	be found in. This selector should create a list of webelements.
     * 	@param by - the type of selector being used (i.e id, name, cssSelector,
     * xpath, etc.)
     *	@param expectedOption the option (based on text value) that is expected to be 
     *	found in the list of options.
     * @throws Exception 
     */

    protected void findEqualOptionInListAndSelectIt(Via via, String selector, String by, String expectedOption) throws Exception {
        try {
            List < WebElement > webElements = SHelper.get().element().getListOf(selector, by);
            String value = null;
            for (int i = 0; i < webElements.size(); i++) {
                String actualOption = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(webElements.get(i));
                if (actualOption.toLowerCase().trim().equals(expectedOption.toLowerCase().trim())) {
                    tryAllClicks(selector, by, via, i);
                    value = actualOption;
                    LocalReport.getReport().reportDoneEvent(expectedOption + " has been selected successfully.");
                    break;
                }
            }

            if (value == null) {
                throw LocalValidation.getValidations().assertionFailed(expectedOption + " is not found in the "
                		+ "list of available options. Unable to select the expected option.");
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    /**	
     *	<p>
     *	Method to find some element in a list of elements and select it based on the visible 
     *	text that displays within the element.
     *	</p>
     *	<p>If the text is not found in any element in the list of elements, the test fails.</p>
     *	<p>The text must only contain the expected option text (letter casing is not a factor).</p>
     *	@param via - the method by which to click a web element (e.g click via 
     * selenium's built in click functionality, javascript, jquery, etc.)
     * 	@param selector - the webelement selector string for the field that the text should
     * 	be found in. This selector should create a list of webelements.
     * 	@param by - the type of selector being used (i.e id, name, cssSelector,
     * xpath, etc.)
     *	@param expectedOption the option (based on text value) that is expected to be 
     *	found in the list of options.
     * @throws Exception 
     */

    protected void findOptionContainedInListAndSelectIt(Via via, String selector, String by, String expectedOption) throws Exception {
        try {
            String value = null;
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on(selector, by, 40);
            List < WebElement > webElements = SHelper.get().element().getListOf(selector, by);
            for (int i = 0; i < webElements.size(); i++) {
                WebElement element = webElements.get(i);
                String actualOption = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(element);
                if (actualOption.toLowerCase().trim().contains(expectedOption.toLowerCase().trim())) {
                    tryAllClicks(selector, by, via, i);
                    value = actualOption;
                    LocalReport.getReport().reportDoneEvent(expectedOption + " has been selected successfully.");
                    break;
                }
            }

            if (value == null) {
                throw LocalValidation.getValidations().assertionFailed(expectedOption + " is not found in the "
                		+ "list of available options. Unable to select the expected option.");
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
    protected void verifySomeElementContainsTheExpectedText(WebElement element, String expectedText,
        String elementBeingTested, Boolean removeAllSpaces) throws Exception {
        try {
            String actualText = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(element);

            if (removeAllSpaces) {
                actualText = actualText.replace(" ", "");
                expectedText = expectedText.replace(" ", "");
            }

            if (actualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
                LocalValidation.getValidations()
                    .assertionPass(elementBeingTested + " contains the correct text: " + actualText);
            } else {
                throw LocalValidation.getValidations()
                    .assertionFailed(elementBeingTested + " does not contain the correct text. Expected text: " +
                        expectedText + ". Actual text: " + actualText);
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
     * @param selector - the webelement selector string for the field that the text
     * will be compared against.
     * @param by - the type of selector being used (i.e id, name, cssSelector,
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
    protected void verifySomeElementContainsTheExpectedText(String selector, String by, String expectedText,
        String elementBeingTested, Boolean removeAllSpaces) throws Exception {
        try {
            WebElement element = SHelper.get().element().get(selector, by);
            String actualText = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(element);

            if (removeAllSpaces) {
                actualText = actualText.replace(" ", "");
                expectedText = expectedText.replace(" ", "");
            }

            if (actualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
                LocalValidation.getValidations()
                    .assertionPass(elementBeingTested + " contains the correct text: " + actualText);
            } else {
                throw LocalValidation.getValidations()
                    .assertionFailed(elementBeingTested + " does not contain the correct text. Expected text: " +
                        expectedText + ". Actual text: " + actualText);
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    /**
     * <p>Method to verify a field is blank utilizing the javascript function of
     * getting a value from a text box webelement.</p>
     * <p>This method is typically only used for validating a textbox or textarea 
     * is blank and void of text. It is not typcially used to verify other elements
     * such as divs or table rows/columns, etc. are blank.</p>
     * @param selector - the webelement selector string for the field that
     * will be evaluated
     * @param byValue - the type of selector being used (i.e id, name, cssSelector,
     * xpath, etc.)
     * @param webElementIndex - the index of the element if there are more than one elements
     * with the same selector
     * @param elementBeingTested - the name of the element being tested. This is used for
     * reporting so that when it is called the report will reflect an
     * element that is unique to the method
     * @throws Exception
     */
    protected void verifyTextFieldIsBlank(String selector, String byValue, String webElementIndex,
        String elementBeingTested) throws Exception {
        try {
            String actualValueInTextBox = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom(selector, byValue,
                webElementIndex);
            if (TestUtils.isNullOrBlank(actualValueInTextBox)) {
                LocalValidation.getValidations().assertionPass(elementBeingTested + " is blank as expected.");
            } else {
                throw LocalValidation.getValidations()
                    .assertionFailed(elementBeingTested +
                        " should be blank but is retaining a value instead. The value being retained is " +
                        actualValueInTextBox);
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
     * @param selector - the webelement selector string for the field of the text
     * being verified
     * @param byValue - the type of selector being used (i.e id, name, cssSelector,
     * xpath, etc.)
     * @param webElementIndex - the index of the element if there are more than one elements
     * with the same selector. Leave this value null to not include an index.
     * @param expectedText - the text that is expected to be in the element
     * @param elementBeingTested - the name of the element being tested. This is used for
     * reporting so that when it is called the report will reflect an
     * element that is unique to the method
     * @throws Exception
     */
    protected void verifyTextInTextField(String selector, String byValue, String webElementIndex, String expectedText,
        String elementBeingTested, Boolean removeAllSpaces) throws Exception {
        try {
            String index = null;
            if (!TestUtils.isNullOrBlank(webElementIndex)) {
                index = webElementIndex;
            }
            String actualValueInTextBox = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom(selector, byValue,
                index);
            if (actualValueInTextBox.contains("\n")) {
                actualValueInTextBox = actualValueInTextBox.replace("\n", " ");
            }

            if (removeAllSpaces) {
                actualValueInTextBox = actualValueInTextBox.replace(" ", "");
                expectedText = expectedText.replace(" ", "");
            }

            if (actualValueInTextBox.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
                LocalValidation.getValidations()
                    .assertionPass(elementBeingTested + " contains " + actualValueInTextBox + " as expected.");
            } else {
                throw LocalValidation.getValidations()
                    .assertionFailed(elementBeingTested + " should contain " + expectedText +
                        " but is retaining an incorrect value instead. The value being retained is " +
                        actualValueInTextBox);
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    /**
     * <p> enum of the accepted timezones within the newstron app </p>
     */
    public enum Timezones {
        Pacific,
        Central,
        Eastern,
        GMT,
        Dubai,
        HK
    }

    /**
     * <p> Entity enum that holds all of the api models object categories
     * </p>
     */

    public enum Entity {
        Scheduler,
        MS2,
        CrashRecords,
        Requests
    }

    /**
     * <p> Method to get the current date and split it into a string array
     * </p>
     * 
     * @return String[]
     */
    protected String[] getCurrentSplitDate() throws Exception {
        String[] currentDate = null;
        String date = TestUtils.GetCurrentDateTime("dd-MM-yyyy");
        currentDate = date.split("-");
        return currentDate;
    }

    /**
     * <p> Method to get the total days in the current month of the
     * current year - taking into account leap year </p>
     * 
     */

    protected int getTotalDaysInMonth() throws Exception {
        int totalDaysInMonth = 0;
        int currentYear = Integer.parseInt(getCurrentSplitDate()[2]);
        int currentDay = Integer.parseInt(getCurrentSplitDate()[0]);
        int currentMonth = Integer.parseInt(getCurrentSplitDate()[1]);

        Calendar cal = new GregorianCalendar(currentYear, currentMonth - 1, currentDay);

        totalDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return totalDaysInMonth;
    }

    /**
     * <p> Method to get the future date based on user input of how many
     * days they wish to select from today's date </p>
     * 
     * @return int
     * @param daysOutFromCurrentDay - the number of days out from today to select
     */

    protected int getFutureDate(int daysOutFromCurrentDay) throws Exception {
        int futureDate = 0;
        int currentDay = Integer.parseInt(getCurrentSplitDate()[0]);
        futureDate = currentDay + daysOutFromCurrentDay;
        return futureDate;
    }

    /**
     * <p> Days of the week combinations enum. This enum holds all the
     * different combinations of days of the week that may need to be selected
     * in order to properly test as listed in the Newstron Application. </p>
     */
    public enum DaysOfTheWeek {
        ALL,
        WEEKDAYS,
        WEEKENDS,
    }

    /**
     * <p> Method to clean up static global variables. </p>
     * @param value - the static global value that needs to be cleaned up. Is set to
     * null if it is not already null or empty
     */
    protected void cleanUp(String value) {
        if (!TestUtils.isNullOrBlank(value)) {
            value = null;
        }
    }

    /**
     * <p>Method to evaluate whether a variable is null and fail the test
     * case if it is null. Used primarily in for loops to assure the loop is
     * executed properly and fails at the right place if not.</p>
     * @param variable - the string variable being evaluated
     * @param passMessage - the message for reporting if the test passes
     * @param failMessage - the message for reporting if the test fails
     * @throws Exception
     */
    protected void failTestIfVariableIsNull(String variable, String passMessage, String failMessage) throws Exception {
        if (variable != null) {
            LocalValidation.getValidations().assertionPass(passMessage);
        } else {
            throw LocalValidation.getValidations().assertionFailed(failMessage);
        }
    }

    /**
     * <p> Method to remove an unwanted character or to replace it with a
     * different character if it is present in the String.</p>
     * @param variable - the string variable that is to be modified
     * @param character - the character or string that is unwanted that will be removed
     * or replaced if present in the variable
     * @param replaceValue - the value to replace the character. Leave as empty string to
     * remove character completely.
     * @return String
     * @throws Exception
     */
    protected String removeOrChangeUnwantedCharacter(String variable, String character, String replaceValue)
    throws Exception {
        String result = null;
        try {
            if (variable.contains(character)) {
                result = variable.replace(character, replaceValue);
            } else {
                result = variable;
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
        return result;
    }

    /**
     * <p> Method to verify an expected value matches an actual value.</p>
     * <p>Method is primarily used to compare api actual values with
     * expected values, but can also be used for other comparisons.</p>
     * <p>Method first evaluates whether the expected value and/or the actual value is
     * null or an empty string. If one of them are null or empty the test is failed. Then
     * the comparison is made.</p>
     * <p>The text must exactly equal the expected option text (letter casing is not a factor), but
     *	spelling and in some cases spacing could cause the option to not match.</p>
     * @param expectedValue - the value that is expected to have returned in the api
     * response
     * @param actualValue - the actual value that returned in the api response
     * @param variableBeingTested - the name of the variable being tested. This is used for
     * reporting so that when it is called the report will reflect wording that is unique to the
     * variable it is testing.
     * @throws Exception
     */
    protected void verifyTheActualValueMatchesTheExpectedValue(String expectedValue, String actualValue,
        String variableBeingTested) throws Exception {
        try {
            if (!TestUtils.isNullOrBlank(expectedValue) && !TestUtils.isNullOrBlank(actualValue)) {
                if (actualValue.toLowerCase().trim().equals(expectedValue.toLowerCase().trim())) {
                    LocalValidation.getValidations()
                        .assertionPass(actualValue + " is set correctly in " + variableBeingTested);
                } else {
                    throw LocalValidation.getValidations().assertionFailed(
                        expectedValue + " is not set as expected. " + actualValue + " is set instead.");
                }
            } else {
                throw LocalValidation.getValidations()
                    .assertionFailed(variableBeingTested +
                        " returned null in either the actual or the expected variable that was set. "
                        + "Check the test to verify all variables are being assigned a value appropriately. Actual: " +
                        actualValue + ". Expected: " + expectedValue);
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    /**
     * <p> Method to add i number of days to a date </p>
     * @return String
     * @param date - the date that needs to be subtracted from
     * @param totalDays - the total number of days in the month
     * @param daysToAdd - the number of days to add to the date
     * @throws Exception
     */
    protected String subtractDays(String date, int totalDays, int daysToAdd) throws Exception {
        String addedDay = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
            Date myDate = format.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(myDate);
            int days = totalDays - daysToAdd - 1;
            cal.add(Calendar.DATE, days);
            myDate = cal.getTime();
            String dummyDate = format.format(myDate);
            addedDay = dummyDate.startsWith("0") ? dummyDate.substring(1) : dummyDate;
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
        return addedDay;
    }

    /**
     * <p> Method to verify an expected value contains an actual value.</p>
     * <p>Method is primarily used to compare api actual values with
     * expected values, but can also be used for other comparisons.</p>
     * <p>Method first evaluates whether the expected value and/or the actual value is
     * null or an empty string. If one of them are null or empty the test is failed. Then
     * the comparison is made.</p>
     * <p>The text must only contain the expected text (letter casing is not a factor).</p>
     * @param expectedValue - the value that is expected to have returned in the api
     * response
     * @param actualValue - the actual value that returned in the api response
     * @param variableBeingTested - the name of the variable being tested. This is used for
     * reporting so that when it is called the report will reflect wording that is unique to the
     * variable it is testing.
     * @throws Exception
     */
    protected void verifyTheActualValueContainsTheExpectedValue(String expectedValue, String actualValue,
        String variableBeingTested) throws Exception {
        try {
            if (!TestUtils.isNullOrBlank(expectedValue) && !TestUtils.isNullOrBlank(actualValue)) {
                if (actualValue.toLowerCase().trim().contains(expectedValue.toLowerCase().trim()))

                {
                    LocalValidation.getValidations()
                        .assertionPass(actualValue + " is set correctly in " + variableBeingTested);
                } else {
                    throw LocalValidation.getValidations().assertionFailed(
                        expectedValue + " is not set as expected. " + actualValue + " is set instead.");
                }
            } else {
                if (TestUtils.isNullOrBlank(expectedValue)) {
                    throw LocalValidation.getValidations().assertionFailed(
                        variableBeingTested + " returned null. Variable was not expected to return null.");
                } else if (TestUtils.isNullOrBlank(actualValue)) {
                    throw LocalValidation.getValidations().assertionFailed(variableBeingTested +
                        " returned null in the actual variable that was set. Check the test to verify all "
                        + "variables are being assigned a value appropriately.");
                }
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    /**
     * <p> Method to generate a random string of text by taking a string of
     * text and scrambling it into a new string at a randomly selected length
     * that will not surpass the specified length param.</p>
     * @param length - the total length that the random string of text should be when
     * completed. This will randomly select a length with the number specified in this
     * param being the highest length possible.
     * @param value - the String of characters that will be used to create the
     * random string of text. Method takes the the characters in this
     * string and scrambles them to generte the random string.
     * @return String
     * @throws Exception
     */
    protected String randomStringAtRandomLength(int length, String value) throws Exception {
        StringBuilder sb = new StringBuilder(length);
        try {
            SecureRandom rnd = new SecureRandom();
            Random rand = new Random();
            int randomLength = rand.nextInt(length);
            if (randomLength == 0) {
                randomLength++;
            }
            for (int i = 0; i < randomLength; i++) {
                sb.append(value.charAt(rnd.nextInt(value.length())));
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
        return sb.toString();
    }

    /**
     * <p>Method to generate a random string of text by taking a string of
     * text and scrambling it into a new string at a specifically set length.</p>
     * @param length - the total length that the random string of text should be when
     * completed
     * @param value - the String of characters that will be used to create the
     * random string of text. Method takes the the characters in this
     * string and scrambles them to generte the random string.
     * @return String
     * @throws Exception
     */
    protected String randomStringAtSetLength(int length, String value) throws Exception {
        StringBuilder sb = new StringBuilder(length);
        try {
            SecureRandom rnd = new SecureRandom();
            for (int i = 0; i < length; i++) {
                sb.append(value.charAt(rnd.nextInt(value.length())));
            }
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
        return sb.toString();
    }

    /**
     * <p> Method to clear all text in a field by selecting all text and selecting 
     * the backspace key on the keyboard.
     * </p>
     * @param selectorString - the webelement selector string necessary for the webelement to
     * be found
     * @param by - the type of selector being used (i.e id, name, cssSelector,
     * xpath, etc.). Necessary for the WebElement to be found
     * @throws Exception
     */
    protected void clearAllTextByBackspacing(String selectorString, String by) throws Exception {
        try {
            SHelper.get().enter().textInto(selectorString, by, Keys.CONTROL + "a");
            SHelper.get().enter().textInto(selectorString, by, Keys.BACK_SPACE);
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    /**
     * <p> Method to clear all text in a field by selecting all text and selecting 
     * the backspace key on the keyboard.
     * </p>
     * @param element - a webelement that is defined and found in the calling method
     * @throws Exception
     */
    protected void clearAllTextByBackspacing(WebElement element) throws Exception {
        try {
            SHelper.get().enter().textInto(element, Keys.CONTROL + "a");
            SHelper.get().enter().textInto(element, Keys.BACK_SPACE);
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    /**
     * <p>This method refreshes the page and waits for an element to appear on the page.
     * The wait is explicit and will wait up to the specified amount of time for the 
     * condition to be met (i.e the element to be displayed after the page is refreshed). 
     * If the element is not found in the specified amount of time, the test will fail.
     * If the element is found sooner than the specified time, the test will not longer wait
     * and will continue executing the next method in the chain.</p>
     * @param selectorString - the webelement selector string necessary for the webelement to
     * be found
     * @param by - the type of selector being used (i.e id, name, cssSelector,
     * xpath, etc.). Necessary for the WebElement to be found
     * @param i - the total amount of time the test should wait for the element to be found.
     */
    public void refreshPageAndWaitForElementToDisplay(String selectorString, String by, int i) {
        WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

        wait.until(new ExpectedCondition < Boolean > () {
            public Boolean apply(WebDriver driver) {
                Boolean result = false;
                try {
                    SHelper.get().page().refresh();
                    Thread.sleep(900);
                    if (SHelper.get().element().isDisplayed(SHelper.get().element().get(selectorString, by), 10)) {
                        result = true;
                    } else {
                        result = false;
                    }
				} catch (Exception e) {
					result = false;
				}
				return result;
            };
        });
    }

    /**
     * <p>Method to extract digits from strings</p>
     * @param selector - the webelement selector string necessary for the webelement to
     * be found
     * @param by - the type of selector being used (i.e id, name, cssSelector,
     * xpath, etc.). Necessary for the WebElement to be found
     * @return String
     */
    protected String getNumbersFromString(String selector, String by) throws Exception {
        String z = null;
        try {
            z = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(selector, by);
            return z.replaceAll("[^0-9]", "");
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    /**
     * <p>Method to extract digits from strings</p>
     * @param element - the element from which the digits need to be
     * extracted.
     * @return String
     */
    protected String getNumbersFromString(WebElement element) throws Exception {
        try {
            String z = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(element);
            return z.replaceAll("[^0-9]", "");
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }


}