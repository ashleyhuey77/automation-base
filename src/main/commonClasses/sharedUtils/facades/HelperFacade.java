package commonClasses.sharedUtils.facades;

import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebDriver;
import commonClasses.sharedUtils.HtmlReport;
import commonClasses.sharedUtils.contexts.OSContext;
import commonClasses.sharedUtils.enums.*;
import commonClasses.sharedUtils.helpers.*;
import commonClasses.sharedUtils.interfaces.State;
import commonClasses.sharedUtils.managers.*;
import commonClasses.sharedUtils.states.*;

public class HelperFacade {

    public static WebDriver getDriver(Drivers driverType) throws Exception {
        WebDriver driver = null;
        switch (driverType) {
            case CHROME:
                ChromeDriverHelper cFac = new ChromeDriverHelper();
                driver = cFac.driver.get();
                break;
            case FIREFOX:
                throw new NotImplementedException("Nothing has been entered for Firefox. You will need to set up the driver before use.");
            case SAFARI:
                throw new NotImplementedException("Nothing has been entered for Safari. You will need to set up the driver before use.");
            default:
                throw new Exception("You did not enter a correct driver. Please enter a usable/executable driver name and try again.");
        }
        return driver;
    }

    public static void initializeReportType(ReportType type, commonClasses.sharedUtils.HtmlReport getHTMLReport) throws Exception {
        switch (type) {
            case REPORT:
                HtmlReport report = getHTMLReport;
                LocalReport.setHtmlReport(report);
                LocalReport.setReport(new ReportHelper(LocalReport.getHtmlReport()));
                break;
            case VALIDATIONS:
                LocalValidation.setValidations(new ValidationsHelper(LocalReport.getHtmlReport()));
                break;
            default:
                throw new Exception("User did not supply the correct Report Type. Unable to determine which report to initialize for testing.");
        }
    }

    public static void setDriverLocalPathBasedOnOS(OS os) throws Exception {
        try {
            State state = null;
            OSContext context = new OSContext();
            switch (os) {
                case MAC:
                    state = new Mac();
                    break;
                case WINDOWS:
                    state = new Windows();
                    break;
                case LINUX:
                    state = new Linux();
                    break;
                default:
                    throw new Exception("User did not provide a valid operating system. Unable to open browser because the operating system is unknown.");
            }
            context.setState(state);
            context.doAction();
        } catch (Exception ex) {
            throw ex;
        }
    }

}