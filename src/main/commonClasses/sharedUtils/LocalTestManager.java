package commonClasses.sharedUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LocalTestManager {
	
	private static ThreadLocal<String> testName = new ThreadLocal<String>();
	 
    public static String getTestName() {
    	return testName.get();
    }
    
    public static void setTestName(String name)
    {
    	testName.set(name);
    }
    
    public static void initializeSettings() throws IOException
    {
    	try
    	{
	    	File configFile = new File(TestUtils.getRelativePath() + File.separatorChar + "resources" + File.separatorChar + "config.properties");
	    	InputStream inputStream = new FileInputStream(configFile);
	    	Properties props = new Properties();
	    	 
	    	props.load(inputStream);
	
	    	String appUrl = props.getProperty("ApplicationUrl", "");
	    	String newstronUN = props.getProperty("NewstronEncryptedUserName", "");
	    	String newstronPWD = props.getProperty("NewstronEncryptedPassword", "");
	    	String env = props.getProperty("Environment", "ref");
	    	String browser = props.getProperty("Browser", "Chrome");
	    	Boolean isHeadless = Boolean.parseBoolean(props.getProperty("IsHeadless", "false"));
	
	    	TestSettings.setApplicationUrl(appUrl);
	    	TestSettings.setNewstronEncryptedUserName(newstronUN);
	    	TestSettings.setNewstronEncryptedPassword(newstronPWD);
	    	TestSettings.setEnvironment(env);
	    	TestSettings.setBrowser(browser);
	    	TestSettings.setIsHeadless(isHeadless);
    	}
    	catch (Exception ex)
    	{
    		throw ex;
    	}
    }

}
