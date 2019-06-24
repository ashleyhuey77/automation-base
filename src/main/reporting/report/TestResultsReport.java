package reporting.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import common.base.interfaces.DatePicker;
import common.base.methods.PresentDate;
import common.utils.TestUtils;
import log.Log;
import log.TestException;
import reporting.report.dobjects.ReportSettings;
import reporting.report.dobjects.TestContent;
import reporting.report.dobjects.TestStepContent;
import reporting.report.interfaces.ReportContent;
import reporting.utilities.Util;

public class TestResultsReport implements ReportContent {

	private String resultSummaryPath;
	private ThreadLocal<Document> document = new ThreadLocal<>();

	public TestResultsReport(ReportSettings reportSettings) {
		String reportPath = reportSettings.getReportPath() + Util.getFileSeparator() + "HTML Results"
				+ Util.getFileSeparator() + reportSettings.getReportName() + ".html";
		this.resultSummaryPath = reportPath;
	}

	@Override
	public void addBaseReportContent(TestContent report) throws TestException {
		InputStream htmlstream = getClass().getResourceAsStream("web/HTMLReport.html");
		InputStream cssstream = getClass().getResourceAsStream("web/reportCss.html");
		try (FileWriter file = new FileWriter(this.resultSummaryPath, false)) {
			try (BufferedWriter streamWriter = new BufferedWriter(file)) {
				document.set(Jsoup.parse(htmlstream, "utf-8", "/src/main/reporting/report/web/HTMLReport.html"));
				Document newDoc = document.get();
				Document css = Jsoup.parse(cssstream, "utf-8", "/src/main/reporting/report/web/reportCss.html");
				Element head = newDoc.head();
				head.append(css.html());
				changeBaseReportPageText(head, newDoc, report);
				String objArray = newDoc.outerHtml();
				streamWriter.write(objArray);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addResultContent(TestStepContent testStep) throws TestException {
		try {
			DatePicker picker = new PresentDate();
			File test = new File(this.resultSummaryPath);
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
			createNewRowInTable(tr, null, picker.getDate("hh:mm:ss"));
			streamWriter.write(document.get().toString());
			streamWriter.close();
		} catch (Exception e) {

		}
	}

	@Override
	public void createTestReportFile() throws TestException {
		try {
			File file = new File(this.resultSummaryPath);
			file.setWritable(true);
			if (!file.exists()) {
				file.getParentFile().mkdir();
				file.createNewFile();
			}
		} catch (IOException oException) {
			Log.get().log(Level.SEVERE, oException.getMessage(), oException);
			throw new TestException("Error while creating HTML result summary file");
		}
	}

	private void createNewRowInTable(Element tr, String className, String content) {
		Element td = null;

		if (TestUtils.isNullOrBlank(className)) {
			td = tr.appendElement("td");
		} else {
			td = tr.appendElement("td").attr("class", className);
		}

		td.text(content);
	}

	private void createStatusRow(Element tr, String status, String screenShotName) {
		Element statusRow = tr.appendElement("td").attr("class", status);
		Element link = null;
		Element element = null;
		switch (status) {
			case "pass":
				statusRow.appendElement("img").attr("id", "glass").attr("align", "justify").attr("src",
						"../../../resources/reportContent/64x64_ICONS_Navigation_Checkmark.jpg");
				link = statusRow.appendElement("a").attr("href", "..\\Screenshots\\" + screenShotName);
				link.text(status.toUpperCase());
				break;
			case "fail":
				statusRow.appendElement("img").attr("id", "glass").attr("align", "justify").attr("src",
						"../../../resources/reportContent/64x64_ICONS_Navigation_Alert.jpg");
				link = statusRow.appendElement("a").attr("href", "..\\Screenshots\\" + screenShotName);
				link.text(status.toUpperCase());
				break;
			case "done":
				element = statusRow.appendElement("img").attr("id", "glass").attr("align", "justify").attr("src",
						"../../../resources/reportContent/64x64_ICONS_Navigation_Checkmark.jpg");
				element.text(status.toUpperCase());
				break;
			case "warning":
				element = statusRow.appendElement("img").attr("id", "glass").attr("align", "justify").attr("src",
						"../../../resources/reportContent/64x64_ICONS_Navigation_Alert.jpg");
				element.text(status.toUpperCase());
				break;
			default:
				break;
		}
	}

	private void createStepNumberRow(Element tr, String className, String content) {
		Element td = null;

		if (TestUtils.isNullOrBlank(className)) {
			td = tr.appendElement("td");
		} else {
			td = tr.appendElement("td").attr("class", className);
		}

		td.text(content);
	}

	private void changeBaseReportPageText(Element head, Document newDoc, TestContent report) {
		Element title = head.appendElement("title");
		Element testHeading = newDoc.getElementById("testHeading");
		Element browser = newDoc.getElementById("browser");
		Element url = newDoc.getElementById("url");
		title.text(report.testScenarioName() + "_" + report.browserName() + " – " + "Automation Execution Results");
		testHeading.text(report.testScenarioName());
		browser.text(report.browserName());
		url.text(report.url());
	}

}
