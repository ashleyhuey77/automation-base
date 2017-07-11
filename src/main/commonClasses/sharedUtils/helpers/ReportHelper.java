package commonClasses.sharedUtils.helpers;


import java.io.IOException;

import commonClasses.sharedUtils.HtmlReport;
import reporting.framework.utilities.FrameworkException;

public class ReportHelper {
	
	private HtmlReport htmlReport;
	
	public ReportHelper(HtmlReport htmlReport)
	{
		this.htmlReport = htmlReport;
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
	}

}
