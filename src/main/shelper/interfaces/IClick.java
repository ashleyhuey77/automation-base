package shelper.interfaces;

import org.openqa.selenium.WebElement;
import log.TestException;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

public interface IClick {

	/**
	 * <p>This method is meant to form the overall text command that specifies
	 * how and which element should be clicked.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().click(VIA.SELENIUM).on("someElementSelector", xpath)}</br>
	 * {@code SHelper.get().click(VIA.JQUERY).on("someElementSelector", cssSelector)} </br>
	 * {@code SHelper.get().click(VIA.JAVASCRIPT).on("someElementSelector", id)} </br>
	 * </p>
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	 * WebElement to be found
	 * @throws TestException
	 */
	public void on(Locator locator, By by) throws TestException;
	
	/**
	 * <p>This method is meant to form the overall text command that specifies
	 * how and which element should be clicked.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement SomeWebElement = SHelper.get().element().get();}
	 * {@code SHelper.get().click(VIA.SELENIUM).on(SomeWebElement)}</br>
	 * {@code SHelper.get().click(VIA.JQUERY).on(SomeWebElement)} </br>
	 * {@code SHelper.get().click(VIA.JAVASCRIPT).on(SomeWebElement)} </br>
	 * </p>
	 * @param element - a webelement that is defined and found in the calling method 
	 * @throws TestException
	 */
	public void on(WebElement element) throws TestException;
	
	/**
	 * <p>This method is meant to form the overall text command that specifies
	 * how and which element should be clicked.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().click(VIA.SELENIUM).on("someElementSelector", xpath, "2")}</br>
	 * {@code SHelper.get().click(VIA.JQUERY).on("someElementSelector", cssSelector, "0")} </br>
	 * {@code SHelper.get().click(VIA.JAVASCRIPT).on("someElementSelector", id, "3")} </br>
	 * </p>
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	 * WebElement to be found
	 * @param index - the index used to identify a particular element in a list of elements that contain
     * more than one element with a similar selector
	 * @throws TestException
	 */
	public void on(Locator locator, By by, String index) throws TestException;
	
	/**
	 * <p>This method is meant to form the overall text command that specifies
	 * how and which element should be clicked.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().click(VIA.SELENIUM).on("someElementSelector", xpath, 2)}</br>
	 * {@code SHelper.get().click(VIA.JQUERY).on("someElementSelector", cssSelector, 0)} </br>
	 * {@code SHelper.get().click(VIA.JAVASCRIPT).on("someElementSelector", id, 3)} </br>
	 * </p>
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	 * WebElement to be found
	 * @param index - the index used to identify a particular element in a list of elements that contain
     * more than one element with a similar selector
	 * @throws TestException
	 */
	public void on(Locator locator, By by, int index) throws TestException;
}
