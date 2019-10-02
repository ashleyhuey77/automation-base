package com.warnermedia.config.driver;

import java.util.logging.Level;
import com.warnermedia.config.State;
import com.warnermedia.utils.Log;

public class HeadlessStart implements State {

	@Override
	public void doAction() {
		LocalChromeOptions.get().addArguments("headless");
		LocalChromeOptions.get().addArguments("window-size=1920,1080");
		Log.get().log(Level.INFO, "Headless mode has been enabled. All tests will run in headless Chrome.");
	}

}
