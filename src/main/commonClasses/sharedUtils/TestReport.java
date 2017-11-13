package commonClasses.sharedUtils;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import commonClasses.sharedUtils.managers.LocalDriver;
import reporting.report.ReportTemplate;
import reporting.report.ReportTheme;
import reporting.report.dataObjects.ReportSettings;
import reporting.report.dataObjects.TestStepContent;
import reporting.report.enums.Status;

public class TestReport extends ReportTemplate {

	public TestReport(ReportSettings reportSettings, ReportTheme reportTheme) throws Exception {
		super(reportSettings, reportTheme);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    protected void TakeScreenshot(String screenshotPath) throws Exception {
        try {

            if (LocalDriver.getDriver() == null) {
                throw new Exception("Report.driver is not initialized!");
            }

            File source = ((TakesScreenshot)LocalDriver.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File(screenshotPath), true);
        } catch (Exception exception) {
            reportWarning("TakeScreenshot", exception.getMessage());
        }
    }
	
    public void reportDoneEvent(String stepName, String description) throws Exception {
    		TestStepContent testStepContent = new TestStepContent(stepName, description, Status.DONE);
        super.addResultContent(testStepContent);
    }
    
    public void reportWarning(String stepName, String description) throws Exception {
		TestStepContent testStepContent = new TestStepContent(stepName, description, Status.WARNING);
        super.addResultContent(testStepContent);
    }
    
    public void reportPassEvent(String stepName, String description) throws Exception {
        try {
    			TestStepContent testStepContent = new TestStepContent(stepName, description, Status.PASS);
            super.addResultContent(testStepContent);
        } catch (Exception invalidOperationException) {
    			TestStepContent testStepContent = new TestStepContent(stepName, description, Status.WARNING);
            super.addResultContent(testStepContent);
        }
    }
    
    public void reportFailEvent(String stepName, String description) throws Exception {
        try {
			TestStepContent testStepContent = new TestStepContent(stepName, description, Status.FAIL);
            super.addResultContent(testStepContent);
        } catch (Exception invalidOperationException) {
        	    TestStepContent testStepContent = new TestStepContent(stepName, description, Status.WARNING);
            super.addResultContent(testStepContent);
        }
    }

}
