package commonClasses.sharedUtils.builders;

import java.io.File;
import java.io.IOException;

import commonClasses.sharedUtils.TestUtils;

public class ReportPathBuilder {

    private static String reportPath;
    private static ReportPathBuilder instance = null;
    public final static String RESULT_FOLDER = "Results";

    protected ReportPathBuilder() throws IOException
    {
    	File directory = new File(String.valueOf(RESULT_FOLDER));
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
    	String reportPath = TestUtils.getRelativePath() /*"/Users/ashleyhuey/Documents/workspace/Automation.Tests"*/+ "/" + RESULT_FOLDER + "/" + "Result_" + TestUtils.getTimeStamp();
            return reportPath;
    }
    
    public String getReportPath()
    {
        return reportPath;
    }
    
    public static ReportPathBuilder getInstance() throws IOException
    {
    	if (instance == null)
        {
    		instance = new ReportPathBuilder();
        }
        return instance;
    }
}
