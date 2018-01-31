package common.utils.builders;

import java.io.File;
import java.io.IOException;
import common.utils.TestUtils;
import common.utils.managers.LocalReport;

public class ReportPathBuilder {

    private static String reportPath;
    private static ReportPathBuilder instance = null;
    public final static String RESULT_FOLDER = "Results";

    protected ReportPathBuilder() throws IOException {
        File directory = new File(String.valueOf(RESULT_FOLDER));
        if (!directory.exists()) {
            directory.mkdir();
        }
        reportPath = getLocalReportPath();
        File _file = new File(reportPath);
        _file.mkdir();
        _file.createNewFile();

    }

    private String getLocalReportPath() {
        String reportPath = TestUtils.getRelativePath() + "/" + RESULT_FOLDER + "/" + "Result_" + TestUtils.getTimeStamp();
        LocalReport.setFilePath(reportPath);
        return reportPath;
    }

    public String getReportPath() {
        return reportPath;
    }

    public static ReportPathBuilder getInstance() throws IOException {
        if (instance == null) {
            instance = new ReportPathBuilder();
        }
        return instance;
    }
}