package commonClasses.sharedUtils;


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
	
	public void assertionPass(String stepName, String message) throws FrameworkException
	{
		htmlReport.reportPassEvent(stepName, message);
		System.out.println("Step: " + stepName + " has passed. " + message);
	}
	
	public void assertionFailed(String stepName, String message) throws FrameworkException
    {
		AssertionFailedError assertionFailedError = getAssertionFailedErrorObject(stepName, message);
			htmlReport.reportFailEvent(stepName, message);
			System.out.println(assertionFailedError.toString());
			msTestReport.reportAssertionFailed(assertionFailedError);
    }
	
	private AssertionFailedError getAssertionFailedErrorObject(String stepName, String errorMessage)
    {
        String message = "StepName: " + stepName + "\n ErrorMessage : " + errorMessage;
        AssertionFailedError assertionFailedError = new AssertionFailedError(message);
        return assertionFailedError;
    }

}
