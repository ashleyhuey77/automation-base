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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), Duration.ofMinutes(5));

            wait.until((WebDriver driver) -> {
                Boolean result = false;
                try {
                    Thread.sleep(5000);
                    List<WebElement> rows = SHelper.get().element().getListOf(MiraServerFulfillment.REQUEST_ROWS.element());
                    if (rows.isEmpty()) {
                        System.out.println("The list of rows was not found with the given selector.");
                    }
                    WebElement row = find(rows).that(Condition.CONTAIN).text(cnnId).get();
                    if (row == null) {
                        System.out.println("The row was not found with cnnid " + cnnId + " and status " + status);
                    }
                    if (row.getText().contains(status)) {
                        result = true;
                        LocalValidation.getValidations().assertionPass(status + " appears for the fulfillment as expected.");
                    } else {
                        result = false;
                        click().on(MiraServerFulfillment.REFRSH_BTN).start();
                    }
                } catch (Exception ex) {
                    try {
                        result = false;
                        click().on(MiraServerFulfillment.REFRSH_BTN).start();
                    } catch (Exception e) {
                        System.out.println("Status is not as expected. Retrying...");
                    }
                }
                return result;
            });
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }
}
