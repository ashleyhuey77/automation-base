package reporting.framework.reporting;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import commonClasses.sharedUtils.LocalDriverManager;
import reporting.framework.utilities.FrameworkException;
import reporting.framework.utilities.Util;

public class Report {
	
	private ReportSettings _reportSettings;

    private ReportTheme _reportTheme;

    private String _stepNumber;

    private int _nStepsPassed = 0;

    private int _nStepsFailed = 0;

    private int _nTestsPassed = 0;

    private int _nTestsFailed = 0;

    private ArrayList<ReportType> _reportTypes = new ArrayList<ReportType>();

    public String TestStatus = "Passed";

    public String FailureDescription;

    public Report(ReportSettings reportSettings, ReportTheme reportTheme)
    {
        this._reportSettings = reportSettings;
        this._reportTheme = reportTheme;
    }

    public void AddResultSummaryFooter(String totalExecutionTime) throws FrameworkException
    {
        for (int i = 0; i < this._reportTypes.size(); i++)
        {
            this._reportTypes.get(i).AddResultSummaryFooter(totalExecutionTime, this._nTestsPassed, this._nTestsFailed);
        }
    }

    public void AddResultSummaryHeading(String heading) throws FrameworkException
    {
        for (int i = 0; i < this._reportTypes.size(); i++)
        {
            this._reportTypes.get(i).AddResultSummaryHeading(heading);
        }
    }

    public void AddResultSummarySubHeading(String subHeading1, String subHeading2, String subHeading3, String subHeading4) throws FrameworkException
    {
        for (int i = 0; i < this._reportTypes.size(); i++)
        {
            this._reportTypes.get(i).AddResultSummarySubHeading(subHeading1, subHeading2, subHeading3, subHeading4);
        }
    }

    public void AddResultSummaryTableHeadings() throws FrameworkException
    {
        for (int i = 0; i < this._reportTypes.size(); i++)
        {
            this._reportTypes.get(i).AddResultSummaryTableHeadings();
        }
    }

    public void AddTestLogFooter(String executionTime) throws FrameworkException
    {
        for (int i = 0; i < this._reportTypes.size(); i++)
        {
            this._reportTypes.get(i).AddTestLogFooter(executionTime, this._nStepsPassed, this._nStepsFailed);
        }
    }

    public void AddTestLogHeading(String heading) throws FrameworkException
    {
        for (int i = 0; i < this._reportTypes.size(); i++)
        {
            this._reportTypes.get(i).AddTestLogHeading(heading);
        }
    }

    public void AddTestLogSection(String section) throws FrameworkException
    {
        for (int i = 0; i < this._reportTypes.size(); i++)
        {
            this._reportTypes.get(i).AddTestLogSection(section);
        }
        this._stepNumber = "1";
    }

    public void AddTestLogSubHeading(String subHeading1, String subHeading2, String subHeading3, String subHeading4) throws FrameworkException
    {
        for (int i = 0; i < this._reportTypes.size(); i++)
        {
            this._reportTypes.get(i).AddTestLogSubHeading(subHeading1, subHeading2, subHeading3, subHeading4);
        }
    }

    public void AddTestLogSubSection(String subSection) throws FrameworkException
    {
        for (int i = 0; i < this._reportTypes.size(); i++)
        {
            this._reportTypes.get(i).AddTestLogSubSection(subSection);
        }
    }

    public void AddTestLogTableHeadings() throws FrameworkException
    {
        for (int i = 0; i < this._reportTypes.size(); i++)
        {
            this._reportTypes.get(i).AddTestLogTableHeadings();
        }
    }

    public void InitializeReportTypes() throws IOException
    {
        /*if (this._reportSettings.GenerateExcelReports)
        {
            Directory.CreateDirectory(string.Concat(this._reportSettings.ReportPath, Util.GetFileSeparator(), "Excel Results"));
            ExcelReport excelReport = new ExcelReport(this._reportSettings, this._reportTheme);
            this._reportTypes.Add(excelReport);
        }*/
        if (this._reportSettings.GenerateHtmlReports)
        {
            new File(this._reportSettings.getReportPath() + Util.GetFileSeparator() + "HTML Results");
            HtmlReport htmlReport = new HtmlReport(this._reportSettings, this._reportTheme);
            this._reportTypes.add(htmlReport);
        }
        if (this._reportSettings.IncludeTestDataInReport)
        {
        	new File(this._reportSettings.getReportPath() + Util.GetFileSeparator() + "Datatables");
        }
        File screenshots = new File(this._reportSettings.getReportPath() + Util.GetFileSeparator() + "Screenshots");
        screenshots.mkdir();
        screenshots.createNewFile();
    }

    public void InitializeResultSummary() throws IOException, FrameworkException
    {
        for (int i = 0; i < this._reportTypes.size(); i++)
        {
            this._reportTypes.get(i).InitializeResultSummary();
        }
    }

