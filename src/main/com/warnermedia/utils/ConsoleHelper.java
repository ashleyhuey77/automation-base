package com.warnermedia.utils;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.report.LocalReport;

public class ConsoleHelper {
	
	public static void analyzeLog() throws TestException {
		if (LocalDriver.getDriver() != null) {
    		LogEntries entries = LocalDriver.getDriver().manage().logs().get(LogType.BROWSER);
    		if (entries != null) {
        		for (LogEntry entry : entries) {
        			LocalReport.getReport().reportDoneEvent(entry.getLevel() + " " + entry.getMessage());
        		}
    		}
		}
	}

}
