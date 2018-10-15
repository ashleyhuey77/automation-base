package pages;

import org.openqa.selenium.WebDriverException;
import common.utils.managers.LocalDriver;
import common.utils.managers.LocalReport;
import common.utils.templates.ApplicationTemplate;
import log.TestException;

public class NewsAppsApplication extends ApplicationTemplate {
  
	public NewsAppsApplication() throws Exception {
		super();
		
	}

	@Override
	public void initializeTestData() throws TestException {
		
	}

	@Override
	public TestPage openApplication() throws TestException, Exception {
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
