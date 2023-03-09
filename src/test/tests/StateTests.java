package tests;

import org.apache.commons.lang3.NotImplementedException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.config.setup.app.State;
import com.config.setup.app.EnvironmentContext;
import com.config.setup.browser.DriverFacade;
import com.config.setup.browser.Drivers;
import com.config.setup.browser.HeadlessContext;
import com.config.setup.browser.HeadlessStop;
import com.config.setup.browser.Linux;
import com.config.setup.browser.OS;
import com.config.setup.browser.OSContext;
import com.config.setup.browser.OSFacade;
import com.config.setup.browser.Windows;

public class StateTests {
	
	@Test
	public void verifyHeadlessStopState() throws Exception {
		HeadlessContext context = new HeadlessContext();
		State headlessStopState = new HeadlessStop();
        context.setState(headlessStopState);
        context.doAction();
        State state = context.getState();
        Assert.assertNotNull(state);
	}

	@Test
	public void verifyLinuxState() throws Exception {
		OSContext context = new OSContext();
		State linux = new Linux();
        context.setState(linux);
        context.doAction();
        String value = System.getProperty("webdriver.chrome.driver");
        Assert.assertNotNull(value);
        State state = context.getState();
        Assert.assertNotNull(state);
	}
	
	@Test
	public void verifyWindowsState() throws Exception {
		OSContext context = new OSContext();
		State windows = new Windows();
        context.setState(windows);
        context.doAction();
        String value = System.getProperty("webdriver.chrome.driver");
        Assert.assertNotNull(value);
        State state = context.getState();
        Assert.assertNotNull(state);
	}
	
	@Test
	public void verifyHelperFacade() throws Exception {
		OSFacade.setDriverLocalPathBasedOnOS(OS.LINUX);
		OSFacade.setDriverLocalPathBasedOnOS(OS.WINDOWS);
	}
	
	@Test(expectedExceptions=NotImplementedException.class)
	public void verifyGetDriver_FF() throws Exception {
		DriverFacade.getDriver(Drivers.FIREFOX);
	}
	
	@Test(expectedExceptions=NotImplementedException.class)
	public void verifyGetDriver_Safari() throws Exception {
		DriverFacade.getDriver(Drivers.SAFARI);
	}
	
	@Test
	public void verifyEnvironmentCOntext() throws Exception {
		EnvironmentContext context = new EnvironmentContext();
		State headlessStopState = new HeadlessStop();
        context.setState(headlessStopState);
        context.doAction();
        State state = context.getState();
        Assert.assertNotNull(state);
	}

}
