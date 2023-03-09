package com.utils.devtools;

import com.config.setup.browser.LocalDriver;
import com.config.setup.report.LocalReport;
import com.utils.devtools.filter.MessageFilter;
import com.utils.devtools.filter.FilterManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
public enum JSTool {

    INSTANCE;

    private List<JavascriptException> jsExceptionsList;
    private String className;

    public void start(String className) {
        this.className = className;
        jsExceptionsList = new ArrayList<>();
        Consumer<JavascriptException> addEntry = jsExceptionsList::add;
        LocalDevTools.getDevTools().createSessionIfThereIsNotOne();
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
            LocalReport.getReport().reportDoneEvent(log, "No javascript errors were found on => " + className);
        }
        FilterManager fm = new FilterManager();
        fm.addFilter(new MessageFilter(className));
        for (JavascriptException jsException : jsExceptionsList) {
            fm.filterRequest(jsException);
        }
    }



}
