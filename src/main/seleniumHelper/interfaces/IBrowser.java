package seleniumHelper.interfaces;

import org.openqa.selenium.WebElement;

import seleniumHelper.enums.BrowserObject;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;

public interface IBrowser {
	
	/**
	 * <p>This method is meant to switch to a browser object. This method is primarily used
	 * for switching between frames or iFrames.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code WebElement element = SHelper.get().element().get("someSelectorString", id);}</br>
	 * {@code SHelper.get().browser().switchTo(BrowserObject.FRAME, element)}</br>
	 * </p>
	 * @param object - the browser object to which the focus should switch
	 * @param element - a webelement that is defined and found in the calling method
	 * @throws Exception
	 */
	public void switchTo(BrowserObject object, WebElement element) throws Exception;
	
	/**
	 * <p>This method is meant to switch to a browser object. This method is primarily used
	 * for switching between frames or iFrames.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().browser().switchTo(BrowserObject.FRAME, "someElementSelector", id)}</br>
	 * </p>
	 * @param object - the browser object to which the focus should switch
	 * @param selectorString - the webelement selector string necessary for the webelement to be found
	 * @param by - the type of selector being used (i.e id, name, cssSelector, xpath, etc.). Necessary for the 
	 * WebElement to be found
	 * @throws Exception
	 */
	public void switchTo(BrowserObject object, Locator locator, By by) throws Exception;
	
	/**
	 * <p>This method is meant to switch to a browser object. This method is primarily used
	 * for switching between frames or iFrames.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().browser().switchTo(BrowserObject.FRAME, "someElementSelector", id)}</br>
	 * </p>
	 * @param object - the browser object to which the focus should switch
	 * @param name - the name of the frame that is to be switched to
	 * @throws Exception
	 */
	public void switchTo(BrowserObject object, String name) throws Exception;
	
	/**
	 * <p>This method is meant to switch to a browser object. This method is primarily used
	 * for anything other than frames such as Windows, tabs, default content, alerts, etc.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().browser().switchTo(BrowserObject.FRAME, "frame name")}</br>
	 * </p>
	 * @param object - the browser object to which the focus should switch
	 * @throws Exception
	 */
	public void switchTo(BrowserObject object) throws Exception;
	
	/**
	 * <p>This method is meant to close all open tabs except the one that contains the
	 * browser focus. This method can be extended to also close other browser objects
	 * as well. There has just not been a need for it up to this point.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().browser().close()}</br>
	 * </p>
	 * @throws Exception
	 */
	public void close() throws Exception;
	
	/**
	 * <p>This method is meant to open a new tab. It does not set focus to
	 * the newl opened tab. This method can be extended to also close other 
	 * browser objects as well. There has just not been a need for it up 
	 * to this point.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().browser().open()}</br>
	 * </p>
	 * @throws Exception
	 */
	public void open() throws Exception;
	
	/**
	 * <p>This method is meant to input a url and navigate to the 
	 * entered url.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().browser().navigateTo()}</br>
	 * </p>
	 * @param url - the desired url one wishes to navigate to.
	 * @throws Exception
	 */
	public void navigateTo(String url) throws Exception;
	
	/**
	 * <p>This method is meant to wait for the total number of windows to 
	 * equal a certain number.</p>
	 * <p>It was placed inside browser instead of wait because the language would not
	 * have been as clear as to what its functionality was if it were in the wait class.
	 * The point is to write these commands like a sentence and it should be clear as
	 * to what the method does after the sentence is formed. Might need to look into 
	 * refactoring this and finding a better home for it in the future.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().browser().waitForWindowCount(30, 2)}</br>
	 * </p>
	 * @param i - the total amount of time alotted to wait for the condition to return true
	 * @param expectedCount - the total number of windows/tabs expected to be open at one time
	 * @throws Exception
	 */
	public void waitForWindowCount(int i, int expectedCount) throws Exception;
	
	/**
	 * <p>This method is meant to switch to a browser object. This method is primarily used
	 * for switching to a particular window based on its index in the total list of
	 * open windows.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().browser().switchTo(BrowserObject.WINDOW, 0)}</br>
	 * </p>
	 * @param object - the browser object to which the focus should switch
	 * @param i - the index of the window that focus is to be switched to
	 * @throws Exception
	 */
	public void switchTo(BrowserObject object, int i) throws Exception;
	
	public void back() throws Exception; 

}
