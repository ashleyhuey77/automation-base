package com.utils.devtools;

public class LocalErrorCallback {

	private static ThreadLocal<ConsoleErrorLogger> logger = new ThreadLocal<>();

	public static ConsoleErrorLogger get() {
		return logger.get();
	}

	public static void set(ConsoleErrorLogger value) {
		logger.set(value);
	}

	public static Boolean isLoggerOn() {
		Boolean result = false;
		if (logger.get() != null) {
			result = true;
		}
		return result;
	}
}
