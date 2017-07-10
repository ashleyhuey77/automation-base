package commonClasses.sharedUtils.managers;

import org.openqa.selenium.WebDriver;

public class LocalDriver {
	
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
 
    public static WebDriver getDriver() {
    	return driver.get();
    	/*driver = chromeWebDriver.get();
        if (driver == null)
        {
        	driver = ffWebDriver.get();
        }
        */
    }
    
    public static void setDriver(WebDriver indriver)
    {
    	driver.set(indriver);
    }
  
}
