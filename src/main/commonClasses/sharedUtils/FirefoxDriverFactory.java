package commonClasses.sharedUtils;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverFactory {
	
	public static final InheritableThreadLocal<FirefoxDriver> driver;
	private static final Set<FirefoxDriver> drivers = Collections.newSetFromMap(new ConcurrentHashMap<>());
	
	static {
		
		System.setProperty("webdriver.gecko.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/geckodriver");
    	System.setProperty("java.awt.headless", "false");
		
		driver = new InheritableThreadLocal<FirefoxDriver>(){
			@Override
			protected FirefoxDriver initialValue() {
				FirefoxDriver ffDriver = new FirefoxDriver();
                drivers.add(ffDriver);
                return ffDriver;
            }
		};
	}


}
