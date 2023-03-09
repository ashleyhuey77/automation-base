package com.utils.devtools;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ConsoleErrorLogger<V> extends Task<V> {

	private State state;

	public ConsoleErrorLogger(V value) {
		className = value;
	}
	public ConsoleErrorLogger() {

	}

	@Override
	public void execute() {
		try {
			JSTool.INSTANCE.parse();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public void setState(Switch future) {
		if (future.equals(Switch.ON)) {
			changeStateTo(new OnState(this));
		} else {
			changeStateTo(new OffState());
		}
	}

	private void changeStateTo(State newState) {
		this.state = newState;
		this.state.onEnterState();
	}

	public void initialize() {
		JSTool.INSTANCE.start((String) className);
	}

}
