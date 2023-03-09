package com.selenium;

import com.selenium.click.IClick;
import com.selenium.click.RetryClick;
import com.selenium.page.IPage;
import com.selenium.page.Page;
import com.config.TestException;
import com.selenium.actions.ElementActions;
import com.selenium.actions.IActions;
import com.selenium.browser.Browser;
import com.selenium.browser.IBrowser;
import com.selenium.element.Element;
import com.selenium.element.IElement;
import com.selenium.enter.Enter;
import com.selenium.enter.IEnter;
import com.selenium.enter.JSEnter;
import com.selenium.shared.Via;
import com.selenium.text.AttributeText;
import com.selenium.text.IText;
import com.selenium.text.JSPageText;
import com.selenium.text.PageText;
import com.selenium.text.Variable;
import com.selenium.wait.ClickableElement;
import com.selenium.wait.ElementCount;
import com.selenium.wait.IWait;
import com.selenium.wait.NonPresentAttribute;
import com.selenium.wait.NonPresentAttributeText;
import com.selenium.wait.NonPresentElement;
import com.selenium.wait.NonPresentElementText;
import com.selenium.wait.PresentAttribute;
import com.selenium.wait.PresentAttributeText;
import com.selenium.wait.PresentElement;
import com.selenium.wait.PresentElementText;
import com.selenium.wait.Wait;
import com.selenium.wait.WaitBuilder;
import com.utils.ex.ErrorCode;
import com.utils.ex.SeleniumException;
import com.utils.retry.Retry;
import com.utils.retry.TestOperation;

public class SeleniumHelper {

	private static TestOperation op;

	/**
	 * <p>
	 * This method is meant to form the overall text
	 * command that specifies what should be done with
	 * a particular webelement text.
	 * </p>
	 * <p>
	 * Overall, the methods linked together should
	 * form a sentence that shapes which methods are
	 * executed.
	 * </p>
	 * <p>
	 * Examples of the different types of sentences
	 * that can be formed are as follows:
	 * </p>
	 * <pre>
	 * {@code SHelper.get().text(Variable.ATTRIBUTE, VIA.SELENIUM)
	 * SHelper.get().text(Variable.ELEMENT, VIA.SELENIUM)
	 * SHelper.get().text(Variable.ELEMENT, VIA.JAVASCRIPT)}
	 * </pre>
	 * @throws TestException
	 */
	public IText text(Variable variable, Via actionType) throws TestException {
		IText text = null;
		switch (actionType) {
			case JAVASCRIPT:
				text = new JSPageText();
				break;
			case SELENIUM:
				switch (variable) {
					case ATTRIBUTE:
						text = new AttributeText();
						break;
					case ELEMENT:
						text = new PageText();
						break;
					default:
						throw new TestException("Please select a valid Text type from the TextType enum.");
				}
				break;
			default:
				throw new TestException("Please select a valid Action type from the ActionType enum.");
		}
		return text;
	}

	/**
	 * <p>
	 * This method is meant to form the overall text
	 * command that specifies how and which element
	 * should be clicked.
	 * </p>
	 * <p>
	 * Overall, the methods linked together should
	 * form a sentence that shapes which methods are
	 * executed.
	 * </p>
	 * <p>
	 * Examples of the different types of sentences
	 * that can be formed are as follows:
	 * </p>
	 * <pre>
	 * {@code SHelper.get().click(VIA.SELENIUM)
	 * SHelper.get().click(VIA.JQUERY)
	 * SHelper.get().click(VIA.JAVASCRIPT)}
	 * </pre>
	 * @throws TestException
	 */
	public IClick click(Via type) throws TestException {
		TestOperation test = new RetryClick(type,
				new SeleniumException(ErrorCode.CLICK),
				new SeleniumException(ErrorCode.FIND_ELEMENT)
		);
		final Retry retry = new Retry(
				test,
				3,  //3 attempts
				100, //100 ms delay between attempts
				e -> SeleniumException.class.isAssignableFrom(e.getClass())
		);
		op = retry;
		return (IClick) op.perform();
	}

	/**
	 * <p>
	 * This method is meant to form the overall text
	 * command that specifies how and what text should
	 * be entered into a webelement.
	 * </p>
	 * <p>
	 * Overall, the methods linked together should
	 * form a sentence that shapes which methods are
	 * executed.
	 * </p>
	 * <p>
	 * Examples of the different types of sentences
	 * that can be formed are as follows:
	 * </p>
	 * <pre>
	 * {@code SHelper.get().enter()}
	 * </pre>
	 * @throws TestException
	 */
	public IEnter enter(Via via) throws TestException {
		IEnter enter = null;
		switch (via) {
			case SELENIUM:
				enter = new Enter();
				break;
			case JAVASCRIPT:
				enter = new JSEnter();
				break;
			default:
				throw new TestException("Please select an appropriate action type from the action type enum.");
		}

		return enter;
	}

