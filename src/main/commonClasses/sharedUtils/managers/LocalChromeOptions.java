package commonClasses.sharedUtils.managers;

import org.openqa.selenium.chrome.ChromeOptions;

public class LocalChromeOptions {

    private static ThreadLocal < ChromeOptions > cOptions = new ThreadLocal < ChromeOptions > ();

    public static ChromeOptions get() {
        return cOptions.get();
    }

    public static void set(ChromeOptions indriver) {
        cOptions.set(indriver);
    }
}