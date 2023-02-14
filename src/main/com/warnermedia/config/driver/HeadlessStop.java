package com.warnermedia.config.driver;

import java.util.logging.Level;
import com.warnermedia.config.State;
import com.warnermedia.utils.ConsoleDecoration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeadlessStop implements State {

	@Override
	public void doAction() {

		log.info("{}{}Headless mode is disabled for the following test.{}", ConsoleDecoration.CYAN_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, ConsoleDecoration.RESET.value);
	}

}
