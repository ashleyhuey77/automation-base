package commonClasses.sharedUtils;

public class LocalReportManager {
	
	private static ThreadLocal<Report> report = new ThreadLocal<Report>();
	private static ThreadLocal<MsTESTReport> msTestReport = new ThreadLocal<MsTESTReport>();
	private static ThreadLocal<HtmlReport> HtmlReport = new ThreadLocal<HtmlReport>();
	 
    public static Report getReport() {
    	return report.get();
    }
    
    public static void setReport(Report inreport)
    {
    	report.set(inreport);
    }
    
    public static MsTESTReport getMsTestReport() {
    	return msTestReport.get();
    }
    
    public static void setMsTestReport(MsTESTReport inMsReport) {
    	msTestReport.set(inMsReport);
    }
    
    public static HtmlReport getHtmlReport() {
    	return HtmlReport.get();
    }
    
    public static void setHtmlReport(HtmlReport inhtmlreport) {
    	HtmlReport.set(inhtmlreport);
    }

}
