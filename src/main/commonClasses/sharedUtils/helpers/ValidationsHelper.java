package commonClasses.sharedUtils.helpers;


import java.io.IOException;

import commonClasses.sharedUtils.HtmlReport;
import junit.framework.AssertionFailedError;
import reporting.framework.utilities.FrameworkException;

public class ValidationsHelper {

    private HtmlReport htmlReport;

    public ValidationsHelper(HtmlReport htmlReport) {
        this.htmlReport = htmlReport;
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
    public void assertionPass(String message) throws FrameworkException, IOException {
        String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
        htmlReport.reportPassEvent(stepName, message);
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
    public FrameworkException assertionFailed(String message) throws FrameworkException, IOException {
        String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
        AssertionFailedError assertionFailedError = getAssertionFailedErrorObject(stepName, message);
        htmlReport.reportFailEvent(stepName, message);
        System.out.println(assertionFailedError.toString());
        FrameworkException ex = reportAssertionFailed(assertionFailedError);
        return ex;
    }

    private AssertionFailedError getAssertionFailedErrorObject(String stepName, String errorMessage) {
        String message = "StepName: " + stepName + "\n ErrorMessage: " + errorMessage;
        AssertionFailedError assertionFailedError = new AssertionFailedError(message);
        return assertionFailedError;
    }

    private FrameworkException reportAssertionFailed(AssertionFailedError assertionFailedError) throws FrameworkException {
        FrameworkException exception = new FrameworkException(assertionFailedError.toString());
        System.out.println(exception);
        return exception;
    }

}