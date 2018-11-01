package shelper.interfaces;

import java.util.List;
import org.openqa.selenium.WebElement;
import log.TestException;
import shelper.vobjects.TestElement;

public interface IWait {
	
	/**
	 * <p>This method is meant to wait for an element or attribute to be
	 * present on the page. And if the Wait.CLICKABILITY_OF_ELEMENT param
	 * is set, it will wait for the element to be clickable.</p>
	 * <p>This method waits up to the specified amount of time for the condition
	 * to be true. If the condition returns true before the specified time, then
	 * the test moves on to the next method. If the condition does not return true
	 * by the specified time, the text fails.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on("someSelectorString", id, 30);}</br>
	 * {@code SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on("someSelectorString", id, 30);} </br>
	 * {@code SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on("someSelectorString", id, 30, "style");} </br>
	 * {@code SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on("someSelectorString", id, 30, "style");} </br>
	 * {@code SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT).on("someSelectorString", id, 30);} </br>
	 * </p>
	 * @param element TODO
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @throws TestException
	 */
	public void on(TestElement element) throws TestException;
	
	/**
	 * <p>This method is meant to wait for an element or attribute to be
	 * present on the page. And if the Wait.CLICKABILITY_OF_ELEMENT param
	 * is set, it will wait for the element to be clickable.</p>
	 * <p>This method waits up to the specified amount of time for the condition
	 * to be true. If the condition returns true before the specified time, then
	 * the test moves on to the next method. If the condition does not return true
	 * by the specified time, the text fails.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement element = SHelper.get().element().get("someSelectorString", id);}</br>
	 * {@code SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE).on(element, 30);}</br>
	 * {@code SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).on(element, 30);} </br>
	 * {@code SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE).on(element, 30, "style");} </br>
	 * {@code SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT).on(element, 30, "style");} </br>
	 * {@code SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT).on(element, 30);} </br>
	 * </p>
	 * @param element - a webelement that is defined and found in the calling method
	 * @throws TestException
	 */
	public void on(WebElement element) throws TestException;
	
	/**
	 * <p>This method is meant to wait for the total number of elements to equal a certain
	 * number.</p>
	 * <p>This method waits up to the specified amount of time for the condition
	 * to be true. If the condition returns true before the specified time, then
	 * the test moves on to the next method. If the condition does not return true
	 * by the specified time, the text fails.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code List<WebElement> element = SHelper.get().element().getListOf("someSelectorString", cssSelector);} </br>
	 * {@code SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS).on(element, 3, 30);}</br>
	 * </p>
	 * @param element - a list of webelements that are defined and found in the calling method
	 * @throws TestException
	 */
	public void on(List<WebElement> element) throws TestException;

}