package commonClasses.sharedUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import commonClasses.sharedUtils.ExtensionMethods;
import commonClasses.sharedUtils.ReportPath;
import reporting.framework.reporting.ReportSettings;
import reporting.framework.reporting.ReportThemeFactory;
import reporting.framework.reporting.TestRunnerReport;

public class TestRunner {
	
	Result result = null;
	List<String> testNameList =  new ArrayList<String>();
	List<String> exceptionList = new ArrayList<String>();
	List<String> messageList = new ArrayList<String>();
	public static int totalUnexecutedTests = 0;
	
	public TestRunner(Class<?>... classes) throws IOException
    {
		result = JUnitCore.runClasses(classes);
		updateConsole();
		getFailureInfo();
		updateTestRunnerReport();
    }
	
	public void updateConsole()
	{
		System.out.println("Total number of tests: " + result.getRunCount());
		System.out.println("Total number of tests failed: " + result.getFailureCount());
		System.out.println("Total run time in ms: " + result.getRunTime());
		System.out.println("" + result.getFailures());
	}
	
	private void getFailureInfo()
	{
		for(Failure failure : result.getFailures())
		{	
			System.out.println(failure.getTestHeader());
			System.out.println(failure.getException());
			System.out.println(failure.getMessage());
			
			testNameList.add(failure.getTestHeader());
			exceptionList.add(failure.getException().toString());
			messageList.add(failure.getTrace().toString());
		}
	}
	
	public void updateTestRunnerReport() throws IOException
	{
		String reportFilePath = ReportPath.getInstance().getReportPath();
	    ReportSettings reportSettings = new ReportSettings(reportFilePath, "TestRunnerReport");
	    
		float passNumber = ExtensionMethods.getTotalPassNumber(result.getFailureCount(), result.getRunCount() - totalUnexecutedTests);
		float passPercentage = ExtensionMethods.getThePassPercentage(passNumber, result.getRunCount() - totalUnexecutedTests);
		int nonFloatPassNumber = (int)passNumber;

	    reportSettings.GenerateExcelReports = false;
	    reportSettings.GenerateHtmlReports = true;
	    reportSettings.IncludeTestDataInReport = false;
	    reportSettings.TakeScreenshotFailedStep = true;
	    reportSettings.TakeScreenshotPassedStep = true;
	    TestRunnerReport testReport = new TestRunnerReport(reportSettings, ReportThemeFactory.GetReportsTheme(ReportThemeFactory.Theme.COOL));
	    //htmlReport.driver = browser;
	    testReport.InitializeReportTypes();
	    testReport.InitializeTestLog();
	    testReport.AddTestLogHeading("Test Result Report");
	    testReport.AddTestLogSubHeading(passPercentage);
	    testReport.AddTestLogTableHeadings();
	    //testReport.AddTestLogSection("Detailed Steps");
	    testReport.UpdateTestLog(nonFloatPassNumber, result.getFailureCount(), result.getRunCount()  - totalUnexecutedTests, result.getRunTime(), testNameList, exceptionList, messageList);
	}

}

