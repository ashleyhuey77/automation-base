package commonClasses.sharedPageClasses;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import commonClasses.sharedUtils.TestUtils;
import commonClasses.sharedUtils.managers.*;
import seleniumHelper.enums.*;
import org.openqa.selenium.support.ui.*;

public abstract class PageHelper {

    protected static String cssSelector = IdentifyBy.CssSelector.toString();
    protected static String id = IdentifyBy.Id.toString();
    protected static String name = IdentifyBy.Name.toString();
    protected static String xpath = IdentifyBy.Xpath.toString();
    protected static String className = IdentifyBy.ClassName.toString();
    protected static String tagName = IdentifyBy.TagName.toString();
    protected static String linkText = IdentifyBy.LinkText.toString();
    protected static String partialLinkText = IdentifyBy.PartialLinkText.toString();
	
	public PageHelper () throws Exception {
		
	}
 
    /**
     * <summary> Method to enter a value into a text field </summary>
     * 
     * @param value
     *            the text that will be entered into the text field
     * @param webElement
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
    protected void enterAvalueIntoATextField(String value, String webElement, String byValue, String elementBeingTested)
	    throws Exception 
    {
		try 
		{
		    if (SHelper.get().element().isDisplayed(webElement, byValue, 5)) 
		    {
		    	clickSomeElement(Via.SELENIUM, webElement, byValue, elementBeingTested);
		    	clearAllTextByBackspacing(webElement, byValue);
	
			if (!TestUtils.isNullOrBlank(value)) 
			{
			    SHelper.get().enter().clear(webElement, byValue);
			    clickSomeElement(Via.SELENIUM, webElement, byValue, elementBeingTested);
			    SHelper.get().enter().textInto(webElement, byValue, value);
			}
			LocalReport.getReport().reportDoneEvent(elementBeingTested + " has been entered successfully");
		    } 
		    else 
		    {
		    	throw LocalValidation.getValidations().assertionFailed(
				elementBeingTested + " does not display as expected. Unable to enter text in this field.");
		    }
		} 
		catch (Exception ex) 
		{
		    throw LocalReport.getReport().reportException(ex);
		}
    }
    
    /**
     * <summary> Method to enter a value into a text field </summary>
     * 
     * @param value
     *            the text that will be entered into the text field
     * @param webElement
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
    protected void enterAvalueIntoATextField(String value, WebElement element, String elementBeingTested)
	    throws Exception 
    {
		try 
		{
		    if (SHelper.get().element().isDisplayed(element, 5)) 
		    {
		    	SHelper.get().click(Via.SELENIUM).on(element);
		    	clearAllTextByBackspacing(element);
	
			if (!TestUtils.isNullOrBlank(value)) 
			{
			    SHelper.get().enter().clear(element);
			    SHelper.get().click(Via.SELENIUM).on(element);
			    SHelper.get().enter().textInto(element, value);
			}
			LocalReport.getReport().reportDoneEvent(elementBeingTested + " has been entered successfully");
		    } 
		    else 
		    {
		    	throw LocalValidation.getValidations().assertionFailed(
				elementBeingTested + " does not display as expected. Unable to enter text in this field.");
		    }
		} 
		catch (Exception ex) 
		{
		    throw LocalReport.getReport().reportException(ex);
		}
    }
	
	/**	
  	 *	<summary>
	 *	Method to wait for and then click some element
	 *	</summary>
	 *	@return void
	 *	@param webElement the webelement selector string for the field that
   	 *					  the text will be entered into
   	 *	@param byValue the type of selector being used (i.e id, name, cssSelector, xpath, etc.)
   	 *	@param elementBeingTested the name of the element being tested. This is used for 
   	 *							  reporting so that when it is called the report will reflect an
   	 *							  element that is unique to the method 
	 * @throws Exception 
	*/
	protected void clickSomeElement(Via via, String html, String byValue, String elementBeingTested, int...index) throws Exception
	{
		try
		{
			if (SHelper.get().element().isDisplayed(html, byValue, 5))
			{
				tryAllClicks(html, byValue, via, index);
				LocalReport.getReport().reportDoneEvent(elementBeingTested + " clicked successfully." );
			}
			else
			{
				throw LocalValidation.getValidations().assertionFailed("Element is not on the page. Unable to click the " + elementBeingTested);
			}
		}
		catch (Exception ex)
		{
			throw LocalReport.getReport().reportException(ex);
		}
	}
	
