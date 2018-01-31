package common.utils.states;

import common.utils.TestUtils;
import common.utils.interfaces.State;
import common.utils.managers.LocalChromeOptions;

public class HeadlessStart implements State {

	@Override
	public void doAction() {
		LocalChromeOptions.get().addArguments("headless");
		LocalChromeOptions.get().addArguments("disable-extensions-except=");
		LocalChromeOptions.get().addArguments("load-extension=" + TestUtils.getRelativePath() + "/externalLibraries/Native-Message-Sender_v1.1.crx");
		LocalChromeOptions.get().addArguments("disable-gpu");
		LocalChromeOptions.get().addArguments("window-size=1920,1080");
		System.out.println("Headless mode has been enabled. All tests will run in headless Chrome.");
	}

}
