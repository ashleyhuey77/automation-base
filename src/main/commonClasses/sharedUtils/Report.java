package commonClasses.sharedUtils;


import java.io.IOException;

import reporting.framework.utilities.FrameworkException;

public class Report {
	
	private HtmlReport htmlReport;
    @SuppressWarnings("unused")
	private MsTESTReport msTestReport;
	
	public Report(HtmlReport htmlReport, MsTESTReport msTestReport)
	{
		this.htmlReport = htmlReport;
        this.msTestReport = msTestReport;
	}
	
	public void reportDoneEvent(String description) throws FrameworkException, IOException
    {
		String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
        htmlReport.reportDoneEvent(stepName, description);
        System.out.println("Step: " + stepName + " has passed. " + description);
    }
	
	public FrameworkException reportException(Exception webDriverException) throws FrameworkException, IOException
	{
		String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
		htmlReport.reportFailEvent(stepName, webDriverException.getMessage());
		String message = "StepName: " + stepName + "\n ErrorMessage : " + webDriverException.getMessage();
        FrameworkException assertionFailedException = new FrameworkException(message);
        return assertionFailedException;
        //throw assertionFailedException;
	}

}
