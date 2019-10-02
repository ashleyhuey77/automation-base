package pages;

import org.openqa.selenium.WebDriverException;
import com.warnermedia.config.TestException;
import com.warnermedia.config.app.ApplicationTemplate;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.report.LocalReport;

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
