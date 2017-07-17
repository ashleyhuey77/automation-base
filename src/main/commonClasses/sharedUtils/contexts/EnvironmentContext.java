package commonClasses.sharedUtils.contexts;

import commonClasses.sharedUtils.interfaces.EnvironmentState;

public class EnvironmentContext implements EnvironmentState {

	private EnvironmentState envState;
	
	public void setState(EnvironmentState state) {
		this.envState=state;
	}
	
	public EnvironmentState getState() {
		return this.envState;
	}
	
	@Override
	public void doAction() {
		this.envState.doAction();	
	}
}
