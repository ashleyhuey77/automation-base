package seleniumHelper.methods;

import java.util.List;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import commonClasses.sharedUtils.managers.SHelper;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.enums.WaitFor;
import seleniumHelper.interfaces.IClick;

public class Click extends Commands implements IClick {

    @Override
    public void on(String selectorString, String by) throws Exception {
        try {
            SHelper.get().waitMethod(WaitFor.CLICKABILITY_OF_ELEMENT).waitOn(selectorString, by, 40);
            getElement(selectorString, by).click();
        } catch (WebDriverException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void on(WebElement element) throws Exception {
        try {
            SHelper.get().waitMethod(WaitFor.CLICKABILITY_OF_ELEMENT).waitOn(element, 40);
            element.click();
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void on(String selectorString, String by, String index) throws Exception {
        try {
            SHelper.get().waitMethod(WaitFor.CLICKABILITY_OF_ELEMENT).waitOn(selectorString, by, 40);
            List < WebElement > element = getElements(selectorString, by);
            element.get(Integer.parseInt(index)).click();
        } catch (WebDriverException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void on(String selectorString, String by, int index) throws Exception {
        try {
            SHelper.get().waitMethod(WaitFor.CLICKABILITY_OF_ELEMENT).waitOn(selectorString, by, 40);
            List < WebElement > element = getElements(selectorString, by);
            element.get(index).click();
        } catch (WebDriverException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

}