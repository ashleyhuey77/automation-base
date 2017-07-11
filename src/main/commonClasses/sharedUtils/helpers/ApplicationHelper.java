package commonClasses.sharedUtils.helpers;

import java.awt.Toolkit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import commonClasses.sharedUtils.managers.LocalDriver;
import commonClasses.sharedUtils.managers.LocalReport;
import commonClasses.sharedUtils.managers.LocalTest;

public abstract class ApplicationHelper {
	
	public static String url;
	public static String browserName;
	public static String environment;
	
	public ApplicationHelper()
	{
    	WebDriver browser = LocalDriver.getDriver();
        ApplicationHelper.url = LocalTest.getEnvironment().getApplicationUrl();
        PageFactory.initElements(browser, this);
	}
	
	
    protected void maximizeScreen() {
    	try
    	{
	        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        Point position = new Point(0, 0);
	        LocalDriver.getDriver().manage().window().setPosition(position);
	        Dimension maximizedScreenSize =
	            new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
	        LocalDriver.getDriver().manage().window().setSize(maximizedScreenSize);
    	}
    	catch (Exception ex)
    	{
    		throw ex;
    	}
      }
    
    protected commonClasses.sharedUtils.HtmlReport getHtmlReport(String testScenarioName, WebDriver browser) throws Exception
    {
    	try
    	{
	        return HtmlReportHelper.initialize(LocalReport.getHtmlReport(), browser, testScenarioName, browserName);
    	}
    	catch (Exception ex) 
    	{
			throw ex;
		}

    }

}
