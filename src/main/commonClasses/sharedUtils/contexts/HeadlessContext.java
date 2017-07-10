package commonClasses.sharedUtils.contexts;

import org.openqa.selenium.chrome.ChromeOptions;

import commonClasses.sharedUtils.interfaces.State;

public class HeadlessContext implements State {

	private State headlessState;
	
	public void setState(State state) {
		this.headlessState=state;
	}
	
	public State getState() {
		return this.headlessState;
	}
	
	@Override
	public void doAction(ChromeOptions options) {
		this.headlessState.doAction(options);	
	}

}
