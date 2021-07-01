package com.warnermedia.mira;

import com.warnermedia.config.TestException;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.mira.web.MiraGlobalNav;

public class MiraGlobalNavComp extends AbstractMiraPage {

    public MiraGlobalNavComp() {

    }

    public MiraGlobalNavComp goToTheServerFulfillmentComp() throws TestException {
        try {
            switchToCommandBar();
            click().on(MiraGlobalNav.SRVR_FULFILLMENT_TAB).start();
            Thread.sleep(2000);
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
            waitForFrameToBePresent("TaskArea");
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }
}
