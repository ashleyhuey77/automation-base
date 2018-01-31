package seleniumHelper.methods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import com.google.errorprone.annotations.DoNotCall;
import common.utils.TestUtils;
import common.utils.managers.LocalDriver;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.interfaces.IText;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;

public class JSPageText extends Commands implements IText {

	private String getValueViaJavascriptIndexNoIndex(String javascriptStartTxt, String locator, String index, Boolean requiresIndex) {
        String textBoxText = null;
        try {
            if (requiresIndex) {
                textBoxText = (String)((JavascriptExecutor) LocalDriver.getDriver()).executeScript(javascriptStartTxt + locator + "')[" + index + "].value");
            } else {
                textBoxText = (String)((JavascriptExecutor) LocalDriver.getDriver()).executeScript(javascriptStartTxt + locator + "').value");
            }
        } catch (Exception ex) {
            throw ex;
        }
        return textBoxText;
    }

    /** <summary>
    	Method to get text from a textbox via javascript based on
    	webelement type
    	</summary>
    	* @param locator the webelement selector string necessary for the webelement to be found
    	* @param index the index used to identify a particular element in a list of elements tat contain
    	* 				more than one element with a similar selector
    	* @param requiresIndex boolean value to toggle whether the index is necessary. True uses an index and index
    	* 				       cannot be blank or null. False does not use an index and index can be blank or null.
    	* @param webElements the locator type used to identify webelements on the page. Based on the values in the
    	* 					LocatorTypes enum.
    	* @return
    */
    private String getTxtBoxValueViaJavascriptScript(String locator, String index, Boolean requiresIndex, How webElements) {
        String textBoxText = null;
        try {
            switch (webElements) {
                case ID:
                    textBoxText = getValueViaJavascriptIndexNoIndex("return document.getElementById('", locator, index, requiresIndex);
                    break;
                case CLASS_NAME:
                    textBoxText = getValueViaJavascriptIndexNoIndex("return document.getElementsByClassName('", locator, index, requiresIndex);
                    break;
                case NAME:
                    textBoxText = getValueViaJavascriptIndexNoIndex("return document.getElementsByName('", locator, index, requiresIndex);
                    break;
                case TAG_NAME:
                    textBoxText = getValueViaJavascriptIndexNoIndex("return document.getElementsByTagName('", locator, index, requiresIndex);
                    break;
                case CSS:
                    textBoxText = getValueViaJavascriptIndexNoIndex("return document.querySelector('", locator, index, requiresIndex);
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return textBoxText;
    }


    @Override
    public String getFrom(Locator locator, By by, String...attribute) throws Exception {
        try {
            String webElement = null;
            if (locator.value().contains("'")) {
                webElement = locator.value().replace("'", "");
            } else {
                webElement = locator.value();
            }
            Boolean requiresIndex = false;
            int count = attribute.length;
            String attr = null;
            if (count > 0) {
                if (!TestUtils.isNullOrBlank(attribute[0])) {
                    requiresIndex = true;
                    attr = attribute[0];
                } else {
                    attr = "";
                }
            } else {
                attr = "";
            }

            return getTxtBoxValueViaJavascriptScript(webElement, attr, requiresIndex, by.value());
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public String getFrom(WebElement element, String...attribute) throws Exception {
        try {
            return (String)((JavascriptExecutor) LocalDriver.getDriver()).executeScript("return arguments[0].value", element);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    @DoNotCall
    public Boolean isDisplayed(Locator locator, By by, String expectedText, String...attribute) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @DoNotCall
    public Boolean isDisplayed(WebElement element, String expectedText, String...attribute) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}