package com.warnermedia.selenium.click;

import org.openqa.selenium.WebElement;
import com.warnermedia.config.TestException;
import com.warnermedia.selenium.TestElement;

public interface IClick {

	/**
	 * <p>This method is meant to form the overall text command that specifies
	 * how and which element should be clicked.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code SHelper.get().click(VIA.SELENIUM).on(Generic.ELEMENT.element());
	 * 			- OR -
	 * SHelper.get().click(VIA.JQUERY).on(Generic.ELEMENT.element());
	 * 			- OR -
	 * SHelper.get().click(VIA.JAVASCRIPT).on(Generic.ELEMENT.element())}
	 * </pre>
	 * @throws TestException
	 */
	public void on(TestElement element) throws TestException;
	
	/**
	 * <p>This method is meant to form the overall text command that specifies
	 * how and which element should be clicked.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement SomeWebElement = SHelper.get().element().get(Generic.ELEMENT.element());
	 * SHelper.get().click(VIA.SELENIUM).on(SomeWebElement);
	 * 		- OR -
	 * SHelper.get().click(VIA.JQUERY).on(SomeWebElement);
	 * 		- OR -
	 * SHelper.get().click(VIA.JAVASCRIPT).on(SomeWebElement)}
	 * </pre>
	 * @throws TestException
	 */
	public void on(WebElement element) throws TestException;
	
	/**
	 * <p>This method is meant to form the overall text command that specifies
	 * how and which element should be clicked.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code SHelper.get().click(VIA.SELENIUM).on(Generic.ELEMENT.element(), "2");
	 * 		- OR -
	 * SHelper.get().click(VIA.JQUERY).on(Generic.ELEMENT.element(), "0");
	 * 		- OR -
	 * SHelper.get().click(VIA.JAVASCRIPT).on(Generic.ELEMENT.element(), "3")}
	 * </pre>
	 * @throws TestException
	 */
	public void on(TestElement element, String index) throws TestException;
	
	/**
	 * <p>This method is meant to form the overall text command that specifies
	 * how and which element should be clicked.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code SHelper.get().click(VIA.SELENIUM).on("someElementSelector", xpath, 2);
	 * 		- OR -
	 * SHelper.get().click(VIA.JQUERY).on("someElementSelector", cssSelector, 0);
	 * 		- OR -
	 * SHelper.get().click(VIA.JAVASCRIPT).on("someElementSelector", id, 3)}
	 * </pre>
	 * @throws TestException
	 */
	public void on(TestElement element, int index) throws TestException;
}
