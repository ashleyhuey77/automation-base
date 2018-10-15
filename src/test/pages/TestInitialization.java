package pages;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import common.utils.managers.LocalReport;
import common.utils.managers.LocalTest;

public class TestInitialization {

    protected static String browserName;
    protected static String environment;

    public TestInitialization() {

    }

    /**
     * <summary> Before scenario to start up a new NewsAppsApplication. It also
     * cleans up global static values as well as sets the test data values to
     * the appropriate test data variable </summary>
     * 
     * @param method
     * @throws Exception
     */
    @BeforeMethod(alwaysRun = true)
    public void BeforeScenario(Method method) throws Exception {
	try {
	    // System.setProperty("webdriver.chrome.driver",
	    // TestUtils.getRelativePath() + "/ExternalLibraries/chromedriver");
	    String name = method.getName();
	    LocalTest.setTestName(name);
	    new NewsAppsApplication();
	} catch (Exception ex) {
	    throw ex;
	}
    }

    protected String getTime(int minutesIntoTheFuture) throws Exception {
	String time = null;
	try {
	    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	    Date myDate = new Date();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(myDate);
	    cal.add(Calendar.MINUTE, minutesIntoTheFuture);
	    myDate = cal.getTime();
	    time = format.format(myDate);
	} catch (Exception ex) {
	    throw LocalReport.getReport().reportException(ex);
	}
	return time;
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
