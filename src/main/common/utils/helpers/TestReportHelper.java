package common.utils.helpers;

import common.utils.TestReport;
import common.utils.builders.ReportPathBuilder;
import common.utils.managers.LocalTest;
import log.TestException;
import reporting.report.dobjects.ReportSettings;
import reporting.report.dobjects.TestContent;
import reporting.report.enums.ReportType;

public class TestReportHelper {
	public TestReport initialize(TestReport htmlReport, String testScenarioName, String browserName) throws TestException {
            String reportFilePath = ReportPathBuilder.getInstance().getReportPath();
            String reportFileName = testScenarioName + "_" + browserName;
            ReportSettings reportSettings = new ReportSettings(reportFilePath, reportFileName, ReportType.TEST_RESULTS_REPORT);
            TestContent tContent = new TestContent(testScenarioName, browserName, LocalTest.getEnvironment().getApplicationUrl());

            reportSettings.GENERATE_HTML_REPORTS = true;
            reportSettings.IncludeTestDataInReport = false;
            reportSettings.TakeScreenshotFailedStep = true;
            reportSettings.TakeScreenshotPassedStep = true;
            htmlReport = new TestReport(reportSettings);
            htmlReport.addBaseReportContent(tContent);

            return htmlReport;

    }
}
