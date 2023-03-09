package com.report.controllers;

import java.io.*;

import com.utils.ConsoleDecoration;
import com.utils.observers.app.Application;
import com.utils.observers.app.ApplicationState;
import com.utils.observers.app.IssueType;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.config.TestException;
import com.page.utils.date.DatePicker;
import com.page.utils.date.PresentDate;
import com.report.Util;
import com.report.models.ReportSettings;
import com.report.models.TestContent;
import com.report.models.TestStepContent;
import com.utils.TestUtils;
import org.jsoup.select.Elements;

@Slf4j
public class TestResultsReport implements ReportContent, Application  {

	private String resultSummaryPath;
	private ThreadLocal<Document> document = new ThreadLocal<>();
	private static ThreadLocal<IssueType> issueType = new ThreadLocal<>();

	public TestResultsReport(ReportSettings reportSettings) {
		String reportPath = reportSettings.getReportPath() + Util.getFileSeparator() + "HTML Results"
				+ Util.getFileSeparator() + reportSettings.getReportName() + ".html";
		this.resultSummaryPath = reportPath;
	}

	@Override
	public void addBaseReportContent(TestContent report) throws TestException {
		InputStream htmlstream = null;
		try (FileWriter file = new FileWriter(this.resultSummaryPath, false)) {
			htmlstream = Util.class.getResourceAsStream("views/HTMLReport.html");
			if (htmlstream == null) {
				htmlstream = new FileInputStream(new File("src/main/com/warnermedia/report/views/HTMLReport.html"));
			}
			try (BufferedWriter streamWriter = new BufferedWriter(file)) {
				document.set(Jsoup.parse(htmlstream, "utf-8", "/src/main/com/warnermedia/report/views/HTMLReport.html"));
				Document newDoc = document.get();
				Element head = newDoc.head();
				changeBaseReportPageText(head, newDoc, report);
				String objArray = newDoc.outerHtml();
				streamWriter.write(objArray);
			}
		} catch (Exception e) {
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
	public void addFailDetailsContent(TestStepContent content) throws TestException {
		try {
			File test = new File(this.resultSummaryPath);
			BufferedWriter streamWriter = new BufferedWriter(new FileWriter(test, false));
			if (content.status().equals(Status.FAIL)) {
				Document doc = document.get();
				Element body = doc.body();
				if (!issueType.get().equals(IssueType.NONE)) {
					body.appendElement("div").attr("id", "env").attr("class", "info");
					Element env = body.getElementById("env");
					env.appendElement("div").attr("class", "message");
					Elements message = env.getElementsByClass("message");
					message.get(0).appendElement("p").attr("class", "bold");
					Elements messageTitle = message.get(0).getElementsByClass("bold");
					messageTitle.get(0).text("ENVIRONMENT");
					message.get(0).appendElement("p").attr("class", "messageContent");
					Elements messageContent = message.get(0).getElementsByClass("messageContent");
					messageContent.get(0).text("The following failure was likely due to an environment issue" +
							" with code ");
					Element link = messageContent.get(0).appendElement("a").attr("href", "http://atomwiki.turner.com/display/MSNS/Environment+Issue+Status+Codes");
					link.text(issueType.get().name());
					messageContent.get(0).appendText(". Please check the environment and assure it is working properly.");
				} else {
					body.appendElement("div").attr("id", "bug").attr("class", "info");
					Element env = body.getElementById("bug");
					env.appendElement("div").attr("class", "message");
					Elements message = env.getElementsByClass("message");
					message.get(0).appendElement("p").attr("class", "bold");
					Elements messageTitle = message.get(0).getElementsByClass("bold");
					messageTitle.get(0).text("BUG");
					message.get(0).appendElement("p").attr("class", "messageContent");
					Elements messageContent = message.get(0).getElementsByClass("messageContent");
					messageContent.get(0).text("The following failure was not found in the current list of common environmental issues." +
							" Manually retest the scenario to confirm whether or not there is a bug in the application. " +
							" If there is a bug in the application, please report it to the appropriate channels. " +
							"If the issue is not reproducible manually, then it is likely a bug in the test scripts." +
							" At that point, further debugging of the test would be required.");
				}
			}
			streamWriter.write(document.get().toString());
			streamWriter.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
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
			log.error("{}{}{}{}", ConsoleDecoration.RED_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, oException.getMessage(), ConsoleDecoration.RESET.value);
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
				if (resultSummaryPath.contains(File.separator + "ws" + File.separator)) {
					link = statusRow.appendElement("a").attr("href", "..\\..\\Screenshots\\" + screenShotName);
				} else {
					link = statusRow.appendElement("a").attr("href", "..\\Screenshots\\" + screenShotName);
				}
				link.text(status.toUpperCase());
				break;
			case "fail":
				statusRow.appendElement("img").attr("id", "glass").attr("align", "justify").attr("src",
						"../../../resources/reportContent/64x64_ICONS_Navigation_Alert.jpg");
				if (resultSummaryPath.contains(File.separator + "ws" + File.separator)) {
					link = statusRow.appendElement("a").attr("href", "..\\..\\Screenshots\\" + screenShotName);
				} else {
					link = statusRow.appendElement("a").attr("href", "..\\Screenshots\\" + screenShotName);
				}
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

	@Override
	public void update(ApplicationState subject, IssueType argument) throws TestException {
		issueType.set(argument);
	}
}
