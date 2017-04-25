package commonClasses.sharedUtils;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverFactory {
	
	public ThreadLocal<WebDriver> driver;
	private Set<WebDriver> drivers = Collections.newSetFromMap(new ConcurrentHashMap<>());
	
	 {
		System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
    	System.setProperty("java.awt.headless", "false");
		
		driver = new InheritableThreadLocal<WebDriver>(){
			@Override
			protected ChromeDriver initialValue() {
                ChromeDriver chromeDriver = new ChromeDriver();
                drivers.add(chromeDriver);
                return chromeDriver;
            }
		};
	}

}
