package commonClasses.sharedUtils.states;

import org.openqa.selenium.chrome.ChromeOptions;

import commonClasses.sharedUtils.interfaces.State;

public class HeadlessStopState implements State {

	@Override
	public void doAction(ChromeOptions options) {
		System.out.println("Headless mode is disabled for the following test.");
	}

}
