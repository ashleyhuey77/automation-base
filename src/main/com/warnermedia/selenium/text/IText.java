package com.warnermedia.selenium.text;

import org.openqa.selenium.WebElement;
import com.warnermedia.config.TestException;
import com.warnermedia.selenium.TestElement;

public interface IText {

	/**
	 * <p>This method is meant to get the visible (i.e. not hidden by CSS) 
	 * innerText of this element, including sub-elements, without any leading or trailing whitespace.</p>
	 * <p>Note: the attribute param is not necessary when getting text from an element,
	 * however it is required when getting text from an attribute.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code String someValue = SHelper.get().text(Variable.ATTRIBUTE, VIA.SELENIUM).getFrom(Generic.ELEMENT.element(), "style");
	 * 		- OR -
	 * String someValue = SHelper.get().text(Variable.ELEMENT, VIA.SELENIUM).getFrom(Generic.ELEMENT.element());
	 * 		- OR -
	 * String someValue = SHelper.get().text(Variable.ELEMENT, VIA.JAVASCRIPT).getFrom(Generic.ELEMENT.element());}
	 * </pre>
	 * @throws TestException
	 */
	public String getFrom(TestElement element, String...attribute) throws TestException;
	
	/**
	 * <p>This method is meant to get the visible (i.e. not hidden by CSS) 
	 * innerText of this element, including sub-elements, without any leading or trailing whitespace.</p>
	 * <p>Note: the attribute param is not necessary when getting text from an element,
	 * however it is required when getting text from an attribute.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement element = SHelper.get().element().get(Generic.ELEMENT.element());
	 * String someValue = SHelper.get().text(Variable.ATTRIBUTE, VIA.SELENIUM).getFrom(element, "class");
	 * 		- OR -
	 * String someValue = SHelper.get().text(Variable.ELEMENT, VIA.SELENIUM).getFrom(element);
	 * 		- OR -
	 * String someValue = SHelper.get().text(Variable.ELEMENT, VIA.JAVASCRIPT).getFrom(element);}
	 * </pre>
	 * @throws TestException
	 */
	public String getFrom(WebElement element, String...attribute) throws TestException;
	
	/**
	 * <p>This method is meant to verify whether or not a specific text displays in a webelement and
	 * then returns a boolean value based on the result.</p>
	 * <p>Note: it is looking for the element to contain the expected text, not 
	 * to exactly match the expected text.</p>
	 * <p>Note: the attribute param is not necessary when getting text from an element,
	 * however it is required when getting text from an attribute.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code if (SHelper.get().text(Variable.ATTRIBUTE, VIA.SELENIUM).isDisplayed(Generic.ELEMENT.element(), "display: none;", "style")) {
	 * 	doSomething();
	 * }
	 * 		- OR -
	 * if (SHelper.get().text(Variable.ELEMENT, VIA.SELENIUM).isDisplayed(Generic.ELEMENT.element(), "Some Text")) {
	 * 	doSomething();
	 * }
	 * </pre>
	 * @throws TestException
	 */
	public Boolean isDisplayed(TestElement element, String expectedText, String...attribute) throws TestException;
	
	/**
	 * <p>This method is meant to verify whether or not a specific text displays in a webelement and
	 * then returns a boolean value based on the result.</p>
	 * <p>Note: it is looking for the element to contain the expected text, not 
	 * to exactly match the expected text.</p>
	 * <p>Note: the attribute param is not necessary when getting text from an element,
	 * however it is required when getting text from an attribute.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement element = SHelper.get().element().get("someSelectorString", id);
	 * if (SHelper.get().text(Variable.ATTRIBUTE, VIA.SELENIUM).isDisplayed(element, "display: none;", "style")) {
	 * 	doSomething();
	 * }
	 * 		- OR -
	 * if (SHelper.get().text(Variable.ELEMENT, VIA.SELENIUM).isDisplayed(element, "Some Text")) {
	 * 	doSomething();
	 * }
	 * </pre>
	 * @throws TestException
	 */
	public Boolean isDisplayed(WebElement element, String expectedText, String...attribute) throws TestException;
	
}
