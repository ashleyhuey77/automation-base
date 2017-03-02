package unitTests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonClasses.sharedUtils.ExtensionMethods;
import commonClasses.sharedUtils.FrameworkConstants;
import commonClasses.sharedUtils.Security;
import commonClasses.sharedUtils.TestSettings;
import commonClasses.sharedUtils.TestUtils;
import commonClasses.sharedUtils.WebDriverListener;

@Listeners(WebDriverListener.class)
public class UtilsTests {

	@Test
	public void verifyGetTimeStamp() {
		TestUtils test = new TestUtils();
		System.out.println(test);
		
		String timeStamp = TestUtils.getTimeStamp();
		
		Assert.assertTrue(!ExtensionMethods.isNullOrBlank(timeStamp));
	}
	
	@Test
	public void verifyGetTotalPassNumber_TotalFailsEqualsZero()
	{
		ExtensionMethods ext = new ExtensionMethods();
		System.out.println(ext);
		
		float value = ExtensionMethods.getTotalPassNumber(0, 20);
		
		Assert.assertEquals(20, value, 0);
	}
	
	@Test
	public void verifyGetTotalPassNumber_TotalFailsNotZero()
	{
		ExtensionMethods ext = new ExtensionMethods();
		System.out.println(ext);
		
		float value = ExtensionMethods.getTotalPassNumber(5, 20);
		
		Assert.assertEquals(15, value, 0);
	}
	
	@Test
	public void verifyTotalPassPercentage_TotalPassEqualToTotalTestCase()
	{
		ExtensionMethods ext = new ExtensionMethods();
		System.out.println(ext);
		
		float value = ExtensionMethods.getThePassPercentage(20, 20);
		
		Assert.assertEquals(100, value, 0);
	}
	
	@Test
	public void verifyTotalPassPercentage_TotalPassNotEqualToTotalTestCase()
	{
		ExtensionMethods ext = new ExtensionMethods();
		System.out.println(ext);
		
		float value = ExtensionMethods.getThePassPercentage(15, 20);
		
		Assert.assertEquals(75, value, 0);
	}
	
	@Test
	public void verifyEncryptAndDecrypt() throws Exception
	{
		Security s = new Security();
		System.out.println(s);
		
		String result = Security.encrypt("Testing123456");
		
		Assert.assertTrue(!ExtensionMethods.isNullOrBlank(result));
		Assert.assertTrue(result != "Testing123456");
		
		String result2 = Security.decrypt(result);
		
		Assert.assertTrue(!ExtensionMethods.isNullOrBlank(result2));
		Assert.assertEquals(result2, "Testing123456");
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
			
			Assert.assertEquals(url, "url");
			Assert.assertEquals(newstronun, "user");
			Assert.assertEquals(newstronpwd, "pass");
			Assert.assertEquals(envn, "ref");
			Assert.assertEquals(brows, "Chrome");
			Assert.assertEquals(browserName, "Chrome");
			Assert.assertEquals(environment, "ref");
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
		
		Assert.assertEquals(result, "Results");
	}
	
	

}
