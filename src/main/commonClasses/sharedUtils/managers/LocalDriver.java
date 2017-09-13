package commonClasses.sharedUtils.managers;

import org.openqa.selenium.WebDriver;

/**
 * <h2>LocalDriver</h2>
 * <p>The LocalDriver class is used to get and set the threadsafe instance of 
 * WebDriver.</p>
 * @author ashleyhuey
 *
 */
public class LocalDriver {
	
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
 
    /**
     * <p>Get the threadsafe instance of WebDriver.</p>
     * @return WebDriver
     */
    public static WebDriver getDriver() {
    	return driver.get();
    }
    
    /**
     * <p>Set the threadsafe instance of WebDriver </p>
     * @param value - the WebDriver instance to set.
     */
    public static void setDriver(WebDriver value) {
    	driver.set(value);
    }
  
}
