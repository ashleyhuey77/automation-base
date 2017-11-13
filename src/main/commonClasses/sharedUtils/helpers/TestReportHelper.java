package commonClasses.sharedUtils.helpers;

import commonClasses.sharedUtils.TestReport;
import commonClasses.sharedUtils.builders.ReportPathBuilder;
import commonClasses.sharedUtils.managers.LocalTest;
import reporting.report.ReportThemeFactory;
import reporting.report.dataObjects.ReportSettings;
import reporting.report.dataObjects.TestContent;
import reporting.report.enums.ReportType;

public class TestReportHelper {
	public TestReport initialize(TestReport htmlReport, String testScenarioName, String browserName) throws Exception {
        try {
            String reportFilePath = ReportPathBuilder.getInstance().getReportPath();
            String reportFileName = testScenarioName + "_" + browserName;
            ReportSettings reportSettings = new ReportSettings(reportFilePath, reportFileName, ReportType.TEST_RESULTS_REPORT);
            TestContent tContent = new TestContent(testScenarioName, browserName, LocalTest.getEnvironment().getApplicationUrl());

            reportSettings.GenerateHtmlReports = true;
            reportSettings.IncludeTestDataInReport = false;
            reportSettings.TakeScreenshotFailedStep = true;
            reportSettings.TakeScreenshotPassedStep = true;
            htmlReport = new TestReport(reportSettings, ReportThemeFactory.GetReportsTheme(ReportThemeFactory.Theme.COOL));
            htmlReport.addBaseReportContent(tContent);

            return htmlReport;
        } catch (Exception ex) {
            throw ex;
        }

    }
}
