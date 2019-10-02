package com.warnermedia.report.models;

public class TestContent {

	private ThreadLocal<String> testScenarioName = new ThreadLocal<>();
	private ThreadLocal<String> browserName = new ThreadLocal<>();
	private ThreadLocal<String> url = new ThreadLocal<>();
	private ThreadLocal<String> heading4 = new ThreadLocal<>();

	public TestContent(String testScenarioName, String browserName, String url) {
		this.testScenarioName.set(testScenarioName);
		this.browserName.set(browserName);
		this.url.set(url);
	}

	public TestContent(String heading1, String heading2, String heading3, String heading4) {
		this.testScenarioName.set(heading1);
		this.browserName.set(heading2);
		this.url.set(heading3);
		this.heading4.set(heading4);
	}

	public String testScenarioName() {
		return testScenarioName.get();
	}

	public String browserName() {
		return browserName.get();
	}

	public String url() {
		return url.get();
	}

}
