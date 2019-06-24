package shelper.interfaces;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import log.TestException;
import shelper.vobjects.TestElement;

public interface IEnter {
	
	/**
	 * <p>This method is used to enter any text into a specified WebElement.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code SHelper.get().enter().textInto(Generic.ELEMENT.element(), "UserName");}
	 * </pre>
	 * @throws TestException
	 */
	public void textInto(TestElement element, String text) throws TestException;
	
	/**
	 * <p>This method is used to enter any text into a specified WebElement.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement element = SHelper.get().element().get(Generic.ELEMENT.element());
	 * SHelper.get().enter().textInto(element, "UserName");}
	 * </pre>
	 * @throws TestException
	 */
	public void textInto(WebElement element, String text) throws TestException;
	
	/**
	 * <p>This method is used to send and key or keys (as simulated on a keyboard) to a specified
	 * WebElement.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code SHelper.get().enter().textInto(Generic.ELEMENT.element(), Keys.CONTROL + "a");
	 * SHelper.get().enter().textInto(Generic.ELEMENT.element(), Keys.DOWN);
	 * SHelper.get().enter().textInto(Generic.ELEMENT.element(), Keys.CONTROL + Keys.ALT + Keys.DELETE);}
	 * </pre>
	 * @throws TestException
	 */
	public void textInto(TestElement element, Keys key) throws TestException;
	
	/**
	 * <p>This method is used to send and key or keys (as simulated on a keyboard) to a specified
	 * WebElement.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement element = SHelper.get().element().get(Generic.ELEMENT.element());
	 * SHelper.get().enter().textInto(element, Keys.CONTROL + "a");
	 * SHelper.get().enter().textInto(element, Keys.DOWN);
	 * SHelper.get().enter().textInto(element, Keys.CONTROL + Keys.ALT + Keys.DELETE);}
	 * </pre>
	 * @throws TestException
	 */
	public void textInto(WebElement element, Keys key) throws TestException;
	
	/**
	 * <p>This method is used to clear any and all text that is
	 * already present in a textbox/textfield at any given time.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code SHelper.get().enter().clear(Generic.ELEMENT.element());}
	 * </pre>
	 * @throws TestException
	 */
	public void clear(TestElement element) throws TestException;
	
	/**
	 * <p>This method is used to clear any and all text that is
	 * already present in a textbox/textfield at any given time.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement element = SHelper.get().element().get(Generic.ELEMENT.element());
	 * SHelper.get().enter().clear(element);}
	 * </pre>
	 * @throws TestException
	 */
	public void clear(WebElement element) throws TestException;

}
