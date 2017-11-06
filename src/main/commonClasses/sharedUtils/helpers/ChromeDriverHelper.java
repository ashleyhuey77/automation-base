package commonClasses.sharedUtils.helpers;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import commonClasses.sharedUtils.contexts.HeadlessContext;
import commonClasses.sharedUtils.enums.OS;
import commonClasses.sharedUtils.facades.HelperFacade;
import commonClasses.sharedUtils.interfaces.State;
import commonClasses.sharedUtils.managers.*;
import commonClasses.sharedUtils.states.*;

public class ChromeDriverHelper {

    public ThreadLocal < WebDriver > driver;
    private Set < WebDriver > drivers = Collections.newSetFromMap(new ConcurrentHashMap < > ());

    public ChromeDriverHelper() throws Exception {
        HelperFacade.setDriverLocalPathBasedOnOS(OS.valueOf(LocalTest.getEnvironment().getOS().toUpperCase()));
        System.setProperty("java.awt.headless", Boolean.toString(LocalTest.getEnvironment().isHeadlessEnabled()));
        DesiredCapabilities caps = DesiredCapabilities.chrome();
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
        LocalChromeOptions.get().addArguments("disable-extensions");
        caps.setCapability(ChromeOptions.CAPABILITY, LocalChromeOptions.get());

        driver = new InheritableThreadLocal < WebDriver > () {
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