package com.selenium.click;

import com.config.TestException;
import com.config.setup.browser.LocalDriver;
import com.selenium.TestElement;
import com.selenium.shared.Commands;
import com.utils.observers.StateManager;
import com.utils.ex.ErrorCode;
import com.utils.ex.SeleniumException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JSClick extends Commands implements IClick {

    /**
     * <summary> Method to click an element via
     * javascript based on webelement type </summary>
     *
     * @param index the index used to identify a
     *              particular element in a list of
     *              elements tat contain more than one
     *              element with a similar selector
     * @return void
     */
    private void clickViaJavascriptElementType(TestElement element, String index) throws Exception {
        String click = "].click();";

        switch (element.by().value()) {
            case ID:
                ((JavascriptExecutor) LocalDriver.getDriver())
                        .executeScript("document.getElementById(\"" + element.locator().value() + "\").click();");
                break;
            case CLASS_NAME:
                ((JavascriptExecutor) LocalDriver.getDriver())
                        .executeScript("document.getElementsByClassName(\"" + element.locator().value() + "\")[" + index + click);
                break;
            case NAME:
                ((JavascriptExecutor) LocalDriver.getDriver())
                        .executeScript("document.getElementsByName(\"" + element.locator().value() + "\")[" + index + click);
                break;
            case TAG_NAME:
                ((JavascriptExecutor) LocalDriver.getDriver())
                        .executeScript("document.getElementsByTagName(\"" + element.locator().value() + "\")[" + index + click);
                break;
            case CSS:
                ((JavascriptExecutor) LocalDriver.getDriver())
                        .executeScript("document.querySelector(\"" + element.locator().value() + "\").click();");
                break;
            default:
                break;
        }
    }

    @Override
    public void on(TestElement element) throws TestException {
        if (StateManager.getState() != null) {
            StateManager.getState().checkState();
        }
        try {
            clickViaJavascriptElementType(element, "0");
        } catch (Exception e) {
            throw new SeleniumException("A javascript error occurred when trying to click on element " + element + " via javascript.", ErrorCode.CLICK);
        }
    }

    @Override
    public void on(WebElement element) throws TestException {
        if (StateManager.getState() != null) {
            StateManager.getState().checkState();
        }
        try {
            ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            throw new SeleniumException("A javascript error occurred when trying to click on the element via javascript.", ErrorCode.CLICK);
        }
    }

    @Override
    public void on(TestElement element, String index) throws TestException {
        if (StateManager.getState() != null) {
            StateManager.getState().checkState();
        }
        try {
            clickViaJavascriptElementType(element, index);
        } catch (Exception e) {
            throw new SeleniumException("A javascript error occurred when trying to click on element " + element.locator().value() + " via javascript.", ErrorCode.CLICK);
        }
    }

    @Override
    public void on(TestElement element, int index) throws TestException {
        if (StateManager.getState() != null) {
            StateManager.getState().checkState();
        }
        try {
            clickViaJavascriptElementType(element, Integer.toString(index));
        } catch (Exception e) {
            throw new SeleniumException("A javascript error occurred when trying to click on element " + element.locator().value() + " via javascript.", ErrorCode.CLICK);
        }
    }

}