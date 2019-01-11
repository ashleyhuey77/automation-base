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
	 * <p>
	 * <p>
	 * Examples of the different types of sentences
	 * that can be formed are as follows:
	 * </p>
	 * {@code SHelper.get().text(Variable.ATTRIBUTE, VIA.SELENIUM)}</br>
	 * {@code SHelper.get().text(Variable.ELEMENT, VIA.SELENIUM)}
	 * </br>
	 * {@code SHelper.get().text(Variable.ELEMENT, VIA.JAVASCRIPT)}
	 * </br>
	 * </p>
	 * 
	 * @param variable
	 *            - the variable where the text lives.
	 *            Is the text in the attribute of an
	 *            element or is the text in an element
	 *            itself.
	 * @param actionType
	 *            - The type of action that should be
	 *            executed on the element. Should it
	 *            get text via javascript means or
	 *            will selenium suffice.
	 * @return IText
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
	 * <p>
	 * <p>
	 * Examples of the different types of sentences
	 * that can be formed are as follows:
	 * </p>
	 * {@code SHelper.get().click(VIA.SELENIUM)}</br>
	 * {@code SHelper.get().click(VIA.JQUERY)} </br>
	 * {@code SHelper.get().click(VIA.JAVASCRIPT)}
	 * </br>
	 * </p>
	 * 
	 * @param type
	 *            - The type of click action that
	 *            should be executed on the element.
	 *            Should it click via javascript means
	 *            or will selenium suffice.
	 * @return IClick
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
	 * <p>
	 * <p>
	 * Examples of the different types of sentences
	 * that can be formed are as follows:
	 * </p>
	 * {@code SHelper.get().enter()}</br>
	 * </p>
	 * 
	 * @return IEnter
	 * @throws TestException
	 */
	public IEnter enter() {
		IEnter enter = null;

		enter = new Enter();

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
	 * <p>
	 * <p>
	 * Examples of the different types of sentences
	 * that can be formed are as follows:
	 * </p>
	 * {@code SHelper.get().browser()}</br>
	 * </p>
	 * 
	 * @return IBrowser
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
	 * <p>
	 * <p>
	 * Examples of the different types of sentences
	 * that can be formed are as follows:
	 * </p>
	 * {@code SHelper.get().element()}</br>
	 * </p>
	 * 
	 * @return IElement
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
	 * <p>
	 * <p>
	 * Examples of the different types of sentences
	 * that can be formed are as follows:
	 * </p>
	 * {@code SHelper.get().page()}</br>
	 * </p>
	 * 
	 * @return IPage
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
	 * <p>
	 * <p>
	 * Examples of the different types of sentences
	 * that can be formed are as follows:
	 * </p>
	 * {@code SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE)}</br>
	 * {@code SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT)}
	 * </br>
	 * {@code SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE)}
	 * </br>
	 * {@code SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT)}
	 * </br>
	 * {@code SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT)}
	 * </br>
	 * {@code SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS)}
	 * </br>
	 * </p>
	 * 
	 * @param condition
	 *            - the wait condition that determines
	 *            which selenium wait command is
	 *            executed.
	 * @return IWait
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
	 * <p>
	 * <p>
	 * Examples of the different types of sentences
	 * that can be formed are as follows:
	 * </p>
	 * {@code SHelper.get().actions()}</br>
	 * </p>
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