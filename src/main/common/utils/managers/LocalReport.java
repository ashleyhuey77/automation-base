package common.utils.managers;

import common.utils.TestReport;
import common.utils.helpers.ReportHelper;

/**
 * <h2>LocalReport</h2>
 * <p>The LocalReport class is used to get and set the threadsafe instance of 
 * ReportHelper, HtmlReport, and the file path where reporting items should be saved.</p>
 * @author ashleyhuey
 *
 */
public class LocalReport {
	
	private LocalReport() {
		
	}

    private static ThreadLocal < ReportHelper > report = new ThreadLocal <> ();
    private static ThreadLocal < TestReport > htmlReport = new ThreadLocal <> ();
    private static ThreadLocal < String > filePath = new ThreadLocal <> ();

    /**
     * <p>Get the threadsafe instance of ReportHelper.</p>
     * @return ReportHelper
     */
    public static ReportHelper getReport() {
        return report.get();
    }

    /**
     * <p>Set the threadsafe instance of ReportHelper </p>
     * @param value - the ReportHelper instance to set.
     */
    public static void setReport(ReportHelper value) {
        report.set(value);
    }

    /**
     * <p>Get the threadsafe instance of HtmlReport.</p>
     * @return HtmlReport
     */
    public static TestReport getHtmlReport() {
        return htmlReport.get();
    }

    /**
     * <p>Set the threadsafe instance of HtmlReport </p>
     * @param value - the HtmlReport instance to set.
     */
    public static void setHtmlReport(TestReport value) {
        htmlReport.set(value);
    }

    /**
     * <p>Get the threadsafe instance of file path.</p>
     * @return String
     */
    public static String getFilePath() {
        return filePath.get();
    }

    /**
     * <p>Set the threadsafe instance of file path </p>
     * @param value - the file path instance to set.
     */
    public static void setFilePath(String value) {
        filePath.set(value);
    }

}