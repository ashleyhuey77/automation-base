package com.report.models;

import com.report.controllers.ReportType;

public class ReportSettings {

	public Boolean GENERATE_HTML_REPORTS = true;

	public Boolean IncludeTestDataInReport = true;

	public Boolean TakeScreenshotFailedStep = true;

	public Boolean TakeScreenshotPassedStep = true;

	private String reportName;

	private String reportPath;

	private ReportType type;

	public String getReportName() {
		return this.reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public void setReportType(ReportType type) {
		this.type = type;
	}

	public ReportType getReportType() {
		return type;
	}

	public String getReportPath() {
		return this.reportPath;
	}

	public void setReportPath(String value) {
		this.reportPath = value;
	}

	public ReportSettings(String reportPath, String reportName, ReportType type) {
		this.setReportPath(reportPath);
		this.setReportName(reportName);
		this.setReportType(type);

	}
}
