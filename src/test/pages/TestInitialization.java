package pages;

import java.io.BufferedReader;
import java.io.Reader;
import java.lang.reflect.Method;
import org.jsoup.Jsoup;
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
	
    protected static String extractText(Reader reader) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(reader);
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String textOnly = Jsoup.parse(sb.toString()).text();
        return textOnly;
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
