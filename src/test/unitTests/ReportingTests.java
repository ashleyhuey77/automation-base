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
      LocalValidation.getValidations().assertionFailed("Test Fail");
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
