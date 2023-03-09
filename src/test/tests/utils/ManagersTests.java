package tests.utils;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.config.setup.app.Environment;
import com.config.setup.app.TestSettings;

public class ManagersTests {
	
	@Test
	public void verifyGetLocalTestVariables() throws Exception {
		TestSettings ts = new TestSettings(new Environment("", "", "", "", "", true));
		Assert.assertNotNull(ts);
		Assert.assertNotNull(ts.getEnvironment());
	}

}
