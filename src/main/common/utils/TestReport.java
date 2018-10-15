package common.utils;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import common.utils.managers.LocalDriver;
import log.TestException;
import reporting.report.ReportTemplate;
import reporting.report.dobjects.ReportSettings;
import reporting.report.dobjects.TestStepContent;
import reporting.report.enums.Status;

public class TestReport extends ReportTemplate {

	public TestReport(ReportSettings reportSettings) throws TestException {
		super(reportSettings);
		
	}
	
	@Override
    protected void takeScreenshot(String screenshotPath) throws TestException {
        try {

            if (LocalDriver.getDriver() == null) {
                throw new TestException("Report.driver is not initialized!");
            }

            File source = ((TakesScreenshot)LocalDriver.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File(screenshotPath), true);
        } catch (Exception exception) {
            reportWarning("TakeScreenshot", exception.getMessage());
        }
    }
	
    public void reportDoneEvent(String stepName, String description) throws TestException {
    		TestStepContent testStepContent = new TestStepContent(stepName, description, Status.DONE);
        super.addResultContent(testStepContent);
    }
    
    public void reportWarning(String stepName, String description) throws TestException {
		TestStepContent testStepContent = new TestStepContent(stepName, description, Status.WARNING);
        super.addResultContent(testStepContent);
    }
    
    public void reportPassEvent(String stepName, String description) throws TestException {
        try {
    			TestStepContent testStepContent = new TestStepContent(stepName, description, Status.PASS);
            super.addResultContent(testStepContent);
        } catch (Exception invalidOperationException) {
    			TestStepContent testStepContent = new TestStepContent(stepName, description, Status.WARNING);
            super.addResultContent(testStepContent);
        }
    }
    
    public void reportFailEvent(String stepName, String description) throws TestException {
        try {
			TestStepContent testStepContent = new TestStepContent(stepName, description, Status.FAIL);
            super.addResultContent(testStepContent);
        } catch (Exception invalidOperationException) {
        	    TestStepContent testStepContent = new TestStepContent(stepName, description, Status.WARNING);
            super.addResultContent(testStepContent);
        }
    }

}
