package tests;

import com.config.SHelper;
import com.config.setup.browser.LocalDriver;
import com.config.setup.browser.WebDriverListener;
import com.selenium.By;
import com.selenium.Locator;
import com.selenium.SeleniumHelper;
import com.selenium.TestElement;
import com.utils.ConsoleDecoration;
import com.utils.devtools.ConsoleErrorLogger;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.TestInitialization;

@Slf4j
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
            ConsoleErrorLogger task = new ConsoleErrorLogger<String>("Testing");
            task.initialize();
            WebElement link2click = SHelper.get().element().get(new TestElement(new Locator("Test"), new By(How.ID)));
            ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                    link2click, "onclick", "throw new Error('Hello, world!')");
            link2click.click();
            task.executeWith(() -> log.info("{}{}Console error task has completed successfully.{}", ConsoleDecoration.CYAN_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, ConsoleDecoration.RESET.value));
        } catch (Exception e) {
            throw e;
        }
    }

}
