package common.utils.helpers;


import java.io.IOException;
import common.utils.TestReport;
import junit.framework.AssertionFailedError;

public class ValidationsHelper {

    //private HtmlReport htmlReport;
	private TestReport testReport;

/*    public ValidationsHelper(HtmlReport htmlReport) {
        this.htmlReport = htmlReport;
    }*/
	
    public ValidationsHelper(TestReport htmlReport) {
        this.testReport = htmlReport;
    }

    /**
     * <p>This method will report a pass event to the test steps html 
     * report that is generated at the end of each test run.</p>
     * <p>This method would get called when it is necessary to document
     * a passed step to the html report along with a screencap of the passed event.</p>
     * <p>A line will be printed to the html report in the following format:</p>
     * <p>Step: [stepName] has passed. [message]</p>
     * <p>The step name param in the above example is autopopulated with
     * the last step that was executed in the stack trace.</p>
     * @param message - a description of the step that was executed. The text
     * one wishes to display on the html report for the executed step.
     * @throws FrameworkException
     * @throws IOException
     */
    public void assertionPass(String message) throws Exception {
        String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
        testReport.reportPassEvent(stepName, message);
        System.out.println("Step: " + stepName + " has passed. " + message);
    }

    /**
     * <p>This method will report a fail event to the test steps html 
     * report that is generated at the end of each test run.</p>
     * <p>This method would get called when it is necessary to document
     * a failed step to the html report along with a screencap of the failed event.</p>
     * <p>A line will be printed to the html report in the following format:</p>
     * <p>StepName: [stepName]. ErrorMessage: [message]</p>
     * <p>The step name param in the above example is autopopulated with
     * the last step that was executed in the stack trace.</p>
     * @param message - a description of the step that was executed. The text
     * one wishes to display on the html report for the executed step.
     * @throws FrameworkException
     * @throws IOException
     */
    public Exception assertionFailed(String message) throws Exception {
        String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
        AssertionFailedError assertionFailedError = getAssertionFailedErrorObject(stepName, message);
        testReport.reportFailEvent(stepName, message);
        System.out.println(assertionFailedError.toString());
        Exception ex = reportAssertionFailed(assertionFailedError);
        return ex;
    }

    private AssertionFailedError getAssertionFailedErrorObject(String stepName, String errorMessage) {
        String message = "StepName: " + stepName + "\n ErrorMessage: " + errorMessage;
        AssertionFailedError assertionFailedError = new AssertionFailedError(message);
        return assertionFailedError;
    }

    private Exception reportAssertionFailed(AssertionFailedError assertionFailedError) throws Exception {
        Exception exception = new Exception(assertionFailedError.toString());
        System.out.println(exception);
        return exception;
    }

}