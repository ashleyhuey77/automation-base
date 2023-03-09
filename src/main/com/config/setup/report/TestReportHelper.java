package com.config.setup.report;

import com.config.setup.app.LocalTest;
import com.report.controllers.ReportType;
import com.report.models.ReportSettings;
import com.report.models.TestContent;
import com.config.TestException;

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
