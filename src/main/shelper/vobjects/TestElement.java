package shelper.vobjects;

public class TestElement {
	private Locator locator;
	private By by;
	
	public TestElement(Locator locator, By by) {
		this.locator = locator;
		this.by = by;
	}
	
	public Locator locator() {
		return locator;
	}
	
	public By by() {
		return by;
	}
	
	public void setLocator(Locator value) {
		this.locator = value;
	}
}
