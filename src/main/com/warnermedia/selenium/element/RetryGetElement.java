package com.warnermedia.selenium.element;

import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.click.*;
import com.warnermedia.selenium.shared.Commands;
import com.warnermedia.selenium.shared.Via;
import com.warnermedia.utils.ex.ErrorCode;
import com.warnermedia.utils.ex.SeleniumException;
import com.warnermedia.utils.retry.TestOperation;
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
