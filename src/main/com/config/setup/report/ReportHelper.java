package com.config.setup.report;


import java.io.IOException;

import com.config.TestException;
import com.utils.devtools.LocalErrorCallback;
import com.utils.ConsoleDecoration;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
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
     * @throws IOException
     */
    public void reportDoneEvent(Logger log, String description) throws TestException {
        String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
        testReport.reportDoneEvent(stepName, description);
        log.info("{}{}Step: {} has passed. {}{}", ConsoleDecoration.WHITE_TEXT.value, ConsoleDecoration.CYAN_BACKGROUND.value, stepName, description, ConsoleDecoration.RESET.value);
    }

    public void reportDoneEvent(Logger log, String description, String value) throws TestException {
        String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String newDes = null;
        if (description.contains("{0}")) {
            newDes = description.replace("{0}", value);
        } else {
            newDes = description;
        }
        testReport.reportDoneEvent(stepName, newDes);
        log.info("Step: {0} has passed. {1}", stepName, newDes);
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
        if (LocalErrorCallback.isLoggerOn()) {
            LocalErrorCallback.get().executeWith(() -> log.info("{}{}Console error task has completed successfully.{}", ConsoleDecoration.CYAN_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, ConsoleDecoration.RESET.value));
        }
        testReport.reportFailEvent(stepName, errorMessage);
        String message = "StepName: " + stepName + "\n ErrorMessage : " + errorMessage;
        return new TestException(message);
    }

}