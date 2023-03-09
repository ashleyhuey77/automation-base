package com.config.setup.report;

import java.io.IOException;
import com.config.TestException;
import com.utils.ConsoleDecoration;
import org.slf4j.Logger;

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
	 * @throws IOException
	 */
	public void assertionPass(Logger log, String message) throws TestException {
		String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
		testReport.reportPassEvent(stepName, message);
		log.info("{}{}Step: {} has passed. {}{}", ConsoleDecoration.WHITE_TEXT.value, ConsoleDecoration.GREEN_BACKGROUND.value, stepName, message, ConsoleDecoration.RESET.value);
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
	 * @throws IOException
	 */
	public TestException assertionFailed(Logger log, String message) throws TestException {
		String stepName = Thread.currentThread().getStackTrace()[2].getMethodName();
		AssertionError assertionFailedError = getAssertionFailedErrorObject(stepName, message);
		testReport.reportFailEvent(stepName, message);
		log.error("{}{}{}{}", ConsoleDecoration.WHITE_TEXT.value, ConsoleDecoration.RED_BACKGROUND.value, assertionFailedError.toString(), ConsoleDecoration.RESET.value);
		return reportAssertionFailed(assertionFailedError);
	}

	private AssertionError getAssertionFailedErrorObject(String stepName, String errorMessage) {
		String message = "StepName: " + stepName + "\n ErrorMessage: " + errorMessage;
		return new AssertionError(message);
	}

	private TestException reportAssertionFailed(AssertionError assertionFailedError) {
		TestException exception = new TestException(assertionFailedError.toString());
		return exception;
	}

}