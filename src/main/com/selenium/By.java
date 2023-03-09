package com.selenium;

import org.openqa.selenium.support.How;

public class By {
	How value;
	
	public By(How types) {
		value = types;
	}
	
	public By(String value) {
		this.value = How.valueOf(value.trim().toUpperCase());
	}
	
	public How value() {
		return value;
	}
}