    public void InitializeTestLog() throws IOException, FrameworkException
    {
        for (int i = 0; i < this._reportTypes.size(); i++)
        {
            this._reportTypes.get(i).InitializeTestLog();
        }
    }

    protected void TakeScreenshot(String screenshotPath) throws FrameworkException, IOException
    {
/*            try
            {               
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  
                GraphicsDevice[] screens = ge.getScreenDevices();       
                Rectangle allScreenBounds = new Rectangle();  
                for (GraphicsDevice screen : screens) {  
                       Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();        
                       allScreenBounds.width += screenBounds.width;  
                       allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);
                       allScreenBounds.x=Math.min(allScreenBounds.x, screenBounds.x);
                       allScreenBounds.y=Math.min(allScreenBounds.y, screenBounds.y);
                      } 
                Robot robot = new Robot();
                BufferedImage bufferedImage = robot.createScreenCapture(allScreenBounds);
                File file = new File(screenshotPath);
                if(!file.exists())
                    file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                ImageIO.write( bufferedImage, "png", fos );
            }
            catch (Exception exception)
            {
            	System.out.println(exception.getStackTrace());
                throw new FrameworkException("Error while writing screenshot to .png file");
            }*/
    	
    	File srcFile = ((TakesScreenshot)LocalDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
    	FileUtils.copyFile(srcFile, new File(screenshotPath), true);
    }

    public void UpdateResultSummary(String currentScenario, String currentTestcase, String currentTestDescription, String executionTime, String testStatus) throws FrameworkException
    {
        if (testStatus.toLowerCase().trim().equals("failed"))
        {
            Report report = this;
            report._nTestsFailed = report._nTestsFailed + 1;
        }
        else if (testStatus.toLowerCase().trim().equals("passed"))
        {
            Report report1 = this;
            report1._nTestsPassed = report1._nTestsPassed + 1;
        }
        for (int i = 0; i < this._reportTypes.size(); i++)
        {
            this._reportTypes.get(i).UpdateResultSummary(currentScenario, currentTestcase, currentTestDescription, executionTime, testStatus);
        }
    }

    public void UpdateTestLog(String stepName, String stepDescription, Status stepStatus) throws FrameworkException, IOException
    {
        String reportPath;
        //String _stepStatus = stepStatus.toString();
        if (stepStatus == Status.FAIL)
        {
            this.TestStatus = "Failed";
            if (this.FailureDescription != null)
            {
                this.FailureDescription = this.FailureDescription + "; " + stepDescription;
            }
            else
            {
                this.FailureDescription = stepDescription;
            }
            Report report = this;
            report._nStepsFailed = report._nStepsFailed + 1;
        }
        if (stepStatus == Status.PASS)
        {
            Report report1 = this;
            report1._nStepsPassed = report1._nStepsPassed + 1;
        }
        if (stepStatus.ordinal() <= this._reportSettings.getLogLevel())
        {
            String str = null;
            if (stepStatus == Status.FAIL)
            {
                if (this._reportSettings.TakeScreenshotFailedStep)
                {
                    str = this._reportSettings.getReportName() + "_" + Util.GetCurrentFormattedTime(this._reportSettings.getDateFormatString()).replace(" ", "_").replace(":", "-") + ".png";
                    reportPath = this._reportSettings.getReportPath() + Util.GetFileSeparator() + "Screenshots" + Util.GetFileSeparator() + str;
                    this.TakeScreenshot(reportPath);
                }
            }
            if (stepStatus == Status.PASS)
            {
                if (this._reportSettings.TakeScreenshotPassedStep)
                {
                    str = this._reportSettings.getReportName() + "_" + Util.GetCurrentFormattedTime(this._reportSettings.getDateFormatString()).replace(" ", "_").replace(":", "-") + ".png";
                    reportPath = this._reportSettings.getReportPath() + Util.GetFileSeparator() + "Screenshots" + Util.GetFileSeparator() + str;
                    this.TakeScreenshot(reportPath);
                }
            }
            if (stepStatus == Status.SCREENSHOT)
            {
                str = this._reportSettings.getReportName() + "_" + Util.GetCurrentFormattedTime(this._reportSettings.getDateFormatString()).replace(" ", "_").replace(":", "-") + ".png";
                reportPath = this._reportSettings.getReportPath() + Util.GetFileSeparator() + "Screenshots" + Util.GetFileSeparator() + str;
                this.TakeScreenshot(reportPath);
            }
            for (int i = 0; i < this._reportTypes.size(); i++)
            {
                this._reportTypes.get(i).UpdateTestLog(this._stepNumber, stepName, stepDescription, stepStatus, str);
            }
            if (this._stepNumber == null)
            {
            	this._stepNumber = "1";
            }
            Report report2 = this;
            int _stepNumber = Integer.parseInt(report2._stepNumber) + 1;
            report2._stepNumber = Integer.toString(_stepNumber);
        }
    }

}
