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
	
	public FrameworkException assertionFailed(String stepName, String message) throws FrameworkException
    {
		//instead of throwing error directly in this method, we should set some int or something equal to something that then throws the error
		//it should be maintained in some other code. Maybe in reporting or some such
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
