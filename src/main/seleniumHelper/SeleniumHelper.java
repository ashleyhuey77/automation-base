package seleniumHelper;

import commonClasses.sharedUtils.managers.SHelper;
import seleniumHelper.enums.*;
import seleniumHelper.interfaces.*;
import seleniumHelper.methods.*;

public class SeleniumHelper {

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

    public IEnter enter() {
        IEnter enter = null;

        enter = new Enter();

        return enter;
    }

    public IBrowser browser() {
        IBrowser browser = null;

        browser = new Browser();

        return browser;
    }

    public IElement element() {
        IElement element = null;

        element = new Element();

        return element;
    }

    public IPage page() {
        IPage page = null;

        page = new Page();

        return page;
    }

    public IWait waitMethod(WaitFor condition) throws Exception {
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

    public IActions actions() {
        IActions sActions = null;

        sActions = new ElementActions();

        return sActions;
    }

    protected void test() throws Exception {
        SHelper.get().click(Via.JAVASCRIPT).on("", "");
        SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom("", "");
        SHelper.get().enter().textInto("", "", "");
        SHelper.get().browser().switchTo(BrowserObject.WINDOW);
        SHelper.get().element().isDisplayed("", "", 30);
        SHelper.get().page().scrollTo(Location.TOP_OF_PAGE);
        SHelper.get().waitMethod(WaitFor.ELEMENT_OR_VALUE_NOT_TO_BE_PRESENT).waitOn("", "", Condition.CONTAINS, "", 40);

    }

}