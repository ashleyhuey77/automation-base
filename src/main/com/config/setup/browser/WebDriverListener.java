package com.config.setup.browser;

import java.util.Set;

import com.config.setup.app.LocalTest;
import com.utils.ConsoleDecoration;
import com.utils.devtools.CookieManager;
import lombok.extern.slf4j.Slf4j;
import org.testng.*;

@Slf4j
public class WebDriverListener extends TestListenerAdapter implements ITestListener, IInvokedMethodListener {

    public static int testNumber = 0;
    public static ThreadLocal<Boolean> hasBeforeScenarioAlreadyBeenExecuted = new ThreadLocal<>();

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        try {
                LocalTest.initializeSettings();
                LocalTest.setTestThreadCount(method.getTestMethod().getTestClass().getXmlTest().getThreadCount());
            if (method.toString().toLowerCase().contains("beforescenario")) {
                if (hasBeforeScenarioAlreadyBeenExecuted.get() == null) {
                    hasBeforeScenarioAlreadyBeenExecuted.set(false);
                }
                if (!hasBeforeScenarioAlreadyBeenExecuted.get()) {
                    LocalDriver.getDriver();
                    testNumber++;
                    log.info("{}{}Now executing test number: {}{}", ConsoleDecoration.CYAN_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, testNumber, ConsoleDecoration.RESET.value);
                    hasBeforeScenarioAlreadyBeenExecuted.set(true);
                }
            }
        } catch (Exception e) {
            log.error("{}{}Could not open the browser for the following reason: {}{}", ConsoleDecoration.RED_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, e.getMessage(), ConsoleDecoration.RESET.value);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
        for (ITestResult temp : failedTests) {
            ITestNGMethod method = temp.getMethod();
            if (context.getFailedTests().getResults(method).size() > 1) {
                failedTests.remove(temp);
            } else {
                if (context.getPassedTests().getResults(method).size() > 0) {
                    failedTests.remove(temp);
                }
            }
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.toString().toLowerCase().contains("afterscenario")) {
            try {
                hasBeforeScenarioAlreadyBeenExecuted.set(false);
                CookieManager.setCookies(null);
                LocalDriver.quitDriver();
            } catch (Exception e) {
                LocalDriver.quitDriver();
            } finally {
                LocalDriver.quitDriver();
            }
        }
    }
}
