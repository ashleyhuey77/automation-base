package common.utils.states;

import common.utils.TestUtils;
import common.utils.interfaces.State;

public class Linux implements State {

	@Override
	public void doAction() {
		System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/lchromedriver");
	}

}