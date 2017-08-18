package seleniumHelper.methods;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import seleniumHelper.abstracts.Commands;
import seleniumHelper.interfaces.IEnter;

public class Enter extends Commands implements IEnter {

    @Override
    public void textInto(String selectorString, String by, String text) throws Exception {
        try {
            getElement(selectorString, by).sendKeys(text);
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void textInto(WebElement element, String text) throws Exception {
        try {
            element.sendKeys(text);
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void textInto(String selectorString, String by, Keys key) throws Exception {
        try {
            getElement(selectorString, by).sendKeys(key);
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void textInto(WebElement element, Keys key) {
        try {
            element.sendKeys(key);
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void clear(String selectorString, String by) throws Exception {
        try {
            getElement(selectorString, by).clear();
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void clear(WebElement element) throws Exception {
        try {
            element.clear();
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

}