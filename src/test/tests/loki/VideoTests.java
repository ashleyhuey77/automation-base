package tests.loki;

import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.warnermedia.data.loki.Environments;
import com.warnermedia.data.loki.Loki;
import com.warnermedia.data.loki.TestEnvironment;

public class VideoTests {
	
	Loki loki = new Loki(new TestEnvironment(Environments.REF), new Random());

	@Test
	public void verifyUrl() {
		String value = loki.video().url();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+|\\,|\\.|\\?|\\'|\\d){1}"));
	}
	
	@Test
	public void verifyFilePath() {
		String value = loki.video().filePath();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+|\\,|\\.|\\?|\\'|\\d){1}"));
	}
}
