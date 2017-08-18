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

    public void assertionPass(String message) throws FrameworkException, IOException {
        String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
        htmlReport.reportPassEvent(stepName, message);
        System.out.println("Step: " + stepName + " has passed. " + message);
    }

    public FrameworkException assertionFailed(String message) throws FrameworkException, IOException {
        String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
        AssertionFailedError assertionFailedError = getAssertionFailedErrorObject(stepName, message);
        htmlReport.reportFailEvent(stepName, message);
        System.out.println(assertionFailedError.toString());
        FrameworkException ex = reportAssertionFailed(assertionFailedError);
        return ex;
    }

    private AssertionFailedError getAssertionFailedErrorObject(String stepName, String errorMessage) {
        String message = "StepName: " + stepName + "\n ErrorMessage : " + errorMessage;
        AssertionFailedError assertionFailedError = new AssertionFailedError(message);
        return assertionFailedError;
    }

    private FrameworkException reportAssertionFailed(AssertionFailedError assertionFailedError) throws FrameworkException {
        FrameworkException exception = new FrameworkException(assertionFailedError.toString());
        System.out.println(exception);
        return exception;
    }

}