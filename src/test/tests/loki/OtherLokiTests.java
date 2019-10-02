package tests.loki;

import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.warnermedia.data.loki.Environments;
import com.warnermedia.data.loki.Loki;
import com.warnermedia.data.loki.TestEnvironment;

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
