package seleniumHelper.methods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;

import commonClasses.sharedUtils.managers.LocalDriver;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.enums.Location;
import seleniumHelper.interfaces.IPage;

public class Page extends Commands implements IPage {

    @Override
    public void scrollTo(Location location) throws Exception {
        try {
            JavascriptExecutor js = ((JavascriptExecutor) LocalDriver.getDriver());
            String locationString = null;
            switch (location) {
                case TOP_OF_PAGE:
                    locationString = "-document.body.scrollHeight";
                    break;
                case BOTTOM_OF_PAGE:
                    locationString = "document.body.scrollHeight";
                    break;
                default:
                    throw new Exception("Did not provide an accepted location on the page.");
            }

            js.executeScript("window.scrollTo(0, " + locationString + ")");

            Thread.sleep(600);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void refresh() throws Exception {
        try {
            LocalDriver.getDriver().navigate().refresh();
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

}