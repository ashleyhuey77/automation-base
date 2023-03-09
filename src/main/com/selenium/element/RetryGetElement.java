package com.selenium.element;

import com.config.TestException;
import com.config.setup.browser.LocalDriver;
import com.selenium.TestElement;
import com.utils.ex.ErrorCode;
import com.utils.ex.SeleniumException;
import com.utils.retry.TestOperation;
import com.selenium.shared.Commands;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class RetryGetElement extends Commands implements TestOperation<WebElement> {

    private final TestElement el;
    private final Deque<TestException> errors;

    public RetryGetElement(TestElement element, TestException... errors) {
        this.el = element;
        this.errors = new ArrayDeque<>(Collections.unmodifiableList(Arrays.asList(errors)));
    }
    @Override
    public WebElement perform() throws TestException {
        if (!this.errors.isEmpty()) {
            throw this.errors.pop();
        }
        try {
            try {
                WebDriver driver = LocalDriver.getDriver();
                WebElement element = driver.findElement(getByValueBasedOnUserInput(el));
                return element;
            } catch (Exception e) {
                throw new SeleniumException("Check that the element exists on the page. Unable to find element by " + el.by().value()
                        + " with locator " + el.locator().value(), ErrorCode.FIND_ELEMENT);
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
