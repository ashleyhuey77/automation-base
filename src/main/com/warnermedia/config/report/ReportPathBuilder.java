package com.warnermedia.config.report;

import java.io.File;
import com.warnermedia.config.TestException;
import com.warnermedia.page.utils.date.DatePicker;
import com.warnermedia.page.utils.date.PresentDate;
import com.warnermedia.utils.TestUtils;

public class ReportPathBuilder {

	public static final String RESULT_FOLDER = "Results";
	private static ReportPathBuilder instance = null;
	private static String reportPath;

	protected ReportPathBuilder() throws TestException {
		try {
			File directory = new File(String.valueOf(RESULT_FOLDER));
			if (!directory.exists()) {
				directory.mkdir();
			}
			if (LocalReport.getFilePath() == null) {
				reportPath = getLocalReportPath();
			}
			File _file = new File(reportPath);
			_file.mkdir();
			_file.createNewFile();
		} catch (Exception e) {

		}

	}
	
	private String getLocalReportPath() throws TestException {
		DatePicker picker = new PresentDate();
		String rPath = TestUtils.getRelativePath() + "/" + RESULT_FOLDER + "/" + "Result_"
				+ picker.getDate("MM_dd_yyyy_HHmmss");
		LocalReport.setFilePath(rPath);
		return rPath;
	}
	
	public String getReportPath() {
		return reportPath;
	}

	public static ReportPathBuilder getInstance() throws TestException {
		if (instance == null) {
			instance = new ReportPathBuilder();
		}
		return instance;
	}
}