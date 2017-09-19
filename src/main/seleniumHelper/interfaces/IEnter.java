package seleniumHelper.interfaces;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public interface IEnter {
	
	/**
	 * <p>This method is used to enter any text into a specified WebElement.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().enter().textInto("someSelectorString", id, "UserName")}</br>
	 * </p>
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	 * WebElement to be found
	 * @param text - the text value to type into the element
	 * @throws Exception
	 */
	public void textInto(String selectorString, String by, String text) throws Exception;
	
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
	 * @throws Exception
	 */
	public void textInto(WebElement element, String text) throws Exception;
	
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
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	 * WebElement to be found
	 * @param key - representations of pressable keys that aren't text.
	 * @throws Exception
	 */
	public void textInto(String selectorString, String by, Keys key) throws Exception;
	
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
	 * @throws Exception
	 */
	public void textInto(WebElement element, Keys key) throws Exception;
	
	/**
	 * <p>This method is used to clear any and all text that is
	 * already present in a textbox/textfield at any given time.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().enter().clear("someSelectorString", xpath)}</br>
	 * </p>
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	 * WebElement to be found
	 * @throws Exception
	 */
	public void clear(String selectorString, String by) throws Exception;
	
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
	 * @throws Exception
	 */
	public void clear(WebElement element) throws Exception;

}
