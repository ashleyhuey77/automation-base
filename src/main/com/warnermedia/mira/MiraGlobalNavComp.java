package com.warnermedia.mira;

import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.mira.web.MiraGlobalNav;
import com.warnermedia.selenium.browser.BrowserObject;
import org.openqa.selenium.WebElement;

public class MiraGlobalNavComp extends AbstractMiraPage {

    public MiraGlobalNavComp() {

    }

    public MiraGlobalNavComp goToTheServerFulfillmentComp() throws TestException {
        try {
            switchToCommandBar();
            WebElement parent = SHelper.get().element().find(MiraGlobalNav.SRVR_FULFILLMENT_TAB.element(), MiraGlobalNav.PARENT.element());
            click().on(MiraGlobalNav.SRVR_FULFILLMENT_TAB).using(parent).start();
            Thread.sleep(2000);
            SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
            waitForFrameToBePresent("TaskArea");
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }

    public MiraGlobalNavComp goToTheCatalogComp() throws TestException {
        try {
            switchToCommandBar();
            click().on(MiraGlobalNav.SRVR_FULFILLMENT_TAB).start();
            Thread.sleep(2000);
            SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
            waitForFrameToBePresent("TaskArea");
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }
}
