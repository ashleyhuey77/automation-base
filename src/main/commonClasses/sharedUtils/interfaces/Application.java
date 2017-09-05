package commonClasses.sharedUtils.interfaces;


public interface Application {
	
	public Object openApplication() throws Exception;
	
	public void initializeBrowserName() throws Exception;
	
	public void initializeEnvironment() throws Exception;

	public void initializeTestData() throws Exception;
	
	public void initializeBrowser() throws Exception;
	
	public void initializeReporting() throws Exception;
	
}
