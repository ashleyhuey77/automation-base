package seleniumHelper.methods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.builders.WaitBuilder;
import seleniumHelper.enums.Wait;
import seleniumHelper.interfaces.IClick;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;

public class JQClick extends Commands implements IClick {

	@Override
    public void on(Locator locator, By by) throws Exception {
        String webElement = locator.value();
        try {
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(locator, by);
            ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "').click();");
        } catch (Exception ex) {
            try {
                if (locator.value().contains("'")) {
                    webElement = locator.value().replace("'", "");
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
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
            ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("arguments[0].click();", element);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void on(Locator locator, By by, String index) throws Exception {
        try {
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(locator, by);
            String webElement = null;
            if (locator.value().contains("'")) {
                webElement = locator.value().replace("'", "");
            } else {
                webElement = locator.value();
            }
            ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "')[" + index + "].click();");
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void on(Locator locator, By by, int index) throws Exception {
        try {
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(locator, by);
            String webElement = locator.value();
            if (locator.value().contains("'")) {
                webElement = locator.value().replace("'", "");
            } 
            
            ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "')[" + Integer.toString(index) + "].click();");
        } catch (Exception ex) {
            throw ex;
        }
    }

}