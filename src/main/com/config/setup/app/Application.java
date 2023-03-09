package com.config.setup.app;

import com.config.TestException;

public interface Application {
	
	public Object openApplication() throws Exception;
	
	public void initializeBrowserName() throws TestException;
	
	public void initializeEnvironment() throws TestException;

	public void initializeTestData() throws TestException;
	
	public void initializeBrowser() throws TestException;
	
	public void initializeReporting() throws TestException;
	
}
