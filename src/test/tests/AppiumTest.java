package tests;

import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.driver.WebDriverListener;
import com.warnermedia.page.core.PageUtils;
import com.warnermedia.page.core.web.Type;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.SeleniumHelper;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;
import org.openqa.selenium.support.How;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.TestInitialization;

@Listeners(WebDriverListener.class)
public class AppiumTest  extends TestInitialization {

    @BeforeMethod
    public void setup() {
        SHelper.set(new SeleniumHelper());
    }

    public enum Variables implements Type {
        PROJECT_BUTTON {
            @Override
            public TestElement element() {
                return new TestElement(new Locator("/AXApplication[@AXTitle='Premiere Pro CC']/AXWindow[@AXTitle='Start' and @AXSubrole='AXFloatingWindow']"), new By(How.XPATH));
            }
        },
        FILE_BUTTON {
            @Override
            public TestElement element() {
                return new TestElement(new Locator("/AXApplication[@AXTitle='Premiere Pro CC']/AXWindow[@AXSubrole='AXFloatingWindow']"), new By(How.XPATH));
            }
        },
        NEW_BUTTON {
            @Override
            public TestElement element() {
                return new TestElement(new Locator("/AXApplication[@AXTitle='Premiere Pro CC']/AXMenuBar[0]/AXMenuBarItem[@AXTitle='File']/AXMenu[0]/AXMenuItem[@AXTitle='New']"), new By(How.XPATH));
            }
        }
    }

    @Test
    public void test() throws TestException, InterruptedException {
        try {
            String stuff = LocalDriver.getDriver().getPageSource();
            System.out.println(stuff);
        } catch (Exception e) {
            throw e;
        }
    }
}
