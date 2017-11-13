package seleniumHelper.methods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import commonClasses.sharedUtils.managers.LocalDriver;
import commonClasses.sharedUtils.managers.SHelper;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.enums.Wait;
import seleniumHelper.interfaces.IClick;

public class JSClick extends Commands implements IClick {

    /** 
     * <summary>
     	Method to click an element via javascript based on
     	webelement type
     	</summary>
     * @param selectorString the webelement selector string necessary for the webelement to be found
     * @param index the index used to identify a particular element in a list of elements tat contain
     	 			more than one element with a similar selector
     * @param webelements the locator type used to identify webelements on the page. Based on the values in the
     						LocatorTypes enum.
     	@return void
     */
    private void clickViaJavascriptElementType(String selectorString, String index, LocatorTypes webelements) {
        try {
            switch (webelements) {
                case ID:
                    ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.getElementById('" + selectorString + "').click();");
                    break;
                case CLASSNAME:
                    ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.getElementsByClassName('" + selectorString + "')[" + index + "].click();");
                    break;
                case NAME:
                    ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.getElementsByName('" + selectorString + "')[" + index + "].click();");
                    break;
                case TAGNAME:
                    ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.getElementsByTagName('" + selectorString + "')[" + index + "].click();");
                    break;
                case CSSSELECTOR:
                    ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.querySelector('" + selectorString + "').click();");
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void on(String selectorString, String by) throws Exception {
        try {
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT).on(selectorString, by, 20);
            clickViaJavascriptElementType(selectorString, "0", (by.toLowerCase().contains(id) ? LocatorTypes.ID :
                (by.toLowerCase().contains(cssSelector)) ? LocatorTypes.CSSSELECTOR :
                (by.toLowerCase().contains(className)) ? LocatorTypes.CLASSNAME :
                (by.toLowerCase().contains(tagName)) ? LocatorTypes.TAGNAME :
                (by.toLowerCase().contains(name)) ? LocatorTypes.NAME :
                LocatorTypes.ID));
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void on(WebElement element) throws Exception {
        try {
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT).on(element, 20);
            ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("arguments[0].click();", element);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void on(String selectorString, String by, String index) throws Exception {
        try {
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT).on(selectorString, by, 20);
            clickViaJavascriptElementType(selectorString, index, (by.toLowerCase().contains(id) ? LocatorTypes.ID :
                (by.toLowerCase().contains(cssSelector)) ? LocatorTypes.CSSSELECTOR :
                (by.toLowerCase().contains(className)) ? LocatorTypes.CLASSNAME :
                (by.toLowerCase().contains(tagName)) ? LocatorTypes.TAGNAME :
                (by.toLowerCase().contains(name)) ? LocatorTypes.NAME :
                LocatorTypes.ID));
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void on(String selectorString, String by, int index) throws Exception {
        try {
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT).on(selectorString, by, 20);
            clickViaJavascriptElementType(selectorString, Integer.toString(index), (by.toLowerCase().contains(id) ? LocatorTypes.ID :
                (by.toLowerCase().contains(cssSelector)) ? LocatorTypes.CSSSELECTOR :
                (by.toLowerCase().contains(className)) ? LocatorTypes.CLASSNAME :
                (by.toLowerCase().contains(tagName)) ? LocatorTypes.TAGNAME :
                (by.toLowerCase().contains(name)) ? LocatorTypes.NAME :
                LocatorTypes.ID));
        } catch (Exception ex) {
            throw ex;
        }
    }

}