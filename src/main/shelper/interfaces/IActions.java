package shelper.interfaces;

import org.openqa.selenium.WebElement;
import log.TestException;
import shelper.enums.SelectType;
import shelper.vobjects.TestElement;

public interface IActions {
	
public void moveTo(TestElement element) throws TestException;
	
	/**
	 * <p>This method is meant to simulate moving the mouse to the middle 
	 * of the specified element.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement element = SHelper.get().element().get("someSelectorString", id);}</br>
	 * {@code SHelper.get().actions().moveTo(element)}</br>
	 * </p>
	 * @param element - a webelement that is defined and found in the calling method
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
	 * {@code SHelper.get().actions().mouseOver("someSelectorString", id)}</br>
	 * </p>
	 * @param element TODO
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
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
	 * {@code WebElement element = SHelper.get().element().get("someSelectorString", id);}</br>
	 * {@code SHelper.get().actions().mouseOver(element)}</br>
	 * </p>
	 * @param element - a webelement that is defined and found in the calling method
	 * @throws TestException
	 */
	public void mouseOver(WebElement element) throws TestException;
	
	/**
	 * <p>This method is meant to simulate the mouse dragging and dropping an element
	 * via javascript scripts created by Brendan. It contains params that I'm not entirely sure what
	 * they are for. May require further discussion with Brendan.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * </p>
	 * @throws TestException
	 */
	public void dragAndDrop(WebElement firstElement, WebElement secondElement, String firstElementSelectorString, String index, String stepWidth, String stepDelay, String dx, String dy) throws TestException, InterruptedException;
	
	/**
	 * <p>This method is meant to simulate the mouse dragging and dropping an element
	 * via javascript scripts created by Brendan. It contains params that I'm not entirely sure what
	 * they are for. May require further discussion with Brendan.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * </p>
	 * @param element1 TODO
	 * @param element2 TODO
	 * @throws TestException
	 */
	public void dragAndDrop(TestElement element1, TestElement element2, String stepWidth, String stepDelay, String dx, String dy) throws TestException, InterruptedException;
	
	/**
	 * <p>This method is meant to simulate the mouse dragging and dropping an element
	 * using code from the Selenium libraries.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * </p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().actions().dragAndDrop("someSelectorString", id, "someOtherSelectorString", id, 10)}</br>
	 * </p>
	 * @param dragElement TODO
	 * @param dropElement TODO
	 * @param timeTowait - the total time to wait inbetween drags and drops.
	 * @param dragable - the webelement selector string necessary for the webelement to be found for the
	 * item that is to be dragged.
	 * @param dragableby - the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	 * WebElement to be found for the item that is to be dragged.
	 * @param dropable - the webelement selector string necessary for the webelement to be found for the 
	 * element that is to be dropped into.
	 * @param dropableby - the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	 * WebElement to be found for the element that is to be dropped into.
	 * @throws TestException
	 */
	public void dragAndDrop(TestElement dragElement, TestElement dropElement,  int timeTowait) throws TestException, InterruptedException;
	
	/**
	 * <p>This method is meant to simulate the mouse dragging and dropping an element
	 * using code from the Selenium libraries.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * </p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().actions().dragAndDrop("someSelectorString", id, "someOtherSelectorString", id, 10)}</br>
	 * </p>
	 * @param elementToBeDragged - the webelement for the item that is to be dragged.
	 * @param elementToBeDropped - the webelement for the element that is to be dropped into.
	 * @param timeTowait - the total time to wait inbetween drags and drops.
	 * @throws TestException
	 */
	public void dragAndDrop(WebElement elementToBeDragged,  WebElement elementToBeDropped, int timeTowait) throws TestException, InterruptedException;
	
	/**
	 * <p>This method is meant to simulate scrolling on the page to a specified
	 * webelement.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().actions().scrollTo("someSelectorString", cssSelector)}</br>
	 * </p>
	 * @param element TODO
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @throws TestException
	 */
	public void scrollTo(TestElement element) throws TestException;
	
	
	/**
	 * <p>This method is meant to simulate scrolling on the page to a specified
	 * webelement</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement element = SHelper.get().element().get("someSelectorString", id);}</br>
	 * {@code SHelper.get().actions().scrollTo(element)}</br>
	 * </p>
	 * @param element - a webelement that is defined and found in the calling method
	 * @throws TestException
	 */
	public void scrollTo(WebElement element) throws TestException;
	
	/**
	 * <p>This method is meant to simulate selecting an option from a dropdown
	 * menu using selenium libraries and methods.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement element = SHelper.get().element().get("someSelectorString", id);}</br>
	 * {@code SHelper.get().actions().selectFromDropDown(element)}</br>
	 * </p>
	 * @param element - a webelement that is defined and found in the calling method
	 * @param value - the option that is to be selected from the dropdown menu
	 * @param selectType - the method by which the option is to be selected
	 * @throws TestException
	 */
	public void selectFromDropDown(WebElement element, String value, SelectType selectType) throws TestException;
	
	/**
	 * <p>This method is meant to simulate selecting an option from a dropdown
	 * menu using selenium libraries and methods.<</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().actions().selectFromDropDown("someSelectorString", xpath)}</br>
	 * </p>
	 * @param element TODO
	 * @param value - the option that is to be selected from the dropdown menu
	 * @param selectType - the method by which the option is to be selected
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @throws TestException
	 */
	public void selectFromDropDown(TestElement element, String value, SelectType selectType) throws TestException;

}
