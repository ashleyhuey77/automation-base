package commonClasses.sharedUtils.managers;

import seleniumHelper.SeleniumHelper;

public class SHelper {
	
    private static ThreadLocal<SeleniumHelper> sHelper = new ThreadLocal<SeleniumHelper>();
 
    public static SeleniumHelper get() {
    	return sHelper.get();
    }
    
    public static void set(SeleniumHelper indriver)
    {
    	sHelper.set(indriver);
    }
}
