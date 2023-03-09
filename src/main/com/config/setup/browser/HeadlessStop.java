package com.config.setup.browser;

import com.config.setup.app.State;
import com.utils.ConsoleDecoration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeadlessStop implements State {

	@Override
	public void doAction() {

		log.info("{}{}Headless mode is disabled for the following test.{}", ConsoleDecoration.CYAN_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, ConsoleDecoration.RESET.value);
	}

}
