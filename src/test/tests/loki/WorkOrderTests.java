package tests.loki;

import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.warnermedia.data.loki.Environments;
import com.warnermedia.data.loki.Loki;
import com.warnermedia.data.loki.TestEnvironment;

public class WorkOrderTests {
	
	
	Loki loki = new Loki(new TestEnvironment(Environments.REF), new Random());

	@Test
	public void verifyRatio() {
		String value = loki.workOrder().aspectRatio();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?|\\.|\\-|\\(|\\)|\\d)+."));
	}
	
	@Test
	public void verifyCustomTrackTypes() {
		String value = loki.workOrder().customTrackTypes();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?|\\.|\\-|\\(|\\)|\\d)+."));
	}
	
	@Test
	public void verifyDepartment() {
		String value = loki.workOrder().department();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?|\\/|\\&|\\-|\\d)+."));
	}
	
	@Test
	public void verifyDistributor() {
		String value = loki.workOrder().distributor();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?|\\/|\\&|\\.|\\,|\\d)+."));
	}
	
	@Test
	public void verifyFeatureFilm() {
		String value = loki.workOrder().featureFilm();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?|\\-|\\:|\\'|\\d)+."));
	}
	
	@Test
	public void verifyNetwork() {
		String value = loki.workOrder().network();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?){1,2}"));
	}
	
	@Test
	public void verifySeries() {
		String value = loki.workOrder().series();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?|\\-|\\:|\\'|\\d)+."));
	}
	
	@Test
	public void verifyTrackLanguage() {
		String value = loki.workOrder().trackLanguage();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?|\\&){1,2}"));
	}
	
	@Test
	public void verifyTrackProfile() {
		String value = loki.workOrder().trackProfile();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?|\\+|\\.|\\d)+."));
	}
	
	@Test
	public void verifyType() {
		String value = loki.workOrder().type();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?|\\'|\\d)+."));
	}
	
	@Test
	public void verifyVideoType() {
		String value = loki.workOrder().videoType();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("(\\w+ ?|\\/|\\&)+."));
	}

}
