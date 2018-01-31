package seleniumHelper.interfaces;

import org.openqa.selenium.WebElement;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;

public interface IText {

	/**
	 * <p>This method is meant to get the visible (i.e. not hidden by CSS) 
	 * innerText of this element, including sub-elements, without any leading or trailing whitespace.</p>
	 * <p>Note: the attribute param is not necessary when getting text from an element,
	 * however it is required when getting text from an attribute.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code String someValue = SHelper.get().text(Variable.ATTRIBUTE, VIA.SELENIUM).getFrom("someSelectorString", xpath, "style");}</br>
	 * {@code String someValue = SHelper.get().text(Variable.ELEMENT, VIA.SELENIUM).getFrom("someSelectorString", id);} </br>
	 * {@code String someValue = SHelper.get().text(Variable.ELEMENT, VIA.JAVASCRIPT).getFrom("someSelectorString", cssSelector);} </br>
	 * </p>
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	 * WebElement to be found
	 * @param attribute - the attribute the method should be validating the presence of.
	 * (i.e style, class, id, etc.)
	 * @return String
	 * @throws Exception
	 */
	public String getFrom(Locator locator, By by, String...attribute) throws Exception;
	
	/**
	 * <p>This method is meant to get the visible (i.e. not hidden by CSS) 
	 * innerText of this element, including sub-elements, without any leading or trailing whitespace.</p>
	 * <p>Note: the attribute param is not necessary when getting text from an element,
	 * however it is required when getting text from an attribute.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement element = SHelper.get().element().get("someSelectorString", id);}</br>
	 * {@code String someValue = SHelper.get().text(Variable.ATTRIBUTE, VIA.SELENIUM).getFrom(element, "class");}</br>
	 * {@code String someValue = SHelper.get().text(Variable.ELEMENT, VIA.SELENIUM).getFrom(element);} </br>
	 * {@code String someValue = SHelper.get().text(Variable.ELEMENT, VIA.JAVASCRIPT).getFrom(element);} </br>
	 * </p>
	 * @param element - a webelement that is defined and found in the calling method
	 * @param attribute - the attribute the method should be validating the presence of.
	 * (i.e style, class, id, etc.)
	 * @return String
	 * @throws Exception
	 */
	public String getFrom(WebElement element, String...attribute) throws Exception;
	
	/**
	 * <p>This method is meant to verify whether or not a specific text displays in a webelement and
	 * then returns a boolean value based on the result.</p>
	 * <p>Note: it is looking for the element to contain the expected text, not 
	 * to exactly match the expected text.</p>
	 * <p>Note: the attribute param is not necessary when getting text from an element,
	 * however it is required when getting text from an attribute.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code if (SHelper.get().text(Variable.ATTRIBUTE, VIA.SELENIUM).isDisplayed(
	 * "someSelectorString", xpath, "display: none;", "style")) {
	 * </br> doSomething();}</br>
	 * {@code if (SHelper.get().text(Variable.ELEMENT, VIA.SELENIUM).isDisplayed(
	 * "someSelectorString", xpath, "Some Text")) {
	 * </br> doSomething();} </br>
	 * </p>
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	 * WebElement to be found
	 * @param attribute - the attribute the method should be validating the presence of.
	 * (i.e style, class, id, etc.)
	 * @param expectedText - the text that is expected to appear in the element or attribute.
	 * @return Boolean
	 * @throws Exception
	 */
	public Boolean isDisplayed(Locator locator, By by, String expectedText, String...attribute) throws Exception;
	
	/**
	 * <p>This method is meant to verify whether or not a specific text displays in a webelement and
	 * then returns a boolean value based on the result.</p>
	 * <p>Note: it is looking for the element to contain the expected text, not 
	 * to exactly match the expected text.</p>
	 * <p>Note: the attribute param is not necessary when getting text from an element,
	 * however it is required when getting text from an attribute.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement element = SHelper.get().element().get("someSelectorString", id);} </br>
	 * {@code if (SHelper.get().text(Variable.ATTRIBUTE, VIA.SELENIUM).isDisplayed(
	 * element, "display: none;", "style")) {
	 * </br> doSomething();}</br>
	 * {@code if (SHelper.get().text(Variable.ELEMENT, VIA.SELENIUM).isDisplayed(
	 * element, "Some Text")) {
	 * </br> doSomething();} </br>
	 * </p>
	 * @param element - a webelement that is defined and found in the calling method
	 * @param attribute - the attribute the method should be validating the presence of.
	 * (i.e style, class, id, etc.)
	 * @param expectedText - the text that is expected to appear in the element or attribute.
	 * @return Boolean
	 * @throws Exception
	 */
	public Boolean isDisplayed(WebElement element, String expectedText, String...attribute) throws Exception;
	
}
