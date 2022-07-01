package com.warnermedia.mira;

import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.mira.web.inputs.MiraAdmin;
import com.warnermedia.selenium.browser.BrowserObject;
import com.warnermedia.selenium.wait.Condition;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;

import java.time.Duration;

public class MiraAdminComp extends AbstractMiraPage {

    public MiraAdminComp() {

    }

    public MiraAdminComp selectTheRequestQueueOption() throws TestException {
        try {
            SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
            waitForFrameToBePresent("TaskArea");
            waitForFrameToBePresent("leftnav");
            click().on(MiraAdmin.DAM_ADMIN_DROPDOWN).start();
            Thread.sleep(1000);
            click().on(MiraAdmin.RESUBMIT_BTN).start();
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }

    public MiraAdminComp selectTheAdminOmneonArchivesOption() throws TestException {
        try {
            SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
            waitForFrameToBePresent("TaskArea");
            waitForFrameToBePresent("workarea");
            click().on(MiraAdmin.ADMIN_OMNEON_ARCHIVES_OPT).start();
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }

    public MiraAdminComp resubmitTheAssetAndForceATLArchive(String cnnid) throws TestException {
        try {
            SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
            waitForFrameToBePresent("TaskArea");
            waitForFrameToBePresent("workarea");
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(30))).on(MiraAdmin.CNN_ID_FIELD.element());
            enter().text(cnnid).into(MiraAdmin.CNN_ID_FIELD).start();
            Thread.sleep(600);
            click().on(MiraAdmin.FORCE_ATL_ARCHIVE_BTN).start();
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder().to(Condition.CONTAIN)
                    .value("Successfully sent a forced archive message")
                    .forAMaxTimeOf(Duration.ofSeconds(40))).on(MiraAdmin.SUCCESS_MSG.element());
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }

}
