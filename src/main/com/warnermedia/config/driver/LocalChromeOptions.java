package com.warnermedia.config.driver;

import org.openqa.selenium.chrome.ChromeOptions;

public class LocalChromeOptions {
	
	private LocalChromeOptions() {
		
	}

    private static ThreadLocal < ChromeOptions > cOptions = new ThreadLocal <> ();

    public static ChromeOptions get() {
        return cOptions.get();
    }

    public static void set(ChromeOptions indriver) {
        cOptions.set(indriver);
    }
}