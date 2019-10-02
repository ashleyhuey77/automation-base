package com.warnermedia.report.controllers;

import com.warnermedia.config.TestException;
import com.warnermedia.report.models.TestContent;
import com.warnermedia.report.models.TestStepContent;

public interface ReportContent {
	
	public void createTestReportFile() throws TestException; 
	
	public void addBaseReportContent(TestContent report) throws TestException;
	
	public void addResultContent(TestStepContent testStep) throws TestException; 

}
