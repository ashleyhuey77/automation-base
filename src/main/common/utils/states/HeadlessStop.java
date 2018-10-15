package common.utils.states;

import java.util.logging.Level;
import common.utils.interfaces.State;
import log.Log;

public class HeadlessStop implements State {

	@Override
	public void doAction() {
		Log.get().log(Level.INFO, "Headless mode is disabled for the following test.");
	}

}
