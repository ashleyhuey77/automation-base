package tests.utils;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.warnermedia.config.settings.Environment;
import com.warnermedia.config.settings.TestSettings;

public class ManagersTests {
	
	@Test
	public void verifyGetLocalTestVariables() throws Exception {
		TestSettings ts = new TestSettings(new Environment("", "", "", "", "", true));
		Assert.assertNotNull(ts);
		Assert.assertNotNull(ts.getEnvironment());
	}

}
