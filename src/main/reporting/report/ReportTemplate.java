package reporting.report;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import reporting.report.dataObjects.ReportSettings;
import reporting.report.dataObjects.TestContent;
import reporting.report.dataObjects.TestStepContent;
import reporting.report.interfaces.ReportContent;
import reporting.utilities.Util;

public class ReportTemplate implements ReportContent {
    
    private ReportSettings _reportSettings;
    
	private ReportContent content;
	
    private int _nStepsPassed = 0;

    private int _nStepsFailed = 0;
    
    private String _stepNumber;
    
    private ArrayList<ReportContent> _reportTypes = new ArrayList<ReportContent>();
	
	public ReportTemplate(ReportSettings reportSettings) throws Exception {
        this._reportSettings = reportSettings;
		switch(reportSettings.getReportType()) {
            	case TEST_RESULTS_REPORT:
            		this.content = new TestResultsReport(reportSettings);
            		break;
            	default:
            		break;
        }
		InitializeReportTypes();
        createTestReportFile();
    }
	
	public void createTestReportFile() throws Exception {
        for (int i = 0; i < this._reportTypes.size(); i++)
        {
        		content.createTestReportFile();
        }
	}
	
	@Override
	public void addBaseReportContent(TestContent report) throws Exception {
        for (int i = 0; i < this._reportTypes.size(); i++)
        {
        		content.addBaseReportContent(report);
        }
	}
	
	@Override
	public void addResultContent(TestStepContent testStep) throws Exception {
		String reportPath;
        String str = null;
        if (this._stepNumber == null) {
    			this._stepNumber = "1";
        }
        switch(testStep.status()) {
            	case FAIL:
            		_nStepsFailed = _nStepsFailed + 1;
                if (this._reportSettings.TakeScreenshotFailedStep) {
                		str = this._reportSettings.getReportName() + "_" + Util.GetCurrentFormattedTime(
                				this._reportSettings.getDateFormatString()).replace(" ", "_").replace(":", "-") + ".png";
                		reportPath = this._reportSettings.getReportPath() + Util.GetFileSeparator() + 
                				"Screenshots" + Util.GetFileSeparator() + str;
                		this.TakeScreenshot(reportPath);
                }
                break;
            	case PASS:
            		_nStepsPassed = _nStepsPassed + 1;
                if (this._reportSettings.TakeScreenshotPassedStep) {
                		str = this._reportSettings.getReportName() + "_" + Util.GetCurrentFormattedTime(
                				this._reportSettings.getDateFormatString()).replace(" ", "_").replace(":", "-") + ".png";
                		reportPath = this._reportSettings.getReportPath() + Util.GetFileSeparator() + 
                				"Screenshots" + Util.GetFileSeparator() + str;
                		this.TakeScreenshot(reportPath);
                }
                break;
            default:
            		break;
         
        }
        for (int i = 0; i < this._reportTypes.size(); i++)
        {
            TestStepContent tsc = new TestStepContent(this._stepNumber, testStep.name(), testStep.description(), 
            											testStep.status(), str);
            this.content.addResultContent(tsc);
        }
        int _stepNumber = Integer.parseInt(this._stepNumber) + 1;
        this._stepNumber = Integer.toString(_stepNumber);
	}
	
	protected void TakeScreenshot(String screenshotPath) throws Exception {

    }
	
	private void InitializeReportTypes() throws IOException {
        if (this._reportSettings.GenerateHtmlReports) {
            new File(this._reportSettings.getReportPath() + Util.GetFileSeparator() + "HTML Results");
            TestResultsReport htmlReport = new TestResultsReport(this._reportSettings);
            this._reportTypes.add(htmlReport);
        }
        File screenshots = new File(this._reportSettings.getReportPath() + Util.GetFileSeparator() + "Screenshots");
        screenshots.mkdir();
        screenshots.createNewFile();
    }
}
