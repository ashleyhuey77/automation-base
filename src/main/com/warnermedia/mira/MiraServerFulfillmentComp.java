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
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), 300);

            wait.until((WebDriver driver) -> {
                Boolean result = false;
                try {
                    SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(10)).on(MiraServerFulfillment.REQUEST_ROWS.element());
                    List<WebElement> rows = SHelper.get().element().getListOf(MiraServerFulfillment.REQUEST_ROWS.element());
                    WebElement row = find(rows).that(Condition.CONTAIN).text(cnnId).get();
                    if (row.getText().contains(status)) {
                        result = true;
                        LocalValidation.getValidations().assertionPass(status + " appears for the fulfillment as expected.");
                    } else {
                        result = false;
                        SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
                        switchToCommandBar();
                        click().on(MiraGlobalNav.SRVR_FULFILLMENT_TAB).start();
                        SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
                        waitForFrameToBePresent("TaskArea");
                        switchToServerTaskArea();
                    }
                } catch (Exception ex) {
                    try {
                        result = false;
                        SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
                        switchToCommandBar();
                        click().on(MiraGlobalNav.SRVR_FULFILLMENT_TAB).start();
                        SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
                        waitForFrameToBePresent("TaskArea");
                        switchToServerTaskArea();
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
