package com.utils.devtools;

public class OffState implements State {

	public OffState() {

	}

	@Override
	public void onEnterState() {
		LocalErrorCallback.set(null);
	}

}
