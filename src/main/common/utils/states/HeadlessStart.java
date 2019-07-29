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
		LocalChromeOptions.get().addArguments("window-size=1920,1080");
		Log.get().log(Level.INFO, "Headless mode has been enabled. All tests will run in headless Chrome.");
	}

}
