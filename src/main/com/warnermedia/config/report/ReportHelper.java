package com.warnermedia.config.report;


import java.io.IOException;
import com.warnermedia.config.TestException;

public class ReportHelper {
	
	private TestReport testReport;
    
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
    public void reportDoneEvent(String description) throws TestException {
        String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
        testReport.reportDoneEvent(stepName, description);
        synchronized (System.out) {
        	System.out.println("Step: " + stepName + " has passed. " + description);
        }
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
    public TestException reportException(Exception webDriverException) throws TestException {
        String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String errorMessage = null;
        if (webDriverException instanceof NullPointerException) {
        	errorMessage = "NullPointerException was thrown. Please check for null values.";
        } else {
        	errorMessage = webDriverException.getMessage();
        }
        testReport.reportFailEvent(stepName, errorMessage);
        String message = "StepName: " + stepName + "\n ErrorMessage : " + errorMessage;
        //ConsoleHelper.analyzeLog();
        return new TestException(message);
    }

}