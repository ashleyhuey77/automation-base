package common.utils.builders;

import java.io.File;
import common.base.interfaces.DatePicker;
import common.base.methods.PresentDate;
import common.utils.TestUtils;
import common.utils.managers.LocalReport;
import log.TestException;

public class ReportPathBuilder {

	public static final String RESULT_FOLDER = "Results";
	private static String reportPath;
	private static ReportPathBuilder instance = null;

	protected ReportPathBuilder() throws TestException {
		try {
			File directory = new File(String.valueOf(RESULT_FOLDER));
			if (!directory.exists()) {
				directory.mkdir();
			}
			reportPath = getLocalReportPath();
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