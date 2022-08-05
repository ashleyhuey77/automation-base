package com.warnermedia.config.driver;

import com.warnermedia.config.TestException;
import com.warnermedia.config.settings.LocalTest;
import com.warnermedia.utils.retry.TestOperation;
import com.warnermedia.wdm.utils.OS;
import org.openqa.selenium.WebDriver;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;

public class LaunchBrowser implements TestOperation<WebDriver> {
    private final Deque<TestException> errors;

    public LaunchBrowser(TestException... errors) {
        this.errors = new ArrayDeque<>(Collections.unmodifiableList(Arrays.asList(errors)));
    }

    @Override
    public WebDriver perform() throws TestException {
        if (!this.errors.isEmpty()) {
            throw this.errors.pop();
        }
        return DriverFacade
                .getDriver(Drivers.valueOf(LocalTest.getEnvironment().getBrowser().toUpperCase().trim()));
    }
}
