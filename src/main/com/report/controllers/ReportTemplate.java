package com.report.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.config.TestException;
import com.report.Util;
import com.report.models.ReportSettings;
import com.report.models.TestContent;
import com.report.models.TestStepContent;

public class ReportTemplate implements ReportContent {

	protected ReportSettings reportSettings;

	private ReportContent content;

	private int nStepsPassed = 0;

	private int nStepsFailed = 0;

	private String stepNumber;

	private ArrayList<ReportContent> reportTypes = new ArrayList<>();

	public ReportTemplate(ReportSettings reportSettings) throws TestException {
		try {
			this.reportSettings = reportSettings;
			switch (reportSettings.getReportType()) {
				case TEST_RESULTS_REPORT:
					this.content = new TestResultsReport(reportSettings);
					break;
				default:
					break;
			}
			initializeReportTypes();
			createTestReportFile();
		} catch (Exception e) {

		}
	}

	public void createTestReportFile() throws TestException {
		for (int i = 0; i < this.reportTypes.size(); i++) {
			content.createTestReportFile();
		}
	}

	@Override
	public void addBaseReportContent(TestContent report) throws TestException {
		for (int i = 0; i < this.reportTypes.size(); i++) {
			content.addBaseReportContent(report);
		}
	}

	@Override
	public void addResultContent(TestStepContent testStep) throws TestException {
		String reportPath;
		String str = null;
		String msg = "Screenshots";
		if (this.stepNumber == null) {
			this.stepNumber = "1";
		}
		switch (testStep.status()) {
			case FAIL:
				nStepsFailed = nStepsFailed + 1;
				if (this.reportSettings.TakeScreenshotFailedStep) {
					str = this.reportSettings.getReportName() + "_"
							+ Util.getCurrentFormattedTime().replace(" ", "_").replace(":", "-") + ".png";
					reportPath = this.reportSettings.getReportPath() + Util.getFileSeparator() + msg
							+ Util.getFileSeparator() + str;
					this.takeScreenshot(reportPath);
				}
				break;
			case PASS:
				nStepsPassed = nStepsPassed + 1;
				if (this.reportSettings.TakeScreenshotPassedStep) {
					str = this.reportSettings.getReportName() + "_"
							+ Util.getCurrentFormattedTime().replace(" ", "_").replace(":", "-") + ".png";
					reportPath = this.reportSettings.getReportPath() + Util.getFileSeparator() + msg
							+ Util.getFileSeparator() + str;
					this.takeScreenshot(reportPath);
				}
				break;
			default:
				break;

		}
		for (int i = 0; i < this.reportTypes.size(); i++) {
			TestStepContent tsc = new TestStepContent(this.stepNumber, testStep.name(), testStep.description(),
					testStep.status(), str);
			this.content.addResultContent(tsc);
		}
		int sNumber = Integer.parseInt(this.stepNumber) + 1;
		this.stepNumber = Integer.toString(sNumber);
	}

	@Override
	public void addFailDetailsContent(TestStepContent content) throws TestException {
		for (int i = 0; i < this.reportTypes.size(); i++) {
			this.content.addFailDetailsContent(content);
		}
	}

	protected void takeScreenshot(String screenshotPath) throws TestException {
		throw new UnsupportedOperationException();
	}

	private void initializeReportTypes() throws IOException {
		if (this.reportSettings.GENERATE_HTML_REPORTS) {
			new File(this.reportSettings.getReportPath() + Util.getFileSeparator() + "HTML Results");
			TestResultsReport htmlReport = new TestResultsReport(this.reportSettings);
			this.reportTypes.add(htmlReport);
		}
		File screenshots = new File(this.reportSettings.getReportPath() + Util.getFileSeparator() + "Screenshots");
		screenshots.mkdir();
		screenshots.createNewFile();
	}
}
