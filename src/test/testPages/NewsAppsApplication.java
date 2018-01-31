package testPages;

import org.openqa.selenium.WebDriverException;
import common.utils.managers.LocalDriver;
import common.utils.managers.LocalReport;
import common.utils.templates.ApplicationTemplate;

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
