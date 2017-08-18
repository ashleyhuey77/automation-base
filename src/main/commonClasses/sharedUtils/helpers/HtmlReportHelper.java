package commonClasses.sharedUtils.helpers;

import org.openqa.selenium.WebDriver;

import commonClasses.sharedUtils.HtmlReport;
import commonClasses.sharedUtils.builders.ReportPathBuilder;
import reporting.framework.reporting.ReportSettings;
import reporting.framework.reporting.ReportThemeFactory;

public class HtmlReportHelper {
	
	public HtmlReport initialize(HtmlReport htmlReport, WebDriver browser, String testScenarioName, String browserName) throws Exception
	{
		try
    	{
	        String reportFilePath = ReportPathBuilder.getInstance().getReportPath();
	        String reportFileName = testScenarioName + "_" + browserName;
	        ReportSettings reportSettings = new ReportSettings(reportFilePath, reportFileName);
	
	        reportSettings.GenerateExcelReports = false;
	        reportSettings.GenerateHtmlReports = true;
	        reportSettings.IncludeTestDataInReport = false;
	        reportSettings.TakeScreenshotFailedStep = true;
	        reportSettings.TakeScreenshotPassedStep = true;
	        htmlReport = new commonClasses.sharedUtils.HtmlReport(reportSettings, ReportThemeFactory.GetReportsTheme(ReportThemeFactory.Theme.COOL));
	        htmlReport.driver = browser;
	        htmlReport.InitializeReportTypes();
	        htmlReport.InitializeTestLog();
	        htmlReport.AddTestLogHeading(testScenarioName);
	        htmlReport.AddTestLogSubHeading("Browser", browserName, "URL", "http://newstron-ref.turner.com/newstron/newstron.html");
	        htmlReport.AddTestLogTableHeadings();
	        htmlReport.AddTestLogSection("Detailed Steps");
	
	        return htmlReport;
    	}
    	catch (Exception ex) 
    	{
			throw ex;
		}

	}

}
