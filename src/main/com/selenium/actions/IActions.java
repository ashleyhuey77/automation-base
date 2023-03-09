package com.selenium.actions;

import com.config.TestException;
import com.selenium.TestElement;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public interface IActions {
	
public void moveTo(TestElement element) throws TestException;
	
	/**
	 * <p>This method is meant to simulate moving the mouse to the middle 
	 * of the specified element.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * Ex:
	 * {@code WebElement element = SHelper.get().element().get(Generic.ELEMENT.element());
	 * SHelper.get().actions().moveTo(element)}
	 * </pre>
	 * </p>
	 * @throws TestException
	 */
	public void moveTo(WebElement element) throws TestException;
	
	/**
	 * <p>This method is meant to simulate the mouse hovering over 
	 * a specified element.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code SHelper.get().actions().mouseOver(Generic.ELEMENT.element());}
	 * </pre>
	 * @throws TestException
	 */
	public void mouseOver(TestElement element) throws TestException;
	
	/**
	 * <p>This method is meant to simulate the mouse hovering over 
	 * a specified element.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement element = SHelper.get().element().get(Generic.ELEMENT.element());
	 * SHelper.get().actions().mouseOver(element)}
	 * </pre>
	 * @throws TestException
	 */
	public void mouseOver(WebElement element) throws TestException;
	
	/**
	 * <p>This method is meant to simulate the mouse dragging and dropping an element
	 * via javascript scripts created by Brendan. It contains params that I'm not entirely sure what
	 * they are for. May require further discussion with Brendan.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.
	 * </p>
	 * @throws TestException
	 */
	public void dragAndDrop(WebElement firstElement, WebElement secondElement, String firstElementSelectorString, String index, String stepWidth, String stepDelay, String dx, String dy) throws TestException, InterruptedException;
	
	/**
	 * <p>This method is meant to simulate the mouse dragging and dropping an element
	 * via javascript scripts created by Brendan. It contains params that I'm not entirely sure what
	 * they are for. May require further discussion with Brendan.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.
	 * </p>
	 * @throws TestException
	 */
	public void dragAndDrop(TestElement element1, TestElement element2, String stepWidth, String stepDelay, String dx, String dy) throws TestException, InterruptedException;
	
	/**
	 * <p>This method is meant to simulate the mouse dragging and dropping an element
	 * using code from the Selenium libraries.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.
	 * </p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code SHelper.get().actions().dragAndDrop(Generic.ELEMENT1.element(), Generic.ELEMENT2.element(), 10)}
	 * </pre>
	 * @throws TestException
	 */
	public void dragAndDrop(TestElement dragElement, TestElement dropElement,  Duration timeTowait) throws TestException, InterruptedException;
	
	/**
	 * <p>This method is meant to simulate the mouse dragging and dropping an element
	 * using code from the Selenium libraries.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.
	 * </p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement element1 = SHelper.get().element().get(Generic.ELEMENT1.element());
	 * WebElement element2 = SHelper.get().element().get(Generic.ELEMENT2.element());
	 * SHelper.get().actions().dragAndDrop(element1, element2, 10);}
	 * </pre>
	 * @throws TestException
	 */
	public void dragAndDrop(WebElement elementToBeDragged,  WebElement elementToBeDropped, Duration timeTowait) throws TestException, InterruptedException;
	
	/**
	 * <p>This method is meant to simulate scrolling on the page to a specified
	 * webelement.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code SHelper.get().actions().scrollTo(Generic.ELEMENT.element());}
	 * </pre>
	 * @throws TestException
	 */
	public void scrollTo(TestElement element) throws TestException;
	
	
	/**
	 * <p>This method is meant to simulate scrolling on the page to a specified
	 * webelement</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement element = SHelper.get().element().get(Generic.ELEMENT.element());
	 * SHelper.get().actions().scrollTo(element);}
	 * </pre>
	 * @throws TestException
	 */
	public void scrollTo(WebElement element) throws TestException;
	
	/**
	 * <p>This method is meant to simulate selecting an option from a dropdown
	 * menu using selenium libraries and methods.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement element = SHelper.get().element().get(Generic.ELEMENT.element());
	 * SHelper.get().actions().selectFromDropDown(element, "Option 1", SelectBy.VALUE);}
	 * </pre>
	 * @throws TestException
	 */
	public void selectFromDropDown(WebElement element, String value, SelectBy selectType) throws TestException;
	
	/**
	 * <p>This method is meant to simulate selecting an option from a dropdown
	 * menu using selenium libraries and methods.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code SHelper.get().actions().selectFromDropDown(Generic.ELEMENT.element(), "Option 1", SelectBy.VALUE);}
	 * </pre>
	 * @throws TestException
	 */
	public void selectFromDropDown(TestElement element, String value, SelectBy selectType) throws TestException;

}
