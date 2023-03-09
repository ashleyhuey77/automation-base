package com.config.setup.browser;

import java.io.File;
import java.util.logging.Level;

import com.config.setup.app.State;
import com.config.TestException;
import com.config.setup.app.LocalTest;
import com.utils.TestUtils;
import com.utils.devtools.LocalDevTools;
import com.utils.ex.ChromeDriverException;
import com.utils.ex.ErrorCode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeDriverHelper {

	public InheritableThreadLocal<WebDriver> driver;

	public ChromeDriverHelper() throws TestException {
		try {
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
			LocalChromeOptions.get().addArguments("--start-maximized");
			LocalChromeOptions.get().addArguments("--enable-automation");
			LocalChromeOptions.get().addArguments("--no-sandbox");
			LocalChromeOptions.get().addArguments("--disable-infobars");
			LocalChromeOptions.get().addArguments("--remote-allow-origins=*");
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
			logPrefs.enable(LogType.BROWSER, Level.ALL);
			LocalChromeOptions.get().setCapability( "goog:loggingPrefs", logPrefs );
			if (!LocalTest.getEnvironment().isHeadlessEnabled()) {
				LocalChromeOptions.get().addExtensions(new File(TestUtils.getRelativePath() + "/externalLibraries/asperaConnect.crx"));
			}

			driver = new InheritableThreadLocal<WebDriver>() {
				@Override
				protected ChromeDriver initialValue() {
					ChromeDriver driver = null;
					try {
						ChromeDriver chromeDriver = new ChromeDriver(LocalChromeOptions.get());
						LocalDevTools.setDevTools(chromeDriver.getDevTools());
						driver = chromeDriver;
					} catch (Exception e) {
						throw e;
					}
					return driver;
				}
			};
		} catch (Exception e) {
			throw new ChromeDriverException(e.getMessage(), ErrorCode.CHROMEDRIVER);
		}
	}

	private static ChromeOptions getCap() {
		ChromeOptions caps = new ChromeOptions();
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
		logPrefs.enable(LogType.PROFILER, Level.INFO);
		logPrefs.enable(LogType.BROWSER, Level.INFO);
		logPrefs.enable(LogType.CLIENT, Level.INFO);
		logPrefs.enable(LogType.DRIVER, Level.INFO);
		logPrefs.enable(LogType.SERVER, Level.INFO);
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		return caps;
	}

}
