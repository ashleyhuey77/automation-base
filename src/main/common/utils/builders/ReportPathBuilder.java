package common.utils.builders;

import java.io.File;
import common.basePage.interfaces.DatePicker;
import common.basePage.methods.PresentDate;
import common.utils.TestUtils;
import common.utils.managers.LocalReport;

public class ReportPathBuilder {

    private static String reportPath;
    private static ReportPathBuilder instance = null;
    public final static String RESULT_FOLDER = "Results";

    protected ReportPathBuilder() throws Exception {
        File directory = new File(String.valueOf(RESULT_FOLDER));
        if (!directory.exists()) {
            directory.mkdir();
        }
        reportPath = getLocalReportPath();
        File _file = new File(reportPath);
        _file.mkdir();
        _file.createNewFile();

    }

    private String getLocalReportPath() throws Exception {
    		DatePicker picker = new PresentDate();
        String reportPath = TestUtils.getRelativePath() + "/" + RESULT_FOLDER + "/" + "Result_" + picker.getDate("MM_dd_yyyy_HHmmss");
        LocalReport.setFilePath(reportPath);
        return reportPath;
    }

    public String getReportPath() {
        return reportPath;
    }

    public static ReportPathBuilder getInstance() throws Exception {
        if (instance == null) {
            instance = new ReportPathBuilder();
        }
        return instance;
    }
}