	/**
	 * <p>
	 * This method is meant to form the overall text
	 * command that specifies how and what browser
	 * level actions should occur
	 * </p>
	 * <p>
	 * Overall, the methods linked together should
	 * form a sentence that shapes which methods are
	 * executed.
	 * </p>
	 * <p>
	 * Examples of the different types of sentences
	 * that can be formed are as follows:
	 * </p>
	 * <pre>
	 * {@code SHelper.get().browser()}
	 * </pre>
	 * @throws TestException
	 */
	public IBrowser browser() {
		IBrowser browser = null;

		browser = new Browser();

		return browser;
	}

	/**
	 * <p>
	 * This method is meant to form the overall text
	 * command that specifies how and what element
	 * level actions should occur
	 * </p>
	 * <p>
	 * Overall, the methods linked together should
	 * form a sentence that shapes which methods are
	 * executed.
	 * </p>
	 * <p>
	 * Examples of the different types of sentences
	 * that can be formed are as follows:
	 * </p>
	 * <pre>
	 * {@code SHelper.get().element()}
	 * </pre>
	 * @throws TestException
	 */
	public IElement element() {
		IElement element = null;

		element = new Element();

		return element;
	}

	/**
	 * <p>
	 * This method is meant to form the overall text
	 * command that specifies how and what page level
	 * actions should occur
	 * </p>
	 * <p>
	 * Overall, the methods linked together should
	 * form a sentence that shapes which methods are
	 * executed.
	 * </p>
	 * <p>
	 * Examples of the different types of sentences
	 * that can be formed are as follows:
	 * </p>
	 * <pre>
	 * {@code SHelper.get().page()}
	 * </pre>
	 * @throws TestException
	 */
	public IPage page() {
		IPage page = null;

		page = new Page();

		return page;
	}

	/**
	 * <p>
	 * This method is meant to form the overall text
	 * command that specifies what condition to wait
	 * on for which element.
	 * </p>
	 * <p>
	 * Overall, the methods linked together should
	 * form a sentence that shapes which methods are
	 * executed.
	 * </p>
	 * <p>
	 * Examples of the different types of sentences
	 * that can be formed are as follows:
	 * </p>
	 * <pre>
	 * {@code SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT)
	 * SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT)
	 * SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT)
	 * SHelper.get().waitMethod(Wait.ELEMENT_TEXT_NOT_TO_BE_PRESENT)
	 * SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE)
	 * SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT)
	 * SHelper.get().waitMethod(Wait.ATTRIBUTE_NOT_TO_BE_PRESENT)
	 * SHelper.get().waitMethod(Wait.ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT)
	 * SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT)
	 * SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS)}
	 * </pre>
	 * @throws TestException
	 */
	public IWait waitMethod(Wait condition, WaitBuilder builder) throws TestException {
		IWait iWait = null;
		switch (condition) {
			case PRESENCE_OF_ELEMENT:
				iWait = new PresentElement(builder);
				break;
			case PRESENCE_OF_ELEMENT_TEXT:
				iWait = new PresentElementText(builder);
				break;
			case ELEMENT_NOT_TO_BE_PRESENT:
				iWait = new NonPresentElement(builder);
				break;
			case ELEMENT_TEXT_NOT_TO_BE_PRESENT:
				iWait = new NonPresentElementText(builder);
				break;
			case PRESENCE_OF_ATTRIBUTE:
				iWait = new PresentAttribute(builder);
				break;
			case PRESENCE_OF_ATTRIBUTE_TEXT:
				iWait = new PresentAttributeText(builder);
				break;
			case ATTRIBUTE_NOT_TO_BE_PRESENT:
				iWait = new NonPresentAttribute(builder);
				break;
			case ATTRIBUTE_TEXT_NOT_TO_BE_PRESENT:
				iWait = new NonPresentAttributeText(builder);
				break;
			case CLICKABILITY_OF_ELEMENT:
				iWait = new ClickableElement(builder);
				break;
			case COUNT_OF_ELEMENTS:
				iWait = new ElementCount(builder);
				break;
			default:
				throw new TestException("Please select an appropriate action type from the action type enum.");
		}

		return iWait;
	}

	/**
	 * <p>
	 * This method is meant to form the overall text
	 * command that specifies how and what
	 * miscellaneous actions should occur
	 * </p>
	 * <p>
	 * Overall, the methods linked together should
	 * form a sentence that shapes which methods are
	 * executed.
	 * </p>
	 * <p>
	 * Examples of the different types of sentences
	 * that can be formed are as follows:
	 * </p>
	 * <pre>
	 * {@code SHelper.get().actions()}
	 * </pre>
	 * 
	 * @return IActions
	 * @throws TestException
	 */
	public IActions actions() {
		IActions sActions = null;

		sActions = new ElementActions();

		return sActions;
	}

}