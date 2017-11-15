package unitTests;

import org.testng.annotations.Test;
import commonClasses.sharedUtils.TestReport;
import commonClasses.sharedUtils.builders.Environment;
import commonClasses.sharedUtils.enums.ReportType;
import commonClasses.sharedUtils.facades.HelperFacade;
import commonClasses.sharedUtils.helpers.TestReportHelper;
import commonClasses.sharedUtils.managers.LocalReport;
import commonClasses.sharedUtils.managers.LocalTest;
import commonClasses.sharedUtils.managers.LocalValidation;

public class ReportingTests {
  @Test
  public void verifyHTMLReport() throws Exception {
	  Environment environment = new Environment("www.google.com", "ref", "chrome", "mac", true);
	  LocalTest.setEnvironment(environment);
	  TestReportHelper helper = new TestReportHelper();
	  TestReport report = helper.initialize(LocalReport.getHtmlReport(), "UnitTest1", "Chrome");
      HelperFacade.initializeReportType(ReportType.REPORT, report);
      HelperFacade.initializeReportType(ReportType.VALIDATIONS, report);
      
      LocalReport.getReport().reportDoneEvent("Test Done Event.");
      LocalValidation.getValidations().assertionPass("Test Pass");
      LocalValidation.getValidations().assertionFailed("Expected condition failed: waiting for page.newstronPages.EditItemSummaryPage$1@35885587 (tried for 120 second(s) with 500 MILLISECONDS interval) Build info: version: '3.6.0', revision: '6fbf3ec767', time: '2017-09-27T15:28:36.4Z' System info: host: 'ashleys-mbp.turner.com', ip: '10.189.67.222', os.name: 'Mac OS X', os.arch: 'x86_64', os.version: '10.12.6', java.version: '1.8.0_101' Driver info: org.openqa.selenium.chrome.ChromeDriver Capabilities [{mobileEmulationEnabled=false, hasTouchScreen=false, platform=MAC, acceptSslCerts=true, webStorageEnabled=true, browserName=chrome, takesScreenshot=true, javascriptEnabled=true, platformName=MAC, setWindowRect=true, unexpectedAlertBehaviour=, applicationCacheEnabled=false, rotatable=false, networkConnectionEnabled=false, chrome={chromedriverVersion=2.33.506106 (8a06c39c4582fbfbab6966dbb1c38a9173bfb1a2), userDataDir=/var/folders/bz/wgk5xb716j73jwmmn_1bpq8c0000gn/T/.org.chromium.Chromium.Ixw6vr}, takesHeapSnapshot=true, pageLoadStrategy=normal, unhandledPromptBehavior=, databaseEnabled=false, handlesAlerts=true, version=62.0.3202.94, browserConnectionEnabled=false, nativeEvents=true, locationContextEnabled=true, cssSelectorsEnabled=true}] Session ID: baa092d7f75039f62cde9d97e905379d");
  }
  
  @Test
  public void verifyHTMLReport5() throws Exception {
	  Environment environment = new Environment("www.google.com", "ref", "chrome", "mac", true);
	  LocalTest.setEnvironment(environment);
	  TestReportHelper helper = new TestReportHelper();
	  TestReport report = helper.initialize(LocalReport.getHtmlReport(), "UnitTest1", "Chrome");
      HelperFacade.initializeReportType(ReportType.REPORT, report);
      HelperFacade.initializeReportType(ReportType.VALIDATIONS, report);
      
      LocalReport.getReport().reportDoneEvent("Test Done Event.");
      LocalValidation.getValidations().assertionPass("Test Pass");
  }
  
  @Test
  public void verifyHTMLReport2() throws Exception {
	  Environment environment = new Environment("www.google.com", "ref", "chrome", "mac", true);
	  LocalTest.setEnvironment(environment);
	  TestReportHelper helper = new TestReportHelper();
	  TestReport report = helper.initialize(LocalReport.getHtmlReport(), "UnitTest1", "Chrome");
      HelperFacade.initializeReportType(ReportType.REPORT, report);
      HelperFacade.initializeReportType(ReportType.VALIDATIONS, report);
      
      LocalReport.getReport().reportDoneEvent("Test Done Event.2");
      LocalValidation.getValidations().assertionPass("Test Pass 2");
  }
  
  @Test
  public void verifyHTMLReport3() throws Exception {
	  Environment environment = new Environment("www.google.com", "ref", "chrome", "mac", true);
	  LocalTest.setEnvironment(environment);
	  TestReportHelper helper = new TestReportHelper();
	  TestReport report = helper.initialize(LocalReport.getHtmlReport(), "UnitTest1", "Chrome");
      HelperFacade.initializeReportType(ReportType.REPORT, report);
      HelperFacade.initializeReportType(ReportType.VALIDATIONS, report);
      
      LocalReport.getReport().reportDoneEvent("Test Done Event.3");
      LocalValidation.getValidations().assertionPass("Test Pass 3");
  }
  
  @Test
  public void verifyHTMLReport4() throws Exception {
	  Environment environment = new Environment("www.google.com", "ref", "chrome", "mac", true);
	  LocalTest.setEnvironment(environment);
	  TestReportHelper helper = new TestReportHelper();
	  TestReport report = helper.initialize(LocalReport.getHtmlReport(), "UnitTest1", "Chrome");
      HelperFacade.initializeReportType(ReportType.REPORT, report);
      HelperFacade.initializeReportType(ReportType.VALIDATIONS, report);
      
      LocalReport.getReport().reportDoneEvent("Test Done Event.4");
      LocalValidation.getValidations().assertionPass("Test Pass 4");
  }
}
