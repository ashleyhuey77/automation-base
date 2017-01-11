package reporting.framework.reporting;

import java.io.IOException;
import java.util.List;

import reporting.framework.utilities.FrameworkException;

interface ReportType {
	
	void AddResultSummaryFooter(String totalExecutionTime, int nTestsPassed, int nTestsFailed) throws FrameworkException;

    void AddResultSummaryHeading(String heading) throws FrameworkException;

    void AddResultSummarySubHeading(String subHeading1, String subHeading2, String subHeading3, String subHeading4) throws FrameworkException;

    void AddResultSummaryTableHeadings() throws FrameworkException;

    void AddTestLogFooter(String executionTime, int nStepsPassed, int nStepsFailed) throws FrameworkException;

    void AddTestLogHeading(String heading) throws FrameworkException;

    void AddTestLogSection(String section) throws FrameworkException;

    void AddTestLogSubHeading(String subHeading1, String subHeading2, String subHeading3, String subHeading4) throws FrameworkException;

    void AddTestLogSubSection(String subSection) throws FrameworkException;

    void AddTestLogTableHeadings() throws FrameworkException;

    void InitializeResultSummary() throws IOException, FrameworkException;

    void InitializeTestLog() throws IOException, FrameworkException;

    void UpdateResultSummary(String scenarioName, String testcaseName, String testcaseDescription, String executionTime, String testStatus) throws FrameworkException;

    void UpdateTestLog(String stepNumber, String stepName, String stepDescription, Status stepStatus, String screenshotName) throws FrameworkException;

	void UpdateTestLog(int totalPassCount, int totalFailCount, int totalRunCount, long totalRunTime, List<String> testNameList, List<String> exceptionList, List<String> messageList)
			throws FrameworkException;

	void AddTestLogSubHeading(Float subHeading1) throws FrameworkException;

}
