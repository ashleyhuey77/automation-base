package seleniumHelper.methods;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.interfaces.IEnter;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;

public class Enter extends Commands implements IEnter {

	@Override
    public void textInto(Locator locator, By by, String text) throws Exception {
        try {
            getElement(locator, by).sendKeys(text);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void textInto(WebElement element, String text) throws Exception {
        try {
            element.sendKeys(text);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void textInto(Locator locator, By by, Keys key) throws Exception {
        try {
            getElement(locator, by).sendKeys(key);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void textInto(WebElement element, Keys key) throws Exception {
        try {
            element.sendKeys(key);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void clear(Locator locator, By by) throws Exception {
        try {
            getElement(locator, by).clear();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void clear(WebElement element) throws Exception {
        try {
            element.clear();
        } catch (Exception ex) {
            throw ex;
        }
    }

}