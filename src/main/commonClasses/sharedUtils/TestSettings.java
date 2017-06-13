package commonClasses.sharedUtils;



public class TestSettings {
	
	private static String applicationUrl;
	private static String newstronEncryptedUserName;
	private static String newstronEncryptedPassword;
	private static String environment;
	private static String browser;
	private static Boolean isHeadless;
	private static String miraEncryptedUserName;
	private static String miraEncryptedPassword;
	
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
	
	public static Boolean getIsHeadless()
	{
		return isHeadless;
	}
	
	public static void setIsHeadless(Boolean value)
	{
		isHeadless = value;
	}
	public static String getMiraEncryptedUserName() {
		return miraEncryptedUserName;
	}
	public static void setMiraEncryptedUserName(String value) {
		miraEncryptedUserName = value;
	}
	public static String getMiraEncryptedPassword() {
		return miraEncryptedPassword;
	}
	public static void setMiraEncryptedPassword(String value) {
		miraEncryptedPassword = value;
	}

}
