package seleniumHelper.methods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import common.utils.managers.LocalDriver;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.enums.Location;
import seleniumHelper.interfaces.IPage;

public class Page extends Commands implements IPage {

	@Override
    public void scrollTo(Location location) throws Exception {
        try {
            JavascriptExecutor js = ((JavascriptExecutor) LocalDriver.getDriver());
            String hLocationString = "0";
            String wLocationString = "0";
            switch (location) {
                case TOP_OF_PAGE:
                	hLocationString = "-document.body.scrollHeight";
                    break;
                case BOTTOM_OF_PAGE:
                	hLocationString = "document.body.scrollHeight";
                    break;
                case RIGHT_OF_PAGE:
                	wLocationString = "document.body.scrollWidth";
                	break;
                case LEFT_OF_PAGE:
                	wLocationString = "-document.body.scrollWidth";
                	break;
                default:
                    throw new Exception("Did not provide an accepted location on the page.");
            }

            js.executeScript("window.scrollTo(" + wLocationString + ", " + hLocationString + ")");

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