	private void tryAllClicks(String html, String byValue, Via via, int... index) throws Exception
	{
		try
		{
			indexCheckClick(via, html, byValue, index);
		}
		catch (Exception ex)
		{
			try 
			{
				indexCheckClick(Via.SELENIUM, html, byValue, index);
			} 
			catch (Exception e2) 
			{
				try 
				{
					indexCheckClick(Via.JAVASCRIPT, html, byValue, index);
				} 
				catch (Exception e3) 
				{
					try 
					{
						indexCheckClick(Via.JQUERY, html, byValue, index);
					} 
					catch (Exception e4) 
					{
						LocalValidation.getValidations().assertionFailed("Test has exhausted all different click methods. Not able to click element with the specified selector.");
						throw LocalReport.getReport().reportException(e4);
					}
				}
			}
		}
	}
	
	private void indexCheckClick(Via via, String html, String byValue, int... index) throws Exception
	{
		try 
		{
			if (index.length > 0)
			{
				SHelper.get().click(via).on(html, byValue, index[0]);
			}
			else 
			{
				SHelper.get().click(via).on(html, byValue);
			}
		} 
		catch (Exception e2) 
		{
			throw e2;
		}
    }
	
    /**	
  	 *	<summary>
	 *	Method to select some text from a drop down
	 *	</summary>
	 *	@return void
	 *	@param option the text value of the option that needs to be selected from the dropdown
	 *	@param clickElement the element selector string of the element that will be clicked
	 *	@param clickByValue the type of selector being used (i.e id, name, cssSelector, xpath, etc.) for the click element
	 *	@param searchElement the element selector string of the search box element
	 *	@param searchByValue the type of selector being used (i.e id, name, cssSelector, xpath, etc.) for the search box element
	 *	@param optionsElement the element selector string of the options element in the drop down
	 *	@param optionsByValue the type of selector being used (i.e id, name, cssSelector, xpath, etc.) for the options element
	 *	@param elementBeingTested the name of the element being tested. This is used for 
   	 *							  reporting so that when it is called the report will reflect an
   	 *							  element that is unique to the method 
	 * @throws Exception 
	*/
	protected void selectSomeOptionFromNonDropdown(String option, String clickElement, String clickByValue, String searchElement, String searchByValue, String optionsElement, String optionsByValue, String elementBeingTested, Via via) throws Exception
	{
		try
		{
			if (SHelper.get().element().isDisplayed(clickElement, clickByValue, 10))
			{
				clickSomeElement(Via.SELENIUM, clickElement, clickByValue, elementBeingTested);
				Thread.sleep(600);
				enterAvalueIntoATextField(option, searchElement, searchByValue, elementBeingTested);
				SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ELEMENT_OR_VALUE).waitOn(optionsElement, optionsByValue, 10);
				Thread.sleep(900);
				findEqualOptionInListAndSelectIt(via, optionsElement, optionsByValue, option);
			}
			else
			{
				throw LocalValidation.getValidations().assertionFailed("Element is not availale. Can not select the " + elementBeingTested + " from the drop down list.");
			}
		}
		catch (Exception ex)
		{
			throw LocalReport.getReport().reportException(ex);
		}
	}
	
	   /**	
	  	 *	<summary>
		 *	Method to select some text from a drop down
		 *	</summary>
		 *	@return void
		 *	@param option the text value of the option that needs to be selected from the dropdown
		 *	@param clickElement the element selector string of the element that will be clicked
		 *	@param clickByValue the type of selector being used (i.e id, name, cssSelector, xpath, etc.) for the click element
		 *	@param searchElement the element selector string of the search box element
		 *	@param searchByValue the type of selector being used (i.e id, name, cssSelector, xpath, etc.) for the search box element
		 *	@param optionsElement the element selector string of the options element in the drop down
		 *	@param optionsByValue the type of selector being used (i.e id, name, cssSelector, xpath, etc.) for the options element
		 *	@param elementBeingTested the name of the element being tested. This is used for 
	   	 *							  reporting so that when it is called the report will reflect an
	   	 *							  element that is unique to the method 
		 * @throws Exception 
		*/
		protected void selectSomeOptionFromNonDropdown(String option, WebElement clickEl, WebElement searchEl, String optionsElement, String optionsByValue, String elementBeingTested, Via via) throws Exception
		{
			try
			{
				if (SHelper.get().element().isDisplayed(clickEl, 10))
				{
					SHelper.get().click(Via.SELENIUM).on(clickEl);
					Thread.sleep(600);
					enterAvalueIntoATextField(option, searchEl, elementBeingTested);
					SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ELEMENT_OR_VALUE).waitOn(optionsElement, optionsByValue, 10);
					Thread.sleep(900);
					findEqualOptionInListAndSelectIt(via, optionsElement, optionsByValue, option);
				}
				else
				{
					throw LocalValidation.getValidations().assertionFailed("Element is not availale. Can not select the " + elementBeingTested + " from the drop down list.");
				}
			}
			catch (Exception ex)
			{
				throw LocalReport.getReport().reportException(ex);
			}
		}

    /**
     * <summary> Method to verify some element is present </summary>
     * 
     * @return void
     * @param elementHtml
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
    protected void verifySomeElementIsPresent(String elementHtml, String byValue, String elementBeingTested)
	    throws Exception 
    {
		try 
		{
		    if (SHelper.get().element().isDisplayed(elementHtml, byValue, 10)) 
		    {
		    	LocalValidation.getValidations()
				.assertionPass(elementBeingTested + " displays in the page as expected.");
		    } 
		    else 
		    {
		    	throw LocalValidation.getValidations().assertionFailed(
				elementBeingTested + " should display in the page. It does not display as expected.");
		    }
		} 
		catch (Exception ex) 
		{
		    throw LocalReport.getReport().reportException(ex);
		}
    }

    /**
     * <summary> Method to verify some element is not present </summary>
     * 
     * @return void
     * @param elementHtml
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
    protected void verifySomeElementIsNotPresent(String elementHtml, String byValue, String elementBeingTested)
	    throws Exception 
    {
		try 
		{
		    if (!SHelper.get().element().isDisplayed(elementHtml, byValue, 5)) 
		    {
		    	LocalValidation.getValidations().assertionPass(elementBeingTested + " does not display in the page.");
		    } 
		    else 
		    {
		    	throw LocalValidation.getValidations().assertionFailed(
				elementBeingTested + " should not display in the page. It does display. This is not expected.");
		    }
		} 
		catch (Exception ex) 
		{
		    throw LocalReport.getReport().reportException(ex);
		}
    }
	
	
	/**	
  	 *	<summary>
	 *	Method to find some option in a list of options 
	 *	and select it based on text
	 *	</summary>
	 *	@return void
	 *	@param webElements a list of webelements that share a similar selector in order
	 *					   to loop through and execute an action based on some parameter
	 *	@param webelementListHtml the webelement selector string for the field that
   	 *					   		  the text will be entered into
   	 *	@param expectedOption the option that is expected to be found
   	 *	@param clickViaJQuery boolean value to execute a jquery click command instead of a 
   	 *				          click via selenium. Is not necessary for all methods, but has been 
   	 *				          necessary in some instances.
	 * @throws Exception 
	*/
	
	protected void findEqualOptionInListAndSelectIt(Via via, String selector, String by, String expectedOption) throws Exception
	{
		try
		{
			List<WebElement> webElements = SHelper.get().element().getListOf(selector, by);
			String value = null;
			for (int i = 0; i < webElements.size(); i++)
			{
				String actualOption = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(webElements.get(i));
				if (actualOption.toLowerCase().trim().equals(expectedOption.toLowerCase().trim()))
				{
					tryAllClicks(selector, by, via, i);
					value = actualOption;
					LocalReport.getReport().reportDoneEvent(expectedOption + " has been selected successfully.");
					break;
				}
			}
			
			if (value == null)
			{
				throw LocalValidation.getValidations().assertionFailed(expectedOption + " is not found in the list of available options. Unable to select the expected option.");
			}
		}
		catch (Exception ex)
		{
			throw LocalReport.getReport().reportException(ex);
		}
	}
	
	/**	
  	 *	<summary>
	 *	Method to find some option in a list of options 
	 *	and select it based on text
	 *	</summary>
	 *	@return void
	 *	@param webElements a list of webelements that share a similar selector in order
	 *					   to loop through and execute an action based on some parameter
	 *	@param webelementListHtml the webelement selector string for the field that
   	 *					   		  the text will be entered into
   	 *	@param expectedOption the option that is expected to be found
   	 *	@param clickViaJQuery boolean value to execute a jquery click command instead of a 
   	 *				          click via selenium. Is not necessary for all methods, but has been 
   	 *				          necessary in some instances.
	 * @throws Exception 
	*/
	
	protected void findOptionContainedInListAndSelectIt(Via via, String selector, String by, String expectedOption) throws Exception
	{
		try
		{
			String value = null;
			SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ELEMENT_OR_VALUE).waitOn(selector, by, 40);
			List<WebElement> webElements = SHelper.get().element().getListOf(selector, by);
			for (int i = 0; i < webElements.size(); i++)
			{
				WebElement element = webElements.get(i);
				String actualOption = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(element);
				if (actualOption.toLowerCase().trim().contains(expectedOption.toLowerCase().trim()))
				{
					tryAllClicks(selector, by, via, i);
					value = actualOption;
					LocalReport.getReport().reportDoneEvent(expectedOption + " has been selected successfully.");
					break;
				}
			}
			
			if (value == null)
			{
				throw LocalValidation.getValidations().assertionFailed(expectedOption + " is not found in the list of available options. Unable to select the expected option.");
			}
		}
		catch (Exception ex)
		{
			throw LocalReport.getReport().reportException(ex);
		}
    }

    /**
     * <summary> Method to verify some element contains the expected text
     * </summary>
     * 
     * @return void
     * @param elementHtml
     *            the webelement selector string for the field that the text
     *            will be entered into
     * @param byValue
     *            the type of selector being used (i.e id, name, cssSelector,
     *            xpath, etc.)
     * @param expectedText
     *            the text that is expected to be in the element
     * @param elementBeingTested
     *            the name of the element being tested. This is used for
     *            reporting so that when it is called the report will reflect an
     *            element that is unique to the method
     * @param removeAllSpaces
     *            boolean to determine whether validation needs to occur with
     *            all spaces removed to producce a more accurate comparison. Not
     *            neccesary for all consumers of this method, however has been
     *            necessary for some instances.
     * @throws Exception
     */
    protected void verifySomeElementContainsTheExpectedText(WebElement element, String expectedText,
	    String elementBeingTested, Boolean removeAllSpaces) throws Exception 
    {
		try 
		{
		    String actualText = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(element);
	
		    if (removeAllSpaces) 
		    {
		    	actualText = actualText.replace(" ", "");
		    	expectedText = expectedText.replace(" ", "");
		    }
	
		    if (actualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) 
		    {
		    	LocalValidation.getValidations()
				.assertionPass(elementBeingTested + " contains the correct text: " + actualText);
		    } 
		    else 
		    {
		    	throw LocalValidation.getValidations()
				.assertionFailed(elementBeingTested + " does not contain the correct text. Expected text: "
					+ expectedText + ". Actual text: " + actualText);
		    }
		} 
		catch (Exception ex) 
		{
		    throw LocalReport.getReport().reportException(ex);
		}
	}
    
    /**
     * <summary> Method to verify some element contains the expected text
     * </summary>
     * 
     * @return void
     * @param elementHtml
     *            the webelement selector string for the field that the text
     *            will be entered into
     * @param byValue
     *            the type of selector being used (i.e id, name, cssSelector,
     *            xpath, etc.)
     * @param expectedText
     *            the text that is expected to be in the element
     * @param elementBeingTested
     *            the name of the element being tested. This is used for
     *            reporting so that when it is called the report will reflect an
     *            element that is unique to the method
     * @param removeAllSpaces
     *            boolean to determine whether validation needs to occur with
     *            all spaces removed to producce a more accurate comparison. Not
     *            neccesary for all consumers of this method, however has been
     *            necessary for some instances.
     * @throws Exception
     */
    protected void verifySomeElementContainsTheExpectedText(String selector, String by, String expectedText,
	    String elementBeingTested, Boolean removeAllSpaces) throws Exception 
    {
		try 
		{
			WebElement element = SHelper.get().element().get(selector, by);
		    String actualText = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(element);
	
		    if (removeAllSpaces) 
		    {
		    	actualText = actualText.replace(" ", "");
		    	expectedText = expectedText.replace(" ", "");
		    }
	
		    if (actualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) 
		    {
		    	LocalValidation.getValidations()
				.assertionPass(elementBeingTested + " contains the correct text: " + actualText);
		    } 
		    else 
		    {
		    	throw LocalValidation.getValidations()
				.assertionFailed(elementBeingTested + " does not contain the correct text. Expected text: "
					+ expectedText + ". Actual text: " + actualText);
		    }
		} 
		catch (Exception ex) 
		{
		    throw LocalReport.getReport().reportException(ex);
		}
	}
    
    /**
     * <summary> Method to verify a field is blank </summary>
     * 
     * @return void
     * @param html
     *            the webelement selector string for the field that the text
     *            will be entered into
     * @param byValue
     *            the type of selector being used (i.e id, name, cssSelector,
     *            xpath, etc.)
     * @param requiresIndex
     *            boolean value that determines whether the index is used or not
     *            in the javascript script. Not necessary for all methods, but
     *            has been necessary for some.
     * @param webElementIndex
     *            the index of the element if there are more than one elements
     *            with the same selector
     * @param elementBeingTested
     *            the name of the element being tested. This is used for
     *            reporting so that when it is called the report will reflect an
     *            element that is unique to the method
     * @throws Exception
     */
    protected void verifyTextFieldIsBlank(String html, String byValue, Boolean requiresIndex, String webElementIndex,
	    String elementBeingTested) throws Exception 
    {
		try 
		{
		    String actualValueInTextBox = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom(html, byValue,
			    webElementIndex);
		    if (TestUtils.isNullOrBlank(actualValueInTextBox)) 
		    {
		    	LocalValidation.getValidations().assertionPass(elementBeingTested + " is blank as expected.");
		    } 
		    else 
		    {
		    	throw LocalValidation.getValidations()
				.assertionFailed(elementBeingTested
					+ " should be blank but is retaining a value instead. The value being retained is "
					+ actualValueInTextBox);
		    }
		} 
		catch (Exception ex) 
		{
		    throw LocalReport.getReport().reportException(ex);
		}
    }

    /**
     * <summary> Method to verify the text in a text field contains the correct
     * text </summary>
     * 
     * @return void
     * @param html
     *            the webelement selector string for the field that the text
     *            will be entered into
     * @param byValue
     *            the type of selector being used (i.e id, name, cssSelector,
     *            xpath, etc.)
     * @param requiresIndex
     *            boolean value that determines whether the index is used or not
     *            in the javascript script. Not necessary for all methods, but
     *            has been necessary for some.
     * @param webElementIndex
     *            the index of the element if there are more than one elements
     *            with the same selector
     * @param expectedText
     *            the text that is expected to be in the element
     * @param elementBeingTested
     *            the name of the element being tested. This is used for
     *            reporting so that when it is called the report will reflect an
     *            element that is unique to the method
     * @throws Exception
     */
    protected void verifyTextInTextField(String html, String byValue, String webElementIndex, String expectedText,
	    String elementBeingTested, Boolean removeAllSpaces) throws Exception 
    {
		try {
		    String index = null;
		    if (!TestUtils.isNullOrBlank(webElementIndex)) 
		    {
		    	index = webElementIndex;
		    }
		    	String actualValueInTextBox = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom(html, byValue,
			    index);
		    if (actualValueInTextBox.contains("\n")) 
		    {
		    	actualValueInTextBox = actualValueInTextBox.replace("\n", " ");
		    }
	
		    if (removeAllSpaces) 
		    {
		    	actualValueInTextBox = actualValueInTextBox.replace(" ", "");
		    	expectedText = expectedText.replace(" ", "");
		    }
	
		    if (actualValueInTextBox.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) 
		    {
		    	LocalValidation.getValidations()
				.assertionPass(elementBeingTested + " contains " + actualValueInTextBox + " as expected.");
		    } 
		    else 
		    {
		    	throw LocalValidation.getValidations()
				.assertionFailed(elementBeingTested + " should contain " + expectedText
					+ " but is retaining an incorrect value instead. The value being retained is "
					+ actualValueInTextBox);
		    }
		} 
		catch (Exception ex) 
		{
		    throw LocalReport.getReport().reportException(ex);
		}
    }

    /**
     * <summary> enum of accepted timezones within the newstron app </summary>
     */
    public enum Timezones {
	Pacific, Central, Eastern, GMT, Dubai, HK
    }

    /**
     * <summary> Entity enum that holds all of the api models object categories
     * </summary>
     */

    public enum Entity {
	Scheduler, MS2, CrashRecords, Requests

    }

    /**
     * <summary> Method to get the current date and split it into a string array
     * </summary>
     * 
     * @return String[]
     */
    protected String[] getCurrentSplitDate() throws Exception 
    {
		String[] currentDate = null;
		String date = TestUtils.GetCurrentDateTime("dd-MM-yyyy");
		currentDate = date.split("-");
		return currentDate;
    }

    /**
     * <summary> Method to get the total days in the current month of the
     * current year - taking into account leap year </summary>
     * 
     * @return void
     */

    protected int getTotalDaysInMonth() throws Exception 
    {
		int totalDaysInMonth = 0;
		int currentYear = Integer.parseInt(getCurrentSplitDate()[2]);
		int currentDay = Integer.parseInt(getCurrentSplitDate()[0]);
		int currentMonth = Integer.parseInt(getCurrentSplitDate()[1]);
	
		Calendar cal = new GregorianCalendar(currentYear, currentMonth - 1, currentDay);
	
		totalDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return totalDaysInMonth;
    }

    /**
     * <summary> Method to get the future date based on user input of how many
     * days they wish to select from today's date </summary>
     * 
     * @return int
     * @param daysOutFromCurrentDay
     *            the number of days out from today to select
     */

    protected int getFutureDate(int daysOutFromCurrentDay) throws Exception 
    {
		int futureDate = 0;
		int currentDay = Integer.parseInt(getCurrentSplitDate()[0]);
		futureDate = currentDay + daysOutFromCurrentDay;
		return futureDate;
    }

    /**
     * <summary> days of the week combinations enum. This enum holds all the
     * different combinations of days of the week that may need to be selected
     * in order to properly test </summary>
     */
    public enum DaysOfTheWeek {
    	ALL, WEEKDAYS, WEEKENDS,
    }

    /**
     * <summary> Method to clean up static global variables </summary>
     * 
     * @return void
     * @param value
     *            the static global value that needs to be cleaned up. Is set to
     *            null if it is not already null or empty
     */
    protected void cleanUp(String value) 
    {
		if (!TestUtils.isNullOrBlank(value)) 
		{
		    value = null;
		}
    }

    /**
     * <summary> Method to evaluate whether a variable is null and fail the test
     * case if it is null. Used primarily in for loops to assure the loop is
     * executed properly and fails at the right place if not. </summary>
     * 
     * @param variable
     *            the string variable being evaluated
     * @param passMessage
     *            the message for reporting if the test passes
     * @param failMessage
     *            the message for reporting if the test fails
     * @throws Exception
     * @return void
     */
    protected void failTestIfVariableIsNull(String variable, String passMessage, String failMessage) throws Exception 
    {
		if (variable != null) 
		{
		    LocalValidation.getValidations().assertionPass(passMessage);
		} 
		else 
		{
		    throw LocalValidation.getValidations().assertionFailed(failMessage);
		}
    }

    /**
     * <summary> Method to remove an unwanted character or to replace it with a
     * different character if it is present in the String </summary>
     * 
     * @param variable
     *            the string variable that is to be modified
     * @param character
     *            the character or string that is unwanted that will be removed
     *            or replaced if present in the variable
     * @param replaceValue
     *            the value to replace the character. Leave as empty string to
     *            remove character completely.
     * @return String
     * @throws Exception
     */
    protected String removeOrChangeUnwantedCharacter(String variable, String character, String replaceValue)
	    throws Exception 
    {
		String result = null;
		try 
		{
		    if (variable.contains(character)) 
		    {
		    	result = variable.replace(character, replaceValue);
		    } 
		    else 
		    {
		    	result = variable;
		    }
		} 
		catch (Exception ex) 
		{
		    throw LocalReport.getReport().reportException(ex);
		}
		return result;
    }

    /**
     * <summary> Method to verify an expected value matches an actual value
     * </summary>
     * 
     * @return void
     * @param expectedValue
     *            the value that is expected to have returned in the api
     *            response
     * @param actualValue
     *            the actual value that returned in the api response
     * @throws Exception
     */
    protected void verifyTheActualValueMatchesTheExpectedValue(String expectedValue, String actualValue,
	    String variableBeingTested) throws Exception 
    {
		try 
		{
		    if (!TestUtils.isNullOrBlank(expectedValue) && !TestUtils.isNullOrBlank(actualValue)) 
		    {
				if (actualValue.toLowerCase().trim().equals(expectedValue.toLowerCase().trim())) 
				{
				    LocalValidation.getValidations()
					    .assertionPass(actualValue + " is set correctly in " + variableBeingTested);
				} 
				else 
				{
				    throw LocalValidation.getValidations().assertionFailed(
					    expectedValue + " is not set as expected. " + actualValue + " is set instead.");
				}
		    } 
		    else 
		    {
		    	throw LocalValidation.getValidations()
				.assertionFailed(variableBeingTested
					+ " returned null in either the actual or the expected variable that was set. Check the test to verify all variables are being assigned a value appropriately. Actual: "
					+ actualValue + ". Expected: " + expectedValue);
		    }
		} 
		catch (Exception ex) 
		{
		    throw LocalReport.getReport().reportException(ex);
		}
    }

    /**
     * <summary> Method to add i number of days to a date </summary>
     * 
     * @return String
     * @param date
     *            the date that needs to be subtracted from
     * @param totalDays
     *            the total number of days in the month
     * @param daysToSubtract
     *            the number of days to subtract from the date
     * @throws Exception
     */
    protected String subtractDays(String date, int totalDays, int daysToAdd) throws Exception 
    {
		String addedDay = null;
		try 
		{
		    SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		    Date myDate = format.parse(date);
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(myDate);
		    int days = totalDays - daysToAdd - 1;
		    cal.add(Calendar.DATE, days);
		    myDate = cal.getTime();
		    String dummyDate = format.format(myDate);
		    addedDay = dummyDate.startsWith("0") ? dummyDate.substring(1) : dummyDate;
		} 
		catch (Exception ex) 
		{
		    throw LocalReport.getReport().reportException(ex);
		}
		return addedDay;
    }

    /**
     * <summary> Method to verify an expected value matches an actual value
     * </summary>
     * 
     * @return void
     * @param expectedValue
     *            the value that is expected to have returned in the api
     *            response
     * @param actualValue
     *            the actual value that returned in the api response
     * @throws Exception
     */
    protected void verifyTheActualValueContainsTheExpectedValue(String expectedValue, String actualValue,
	    String variableBeingTested) throws Exception 
    {
		try 
		{
		    if (!TestUtils.isNullOrBlank(expectedValue) && !TestUtils.isNullOrBlank(actualValue)) 
		    {
				if (actualValue.toLowerCase().trim().contains(expectedValue.toLowerCase().trim()))
		
				{
				    LocalValidation.getValidations()
					    .assertionPass(actualValue + " is set correctly in " + variableBeingTested);
				} 
				else 
				{
				    throw LocalValidation.getValidations().assertionFailed(
					    expectedValue + " is not set as expected. " + actualValue + " is set instead.");
				}
		    } 
		    else 
		    {
				if (TestUtils.isNullOrBlank(expectedValue)) 
				{
				    throw LocalValidation.getValidations().assertionFailed(
					    variableBeingTested + " returned null. Variable was not expected to return null.");
				} 
				else if (TestUtils.isNullOrBlank(actualValue)) 
				{
				    throw LocalValidation.getValidations().assertionFailed(variableBeingTested
					    + " returned null in the actual variable that was set. Check the test to verify all variables are being assigned a value appropriately.");
				}
		    }
		} 
		catch (Exception ex) 
		{
		    throw LocalReport.getReport().reportException(ex);
		}
    }

    /**
     * <summary> Method to generate a random string of text </summary>
     * 
     * @param length
     *            the total length that the random string of text should be when
     *            completed
     * @param value
     *            the String of characters that will be used to create the
     *            random string of text. Method takes the the characters in this
     *            string and scrambles them to generte thte random string.
     * @return String
     * @throws Exception
     */
    protected String randomStringAtRandomLength(int length, String value) throws Exception 
    {
		StringBuilder sb = new StringBuilder(length);
		try 
		{
		    SecureRandom rnd = new SecureRandom();
		    Random rand = new Random();
		    int randomLength = rand.nextInt(length);
		    if (randomLength == 0) 
		    {
		    	randomLength++;
		    }
		    for (int i = 0; i < randomLength; i++) 
		    {
		    	sb.append(value.charAt(rnd.nextInt(value.length())));
		    }
		} 
		catch (Exception ex) 
		{
		    throw LocalReport.getReport().reportException(ex);
		}
		return sb.toString();
    }

    /**
     * <summary> Method to generate a random string of text </summary>
     * 
     * @param length
     *            the total length that the random string of text should be when
     *            completed
     * @param value
     *            the String of characters that will be used to create the
     *            random string of text. Method takes the the characters in this
     *            string and scrambles them to generte thte random string.
     * @return String
     * @throws Exception
     */
    protected String randomStringAtSetLength(int length, String value) throws Exception 
    {
		StringBuilder sb = new StringBuilder(length);
		try 
		{
		    SecureRandom rnd = new SecureRandom();
		    for (int i = 0; i < length; i++) 
		    {
		    	sb.append(value.charAt(rnd.nextInt(value.length())));
		    }
		} 
		catch (Exception ex) 
		{
		    throw LocalReport.getReport().reportException(ex);
		}
		return sb.toString();
    }

    /**
     * <summary> method to clear a field by selecting all text and backspacing
     * </summary>
     * 
     * @param selectorString
     *            the webelement selector string necessary for the webelement to
     *            be found
     * @param by
     *            the type of selector being used (i.e id, name, cssSelector,
     *            xpath, etc.). Necessary for the WebElement to be found
     * @return void
     * @throws Exception
     */
    protected void clearAllTextByBackspacing(String selectorString, String by) throws Exception 
    {
		try 
		{
		    SHelper.get().enter().textInto(selectorString, by, Keys.CONTROL + "a");
		    SHelper.get().enter().textInto(selectorString, by, Keys.BACK_SPACE);
		} 
		catch (WebDriverException ex) 
		{
		    throw ex;
		}
    }

    /**
     * <summary> method to clear a field by selecting all text and backspacing
     * </summary>
     * 
     * @param element
     *            a webelement that is defined and found in the calling method
     * @return void
     * @throws Exception
     */
    protected void clearAllTextByBackspacing(WebElement element) throws Exception 
    {
		try 
		{
		    SHelper.get().enter().textInto(element, Keys.CONTROL + "a");
		    SHelper.get().enter().textInto(element, Keys.BACK_SPACE);
		} 
		catch (WebDriverException ex) 
		{
		    throw ex;
		}
    }

    /**
     * <summary>Refresh a page and wial for element to display</summary>
     * 
     * @param selectorString
     * @param by
     * @param i
     */
    public void refreshPageAndWaitForElementToDisplay(String selectorString, String by, int i)
    {
    	WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(),i);

		wait.until(new ExpectedCondition<Boolean>() {
            	public Boolean apply(WebDriver driver) {
            				Boolean result = false;
            				try {
								SHelper.get().page().refresh();
							} catch (Exception e1) {

							}
            				try {
								Thread.sleep(900);
							} catch (InterruptedException e) {
							}
                         	try
                         	{
                         		if(SHelper.get().element().isDisplayed(SHelper.get().element().get(selectorString, by), 10))
                         		{
                         			result = true;
                         			return result;
                         		}
                         		else 
                         		{
                         			return result;
								}
                         	}
                         	catch (StaleElementReferenceException ex)
                         	{
                         		return result;
                         	} catch (Exception e) {
                         		return result;
							}
		   		};
		});
    }
    
	/**
	 * <summary>Method to extract digits from strings</summary>
	 * 
	 * @param locator
	 * @param type
	 * @return String
	 */
	protected String getNumbersFromString(String locator, String type) throws Exception 
	{
		String z = null;
		try 
		{
			z = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(locator, type);
			return z.replaceAll("[^0-9]", "");
		} 
		catch (Exception ex) 
		{
			throw LocalReport.getReport().reportException(ex);
		}
	}
	
    /**
     * <summary>retrieve number form field</summary>
     * 
     * @param elemnt
     * @return
     */
    protected String getNumbersFromString(WebElement elemnt) throws Exception 
    {
		try 
		{
		    String z = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(elemnt);
		    return z.replaceAll("[^0-9]", "");
		} 
		catch (Exception ex) 
		{
		    throw LocalReport.getReport().reportException(ex);
		}
    }


}
