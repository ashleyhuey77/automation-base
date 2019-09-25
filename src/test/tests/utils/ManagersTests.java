package tests.utils;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.utils.builders.Environment;
import common.utils.builders.TestSettings;
import common.utils.managers.LocalReport;

public class ManagersTests {
	
	@Test
	public void verifyGetLocalReportVariables() {
		LocalReport.setFilePath("Test");
		String v1 = LocalReport.getFilePath();
		Assert.assertNotNull(v1);
	}
	
	@Test
	public void verifyGetLocalTestVariables() throws Exception {
		TestSettings ts = new TestSettings(new Environment("", "", "", "", "", true));
		Assert.assertNotNull(ts);
		Assert.assertNotNull(ts.getEnvironment());
	}

}
