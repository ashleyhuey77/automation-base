package common.utils.helpers;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import common.utils.TestUtils;
import common.utils.contexts.HeadlessContext;
import common.utils.enums.OS;
import common.utils.facades.HelperFacade;
import common.utils.interfaces.State;
import common.utils.managers.*;
import common.utils.states.*;
import log.TestException;

public class ChromeDriverHelper {

	public InheritableThreadLocal<WebDriver> driver;
	private static final Set<WebDriver> drivers = Collections.newSetFromMap(new ConcurrentHashMap<>());

	public ChromeDriverHelper() throws TestException {
		HelperFacade.setDriverLocalPathBasedOnOS(OS.valueOf(LocalTest.getEnvironment().getOS().toUpperCase()));
		System.setProperty("java.awt.headless", Boolean.toString(LocalTest.getEnvironment().isHeadlessEnabled()));
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
			File file = new File(TestUtils.getRelativePath() + "/externalLibraries/Native-Message-Sender_v1.1.crx");
			if (file.exists()) {
				LocalChromeOptions.get().addExtensions(
						new File(TestUtils.getRelativePath() + "/externalLibraries/Native-Message-Sender_v1.1.crx"));
			}
		}
		LocalChromeOptions.get().addArguments("--disable-dev-shm-usage");
		LocalChromeOptions.get().addArguments("--no-sandbox");
		LocalChromeOptions.get().addArguments("--disable-gpu");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		LocalChromeOptions.get().setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
		LocalChromeOptions.get().setExperimentalOption("prefs", prefs);
		LocalChromeOptions.get().merge(caps);
		caps.setCapability(ChromeOptions.CAPABILITY, LocalChromeOptions.get());

		driver = new InheritableThreadLocal<WebDriver>() {
			@Override
			protected ChromeDriver initialValue() {
				@SuppressWarnings("deprecation")
				ChromeDriver chromeDriver = new ChromeDriver(caps);
				drivers.add(chromeDriver);
				return chromeDriver;
			}
		};
	}

}
