package shelper.interfaces;

import java.util.List;

import org.openqa.selenium.WebElement;
import log.TestException;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

public interface IElement {
	
	/**
	 * <p>This method is used to get a single webelement from a page so that
	 * it can be stored in the selenium WebElement object.</p>
	 * <p>Most SHelper methods will already define the WebElement object
	 * as a part of the method when passing the selector and by values in through
	 * the parameters. But there are certain scenarios where the WebElement should
	 * be defined outside of that. And this method provides that capability.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement element = SHelper.get().element().get("someSelectorString", id);}</br>
	 * </p>
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	 * WebElement to be found
	 * @return WebElement
	 * @throws TestException
	 */
	public WebElement get(Locator locator, By by) throws TestException;
	
	/**
	 * <p>This method is used to get a list of webelements that
	 * contain a similar selector from a page so that
	 * it can be stored in a list of the selenium WebElement object.</p>
	 * <p>Most SHelper methods will already define the WebElement object
	 * as a part of the method when passing the selector and by values in through
	 * the parameters. However, there are many scenarios where it is necessary to 
	 * list similar elements and iterate through that list to find the correct
	 * element or verify each and every element in the list. This method provides 
	 * that capability.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code List<WebElement> element = SHelper.get().element().getListOf("someSelectorString", cssSelector);}</br>
	 * </p>
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	 * WebElement to be found
	 * @return List<<WebElement>>
	 * @throws TestException
	 */
	public List<WebElement> getListOf(Locator locator, By by) throws TestException;
	
	/**
	 * <p>This method is used to wait for an element to display and then return
	 * a boolean value as to whether it did display or not.</p>
	 * <p>The wait was built in to this method because of timeout issues. This
	 * makes the isDisplayed method more robust and reliable.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code if(SHelper.get().element().isDisplayed("someSelectorString", id, 5)) { </br>doSomething();</br>
	 * }}</br>
	 * </p>
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	 * WebElement to be found
	 * @param i - the total amount of time alotted to wait for the condition to return true
	 * @return Boolean
	 * @throws TestException
	 */
	public Boolean isDisplayed(Locator locator, By by, int i) throws TestException;
	
	/**
	 * <p>This method is used to wait for an element to display and then return
	 * a boolean value as to whether it did display or not.</p>
	 * <p>The wait was built in to this method because of timeout issues. This
	 * makes the isDisplayed method more robust and reliable.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement element = SHelper.get().element().get("someSelectorString", id);
	 * </br>if(SHelper.get().element().isDisplayed("someSelectorString", id, 5)) { </br>doSomething();</br>
	 * }}</br>
	 * </p>
	 * @param element - a webelement that is defined and found in the calling method
	 * @param i - the total amount of time alotted to wait for the condition to return true
	 * @return Boolean
	 * @throws TestException
	 */
	public Boolean isDisplayed(WebElement element, int i) throws TestException;
	
	/**
	 * <p>This method is used to find an element that is 
	 * contained within a different element.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement originalElement = SHelper.get().element().get("someSelectorString", id); </br>
	 * WebElement element = SHelper.get().element().find(originalElement, "someSelectorString", id);}</br>
	 * </p>
	 * @param firstElement - the original webelement that is defined and found in the calling method 
	 * @param selectorString - the webelement selector string for the second webelement necessary for the 
	 * webelement to be found. This element should be found in the original element.
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.) for the second webelement. 
	 * Necessary for the WebElement to be found. This element should be found in the original element.
	 * @return WebElement
	 * @throws TestException
	 */
	public WebElement find(WebElement firstElement, Locator locator, By by) throws TestException;
	
	/**
	 * <p>This method is used to find an element that is 
	 * contained within a different element.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement element = SHelper.get().element().find("origElementSelectorString", cssSelector,
	 *  "someSelectorString", id);}</br>
	 * @param firstESelectorString - the webelement selector string for the original webelement necessary for the 
	 * webelement to be found.
	 * @param firstEBy - the type of selector being used (i.e id, name, cssSelector, xpath, etc.) for the original webelement. 
	 * Necessary for the WebElement to be found.
	 * @param selectorString - the webelement selector string for the second webelement necessary for the 
	 * webelement to be found. This element should be found in the original element.
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.) for the second webelement. 
	 * Necessary for the WebElement to be found. This element should be found in the original element.
	 * @return WebElement
	 * @throws TestException
	 */
	public WebElement find(Locator firstESelectorString, By firstEBy, Locator locator, By by) throws TestException;
	
