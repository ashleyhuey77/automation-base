package com.warnermedia.config.driver;

import java.util.logging.Level;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import com.warnermedia.config.settings.LocalTest;
import com.warnermedia.utils.CookieManager;
import com.warnermedia.utils.Log;

public class WebDriverListener implements IInvokedMethodListener {

	public static int testNumber = 0;
	public static ThreadLocal<Boolean> hasBeforeScenarioAlreadyBeenExecuted = new ThreadLocal<>();

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		try {
			Log.set();
			LocalTest.initializeSettings();
    			if (method.toString().toLowerCase().contains("beforescenario")) {
    				if (hasBeforeScenarioAlreadyBeenExecuted.get() == null) {
    					hasBeforeScenarioAlreadyBeenExecuted.set(false);
    				}
    				if (!hasBeforeScenarioAlreadyBeenExecuted.get()) {
    					WebDriver driver = null;
    					driver = DriverFacade
    							.getDriver(Drivers.valueOf(LocalTest.getEnvironment().getBrowser().toUpperCase().trim()));
    					LocalDriver.setDriver(driver);
    					testNumber++;
    					Log.get().log(Level.INFO, "Now executing test number: {0}", testNumber);
    					hasBeforeScenarioAlreadyBeenExecuted.set(true);
    				}
    			}
			} catch (Exception e) {

		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.toString().toLowerCase().contains("afterscenario")) {
			WebDriver driver = LocalDriver.getDriver();
				try {
					hasBeforeScenarioAlreadyBeenExecuted.set(false);
					CookieManager.setCookies(null);
					driver.quit();
				} catch (Exception e) {
					if (driver != null) {
						driver.quit();
					}
				} finally {
					if (driver != null) {
						driver.quit();
					}
				}
		}
	}
}
