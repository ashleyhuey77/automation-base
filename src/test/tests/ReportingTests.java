package tests;

import org.testng.annotations.Test;
import common.utils.TestReport;
import common.utils.builders.Environment;
import common.utils.enums.ReportType;
import common.utils.facades.HelperFacade;
import common.utils.helpers.TestReportHelper;
import common.utils.managers.LocalReport;
import common.utils.managers.LocalTest;
import common.utils.managers.LocalValidation;
import log.TestException;

public class ReportingTests {
  
	@Test(expectedExceptions=Exception.class)
  public void verifyHTMLReport() throws TestException {
	  Environment environment = new Environment("www.google.com", "ref", "chrome", "mac", "Atlanta", true);
	  LocalTest.setEnvironment(environment);
	  System.out.println("Bureau is " + LocalTest.getEnvironment().getBureau());
	  TestReportHelper helper = new TestReportHelper();
	  TestReport report = helper.initialize(LocalReport.getHtmlReport(), "UnitTest1", "Chrome");
      HelperFacade.initializeReportType(ReportType.REPORT, report);
      HelperFacade.initializeReportType(ReportType.VALIDATIONS, report);
      
      LocalReport.getReport().reportDoneEvent("Test Done Event.");
      LocalValidation.getValidations().assertionPass("Test Pass");
      LocalValidation.getValidations().assertionFailed("Test Fail");
  }
  
  @Test
  public void verifyHTMLReport5() throws TestException {
	  Environment environment = new Environment("www.google.com", "ref", "chrome", "mac", "Atlanta", true);
	  LocalTest.setEnvironment(environment);
	  TestReportHelper helper = new TestReportHelper();
	  TestReport report = helper.initialize(LocalReport.getHtmlReport(), "UnitTest1", "Chrome");
      HelperFacade.initializeReportType(ReportType.REPORT, report);
      HelperFacade.initializeReportType(ReportType.VALIDATIONS, report);
      
      LocalReport.getReport().reportDoneEvent("Test Done Event.");
      LocalValidation.getValidations().assertionPass("Test Pass");
  }
  
  @Test
  public void verifyHTMLReport2() throws TestException {
	  Environment environment = new Environment("www.google.com", "ref", "chrome", "mac", "Atlanta", true);
	  LocalTest.setEnvironment(environment);
	  TestReportHelper helper = new TestReportHelper();
	  TestReport report = helper.initialize(LocalReport.getHtmlReport(), "UnitTest1", "Chrome");
      HelperFacade.initializeReportType(ReportType.REPORT, report);
      HelperFacade.initializeReportType(ReportType.VALIDATIONS, report);
      
      LocalReport.getReport().reportDoneEvent("Test Done Event.2");
      LocalValidation.getValidations().assertionPass("Test Pass 2");
  }
  
  @Test
  public void verifyHTMLReport3() throws TestException {
	  Environment environment = new Environment("www.google.com", "ref", "chrome", "mac", "Atlanta", true);
	  LocalTest.setEnvironment(environment);
	  TestReportHelper helper = new TestReportHelper();
	  TestReport report = helper.initialize(LocalReport.getHtmlReport(), "UnitTest1", "Chrome");
      HelperFacade.initializeReportType(ReportType.REPORT, report);
      HelperFacade.initializeReportType(ReportType.VALIDATIONS, report);
      
      LocalReport.getReport().reportDoneEvent("Test Done Event.3");
      LocalValidation.getValidations().assertionPass("Test Pass 3");
  }
  
  @Test
  public void verifyHTMLReport4() throws TestException {
	  Environment environment = new Environment("www.google.com", "ref", "chrome", "mac", "Atlanta", true);
	  LocalTest.setEnvironment(environment);
	  TestReportHelper helper = new TestReportHelper();
	  TestReport report = helper.initialize(LocalReport.getHtmlReport(), "UnitTest1", "Chrome");
      HelperFacade.initializeReportType(ReportType.REPORT, report);
      HelperFacade.initializeReportType(ReportType.VALIDATIONS, report);
      
      LocalReport.getReport().reportDoneEvent("Test Done Event.4");
      LocalValidation.getValidations().assertionPass("Test Pass 4");
  }
}
