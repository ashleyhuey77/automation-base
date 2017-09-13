package commonClasses.sharedUtils.managers;

import seleniumHelper.SeleniumHelper;

/**
 * <h2>SHelper</h2>
 * <p>The SHelper class is used to get and set the threadsafe instance of 
 * SeleniumHelper.</p>
 * @author ashleyhuey
 *
 */
public class SHelper {
	
    private static ThreadLocal<SeleniumHelper> sHelper = new ThreadLocal<SeleniumHelper>();
 
    /**
     * <p>Get the threadsafe instance of SeleniumHelper.</p>
     * @return SeleniumHelper
     */
    public static SeleniumHelper get() {
    	return sHelper.get();
    }
    
    /**
     * <p>Set the threadsafe instance of SeleniumHelper </p>
     * @param value - the SeleniumHelper instance to set.
     */
    public static void set(SeleniumHelper value) {
    	sHelper.set(value);
    }
}
