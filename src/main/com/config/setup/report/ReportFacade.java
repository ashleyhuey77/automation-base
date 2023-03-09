package com.config.setup.report;

import com.config.TestException;

public class ReportFacade {
	
	public static void initializeReportType(ReportType type, TestReport getHTMLReport) throws TestException {
		switch (type) {
			case REPORT:
				TestReport report = getHTMLReport;
				LocalReport.setHtmlReport(report);
				LocalReport.setReport(new ReportHelper(LocalReport.getHtmlReport()));
				break;
			case VALIDATIONS:
				LocalValidation.setValidations(new ValidationsHelper(LocalReport.getHtmlReport()));
				break;
			default:
				throw new TestException(
						"User did not supply the correct Report Type. Unable to determine which report to initialize for testing.");
		}
	}
}
