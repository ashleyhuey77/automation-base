package reporting.framework.reporting;

public class ReportSettings {
	private int _logLevel = 4;

    public Boolean GenerateExcelReports = false;

    public Boolean GenerateHtmlReports = true;

    public Boolean IncludeTestDataInReport = true;

    public Boolean TakeScreenshotFailedStep = true;

    public Boolean TakeScreenshotPassedStep = false;

    private String _dateFormatString = "dd-MMM-yyyy hh:mm:ss a";
    
    public String projectName;
    
    public String reportName;
    
    public String reportPath;
    
    public String getDateFormatString()
    {
    	return this._dateFormatString;
    }

    public void setDateFormatString(String value)
    {
       this._dateFormatString = value;
    }
    
    public int getLogLevel()
    {
    	return this._logLevel;
    }

	public void setLogLevel(int value)
    {
            if (value < 0)
            {
                this._logLevel = 0;
            }
            if (value > 5)
            {
                this._logLevel = 5;
            }
    }
    
    public String getProjectName()
    {
    	return this.projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }
    
    public String getReportName()
    {
    	return this.reportName;
    }

    public void setReportName(String reportName)
    {
        this.reportName = reportName;
    }

    public String getReportPath()
    {
    	return this.reportPath;
    }
    
    public void setReportPath(String value)
    {
        this.reportPath = value;
    }

	public ReportSettings(String reportPath, String reportName)
    {
        this.setReportPath(reportPath);
        this.setReportName(reportName);
    }
}
