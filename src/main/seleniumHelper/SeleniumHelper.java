package seleniumHelper;

import seleniumHelper.enums.*;
import seleniumHelper.interfaces.*;
import seleniumHelper.methods.*;

public class SeleniumHelper {

	/**
	 * <p>This method is meant to form the overall text command that specifies
	 * what should be done with a particular webelement text.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().text(Variable.ATTRIBUTE, VIA.SELENIUM)}</br>
	 * {@code SHelper.get().text(Variable.ELEMENT, VIA.SELENIUM)} </br>
	 * {@code SHelper.get().text(Variable.ELEMENT, VIA.JAVASCRIPT)} </br>
	 * </p>
	 * @param variable - the variable where the text lives. Is the text in the attribute
	 * of an element or is the text in an element itself.
	 * @param actionType - The type of action that should be executed on the element. Should 
	 * it get text via javascript means or will selenium suffice.
	 * @return IText
	 * @throws Exception
	 */
    public IText text(Variable variable, Via actionType) throws Exception {
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
                        throw new Exception("Please select a valid Text type from the TextType enum.");
                }
                break;
            default:
                throw new Exception("Please select a valid Action type from the ActionType enum.");
        }
        return text;
    }

	/**
	 * <p>This method is meant to form the overall text command that specifies
	 * how and which element should be clicked.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().click(VIA.SELENIUM)}</br>
	 * {@code SHelper.get().click(VIA.JQUERY)} </br>
	 * {@code SHelper.get().click(VIA.JAVASCRIPT)} </br>
	 * </p>
	 * @param type - The type of click action that should be executed on the element. Should 
	 * it click via javascript means or will selenium suffice.
	 * @return IClick
	 * @throws Exception
	 */
    public IClick click(Via type) throws Exception {
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
            default:
                throw new Exception("Please select an appropriate action type from the action type enum.");
        }

        return click;
    }

	/**
	 * <p>This method is meant to form the overall text command that specifies
	 * how and what text should be entered into a webelement.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().enter()}</br>
	 * </p>
	 * @return IEnter
	 * @throws Exception
	 */
    public IEnter enter() {
        IEnter enter = null;

        enter = new Enter();

        return enter;
    }

	/**
	 * <p>This method is meant to form the overall text command that specifies
	 * how and what browser level actions should occur</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().browser()}</br>
	 * </p>
	 * @return IBrowser
	 * @throws Exception
	 */
    public IBrowser browser() {
        IBrowser browser = null;

        browser = new Browser();

        return browser;
    }

	/**
	 * <p>This method is meant to form the overall text command that specifies
	 * how and what element level actions should occur</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().element()}</br>
	 * </p>
	 * @return IElement
	 * @throws Exception
	 */
    public IElement element() {
        IElement element = null;

        element = new Element();

        return element;
    }

	/**
	 * <p>This method is meant to form the overall text command that specifies
	 * how and what page level actions should occur</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().page()}</br>
	 * </p>
	 * @return IPage
	 * @throws Exception
	 */
    public IPage page() {
        IPage page = null;

        page = new Page();

        return page;
    }

	/**
	 * <p>This method is meant to form the overall text command that specifies
	 * what condition to wait on for which element.</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_OR_VALUE)}</br>
	 * {@code SHelper.get().waitMethod(Wait.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT)} </br>
	 * {@code SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_OR_VALUE)} </br>
	 * {@code SHelper.get().waitMethod(Wait.ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT)} </br>
	 * {@code SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT)} </br>
	 * {@code SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS)} </br>
	 * </p>
	 * @param condition - the wait condition that determines which selenium
	 * wait command is executed.
	 * @return IWait
	 * @throws Exception
	 */
    public IWait waitMethod(Wait condition) throws Exception {
        IWait iWait = null;
        switch (condition) {
            case PRESENCE_OF_ELEMENT_OR_VALUE:
                iWait = new PresentElement();
                break;
            case ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT:
                iWait = new NonPresentElement();
                break;
            case PRESENCE_OF_ATTRIBUTE_OR_VALUE:
                iWait = new PresentAttribute();
                break;
            case ATTRIBUTE_OR_VALUE_NOT_TO_BE_PRESENT:
                iWait = new NonPresentAttribute();
                break;
            case CLICKABILITY_OF_ELEMENT:
                iWait = new ClickableElement();
                break;
            case COUNT_OF_ELEMENTS:
                iWait = new ElementCount();
                break;
            default:
                throw new Exception("Please select an appropriate action type from the action type enum.");
        }

        return iWait;
    }

	/**
	 * <p>This method is meant to form the overall text command that specifies
	 * how and what miscellaneous actions should occur</p>
	 * <p>Overall, the methods linked together should form a sentence that
	 * shapes which methods are executed.<p>
	 * <p>Examples of the different types of sentences that can be formed are
	 * as follows: </p>
	 * {@code SHelper.get().actions()}</br>
	 * </p>
	 * @return IActions
	 * @throws Exception
	 */
    public IActions actions() {
        IActions sActions = null;

        sActions = new ElementActions();

        return sActions;
    }

}