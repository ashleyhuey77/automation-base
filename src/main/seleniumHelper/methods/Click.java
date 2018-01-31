package seleniumHelper.methods;

import java.util.List;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import common.utils.managers.SHelper;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.builders.WaitBuilder;
import seleniumHelper.enums.Wait;
import seleniumHelper.interfaces.IClick;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;

public class Click extends Commands implements IClick {

	@Override
    public void on(Locator locator, By by) throws Exception {
        try {
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(locator, by);
            getElement(locator, by).click();
        } catch (WebDriverException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void on(WebElement element) throws Exception {
        try {
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
            element.click();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void on(Locator locator, By by, String index) throws Exception {
        try {
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(locator, by);
            List < WebElement > element = getElements(locator, by);
            element.get(Integer.parseInt(index)).click();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void on(Locator locator, By by, int index) throws Exception {
        try {
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20));
            List < WebElement > element = getElements(locator, by);
            element.get(index).click();
        } catch (Exception ex) {
            throw ex;
        }
    }

}