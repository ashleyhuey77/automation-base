package reporting.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import common.utils.TestUtils;
import reporting.report.dataObjects.ReportSettings;
import reporting.report.dataObjects.TestContent;
import reporting.report.dataObjects.TestStepContent;
import reporting.report.interfaces.ReportContent;
import reporting.utilities.Util;

public class TestResultsReport implements ReportContent {
	
	 private String _resultSummaryPath;
	 private ThreadLocal<Document> document = new ThreadLocal<Document>();
	 
	 public TestResultsReport(ReportSettings reportSettings) {
	        String reportPath = reportSettings.getReportPath() + Util.GetFileSeparator() + "HTML Results" + Util.GetFileSeparator() + reportSettings.getReportName() +".html";
	        this._resultSummaryPath = reportPath;
	}

	@Override
	public void addBaseReportContent(TestContent report) throws Exception {
		try {
			InputStream htmlstream = getClass().getResourceAsStream("web/HTMLReport.html");
			InputStream cssstream = getClass().getResourceAsStream("web/reportCss.html");
        		FileWriter file = new FileWriter(this._resultSummaryPath, false);
        		BufferedWriter streamWriter = new BufferedWriter(file);
        		document.set(Jsoup.parse(htmlstream, "utf-8", "/src/main/reporting/report/web/HTMLReport.html"));
        		Document newDoc = document.get();
        		Document css = Jsoup.parse(cssstream, "utf-8", "/src/main/reporting/report/web/reportCss.html");
        		Element head = newDoc.head();
        		head.append(css.html());
        		changeBaseReportPageText(head, newDoc, report);
            String objArray = newDoc.outerHtml();
            streamWriter.write(objArray);
            streamWriter.close();
            file.close();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void addResultContent(TestStepContent testStep) throws Exception {
		try {
        		File test = new File(this._resultSummaryPath);
        		BufferedWriter streamWriter = new BufferedWriter(new FileWriter(test, false));
        		Document doc = document.get();
        		Element body = doc.body();
        		Element mainTable = body.getElementById("main");
        		Element tbody = mainTable.getElementById("testStepsTable");
        		Element tr = tbody.appendElement("tr").addClass("content").attr("id", testStep.number());
        		createStepNumberRow(tr, null, testStep.number());
            	createNewRowInTable(tr, "justified", testStep.name());
            	createNewRowInTable(tr, "description", testStep.description());
            	createStatusRow(tr, testStep.status().toString().toLowerCase(), testStep.screenshotName());
            	createNewRowInTable(tr, null, TestUtils.GetCurrentDateTime("HH:mm:ss"));
            streamWriter.write(document.get().toString());
            streamWriter.close();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void createTestReportFile() throws Exception {
		try {
            try {
            		File file = new File(this._resultSummaryPath);
            		file.setWritable(true);
                if (!file.exists()) {
                		file.getParentFile().mkdir();
                		file.createNewFile();
                }
            } catch (IOException oException) {
            		System.out.println(oException.getStackTrace());
            		throw new Exception("Error while creating HTML result summary file");
            }
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void createNewRowInTable(Element tr, String className, String content) throws Exception {
		try {
			Element td = null;
			
			if (TestUtils.isNullOrBlank(className)) {
				td =	 tr.appendElement("td");
			} else {
				td = tr.appendElement("td").attr("class", className);
			}
			
			td.text(content);
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void createStatusRow(Element tr, String status, String screenShotName) throws Exception {
		try {
			Element statusRow = tr.appendElement("td").attr("class", status);
			Element link = null;
			Element element = null;
			switch(status) {
				case "pass":
					element = statusRow.appendElement("img").attr("id", "glass").attr("align", "justify").attr("src", "../../../resources/reportContent/64x64_ICONS_Navigation_Checkmark.jpg");
        				link = statusRow.appendElement("a").attr("href", "..\\Screenshots\\" + screenShotName);
        				link.text(status.toUpperCase());
        				break;
				case "fail":
					element = statusRow.appendElement("img").attr("id", "glass").attr("align", "justify").attr("src", "../../../resources/reportContent/64x64_ICONS_Navigation_Alert.jpg");
    					link = statusRow.appendElement("a").attr("href", "..\\Screenshots\\" + screenShotName);
    					link.text(status.toUpperCase());
    					break;
				case "done":
					element = statusRow.appendElement("img").attr("id", "glass").attr("align", "justify").attr("src", "../../../resources/reportContent/64x64_ICONS_Navigation_Checkmark.jpg");
					element.text(status.toUpperCase());
    					break;
				case "warning":
					element = statusRow.appendElement("img").attr("id", "glass").attr("align", "justify").attr("src", "../../../resources/reportContent/64x64_ICONS_Navigation_Alert.jpg");
					element.text(status.toUpperCase());
					break;
				default:
					break;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void createStepNumberRow(Element tr, String className, String content) throws Exception {
		try {
			Element td = null;
			
			if (TestUtils.isNullOrBlank(className)) {
				td =	 tr.appendElement("td");
			} else {
				td = tr.appendElement("td").attr("class", className);
			}
			
			td.text(content);
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void changeBaseReportPageText(Element head, Document newDoc, TestContent report) {
		try {
        		Element title = head.appendElement("title");
        		Element testHeading = newDoc.getElementById("testHeading");
        		Element browser = newDoc.getElementById("browser");
        		Element url = newDoc.getElementById("url");
        		title.text(report.testScenarioName() + "_" + report.browserName() + " – " + "Automation Execution Results");
        		testHeading.text(report.testScenarioName());
        		browser.text(report.browserName());
        		url.text(report.url());
		} catch (Exception e) {
			throw e;
		}
	}

}
