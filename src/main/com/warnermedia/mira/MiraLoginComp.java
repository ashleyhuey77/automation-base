package com.warnermedia.mira;

import com.utils.CredentialsType;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.data.UserHelper;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.report.LocalValidation;
import com.warnermedia.config.settings.LocalTest;

import com.warnermedia.mira.web.inputs.MiraLogin;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class MiraLoginComp extends AbstractMiraPage {

    public MiraLoginComp() {

    }

    public MiraLoginComp switchToMiraLoginWindow() throws TestException {
        try {
            if (LocalTest.getEnvironment().getEnvironment().equalsIgnoreCase("dev")) {
                switchToMiraWindow("MIRA DEVELOPMENT");
            } else {
                switchToMiraWindow("Reference MIRA");
            }
        } catch (Exception e) {
            LocalReport.getReport().reportException(e);
        }
        return this;
    }

    public MiraLoginComp signInToMira() throws TestException {
        try {
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(20)))
                    .on(MiraLogin.USER_NAME.element());
            enter().text(new String(UserHelper.getName(CredentialsType.MIRA)).trim()).into(MiraLogin.USER_NAME).start();
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(20)))
                    .on(MiraLogin.PASSWORD.element());
            enter().text(new String(UserHelper.getPassword(CredentialsType.MIRA)).trim()).into(MiraLogin.PASSWORD).start();
            click().on(MiraLogin.LOGIN_BUTTON).start();
            Thread.sleep(2000);
            switchToMiraAppWindow();
            LocalValidation.getValidations().assertionPass(log, "User is able to sign in to Mira successfully.");
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
        return this;
    }
}
