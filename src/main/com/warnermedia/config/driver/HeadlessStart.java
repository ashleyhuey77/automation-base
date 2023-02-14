package com.warnermedia.config.driver;

import java.util.logging.Level;
import com.warnermedia.config.State;
import com.warnermedia.utils.ConsoleDecoration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeadlessStart implements State {

	@Override
	public void doAction() {
		LocalChromeOptions.get().addArguments("--headless");
		LocalChromeOptions.get().addArguments("--window-size=1920,1080");
		LocalChromeOptions.get().addArguments("--force-device-scale-factor=1");
		log.info("{}{}Headless mode has been enabled. All tests will run in headless Chrome.{}", ConsoleDecoration.CYAN_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, ConsoleDecoration.RESET.value);
	}

}
