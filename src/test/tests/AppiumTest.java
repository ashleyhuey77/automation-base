package tests;

import com.config.SHelper;
import com.config.TestException;
import com.config.setup.browser.LocalDriver;
import com.config.setup.browser.WebDriverListener;
import com.page.core.web.Type;
import com.selenium.By;
import com.selenium.Locator;
import com.selenium.SeleniumHelper;
import com.selenium.TestElement;
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
