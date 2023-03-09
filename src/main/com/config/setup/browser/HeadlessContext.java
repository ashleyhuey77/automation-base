package com.config.setup.browser;

import com.config.setup.app.State;

public class HeadlessContext implements State {

	private State headlessState;
	
	public void setState(State state) {
		this.headlessState=state;
	}
	
	public State getState() {
		return this.headlessState;
	}
	
	@Override
	public void doAction() {
		this.headlessState.doAction();	
	}

}
