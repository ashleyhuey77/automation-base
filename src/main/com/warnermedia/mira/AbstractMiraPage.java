package com.warnermedia.mira;

import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.page.core.PageUtils;
import com.warnermedia.selenium.browser.BrowserObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public abstract class AbstractMiraPage extends PageUtils {

    public void switchToMiraAppWindow() throws TestException {
        try {
            switchToMiraWindow("CNN MIRA");
        } catch (Exception e) {
            LocalReport.getReport().reportException(e);
        }
    }

    protected void switchToMiraWindow(String title) throws TestException {
        try {
            Thread.sleep(2000);
            ArrayList<String> tabs = new ArrayList<>(LocalDriver.getDriver().getWindowHandles());

            for (String i : tabs) {
                String windTitle = LocalDriver.getDriver().switchTo().window(i).getTitle();
                if (windTitle.contains(title)) {
                    break;
                }
            }
        } catch (Exception e) {
            LocalReport.getReport().reportException(e);
        }
    }

    protected void switchToCommandBar() throws TestException {
        try {
            SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
            waitForFrameToBePresent("CommandBar");
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }

    protected void switchToServerTaskArea() throws TestException {
        try {
            SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
            waitForFrameToBePresent("TaskArea");
            waitForFrameToBePresent("SrvrFulfillTaskArea");
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }

    protected void switchToTaskBar() throws TestException {
        try {
            SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
            waitForFrameToBePresent("TaskArea");
            waitForFrameToBePresent("TaskBar");
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }

    protected void switchToMediaTaskArea() throws TestException {
        try {
            SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
            waitForFrameToBePresent("TaskArea");
            waitForFrameToBePresent("SrvrFulfillTaskArea");
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }

    protected void switchToSearchMainFrame() throws TestException {
        try {
            SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
            waitForFrameToBePresent("TaskArea");
            waitForFrameToBePresent("WorkArea");
            waitForFrameToBePresent("SearchMain");
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }

    protected void switchToResultMainFrame() throws TestException {
        try {
            SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
            waitForFrameToBePresent("TaskArea");
            waitForFrameToBePresent("WorkArea");
            waitForFrameToBePresent("SearchMain");
            waitForFrameToBePresent("Result_Main");
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }

    protected void switchToWorkAreaFrame() throws TestException {
        try {
            SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
            waitForFrameToBePresent("TaskArea");
            waitForFrameToBePresent("WorkArea");
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }

    protected void waitForFrameToBePresent(String frameName) throws TestException {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), Duration.ofSeconds(40));

            wait.until((WebDriver driver) -> {
                Boolean result = false;
                try {
                    SHelper.get().browser().switchTo(BrowserObject.FRAME, frameName);
                    result = true;
                } catch (Exception ex) {
                    return result;
                }
                return result;
            });
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }
}
