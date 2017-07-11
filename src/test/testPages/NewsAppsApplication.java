package testPages;

import org.openqa.selenium.WebDriverException;

import commonClasses.sharedUtils.managers.LocalDriver;
import commonClasses.sharedUtils.managers.LocalReport;
import commonClasses.sharedUtils.templates.ApplicationTemplate;

public class NewsAppsApplication extends ApplicationTemplate {
  
	public NewsAppsApplication() throws Exception {
		super();
		
	}

	@Override
	public void initializeTestData() throws Exception {
		
	}

	@Override
	public TestPage openApplication() throws Exception {
        try
        {
        	 LocalDriver.getDriver().get("http://www.google.com");
        }
        catch (WebDriverException ex)
        {
        	throw LocalReport.getReport().reportException(ex);
        }
        return new TestPage();
	}
 
}
