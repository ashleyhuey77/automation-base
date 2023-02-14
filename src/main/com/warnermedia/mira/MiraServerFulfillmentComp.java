package com.warnermedia.mira;

import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.report.LocalValidation;
import com.warnermedia.mira.web.MiraGlobalNav;
import com.warnermedia.mira.web.inputs.MiraServerFulfillment;
import com.warnermedia.selenium.browser.BrowserObject;
import com.warnermedia.selenium.wait.Condition;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;
import com.warnermedia.utils.ConsoleDecoration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Slf4j
public class MiraServerFulfillmentComp extends AbstractMiraPage {

    public MiraServerFulfillmentComp() {

    }

    public MiraServerFulfillmentComp verifyTheStatusOfTheFulfillment(String cnnID, String status) throws TestException {
        try {
            switchToServerTaskArea();
            waitForStatusToExist(cnnID, status);
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }

    private void waitForStatusToExist(String cnnId, String status) throws TestException {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), Duration.ofMinutes(8));

            wait.until((WebDriver driver) -> {
                Boolean result = false;
                try {
                    Thread.sleep(5000);
                    List<WebElement> rows = SHelper.get().element().getListOf(MiraServerFulfillment.REQUEST_ROWS.element());
                    if (rows.isEmpty()) {
                        log.info("{}{}The list of rows was not found with the given selector.{}", ConsoleDecoration.CYAN_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, ConsoleDecoration.RESET.value);
                    }
                    WebElement row = find(rows).that(Condition.CONTAIN).text(cnnId).get();
                    String text = row.getText().toLowerCase();
                    if (text.contains(status.toLowerCase())) {
                        result = true;
                        LocalValidation.getValidations().assertionPass(log, status + " appears for the fulfillment as expected.");
                    } else {
                        result = false;
                        click().on(MiraServerFulfillment.REFRSH_BTN).start();
                    }
                } catch (Exception ex) {
                    try {
                        result = false;
                        click().on(MiraServerFulfillment.REFRSH_BTN).start();
                    } catch (Exception e) {
                        log.info("{}{}Status is not as expected. Retrying...{}", ConsoleDecoration.CYAN_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, ConsoleDecoration.RESET.value);
                    }
                }
                return result;
            });
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }
}
