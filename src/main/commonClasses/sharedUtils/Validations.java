package commonClasses.sharedUtils;


import java.io.IOException;

import junit.framework.AssertionFailedError;
import reporting.framework.utilities.FrameworkException;

public class Validations {
	
	private HtmlReport htmlReport;
    private MsTESTReport msTestReport;
	
	public Validations(HtmlReport htmlReport, MsTESTReport msTestReport)
	{
		this.htmlReport = htmlReport;
        this.msTestReport = msTestReport;
	}
	
	public void assertionPass(String message) throws FrameworkException, IOException
	{
		String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
		htmlReport.reportPassEvent(stepName, message);
		System.out.println("Step: " + stepName + " has passed. " + message);
	}
	
	public FrameworkException assertionFailed(String message) throws FrameworkException, IOException
    {
		String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
		AssertionFailedError assertionFailedError = getAssertionFailedErrorObject(stepName, message);
			htmlReport.reportFailEvent(stepName, message);
			System.out.println(assertionFailedError.toString());
			FrameworkException ex = msTestReport.reportAssertionFailed(assertionFailedError);
			return ex;
    }
	
	private AssertionFailedError getAssertionFailedErrorObject(String stepName, String errorMessage)
    {
        String message = "StepName: " + stepName + "\n ErrorMessage : " + errorMessage;
        AssertionFailedError assertionFailedError = new AssertionFailedError(message);
        return assertionFailedError;
    }

}
