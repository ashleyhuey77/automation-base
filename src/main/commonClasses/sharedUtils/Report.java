package commonClasses.sharedUtils;


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
	
	public void reportDoneEvent(String stepName, String description) throws FrameworkException
    {
        htmlReport.reportDoneEvent(stepName, description);
        System.out.println("Step: " + stepName + " has passed. " + description);
    }
	
	public FrameworkException reportException(String stepName, Exception webDriverException) throws FrameworkException
	{
		htmlReport.reportFailEvent(stepName, webDriverException.getMessage());
		String message = "StepName: " + stepName + "\n ErrorMessage : " + webDriverException.getMessage();
        String innerEx = webDriverException.getCause() != null ? webDriverException.getCause().getMessage() : "";
        if (!ExtensionMethods.isNullOrBlank(innerEx))
            message = message + "\n InnerException: " + innerEx;
        FrameworkException assertionFailedException = new FrameworkException(message);
        return assertionFailedException;
        //throw assertionFailedException;
	}

}
