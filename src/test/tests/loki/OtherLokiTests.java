package tests.loki;

import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.Test;
import data.loki.obj.Loki;
import data.loki.service.Environments;
import data.loki.service.TestEnvironment;

public class OtherLokiTests {
	
	@Test(expectedExceptions=IllegalArgumentException.class)
	public void verifyMockValuesService_NullTestEnvironment() {
		@SuppressWarnings("unused")
		Loki loki = new Loki(null, new Random());
	}
	
	@Test
	public void verifyMockValuesService_NullRandom() {
		Loki loki = new Loki(new TestEnvironment(Environments.REF), null);
		Assert.assertNotNull(loki);
	}

}
