package seleniumHelper.valueObjects;

public class Locator {
	String value;
	
	public Locator(String locator) {
		value = locator;
	}
	
	public String value() {
		return value;
	}
}
