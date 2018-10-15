package common.utils.helpers;

import java.awt.Toolkit;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import common.utils.TestReport;
import common.utils.managers.*;
import log.TestException;

/**
 * <h2>ApplicationHelper</h2>
 * <p>
 * The ApplicationHelper class is meant to hold
 * helper methods that are executed during the set
 * up of an application.
 * </p>
 * 
 * @author ashleyhuey
 */
public abstract class ApplicationHelper {

	protected static String url;
	protected static String browserName;
	protected static String environment;

	/**
	 * <h2>ApplicationHelper</h2>
	 * <p>
	 * The ApplicationHelper class is meant to hold
	 * helper methods that are executed during the set
	 * up of an application.
	 * </p>
	 * 
	 * @author ashleyhuey
	 */
	public ApplicationHelper() {
		WebDriver browser = LocalDriver.getDriver();
		ApplicationHelper.url = LocalTest.getEnvironment().getApplicationUrl();
		PageFactory.initElements(browser, this);
	}

	/**
	 * <p>
	 * This method is used to maximize the screen size
	 * of the browser that opens during execution. It
	 * does not utilize the selenium libraries to do
	 * so because it was found that they do not work
	 * well on MAC OS. This method utilizes java
	 * libraries that make the screen maximization
	 * process universal across the different
	 * operating systems.
	 * </p>
	 * 
	 * @throws TestException
	 */
	protected void maximizeScreen() {
		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point position = new Point(0, 0);
		LocalDriver.getDriver().manage().window().setPosition(position);
		Dimension maximizedScreenSize = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
		LocalDriver.getDriver().manage().window().setSize(maximizedScreenSize);
	}

	/**
	 * <p>
	 * This method gets and initilizes an instance of
	 * the HtmlReportHelper that is necessary for the
	 * reporting functionality built into the test
	 * framework.
	 * </p>
	 * 
	 * @param testScenarioName
	 *            - the name of the test scenario
	 *            being executed.
	 * @return HtmlReport
	 * @throws TestException
	 */
	protected TestReport getHtmlReport(String testScenarioName) throws TestException {
		TestReportHelper helper = new TestReportHelper();
		return helper.initialize(LocalReport.getHtmlReport(), testScenarioName, browserName);
	}

}