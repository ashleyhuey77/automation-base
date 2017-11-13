package reporting.report.interfaces;

import reporting.report.dataObjects.TestContent;
import reporting.report.dataObjects.TestStepContent;

public interface ReportContent {
	
	public void createTestReportFile() throws Exception; 
	
	public void addBaseReportContent(TestContent report) throws Exception;
	
	public void addResultContent(TestStepContent testStep) throws Exception; 

}
