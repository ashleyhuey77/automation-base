package seleniumHelper.methods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import commonClasses.sharedUtils.managers.LocalDriver;
import commonClasses.sharedUtils.managers.SHelper;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.enums.Wait;
import seleniumHelper.interfaces.IClick;

public class JQClick extends Commands implements IClick {

    @Override
    public void on(String selectorString, String by) throws Exception {
        String webElement = null;
        try {
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT).on(selectorString, by, 40);
            ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "').click();");
        } catch (Exception ex) {
            try {
                if (selectorString.contains("'")) {
                    webElement = selectorString.replace("'", "");
                } else {
                    webElement = selectorString;
                }
                ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "').click();");

            } catch (Exception e) {
                throw ex;
            }
        }
    }

    @Override
    public void on(WebElement element) throws Exception {
        try {
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT).on(element, 40);
            ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$arguments[0].click();", element);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void on(String selectorString, String by, String index) throws Exception {
        try {
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT).on(selectorString, by, 40);
            String webElement = null;
            if (selectorString.contains("'")) {
                webElement = selectorString.replace("'", "");
            } else {
                webElement = selectorString;
            }
            ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "')[" + index + "].click();");
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void on(String selectorString, String by, int index) throws Exception {
        try {
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT).on(selectorString, by, 40);
            String webElement = null;
            if (selectorString.contains("'")) {
                webElement = selectorString.replace("'", "");
            } else {
                webElement = selectorString;
            }
            ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "')[" + Integer.toString(index) + "].click();");
        } catch (Exception ex) {
            throw ex;
        }
    }

}