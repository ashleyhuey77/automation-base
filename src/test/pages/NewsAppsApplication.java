package pages;

import org.openqa.selenium.WebDriverException;
import com.config.TestException;
import com.config.setup.app.ApplicationTemplate;
import com.config.setup.browser.LocalDriver;
import com.config.setup.report.LocalReport;

public class NewsAppsApplication extends ApplicationTemplate {
  
	public NewsAppsApplication() throws Exception {
		super();
		
	}

	@Override
	public TestPage openApplication() throws TestException, Exception {
        try{
				LocalDriver.getDriver().get("http://www.google.com");
        } catch (WebDriverException ex) {
        	throw LocalReport.getReport().reportException(ex);
        }
        return new TestPage();
	}
 
}
