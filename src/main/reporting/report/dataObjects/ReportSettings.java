package reporting.report.dataObjects;

import reporting.report.enums.ReportType;

public class ReportSettings {
	
	//private int _logLevel = 4;

    public Boolean GenerateHtmlReports = true;

    public Boolean IncludeTestDataInReport = true;

    public Boolean TakeScreenshotFailedStep = true;

    public Boolean TakeScreenshotPassedStep = true;

    private String _dateFormatString = "dd-MMM-yyyy hh:mm:ss a";
    
    public String reportName;
    
    public String reportPath;
    
    public ReportType type;
    
    public String getDateFormatString()
    {
    	return this._dateFormatString;
    }

    public void setDateFormatString(String value)
    {
       this._dateFormatString = value;
    }
    
/*    public int getLogLevel()
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
    }*/
    
    public String getReportName()
    {
    	return this.reportName;
    }

    public void setReportName(String reportName)
    {
        this.reportName = reportName;
    }
    
    public void setReportType(ReportType type) {
    		this.type = type;
    }
    
    public ReportType getReportType() {
    		return type;
    }

    public String getReportPath()
    {
    	return this.reportPath;
    }
    
    public void setReportPath(String value)
    {
        this.reportPath = value;
    }

	public ReportSettings(String reportPath, String reportName, ReportType type)
    {
        this.setReportPath(reportPath);
        this.setReportName(reportName);
        this.setReportType(type);
        
    }
	
	public ReportSettings(String reportPath, String reportName)
    {
        this.setReportPath(reportPath);
        this.setReportName(reportName);
        
    }
}
