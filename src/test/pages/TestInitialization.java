package pages;

import java.lang.reflect.Method;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.warnermedia.config.settings.LocalTest;

public class TestInitialization {

	protected static String browserName;
	protected static String environment;

	public TestInitialization() {

	}

	/**
	 * <summary> Before scenario to start up a new
	 * NewsAppsApplication. It also cleans up global
	 * static values as well as sets the test data
	 * values to the appropriate test data variable
	 * </summary>
	 * 
	 * @param method
	 * @throws Exception
	 */
	@BeforeMethod(alwaysRun = true)
	public void BeforeScenario(Method method) throws Exception {
		try {
			String name = method.getName();
			LocalTest.setTestName(name);
			new NewsAppsApplication();
		} catch (Exception ex) {
			throw ex;
		}
	}

	@AfterMethod(alwaysRun = true)
	public void AfterScenario() {
		try {

		} catch (WebDriverException ex) {
			throw ex;
		} finally {

		}
	}

}
