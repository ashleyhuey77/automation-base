package common.utils.contexts;

import common.utils.interfaces.State;

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