package com.warnermedia.utils;

public class StateManager {
	
	private static ThreadLocal<State> state = new ThreadLocal<>();
	
	public static void setState(State value) {
		state.set(value);
	}
	
	public static State getState() {
		return state.get();
	}

}
