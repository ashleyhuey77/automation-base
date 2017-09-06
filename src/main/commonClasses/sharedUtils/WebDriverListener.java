package commonClasses.sharedUtils;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import commonClasses.sharedUtils.enums.Drivers;
import commonClasses.sharedUtils.facades.HelperFacade;
import commonClasses.sharedUtils.managers.LocalDriver;
import commonClasses.sharedUtils.managers.LocalTest;

public class WebDriverListener implements IInvokedMethodListener {

    public static int testNumber = 0;

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        try {
            LocalTest.initializeSettings();
        } catch (Exception e) {
        }
        if (method.toString().toLowerCase().contains("beforescenario")) {
            WebDriver driver = null;
            try {
                driver = HelperFacade.getDriver(
                    Drivers.valueOf(LocalTest.getEnvironment().getBrowser().toUpperCase().trim()));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            LocalDriver.setDriver(driver);
            testNumber++;
            System.out.println("Now executing test number: " + testNumber);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        WebDriver driver = LocalDriver.getDriver();
        if (method.toString().toLowerCase().contains("afterscenario")) {
            try {
                if (driver != null) {
                    driver.quit();
                }
            } catch (Exception e) {
                driver.quit();
                throw e;
            } finally {
                driver.quit();
            }
        }
    }
}