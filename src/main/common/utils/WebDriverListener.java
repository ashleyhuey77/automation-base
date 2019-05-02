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
	public static Boolean hasBeforeScenarioAlreadyBeenExecuted = false;

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		try {
			Log.set();
			LocalTest.initializeSettings();
			if (method.toString().toLowerCase().contains("beforescenario")) {
				if (!hasBeforeScenarioAlreadyBeenExecuted) {
					WebDriver driver = null;
					driver = HelperFacade
							.getDriver(Drivers.valueOf(LocalTest.getEnvironment().getBrowser().toUpperCase().trim()));
					LocalDriver.setDriver(driver);
					testNumber++;
					Log.get().log(Level.INFO, "Now executing test number: {0}", testNumber);
					hasBeforeScenarioAlreadyBeenExecuted = true;
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
					hasBeforeScenarioAlreadyBeenExecuted = false;
					driver.quit();
				} catch (Exception e) {
					if (driver != null) {
						driver.quit();
					}
				}
		}
	}
}
