package commonClasses.sharedUtils.facades;

import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebDriver;

import commonClasses.sharedUtils.HtmlReport;
import commonClasses.sharedUtils.enums.Drivers;
import commonClasses.sharedUtils.enums.ReportType;
import commonClasses.sharedUtils.helpers.ChromeDriverHelper;
import commonClasses.sharedUtils.helpers.ReportHelper;
import commonClasses.sharedUtils.helpers.ValidationsHelper;
import commonClasses.sharedUtils.managers.LocalReport;
import commonClasses.sharedUtils.managers.LocalValidation;

public class HelperFacade {
	
	public static WebDriver getDriver(Drivers driverType, Boolean isHeadless) throws Exception {
		WebDriver driver = null;
		switch(driverType)
		{
			case CHROME:
				ChromeDriverHelper cFac = new ChromeDriverHelper(isHeadless);
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
	
	public static void initializeReportType(ReportType type, WebDriver browser, commonClasses.sharedUtils.HtmlReport getHTMLReport) throws Exception {
		switch(type)
		{
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

}
