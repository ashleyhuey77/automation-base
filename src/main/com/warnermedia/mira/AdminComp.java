package com.warnermedia.mira;

import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.report.ReportHelper;
import com.warnermedia.mira.web.inputs.Admin;
import com.warnermedia.selenium.browser.BrowserObject;
import com.warnermedia.selenium.wait.Condition;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;

public class AdminComp extends AbstractMiraPage {

    public AdminComp() {

    }

    public AdminComp selectTheRequestQueueOption() throws TestException {
        try {
            SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
            waitForFrameToBePresent("TaskArea");
            waitForFrameToBePresent("leftnav");
            click().on(Admin.RESUBMIT_BTN).start();
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }

    public AdminComp selectTheAdminOmneonArchivesOption() throws TestException {
        try {
            switchToWorkAreaFrame();
            click().on(Admin.ADMIN_OMNEON_ARCHIVES_OPT).start();
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }

    public AdminComp resubmitTheAssetAndForceATLArchive(String cnnid) throws TestException {
        try {
            switchToWorkAreaFrame();
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(30)).on(Admin.CNN_ID_FIELD.element());
            enter().text(cnnid).into(Admin.CNN_ID_FIELD).start();
            Thread.sleep(600);
            click().on(Admin.FORCE_ATL_ARCHIVE_BTN).start();
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder().to(Condition.CONTAIN)
                    .value("Successfully sent a forced archive message")
                    .forAMaxTimeOf(30)).on(Admin.CNN_ID_FIELD.element());
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }

}
