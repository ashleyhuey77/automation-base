package common.utils.states;

import java.util.logging.Level;
import common.utils.TestUtils;
import common.utils.interfaces.State;
import common.utils.managers.LocalChromeOptions;
import log.Log;

public class HeadlessStart implements State {

	@Override
	public void doAction() {
		LocalChromeOptions.get().addArguments("headless");
		LocalChromeOptions.get().addArguments("disable-gpu");
		LocalChromeOptions.get().addArguments("window-size=1920,1080");
		LocalChromeOptions.get().addArguments("disable-infobars"); // disabling infobars
		LocalChromeOptions.get().addArguments("--disable-extensions"); // disabling extensions
		LocalChromeOptions.get().addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		LocalChromeOptions.get().addArguments("--no-sandbox");
		Log.get().log(Level.INFO, "Headless mode has been enabled. All tests will run in headless Chrome.");
	}

}
