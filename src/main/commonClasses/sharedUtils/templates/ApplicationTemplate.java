package commonClasses.sharedUtils.templates;

import commonClasses.sharedUtils.enums.ReportType;
import commonClasses.sharedUtils.facades.HelperFacade;
import commonClasses.sharedUtils.helpers.ApplicationHelper;
import commonClasses.sharedUtils.interfaces.Application;
import commonClasses.sharedUtils.managers.LocalDriver;
import commonClasses.sharedUtils.managers.LocalTest;

public abstract class ApplicationTemplate extends ApplicationHelper implements Application {

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