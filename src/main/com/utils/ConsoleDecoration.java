package com.utils;

public enum ConsoleDecoration {

	RED_TEXT("\u001B[31m"),
	BLACK_TEXT("\u001B[30m"),
	GREEN_TEXT("\033[92m"),
	CYAN_TEXT("\u001B[36m"),
	WHITE_TEXT("\033[97m"),
	RESET("\u001B[0m"),
	BOLD_TEXT("\u001b[1m"),
	BLACK_BACKGROUND("\u001b[100;1m"),
	RED_BACKGROUND("\033[101;1m"),
	GREEN_BACKGROUND("\u001b[102;2m"),
	CYAN_BACKGROUND("\u001b[46;2m"),
	WHITE_BACKGROUND("\u001b[47;2m");

	public final String value;

	ConsoleDecoration(String value) {
		this.value = value;
	}
}
