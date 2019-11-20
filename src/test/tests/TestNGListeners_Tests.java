package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGListeners_Tests {
	
	private static int retryCount = 0;
	
	@Test
	public void verifyRetryFunctionality() {
		try {
			while(retryCount < 2) {
				retryCount++;
				Assert.fail("This is a test failure to verify retry functionality.");
			}
			Assert.assertTrue(true);
		} catch (Exception e) {
			throw e;
		}
	}
}
