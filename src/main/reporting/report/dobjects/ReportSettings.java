package reporting.report.dobjects;

import reporting.report.enums.ReportType;

public class ReportSettings {

	public Boolean GENERATE_HTML_REPORTS = true;

	public Boolean IncludeTestDataInReport = true;

	public Boolean TakeScreenshotFailedStep = true;

	public Boolean TakeScreenshotPassedStep = true;

	private String dateFormatString = "dd-MMM-yyyy hh:mm:ss a";

	private String reportName;

	private String reportPath;

	private ReportType type;

	public String getDateFormatString() {
		return this.dateFormatString;
	}

	public void setDateFormatString(String value) {
		this.dateFormatString = value;
	}

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

	public ReportSettings(String reportPath, String reportName) {
		this.setReportPath(reportPath);
		this.setReportName(reportName);

	}
}
