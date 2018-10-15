package common.utils.managers;

import org.openqa.selenium.chrome.ChromeOptions;

public class LocalChromeOptions {
	
	private LocalChromeOptions() {
		throw new IllegalStateException("Local Chrome Options");
	}

    private static ThreadLocal < ChromeOptions > cOptions = new ThreadLocal <> ();

    public static ChromeOptions get() {
        return cOptions.get();
    }

    public static void set(ChromeOptions indriver) {
        cOptions.set(indriver);
    }
}