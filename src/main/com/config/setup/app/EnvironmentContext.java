package com.config.setup.app;

public class EnvironmentContext implements State {

	private State envState;
	
	public void setState(State state) {
		this.envState=state;
	}
	
	public State getState() {
		return this.envState;
	}
	
	@Override
	public void doAction() {
		this.envState.doAction();	
	}
}
