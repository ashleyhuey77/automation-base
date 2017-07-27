package commonClasses.sharedUtils.states;

import org.openqa.selenium.chrome.ChromeOptions;

import commonClasses.sharedUtils.interfaces.State;

public class HeadlessStartState implements State {

	@Override
	public void doAction(ChromeOptions options) {
		options.addArguments("headless");
		options.addArguments("disable-gpu");
		System.out.println("Headless mode has been enabled. All tests will run in headless Chrome.");
	}

}
