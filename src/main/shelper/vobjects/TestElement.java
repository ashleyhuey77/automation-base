package shelper.vobjects;

public class TestElement {
	public Locator locator;
	public By by;
	
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
}
