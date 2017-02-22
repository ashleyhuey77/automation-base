package commonClasses.sharedUtils;


public class LocalTestManager {
	
	private static ThreadLocal<String> testName = new ThreadLocal<String>();
	 
    public static String getTestName() {
    	return testName.get();
    }
    
    public static void setTestName(String name)
    {
    	testName.set(name);
    }

}
