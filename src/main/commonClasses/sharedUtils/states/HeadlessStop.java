package commonClasses.sharedUtils.states;

import commonClasses.sharedUtils.interfaces.State;

public class HeadlessStop implements State {

	@Override
	public void doAction() {
		System.out.println("Headless mode is disabled for the following test.");
	}

}
