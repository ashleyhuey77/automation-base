package unitTests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

import commonClasses.sharedUtils.ExtensionMethods;
import commonClasses.sharedUtils.FrameworkConstants;
import commonClasses.sharedUtils.Security;
import commonClasses.sharedUtils.TestSettings;
import commonClasses.sharedUtils.TestUtils;

public class UtilsTests {

	@Test
	public void verifyGetTimeStamp() {
		TestUtils test = new TestUtils();
		System.out.println(test);
		
		String timeStamp = TestUtils.getTimeStamp();
		
		assertTrue(!ExtensionMethods.isNullOrBlank(timeStamp));
	}
	
	@Test
	public void verifyGetTotalPassNumber_TotalFailsEqualsZero()
	{
		ExtensionMethods ext = new ExtensionMethods();
		System.out.println(ext);
		
		float value = ExtensionMethods.getTotalPassNumber(0, 20);
		
		assertEquals("These numbers don't equal each other", 20, value, 0);
	}
	
	@Test
	public void verifyGetTotalPassNumber_TotalFailsNotZero()
	{
		ExtensionMethods ext = new ExtensionMethods();
		System.out.println(ext);
		
		float value = ExtensionMethods.getTotalPassNumber(5, 20);
		
		assertEquals("These numbers don't equal each other", 15, value, 0);
	}
	
	@Test
	public void verifyTotalPassPercentage_TotalPassEqualToTotalTestCase()
	{
		ExtensionMethods ext = new ExtensionMethods();
		System.out.println(ext);
		
		float value = ExtensionMethods.getThePassPercentage(20, 20);
		
		assertEquals("These numbers don't equal each other", 100, value, 0);
	}
	
	@Test
	public void verifyTotalPassPercentage_TotalPassNotEqualToTotalTestCase()
	{
		ExtensionMethods ext = new ExtensionMethods();
		System.out.println(ext);
		
		float value = ExtensionMethods.getThePassPercentage(15, 20);
		
		assertEquals("These numbers don't equal each other", 75, value, 0);
	}
	
	@Test
	public void verifyEncryptAndDecrypt() throws Exception
	{
		Security s = new Security();
		System.out.println(s);
		
		String result = Security.encrypt("Testing123456");
		
		assertTrue("The encrypt result is null", !ExtensionMethods.isNullOrBlank(result));
		assertTrue("The result matches the original value. String is not being encrypted.", result != "Testing123456");
		
		String result2 = Security.decrypt(result);
		
		assertTrue("The decrypt result is null", !ExtensionMethods.isNullOrBlank(result2));
		assertEquals("The result does not match the original value. String is not being decrypted.", result2, "Testing123456");
	}
	
	@Test
	public void verifyTestSettings() throws IOException
	{
		try
		{
			TestSettings test = new TestSettings();
			System.out.println(test);
			File configFile = new File(TestUtils.getRelativePath() + File.separatorChar + "resources" + File.separatorChar + "config.properties");
			InputStream inputStream = new FileInputStream(configFile);
			Properties props = new Properties();
    	 
			props.load(inputStream);

			String appUrl = props.getProperty("ApplicationUrl", "url");
			String newstronUN = props.getProperty("NewstronEncryptedUserName", "user");
			String newstronPWD = props.getProperty("NewstronEncryptedPassword", "pass");
			String env = props.getProperty("Environment", "ref");
			String browser = props.getProperty("Browser", "Chrome");

			TestSettings.setApplicationUrl(appUrl);
			TestSettings.setNewstronEncryptedUserName(newstronUN);
			TestSettings.setNewstronEncryptedPassword(newstronPWD);
			TestSettings.setEnvironment(env);
			TestSettings.setBrowser(browser);
    	
			String browserName = TestSettings.getBrowser();
			String environment = TestSettings.getEnvironment();
			String url = TestSettings.getApplicationUrl();
			String newstronun = TestSettings.getNewstronEncryptedUserName();
			String newstronpwd = TestSettings.getNewstronEncryptedPassword();
			String envn = TestSettings.getEnvironment();
			String brows = TestSettings.getBrowser();
			
			assertEquals("app url values do not match", url, "url");
			assertEquals("newstronUN values do not match", newstronun, "user");
			assertEquals("newstronPWD values do not match", newstronpwd, "pass");
			assertEquals("env values do not match", envn, "ref");
			assertEquals("browser values do not match", brows, "Chrome");
			assertEquals("browserName values do not match", browserName, "Chrome");
			assertEquals("environment values do not match", environment, "ref");
		}
		catch (Exception ex)
		{
			throw ex;
		}  
	}
	
	@Test
	public void verifyFrameworkConstants()
	{
		FrameworkConstants cons = new FrameworkConstants();
		System.out.println(cons);
		
		String result = FrameworkConstants.RESULT_FOLDER;
		
		assertEquals("result folder values do not match", result, "Results");
	}
	
	

}
