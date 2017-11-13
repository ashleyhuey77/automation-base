package reporting.report.dataObjects;

import reporting.report.enums.Status;

public class TestStepContent {

	private ThreadLocal<String> number = new ThreadLocal<String>();
	private ThreadLocal<String> name = new ThreadLocal<String>();
	private ThreadLocal<String> description = new ThreadLocal<String>();
	private ThreadLocal<Status> status = new ThreadLocal<Status>();
	private ThreadLocal<String> screenshotName = new ThreadLocal<String>();
	
	public TestStepContent(String number, String name, String description, Status status, String screenshotName) {
		this.number.set(number);
		this.name.set(name);
		this.description.set(description);
		this.status.set(status);
		this.screenshotName.set(screenshotName);
	}
	
	public TestStepContent(String name, String description, Status status) {
		this.name.set(name);
		this.description.set(description);
		this.status.set(status);
	}
	
	public String number() {
		return number.get();
	}
	
	public String name() {
		return name.get();
	}
	
	public String description() {
		return description.get();
	}
	
	public Status status() {
		return status.get();
	}
	
	public String screenshotName() {
		return screenshotName.get();
	}
}
