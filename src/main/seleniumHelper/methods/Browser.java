package seleniumHelper.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.sun.glass.ui.Window;

import commonClasses.sharedUtils.managers.LocalDriver;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.enums.BrowserObject;
import seleniumHelper.interfaces.IBrowser;

public class Browser extends Commands implements IBrowser {

    private void switchToDefaultContent() throws Exception {
        try {
            LocalDriver.getDriver().switchTo().defaultContent();
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    private void switchToNewWindow() throws Exception {
        try {
            for (String window: LocalDriver.getDriver().getWindowHandles()) {
                LocalDriver.getDriver().switchTo().window(window);
            }
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void switchTo(BrowserObject object, WebElement element) throws Exception {
        try {
            LocalDriver.getDriver().switchTo().frame(element);
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void switchTo(BrowserObject object, String selectorString, String by) throws Exception {
        try {
            LocalDriver.getDriver().switchTo().frame(getElement(selectorString, by));
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void switchTo(BrowserObject object, String name) throws Exception {
        try {
            LocalDriver.getDriver().switchTo().frame(name);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void close(BrowserObject object) throws Exception {
        try {
            String originalHandle = LocalDriver.getDriver().getWindowHandle();

            for (String handle: LocalDriver.getDriver().getWindowHandles()) {
                if (!handle.equals(originalHandle)) {
                    LocalDriver.getDriver().switchTo().window(handle);
                    LocalDriver.getDriver().close();
                }
            }

            LocalDriver.getDriver().switchTo().window(originalHandle);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void open(BrowserObject object) throws Exception {
        try {
            ((JavascriptExecutor) LocalDriver.getDriver()).executeScript("window.open();");
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void navigateTo(String url) throws Exception {
        try {
            LocalDriver.getDriver().navigate().to(url);
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void switchTo(BrowserObject object) throws Exception {
        try {
            switch (object) {
                case WINDOW:
                    switchToNewWindow();
                    break;
                case DEFAULTCONTENT:
                    switchToDefaultContent();
                    break;
                case ALERT:
                    LocalDriver.getDriver().switchTo().alert().accept();
                default:
                    throw new Exception("Please select an available browser object to switch to.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void waitForWindowCount(int i, int expectedCount) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    Boolean result = false;
                    int actualNumberofWindows = LocalDriver.getDriver().getWindowHandles().size();
                    try {
                        if (actualNumberofWindows == expectedCount) {
                            result = true;
                            return result;
                        }
                    } catch (StaleElementReferenceException ex) {
                        return result;
                    }
                    return result;
                };
            });
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

	@Override
	public void switchTo(BrowserObject object, int i) throws Exception {
        try {
        	Set<String> windows = LocalDriver.getDriver().getWindowHandles();
        	List<String> handles = new ArrayList<String>();
        	for (String window : windows) {
        		handles.add(window);
        	}
            LocalDriver.getDriver().switchTo().window(handles.get(i));
        } catch (WebDriverException ex) {
            throw ex;
        }
	}

}