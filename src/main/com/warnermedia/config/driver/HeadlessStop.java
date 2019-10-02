package com.warnermedia.config.driver;

import java.util.logging.Level;
import com.warnermedia.config.State;
import com.warnermedia.utils.Log;

public class HeadlessStop implements State {

	@Override
	public void doAction() {
		Log.get().log(Level.INFO, "Headless mode is disabled for the following test.");
	}

}
