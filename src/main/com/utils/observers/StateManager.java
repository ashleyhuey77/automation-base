package com.utils.observers;

import com.utils.observers.page.PageState;

public class StateManager {
	
	private static ThreadLocal<PageState> state = new ThreadLocal<>();
	
	public static void setState(PageState value) {
		state.set(value);
	}
	
	public static PageState getState() {
		return state.get();
	}

}
