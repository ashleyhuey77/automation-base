package shelper.interfaces;

import org.openqa.selenium.WebElement;
import log.TestException;
import shelper.vobjects.TestElement;

public interface IWait {
	
	/**
	 * <p>This method is meant to wait for an element or attribute to be
	 * present on the page. And if the Wait.CLICKABILITY_OF_ELEMENT param
	 * is set, it will wait for the element to be clickable.</p>
	 * <p>This method waits up to the specified amount of time for the condition
	 * to be true. If the condition returns true before the specified time, then
	 * the test moves on to the next method. If the condition does not return true
	 * by the specified time, the text fails.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, 
	 *		new WaitBuilder().forAMaxTimeOf(20)).on(Generic.ELEMENT.element());
	 * 		- OR -
	 * SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
	 *		new WaitBuilder()
	 *		  .to(Condition.CONTAIN)
	 *		  .value("Test")
	 *		  .forAMaxTimeOf(20))
	 *		  .on(Generic.ELEMENT.element());
	 * 		- OR -
	 * SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, 
	 * 		new WaitBuilder().forAMaxTimeOf(1)).on(Generic.ELEMENT.element());
	 * 		- OR -
	 * SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
	 *		new WaitBuilder()
	 *		  .to(Condition.CONTAIN)
	 *		  .value("Cool")
	 *		  .forAMaxTimeOf(2))
	 *		  .on(Generic.ELEMENT.element());
	 *		- OR -
	 * SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, 
	 * 		new WaitBuilder()
	 * 		  .forAttribute("class")
	 * 		  .forAMaxTimeOf(1))
	 * 		  .on(Generic.ELEMENT.element());
	 * 		- OR -
	 * SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT,
	 *		new WaitBuilder()
	 *		  .forAttribute("class")
	 *		  .to(Condition.CONTAIN)
	 *	      .value("someClassValue")
	 *		  .forAMaxTimeOf(2))
	 *		  .on(Generic.ELEMENT.element());
	 *		- OR -
	 * SHelper.get().waitMethod(Wait.ATTRIBUTE_NOT_TO_BE_PRESENT, 
	 * 		new WaitBuilder().forAttribute("value").forAMaxTimeOf(1)).on(Generic.ELEMENT.element());
	 * 		- OR -
	 * SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
	 *		new WaitBuilder()
	 *		  .forAttribute("class")
	 *		  .to(Condition.CONTAIN)
	 *		  .value("help")
	 *		  .forAMaxTimeOf(2))
	 *		  .on(Generic.ELEMENT.element());
	 *		- OR -
	 * SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, 
	 * 		new WaitBuilder().forAMaxTimeOf(10)).on(Generic.ELEMENT.element());
	 * 		- OR -
	 * SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, 
	 * 		new WaitBuilder().withACountOf(1).forAMaxTimeOf(15)).on(Generic.ELEMENT.element());}
	 * </pre>
	 * @throws TestException
	 */
	public void on(TestElement element) throws TestException;
	
	/**
	 * <p>This method is meant to wait for an element or attribute to be
	 * present on the page. And if the Wait.CLICKABILITY_OF_ELEMENT param
	 * is set, it will wait for the element to be clickable.</p>
	 * <p>This method waits up to the specified amount of time for the condition
	 * to be true. If the condition returns true before the specified time, then
	 * the test moves on to the next method. If the condition does not return true
	 * by the specified time, the text fails.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.</p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * <pre>
	 * {@code WebElement element = SHelper.get().element().get(Generic.ELEMENT.element());
	 * SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, 
	 *		new WaitBuilder().forAMaxTimeOf(20)).on(element);
	 * 		- OR -
	 * SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT,
	 *		new WaitBuilder()
	 *		  .to(Condition.CONTAIN)
	 *		  .value("Test")
	 *		  .forAMaxTimeOf(20))
	 *		  .on(element);
	 * 		- OR -
	 * SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, 
	 * 		new WaitBuilder().forAMaxTimeOf(1)).on(element);
	 * 		- OR -
	 * SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT,
	 *		new WaitBuilder()
	 *		  .to(Condition.CONTAIN)
	 *		  .value("Cool")
	 *		  .forAMaxTimeOf(2))
	 *		  .on(element);
	 *		- OR -
	 * SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE, 
	 * 		new WaitBuilder()
	 * 		  .forAttribute("class")
	 * 		  .forAMaxTimeOf(1))
	 * 		  .on(element);
	 * 		- OR -
	 * SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT,
	 *		new WaitBuilder()
	 *		  .forAttribute("class")
	 *		  .to(Condition.CONTAIN)
	 *	      .value("someClassValue")
	 *		  .forAMaxTimeOf(2))
	 *		  .on(element);
	 *		- OR -
	 * SHelper.get().waitMethod(Wait.ATTRIBUTE_NOT_TO_BE_PRESENT, 
	 * 		new WaitBuilder().forAttribute("value").forAMaxTimeOf(1)).on(element);
	 * 		- OR -
	 * SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT,
	 *		new WaitBuilder()
	 *		  .forAttribute("class")
	 *		  .to(Condition.CONTAIN)
	 *		  .value("help")
	 *		  .forAMaxTimeOf(2))
	 *		  .on(element);
	 *		- OR -
	 * SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, 
	 * 		new WaitBuilder().forAMaxTimeOf(10)).on(element);
	 * 		- OR -
	 * SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, 
	 * 		new WaitBuilder().withACountOf(1).forAMaxTimeOf(15)).on(element);}
	 * </pre>
	 * @throws TestException
	 */
	public void on(WebElement element) throws TestException;

}