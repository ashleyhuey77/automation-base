package com.warnermedia.config.driver;

import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebDriver;
import com.warnermedia.config.TestException;

public class DriverFacade {
	
	public static WebDriver getDriver(Drivers driverType) throws TestException {
		WebDriver driver = null;
		switch (driverType) {
			case CHROME:
				ChromeDriverHelper cFac = new ChromeDriverHelper();
				driver = cFac.driver.get();
				break;
			case FIREFOX:
				throw new NotImplementedException(
						"Nothing has been entered for Firefox. You will need to set up the driver before use.");
			case SAFARI:
				throw new NotImplementedException(
						"Nothing has been entered for Safari. You will need to set up the driver before use.");
			default:
				throw new TestException(
						"You did not enter a correct driver. Please enter a usable/executable driver name and try again.");
		}
		return driver;
	}
}