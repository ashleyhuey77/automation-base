package commonClasses.sharedUtils.templates;

import commonClasses.sharedUtils.enums.ReportType;
import commonClasses.sharedUtils.facades.HelperFacade;
import commonClasses.sharedUtils.helpers.ApplicationHelper;
import commonClasses.sharedUtils.interfaces.Application;
import commonClasses.sharedUtils.managers.LocalDriver;
import commonClasses.sharedUtils.managers.LocalTest;

/**
 * <h2>ApplicationTemplate</h2>
 * <p>The ApplicationTemplate class is meant to bridge the gap between ApplcationHelper and 
 * the Application interface whch defines what methods are required for an application to be 
 * initialized.</p>
 * <p>The ApplicationTemplate defines which methods are required and then fills in the methods
 * that are shared between all the different applications that utilize the automation.base framework.
 * The following methods are defined in the Template class and do not be defined in any application 
 * class that inherits this template: </p>
 * <p>1. initializeBrowser() </br>
 * 	  2. initializeBrowserName() </br>
 *    3. initializeEnvironment() </br>
 *    4. initializeReporting() </br>
 * </p>
 * <p>The following are methods that are not defined in the ApplicationTemplate and will need to be
 * filled out by the individual Applicaiton class that inherits this class:
 * </p>
 * <p>1. initializeTestData() </br>
 * 	  2. openApplication() </br>
 * </p>
 * <p>When the ApplicationTemplate is initialized, it's constructor executes the required methods 
 * in the correct order that is necessary for the application to be set up properly for testing. The 
 * Methods are executed in the following order: </p>
 * <p>1. initializeBrowserName() </br>
 * 	  2. initializeEnvironment() </br>
 *    3. initializeTestData() </br>
 *    4. initializeBrowser() </br>
 *    5. initializeReporting() </br>
 *    6. openApplication() </br>
 * </p>
 * <p>More steps can be added if necessary. And if an application class requires special steps that are not included
 * in this list, they must execute them in their class alone. This class is only meant to contain application methods
 * that are shared between all test Applications.</p>
 * @author ashleyhuey
 *
 */
public abstract class ApplicationTemplate extends ApplicationHelper implements Application {

	/**
	 * <h2>ApplicationTemplate</h2>
	 * <p>The ApplicationTemplate class is meant to bridge the gap between ApplcationHelper and 
	 * the Application interface whch defines what methods are required for an application to be 
	 * initialized.</p>
	 * <p>The ApplicationTemplate defines which methods are required and then fills in the methods
	 * that are shared between all the different applications that utilize the automation.base framework.
	 * The following methods are defined in the Template class and do not be defined in any application 
	 * class that inherits this template: </p>
	 * <p>1. initializeBrowser() </br>
	 * 	  2. initializeBrowserName() </br>
	 *    3. initializeEnvironment() </br>
	 *    4. initializeReporting() </br>
	 * </p>
	 * <p>The following are methods that are not defined in the ApplicationTemplate and will need to be
	 * filled out by the individual Applicaiton class that inherits this class:
	 * </p>
	 * <p>1. initializeTestData() </br>
	 * 	  2. openApplication() </br>
	 * </p>
	 * <p>When the ApplicationTemplate is initialized, it's constructor executes the required methods 
	 * in the correct order that is necessary for the application to be set up properly for testing. The 
	 * Methods are executed in the following order: </p>
	 * <p>1. initializeBrowserName() </br>
	 * 	  2. initializeEnvironment() </br>
	 *    3. initializeTestData() </br>
	 *    4. initializeBrowser() </br>
	 *    5. initializeReporting() </br>
	 *    6. openApplication() </br>
	 * </p>
	 * <p>More steps can be added if necessary. And if an application class requires special steps that are not included
	 * in this list, they must execute them in their class alone. This class is only meant to contain application methods
	 * that are shared between all test Applications.</p>
	 * @author ashleyhuey
	 *
	 */
    public ApplicationTemplate() throws Exception {
        super();

        initializeBrowserName();
        initializeEnvironment();
        initializeTestData();
        initializeBrowser();
        initializeReporting();
        openApplication();
    }


    public void initializeBrowser() throws Exception {
        try {
            if (!browserName.trim().toLowerCase().equals("safari") &&
                !"chrome".equals(browserName.trim().toLowerCase())) {
                LocalDriver.getDriver().manage().window().maximize();
            } else if (browserName.toLowerCase().trim().equals("chrome") && !LocalTest.getEnvironment().isHeadlessEnabled()) {
                LocalTest.getEnvironment();
                if (!LocalTest.getEnvironment().isHeadlessEnabled()) {
                	//LocalDriver.getDriver().manage().window().maximize();
                    maximizeScreen();
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void initializeBrowserName() throws Exception {
        browserName = LocalTest.getEnvironment().getBrowser();
    }

    public void initializeEnvironment() throws Exception {
        environment = LocalTest.getEnvironment().getEnvironment();
    }

    public void initializeReporting() throws Exception {
        HelperFacade.initializeReportType(ReportType.REPORT, getHtmlReport(LocalTest.getTestName()));
        HelperFacade.initializeReportType(ReportType.VALIDATIONS, getHtmlReport(LocalTest.getTestName()));
    }

    @Override
    public void initializeTestData() throws Exception {

    }

    @Override
    public Object openApplication() throws Exception {
        return new Object();
    }

}