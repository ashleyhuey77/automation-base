package com.selenium.element;

import java.time.Duration;
import java.util.List;

import com.config.TestException;
import com.selenium.TestElement;
import org.openqa.selenium.WebElement;

public interface IElement {
	
	/**
	 * <p>This method is used to get a single webelement from a page so that
	 * it can be stored in the selenium WebElement object.</p>
	 * <p>Most SHelper methods will already define the WebElement object
	 * as a part of the method when passing the selector and by values in through
	 * the parameters. But there are certain scenarios where the WebElement should
	 * be defined outside of that. And this method provides that capability.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement element = SHelper.get().element().get(Generic.ELEMENT.element());}
	 * </pre>
	 * @throws TestException
	 */
	public WebElement get(TestElement element) throws TestException;
	
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
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code List<WebElement> element = SHelper.get().element().getListOf(Generic.ELEMENT.element());}
	 * </pre>
	 * @throws TestException
	 */
	public List<WebElement> getListOf(TestElement element) throws TestException;
	
	/**
	 * <p>This method is used to wait for an element to display and then return
	 * a boolean value as to whether it did display or not.</p>
	 * <p>The wait was built in to this method because of timeout issues. This
	 * makes the isDisplayed method more robust and reliable.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code if(SHelper.get().element().isDisplayed(Generic.ELEMENT.element(), 5)) { 
	 * 	doSomething();
	 * }
	 * </pre>
	 * @throws TestException
	 */
	public Boolean isDisplayed(TestElement element, Duration i) throws TestException;
	
	/**
	 * <p>This method is used to wait for an element to display and then return
	 * a boolean value as to whether it did display or not.</p>
	 * <p>The wait was built in to this method because of timeout issues. This
	 * makes the isDisplayed method more robust and reliable.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement element = SHelper.get().element().get(Generic.ELEMENT.element());
	 * if(SHelper.get().element().isDisplayed(element, 5)) { 
	 * 	doSomething();
	 * }
	 * </pre>
	 * @throws TestException
	 */
	public Boolean isDisplayed(WebElement element, Duration i) throws TestException;
	
	/**
	 * <p>This method is used to find an element that is 
	 * contained within a different element.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement originalElement = SHelper.get().element().get(Generic.ELEMENT1.element());
	 * WebElement element = SHelper.get().element().find(originalElement, Generic.ELEMENT2.element());}
	 * </pre>
	 * @throws TestException
	 */
	public WebElement find(WebElement firstElement, TestElement secondElement) throws TestException;
	
	/**
	 * <p>This method is used to find an element that is 
	 * contained within a different element.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement element = SHelper.get().element().find(Generic.ELEMENT1.element(), Generic.ELEMENT2.element());}
	 *  </pre>
	 * @throws TestException
	 */
	public WebElement find(TestElement firstElement, TestElement secondElement) throws TestException;
	
	/**
	 * <p>This method is used to find a list of elements that contain
	 * the same selector string value that are 
	 * contained within a different element.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement originalElement = SHelper.get().element().get(Generic.ELEMENT1.element());
	 * List<WebElement> element = SHelper.get().element().findListOf(originalElement, Generic.ELEMENT2.element());}
	 * </pre>
	 * @throws TestException
	 */
	public List<WebElement> findListOf(WebElement firstElement, TestElement secondElement) throws TestException;
	
	/**
	 * <p>This method is used to find a list of elements that contain
	 * the same selector string value that are 
	 * contained within a different element.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code List<WebElement> element = SHelper.get().element().findListOf(Generic.ELEMENT1.element(), Generic.ELEMENT2.element());}
	 * </pre>
	 * @throws TestException
	 */
	public List<WebElement> findListOf(TestElement firstElement, TestElement secondElement) throws TestException;
	
	/**
	 * <p>This method is used to return whether an attribute is present or not.</p>
	 * <p>Note that it is only looking for the existence of an attribute itself, not the value
	 * of the attribute.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement element = SHelper.get().element().get(Generic.ELEMENT.element());
	 * if(SHelper.get().element().isAttributePresent(element, "style")) { 
	 * 	doSomething();
	 * }
	 * </pre>
	 * @throws TestException
	 */
	public Boolean isAttributePresent(WebElement element, String attribute) throws TestException;
	
	/**
	 * <p>This method is used to return whether an attribute is present or not.</p>
	 * <p>Note that it is only looking for the existence of an attribute itself, not the value
	 * of the attribute.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code if(SHelper.get().element().isAttributePresent(Generic.ELEMENT.element(), "style")) { 
	 * 	doSomething();
	 * }
	 * </pre>
	 * @throws TestException
	 */
	public Boolean isAttributePresent(TestElement element, String attribute) throws TestException;
	
	/**
	 * <p>This method is used to return whether an element is enabled on
	 * the page.</p>
	 * <p>This will generally return true for everything but disabled input elements.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code if(SHelper.get().element().isEnabled(Generic.ELEMENT.element())) { 
	 * 	doSomething();
	 * }
	 * </pre>
	 * @throws TestException
	 */
	public Boolean isEnabled(TestElement element) throws TestException;
	
	/**
	 * <p>This method is used to return whether an element is enabled on
	 * the page.</p>
	 * <p>This will generally return true for everything but disabled input elements.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement element = SHelper.get().element().get(Generic.ELEMENT.element());
	 * if(SHelper.get().element().isEnabled(element)) { 
	 * 	doSomething();
	 * }
	 * </pre>
	 * @throws TestException
	 */
	public Boolean isEnabled(WebElement element) throws TestException;
	
	/**
	 * <p>This method is used to return whether an element is clickable on
	 * the page.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement element = SHelper.get().element().get(Generic.ELEMENT.element());
	 * if(SHelper.get().element().isEnabled(element)) { 
	 * 	doSomething();
	 * }
	 * </pre>
	 * @throws TestException
	 */
	public Boolean isClickable(TestElement element) throws TestException;
	
	/**
	 * <p>This method is used to return whether an element is clickable on
	 * the page.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement element = SHelper.get().element().get(Generic.ELEMENT.element());
	 * if(SHelper.get().element().isEnabled(element)) { 
	 * 	doSomething();
	 * }
	 * </pre>
	 * @throws TestException
	 */
	public Boolean isClickable(WebElement element) throws TestException;
}
