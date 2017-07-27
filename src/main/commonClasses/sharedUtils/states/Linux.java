package commonClasses.sharedUtils.states;

import commonClasses.sharedUtils.TestUtils;
import commonClasses.sharedUtils.interfaces.State;

public class Linux implements State{

	@Override
	public void doAction() {
		try 
		{
			System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/lchromedriver");
		} 
		catch (Exception e) 
		{
			throw e;
		}
	}

}