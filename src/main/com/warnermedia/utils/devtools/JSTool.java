package com.warnermedia.utils.devtools;

import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.utils.devtools.filter.FilterManager;
import com.warnermedia.utils.devtools.filter.MessageFilter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public enum JSTool {

    INSTANCE;

    private List<JavascriptException> jsExceptionsList;

    public void start() {
        jsExceptionsList = new ArrayList<>();
        Consumer<JavascriptException> addEntry = jsExceptionsList::add;
        LocalDevTools.getDevTools().getDomains().events().addJavascriptExceptionListener(addEntry);
    }

    public void parse() throws Exception {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(LocalDriver.getDriver())
                .withTimeout(Duration.ofSeconds(1))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(WebDriverException.class);

        try {
            wait.until(driver -> {
                Boolean result = false;
                if (jsExceptionsList.size() > 0) {
                    result = true;
                }
                return result;
            });
        } catch (Exception e) {

        }
        FilterManager fm = new FilterManager();
        fm.addFilter(new MessageFilter());
        for (JavascriptException jsException : jsExceptionsList) {
            fm.filterRequest(jsException);
        }
    }



}
