package commonClasses.sharedUtils;

import java.io.File;
import java.io.IOException;

public class ReportPath {

    private static String reportPath;
    private static ReportPath instance = null;

    protected ReportPath() throws IOException
    {
    	File directory = new File(String.valueOf(FrameworkConstants.RESULT_FOLDER));
    	if (!directory.exists())
    	{
    		directory.mkdir();
    	}
        reportPath = getLocalReportPath();
        File _file = new File(reportPath);
        _file.mkdir();
        _file.createNewFile();

    }

    private String getLocalReportPath()
    {
            //return TestUtils.RelativePath + File.separator + FrameworkConstants.RESULT_FOLDER + File.separator + "Result_" + TestUtils.TimeStamp;
    	String reportPath = TestUtils.getRelativePath() /*"/Users/ashleyhuey/Documents/workspace/Automation.Tests"*/+ "/" + FrameworkConstants.RESULT_FOLDER + "/" + "Result_" + TestUtils.getTimeStamp();
            return reportPath;
    }
    
    public String getReportPath()
    {
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
