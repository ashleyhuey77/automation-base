package reporting.report.interfaces;

import log.TestException;
import reporting.report.dobjects.TestContent;
import reporting.report.dobjects.TestStepContent;

public interface ReportContent {
	
	public void createTestReportFile() throws TestException; 
	
	public void addBaseReportContent(TestContent report) throws TestException;
	
	public void addResultContent(TestStepContent testStep) throws TestException; 

}
