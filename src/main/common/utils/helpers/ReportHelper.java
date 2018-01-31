package common.utils.helpers;


import java.io.IOException;
import common.utils.TestReport;

public class ReportHelper {

    //private HtmlReport htmlReport;
	
	private TestReport testReport;

/*    public ReportHelper(HtmlReport htmlReport) {
        this.htmlReport = htmlReport;
    }*/
    
    public ReportHelper(TestReport htmlReport) {
        this.testReport = htmlReport;
    }

    /**
     * <p>This method will report a done event to the test steps html 
     * report that is generated at the end of each test run.</p>
     * <p>This method would get called when it is necessary to document
     * a step to the html report but it is not necessary to have a screencap.</p>
     * <p>A line will be printed to the html report in the following format:</p>
     * <p>Step: [stepName] has passed. [description]</p>
     * <p>The step name param in the above example is autopopulated with
     * the last step that was executed in the stack trace.</p>
     * @param description - a description of the step that was executed. The text
     * one wishes to display on the html report for the executed step.
     * @throws FrameworkException
     * @throws IOException
     */
    public void reportDoneEvent(String description) throws Exception {
        String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
        testReport.reportDoneEvent(stepName, description);
        System.out.println("Step: " + stepName + " has passed. " + description);
    }

    /**
     * <p>This method will report an exception message to the test steps html 
     * report that is generated at the end of each test run.</p>
     * <p>This method would get called in a catch block in order to capture the
     * error thrown and report it to the html report.</p>
     * <p>A line will be printed to the html report that contains the webdriver
     * exception message of the exception that was thrown. This can be helpful
     * for debugging or if something failed and it wasn't supposed to.</p>
     * <p>The step name param is autopopulated with
     * the last step that was executed in the stack trace.</p>
     * @param webDriverException - the exception that was thrown during execution of the method.
     * @return FrameworkException
     * @throws FrameworkException
     * @throws IOException
     */
    public Exception reportException(Exception webDriverException) throws Exception {
        String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
        testReport.reportFailEvent(stepName, webDriverException.getMessage());
        String message = "StepName: " + stepName + "\n ErrorMessage : " + webDriverException.getMessage();
        Exception assertionFailedException = new Exception(message);
        return assertionFailedException;
    }

}