package tests;

import com.warnermedia.wdm.utils.OS;
import com.warnermedia.wdm.WebDriverManager;
import org.testng.annotations.Test;

public class WebDriverManagerTests {

    @Test
    public void verifyWebDriverManagerSetup() throws Exception {
        new WebDriverManager().chromedriver().setup();
    }
}
