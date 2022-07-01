package com.warnermedia.utils;

import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.report.LocalReport;

import java.util.List;

public class ConsoleHelper {
	
	public static void analyzeLog() throws TestException {
		if (LocalDriver.getDriver() != null) {
    		List<LogEntry> entries = LocalDriver.getDriver().manage().logs().get(LogType.BROWSER).getAll();
    		if (entries != null) {
        		for (LogEntry entry : entries) {
						if (!entry.getLevel().toString().equalsIgnoreCase("warning")) {
							LocalReport.getReport().reportDoneEvent(entry.getLevel() + " " + entry.getMessage());
						}
        		}
    		}
		}
	}

}
