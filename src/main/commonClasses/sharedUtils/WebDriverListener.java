package commonClasses.sharedUtils;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class WebDriverListener implements IInvokedMethodListener {
	
	public static int testNumber = 0;
	 
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.toString().toLowerCase().contains("beforescenario")) {
            WebDriver driver = LocalDriverFactory.createInstance("chrome");
            LocalDriverManager.setDriver(driver);
            testNumber ++;
            System.out.println("Now executing test number: " + testNumber);
            LocalTestManager.setTestName(method.getTestMethod().getMethodName());
        }
    }
 
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    	if(method.toString().toLowerCase().contains("afterscenario")){
            WebDriver driver = LocalDriverManager.getDriver();
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
 