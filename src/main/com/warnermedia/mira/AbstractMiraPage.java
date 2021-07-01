package com.warnermedia.mira;

import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.page.core.PageUtils;
import com.warnermedia.selenium.browser.BrowserObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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
            String currentWindow = LocalDriver.getDriver().getWindowHandle();  //will keep current window to switch back
            for(String winHandle : LocalDriver.getDriver().getWindowHandles()){
                if (LocalDriver.getDriver().switchTo().window(winHandle).getTitle().contains(title)) {
                    //This is the one you're looking for
                    break;
                } else {
                    LocalDriver.getDriver().switchTo().window(currentWindow);
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
            SHelper.get().browser().switchTo(BrowserObject.FRAME, "SrvrFulfillTaskArea");
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }

    protected void switchToTaskBar() throws TestException {
        try {
            SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
            waitForFrameToBePresent("TaskArea");
            SHelper.get().browser().switchTo(BrowserObject.FRAME, "TaskBar");
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }

    protected void switchToMediaTaskArea() throws TestException {
        try {
            waitForFrameToBePresent("TaskArea");
            SHelper.get().browser().switchTo(BrowserObject.FRAME, "SrvrFulfillTaskArea");
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }

    protected void switchToSearchMainFrame() throws TestException {
        try {
            SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
            waitForFrameToBePresent("WorkArea");
            SHelper.get().browser().switchTo(BrowserObject.FRAME, "WorkArea");
            waitForFrameToBePresent("SearchMain");
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }

    protected void switchToWorkAreaFrame() throws TestException {
        try {
            SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
            waitForFrameToBePresent("TaskArea");
            SHelper.get().browser().switchTo(BrowserObject.FRAME, "workarea");
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }

    protected void waitForFrameToBePresent(String frameName) throws TestException {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), 40);

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
