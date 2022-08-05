package com.warnermedia.config.driver;

import com.warnermedia.config.TestException;
import com.warnermedia.config.settings.LocalTest;
import com.warnermedia.utils.ex.ChromeDriverException;
import com.warnermedia.utils.ex.ErrorCode;
import com.warnermedia.utils.ex.FileDownloadException;
import com.warnermedia.utils.ex.MismatchedVersionException;
import com.warnermedia.utils.retry.Retry;
import com.warnermedia.utils.retry.TestOperation;
import com.warnermedia.wdm.download.Download;
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
    private static ThreadLocal<TestOperation> op = new ThreadLocal<>();
    private static Object mutex = new Object();

    public static WebDriver getDriver() {
        WebDriver localRef = THREAD_LOCAL.get();
        try {
            if (localRef == null) {
                synchronized (mutex) {
                    localRef = THREAD_LOCAL.get();
                    if (localRef == null) {
                        TestOperation test = new LaunchBrowser(
                                new ChromeDriverException(ErrorCode.CHROMEDRIVER)
                        );
                        final Retry retry = new Retry(
                                test,
                                4,  //3 attempts
                                1000, //100 ms delay between attempts
                                e -> ChromeDriverException.class.isAssignableFrom(e.getClass())
                        );
                        op.set(retry);
                        localRef = (WebDriver) op.get().perform();
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
