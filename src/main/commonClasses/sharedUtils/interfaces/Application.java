package commonClasses.sharedUtils.interfaces;

import org.openqa.selenium.WebDriver;

public interface Application {
	
	public Object openApplication() throws Exception;
	
	public void initializeBrowserName() throws Exception;
	
	public void initializeEnvironment() throws Exception;

	public void initializeTestData() throws Exception;
	
	public void initializeBrowser() throws Exception;
	
	public void initializeReporting(WebDriver browser) throws Exception;
	
}
