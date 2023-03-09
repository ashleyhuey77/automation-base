package com.selenium.click;

import com.config.TestException;
import com.selenium.shared.Via;
import com.utils.retry.TestOperation;

import java.util.*;

public class RetryClick implements TestOperation<IClick> {

    private final Via via;
    private final Deque<TestException> errors;

    public RetryClick(Via via, TestException... errors) {
        this.via = via;
        this.errors = new ArrayDeque<>(Collections.unmodifiableList(Arrays.asList(errors)));
    }
    @Override
    public IClick perform() throws TestException {
        IClick click = null;
        if (!this.errors.isEmpty()) {
            throw this.errors.pop();
        }
        try {
            switch (via) {
                case SELENIUM:
                    click = new Click();
                    break;
                case JAVASCRIPT:
                    click = new JSClick();
                    break;
                case JQUERY:
                    click = new JQClick();
                    break;
                case ALTERNATE:
                    click = new RightClick();
                    break;
                default:
                    throw new TestException("Please select an appropriate action type from the action type enum.");
            }
        } catch (Exception e) {
            throw e;
        }
        return click;
    }
}
