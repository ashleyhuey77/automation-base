package tests.utils;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.utils.builders.Credentials;
import common.utils.builders.Environment;
import common.utils.builders.TestSettings;
import common.utils.managers.LocalReport;
import common.utils.managers.LocalTest;

public class ManagersTests {
	
	@Test
	public void verifyGetLocalReportVariables() {
		LocalReport.setFilePath("Test");
		String v1 = LocalReport.getFilePath();
		Assert.assertNotNull(v1);
	}
	
	@Test
	public void verifyGetLocalTestVariables() throws Exception {
		LocalTest.setCredentials(new Credentials("gUWMnZZGdydd8OzRvuL1pg==:QabzBV4P5/ZaLnCfozlZhw==", "gUWMnZZGdydd8OzRvuL1pg==:QabzBV4P5/ZaLnCfozlZhw=="));
		Credentials v1 = LocalTest.getCredentials();
		LocalTest.setEmailCredentials(new Credentials("Test", "Test"));
		Credentials v2 = LocalTest.getEmailCredentials();
		Credentials v3 = new Credentials("gUWMnZZGdydd8OzRvuL1pg==:QabzBV4P5/ZaLnCfozlZhw==", "gUWMnZZGdydd8OzRvuL1pg==:QabzBV4P5/ZaLnCfozlZhw==", "gUWMnZZGdydd8OzRvuL1pg==:QabzBV4P5/ZaLnCfozlZhw==", "gUWMnZZGdydd8OzRvuL1pg==:QabzBV4P5/ZaLnCfozlZhw==");
		TestSettings ts = new TestSettings(new Environment("", "", "", "", "", true), v3);
		Assert.assertNotNull(v1);
		Assert.assertNotNull(v2);
		Assert.assertNotNull(v3);
		Assert.assertNotNull(v3.getEmailServerPWD());
		Assert.assertNotNull(v3.getEmailServerUN());
		Assert.assertNotNull(v3.getMiraPWord());
		Assert.assertNotNull(v3.getMiraUN());
		Assert.assertNotNull(v3.getNewstronPWord());
		Assert.assertNotNull(v3.getNewstronUN());
		Assert.assertNotNull(ts);
		Assert.assertNotNull(ts.getEnvironment());
		Assert.assertNotNull(ts.getCredentials());
	}

}
