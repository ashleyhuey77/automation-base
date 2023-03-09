package com.report.controllers;

import com.config.TestException;
import com.report.models.TestContent;
import com.report.models.TestStepContent;

public interface ReportContent {
	
	public void createTestReportFile() throws TestException; 
	
	public void addBaseReportContent(TestContent report) throws TestException;
	
	public void addResultContent(TestStepContent testStep) throws TestException;

	public void addFailDetailsContent(TestStepContent content) throws TestException;

}
