package com.warnermedia.config.driver;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.warnermedia.config.State;
import com.warnermedia.config.TestException;
import com.warnermedia.config.os.OS;
import com.warnermedia.config.os.OSFacade;
import com.warnermedia.config.settings.LocalTest;

public class ChromeDriverHelper {

	public InheritableThreadLocal<WebDriver> driver;

	public ChromeDriverHelper() throws TestException {
		OSFacade.setDriverLocalPathBasedOnOS(OS.valueOf(LocalTest.getEnvironment().getOS().toUpperCase()));
		if (LocalTest.getIsLoggingEnabled() != null
				&& LocalTest.getIsLoggingEnabled()) {
			System.setProperty("webdriver.chrome.verboseLogging", "true");
		}
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("takesScreenshot", true);
		LocalChromeOptions.set(new ChromeOptions());
		HeadlessContext context = new HeadlessContext();
		if (LocalTest.getEnvironment().isHeadlessEnabled()) {
			State headlessStartState = new HeadlessStart();
			context.setState(headlessStartState);
			context.doAction();
		} else {
			State headlessStopState = new HeadlessStop();
			context.setState(headlessStopState);
			context.doAction();
		}
		LocalChromeOptions.get().setPageLoadStrategy(PageLoadStrategy.NONE);
		LocalChromeOptions.get().addArguments("start-maximized");
		LocalChromeOptions.get().addArguments("enable-automation");
		LocalChromeOptions.get().addArguments("--no-sandbox");
		LocalChromeOptions.get().addArguments("--disable-infobars");
		LocalChromeOptions.get().addArguments("--disable-dev-shm-usage");
		LocalChromeOptions.get().addArguments("--disable-browser-side-navigation");
		LocalChromeOptions.get().addArguments("--disable-gpu");
		LocalChromeOptions.get().merge(caps);

		driver = new InheritableThreadLocal<WebDriver>() {
			@Override
			protected ChromeDriver initialValue() {
				ChromeDriver driver = null;
				try {
    				ChromeDriver chromeDriver = new ChromeDriver(LocalChromeOptions.get());
    				driver = chromeDriver;
				} catch (Exception e) {
					System.out.println(e);
				}
				return driver;
			}
		};
	}

}
