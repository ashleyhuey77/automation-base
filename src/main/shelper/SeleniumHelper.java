package shelper;

import log.TestException;
import shelper.builders.WaitBuilder;
import shelper.enums.*;
import shelper.interfaces.*;
import shelper.methods.*;

public class SeleniumHelper {

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
		IClick click = null;
		switch (type) {
			case SELENIUM:
				click = new Click();
				break;
			case JAVASCRIPT:
				click = new JSClick();
				break;
			case JQUERY:
				click = new JQClick();
				break;
			case ALTERNATE:
				click = new RightClick();
				break;
			default:
				throw new TestException("Please select an appropriate action type from the action type enum.");
		}

		return click;
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
				enter = new JavascriptEnter();
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