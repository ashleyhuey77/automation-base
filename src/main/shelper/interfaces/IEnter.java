package shelper.interfaces;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import log.TestException;
import shelper.vobjects.TestElement;

public interface IEnter {
	
	/**
	 * <p>This method is used to enter any text into a specified WebElement.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().enter().textInto("someSelectorString", id, "UserName")}</br>
	 * </p>
	 * @param element TODO
	 * @param text - the text value to type into the element
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @throws TestException
	 */
	public void textInto(TestElement element, String text) throws TestException;
	
	/**
	 * <p>This method is used to enter any text into a specified WebElement.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement element = SHelper.get().element().get("someSelectorString", id);
	 * SHelper.get().enter().textInto(element, "UserName")}</br>
	 * </p>
	 * @param element - a webelement that is defined and found in the calling method
	 * @param text - the text value to type into the element
	 * @throws TestException
	 */
	public void textInto(WebElement element, String text) throws TestException;
	
	/**
	 * <p>This method is used to send and key or keys (as simulated on a keyboard) to a specified
	 * WebElement.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().enter().textInto("someSelectorString", id, Keys.CONTROL + "a")}</br>
	 * {@code SHelper.get().enter().textInto("someSelectorString", id, Keys.DOWN)}</br>
	 * {@code SHelper.get().enter().textInto("someSelectorString", id, Keys.CONTROL + Keys.ALT + Keys.DELETE)}</br>
	 * </p>
	 * @param element TODO
	 * @param key - representations of pressable keys that aren't text.
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @throws TestException
	 */
	public void textInto(TestElement element, Keys key) throws TestException;
	
	/**
	 * <p>This method is used to send and key or keys (as simulated on a keyboard) to a specified
	 * WebElement.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement element = SHelper.get().element().get("someSelectorString", id);}
	 * {@code SHelper.get().enter().textInto(element, Keys.CONTROL + "a")}</br>
	 * {@code SHelper.get().enter().textInto(element, Keys.DOWN)}</br>
	 * {@code SHelper.get().enter().textInto(element, Keys.CONTROL + Keys.ALT + Keys.DELETE)}</br>
	 * </p>
	 * @param element - a webelement that is defined and found in the calling method
	 * @param key - representations of pressable keys that aren't text.
	 * @throws TestException
	 */
	public void textInto(WebElement element, Keys key) throws TestException;
	
	/**
	 * <p>This method is used to clear any and all text that is
	 * already present in a textbox/textfield at any given time.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().enter().clear("someSelectorString", xpath)}</br>
	 * </p>
	 * @param element TODO
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @throws TestException
	 */
	public void clear(TestElement element) throws TestException;
	
	/**
	 * <p>This method is used to clear any and all text that is
	 * already present in a textbox/textfield at any given time.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement element = SHelper.get().element().get("someSelectorString", id);
	 * SHelper.get().enter().clear(element)}</br>
	 * </p>
	 * @param element - a webelement that is defined and found in the calling method
	 * @throws TestException
	 */
	public void clear(WebElement element) throws TestException;

}
