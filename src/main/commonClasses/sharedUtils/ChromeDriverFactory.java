package commonClasses.sharedUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeDriverFactory {
	
	public ThreadLocal<WebDriver> driver;
	private Set<WebDriver> drivers = Collections.newSetFromMap(new ConcurrentHashMap<>());
	
	public ChromeDriverFactory(Boolean isHeadless) throws IOException
	 {
		System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
    	System.setProperty("java.awt.headless", Boolean.toString(isHeadless));
    	DesiredCapabilities caps = DesiredCapabilities.chrome();
    	caps.setJavascriptEnabled(true);
    	caps.setCapability("takesScreenshot", true);
    	ChromeOptions options = new ChromeOptions();
    	if (isHeadless)
    	{
    		options.addArguments("headless");
    		options.addArguments("start-fullscreen");
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
