package com.warnermedia.config.report;

import java.io.IOException;
import java.util.logging.Level;
import com.warnermedia.config.TestException;
import com.warnermedia.utils.ConsoleHelper;
import com.warnermedia.utils.Log;
import junit.framework.AssertionFailedError;

public class ValidationsHelper {

	private TestReport testReport;

	public ValidationsHelper(TestReport htmlReport) {
		this.testReport = htmlReport;
	}

	/**
	 * <p>
	 * This method will report a pass event to the
	 * test steps html report that is generated at the
	 * end of each test run.
	 * </p>
	 * <p>
	 * This method would get called when it is
	 * necessary to document a passed step to the html
	 * report along with a screencap of the passed
	 * event.
	 * </p>
	 * <p>
	 * A line will be printed to the html report in
	 * the following format:
	 * </p>
	 * <p>
	 * Step: [stepName] has passed. [message]
	 * </p>
	 * <p>
	 * The step name param in the above example is
	 * autopopulated with the last step that was
	 * executed in the stack trace.
	 * </p>
	 * 
	 * @param message
	 *            - a description of the step that was
	 *            executed. The text one wishes to
	 *            display on the html report for the
	 *            executed step.
	 * @throws FrameworkException
	 * @throws IOException
	 */
	public void assertionPass(String message) throws TestException {
		String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
		testReport.reportPassEvent(stepName, message);
		synchronized (System.out) {
			System.out.println("Step: " + stepName + " has passed. " + message);
		}
	}

	/**
	 * <p>
	 * This method will report a fail event to the
	 * test steps html report that is generated at the
	 * end of each test run.
	 * </p>
	 * <p>
	 * This method would get called when it is
	 * necessary to document a failed step to the html
	 * report along with a screencap of the failed
	 * event.
	 * </p>
	 * <p>
	 * A line will be printed to the html report in
	 * the following format:
	 * </p>
	 * <p>
	 * StepName: [stepName]. ErrorMessage: [message]
	 * </p>
	 * <p>
	 * The step name param in the above example is
	 * autopopulated with the last step that was
	 * executed in the stack trace.
	 * </p>
	 * 
	 * @param message
	 *            - a description of the step that was
	 *            executed. The text one wishes to
	 *            display on the html report for the
	 *            executed step.
	 * @throws FrameworkException
	 * @throws IOException
	 */
	public TestException assertionFailed(String message) throws TestException {
		String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
		AssertionFailedError assertionFailedError = getAssertionFailedErrorObject(stepName, message);
		testReport.reportFailEvent(stepName, message);
		ConsoleHelper.analyzeLog();
		Log.get().log(Level.SEVERE, assertionFailedError.toString());
		return reportAssertionFailed(assertionFailedError);
	}

	private AssertionFailedError getAssertionFailedErrorObject(String stepName, String errorMessage) {
		String message = "StepName: " + stepName + "\n ErrorMessage: " + errorMessage;
		return new AssertionFailedError(message);
	}

	private TestException reportAssertionFailed(AssertionFailedError assertionFailedError) {
		TestException exception = new TestException(assertionFailedError.toString());
		Log.get().log(Level.SEVERE, exception.getMessage(), exception);
		return exception;
	}

}