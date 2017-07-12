package commonClasses.sharedUtils.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import commonClasses.sharedUtils.TestUtils;
import commonClasses.sharedUtils.builders.Credentials;
import commonClasses.sharedUtils.builders.Environment;

public class LocalTest {
	
	private static ThreadLocal<String> testName = new ThreadLocal<String>();
	private static Environment environment;
	private static ThreadLocal<Credentials> credentials = new ThreadLocal<Credentials>();
	 
    public static String getTestName() {
    	return testName.get();
    }
    
    public static void setTestName(String name)
    {
    	testName.set(name);
    }
    
    public static Environment getEnvironment() {
    	return environment;
    }
    
    public static void setEnvironment(Environment value) {
    	environment = value;
    }
    
    public static Credentials getCredentials() {
    	return credentials.get();
    }
    
    public static void setCredentials(Credentials value) {
    	credentials.set(value);
    }
    
    public static void initializeSettings() throws Exception
    {
    	try
    	{
	    	File configFile = new File(TestUtils.getRelativePath() + File.separatorChar + "resources" + File.separatorChar + "config.properties");
	    	InputStream inputStream = new FileInputStream(configFile);
	    	Properties props = new Properties();
	    	 
	    	props.load(inputStream);
	
	    	String appUrl = props.getProperty("ApplicationUrl", "");
	    	String newstronUN = props.getProperty("NewstronEncryptedUserName", "n/a");
	    	String newstronPWD = props.getProperty("NewstronEncryptedPassword", "n/a");
	    	String env = props.getProperty("Environment", "ref");
	    	String browser = props.getProperty("Browser", "Chrome");
	    	Boolean isHeadless = Boolean.parseBoolean(props.getProperty("IsHeadless", "false"));
	        String miraUserName = props.getProperty("miraEncryptedUserName", "n/a");
	        String miraPassword= props.getProperty("miraEncryptedPassword", "n/a");
	        
	        Environment environment = new Environment(appUrl, env, browser, isHeadless);
	        Credentials credentials = new Credentials(miraUserName, miraPassword, newstronUN, newstronPWD);
	        setCredentials(credentials);
	        setEnvironment(environment);
    	}
    	catch (Exception ex)
    	{
    		throw ex;
    	}
    }

}