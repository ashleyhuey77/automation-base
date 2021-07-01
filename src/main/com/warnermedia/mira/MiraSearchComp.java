package com.warnermedia.mira;

import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.mira.web.inputs.MiraResults;
import com.warnermedia.mira.web.inputs.MiraSearch;
import com.warnermedia.page.core.PageUtils;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class MiraSearchComp extends AbstractMiraPage {

    public MiraSearchComp() {

    }

    public MiraSearchComp searchForAsset(String cnnId, AssetStatus status) throws TestException {
        try {
            switchToSearchMainFrame();
            switch(status) {
                case NEEDS_TO_BE_ARCHIVED:
                    enter().text(cnnId).into(MiraSearch.SEARCH_FIELD).start();
                    Select select = new Select(
                            LocalDriver.getDriver().findElement(By.cssSelector(
                                    MiraSearch.COLLECTION_FILTER.element().locator().value())));
                    select.selectByVisibleText("Central (Selections)");
                    click().on(MiraSearch.SEARCH_BUTTON).start();
                    SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(60)).on(MiraResults.RECORDS_FOUND.element());
                    break;
                case IS_ARCHIVED:
                    enter().text(cnnId).into(MiraSearch.SEARCH_FIELD).start();
                    click().on(MiraSearch.SEARCH_BUTTON).start();
                    SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(60)).on(MiraResults.RECORDS_FOUND.element());
                    break;
                default:
            }

        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }
}
