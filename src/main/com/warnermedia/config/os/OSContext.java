package com.warnermedia.config.os;

import com.warnermedia.config.State;

public class OSContext implements State{

	private State osState;
	
	public void setState(State state) {
		this.osState=state;
	}
	
	public State getState() {
		return this.osState;
	}
	
	@Override
	public void doAction() {
		this.osState.doAction();	
	}

}
