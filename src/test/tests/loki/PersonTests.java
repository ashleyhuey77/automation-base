package tests.loki;
import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.warnermedia.data.loki.Environments;
import com.warnermedia.data.loki.Loki;
import com.warnermedia.data.loki.TestEnvironment;

public class PersonTests {

	@Test
	public void verifyName() {
		Loki loki = new Loki(new TestEnvironment(Environments.REF), new Random());
		String name = loki.person().name();
		Assert.assertNotNull(name);
		Assert.assertTrue(name.matches("(\\w+ ?){1,2}"));
	}
	
	@Test
	public void verifyFirstName() {
		Loki loki = new Loki(new TestEnvironment(Environments.REF), new Random());
		String name = loki.person().firstName();
		Assert.assertNotNull(name);
		Assert.assertTrue(name.matches("(\\w+ ?){1,2}"));
	}
	
	@Test
	public void verifyLastName() {
		Loki loki = new Loki(new TestEnvironment(Environments.REF), new Random());
		String name = loki.person().lastName();
		Assert.assertNotNull(name);
		Assert.assertTrue(name.matches("(\\w+ ?){1,2}"));
	}
	
	@Test
	public void verifyPhoneNumber() {
		Loki loki = new Loki(new TestEnvironment(Environments.REF), new Random());
		String name = loki.person().phoneNumber();
		Assert.assertNotNull(name);
	}
	
	@Test
	public void verifyRole() {
		Loki loki = new Loki(new TestEnvironment(Environments.REF), new Random());
		String name = loki.person().role();
		Assert.assertNotNull(name);
		Assert.assertTrue(name.matches("(\\w+ ?|\\'){1,6}"));
	}
	
	@Test
	public void verifyPermission() {
		Loki loki = new Loki(new TestEnvironment(Environments.REF), new Random());
		String name = loki.person().permission();
		Assert.assertNotNull(name);
		Assert.assertTrue(name.matches("^.*(\\w+ ?|\\.){1}"));
	}
	
	@Test
	public void verifyGroupName() {
		Loki loki = new Loki(new TestEnvironment(Environments.REF), new Random());
		String name = loki.person().groupName();
		Assert.assertNotNull(name);
		Assert.assertTrue(name.matches("(\\w+ ?|\\'){1,6}"));
	}

}
