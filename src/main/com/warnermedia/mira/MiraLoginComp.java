package com.warnermedia.mira;

import com.utils.CredentialsType;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.report.LocalValidation;
import com.warnermedia.config.settings.LocalTest;
import com.warnermedia.config.settings.SignInHelper;
import com.warnermedia.mira.web.inputs.MiraLogin;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;

import java.time.Duration;

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
            enter().text(new String(SignInHelper.getName(CredentialsType.MIRA)).trim()).into(MiraLogin.USER_NAME).start();
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(20)))
                    .on(MiraLogin.PASSWORD.element());
            enter().text(new String(SignInHelper.getPassword(CredentialsType.MIRA)).trim()).into(MiraLogin.PASSWORD).start();
            click().on(MiraLogin.LOGIN_BUTTON).start();
            Thread.sleep(2000);
            switchToMiraAppWindow();
            LocalValidation.getValidations().assertionPass("User is able to sign in to Mira successfully.");
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
        return this;
    }
}
