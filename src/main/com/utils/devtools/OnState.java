package com.utils.devtools;

public class OnState implements State {

	private final ConsoleErrorLogger logger;

	public OnState(ConsoleErrorLogger logger) {
		this.logger = logger;
	}

	@Override
	public void onEnterState() {
		LocalErrorCallback.set(logger);
		LocalErrorCallback.get().initialize();
	}

}
