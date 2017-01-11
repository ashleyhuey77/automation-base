package commonClasses.sharedUtils;

import java.io.File;
import java.io.IOException;

public class ReportPath {

	private static ReportPath instance = null;
    private static String reportPath;

    protected ReportPath() throws IOException
    {
        reportPath = getLocalReportPath();
        File _file = new File(reportPath);
        _file.mkdir();
        _file.createNewFile();

    }

    /// <summary>
    /// Get the current report folder
    /// @return
    /// </summary>
    public String getReportPath()
    {

        return reportPath;

    }

    private String getLocalReportPath()
    {
            //return TestUtils.RelativePath + File.separator + FrameworkConstants.RESULT_FOLDER + File.separator + "Result_" + TestUtils.TimeStamp;
    	String reportPath = TestUtils.getRelativePath() /*"/Users/ashleyhuey/Documents/workspace/Automation.Tests"*/+ "/" + FrameworkConstants.RESULT_FOLDER + "/" + "Result_" + TestUtils.getTimeStamp();
            return reportPath;
    }

    public static ReportPath getInstance() throws IOException
    {
                if (instance == null)
                {
                    instance = new ReportPath();
                }
                return instance;
    }
}
