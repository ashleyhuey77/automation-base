package com.warnermedia.config.driver;

import java.util.Set;
import java.util.logging.Level;

//import io.appium.java_client.AppiumDriver;
import com.warnermedia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import com.warnermedia.config.settings.LocalTest;
import com.warnermedia.utils.CookieManager;
import com.warnermedia.utils.Log;

public class WebDriverListener extends TestListenerAdapter implements ITestListener, IInvokedMethodListener {

    public static int testNumber = 0;
    public static ThreadLocal<Boolean> hasBeforeScenarioAlreadyBeenExecuted = new ThreadLocal<>();

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        try {
                Log.set();
                LocalTest.initializeSettings();
                LocalTest.setTestThreadCount(method.getTestMethod().getTestClass().getXmlTest().getThreadCount());
            if (method.toString().toLowerCase().contains("beforescenario")) {
                if (hasBeforeScenarioAlreadyBeenExecuted.get() == null) {
                    hasBeforeScenarioAlreadyBeenExecuted.set(false);
                }
                if (!hasBeforeScenarioAlreadyBeenExecuted.get()) {
                    LocalDriver.getDriver();
                    testNumber++;
                    Log.get().log(Level.INFO, "Now executing test number: {0}", testNumber);
                    hasBeforeScenarioAlreadyBeenExecuted.set(true);
                }
            }
        } catch (Exception e) {
            System.out.println("Could not open the browser for the following reason: " + e);
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
            WebDriver driver = null;
            try {
                hasBeforeScenarioAlreadyBeenExecuted.set(false);
                CookieManager.setCookies(null);
                LocalDriver.quitDriver();
            } catch (Exception e) {
                if (driver != null) {
                    LocalDriver.quitDriver();
                }
            } finally {
                if (driver != null) {
                    LocalDriver.quitDriver();
                }
            }
        }
    }
}
