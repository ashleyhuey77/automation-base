package commonClasses.sharedUtils.managers;

import commonClasses.sharedUtils.TestReport;
import commonClasses.sharedUtils.helpers.ReportHelper;

/**
 * <h2>LocalReport</h2>
 * <p>The LocalReport class is used to get and set the threadsafe instance of 
 * ReportHelper, HtmlReport, and the file path where reporting items should be saved.</p>
 * @author ashleyhuey
 *
 */
public class LocalReport {

    private static ThreadLocal < ReportHelper > report = new ThreadLocal < ReportHelper > ();
    private static ThreadLocal < TestReport > HtmlReport = new ThreadLocal < TestReport > ();
    private static ThreadLocal < String > filePath = new ThreadLocal < String > ();

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
        return HtmlReport.get();
    }

    /**
     * <p>Set the threadsafe instance of HtmlReport </p>
     * @param value - the HtmlReport instance to set.
     */
    public static void setHtmlReport(TestReport value) {
        HtmlReport.set(value);
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