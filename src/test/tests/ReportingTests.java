package tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.report.LocalValidation;
import com.warnermedia.config.report.ReportFacade;
import com.warnermedia.config.report.ReportType;
import com.warnermedia.config.report.TestReport;
import com.warnermedia.config.report.TestReportHelper;
import com.warnermedia.config.settings.Environment;
import com.warnermedia.config.settings.LocalTest;

@Slf4j
public class ReportingTests {

	@Test(expectedExceptions=Exception.class)
	public void verifyHTMLReport() throws Exception {
		Environment environment = new Environment("www.google.com", "ref", "chrome", "mac", "Atlanta", true);
		LocalTest.setEnvironment(environment);
		System.out.println("Bureau is " + LocalTest.getEnvironment().getBureau());
		TestReportHelper helper = new TestReportHelper();
		TestReport report = helper.initialize(LocalReport.getHtmlReport(), "UnitTest1", "Chrome");
		ReportFacade.initializeReportType(ReportType.REPORT, report);
		ReportFacade.initializeReportType(ReportType.VALIDATIONS, report);
		LocalReport.getReport().reportDoneEvent(log, "Test Done Event.");
		LocalValidation.getValidations().assertionPass(log, "Test Pass");
		throw LocalValidation.getValidations().assertionFailed(log, "Test Fail");
	}

	@Test
	public void verifyHTMLReport5() throws Exception{
		Environment environment = new Environment("www.google.com", "ref", "chrome", "mac", "Atlanta", true);
		LocalTest.setEnvironment(environment);
		TestReportHelper helper = new TestReportHelper();
		TestReport report = helper.initialize(LocalReport.getHtmlReport(), "UnitTest1", "Chrome");
		ReportFacade.initializeReportType(ReportType.REPORT, report);
		ReportFacade.initializeReportType(ReportType.VALIDATIONS, report);

		LocalReport.getReport().reportDoneEvent(log, "Test Done Event.");
		LocalValidation.getValidations().assertionPass(log, "Test Pass");
	}

	@Test
	public void verifyHTMLReport2() throws Exception{
		Environment environment = new Environment("www.google.com", "ref", "chrome", "mac", "Atlanta", true);
		LocalTest.setEnvironment(environment);
		TestReportHelper helper = new TestReportHelper();
		TestReport report = helper.initialize(LocalReport.getHtmlReport(), "UnitTest1", "Chrome");
		ReportFacade.initializeReportType(ReportType.REPORT, report);
		ReportFacade.initializeReportType(ReportType.VALIDATIONS, report);

		LocalReport.getReport().reportDoneEvent(log, "Test Done Event.2");
		LocalValidation.getValidations().assertionPass(log, "Test Pass 2");
	}

	@Test
	public void verifyHTMLReport3() throws Exception{
		Environment environment = new Environment("www.google.com", "ref", "chrome", "mac", "Atlanta", true);
		LocalTest.setEnvironment(environment);
		TestReportHelper helper = new TestReportHelper();
		TestReport report = helper.initialize(LocalReport.getHtmlReport(), "UnitTest1", "Chrome");
		ReportFacade.initializeReportType(ReportType.REPORT, report);
		ReportFacade.initializeReportType(ReportType.VALIDATIONS, report);

		LocalReport.getReport().reportDoneEvent(log, "Test Done Event.3");
		LocalValidation.getValidations().assertionPass(log, "Test Pass 3");
	}

	@Test
	public void verifyHTMLReport4() throws Exception{
		Environment environment = new Environment("www.google.com", "ref", "chrome", "mac", "Atlanta", true);
		LocalTest.setEnvironment(environment);
		TestReportHelper helper = new TestReportHelper();
		TestReport report = helper.initialize(LocalReport.getHtmlReport(), "UnitTest1", "Chrome");
		ReportFacade.initializeReportType(ReportType.REPORT, report);
		ReportFacade.initializeReportType(ReportType.VALIDATIONS, report);

		LocalReport.getReport().reportDoneEvent(log, "Test Done Event.4");
		LocalValidation.getValidations().assertionPass(log, "Test Pass 4");
	}
}
