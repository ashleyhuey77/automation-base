package tests;

import com.wdm.WebDriverManager;
import org.testng.annotations.Test;

public class WebDriverManagerTests {

    @Test
    public void verifyWebDriverManagerSetup() throws Exception {
        new WebDriverManager().chromedriver().setup();
    }
}
