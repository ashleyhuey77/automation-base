package commonClasses.sharedUtils;

public class TestSettings {
	
	private static String applicationUrl;
	private static String newstronEncryptedUserName;
	private static String newstronEncryptedPassword;
	private static String environment;
	private static String browser;
	private static boolean requiresTestCleanUp;
	
	public static String getApplicationUrl()
	{
		return applicationUrl;
	}
	
	public static void setApplicationUrl(String value)
	{
		applicationUrl = value;
	}
	
	public static String getNewstronEncryptedUserName()
	{
		return newstronEncryptedUserName;
	}
	
	public static void setNewstronEncryptedUserName(String value)
	{
		newstronEncryptedUserName = value;
	}
	
	public static String getNewstronEncryptedPassword()
	{
		return newstronEncryptedPassword;
	}
	
	public static void setNewstronEncryptedPassword(String value)
	{
		newstronEncryptedPassword = value;
	}
	
	public static String getEnvironment()
	{
		return environment;
	}
	
	public static void setEnvironment(String value)
	{
		environment = value;
	}
	
	public static String getBrowser()
	{
		return browser;
	}
	
	public static void setBrowser(String value)
	{
		browser = value;
	}
	
	public static boolean getRequiresTestCleanUp()
	{
		return requiresTestCleanUp;
	}
	
	public static void setRequiresTestCleanUp(Boolean value)
	{
		requiresTestCleanUp = value;
	}
}
