package com.warnermedia.config.os;

import com.warnermedia.config.State;
import com.warnermedia.utils.TestUtils;

public class Windows implements State {

	@Override
	public void doAction() {
		System.setProperty("webdriver.chrome.driver",
				TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver.exe");
	}

}