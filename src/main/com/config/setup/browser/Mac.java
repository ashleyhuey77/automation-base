package com.config.setup.browser;

import com.config.setup.app.State;
import com.utils.TestUtils;

public class Mac implements State {

	@Override
	public void doAction() {
		System.setProperty("webdriver.chrome.driver",
				TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
	}

}