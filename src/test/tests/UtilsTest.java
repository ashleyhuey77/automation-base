package tests;

import com.app.SecurityHelper;
import com.app.file.FileCredentials;
import com.app.file.FileCredentialsType;
import com.app.file.FileEncrypterDecrypter;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.driver.WebDriverListener;
import com.warnermedia.page.core.web.BaseGeneric;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.SeleniumHelper;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.utils.devtools.JSTool;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.TestInitialization;
import utils.Variables;

import java.net.URI;
import java.util.function.Predicate;

@Listeners(WebDriverListener.class)
public class UtilsTest extends TestInitialization {

    @BeforeMethod
    public void setup() {
        SHelper.set(new SeleniumHelper());
    }

    @Test
    public void testJSTool() throws Exception {
        try {
            ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("document.write('<a id=\"Test\" >Butter</a>');");
            Thread.sleep(500);
            JSTool tool = JSTool.INSTANCE;
            tool.start();
            WebElement link2click = SHelper.get().element().get(new TestElement(new Locator("Test"), new By(How.ID)));
            ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                    link2click, "onclick", "throw new Error('Hello, world!')");
            link2click.click();
            tool.parse();
        } catch (Exception e) {
            throw e;
        }
    }

}
