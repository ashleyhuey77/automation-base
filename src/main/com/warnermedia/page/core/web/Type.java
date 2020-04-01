package com.warnermedia.page.core.web;

import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;

public interface Type {
	
	public Locator locator();
	
	public By by();
	
	public TestElement element();

}