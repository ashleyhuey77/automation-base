package commonClasses.sharedUtils.helpers;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import commonClasses.sharedUtils.TestUtils;
import commonClasses.sharedUtils.contexts.HeadlessContext;
import commonClasses.sharedUtils.interfaces.State;
import commonClasses.sharedUtils.states.HeadlessStartState;
import commonClasses.sharedUtils.states.HeadlessStopState;

public class ChromeDriverHelper {
	
	public ThreadLocal<WebDriver> driver;
	private Set<WebDriver> drivers = Collections.newSetFromMap(new ConcurrentHashMap<>());
	
	public ChromeDriverHelper(Boolean isHeadless) throws IOException
	 {
		System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
    	System.setProperty("java.awt.headless", Boolean.toString(isHeadless));
    	DesiredCapabilities caps = DesiredCapabilities.chrome();
    	caps.setJavascriptEnabled(true);
    	caps.setCapability("takesScreenshot", true);
    	ChromeOptions options = new ChromeOptions();
    	HeadlessContext context = new HeadlessContext();
    	if (isHeadless)
    	{
    		State headlessStartState = new HeadlessStartState();
    		context.setState(headlessStartState);
    		context.doAction(options);
    	}
    	else 
    	{
			State headlessStopState = new HeadlessStopState();
			context.setState(headlessStopState);
			context.doAction(options);
		}
    	options.addArguments("disable-extensions");
    	caps.setCapability(ChromeOptions.CAPABILITY, options);
		
		driver = new InheritableThreadLocal<WebDriver>(){
			@Override
			protected ChromeDriver initialValue() {
                ChromeDriver chromeDriver = new ChromeDriver(caps);
                drivers.add(chromeDriver);
                return chromeDriver;
            }
		};
	}

}
