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
    		int counter = 0;
    		if (entries != null) {
        		for (LogEntry entry : entries) {
        			if (counter > 50) {
        				break;
					} else {
						if (entry.getLevel().getName().contains("SEVERE")
								&& !entry.getMessage().contains("CORS policy: No 'Access-Control-Allow-Origin' header")
								&& !entry.getMessage().contains("fontawesome-webfont.woff2")
								&& !entry.getMessage().contains("/Volumes/Grid_A/editor/resources/")) {
							LocalReport.getReport().reportDoneEvent(entry.getLevel() + " " + entry.getMessage());
							counter++;
						}
					}
        		}
    		}
		}
	}

}
