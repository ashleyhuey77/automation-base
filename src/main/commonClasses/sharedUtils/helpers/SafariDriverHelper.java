package commonClasses.sharedUtils.helpers;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SafariDriverHelper {
	
	public static final ThreadLocal<SafariDriver> driver;
	private static final Set<SafariDriver> drivers = Collections.newSetFromMap(new ConcurrentHashMap<>());
	
	static {
		
		SafariOptions safariOptions = new SafariOptions();
		
		driver = new ThreadLocal<SafariDriver>(){
			@Override
			protected SafariDriver initialValue() {
				SafariDriver safariDriver = new SafariDriver(safariOptions);
                drivers.add(safariDriver);
                return safariDriver;
            }
		};
	}

}
