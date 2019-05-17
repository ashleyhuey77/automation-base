package log;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import common.utils.managers.LocalDriver;
import common.utils.managers.LocalReport;

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
