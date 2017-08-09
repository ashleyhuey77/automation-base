package unitTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import commonClasses.sharedUtils.TestUtils;
import commonClasses.sharedUtils.helpers.SecurityHelper;

//@Listeners(WebDriverListener.class)
public class UtilsTest {

	@Test
	public void verifyGetTimeStamp() {
		TestUtils test = new TestUtils();
		System.out.println(test);
		
		String timeStamp = TestUtils.getTimeStamp();
		
		Assert.assertTrue(!TestUtils.isNullOrBlank(timeStamp));
	}
	
	@Test
	public void verifyEncryptAndDecrypt() throws Exception
	{
		SecurityHelper s = new SecurityHelper();
		System.out.println(s);
		
		String p = "Sami123!";
		//String u = "";
		String result1 = SecurityHelper.encrypt(p);
		//String result3 = SecurityHelper.encrypt(u);
		
		Assert.assertTrue(!TestUtils.isNullOrBlank(result1));
		Assert.assertTrue(result1 != "Testing123456");
		
		String result2 = SecurityHelper.decrypt(result1);
		
		Assert.assertTrue(!TestUtils.isNullOrBlank(result2));
		Assert.assertEquals(result2, "Testing123456");
	}
	
	

}
