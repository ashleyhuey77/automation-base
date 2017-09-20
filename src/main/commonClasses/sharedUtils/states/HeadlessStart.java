package commonClasses.sharedUtils.states;

import commonClasses.sharedUtils.interfaces.State;
import commonClasses.sharedUtils.managers.LocalChromeOptions;

public class HeadlessStart implements State {

	@Override
	public void doAction() {
		LocalChromeOptions.get().addArguments("headless");
		LocalChromeOptions.get().addArguments("disable-gpu");
		LocalChromeOptions.get().addArguments("window-size=1920,1080");
		System.out.println("Headless mode has been enabled. All tests will run in headless Chrome.");
	}

}
