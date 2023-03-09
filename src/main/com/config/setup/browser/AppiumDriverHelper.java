/*
package com.warnermedia.config.driver;

import com.warnermedia.config.State;
import com.warnermedia.config.TestException;
import com.warnermedia.config.os.OS;
import com.warnermedia.config.os.OSFacade;
import com.warnermedia.config.settings.LocalTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AppiumDriverHelper {

    public InheritableThreadLocal<AppiumDriver> driver;
    private static final Set<AppiumDriver> drivers = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public AppiumDriverHelper() throws TestException, MalformedURLException {
        OSFacade.setDriverLocalPathBasedOnOS(OS.valueOf(LocalTest.getEnvironment().getOS().toUpperCase()));
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", "Mac");
        caps.setCapability("deviceName", "Mac");

        caps.setCapability("app", "Adobe Premiere Pro CC 2017");
        caps.setCapability("newCommandTimeout", 300);

        driver = new InheritableThreadLocal<AppiumDriver>() {
            @Override
            protected AppiumDriver initialValue() {
                AppiumDriver driver = null;
                try {
                    AppiumDriver aDriver = new AppiumDriver(new URL("http://localhost:4723/wd/hub"), caps);
                    drivers.add(aDriver);
                    driver = aDriver;
                } catch (Exception e) {
                    System.out.println(e);
                }
                return driver;
            }
        };
    }

}
*/
