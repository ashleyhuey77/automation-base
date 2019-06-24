package tests.loki;

import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.Test;
import data.loki.obj.Loki;
import data.loki.service.Environments;
import data.loki.service.TestEnvironment;

public class SlugTests {
	
	Loki loki = new Loki(new TestEnvironment(Environments.REF), new Random());

	@Test
	public void verifyAlerts() {
		String value = loki.slug().alerts();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?|\\,|\\.|\\?|\\'|\\!|\\-|\\d)+."));
	}
	
	@Test
	public void verifyCity() {
		String value = loki.slug().city();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("(\\w+ ?){1,2}"));
	}
	
	@Test
	public void verifyCountry() {
		String value = loki.slug().country();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("(\\w+ ?|\\&|\\'|\\,|\\-|\\(|\\)|\\:|\\/){1,}"));
	}
	
	@Test
	public void verifyCountryAbbr() {
		String value = loki.slug().countryAbbr();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("(\\w+ ?){1}"));
	}
	
	@Test
	public void verifyCounty() {
		String value = loki.slug().county();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("(\\w+ ?){1}"));
	}
	
	@Test
	public void verifyDivision() {
		String value = loki.slug().division();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?|\\-|\\+|\\/|\\d)+."));
	}
	
	@Test
	public void verifyForProgram() {
		String value = loki.slug().forProgram();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?|\\-|\\+|\\/|\\d)+."));
	}
	
	@Test
	public void verifyNAR() {
		String value = loki.slug().nativeAspectRatio();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?|\\:|\\d)+."));
	}
	
	@Test
	public void verifyReporter() {
		String value = loki.slug().reporter();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?|\\,|'|-)"));
	}
	
	@Test
	public void verifyRestrictions() {
		String value = loki.slug().restrictions();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?|\\,|\\.|\\?|\\'|\\d)+."));
	}
	
	@Test
	public void verifySource() {
		String value = loki.slug().source();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("(\\w+ ?){1,2}"));
	}
	
	@Test
	public void verifyState() {
		String value = loki.slug().state();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("(\\w+ ?){1,2}"));
	}
	
	@Test
	public void verifyStateAbbr() {
		String value = loki.slug().stateAbbr();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("(\\w+ ?){1}"));
	}
	
	@Test
	public void verifyShortText() {
		String value = loki.slug().shortText();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?|\\,|\\.|\\?|\\'|\\!|\\-|\\d)+."));
	}
	
	@Test
	public void verifyLongText() {
		String value = loki.slug().longText();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("^.*(\\w+ ?|\\,|\\.|\\?|\\'|\\!|\\-|\\d)+."));
	}
	
	@Test
	public void verifyType() {
		String value = loki.slug().type();
		Assert.assertNotNull(value);
		Assert.assertTrue(value.matches("(\\w+ ?|\\-|\\/){1,3}"));
	}
	
}
