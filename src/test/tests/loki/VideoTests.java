package tests.loki;

import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.Test;
import data.loki.obj.Loki;
import data.loki.service.Environments;
import data.loki.service.TestEnvironment;

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
