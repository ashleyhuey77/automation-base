package com.warnermedia.config.driver;

import com.warnermedia.config.TestException;
import com.warnermedia.config.settings.LocalTest;
import org.openqa.selenium.WebDriver;

/**
 * <h2>LocalDriver</h2>
 * <p>The LocalDriver class is used to get and set the threadsafe instance of 
 * WebDriver.</p>
 * @author ashleyhuey
 *
 */
public class LocalDriver {

    private static ThreadLocal<WebDriver> THREAD_LOCAL = new ThreadLocal();
    private static Object mutex = new Object();

    public static WebDriver getDriver() {
        WebDriver localRef = THREAD_LOCAL.get();
        try {
            if (localRef == null) {
                synchronized (mutex) {
                    localRef = THREAD_LOCAL.get();
                    if (localRef == null) {
                        localRef = DriverFacade
                                .getDriver(Drivers.valueOf(LocalTest.getEnvironment().getBrowser().toUpperCase().trim()));
                        THREAD_LOCAL.set(localRef);
                    }
                }
            }
        } catch (Exception e) {
            try {
                throw e;
            } catch (TestException ex) {
                ex.printStackTrace();
            }
        }
        return localRef;
    }

    public static void quitDriver() {
        WebDriver localRef = THREAD_LOCAL.get();
        if (localRef != null) {
            synchronized (mutex) {
                localRef = THREAD_LOCAL.get();
                if (localRef != null) {
                    THREAD_LOCAL.get().quit();
                    THREAD_LOCAL.remove();
                }
            }
        }
    }
}
