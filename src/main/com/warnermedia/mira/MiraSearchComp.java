package com.warnermedia.mira;

import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.report.LocalValidation;
import com.warnermedia.mira.web.inputs.MiraResults;
import com.warnermedia.mira.web.inputs.MiraSearch;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Via;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MiraSearchComp extends AbstractMiraPage {

    public MiraSearchComp() {

    }

    public MiraSearchComp searchForAsset(String cnnId, AssetStatus status) throws TestException {
        try {
            switchToSearchMainFrame();
            switch(status) {
                case NEEDS_TO_BE_ARCHIVED:
                    waitForAssetToChangeStatus(cnnId, "Central (Selections)");
                    break;
                case IS_ARCHIVED:
                    waitForAssetToChangeStatus(cnnId, "Central");
                    break;
                default:
            }

        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }

    private void waitForAssetToChangeStatus(String cnnId, String filter) throws TestException {
        try {
            switchToSearchMainFrame();
            if (SHelper.get().element().isDisplayed(MiraSearch.SEARCH_FIELD.element(), 1)) {
                enter().text(cnnId).into(MiraSearch.SEARCH_FIELD).start();
                Select select = new Select(
                        LocalDriver.getDriver().findElement(org.openqa.selenium.By.cssSelector(
                                MiraSearch.COLLECTION_FILTER.element().locator().value())));
                select.selectByVisibleText(filter);
                click().on(MiraSearch.SEARCH_BUTTON).start();
            }

            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), 1300);

            wait.until((WebDriver driver) -> {
                Boolean result = false;
                try {
                    switchToResultMainFrame();
                    SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(MiraResults.RECORD_CHECKBOX.element());
                    LocalValidation.getValidations().assertionPass("Asset has successfully processed.");
                    result = true;
                } catch (Exception ex) {
                    try {
                        switchToTaskBar();
                        click().on(MiraSearch.REFINE_BUTTON).start();
                        Thread.sleep(2000);
                        switchToSearchMainFrame();
                        click().on(MiraSearch.SEARCH_BUTTON).start();
                        Thread.sleep(1000);
                    } catch (Exception e) {

                    }
                    return result;
                }
                return result;
            });
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }
}
