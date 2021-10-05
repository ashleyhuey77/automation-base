package com.warnermedia.mira;

import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.mira.web.inputs.MiraResults;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class MiraResultsComp extends AbstractMiraPage {

    public MiraResultsComp() {

    }

    public MiraResultsComp selectAllItems() throws TestException {
        try {
            switchToTaskBar();
            Select select = new Select(
                    LocalDriver.getDriver().findElement(By.cssSelector(
                            MiraResults.SELECT_ITEMS_DROPDOWN.element().locator().value())));
            select.selectByVisibleText("Select Current Page");
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }

    public MiraResultsComp selectItems() throws TestException {
        try {
            switchToResultMainFrame();
            click().on(MiraResults.RECORD_CHECKBOX).start();
            Thread.sleep(800);
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }

    public MiraResultsComp selectSendItemsToOption(SendItemsTo value) throws TestException {
        try {
            switchToTaskBar();
            Select select = new Select(
                    LocalDriver.getDriver().findElement(By.cssSelector(
                            MiraResults.SEND_ITEMS_TO_DROPDOWN.element().locator().value())));
            select.selectByValue(value.name().toLowerCase());
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }

    public MiraResultsComp selectTheSubmitButton() throws TestException {
        try {
            switchToTaskBar();
            click().on(MiraResults.SUBMIT_BUTTON).start();
            switchToSearchMainFrame();
            if (SHelper.get().element().isDisplayed(MiraResults.PROCESS_BUTTON.element(), 5)) {
                click().on(MiraResults.PROCESS_BUTTON).start();
            }
            if (SHelper.get().element().isDisplayed(MiraResults.OK_BUTTON.element(), 5)) {
                click().on(MiraResults.OK_BUTTON).start();
            }
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }

    public MiraResultsComp selectTheRecordNameLink() throws TestException {
        try {
            switchToSearchMainFrame();
            click().on(MiraResults.RECORD_DETAILS_LINK).start();
            Thread.sleep(2000);
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }
}
