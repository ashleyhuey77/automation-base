package common.utils;

import java.util.logging.Level;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import common.utils.enums.Drivers;
import common.utils.facades.HelperFacade;
import common.utils.managers.LocalDriver;
import common.utils.managers.LocalTest;
import log.Log;

public class WebDriverListener implements IInvokedMethodListener {

    public static int testNumber = 0;

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        try {
        	Log.set();
            LocalTest.initializeSettings();
        } catch (Exception e) {
        	Log.get().log(Level.SEVERE, e.getMessage(), e);
        }
        if (method.toString().toLowerCase().contains("beforescenario")) {
            WebDriver driver = null;
            try {
                driver = HelperFacade.getDriver(
                    Drivers.valueOf(LocalTest.getEnvironment().getBrowser().toUpperCase().trim()));
            } catch (Exception e) {
                Log.get().log(Level.SEVERE, e.getMessage(), e);
            }
            LocalDriver.setDriver(driver);
            testNumber++;
            Log.get().log(Level.INFO, "Now executing test number: {0}", testNumber);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        WebDriver driver = LocalDriver.getDriver();
        if (method.toString().toLowerCase().contains("afterscenario")) {
            try {
                driver.quit();
            } catch (Exception e) {
                driver.quit();
                throw e;
            } finally {
                driver.quit();
            }
        }
    }
}