	/**
	 * <p>This method is used to find a list of elements that contain
	 * the same selector string value that are 
	 * contained within a different element.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement originalElement = SHelper.get().element().get("someSelectorString", id); </br>
	 * List<WebElement> element = SHelper.get().element().findListOf(originalElement, "someSelectorString", id);}</br>
	 * </p>
	 * @param firstElement - the original webelement that is defined and found in the calling method 
	 * @param selectorString - the webelement selector string for the second webelement necessary for the 
	 * webelement to be found. This element should be found in the original element.
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.) for the second webelement. 
	 * Necessary for the WebElement to be found. This element should be found in the original element.
	 * @return List<<WebElement>>
	 * @throws TestException
	 */
	public List<WebElement> findListOf(WebElement firstElement, Locator locator, By by) throws TestException;
	
	/**
	 * <p>This method is used to find a list of elements that contain
	 * the same selector string value that are 
	 * contained within a different element.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code List<WebElement> element = SHelper.get().element().findListOf("originalElementSelector", xpath,
	 *  "someSelectorString", id);}</br>
	 * </p>
	 * @param firstESelectorString - the webelement selector string for the original webelement necessary for the 
	 * webelement to be found.
	 * @param firstEBy - the type of selector being used (i.e id, name, cssSelector, xpath, etc.) for the original webelement. 
	 * Necessary for the WebElement to be found.
	 * @param selectorString - the webelement selector string for the second webelement necessary for the 
	 * webelement to be found. This element should be found in the original element.
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.) for the second webelement. 
	 * Necessary for the WebElement to be found. This element should be found in the original element.
	 * @return List<<WebElement>>
	 * @throws TestException
	 */
	public List<WebElement> findListOf(Locator firstESelectorString, By firstEBy, Locator locator, By by) throws TestException;
	
	/**
	 * <p>This method is used to return whether an attribute is present or not.</p>
	 * <p>Note that it is only looking for the existence of an attribute itself, not the value
	 * of the attribute.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement element = SHelper.get().element().get("someSelectorString", id);
	 * if(SHelper.get().element().isAttributePresent(element, "style")) { </br>doSomething();</br>
	 * }}</br>
	 * </p>
	 * @param element - a webelement that is defined and found in the calling method
	 * @param attribute - the attribute the method should be validating the presence of.
	 * (i.e style, class, id, etc.)
	 * @return Boolean
	 * @throws TestException
	 */
	public Boolean isAttributePresent(WebElement element, String attribute) throws TestException;
	
	/**
	 * <p>This method is used to return whether an attribute is present or not.</p>
	 * <p>Note that it is only looking for the existence of an attribute itself, not the value
	 * of the attribute.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code if(SHelper.get().element().isAttributePresent("someSelectorString", id, "style")) 
	 * { </br>doSomething();</br>
	 * }}</br>
	 * </p>
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	 * WebElement to be found
	 * @param attribute - the attribute the method should be validating the presence of.
	 * (i.e style, class, id, etc.)
	 * @return Boolean
	 * @throws TestException
	 */
	public Boolean isAttributePresent(Locator locator, By by, String attribute) throws TestException;
	
	/**
	 * <p>This method is used to return whether an element is enabled on
	 * the page.</p>
	 * <p>This will generally return true for everything but disabled input elements.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code if(SHelper.get().element().isEnabled("someSelectorString", id)) 
	 * { </br>doSomething();</br>
	 * }}</br>
	 * </p>
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	 * WebElement to be found
	 * @return Boolean
	 * @throws TestException
	 */
	public Boolean isEnabled(Locator locator, By by) throws TestException;
	
	/**
	 * <p>This method is used to return whether an element is enabled on
	 * the page.</p>
	 * <p>This will generally return true for everything but disabled input elements.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement element = SHelper.get().element().get("someSelectorString", id);
	 * if(SHelper.get().element().isEnabled("someSelectorString", id)) 
	 * { </br>doSomething();</br>
	 * }}</br>
	 * </p>
	 * @param element - a webelement that is defined and found in the calling method 
	 * @return Boolean
	 * @throws TestException
	 */
	public Boolean isEnabled(WebElement element) throws TestException;
}
