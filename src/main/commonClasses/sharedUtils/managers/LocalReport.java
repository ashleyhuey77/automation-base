package commonClasses.sharedUtils.managers;

import commonClasses.sharedUtils.HtmlReport;
import commonClasses.sharedUtils.helpers.ReportHelper;

public class LocalReport {
	
	private static ThreadLocal<ReportHelper> report = new ThreadLocal<ReportHelper>();
	private static ThreadLocal<HtmlReport> HtmlReport = new ThreadLocal<HtmlReport>();
	 
    public static ReportHelper getReport() {
    	return report.get();
    }
    
    public static void setReport(ReportHelper inreport)
    {
    	report.set(inreport);
    }
   
    public static HtmlReport getHtmlReport() {
    	return HtmlReport.get();
    }
    
    public static void setHtmlReport(HtmlReport inhtmlreport) {
    	HtmlReport.set(inhtmlreport);
    }

}